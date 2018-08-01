package com.youngsun.admin.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_pic_content")
public class PictureContentEntity extends BasicEntity implements Serializable {

    /**
     * 图片地址
     */
    @Column(name = "t_pic_url")
    private String pictureUrl;

    /**
     * 文章ID
     */
    @Column(name = "t_content_id")
    private Long contentId;

    /**
     * 图片描述
     */
    @Column(name = "t_pic_desc")
    private String pictureDesc;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getPictureDesc() {
        return pictureDesc;
    }

    public void setPictureDesc(String pictureDesc) {
        this.pictureDesc = pictureDesc;
    }
}
