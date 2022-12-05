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

@WebServlet("/generateRace")
public class GenerateRaceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String race_name = req.getParameter("race_name");
        System.out.println(race_name);
        ApplicationDao dao = new ApplicationDao();
        boolean status = dao.generateRace(race_name);

        String infoMessage = null;
        if (!status) {
            infoMessage = "Sorry, an error occurred!";
        } else {
            infoMessage = "Race successfully generated.You can see it!";
        }


        List<Races> openRaces = dao.getOpenRaces();
        req.setAttribute("openRaces", openRaces);
        req.setAttribute("message", infoMessage);
        req.getRequestDispatcher("/html/generateRace.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get the username from the session
        System.out.println("User name in profile servlet  :" + req.getSession().getAttribute("username"));

        ApplicationDao dao = new ApplicationDao();
        List<Races> openRaces = dao.getOpenRaces();

        req.setAttribute("openRaces", openRaces);

        req.getRequestDispatcher("/html/generateRace.jsp").forward(req, resp);
    }
}
