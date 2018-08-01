package com.youngsun.admin.dictionary.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youngsun.admin.dictionary.entity.DictionaryEntity;
import com.youngsun.admin.dictionary.mapper.DictionaryMapper;
import com.youngsun.admin.dictionary.service.DictionaryService;
import com.youngsun.admin.vo.DictionaryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by 国平 on 2016/10/21.
 */
@Service
public class DictionaryServiceImpl extends BasicServiceImpl<DictionaryEntity> implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    private static final Logger log = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    @Override
    public void delete(List<Long> ids) throws Exception {
        DictionaryEntity dictionaryEntity = new DictionaryEntity();
        dictionaryEntity.setDeleted(true);
        Example example = new Example(DictionaryEntity.class);
        example.createCriteria().andIn("id", ids);
        dictionaryMapper.updateByExampleSelective(dictionaryEntity, example);
    }

    @Override
    public PageInfo<DictionaryVo> getAllDictionaryVo(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        Example example = new Example(DictionaryEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);
        List<DictionaryEntity> dictionaryEntities = getAllByExample(page, rows, example);
        List<DictionaryVo> dictionaryVos = new ArrayList<>(dictionaryEntities.size());
        dictionaryEntities.forEach(e -> {
            DictionaryVo dictionaryVo = new DictionaryVo();
            BeanUtils.copyProperties(e, dictionaryVo);
            dictionaryVos.add(dictionaryVo);
        });
        PageInfo<DictionaryVo> returnPage = new PageInfo<>(dictionaryVos);
        DictionaryEntity dictionaryEntity = new DictionaryEntity();
        dictionaryEntity.setDeleted(false);
        Integer count = dictionaryMapper.selectCount(dictionaryEntity);
        Integer pages = count%rows==0 ? count/rows : (count/rows) + 1;
        returnPage.setTotal(count);
        returnPage.setPages(pages);
        return returnPage;
    }

    @Override
    public PageInfo<DictionaryVo> getByDictionaryVo(Integer page, Integer rows, DictionaryVo dictionaryVo) {
        PageHelper.startPage(page, rows);
        Example example = new Example(DictionaryEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);
        if (StringUtils.isEmpty(dictionaryVo.getName())) {
            dictionaryVo.setName(null);
        } else {
            criteria.andLike("name", "%"+dictionaryVo.getName()+"%");
        }
        if (StringUtils.isEmpty(dictionaryVo.getType())) {
            dictionaryVo.setType(null);
        } else {
            criteria.andLike("type", "%"+dictionaryVo.getType()+"%");
        }
        List<DictionaryEntity> dictionaryEntities = super.getAllByExample(page, rows, example);
        List<DictionaryVo> dictionaryVos = dictionaryEntities.stream().map(e -> {
            DictionaryVo dictionaryVoReturn = new DictionaryVo();
            BeanUtils.copyProperties(e, dictionaryVoReturn);
            return dictionaryVoReturn;
        }).collect(Collectors.toList());
        PageInfo<DictionaryVo> returnPage = new PageInfo<>(dictionaryVos);
        Integer count = dictionaryMapper.selectCountByExample(example);
        Integer pages = count%rows==0 ? count/rows : (count/rows) + 1;
        returnPage.setTotal(count);
        returnPage.setPages(pages);
        return returnPage;
    }

    @Override
    public Integer save(DictionaryVo dictionaryVo) {
        DictionaryEntity dictionaryEntity = new DictionaryEntity();
        BeanUtils.copyProperties(dictionaryVo, dictionaryEntity);
        return save(dictionaryEntity);
    }

    @Override
    public List<DictionaryVo> getByType(String type) {
        Example example = new Example(DictionaryEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", false);
        criteria.andEqualTo("type", type);
        List<DictionaryEntity> dictionaryEntities = super.getAllByExample(example);
        List<DictionaryVo> dictionaryVos = dictionaryEntities.stream().map(e -> {
            DictionaryVo dictionaryVoReturn = new DictionaryVo();
            BeanUtils.copyProperties(e, dictionaryVoReturn);
            return dictionaryVoReturn;
        }).collect(Collectors.toList());
        return dictionaryVos;
    }
}
