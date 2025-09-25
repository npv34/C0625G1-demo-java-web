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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        System.out.println(path);
        switch (path) {
            case "/dashboard":
                AdminService.renderDashboardPage(req, resp);
                break;
            default:
                System.out.println("Not found");
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

}
