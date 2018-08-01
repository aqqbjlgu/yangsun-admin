package com.youngsun.admin.cms.mapper;

import com.youngsun.admin.cms.entity.ChannelEntity;
import com.youngsun.admin.cms.vo.ChannelSimpleVo;
import com.youngsun.admin.cms.vo.ChannelVo;
import com.youngsun.common.mapper.MyMapper;

import java.util.List;

public interface ChannelMapper extends MyMapper<ChannelEntity> {
    List<ChannelSimpleVo> getSimpleChannel(ChannelVo channelVo);
}
