package com.youngsun.admin.controller;

import com.youngsun.admin.feign.OrganizationFeignClient;
import com.youngsun.admin.util.ExceptionUtil;
import com.youngsun.admin.util.JsonUtils;
import com.youngsun.admin.util.Result;
import com.youngsun.admin.vo.OrgVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by 国平 on 2016/10/23.
 */
@RestController
@RequestMapping("/org")
public class OrganizationController {
    @Autowired
    private OrganizationFeignClient organizationFeignClient;
    private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);
    @GetMapping(value = "/getAllLikeTree")
    Result getAllLikeTree() {
        Result result;
        try {
            result = organizationFeignClient.getAllLikeTree();
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false);
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @GetMapping(value = "/getAllOrganization")
    Result getAllOrganization() {
        Result result;
        try {
            result = organizationFeignClient.getAllOrganization();
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false);
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @GetMapping(value = "/getAllByName/{name}")
    Result getAllByName(@PathVariable String name) {
        Result result;
        try {
            result = organizationFeignClient.getAllByName(name);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false);
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @PostMapping(value = "/save")
    @RequiresPermissions({"system.org.add"})
    public Result save(@RequestBody @Valid OrgVo orgVo, BindingResult bindingResult){
        Result result;
        try {
            if (bindingResult.getErrorCount()>0){
                String errorMessage = "";
                for (FieldError error : bindingResult.getFieldErrors()){
                    errorMessage += error.getField()+ ":" + error.getDefaultMessage()+"</br>";
                }
                return Result.build(500, errorMessage, false);
            }
            if(orgVo.getId() == null){
                orgVo.setInsertDate(new Date());
                orgVo.setInsertUserId(orgVo.getInsertUserId());
                orgVo.setLeaf(true);
            }
            orgVo.setUpDateDate(new Date());
            orgVo.setUpDateUserId(orgVo.getUpDateUserId());
            if(orgVo.getParentId() == null){
                orgVo.setParentId(0L);
            }
            result = organizationFeignClient.save(JsonUtils.objectToJson(orgVo));
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result;
    }

}
