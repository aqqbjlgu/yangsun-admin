package com.youngsun.admin.cms.service.impl;

import com.youngsun.admin.cms.entity.ChannelEntity;
import com.youngsun.admin.cms.mapper.ChannelMapper;
import com.youngsun.admin.cms.service.ChannelService;
import com.youngsun.admin.cms.vo.ChannelSimpleVo;
import com.youngsun.admin.cms.vo.ChannelVo;
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
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private ChannelMapper channelMapper;
    private static final Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);

    @Override
    public void delete(List<ChannelSimpleVo> channelSimpleVos) throws Exception {
        channelSimpleVos.forEach(channelSimpleVo -> {
            Example example = new Example(ChannelEntity.class);
            example.createCriteria().andEqualTo("parentId", channelSimpleVo.getId())
                                    .andEqualTo("hasDeleted", false);
            List<ChannelEntity> childrenChannel = channelMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(childrenChannel)) {
                throw new BudinessRuntimeException("请先删除子栏目", 500);
            }
            example.clear();
            example.createCriteria().andEqualTo("parentId", channelSimpleVo.getParentId())
                                    .andEqualTo("hasDeleted", false)
                                    .andNotEqualTo("id", channelSimpleVo.getId());
            List<ChannelEntity> parentChannelChildrenEntities = channelMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(parentChannelChildrenEntities)) {
                ChannelEntity parentChannelEntity = channelMapper.selectByPrimaryKey(channelSimpleVo.getParentId());
                parentChannelEntity.setLeaf(true);
                this.channelMapper.updateByPrimaryKeySelective(parentChannelEntity);
            }

        });
        List<Long> ids = channelSimpleVos.stream().map(e -> e.getId()).collect(Collectors.toList());
        ChannelEntity channelEntity = new ChannelEntity();
        channelEntity.setHasDeleted(true);
        Example example = new Example(ChannelEntity.class);
        example.createCriteria().andIn("id", ids);
        channelMapper.updateByExampleSelective(channelEntity, example);
    }

    @Override
    public List<ChannelSimpleVo> getAllChannelVo() {
        ChannelVo channelVo = new ChannelVo();
        return channelMapper.getSimpleChannel(channelVo);
    }

    @Override
    public List<ChannelSimpleVo> getByChannelVo(ChannelVo channelVo) {
        return channelMapper.getSimpleChannel(channelVo);
    }

    @Transactional(rollbackFor=Exception.class)
    public Integer save(ChannelVo channelVo) {
        ChannelEntity parentChannelEntity = new ChannelEntity();
        ChannelVo parentChannelVo = getChannelVoById(channelVo.getParentId());
        if (parentChannelVo != null) {
            parentChannelEntity.setId(parentChannelVo.getId());
            parentChannelEntity.setLeaf(false);
            channelMapper.updateByPrimaryKeySelective(parentChannelEntity);
        }
        ChannelEntity channelEntity = new ChannelEntity();
        BeanUtils.copyProperties(channelVo, channelEntity);
        if (StringUtils.isEmpty(channelVo.getId()) || channelVo.getId() == null) {
            return channelMapper.insert(channelEntity);
        }
        return channelMapper.updateByPrimaryKeySelective(channelEntity);
    }

    @Override
    public ChannelVo getChannelVoById(Long id) {
        ChannelVo channelVo;
        ChannelEntity channelEntity = channelMapper.selectByPrimaryKey(id);
        if (channelEntity != null) {
            channelVo = new ChannelVo();
            BeanUtils.copyProperties(channelEntity, channelVo);
            return channelVo;
        }
        return null;
    }

    @Override
    public List<ChannelVo> getChannelVoByParentId(Long parentId) {
        ChannelEntity channelEntity = new ChannelEntity();
        channelEntity.setParentId(parentId);
        List<ChannelEntity> channelEntities = channelMapper.select(channelEntity);
        List<ChannelVo> channelVos = new ArrayList<>(channelEntities.size());
        channelEntities.forEach(e -> {
            ChannelVo channelVo = new ChannelVo();
            BeanUtils.copyProperties(e, channelVo);
            channelVos.add(channelVo);
        });
        return channelVos;
    }

    @Override
    public List<ChannelSimpleVo> getAllChannelWithTree() {
        return getAllChannelWithTree(0L);
    }

    private List<ChannelSimpleVo> getAllChannelWithTree(Long parentId) {
        ChannelVo channelVo = new ChannelVo();
        channelVo.setParentId(parentId);
        List<ChannelSimpleVo> simpleChannels = channelMapper.getSimpleChannel(channelVo);
        List<ChannelSimpleVo> channelSimpleVos = new ArrayList<>();
        for(ChannelSimpleVo channelSimpleVo : simpleChannels){
            if(!channelSimpleVo.getLeaf()){
                channelSimpleVo.setChildren(getAllChannelWithTree(channelSimpleVo.getId()));
                channelSimpleVos.add(channelSimpleVo);
            } else {
                channelSimpleVos.add(channelSimpleVo);
            }
        }
        return channelSimpleVos;
    }
}
