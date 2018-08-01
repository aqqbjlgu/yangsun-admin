package com.youngsun.admin.controller;

import com.youngsun.admin.feign.PermissionFeignClient;
import com.youngsun.admin.util.ExceptionUtil;
import com.youngsun.admin.util.JsonUtils;
import com.youngsun.admin.util.Result;
import com.youngsun.admin.vo.RoleVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by 国平 on 2017/6/28.
 */
@RestController
public class RoleController {
    @Autowired
    private PermissionFeignClient permissionFeignClient;
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);
    @GetMapping(value = "/role/getRoleByUserId/{userId}")
    public Result getRoleByUserId(@PathVariable Long userId){
        Result result;
        try {
            result = permissionFeignClient.getRoleByUserId(userId);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result;
    }

    @GetMapping(value = "/role/getAllRoles")
    public Result getAllRoles(){
        Result result;
        try {
            //todo 以后换成session
            result = permissionFeignClient.getAllRoles("admin");
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result;
    }

    @GetMapping(value = "/role/getByRoleVo")
    public Result getByRoleVo(Integer start, Integer limit, RoleVo roleVo){
        Result result;
        String roleVoString = JsonUtils.objectToJson(roleVo);
        try {
            result = permissionFeignClient.getByRoleVo(start, limit, roleVoString);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false);
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @PostMapping(value = "/role/save")
    @RequiresPermissions({"system.role.add"})
    public Result save(@RequestBody RoleVo roleVo, BindingResult result){
        Result resultReturn;
        try {
            if (result.getErrorCount()>0){
                String errorMessage = "";
                for (FieldError error : result.getFieldErrors()){
                    errorMessage += error.getField()+ ":" + error.getDefaultMessage()+"</br>";
                }
                return Result.build(500, errorMessage, false);
            }
            roleVo.setUpDateDate(new Date());
            roleVo.setUpDateUserId(-1L);
            roleVo.setInsertUserId(-1L);
//            roleVo.setUpDateUserId(session.getAttribute("userId")== null ? -1L : (Long) session.getAttribute("userId"));
//            roleVo.setInsertUserId(session.getAttribute("userId")== null ? -1L : (Long) session.getAttribute("userId"));
            String roleVoString = JsonUtils.objectToJson(roleVo);
            resultReturn = permissionFeignClient.roleSave(roleVoString);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false);
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return resultReturn;
    }

    @PutMapping (value = "/role/delete")
    public Result delete(@RequestBody String id){
        Result result;
        try {
            result = permissionFeignClient.delete(id);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result;
    }

    @GetMapping(value = "/role/getAllRolesByUserId/{userId}")
    public Result getAllRolesByUserId(int page, int limit, @PathVariable String userId){
        Result result;
        try {
            result = permissionFeignClient.getAllRolesByUserId(page, limit, userId);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false);
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return Result.ok(result);
    }

    @GetMapping(value = "/role/getByName/{name}/{belongTo}")
    public Result getByName(@PathVariable("name") String name, @PathVariable("belongTo") String belongTo) {
        Result result;
        try {
            result = permissionFeignClient.getByRoleName(name, belongTo);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false);
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }


}
