package com.youngsun.admin.vo;



import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 组织机构类型的规则表，用来确定组织之间的关系
 * Created by 国平 on 2016/10/20.
 */
public class OrgTypeRuleVo extends BasicVo  implements Serializable {
    @NotNull
    private String pid;
    @NotNull
    private String cid;
    @NotNull
    @Digits(integer= 5 ,fraction= 0 )
    private Integer num;
    private String rid;
    
    public String getPid() {
        return pid;
    }
    
    public void setPid(String pid) {
        this.pid = pid;
    }
    
    public String getCid() {
        return cid;
    }
    
    public void setCid(String cid) {
        this.cid = cid;
    }
    
    public Integer getNum() {
        return num;
    }
    
    public void setNum(Integer num) {
        this.num = num;
    }
    
    public String getRid() {
        return rid;
    }
    
    public void setRid(String rid) {
        this.rid = rid;
    }
}

