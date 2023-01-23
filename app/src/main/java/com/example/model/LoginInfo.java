package com.example.model;

public class LoginInfo {
    public int id;
    public String phone;
    public String password;
    public Boolean remember=false;

    public LoginInfo() {
    }

    public LoginInfo(int id, String phone, String password, Boolean remember) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", remember=" + remember +
                '}';
    }
}
