package com.youngsun.admin.vo;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 国平 on 2016/10/20.
 */
public class OrgTypeVo extends BasicVo  implements Serializable {

    /**
     * 类型的序号
     */
    @NotNull
    private String sn;
    /**
     * 类型的名称
     */
    @NotNull
    private String name;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
