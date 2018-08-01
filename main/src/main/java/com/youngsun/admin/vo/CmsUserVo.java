package com.youngsun.admin.vo;


import java.io.Serializable;
import java.util.List;

/**
 * Created by 国平 on 2017/6/28.
 */
public class CmsUserVo extends UserVo implements Serializable {

    private List<RoleVo> roleVos;

    private List<MenuPermissionVo> menuPermissionVo;

    private List<PermissionVo> permissionVos;

    public List<RoleVo> getRoleVos() {
        return roleVos;
    }

    public void setRoleVos(List<RoleVo> roleVos) {
        this.roleVos = roleVos;
    }

    public List<MenuPermissionVo> getMenuPermissionVo() {
        return menuPermissionVo;
    }

    public void setMenuPermissionVo(List<MenuPermissionVo> menuPermissionVo) {
        this.menuPermissionVo = menuPermissionVo;
    }

    public List<PermissionVo> getPermissionVos() {
        return permissionVos;
    }

    public void setPermissionVos(List<PermissionVo> permissionVos) {
        this.permissionVos = permissionVos;
    }
}
