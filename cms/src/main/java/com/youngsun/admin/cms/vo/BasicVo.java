package com.youngsun.admin.cms.vo;

import java.util.Date;

/**
 * Created by 国平 on 2016/10/20.
 */
public class BasicVo {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 插入时间
     */
    private Date insertDate;
    /**
     * 更新时间
     */
    private Date upDateDate;
    /**
     * 插入者ID
     */
    private Long insertUserId;
    /**
     * 更新者ID
     */
    private Long upDateUserId;
    /**
     * 是否是删除
     */
    private Boolean hasDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpDateDate() {
        return upDateDate;
    }

    public void setUpDateDate(Date upDateDate) {
        this.upDateDate = upDateDate;
    }

    public Long getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(Long insertUserId) {
        this.insertUserId = insertUserId;
    }

    public Long getUpDateUserId() {
        return upDateUserId;
    }

    public void setUpDateUserId(Long upDateUserId) {
        this.upDateUserId = upDateUserId;
    }

    public Boolean getHasDeleted() {
        return hasDeleted;
    }

    public void setHasDeleted(Boolean hasDeleted) {
        this.hasDeleted = hasDeleted;
    }
}
