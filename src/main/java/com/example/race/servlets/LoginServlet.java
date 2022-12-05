package com.example.race.servlets;

import com.example.race.dao.ApplicationDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/html/login.jsp");
        dispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        ApplicationDao dao = new ApplicationDao();
        boolean isValidUser = dao.validateUser(username, password);

        if (isValidUser) {
            HttpSession session = req.getSession();

            session.setAttribute("username", username);

            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            String errorMessage = "Invalid Credentials, please login again!";
            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("/html/login.jsp").forward(req, resp);
        }
    }
}
