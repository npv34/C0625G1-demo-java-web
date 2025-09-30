package com.codegym.myapp.controllers;

import com.codegym.myapp.services.AdminService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
@MultipartConfig
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/*"})
public class AdminController extends BaseController {
    protected String uploadDir;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    private final AdminService adminService;
    public AdminController() {
        this.adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        switch (path) {
            case "/dashboard":
                adminService.renderDashboardPage(req, resp);
                break;
            case "/users":
                adminService.renderUsersPage(req, resp);
                break;
            case "/users/create":
                adminService.renderCreateUsersPage(req, resp);
                break;
            default:
                System.out.println("Not found");
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        uploadDir = getServletContext().getRealPath("uploads"); // Example: stores in webapp/uploads
        System.out.println(uploadDir);
        switch (path) {
            case "/users/store":
                adminService.createUser(req, resp, uploadDir);
                break;
        }
    }
}
