package com.youngsun.admin.vo;

import java.io.Serializable;

/**
 * Created by 国平 on 2016/12/7.
 */
public class UserOrgPosVo  implements Serializable {
    
    /**
     * 人员ID
     */
    private Long pId;
    /**
     * 组织ID
     */
    private Long orgId;
    /**
     * 岗位ID
     */
    private Long posId;
    
    public Long getpId() {
        return pId;
    }
    
    public void setpId(Long pId) {
        this.pId = pId;
    }
    
    public Long getOrgId() {
        return orgId;
    }
    
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    
    public Long getPosId() {
        return posId;
    }
    
    public void setPosId(Long posId) {
        this.posId = posId;
    }
}
