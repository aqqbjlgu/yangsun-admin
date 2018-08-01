package com.youngsun.admin.feign;

import com.youngsun.admin.util.Result;
import com.youngsun.admin.vo.dto.NavigationTreeDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 国平 on 2017/6/24.
 */
@FeignClient(name = "youngsun-rms-user")
public interface PermissionFeignClient {
    @RequestMapping(value = "/permission/getPermissionByRoleIds", method = RequestMethod.GET)
    Result getPermissionByRoleIds(@RequestParam("roleIds") Long[] roleIds);

    @RequestMapping(value = "/role/getByUserId", method = RequestMethod.GET)
    Result getRoleByUserId(@RequestParam("userId") Long userId);

    @RequestMapping(value = "/permission/getAllPermissions/{belongTo}", method = RequestMethod.GET)
    Result getAllPermissions(@RequestParam("start") Integer start, @RequestParam("limit") Integer limit, @PathVariable("belongTo") String belongTo);

    @RequestMapping(value = "/permission/getPermissionByParentId", method = RequestMethod.GET)
    Result getPermissionByParentId(@RequestParam("node") String node);

    @RequestMapping(value = "/permission/getPermissionByRoleId/{roleId}", method = RequestMethod.GET)
    Result getPermissionByRoleId(@PathVariable("roleId") String roleId);

    @RequestMapping(value = "/role/getAllRoles/{belongTo}", method = RequestMethod.GET)
    Result getAllRoles(@PathVariable("belongTo") String belongTo);

    @RequestMapping(value = "/role/getAllRolesByUserId/{userId}", method = RequestMethod.GET)
    Result getAllRolesByUserId(@RequestParam("page") int page, @RequestParam("limit") int limit, @PathVariable("userId") String userId);

    @RequestMapping(value = "/permission/getByPermissionVo", method = RequestMethod.GET)
    Result getByPermissionVo(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam("permissionVoString") String permissionVoString);

    @RequestMapping(value = "/permission/save", method = RequestMethod.POST)
    Result save(@RequestParam("permissionVoString") String permissionVoString);

    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    Result roleSave(@RequestParam("roleVoString") String roleVoString);

    @RequestMapping(value = "/permission/delete/{id}/{leaf}/{parentId}", method = RequestMethod.GET)
    Result delete(@PathVariable("id") String id, @PathVariable("leaf") Boolean leaf, @PathVariable("parentId") String parentId);

    @RequestMapping(value = "/role/getByRoleVo", method = RequestMethod.GET)
    Result getByRoleVo(@PathVariable("start") Integer start, @PathVariable("limit") Integer limit, @PathVariable("roleVoString") String roleVoString);

    @RequestMapping(value = "/role/delete", method = RequestMethod.PUT)
    Result delete(@RequestParam("id") String id);

    @RequestMapping(value = "/rolePermission/add/{roleId}", method = RequestMethod.POST)
    Result save(@RequestBody String rolePermissionVos, @PathVariable("roleId") String roleId);

    @RequestMapping(value = "/user/role/add/{userId}", method = RequestMethod.POST)
    Result saveUserRole(@RequestBody String userRoleVos, @PathVariable("userId") Long userId);

    @RequestMapping(value = "/permission/getNavigationTree", consumes = "application/json", method = RequestMethod.POST)
    List<NavigationTreeDto> getNavigationTree(String permissionVos);

    @RequestMapping(value = "/permission/getAllPermissionWithTree/{belongTo}", method = RequestMethod.GET)
    Result getAllPermissionWithTree(@PathVariable("belongTo") String belongTo);

    @RequestMapping(value = "/permission/getPermissionsByPercode/{percode}/{belongTo}", method = RequestMethod.GET)
    Result getPermissionsByPercode(@PathVariable("percode") String percode, @PathVariable("belongTo") String belongTo);

    @RequestMapping(value = "/permission/getPermissionsByUrl/{url}/{belongTo}", method = RequestMethod.GET)
    Result getPermissionsByUrl(@PathVariable("url") String url, @PathVariable("belongTo") String belongTo);

    @RequestMapping(value = "/permission/getPermissionsByName/{name}/{belongTo}", method = RequestMethod.GET)
    Result getPermissionsByName(@PathVariable("name") String name, @PathVariable("belongTo") String belongTo);

    @RequestMapping(value = "/role/getByRoleName", method = RequestMethod.GET)
    public Result getByRoleName(@RequestParam("name") String name, @RequestParam("belongTo")String belongTo);
}
