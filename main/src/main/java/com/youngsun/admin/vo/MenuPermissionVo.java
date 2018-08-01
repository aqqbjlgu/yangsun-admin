package com.youngsun.admin.vo;

import java.io.Serializable;
import java.util.List;

public class MenuPermissionVo implements Serializable {
    private String title;
    private String icon;
    private String link;
    private List<MenuPermissionVo> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<MenuPermissionVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuPermissionVo> children) {
        this.children = children;
    }
}
