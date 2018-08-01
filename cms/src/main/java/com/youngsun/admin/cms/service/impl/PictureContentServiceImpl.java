package com.youngsun.admin.cms.service.impl;

import com.youngsun.admin.cms.entity.ChannelEntity;
import com.youngsun.admin.cms.entity.PictureContentEntity;
import com.youngsun.admin.cms.mapper.ChannelMapper;
import com.youngsun.admin.cms.mapper.PictureContentMapper;
import com.youngsun.admin.cms.service.ChannelService;
import com.youngsun.admin.cms.service.PictureContentService;
import com.youngsun.admin.cms.vo.ChannelSimpleVo;
import com.youngsun.admin.cms.vo.ChannelVo;
import com.youngsun.admin.cms.vo.PictureContentVo;
import com.youngsun.common.exception.BudinessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by 国平 on 2016/10/21.
 */
@Service
public class PictureContentServiceImpl implements PictureContentService {
    @Autowired
    private PictureContentMapper pictureContentMapper;
    private static final Logger log = LoggerFactory.getLogger(PictureContentServiceImpl.class);

    @Override
    public void deleteByContentId(Long contentId) throws Exception {
        Example example = new Example(PictureContentEntity.class);
        example.createCriteria().andEqualTo("contentId", contentId);
        pictureContentMapper.deleteByExample(example);
    }

    @Override
    public List<PictureContentVo> getAllPictureContentVoByContentId(Long contentId) {
        PictureContentEntity pictureContentEntity = new PictureContentEntity();
        pictureContentEntity.setHasDeleted(false);
        pictureContentEntity.setContentId(contentId);
        List<PictureContentEntity> pictureContentEntities = pictureContentMapper.select(pictureContentEntity);
        List<PictureContentVo> pictureContentVos = new ArrayList<>(pictureContentEntities.size());
        pictureContentEntities.forEach(e -> {
            PictureContentVo pictureContentVo = new PictureContentVo();
            BeanUtils.copyProperties(e, pictureContentVo);
            List<String> pictureUrls = new ArrayList<>(1);
            pictureUrls.add(e.getPictureUrl());
            pictureContentVo.setPictureUrl(pictureUrls);
            pictureContentVos.add(pictureContentVo);
        });
        return pictureContentVos;
    }

    @Override
    public Integer save(List<PictureContentVo> pictureContentVos) {
        return pictureContentMapper.save(pictureContentVos);
    }
}
