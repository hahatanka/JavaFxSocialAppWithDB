package com.socialapp.socialapp.model;

import java.sql.Timestamp;

public class Post {
    public int post_id;
    public String username;
    public String postText;
    public Timestamp createdAt;

    public Post(int post_id, String username, String postText, Timestamp createdAt) {
        this.post_id = post_id;
        this.username = username;
        this.postText = postText;
        this.createdAt = createdAt;
    }


    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostText() {
        return postText;
    }

    public void setPost(String postText) {
        this.postText = postText;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
