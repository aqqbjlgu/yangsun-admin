package com.youngsun.admin.vo;


import java.io.Serializable;
import java.util.List;


/**
 * Created by 国平 on 2016/10/20.
 */
public class UserVo extends BasicVo  implements Serializable {
    public static final String PRINCIPAL_TYPE = "user";
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓别
     */
    private String sex;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String image;
    /**
     * 状态
     */
    private String status;
    /**
     * 邮箱
     */
    private String email;
    private String orgName;
    private String posName;
    private List<UserOrgPosVo> userOrgPosVos;
    
    public String getOrgName() {
        return orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    public String getPosName() {
        return posName;
    }
    
    public void setPosName(String posName) {
        this.posName = posName;
    }
    
    public List<UserOrgPosVo> getUserOrgPosVos() {
        return userOrgPosVos;
    }
    
    public void setUserOrgPosVos(List<UserOrgPosVo> userOrgPosVos) {
        this.userOrgPosVos = userOrgPosVos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
