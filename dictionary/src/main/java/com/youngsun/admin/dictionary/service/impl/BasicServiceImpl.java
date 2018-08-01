package com.youngsun.admin.dictionary.service.impl;

import com.github.pagehelper.PageHelper;
import com.youngsun.admin.dictionary.entity.BasicEntity;
import com.youngsun.admin.dictionary.service.BasicService;
import com.youngsun.common.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by 国平 on 2016/10/22.
 */
public class BasicServiceImpl<T extends BasicEntity> implements BasicService<T> {
    @Autowired
    private MyMapper<T> myMapper;

    public List<T> getAll() {
        return myMapper.selectAll();
    }

    public List<T> getAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        return myMapper.selectAll();
    }

    public Integer save(T t) {
        if (StringUtils.isEmpty(t.getId()) || t.getId() == null) {
            return myMapper.insert(t);
        }
        return myMapper.updateByPrimaryKeySelective(t);
    }

    public Integer save(List<T> ts) {
        for (T t : ts) {
            if (StringUtils.isEmpty(t.getId())) {
                myMapper.insert(t);
            } else {
                myMapper.updateByPrimaryKeySelective(t);
            }
        }
        return null;
    }

    public T getById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    public T getOne(T t) {
        return myMapper.selectOne(t);
    }

    public List<T> getAllByExample(Example example) {
        return myMapper.selectByExample(example);
    }

    public List<T> getAllByExample(Integer start, Integer pageSize, Example example) {
        PageHelper.startPage(start, pageSize);
        return myMapper.selectByExample(example);
    }

    public void delete(T t){
        myMapper.delete(t);
    }
    
}
