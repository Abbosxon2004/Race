package com.example.race.servlets;

import com.example.race.dao.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    public Connection connection = null;

    //lifecycle methods - init, service, destroy
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in doGET method");

        //forward the control to the index.html
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {

        //initialization activity - set up DB connection object
        System.out.println("in init method");
        connection = DBConnection.getConnectionToDatabase();
    }

    @Override
    public void destroy() {
        System.out.println("in destroy method");
        //clean up activity - close DB connection object
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
