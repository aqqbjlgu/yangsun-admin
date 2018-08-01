package com.youngsun.admin.cms.service;

import com.youngsun.admin.cms.vo.PictureContentVo;
import com.youngsun.admin.cms.vo.PictureContentVo;

import java.util.List;

/**
 * Created by 国平 on 2016/10/21.
 */
public interface PictureContentService {
    
    void deleteByContentId(Long contentId) throws Exception;

    List<PictureContentVo> getAllPictureContentVoByContentId(Long contentId);

    Integer save(List<PictureContentVo> pictureContentVos);

}
