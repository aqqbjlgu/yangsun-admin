package com.youngsun.admin.vo;

import java.io.Serializable;

/**
 * Created by 国平 on 2017/6/28.
 */
public class RoleVo extends BasicVo  implements Serializable {
    /**
     * 角色名称.
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
