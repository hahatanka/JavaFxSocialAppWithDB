package com.socialapp.socialapp.model;

import java.sql.Timestamp;

public class User {
   private Integer id;
   private String name;
   private String userName;
   private String password;
   private String location;
   private String email;
   private String about;
   private Timestamp createdAt;
   private Timestamp updatedAt;

    public User(Integer id, String name, String userName, String location, String email, String about, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.location = location;
        this.email = email;
        this.about = about;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(String name, String userName, String location, String email, String about, Timestamp createdAt, Timestamp updatedAt) {
        this.name = name;
        this.userName = userName;
        this.location = location;
        this.email = email;
        this.about = about;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", about='" + about + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
