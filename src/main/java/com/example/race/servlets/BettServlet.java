package com.example.race.servlets;

import com.example.race.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/bettingMoney")
public class BettServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String race_name = (String) req.getAttribute("race_name");
        String race_name = req.getParameter("race_name");
        System.out.println(race_name);
        Integer for_betting_money = Integer.valueOf(req.getParameter("betting_money"));
        String username = (String) req.getSession().getAttribute("username");
        String horse_name = (String) req.getParameter("horse_name");
        String horse_position = (String) req.getParameter("horse_position");
        ApplicationDao dao=new ApplicationDao();
        boolean success = dao.bettingMoney(username, race_name, for_betting_money, horse_name, horse_position);
        String message=null;
        if (success)
            message="You successfully bet for Race";
        else
            message="Something went wrong.Please try again!!!";
        req.setAttribute("message",message);
//        req.getRequestDispatcher("/getProfileDetails").forward(req, resp);
        resp.sendRedirect("http://localhost:8080/Race_war_exploded/getProfileDetails");
    }
}
