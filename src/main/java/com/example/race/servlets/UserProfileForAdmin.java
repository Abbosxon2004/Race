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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ApplicationDao dao = new ApplicationDao();
        String changingUsername = req.getParameter("changingUsername");
        System.out.println(changingUsername);
        User user = dao.getProfileDetails(changingUsername);
        List<History> winningHistory = dao.getHistories(changingUsername);

        //store all information in request object
        req.setAttribute("user", user);
        req.setAttribute("winningHistory", winningHistory);

        //forward control to profile jsp
        req.getRequestDispatcher("/html/viewProfilePageForAdmin.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationDao dao = new ApplicationDao();

        String url=req.getRequestURI();
        String changingUsername = url.substring(url.indexOf("changingUsername=") + 17);

//        String changingUsername = (String) req.getSession().getAttribute("changingUsername");
        System.out.println(changingUsername);
        System.out.println("ishadi");
        User user = dao.getProfileDetails(changingUsername);
        List<History> winningHistory = dao.getHistories(changingUsername);

        //store all information in request object
        req.setAttribute("user", user);
        req.setAttribute("winningHistory", winningHistory);
        System.out.println(winningHistory.size());
        //forward control to profile jsp
        req.getRequestDispatcher("/html/viewProfilePageForAdmin.jsp").forward(req, resp);
    }
}
