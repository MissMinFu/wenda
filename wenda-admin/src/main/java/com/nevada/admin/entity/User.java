package com.nevada.admin.entity;

import io.swagger.annotations.ApiModelProperty;

public class User  {
    private int id;

    private String name;

    private String password;

    @ApiModelProperty(value = "头像")
    private String headUrl;

    @ApiModelProperty(value="手机号")
    private String phone;

    public User(){
    }

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
