package com.codegym.myapp.models;

import com.codegym.myapp.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserModel extends BaseModel{

    public UserModel() {
        super();
    }

    public ResultSet getAllUsers() {
        try {
            String sql = "SELECT * FROM users";
            Statement statement = conn.prepareStatement(sql);
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(User user) {
        try {
            String sql = "INSERT INTO users(name, email, phone, password, imageUrl) VALUE (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getImageUrl());
            statement.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
