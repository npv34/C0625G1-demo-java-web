package com.codegym.myapp.controllers;

import com.codegym.myapp.services.AdminService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/*"})
public class AdminController extends BaseController {

    private final AdminService adminService;
    public AdminController() {
        this.adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        System.out.println(path);
        switch (path) {
            case "/dashboard":
                adminService.renderDashboardPage(req, resp);
                break;
            case "/users":
                adminService.renderUsersPage(req, resp);
                break;
            default:
                System.out.println("Not found");
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

}
