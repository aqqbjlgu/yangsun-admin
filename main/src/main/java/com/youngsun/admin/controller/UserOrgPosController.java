package com.youngsun.admin.controller;

import com.youngsun.admin.feign.UserFeignClient;
import com.youngsun.admin.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 国平 on 2017/6/28.
 */
@RestController
@RequestMapping("/user/org/pos")
public class UserOrgPosController {
    @Autowired
    private UserFeignClient userFeignClient;
    private static final Logger log = LoggerFactory.getLogger(UserOrgPosController.class);

    @PostMapping(value = "/save/{userId}")
    public Result saveUserOrgPos(@RequestBody String userOrgPosVos, @PathVariable Long userId){
        Result result;
        try {
            result = userFeignClient.saveUserOrgPos(userOrgPosVos, userId);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return Result.ok(result);
    }
}
