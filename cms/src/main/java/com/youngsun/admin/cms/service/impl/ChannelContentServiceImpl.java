package com.youngsun.admin.cms.service.impl;

import com.youngsun.admin.cms.entity.ChannelContentEntity;
import com.youngsun.admin.cms.entity.PictureContentEntity;
import com.youngsun.admin.cms.mapper.ChannelContentMapper;
import com.youngsun.admin.cms.mapper.PictureContentMapper;
import com.youngsun.admin.cms.service.ChannelContentService;
import com.youngsun.admin.cms.service.PictureContentService;
import com.youngsun.admin.cms.vo.ChannelContentVo;
import com.youngsun.admin.cms.vo.PictureContentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 国平 on 2016/10/21.
 */
@Service
public class ChannelContentServiceImpl implements ChannelContentService {
    @Autowired
    private ChannelContentMapper channelContentMapper;
    private static final Logger log = LoggerFactory.getLogger(ChannelContentServiceImpl.class);

    @Override
    public void deleteByChannelId(Long channelId) throws Exception {
        ChannelContentEntity channelContentEntity = new ChannelContentEntity();
        channelContentEntity.setChannelId(channelId);
        channelContentMapper.delete(channelContentEntity);
    }

    @Override
    public void deleteByContentId(Long contentId) throws Exception {
        ChannelContentEntity channelContentEntity = new ChannelContentEntity();
        channelContentEntity.setContentId(contentId);
        channelContentMapper.delete(channelContentEntity);
    }

    @Override
    public List<ChannelContentVo> getAllChannelContentVoByChannelId(Long channelId) {
        ChannelContentEntity channelContentEntity = new ChannelContentEntity();
        channelContentEntity.setChannelId(channelId);
        List<ChannelContentEntity> channelContentEntities = channelContentMapper.select(channelContentEntity);
        List<ChannelContentVo> channelContentVos = new ArrayList<>(channelContentEntities.size());
        channelContentEntities.forEach(e -> {
            ChannelContentVo channelContentVo = new ChannelContentVo();
            BeanUtils.copyProperties(e, channelContentVo);
            channelContentVos.add(channelContentVo);
        });
        return channelContentVos;
    }

    @Override
    public List<ChannelContentVo> getAllChannelContentVoByContentId(Long contentId) {
        ChannelContentEntity channelContentEntity = new ChannelContentEntity();
        channelContentEntity.setContentId(contentId);
        List<ChannelContentEntity> channelContentEntities = channelContentMapper.select(channelContentEntity);
        List<ChannelContentVo> channelContentVos = new ArrayList<>(channelContentEntities.size());
        channelContentEntities.forEach(e -> {
            ChannelContentVo channelContentVo = new ChannelContentVo();
            BeanUtils.copyProperties(e, channelContentVo);
            channelContentVos.add(channelContentVo);
        });
        return channelContentVos;
    }

    @Override
    public Integer save(List<ChannelContentVo> channelContentVos) {
        return channelContentMapper.save(channelContentVos);
    }
}
