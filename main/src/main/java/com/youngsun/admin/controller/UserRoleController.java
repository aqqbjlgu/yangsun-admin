package com.youngsun.admin.controller;

import com.youngsun.admin.feign.PermissionFeignClient;
import com.youngsun.admin.util.ExceptionUtil;
import com.youngsun.admin.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 国平 on 2017/6/28.
 */
@RestController
public class UserRoleController {
    @Autowired
    private PermissionFeignClient permissionFeignClient;
    private static final Logger log = LoggerFactory.getLogger(UserRoleController.class);

    @PostMapping(value = "/user/role/add/{userId}")
    public Result saveUserRole(@RequestBody String userRoleVos, @PathVariable Long userId){
        try {
            permissionFeignClient.saveUserRole(userRoleVos, userId);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false);
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return Result.ok("");
    }
}
