package com.youngsun.admin.controller;

import com.youngsun.admin.feign.PermissionFeignClient;
import com.youngsun.admin.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by 国平 on 2017/6/28.
 */
@RestController
public class RolePermissionController {
    @Autowired
    private PermissionFeignClient permissionFeignClient;
    private static final Logger log = LoggerFactory.getLogger(RolePermissionController.class);


    @PostMapping(value = "/rolePermission/add/{roleId}")
    public Result save(@RequestBody String rolePermissionVos, @PathVariable String roleId){
        Result result;
        try {
            result = permissionFeignClient.save(rolePermissionVos, roleId);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result;
    }
}
