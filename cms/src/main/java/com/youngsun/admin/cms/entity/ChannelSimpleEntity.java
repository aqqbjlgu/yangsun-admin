package com.youngsun.admin.cms.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_channel")
public class ChannelSimpleEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
     * 名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 访问路径
     */
    @Column(name = "t_url")
    private String url;

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
     * 父栏目ID
     */
    @Column(name = "t_parent_id")
    private Long parentId;

    /**
     * 分类
     */
    @Column(name = "t_type")
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
