package com.youngsun.admin.feign;

import com.youngsun.admin.util.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 国平 on 2017/6/24.
 */
@FeignClient(name = "youngsun-rms-user")
public interface UserFeignClient {
    @RequestMapping(value = "/user/getByPhone/{phone}/{belongTo}", method = RequestMethod.GET)
    Result getByPhone(@PathVariable("phone") String phone, @PathVariable("belongTo") String belongTo);

    @RequestMapping(value = "/user/getByNickName/{nickName}/{belongTo}", method = RequestMethod.GET)
    Result getByNickName(@PathVariable("nickName") String nickName, @PathVariable("belongTo") String belongTo);

    @RequestMapping(value = "/user/getByEmail/{email}/{belongTo}", method = RequestMethod.GET)
    Result getByEmail(@PathVariable("email") String email, @PathVariable("belongTo") String belongTo);

    @RequestMapping(value = "/user/getAll", method = RequestMethod.GET)
    Result getAll(@RequestParam("belongTo") String belongTo);

    @RequestMapping(value = "/user/getByUserVo", method = RequestMethod.GET)
    Result getByUserVo(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam("userVoString") String userVoString);

    @RequestMapping(value = "/user/getByIdCard/{idCard}/{belongTo}", method = RequestMethod.GET)
    Result getByIdCard(@PathVariable("idCard") String idCard, @PathVariable("belongTo") String belongTo);

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") String id);

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    Result save(@RequestParam("userString") String userString);

    @RequestMapping(value = "/user/org/pos/save/{userId}", method = RequestMethod.POST)
    Result saveUserOrgPos(@RequestBody String userOrgPosVos, @PathVariable("userId") Long userId);

    @RequestMapping(value = "/user/getByPhonePassword", method = RequestMethod.POST)
    Result getByPhonePassword(@RequestParam("phone") String phone, @RequestParam("password") String password);

    @RequestMapping(value = "/user/getByNickNamePassword", method = RequestMethod.POST)
    Result getByNickNamePassword(@RequestParam("nickName") String nickName, @RequestParam("password") String password);

    @RequestMapping(value = "/user/getByEmailPassword", method = RequestMethod.POST)
    Result getByEmailPassword(@RequestParam("email") String email, @RequestParam("password") String password);
}