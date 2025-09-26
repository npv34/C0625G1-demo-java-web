package com.codegym.myapp.entities;

public class User {
    private int id;
    private String email;
    private String password;
    private String imageUrl;
    private String name;
    private String phone;

    public User() {
    }

    public User(int id, String email, String password, String imageUrl, String name, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
