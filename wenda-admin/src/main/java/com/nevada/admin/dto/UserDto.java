package com.nevada.admin.dto;

import javax.validation.constraints.NotEmpty;

public class UserDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
