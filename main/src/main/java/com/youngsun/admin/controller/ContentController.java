package com.youngsun.admin.controller;

import com.github.pagehelper.PageInfo;
import com.youngsun.admin.cms.service.ContentService;
import com.youngsun.admin.cms.vo.ContentSimpleVo;
import com.youngsun.admin.cms.vo.ContentVo;
import com.youngsun.admin.util.JsonUtils;
import com.youngsun.admin.util.Result;
import com.youngsun.admin.vo.CmsUserVo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by 国平 on 2017/6/29.
 */
@RestController
public class ContentController {
    private static final Logger log = LoggerFactory.getLogger(ContentController.class);
    @Autowired
    private ContentService contentService;
    @GetMapping ("/content/getAllContentVo/{page}/{rows}")
    public Result getAllContentVo(@PathVariable Integer page, @PathVariable Integer rows){
        PageInfo<ContentSimpleVo> pageInfo = null;
        try {
            pageInfo = contentService.getAllContentVo(page, rows);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(pageInfo);
    }

    @GetMapping ("/content/getContentById/{id}")
    public Result getContentById(@PathVariable Long id){
        ContentVo contentVo = null;
        try {
            contentVo = contentService.getContentVoById(id);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(contentVo);
    }

    @PostMapping ("/content/getByContentVo/{page}/{rows}")
    public Result getByContentVo(@PathVariable Integer page, @PathVariable Integer rows, @RequestBody ContentVo contentVo){
        PageInfo<ContentSimpleVo> pageInfo = null;
        try {
            pageInfo = contentService.getByContentVo(page, rows, contentVo);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(pageInfo);
    }

    @PostMapping("/content/delete")
    public Result delete(@RequestBody String contentSimpleVoString) {
        try {
            contentService.delete(JsonUtils.jsonToList(contentSimpleVoString, ContentSimpleVo.class));
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(null);
    }
    @PostMapping ("/content/save")
    public Result save(@RequestBody ContentVo contentVo) {
        Integer count = null;
        CmsUserVo user = (CmsUserVo) SecurityUtils.getSubject().getPrincipal();
        try {
            if(contentVo.getId() == null){
                contentVo.setInsertUserId(user.getId() == null ? -1L : user.getId());
                contentVo.setInsertDate(new Date());
            }
            contentVo.setUpDateDate(new Date());
            contentVo.setUpDateUserId(user.getId() == null ? -1L : user.getId());
            contentVo.setHasDeleted(false);
            count = contentService.save(contentVo);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(count);
    }

    @PostMapping ("/content/update")
    public Result update(@RequestBody ContentVo contentVo) {
        Integer count = null;
        try {
            count = contentService.save(contentVo);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(count);
    }

    @GetMapping ("/content/getByChannelId/{page}/{rows}/{channelId}")
    public Result getContentByChannelId(@PathVariable Integer page, @PathVariable Integer rows, @PathVariable Long channelId){
        PageInfo<ContentSimpleVo> contentVo = null;
        try {
            contentVo = contentService.getByChannelId(page, rows, channelId);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(contentVo);
    }

    @GetMapping ("/content/getSimplePictureContentById/{id}")
    public Result getSimplePictureContentById(@PathVariable Long id){
        List<ContentSimpleVo> contentVos = null;
        try {
            contentVos = contentService.getSimplePictureContentById(id);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(contentVos);
    }

    @GetMapping ("/content/getByType/{type}/{limit}")
    public Result getByType(@PathVariable String type, @PathVariable Integer limit){
        List<ContentSimpleVo> contentVos = null;
        try {
            contentVos = contentService.getByHot(type, limit);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);
        }
        return Result.ok(contentVos);
    }
}
