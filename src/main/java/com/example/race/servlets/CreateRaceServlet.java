package com.example.race.servlets;

import com.example.race.beans.Races;
import com.example.race.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/createRace")
public class CreateRaceServlet extends HttpServlet   {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        Date startDate = null;
        Date bettingDeadlineDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("startDate"));
            bettingDeadlineDate=new DateTime(startDate).minusDays(2).toDate();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ;
        Races races = new Races();
        races.setName(name);
        races.setStartDate(new java.sql.Date(startDate.getTime()));
        races.setBettingDeadlineDate(new java.sql.Date(bettingDeadlineDate.getTime()));

        ApplicationDao dao = new ApplicationDao();
        dao.createRace(races);
        req.setAttribute("message","Race created!!!");
        req.getRequestDispatcher("/html/createRace.jsp").forward(req, resp);

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/createRace.jsp").forward(req, resp);
    }
}
