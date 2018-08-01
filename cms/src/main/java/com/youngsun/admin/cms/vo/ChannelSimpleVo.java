package com.youngsun.admin.cms.vo;

import java.io.Serializable;
import java.util.List;

public class ChannelSimpleVo implements Serializable {

    private Long id;
    /**
     * 名称
     */
    private String name;

    /**
     * 访问路径
     */
    private String url;

    /**
     * 每页显示文章数
     */
    private String pageSize;

    /**
     * 父栏目ID
     */
    private Long parentId;

    /**
     * 排序
     */
    private String sort;

    /**
     * 子栏目
     */
    private List<ChannelSimpleVo> children;
    /**
     * 是不是叶子节点
     */
    private Boolean leaf;

    /**
     * 分类
     */
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<ChannelSimpleVo> getChildren() {
        return children;
    }

    public void setChildren(List<ChannelSimpleVo> children) {
        this.children = children;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
