<%@ page import="com.example.race.dao.ApplicationDao" %><%--
  Created by IntelliJ IDEA.
  User: Abbosxon
  Date: 12/3/2022
  Time: 3:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
</head>
<header id="header">
    <a href="redirect" class="logo">Real horse rising</a>
    <div id="header-right">
        <a id="active" href="home">Home</a>

        <%
            ApplicationDao dao = new ApplicationDao();
            if (dao.getRoleByUsername(String.valueOf(request.getSession().getAttribute("username"))).equals("ADMIN")) {
        %>
        <a href="createRace">Create new Race?</a>
        <a href="generateRace">Generate Races</a>
        <a href="racesResults">All results</a>
        <a href="userList">All Users</a>
        <%} %>

        <%
            if (dao.getRoleByUsername(String.valueOf(request.getSession().getAttribute("username"))).equals("BOOK_MAKER")) {
        %>
        <a href="bookMakerPage">SET coefficient</a>
        <%} %>

        <% if (request.getSession().getAttribute("username") == null) {%>
        <a href="login">Login</a>
        <a href="register">New user?</a>
        <%} else {%>
        <a href="getProfileDetails">Profile</a>
        <a href='logout'>logout</a>
        <%}%>
    </div>
</header>
<body>

</body>
</html>
