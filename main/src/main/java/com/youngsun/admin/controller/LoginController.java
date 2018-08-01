package com.youngsun.admin.controller;

import com.youngsun.admin.util.Result;
import com.youngsun.admin.vo.CmsUserVo;
import com.youngsun.admin.vo.LoginUserVo;
import com.youngsun.admin.vo.MenuPermissionVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 国平 on 2017/6/29.
 */
@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @PostMapping("/login")
    public Result login(@RequestBody LoginUserVo loginUserVo)throws Exception{
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            String username = loginUserVo.getName()+"_"+loginUserVo.getBelongTo();
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, loginUserVo.getPassword());
            //rememberme
            if (loginUserVo.getRememberMe() !=null ) {
                token.setRememberMe(loginUserVo.getRememberMe());
            }
            try {
                // 执行登录.
                currentUser.login(token);
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类.
            catch (UnknownAccountException ae) {
                log.error("403", ae);
                token.clear();
                return Result.build(500, "账号不存在", false);
            }
            catch (IncorrectCredentialsException ie) {
                log.error("403", ie);
                token.clear();
                return Result.build(500, "用户名/密码错误", false);
            } catch (LockedAccountException le) {
                log.error("403", le);
                token.clear();
                return Result.build(500, "用户被锁定", false);
            }
            catch (Exception e) {
                log.error("403", e);
                token.clear();
                return Result.build(500, e.getMessage(), false);
            }
        }
        return Result.ok(currentUser.getPrincipal());
    }

    @GetMapping(value="/logout")
    public void logout(){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
    }

    @GetMapping(value="/isAuthenticated")
    public Boolean isAuthenticated(){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
           return false;
        }
        return true;
    }

    @GetMapping(value="/loadMenu")
    public List<MenuPermissionVo> loadMenu(){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return ((CmsUserVo)currentUser.getPrincipal()).getMenuPermissionVo();
        }
        return null;
    }

    @GetMapping(value="/getLoginUser")
    public Result getLoginUser(){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            return Result.build(403, "用户未登录", false);
        }
        return Result.ok(currentUser.getPrincipal());
    }

}
