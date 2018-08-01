package com.youngsun.admin.dictionary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_dict")
public class DictionaryEntity extends BasicEntity implements Serializable {

    /**
     * 名称
     */
    @Column(name = "t_name")
    private String name;

    /**
     * 类型
     */
    @Column(name = "t_type")
    private String type;

    /**
     * 值
     */
    @Column(name = "t_value")
    private String value;

    /**
     * 描述
     */
    @Column(name = "t_desc")
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
