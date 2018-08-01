package com.youngsun.admin.controller;

import com.github.pagehelper.PageInfo;
import com.youngsun.admin.util.Result;
import com.youngsun.admin.dictionary.service.DictionaryService;
import com.youngsun.admin.vo.CmsUserVo;
import com.youngsun.admin.vo.DictionaryVo;
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
public class DitcitonaryController {
    private static final Logger log = LoggerFactory.getLogger(DitcitonaryController.class);
    @Autowired
    private DictionaryService dictionaryService;
    @GetMapping ("/dict/getAllDictionaryVo/{page}/{rows}")
    public Result getAllDictionaryVo(@PathVariable Integer page, @PathVariable Integer rows){
        PageInfo<DictionaryVo> pageInfo = null;
        try {
            pageInfo = dictionaryService.getAllDictionaryVo(page, rows);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(pageInfo);
    }
    @GetMapping ("/dict/getByType/{type}")
    public Result getByType(@PathVariable String type){
        List<DictionaryVo> dictionaryVos = null;
        try {
            dictionaryVos = dictionaryService.getByType(type);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(dictionaryVos);
    }
    @PostMapping ("/dict/getByDictionaryVo/{page}/{rows}")
    public Result getByDictionaryVo(@PathVariable Integer page, @PathVariable Integer rows, @RequestBody DictionaryVo dictionaryVo){
        PageInfo<DictionaryVo> pageInfo = null;
        try {
            pageInfo = dictionaryService.getByDictionaryVo(page, rows, dictionaryVo);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(pageInfo);
    }

    @DeleteMapping("/dict/delete")
    public Result delete(Long[] ids) {
        try {
            dictionaryService.delete(Arrays.asList(ids));
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(null);
    }
    @PostMapping ("/dict/save")
    public Result save(@RequestBody DictionaryVo dictionaryVo) {
        Integer count = null;
        CmsUserVo user = (CmsUserVo) SecurityUtils.getSubject().getPrincipal();
        try {
            if(dictionaryVo.getId() == null){
                dictionaryVo.setInsertDate(new Date());
                dictionaryVo.setInsertUserId(user.getId() == null ? -1L : user.getId());
            }
            dictionaryVo.setUpDateDate(new Date());
            dictionaryVo.setUpDateUserId(user.getId() == null ? -1L : user.getId());
            dictionaryVo.setDeleted(false);
            count = dictionaryService.save(dictionaryVo);
        } catch (Exception e) {
            log.error("500", e);
            return Result.build(500, e.getMessage(), false);

        }
        return Result.ok(count);
    }
}
