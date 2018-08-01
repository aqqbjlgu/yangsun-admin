package com.youngsun.admin.feign;

import com.youngsun.admin.util.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 国平 on 2017/6/24.
 */
@FeignClient(name = "youngsun-organization")
public interface OrganizationFeignClient {
    @RequestMapping(value = "/org/getAllLikeTree", method = RequestMethod.GET)
    Result getAllLikeTree();

    @RequestMapping(value = "/org/getAllOrganization", method = RequestMethod.GET)
    Result getAllOrganization();

    @RequestMapping(value = "/org/getAllByName/{name}", method = RequestMethod.GET)
    Result getAllByName(@PathVariable("name") String name);

    @RequestMapping(value = "/org/save", method = RequestMethod.POST)
    Result save(@RequestParam("orgVoString") String orgVoString);


}
