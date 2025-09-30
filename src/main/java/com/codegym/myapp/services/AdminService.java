package com.codegym.myapp.services;

import com.codegym.myapp.entities.User;
import com.codegym.myapp.models.UserModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/users/list.jsp");
        try {
            List<User> users = this.getAllUsers();
            System.out.println(users.size());
            req.setAttribute("users", users);
            requestDispatcher.forward(req, resp);
        } catch (ServletException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void renderCreateUsersPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/users/create.jsp");
        try {
            List<User> users = this.getAllUsers();
            System.out.println(users.size());
            req.setAttribute("users", users);
            requestDispatcher.forward(req, resp);
        } catch (ServletException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(HttpServletRequest request, HttpServletResponse response, String uploadDir) throws ServletException, IOException {
        // lay du lieu tu request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Lay file tu request -> upload len he thong
        // Get the file part from the request
        Part filePart = request.getPart("imageUrl"); // "imageUrl" matches the name attribute in the HTML input
        System.out.println(filePart);
        Path uploadPath = Paths.get(uploadDir);
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        System.out.println("file: " + fileName);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        fileName = Instant.now().getEpochSecond() + "-" + fileName;
        // Write the file to the server
        Path filePath = Paths.get(uploadDir, fileName);
        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, filePath);
        }

        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPhone(phone);
        u.setImageUrl(fileName);
        u.setPassword(password);
        userModel.save(u);

        response.sendRedirect("/admin/users");
    }

}
