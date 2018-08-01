package com.youngsun.admin.cms.vo;


import java.io.Serializable;
import java.util.List;

public class ContentVo extends BasicVo implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 有无标题图片
     */
    private Boolean hasTitleImg;

    /**
     * 标题图片地址
     */
    private String titleImg;

    /**
     * 是否推荐
     */
    private Boolean hasRecommend;

    /**
     * 状态(0:草稿;1:审核中;2:审核通过;3:回收站;4:投稿;5:归档),
     */
    private String status;

    /**
     *  '访问数',
     */
    private Integer viewsCount;

    /**
     *  评论数,
     */
    private Integer commentsCount;

    /**
     *  顶数,
     */
    private Integer upsCount;

    /**
     *  文本信息,
     */
    private String content;

    /**
     *  摘要,
     */
    private String subject;

    /**
     *  内容分类,
     */
    private String type;

    /**
     *  栏目类型,
     */
    private String channelType;

    private List<Long> channelIds;

    private List<PictureContentVo> pictureContentVos;

    /**
     *  作者,
     */
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getHasTitleImg() {
        return hasTitleImg;
    }

    public void setHasTitleImg(Boolean hasTitleImg) {
        this.hasTitleImg = hasTitleImg;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public Boolean getHasRecommend() {
        return hasRecommend;
    }

    public void setHasRecommend(Boolean hasRecommend) {
        this.hasRecommend = hasRecommend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Long> getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(List<Long> channelIds) {
        this.channelIds = channelIds;
    }

    public List<PictureContentVo> getPictureContentVos() {
        return pictureContentVos;
    }

    public void setPictureContentVos(List<PictureContentVo> pictureContentVos) {
        this.pictureContentVos = pictureContentVos;
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
}
