package com.youngsun.admin.vo.dto;

import java.util.Date;

/**
 * Created by 国平 on 2016/10/20.
 */
public class BasicDto {
    /**
     * 主键ID
     */
    private String id;
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
    private String insertUserId;
    /**
     * 更新者ID
     */
    private String upDateUserId;
    /**
     * 所属系统
     */
    private String belongTo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(String insertUserId) {
        this.insertUserId = insertUserId;
    }

    public String getUpDateUserId() {
        return upDateUserId;
    }

    public void setUpDateUserId(String upDateUserId) {
        this.upDateUserId = upDateUserId;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }
}
