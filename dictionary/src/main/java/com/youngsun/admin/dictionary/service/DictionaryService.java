package com.youngsun.admin.dictionary.service;


import com.github.pagehelper.PageInfo;
import com.youngsun.admin.dictionary.entity.DictionaryEntity;
import com.youngsun.admin.vo.DictionaryVo;

import java.util.List;

/**
 * Created by 国平 on 2016/10/21.
 */
public interface DictionaryService extends BasicService<DictionaryEntity> {
    
    void delete(List<Long> ids) throws Exception;

    PageInfo<DictionaryVo> getAllDictionaryVo(Integer page, Integer rows);

    PageInfo<DictionaryVo> getByDictionaryVo(Integer page, Integer rows, DictionaryVo dictionaryVo);

    Integer save(DictionaryVo dictionaryVo);

    List<DictionaryVo> getByType(String type);
}
