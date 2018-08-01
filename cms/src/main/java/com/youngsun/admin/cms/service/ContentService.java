package com.youngsun.admin.cms.service;

import com.github.pagehelper.PageInfo;
import com.youngsun.admin.cms.entity.ChannelEntity;
import com.youngsun.admin.cms.vo.ContentSimpleVo;
import com.youngsun.admin.cms.vo.ContentVo;

import java.util.List;

/**
 * Created by 国平 on 2016/10/21.
 */
public interface ContentService {
    
    void delete(List<ContentSimpleVo> contentSimpleVos) throws Exception;

    PageInfo<ContentSimpleVo> getAllContentVo(Integer page, Integer rows);

    PageInfo<ContentSimpleVo> getByContentVo(Integer page, Integer rows, ContentVo contentVo);

    Integer save(ContentVo contentVo) throws Exception;

    ContentVo update(ContentVo contentVo) throws Exception;

    ContentVo getContentVoById(Long id);

    PageInfo<ContentSimpleVo> getByChannelId(Integer page, Integer rows, Long channelId);

    List<ContentSimpleVo> getSimplePictureContentById(Long channelId);

    List<ContentSimpleVo> getByHot(String type, Integer limit);

}
