package com.koko.it.entity;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String mobile;
    private String email;
    private String password;
    private String insertUid;
    private String insertTime;
    private String updateTime;
    private Integer isDel; //是否删除（0：正常；1：已删）
    private Integer isJob; //是否在职（0：正常；1，离职）
    private String mcode;
    private String sendTime;
    private String version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInsertUid() {
        return insertUid;
    }

    public void setInsertUid(String insertUid) {
        this.insertUid = insertUid;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getIsJob() {
        return isJob;
    }

    public void setIsJob(Integer isJob) {
        this.isJob = isJob;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    @Override public String toString() {
        return "UserDTO{" + "id=" + id + ", username='" + username + '\''
                + ", mobile='" + mobile + '\'' + ", email='" + email + '\''
                + ", password='" + password + '\'' + ", code='" ;
    }
}
