package com.example.race.servlets;

import com.example.race.beans.User;
import com.example.race.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/userList")
public class AllUsersForAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationDao dao = new ApplicationDao();
        List<User> allUsersForAdmin = dao.getAllUsersForAdmin();
        req.setAttribute("userList", allUsersForAdmin);
        req.getRequestDispatcher("/html/userListAdmin.jsp").forward(req, resp);
    }
}
