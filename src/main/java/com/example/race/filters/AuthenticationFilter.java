package com.example.race.filters;

import com.example.race.dao.ApplicationDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) arg0;
        if (request.getRequestURI().contains("getProfileDetails")
                || request.getRequestURI().contains("createRace")) {
            HttpSession session = request.getSession();
            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("html/login.jsp").forward(request, arg1);
            }
        }
        if (request.getRequestURI().contains("createRace")) {
            HttpSession session = request.getSession();
            ApplicationDao dao = new ApplicationDao();

            if (!dao.getRoleByUsername(String.valueOf(session.getAttribute("username"))).equals("ADMIN")) {
                request.getRequestDispatcher("html/error.jsp").forward(request, arg1);
            }
        }
        arg2.doFilter(request, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
