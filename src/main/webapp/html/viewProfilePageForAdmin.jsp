<%@ page import="com.example.race.beans.User" %>
<%@ page import="com.example.race.beans.History" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Abbosxon
  Date: 12/6/2022
  Time: 9:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change profile details</title>
    <link rel="stylesheet" href="styles/header.css">
    <%User user = (User) request.getAttribute("user");%>
    <%List<History> historyList = (List<History>) request.getAttribute("winningHistory");%>

</head>
<body>
<%@include file="header.jsp" %>
<table id="history">
    <tr>
        <td>FullName</td>
        <td>${user.fullName}</td>
    </tr>
    <tr>
        <td>Username</td>
        <td>${user.username}</td>
    </tr>
    <tr>
        <td>Balance</td>
        <td>${user.balance}</td>
    </tr>
</table>
<br><br>

<table id="history">
    <th>Race name</th>
    <th>Won money</th>

    <%
        for (int i = 0; i < historyList.size(); i++) {%>
    <tr>
        <td><%= historyList.get(i).getRace() %>
        </td>
        <td><%=historyList.get(i).getWinMoney() %>
        </td>
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
</style>
</body>
</html>
