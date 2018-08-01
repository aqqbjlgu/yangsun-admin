package com.youngsun.admin.cms.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ContentSimpleVo implements Serializable {
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 是否推荐
     */
    private String hasRecommend;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Long status;

    /**
     * 修改日期
     */
    private Date insertDate;

    /**
     * 访问量
     */
    private Integer viewsCount;

    private String titleImg;

    /**
     *  栏目类型,
     */
    private String channelType;

    private List<PictureContentVo> pictureContentVos;

    private String singlePictureUrl;

    private String pictureDesc;

    private Long pictureContentId;

    /**
     *  作者,
     */
    private String author;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * 评论数
     */
    private Integer commentsCount;

    /**
     * 赞数
     */
    private Integer upsCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHasRecommend() {
        return hasRecommend;
    }

    public void setHasRecommend(String hasRecommend) {
        this.hasRecommend = hasRecommend;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getSinglePictureUrl() {
        return singlePictureUrl;
    }

    public void setSinglePictureUrl(String singlePictureUrl) {
        this.singlePictureUrl = singlePictureUrl;
    }

    public List<PictureContentVo> getPictureContentVos() {
        return pictureContentVos;
    }

    public void setPictureContentVos(List<PictureContentVo> pictureContentVos) {
        this.pictureContentVos = pictureContentVos;
    }

    public String getPictureDesc() {
        return pictureDesc;
    }

    public void setPictureDesc(String pictureDesc) {
        this.pictureDesc = pictureDesc;
    }

    public Long getPictureContentId() {
        return pictureContentId;
    }

    public void setPictureContentId(Long pictureContentId) {
        this.pictureContentId = pictureContentId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getUpsCount() {
        return upsCount;
    }

    public void setUpsCount(Integer upsCount) {
        this.upsCount = upsCount;
    }
}
