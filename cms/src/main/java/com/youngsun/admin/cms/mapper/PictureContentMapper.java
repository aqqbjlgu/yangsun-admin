package com.youngsun.admin.cms.mapper;


import com.youngsun.admin.cms.entity.PictureContentEntity;
import com.youngsun.admin.cms.vo.PictureContentVo;
import com.youngsun.common.mapper.MyMapper;

import java.util.List;

public interface PictureContentMapper extends MyMapper<PictureContentEntity> {
    Integer save(List<PictureContentVo> pictureContentVos);
}
