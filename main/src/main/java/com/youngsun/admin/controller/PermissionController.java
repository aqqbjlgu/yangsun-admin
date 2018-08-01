package com.youngsun.admin.controller;

import com.youngsun.admin.feign.PermissionFeignClient;
import com.youngsun.admin.util.ExceptionUtil;
import com.youngsun.admin.util.JsonUtils;
import com.youngsun.admin.util.Result;
import com.youngsun.admin.vo.PermissionVo;
import com.youngsun.admin.vo.dto.NavigationTreeDto;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 国平 on 2017/6/28.
 */
@RestController
public class PermissionController {
    @Autowired
    private PermissionFeignClient permissionFeignClient;
    private static final Logger log = LoggerFactory.getLogger(PermissionController.class);

    @GetMapping(value = "/permission/getPermissionByRoleId/{roleId}")
    public Result getPermissionByRoleId(@PathVariable String roleId){
        Result result;
        try {
            result = permissionFeignClient.getPermissionByRoleId(roleId);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result;
    }

    @GetMapping(value = "/permission/getPermissionByRoleIds")
    public Result getPermissionByRoleIds(Long [] roleIds){
        Result result;
        try {
            result = permissionFeignClient.getPermissionByRoleIds(roleIds);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @GetMapping(value = "/permission/getAllPermissions/{belongTo}")
    public Result getAllPermissions(Integer start, Integer limit, @PathVariable String belongTo){
        Result result;
        try {
            result = permissionFeignClient.getAllPermissions(start, limit, belongTo);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @GetMapping(value = "/permission/getByPermissionVo")
    public Result getByPermissionVo(int page, int limit, PermissionVo permissionVo){
        Result result;
        String permissionVoString = JsonUtils.objectToJson(permissionVo);
        try {
            result = permissionFeignClient.getByPermissionVo(page, limit, permissionVoString);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    // todo belongTo 要从session里拿
    @GetMapping(value = "/permission/getAllPermissionWithTree/{belongTo}")
    public Result getAllPermissionWithTree(@PathVariable("belongTo") String belongTo){
        Result result;
        try {
            result = permissionFeignClient.getAllPermissionWithTree(belongTo);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result;
    }

    @PostMapping(value = "/permission/save")
    @RequiresPermissions({"system.resource.add"})
    public Result save(@RequestBody @Valid PermissionVo permissionVo, BindingResult result){
        Result result1;
        try {
//            Session session = SecurityUtils.getSubject().getSession();
            if (result.getErrorCount()>0){
                String errorMessage = "";
                for (FieldError error : result.getFieldErrors()){
                    errorMessage += error.getField()+ ":" + error.getDefaultMessage()+"</br>";
                }
                return Result.build(500, errorMessage, false);
            }
            permissionVo.setUpDateDate(new Date());
//            permissionVo.setUpDateUserId(session.getAttribute("userSession") == null ? -1L : ((CmsUserVo)session.getAttribute("userSession")).getId());
//            permissionVo.setInsertUserId(session.getAttribute("userSession") == null ? -1L : ((CmsUserVo)session.getAttribute("userSession")).getId());
            permissionVo.setUpDateUserId(-1L);
            permissionVo.setInsertUserId(-1L);
            if(permissionVo.getParentId() == null){
                permissionVo.setParentId(0L);
            }
            String permissionVoString = JsonUtils.objectToJson(permissionVo);
            result1 = permissionFeignClient.save(permissionVoString);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result1;
    }

    @GetMapping(value = "/permission/delete/{id}/{leaf}/{parentId}")
    public Result delete(@PathVariable String id, @PathVariable Boolean leaf, @PathVariable String parentId){
        Result result;
        try {
            result = permissionFeignClient.delete(id, leaf, parentId);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    @GetMapping(value = "/permission/getPermissionByParentId")
    public Result getPermissionByParentId(String node){
        Result result;
        try {
            result = permissionFeignClient.getPermissionByParentId(node);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, ExceptionUtil.getStackTrace(e), false);
        }
        return result;
    }

    private List<NavigationTreeDto> getNavigationTree(List<PermissionVo> permissionVos) {
        List<NavigationTreeDto> navigationTreeDtos = new ArrayList<>();
        List<PermissionVo> permissionVoList = permissionVos.stream().filter(e -> e.getType().equals("1")).collect(Collectors.toList());
        permissionVoList.stream().forEach(e -> {
            NavigationTreeDto navigationTreeDto = new NavigationTreeDto();
            navigationTreeDto.setText(e.getPermissionName());
            navigationTreeDto.setLeaf(e.getLeaf());
            navigationTreeDto.setView(e.getUrl());
            navigationTreeDto.setRouteId(e.getUrl());
            navigationTreeDto.setIconCls(e.getIconCls());
            if (e.getLeaf()) {
                navigationTreeDtos.add(navigationTreeDto);
            }
            permissionVoList.stream().forEach(e1 -> {
                if (e1.getParentId().equals(e.getId())) {
                    if (e.getChildren() == null) {
                        e.setChildren(new ArrayList());
                    }
                    e.getChildren().add(e1);
                }
            });
        });
        return navigationTreeDtos;
    }

    // todo belongTo 要从session里拿
    @GetMapping(value = "/permission/getPermissionByName/{name}/{belongTo}")
    public Result getPermissionByName(@PathVariable("name") String name, @PathVariable("belongTo") String belongTo){
        Result result;
        try {
            result = permissionFeignClient.getPermissionsByName(name, belongTo);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result;
    }
    // todo belongTo 要从session里拿
    @GetMapping(value = "/permission/getPermissionByUrl/{url}/{belongTo}")
    public Result getPermissionByUrl(@PathVariable("url") String url, @PathVariable("belongTo") String belongTo){
        Result result;
        try {
            result = permissionFeignClient.getPermissionsByUrl(url, belongTo);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result;
    }
    // todo belongTo 要从session里拿
    @GetMapping(value = "/permission/getPermissionsByPercode/{percode}/{belongTo}")
    public Result getPermissionsByPercode(@PathVariable("percode") String percode, @PathVariable("belongTo") String belongTo){
        Result result;
        try {
            result = permissionFeignClient.getPermissionsByPercode(percode, belongTo);
        }catch (Exception e){
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return result;
    }

}
