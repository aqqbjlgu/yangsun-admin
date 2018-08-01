package com.youngsun.admin.cms.mapper;


import com.youngsun.admin.cms.entity.ChannelContentEntity;
import com.youngsun.admin.cms.vo.ChannelContentVo;
import com.youngsun.common.mapper.MyMapper;

import java.util.List;

public interface ChannelContentMapper extends MyMapper<ChannelContentEntity> {
    Integer save(List<ChannelContentVo> pictureContentVos);
}
