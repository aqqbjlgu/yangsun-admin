package com.youngsun.admin.vo.dto;

import java.util.List;

/**
 * Created by 国平 on 2017/7/14.
 * 左侧菜单
 */
public class NavigationTreeDto {
    /**
     * 菜单项名称
     */
    private String text;
    /**
     * 菜单项显示控件
     */
    private String view;
    /**
     * 菜单项是否是叶子节点
     */
    private Boolean leaf;
    /**
     * 菜单项标志
     */
    private String iconCls;
    /**
     * 菜单项路由
     */
    private String routeId;

    private List<NavigationTreeDto> children;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public List<NavigationTreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<NavigationTreeDto> children) {
        this.children = children;
    }
}
