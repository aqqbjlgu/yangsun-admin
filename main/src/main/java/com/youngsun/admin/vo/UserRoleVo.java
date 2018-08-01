package com.youngsun.admin.vo;

import java.io.Serializable;

/**
 * Created by 国平 on 2017/6/28.
 */
public class UserRoleVo extends BasicVo  implements Serializable {
    private String userId;
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
