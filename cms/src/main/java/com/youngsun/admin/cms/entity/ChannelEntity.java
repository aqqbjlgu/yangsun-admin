package com.youngsun.admin.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_channel")
public class ChannelEntity extends BasicEntity implements Serializable {

    /**
     * 名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 类型
     */
    @Column(name = "t_title")
    private String title;

    /**
     * 描述
     */
    @Column(name = "t_description")
    private String description;

    /**
     * 访问路径
     */
    @Column(name = "t_url")
    private String url;

    /**
     * 父频道ID
     */
    @Column(name = "t_parent_id")
    private Long parentId;

    /**
     * 频道banner
     */
    @Column(name = "t_title_img")
    private String titleImages;

    /**
     * 是否显示
     */
    @Column(name = "t_allow_dispaly")
    private Boolean allowDispaly;

    /**
     * 每页显示文章数
     */
    @Column(name = "t_page_size")
    private String pageSize;

    /**
     * 排序
     */
    @Column(name = "t_sort")
    private String sort;

    /**
     * 类型：分为单页和普通型
     */
    @Column(name = "t_type")
    private String type;

    /**
     * 类型：分为单页和普通型
     */
    @Column(name = "t_content")
    private String content;

    @Column(name = "t_is_leaf")
    private Boolean leaf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTitleImages() {
        return titleImages;
    }

    public void setTitleImages(String titleImages) {
        this.titleImages = titleImages;
    }

    public Boolean getAllowDispaly() {
        return allowDispaly;
    }

    public void setAllowDispaly(Boolean allowDispaly) {
        this.allowDispaly = allowDispaly;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
