package com.youngsun.admin.controller;

import com.youngsun.admin.cms.service.ChannelService;
import com.youngsun.admin.cms.vo.ChannelSimpleVo;
import com.youngsun.admin.cms.vo.ChannelVo;
import com.youngsun.admin.util.JsonUtils;
import com.youngsun.admin.util.Result;
import com.youngsun.admin.vo.CmsUserVo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by 国平 on 2017/6/29.
 */
@RestController
public class ChannelController {
    private static final Logger log = LoggerFactory.getLogger(ChannelController.class);
    @Autowired
    private ChannelService channelService;
    @GetMapping ("/channel/getAllChannelVo")
    public Result getAllChannelVo(){
        List<ChannelSimpleVo> pageInfo = null;
        try {
            pageInfo = channelService.getAllChannelVo();
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(pageInfo);
    }

//    getAllChannelWithTree
    @GetMapping ("/channel/getAllChannelWithTree")
    public Result getAllChannelWithTree(){
        List<ChannelSimpleVo> pageInfo = null;
        try {
            pageInfo = channelService.getAllChannelWithTree();
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(pageInfo);
    }

    @GetMapping ("/channel/getChannelById/{id}")
    public Result getChannelById(@PathVariable Long id){
        ChannelVo channelVo = null;
        try {
            channelVo = channelService.getChannelVoById(id);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(channelVo);
    }

    @PostMapping ("/channel/getByChannelVo/")
    public Result getByChannelVo(@RequestBody ChannelVo channelVo){
        List<ChannelSimpleVo> pageInfo = null;
        try {
            pageInfo = channelService.getByChannelVo(channelVo);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(pageInfo);
    }

    @GetMapping ("/channel/getChannelVoByParentId/{parentId}")
    public Result getChannelVoByParentId(@PathVariable Long parentId){
        List<ChannelVo> channelVos = null;
        try {
            channelVos = channelService.getChannelVoByParentId(parentId);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(channelVos);
    }

    @PostMapping("/channel/delete")
    public Result delete(@RequestBody String channelSimpleVoString) {
        try {
            channelService.delete(JsonUtils.jsonToList(channelSimpleVoString, ChannelSimpleVo.class));
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(null);
    }
    @PostMapping ("/channel/save")
    public Result save(@RequestBody ChannelVo channelVo) {
        Integer count = null;
        CmsUserVo user = (CmsUserVo) SecurityUtils.getSubject().getPrincipal();
        try {
            if(channelVo.getId() == null){
                channelVo.setInsertDate(new Date());
                channelVo.setInsertUserId(user.getId() == null ? -1L : user.getId());
            }
            channelVo.setUpDateDate(new Date());
            channelVo.setUpDateUserId(user.getId() == null ? -1L : user.getId());
            channelVo.setHasDeleted(false);
            count = channelService.save(channelVo);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(count);
    }
}
