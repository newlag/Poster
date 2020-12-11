package com.newlag.poster.model;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("post_id")
    private String post_id;
    @SerializedName("text")
    private String content;
    @SerializedName("date")
    private Long date;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("username")
    private String username;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("image")
    private String userImage;

    public String getPost_id() {
        return post_id;
    }

    public String getContent() {
        return content;
    }

    public Long getDate() {
        return date;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUserImage() {
        return userImage;
    }
}
