package com.youngsun.admin.controller;

import com.youngsun.admin.feign.PositionFeignClient;
import com.youngsun.admin.util.ExceptionUtil;
import com.youngsun.admin.util.JsonUtils;
import com.youngsun.admin.util.Result;
import com.youngsun.admin.vo.PositionVo;
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

/**
 * Created by 国平 on 2016/10/23.
 */
@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionFeignClient positionFeignClient;
    private static final Logger log = LoggerFactory.getLogger(PositionController.class);
    @GetMapping(value = "/getAll")
    public Result getAll(){
        Result result;
        try {
            result = positionFeignClient.getAll();
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false);
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }
    
    @DeleteMapping(value = "/deleteAll/{id}")
    public Result deleteAll(@PathVariable Long id){
        try {
            positionFeignClient.deleteAll(id);
        }catch (Exception e){
            log.error("500", e);
            if(e instanceof RuntimeException){
                return Result.build(500, ExceptionUtil.getStackTrace(e), false);
            }
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return Result.build(200,"删除成功",true);
    }
    
    @PostMapping(value = "/add")
    @RequiresPermissions({"system.position.add"})
    public Result add(@RequestBody @Valid PositionVo positionVo, BindingResult bindingResult){
        positionVo.setBelongTo("admin");
        Result result = null;
        try {
            if (bindingResult.getErrorCount()>0){
                String errorMessage = "";
                for (FieldError error : bindingResult.getFieldErrors()){
                    errorMessage += error.getField()+ ":" + error.getDefaultMessage()+"</br>";
                }
                return Result.build(500, errorMessage, false);
            }
            if(positionVo.getId() == null){
                positionVo.setInsertDate(new Date());
//                positionVo.setInsertUserId(session.getAttribute("userId") == null ? -1L : (Long) session.getAttribute("userId"));
                positionVo.setInsertUserId(-1L);
            }
            positionVo.setUpDateDate(new Date());
//            positionVo.setUpDateUserId(session.getAttribute("userId") == null ? -1L : (Long) session.getAttribute("userId"));
            positionVo.setUpDateUserId(-1L);
            result = positionFeignClient.add(JsonUtils.objectToJson(positionVo));
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @GetMapping(value = "/getByName/{name}")
    public Result getByName(@PathVariable String name){
        Result result = null;
        try {
            result = positionFeignClient.getByName(StringUtils.trimAllWhitespace(name));
        }catch (Exception e){
            e.printStackTrace();
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @GetMapping(value = "/getBySn/{sn}")
    public Result getBySn(@PathVariable String sn){
        Result result = null;
        try {
            result = positionFeignClient.getBySn(StringUtils.trimAllWhitespace(sn));
        }catch (Exception e){
            e.printStackTrace();
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @PostMapping(value = "/getByCondition")
    public Result getByCondition(@RequestBody PositionVo positionVo){
        Result result;
        try {
            result = positionFeignClient.getByCondition(JsonUtils.objectToJson(positionVo));
        }catch (Exception e){
            e.printStackTrace();
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }
}
