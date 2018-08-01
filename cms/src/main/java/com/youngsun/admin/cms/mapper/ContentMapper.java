package com.youngsun.admin.cms.mapper;


import com.youngsun.admin.cms.entity.ContentEntity;
import com.youngsun.admin.cms.vo.ContentSimpleVo;
import com.youngsun.admin.cms.vo.ContentVo;
import com.youngsun.common.mapper.MyMapper;

import java.util.List;

public interface ContentMapper extends MyMapper<ContentEntity> {
    List<ContentSimpleVo> getSimpleContent(ContentVo contentVo);
    List<ContentSimpleVo> getSimpleContentByIds(Long channelId);
    List<ContentSimpleVo> getSimplePictureContentById(Long channelId);
    Integer countSimpleContentByChannelId(Long channelId);
}
