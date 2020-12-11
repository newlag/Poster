package com.newlag.poster.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("fullname")
    private String fullname;
    @SerializedName("username")
    private String name;
    @SerializedName("password")
    private String password;
    @SerializedName("image")
    private String avatar;
    @SerializedName("user_id")
    private String userId;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String fullname, String name, String password, String avatar) {
        this.fullname = fullname;
        this.name = name;
        this.password = password;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUserId() {
        return userId;
    }
}
