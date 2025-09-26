package com.codegym.myapp.models;

import com.codegym.myapp.database.DBConnect;

import java.sql.Connection;

public class BaseModel {
    protected Connection conn;

    public BaseModel() {
        DBConnect dbConnect = new DBConnect();
        this.conn = dbConnect.getConnection();
    }
}
