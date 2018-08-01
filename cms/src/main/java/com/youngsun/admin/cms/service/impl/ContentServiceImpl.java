package com.youngsun.admin.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youngsun.admin.cms.entity.ContentEntity;
import com.youngsun.admin.cms.mapper.ContentMapper;
import com.youngsun.admin.cms.service.ChannelContentService;
import com.youngsun.admin.cms.service.ContentService;
import com.youngsun.admin.cms.service.PictureContentService;
import com.youngsun.admin.cms.vo.ChannelContentVo;
import com.youngsun.admin.cms.vo.ContentSimpleVo;
import com.youngsun.admin.cms.vo.ContentVo;
import com.youngsun.admin.cms.vo.PictureContentVo;
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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


/**
 * Created by 国平 on 2016/10/21.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ChannelContentService channelContentService;
    @Autowired
    private PictureContentService pictureContentService;
    private static final Logger log = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Override
    public void delete(List<ContentSimpleVo> contentSimpleVos) throws Exception {
        List<Long> ids = contentSimpleVos.stream().map(e -> e.getId()).collect(Collectors.toList());
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setHasDeleted(true);
        Example example = new Example(ContentEntity.class);
        example.createCriteria().andIn("id", ids);
        contentMapper.updateByExampleSelective(contentEntity, example);
    }

    @Override
    public PageInfo<ContentSimpleVo> getAllContentVo(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        ContentVo contentVo = new ContentVo();
        List<ContentSimpleVo> contentSimpleVos = contentMapper.getSimpleContent(contentVo);
        PageInfo<ContentSimpleVo> returnPage = new PageInfo<>(contentSimpleVos);
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setHasDeleted(false);
        Integer count = contentMapper.selectCount(contentEntity);
        Integer pages = count%rows==0 ? count/rows : (count/rows) + 1;
        returnPage.setTotal(count);
        returnPage.setPages(pages);
        return returnPage;
    }

    @Override
    public PageInfo<ContentSimpleVo> getByContentVo(Integer page, Integer rows, ContentVo contentVo) {
        PageHelper.startPage(page, rows);
        List<ContentSimpleVo> contentSimpleVos = contentMapper.getSimpleContent(contentVo);
        PageInfo<ContentSimpleVo> returnPage = new PageInfo<>(contentSimpleVos);
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setHasDeleted(false);
        Integer count = contentMapper.selectCount(contentEntity);
        Integer pages = count%rows==0 ? count/rows : (count/rows) + 1;
        returnPage.setTotal(count);
        returnPage.setPages(pages);
        return returnPage;
    }

    @Override
    public List<ContentSimpleVo> getByHot(String type, Integer limit) {
        PageHelper.startPage(0, limit);
        ContentVo contentVo = new ContentVo();
        contentVo.setType(type);
        List<ContentSimpleVo> contentSimpleVos = contentMapper.getSimpleContent(contentVo);
        return contentSimpleVos;
    }

    @Transactional(rollbackFor=Exception.class)
    public Integer save(ContentVo contentVo) throws Exception {
        Integer count;
        ContentEntity contentEntity = new ContentEntity();
        BeanUtils.copyProperties(contentVo, contentEntity);
        List<Long> channels = contentVo.getChannelIds();
        if (StringUtils.isEmpty(contentEntity.getId()) || contentEntity.getId() == null) {
            count = contentMapper.insert(contentEntity);
        } else {
            count = contentMapper.updateByPrimaryKeySelective(contentEntity);
        }
        List<ChannelContentVo> channelContentVos;
        if (!CollectionUtils.isEmpty(channels)) {
            channelContentVos = new ArrayList<>(channels.size());
            channels.forEach(e -> {
                ChannelContentVo channelContentVo = new ChannelContentVo();
                channelContentVo.setChannelId(e);
                channelContentVo.setContentId(contentEntity.getId());
                channelContentVos.add(channelContentVo);
            });
            if (!CollectionUtils.isEmpty(channelContentVos)) {
                channelContentService.deleteByContentId(contentEntity.getId());
                channelContentService.save(channelContentVos);
            }
        }
        if (!CollectionUtils.isEmpty(contentVo.getPictureContentVos())) {
            contentVo.getPictureContentVos().forEach(e -> {
                e.setContentId(contentEntity.getId());
                e.setSinglePictureUrl(e.getPictureUrl().get(0));
                e.setInsertDate(contentVo.getInsertDate());
                e.setInsertUserId(contentVo.getInsertUserId());
                e.setUpDateDate(contentVo.getUpDateDate());
                e.setUpDateUserId(contentVo.getUpDateUserId());
            });
            pictureContentService.deleteByContentId(contentEntity.getId());
            pictureContentService.save(contentVo.getPictureContentVos());
        }
        return count;
    }

    @Transactional(rollbackFor=Exception.class)
    public ContentVo update(ContentVo contentVo) throws Exception {
        ContentEntity contentEntity = new ContentEntity();
        BeanUtils.copyProperties(contentVo, contentEntity);
        contentMapper.updateByPrimaryKeySelective(contentEntity);
        BeanUtils.copyProperties(contentEntity, contentVo);
        return contentVo;
    }

    @Override
    public ContentVo getContentVoById(Long id) {
        ContentVo contentVo;
        ContentEntity contentEntity = contentMapper.selectByPrimaryKey(id);
        if (contentEntity != null) {
            contentVo = new ContentVo();
            BeanUtils.copyProperties(contentEntity, contentVo);
            List<ChannelContentVo> channelContentVos = channelContentService.getAllChannelContentVoByContentId(id);
            List<Long> channelIds = channelContentVos.stream().map( e -> e.getChannelId()).collect(Collectors.toList());
            contentVo.setChannelIds(channelIds);
            List<PictureContentVo> pictureContentVos = pictureContentService.getAllPictureContentVoByContentId(id);
            contentVo.setPictureContentVos(pictureContentVos);
            return contentVo;
        }
        return null;
    }

    @Override
    public PageInfo<ContentSimpleVo> getByChannelId(Integer page, Integer rows, Long channelId) {
        PageHelper.startPage(page, rows);
        PageInfo<ContentSimpleVo> returnPage;
        List<ContentSimpleVo> contentSimpleVos = contentMapper.getSimpleContentByIds(channelId);
        Integer count = contentMapper.countSimpleContentByChannelId(channelId);
        Integer pages = count%rows==0 ? count/rows : (count/rows) + 1;
        returnPage = new PageInfo<>();
        returnPage.setList(contentSimpleVos);
        returnPage.setTotal(count);
        returnPage.setPages(pages);
        return returnPage;
    }

    @Override
    public List<ContentSimpleVo> getSimplePictureContentById(Long contentId) {
        List<ContentSimpleVo> contentSimpleVos = this.contentMapper.getSimplePictureContentById(contentId);
        Map<Long, List<ContentSimpleVo>> contentSimpleVoMap = contentSimpleVos.stream()
                .collect(groupingBy(ContentSimpleVo::getId));
        List<ContentSimpleVo> contentSimpleVosAfterGroup = new ArrayList<>();
        contentSimpleVoMap.entrySet().stream().forEach(entry -> {
            List<ContentSimpleVo> values = entry.getValue();
            ContentSimpleVo firstContentSimpleVo = values.stream().findFirst().get();
            List<PictureContentVo> pictureContentVos = new ArrayList<>();
            values.stream().forEach(ortherContentSimpleVo -> {
                List<String> pictureUrls = new ArrayList<>();
                pictureUrls.add(ortherContentSimpleVo.getSinglePictureUrl());
                PictureContentVo pictureContentVo = new PictureContentVo();
                pictureContentVo.setPictureUrl(pictureUrls);
                pictureContentVo.setId(ortherContentSimpleVo.getPictureContentId());
                pictureContentVo.setPictureDesc(ortherContentSimpleVo.getPictureDesc());
                pictureContentVos.add(pictureContentVo);
            });
            firstContentSimpleVo.setPictureContentVos(pictureContentVos);
            contentSimpleVosAfterGroup.add(firstContentSimpleVo);
        });
        return contentSimpleVosAfterGroup;
    }
}
