package com.example.race.servlets;

import com.example.race.beans.Horses;
import com.example.race.beans.RaceResults;
import com.example.race.beans.Races;
import com.example.race.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/racesResults")
public class RaceResultsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationDao dao=new ApplicationDao();
        Map<String, List<RaceResults>> racesResults = dao.getRacesResults();
        List<Races> generatedRaces = dao.getGeneratedRaces();
        List<Horses> horseList = dao.getHorseList();
        req.setAttribute("generatedRaces",generatedRaces);
        req.setAttribute("racesResults", racesResults);
        req.setAttribute("horseList", horseList);
        req.getRequestDispatcher("/html/raceResults.jsp").forward(req, resp);
    }
}
