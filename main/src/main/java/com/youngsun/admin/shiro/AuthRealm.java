package com.youngsun.admin.shiro;

import com.youngsun.admin.feign.PermissionFeignClient;
import com.youngsun.admin.feign.UserFeignClient;
import com.youngsun.admin.util.JsonUtils;
import com.youngsun.admin.util.Result;
import com.youngsun.admin.vo.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private PermissionFeignClient permissionFeignClient;
    private static final Logger log = LoggerFactory.getLogger(AuthRealm.class);
    
    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String usernamePrincipal = usernamePasswordToken.getPrincipal().toString().trim();
        String username = usernamePrincipal.split("_")[0];
        String belongTo = usernamePrincipal.split("_")[1];
        String ruleEmail = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        String rulePhoneNo = "^\\d+$";

        Result result;
        if (username.matches(ruleEmail)) {
            result = userFeignClient.getByEmail(username, belongTo);
        } else if (username.matches(rulePhoneNo)){
            result = userFeignClient.getByPhone(username, belongTo);
        } else {
            result = userFeignClient.getByNickName(username, belongTo);
        }
        String json = JsonUtils.objectToJson(result.getData());
        UserVo userVo = JsonUtils.jsonToPojo(json, UserVo.class);
        if (userVo == null) {
            throw new UnknownAccountException(); // 账号不存在
        }
        if (!userVo.getStatus().equals("1")) {
            throw new LockedAccountException(); // 帐号锁定
        }
        SimpleAuthenticationInfo authenticationInfo = null;
        if (userVo != null) {
            CmsUserVo cmsUserVo = new CmsUserVo();
            BeanUtils.copyProperties(userVo, cmsUserVo);
            result  = permissionFeignClient.getRoleByUserId(userVo.getId());
            String roleJson = JsonUtils.objectToJson(result.getData());
            List<RoleVo> roleVos = JsonUtils.jsonToList(roleJson, RoleVo.class);
            if (!CollectionUtils.isEmpty(roleVos)) {
                cmsUserVo.setRoleVos(roleVos);
                List<Long> roleIds = roleVos.stream().map(RoleVo:: getId).collect(Collectors.toList());
                Long[] strings = new Long[roleIds.size()];
                result = permissionFeignClient.getPermissionByRoleIds(roleIds.toArray(strings));
                String permissionJson = JsonUtils.objectToJson(result.getData());
                List<PermissionVo> permissionVos = JsonUtils.jsonToList(permissionJson, PermissionVo.class);
                cmsUserVo.setPermissionVos(permissionVos);
                List<PermissionVo> permissionVosExcludes = permissionVos.stream().filter(e -> e.getType().equals("menu")).collect(Collectors.toList());
                List<PermissionVo> paraPermissionVos = new CopyOnWriteArrayList<>(permissionVosExcludes);
                List<MenuPermissionVo> menuPermissionVos = new ArrayList<>();
                paraPermissionVos.forEach(e -> this.getMenuPermission(paraPermissionVos, menuPermissionVos, e));
                cmsUserVo.setMenuPermissionVo(menuPermissionVos);
            }
            authenticationInfo = new SimpleAuthenticationInfo(
                    cmsUserVo, //用户
                    cmsUserVo.getPassword(), //密码
                    ByteSource.Util.bytes(userVo.getNickName()),//盐
                    getName()  //realm name
            );
        }
//        this.doGetAuthorizationInfo(authenticationInfo.getPrincipals());
        return authenticationInfo;//放入shiro.调用CredentialsMatcher检验密码
    }

    private MenuPermissionVo getMenuPermission (List<PermissionVo> permissionVos, List<MenuPermissionVo> menuPermissionVos, PermissionVo permissionVo) {
        MenuPermissionVo menuPermissionVo = new MenuPermissionVo();
        menuPermissionVo.setIcon(permissionVo.getIconCls());
        menuPermissionVo.setLink(permissionVo.getUrl().replace(".", "/"));
        menuPermissionVo.setTitle(permissionVo.getPermissionName());
        if (!permissionVo.getLeaf()) {
            List<PermissionVo> children = permissionVos.stream().filter(child -> child.getParentId().longValue() == permissionVo.getId().longValue()).collect(Collectors.toList());
            for (PermissionVo child : children) {
                MenuPermissionVo childReturn = null;
                permissionVos.remove(child);
                if (!child.getLeaf()) {
                    childReturn = getMenuPermission(permissionVos, menuPermissionVos, child);
                } else {
                    childReturn = new MenuPermissionVo();
                    childReturn.setIcon(child.getIconCls());
                    childReturn.setLink(child.getUrl().replace(".", "/"));
                    childReturn.setTitle(child.getPermissionName());
                }
                if ( menuPermissionVo.getChildren() == null ) {
                    menuPermissionVo.setChildren(new ArrayList<>());
                }
                menuPermissionVo.getChildren().add(childReturn);
            }
            if (permissionVo.getParentId().equals(0L)) {
                menuPermissionVos.add(menuPermissionVo);
            }
        } else {
            if (permissionVo.getParentId().equals(0L)) {
                menuPermissionVos.add(menuPermissionVo);
            }
        }
        return menuPermissionVo;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        CmsUserVo user = (CmsUserVo) principal.getPrimaryPrincipal();
        List<String> permissions = user.getPermissionVos().stream().map(PermissionVo:: getPercode).collect(Collectors.toList());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }

}