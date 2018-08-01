package com.youngsun.admin.dictionary.entity;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by 国平 on 2016/10/20.
 */
@MappedSuperclass
public class BasicEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
     * 插入时间
     */
    @Column(name = "t_insert_date")
    private Date insertDate;
    /**
     * 更新时间
     */
    @Column(name = "t_update_date")
    private Date upDateDate;
    /**
     * 插入者ID
     */
    @Column(name = "t_insert_userid")
    private Long insertUserId;
    /**
     * 更新者ID
     */
    @Column(name = "t_update_userid")
    private Long upDateUserId;
    /**
     * 是否是删除
     */
    @Column(name = "t_isDeleted")
    private Boolean isDeleted;

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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
