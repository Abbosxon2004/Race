<%@ page import="com.example.race.beans.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Abbosxon
  Date: 12/6/2022
  Time: 9:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users List</title>
    <link rel="stylesheet" href="styles/header.css">

    <%List<User> userList = (List<User>) request.getAttribute("userList");%>
</head>
<body>
<%@include file="header.jsp" %>


<table id="history">
    <th>Fullname</th>
    <th>Username</th>
    <th>Role</th>
    <th>Balance</th>

    <%
        for (User user : userList) {
            request.getSession().setAttribute("changingUsername", user.getUsername());
    %>

    <tr>
        <div>
            <td><%= user.getFullName()%>
            </td>
            <%--            <td><a href="http://localhost:8080/Race_war_exploded/userProfile?changingUsername=<%= user.getUsername() %>"><%= user.getUsername() %>--%>
            <%--            </a>--%>
            <%--            </td>--%>
            <td><%= user.getUsername() %>
            </td>
            <td><%= user.getRole() %>
            </td>
            <td><%= user.getBalance() %>
            </td>
        </div>

    </tr>
    <%}%>
</table>

<style>
    #history {
        font-family: Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    #history td, #customers th {
        border: 1px solid #ddd;
        padding: 8px;
    }

    #history tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    #history tr:hover {
        background-color: #ddd;
    }

    #history th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;
        background-color: #04AA6D;
        color: white;
    }

    a {
        text-decoration: none !important;
    }
</style>

</body>
</html>
