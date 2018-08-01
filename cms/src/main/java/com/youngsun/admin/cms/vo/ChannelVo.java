package com.youngsun.admin.cms.vo;

import java.io.Serializable;
import java.util.List;

public class ChannelVo extends BasicVo implements Serializable {

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 访问路径
     */
    private String url;

    /**
     * 子频道
     */
    private List<ChannelVo> children;

    /**
     * 频道banner
     */
    private String titleImages;

    /**
     * 是否显示
     */
    private Boolean allowDispaly;

    /**
     * 每页显示文章数
     */
    private String pageSize;

    /**
     * 排序
     */
    private String sort;

    /**
     * 类型：分为单页和普通型
     */
    private String type;

    /**
     * 栏目类容，单页栏目用
     */
    private String content;

    private Boolean leaf;

    /**
     * 父栏目ID
     */
    private Long parentId;

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

    public List<ChannelVo> getChildren() {
        return children;
    }

    public void setChildren(List<ChannelVo> children) {
        this.children = children;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
