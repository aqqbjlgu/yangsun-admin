package com.youngsun.admin.cms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_content")
public class ContentSimpleEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
     * 标题
     */
    @Column(name = "t_title")
    private String title;
    /**
     * 是否推荐
     */
    @Column(name = "t_is_recommend")
    private String hasRecommend;

    /**
     * 排序
     */
    @Column(name = "t_sort")
    private Integer sort;

    /**
     * 状态
     */
    @Column(name = "t_status")
    private Long status;

    /**
     * 修改日期
     */
    @Column(name = "t_insert_date")
    private Date insertDate;

    /**
     * 修改日期
     */
    @Column(name = "t_update_date")
    private Date updateDate;

    /**
     * 评论数
     */
    @Column(name = "commentsCount")
    private Integer commentsCount;

    /**
     * 赞数
     */
    @Column(name = "t_ups_count")
    private Integer upsCount;

    /**
     * 访问量
     */
    @Column(name = "t_views_count")
    private Integer viewsCount;

    /**
     * 标题图片地址
     */
    @Column(name = "t_title_img")
    private String titleImg;

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
