package com.codegym.myapp.models;

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

}
