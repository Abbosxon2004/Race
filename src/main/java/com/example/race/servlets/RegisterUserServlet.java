package com.example.race.servlets;

import com.example.race.beans.User;
import com.example.race.beans.enums.Roles;
import com.example.race.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;


@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // collect all form data
        String fullName = req.getParameter("fullName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Integer balance = 10000;

        ApplicationDao dao = new ApplicationDao();
        boolean existsUser = dao.existsUser(username);
        String infoMessage = null;
        if (!existsUser) {
            // fill it up in a User bean
            User user = new User();
            user.setFullName(fullName);
            user.setRole(Roles.USER.name());
            user.setUsername(username);
            user.setPassword(password);
            user.setBalance(balance);


            // call DAO layer and save the user object to DB

            int rows = dao.registerUser(user);

            // prepare an information message for user about the success or failure of the operation

            if (rows == 0) {
                infoMessage = "Sorry, an error occurred!";
            } else {
                infoMessage = "User registered successfully!";
            }
        } else
            infoMessage = "User already exists, Please try again!";
        // write the message back to the page in client browser\
        req.setAttribute("message", infoMessage);
        req.getRequestDispatcher("/html/register.jsp").forward(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/register.jsp").forward(req, resp);
    }


}
