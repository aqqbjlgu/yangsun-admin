package com.youngsun.admin.controller;

import com.youngsun.admin.util.EncryptPasswordUtil;
import com.youngsun.admin.feign.UserFeignClient;
import com.youngsun.admin.util.ExceptionUtil;
import com.youngsun.admin.util.JsonUtils;
import com.youngsun.admin.util.Result;
import com.youngsun.admin.vo.UserVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by 国平 on 2016/10/23.
 */
@RestController
@RequestMapping("/user")
public class UserContorller {

    @Autowired
    private UserFeignClient userFeignClient;
    
    private static final Logger log = LoggerFactory.getLogger(UserContorller.class);

    @GetMapping(value = "/getAll/{belongTo}")
    public Result getAll(@PathVariable String belongTo){
        Result result;
        try {
            result = userFeignClient.getAll(belongTo);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result;
    }

    @GetMapping(value = "/getByUserVo")
    public Result getByUserVo(int page, int limit, UserVo userVo){
        Result result;
        try {
            String userVoString = JsonUtils.objectToJson(userVo);
            result = userFeignClient.getByUserVo(page, limit, userVoString);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }
    
    @GetMapping(value = "/getByEmail/{email}/{belongTo}")
    public Result getByEmail(@PathVariable String email, @PathVariable String belongTo){
        Result result;
        try {
            result = userFeignClient.getByEmail(email, belongTo);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }
    
    @GetMapping(value = "/getByIdCard/{idCard}/{belongTo}")
    public Result getByIdCard(@PathVariable String idCard, @PathVariable String belongTo){
        Result result;
        try {
            result = userFeignClient.getByIdCard(idCard, belongTo);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }
    
    @GetMapping(value = "/getByPhone/{phone}/{belongTo}")
    public Result getByPhone(@PathVariable String phone, @PathVariable String belongTo){
        Result result;
        try {
            result = userFeignClient.getByPhone(phone, belongTo);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }
    
    @GetMapping(value = "/getByNickName/{nickName}/{belongTo}")
    public Result getByNickName(@PathVariable String nickName, @PathVariable String belongTo){
        Result result;
        try {
            result = userFeignClient.getByNickName(nickName, belongTo);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @PostMapping(value = "/getByEmailPassword")
    public Result getByEmailPassword(String email, String password){
        Result result;
        try {
            result = userFeignClient.getByEmailPassword(email, password);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @PostMapping(value = "/getByPhonePassword")
    public Result getByPhonePassword(String phone, String password){
        Result result;
        try {
            result = userFeignClient.getByPhonePassword(phone, password);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @PostMapping(value = "/getByNickNamePassword")
    public Result getByNickNamePassword(String nickName, String password){
        Result result;
        try {
            result = userFeignClient.getByNickNamePassword(nickName, password);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable String id){
        userFeignClient.delete(id);
    }

    @PostMapping(value = "/save")
//    @RequiresPermissions({"system.user.add"})
    public Result save(@Valid @RequestBody UserVo userVo, BindingResult result){
        try {
            if (result.getErrorCount()>0){
                String errorMessage = "";
                for (FieldError error : result.getFieldErrors()){
                    errorMessage += error.getField()+ ":" + error.getDefaultMessage()+"</br>";
                }
                return Result.build(500, errorMessage, false);
            }
            if(StringUtils.isEmpty(userVo.getId())){
                userVo.setInsertDate(new Date());
                userVo.setInsertUserId(0L);
                userVo.setUpDateUserId(0L);
                userVo.setBelongTo("admin");
                userVo.setStatus("1");
                EncryptPasswordUtil.encryptPassword(userVo);
            }else{
                userVo.setUpDateDate(new Date());
                userVo.setInsertUserId(userVo.getInsertUserId());
            }
            String userString = JsonUtils.objectToJson(userVo);
            userFeignClient.save(userString);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return Result.ok(userVo);
    }

}
