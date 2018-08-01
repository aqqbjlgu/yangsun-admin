package com.youngsun.admin.dictionary.service;


import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by 国平 on 2016/10/22.
 */
public interface BasicService<T> {
    public List<T> getAll();

//    List<T> getAll(Sort var1);

    List<T> getAll(int page, int rows);

    public Integer save(T t);

    public Integer save(List<T> t);

    public T getById(Long id);

    public T getOne(T t);

    public List<T> getAllByExample(Example example);

    public void delete(T t);
    
}
