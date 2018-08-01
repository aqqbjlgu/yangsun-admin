package com.youngsun.admin.vo;



import java.io.Serializable;
import java.util.List;

/**组织对象，该表能生成完整的对像。
 * 根据组织类型具体存储实际中存在的组织
 * Created by 国平 on 2016/10/20.
 */
public class OrgVo extends BasicVo  implements Serializable {
    /**
     * 组织机构名称
     */
    private String name;
    /**
     * 组织机构分类ID
     */
    private String typeId;
    /**
     * 组织机构分类名称
     */
    private String typeName;
    /**
     * 组织机构排序号
     */
    private Integer orderNo;
    /**
     * 组织机构父类ID
     */
    private Long parentId;
    /**
     * 组织机构地址
     */
    private String address;
    /**
     * 组织机构电话
     */
    private String phone;
    /**
     * 是否为叶子节点
     */
    private boolean isLeaf;
    /**
     * 管理类型
     */
    private String managerType;
    /**
     * 备用字段
     */
    private String att1;
    /**
     * 备用字段
     */
    private String att2;
    /**
     * 备用字段
     */
    private String att3;
    /**
     * 备用字段
     */
    private String att4;
    /**
     * 备用字段
     */
    private String att5;
    /**
     * 备用字段
     */
    private String att6;

    private List<String> orgRuleIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public String getManagerType() {
        return managerType;
    }

    public void setManagerType(String managerType) {
        this.managerType = managerType;
    }

    public String getAtt1() {
        return att1;
    }

    public void setAtt1(String att1) {
        this.att1 = att1;
    }

    public String getAtt2() {
        return att2;
    }

    public void setAtt2(String att2) {
        this.att2 = att2;
    }

    public String getAtt3() {
        return att3;
    }

    public void setAtt3(String att3) {
        this.att3 = att3;
    }

    public String getAtt4() {
        return att4;
    }

    public void setAtt4(String att4) {
        this.att4 = att4;
    }

    public String getAtt5() {
        return att5;
    }

    public void setAtt5(String att5) {
        this.att5 = att5;
    }

    public String getAtt6() {
        return att6;
    }

    public void setAtt6(String att6) {
        this.att6 = att6;
    }

    public List<String> getOrgRuleIds() {
        return orgRuleIds;
    }
    
    public void setOrgRuleIds(List<String> orgRuleIds) {
        this.orgRuleIds = orgRuleIds;
    }
}
