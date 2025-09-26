package com.codegym.myapp.services;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthService extends BaseService {

    public AuthService() {
        super();
    }

    public void renderLoginPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/auth/login.jsp");
        try {
            requestDispatcher.include(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    public void renderRegisterPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("<form method='POST' action='" + req.getContextPath() + "/auth/login'>");
        out.write("Username: <input type='text' name='username'/><br/>");
        out.write("Password: <input type='password' name='password'/><br/>");
        out.write("<input type='submit' value='Login'/>");
        out.write("</form>");
        out.close();
    }
}
