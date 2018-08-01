package com.youngsun.admin.vo;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 岗位对像，用来确认人员岗位
 * Created by 国平 on 2016/10/21.
 */
public class PositionVo extends BasicVo  implements Serializable {
    @NotNull
    private String name;
    /**
     * 岗位编号
     */
    @NotNull
    private String sn;

    /**
     * 岗位是否具有管理功能
     */
    @NotNull
    private Boolean isManager;

    private Date startDate;

    private Date endDate;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSn() {
        return sn;
    }
    
    public void setSn(String sn) {
        this.sn = sn;
    }
    
    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
