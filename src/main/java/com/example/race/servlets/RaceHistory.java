package com.example.race.servlets;


import com.example.race.beans.History;
import com.example.race.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/raceHistory")
public class RaceHistory extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get username from session
        String username = (String) request.getSession().getAttribute("username");

        // call dao and get order history
        ApplicationDao dao = new ApplicationDao();
        List<History> orders = dao.getHistories(username);

        // set order data in request
        request.setAttribute("history", orders);

        // forward to home jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

}
