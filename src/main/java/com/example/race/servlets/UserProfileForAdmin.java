package com.example.race.servlets;

import com.example.race.beans.History;
import com.example.race.beans.User;
import com.example.race.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/userProfile")
public class UserProfileForAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationDao dao = new ApplicationDao();
        String changingUsername = req.getQueryString().substring(req.getQueryString().indexOf("=") + 1);
        System.out.println(req.getQueryString().substring(req.getQueryString().indexOf("=") + 1));
        User user = dao.getProfileDetails(changingUsername);
        List<History> winningHistory = dao.getHistories(changingUsername);

        req.setAttribute("user", user);
        req.setAttribute("winningHistory", winningHistory);
        System.out.println(winningHistory.size());
        req.getRequestDispatcher("/html/viewProfilePageForAdmin.jsp").forward(req, resp);
    }
}
