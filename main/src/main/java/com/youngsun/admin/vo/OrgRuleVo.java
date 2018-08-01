package com.youngsun.admin.vo;


import java.io.Serializable;

/**
 * 用来确定组织之间的管理关系
 * Created by 国平 on 2016/10/21.
 */
public class OrgRuleVo extends BasicVo  implements Serializable {

    /**
     * 管理组织下的信息
     */
    private static final Integer DEFAULT_TYPE = 0;
    /**
     * 管理组织下所有的信息
     */
    private static final Integer ALL_TYPE = 1;
    /**
     * 自定义的管理信息。
     */
    private static final Integer DEF_TYPE = 2;
    /**
     * 不具有管理功能。
     */
    private static final Integer NO_TYPE = -1;
    private String orgId;
    private String managerOrg;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getManagerOrg() {
        return managerOrg;
    }

    public void setManagerOrg(String managerOrg) {
        this.managerOrg = managerOrg;
    }
}
