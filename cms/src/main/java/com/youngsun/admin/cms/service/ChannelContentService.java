package com.youngsun.admin.cms.service;

import com.youngsun.admin.cms.vo.ChannelContentVo;

import java.util.List;

/**
 * Created by 国平 on 2016/10/21.
 */
public interface ChannelContentService {
    
    void deleteByChannelId(Long channelId) throws Exception;

    void deleteByContentId(Long contentId) throws Exception;

    List<ChannelContentVo> getAllChannelContentVoByChannelId(Long channelId);

    List<ChannelContentVo> getAllChannelContentVoByContentId(Long contentId);

    Integer save(List<ChannelContentVo> channelContentVos);

}
