package com.youngsun.admin.vo.dto;

import java.util.List;

/**
 * Created by 国平 on 2017/7/11.
 */
public class PermissionDto extends BasicDto {
    /**
     * 资源名称
     */
    private String permissionName;
    /**
     * 资源类型：menu,button,
     */
    private String type;
    /**
     * 访问url地址
     */
    private String url;
    /**
     * 权限代码字符串
     */
    private String percode;
    /**
     * 父结点id
     */
    private String parentId;
    /**
     * 父结点id列表串
     */
    private String parentIds;
    /**
     * 排序号
     */
    private String sortString;
    /**
     * 是否可用,1：可用，0不可用
     */
    private Boolean permissionAvailable;
    /**
     * 是否是叶子节点
     */
    private Boolean leaf;
    /**
     * 子节点
     */
    private List<PermissionDto> children;
    /**
     * 图标
     */
    private String iconCls;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getSortString() {
        return sortString;
    }

    public void setSortString(String sortString) {
        this.sortString = sortString;
    }

    public Boolean getPermissionAvailable() {
        return permissionAvailable;
    }

    public void setPermissionAvailable(Boolean permissionAvailable) {
        this.permissionAvailable = permissionAvailable;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public List<PermissionDto> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionDto> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
}
