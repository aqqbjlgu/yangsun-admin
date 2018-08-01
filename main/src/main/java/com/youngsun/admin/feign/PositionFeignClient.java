package com.youngsun.admin.feign;

import com.youngsun.admin.util.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 国平 on 2017/6/24.
 */
@FeignClient(name = "youngsun-rms-user")
public interface PositionFeignClient {
    @RequestMapping(value = "/position/getAll", method = RequestMethod.GET)
    Result getAll();

    @RequestMapping(value = "/position/deleteAll", method = RequestMethod.DELETE)
    Result deleteAll(@RequestParam("id") Long id);

    @RequestMapping(value = "/position/add", method = RequestMethod.POST)
    Result add(@RequestParam("positionVoString") String positionVoString);

    @RequestMapping(value = "/position/getByName", method = RequestMethod.GET)
    Result getByName(@RequestParam("name") String name);

    @RequestMapping(value = "/position/getBySn", method = RequestMethod.GET)
    Result getBySn(@RequestParam("sn") String sn);

    @RequestMapping(value = "/position/getByCondition", method = RequestMethod.POST)
    Result getByCondition(@RequestParam("positionVoString") String positionVoString);
}
