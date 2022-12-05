package com.example.race.servlets;

import com.example.race.beans.Horses;
import com.example.race.beans.Races;
import com.example.race.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/bookMakerPage")
public class BookMakerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String race = req.getParameter("race_name");
        String horse = req.getParameter("horse_name");
        int coefficient_degree = 1;
        coefficient_degree = Integer.parseInt(req.getParameter("coefficient_degree"));
        ApplicationDao dao = new ApplicationDao();
        boolean success = dao.setCoefficient(race, horse, coefficient_degree);

        String infoMessage = null;
        if (success)
            infoMessage = "Coefficients added successfully";
        else
            infoMessage = "Something went wrong!";

        List<Races> openRaces = dao.getOpenRaces();
        List<Horses> horsesList = dao.getHorseList();
        req.setAttribute("horseList", horsesList);
        req.setAttribute("openRaces", openRaces);

        req.setAttribute("message", infoMessage);
        req.getRequestDispatcher("/html/bookMakerPage.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationDao dao = new ApplicationDao();
        List<Races> openRaces = dao.getOpenRaces();
        List<Horses> horsesList = dao.getHorseList();
        req.setAttribute("horseList", horsesList);
        req.setAttribute("openRaces", openRaces);
        req.getRequestDispatcher("/html/bookMakerPage.jsp").forward(req, resp);
    }
}
