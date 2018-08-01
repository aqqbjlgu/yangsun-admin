package com.youngsun.admin.cms.vo;


import java.io.Serializable;
import java.util.List;

public class PictureContentVo extends BasicVo implements Serializable {
    /**
     * 图片地址
     */
    private List<String> pictureUrl;

    /**
     * 文章ID
     */
    private Long contentId;

    /**
     * 图片描述
     */
    private String pictureDesc;

    private Boolean imageDisabled;

    /**
     * 图片地址
     */
    private String singlePictureUrl;

    public List<String> getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(List<String> pictureUrl) {
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

    public Boolean getImageDisabled() {
        return imageDisabled;
    }

    public void setImageDisabled(Boolean imageDisabled) {
        this.imageDisabled = imageDisabled;
    }

    public String getSinglePictureUrl() {
        return singlePictureUrl;
    }

    public void setSinglePictureUrl(String singlePictureUrl) {
        this.singlePictureUrl = singlePictureUrl;
    }
}
