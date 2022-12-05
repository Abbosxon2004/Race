package com.example.race.servlets;

import com.example.race.beans.History;
import com.example.race.beans.Horses;
import com.example.race.beans.Races;
import com.example.race.beans.User;
import com.example.race.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/getProfileDetails")
public class ViewProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the username from the session
        System.out.println("User name in profile servlet  :" + request.getSession().getAttribute("username"));
        String username = (String) request.getSession().getAttribute("username");


        //call dao and get profile details
        ApplicationDao dao = new ApplicationDao();
        User user = dao.getProfileDetails(username);
        List<History> winningHistory = dao.getHistories(username);
        List<Races> openRaces = dao.getOpenRaces();
        List<Horses> horsesList = dao.getHorseList();

        //store all information in request object
        request.setAttribute("user", user);
        request.setAttribute("openRaces", openRaces);
        request.setAttribute("winningHistory", winningHistory);
        request.setAttribute("horseList",horsesList);

        //forward control to profile jsp
        request.getRequestDispatcher("/html/profile.jsp").forward(request, response);
    }
}
