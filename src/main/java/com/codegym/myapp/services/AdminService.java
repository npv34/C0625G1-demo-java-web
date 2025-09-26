package com.codegym.myapp.services;

import com.codegym.myapp.entities.User;
import com.codegym.myapp.models.UserModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService extends BaseService {
    private UserModel userModel;

    public AdminService() {
        super();
        this.userModel = new UserModel();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        ResultSet result = this.userModel.getAllUsers();
        while (result.next()) {
            User user = new User();
            user.setId(result.getInt("id"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
            user.setEmail(result.getString("email"));
            user.setImageUrl(result.getString("imageUrl"));
            user.setPhone(result.getString("phone"));
            users.add(user);
        }
        return users;
    }

    public void renderDashboardPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/index.jsp");
        try {
            req.setAttribute("message", "Welcome to Admin Dashboard");
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void renderUsersPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/users.jsp");
        try {
            List<User> users = this.getAllUsers();
            System.out.println(users.size());
            req.setAttribute("users", users);
            requestDispatcher.forward(req, resp);
        } catch (ServletException | SQLException e) {
            e.printStackTrace();
        }
    }

}
