package com.foodproduct.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Authenticate the user against the database
        if (authenticateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("admin.jsp"); // Redirect to the admin page
        } else {
            response.sendRedirect("login.jsp?error=true"); // Redirect to the login page with an error parameter
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Perform authentication logic using the "users" table in the database
        // Return true if authentication is successful, false otherwise
    	if ("admin".equals(username) && "password".equals(password)) {
            return true; // Authentication successful
        } else {
            return false; // Authentication failed
        }
    }
}
