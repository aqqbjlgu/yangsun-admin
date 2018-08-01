package com.youngsun.admin.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_content")
public class ContentEntity extends BasicEntity implements Serializable {

    /**
     * 标题
     */
    @Column(name = "t_title")
    private String title;

    /**
     * 排序
     */
    @Column(name = "t_sort")
    private Integer sort;

    /**
     * 有无标题图片
     */
    @Column(name = "t_has_title_img")
    private Boolean hasTitleImg;

    /**
     * 标题图片地址
     */
    @Column(name = "t_title_img")
    private String titleImg;

    /**
     * 是否推荐
     */
    @Column(name = "t_is_recommend")
    private Boolean hasRecommend;

    /**
     * 状态(0:草稿;1:审核中;2:审核通过;3:回收站;4:投稿;5:归档),
     */
    @Column(name = "t_status")
    private String status;

    /**
     *  '访问数',
     */
    @Column(name = "t_views_count")
    private Integer viewsCount;

    /**
     *  评论数,
     */
    @Column(name = "t_comments_count")
    private Integer commentsCount;

    /**
     *  顶数,
     */
    @Column(name = "t_ups_count")
    private Integer upsCount;

    /**
     *  摘要,
     */
    @Column(name = "t_subject")
    private String subject;

    /**
     *  内容分类,
     */
    @Column(name = "t_type")
    private String type;

    /**
     *  文本信息,
     */
    @Column(name = "t_content")
    private String content;

    /**
     *  栏目类型,
     */
    @Column(name = "t_channel_type")
    private String channelType;

    /**
     *  作者,
     */
    @Column(name = "t_author")
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
