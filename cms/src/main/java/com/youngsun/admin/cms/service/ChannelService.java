package com.youngsun.admin.cms.service;

import com.youngsun.admin.cms.vo.ChannelSimpleVo;
import com.youngsun.admin.cms.vo.ChannelVo;

import java.util.List;

/**
 * Created by 国平 on 2016/10/21.
 */
public interface ChannelService {
    
    void delete(List<ChannelSimpleVo> channelSimpleVos) throws Exception;

    List<ChannelSimpleVo> getAllChannelVo();

    List<ChannelSimpleVo> getByChannelVo(ChannelVo channelVo);

    Integer save(ChannelVo channelVo);

    ChannelVo getChannelVoById(Long id);

    List<ChannelVo> getChannelVoByParentId(Long parentId);

    List<ChannelSimpleVo> getAllChannelWithTree();

}
