<%@ page import="java.util.Map" %>
<%@ page import="com.example.race.beans.Races" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.race.beans.RaceResults" %>
<%@ page import="com.example.race.beans.Horses" %><%--
  Created by IntelliJ IDEA.
  User: Abbosxon
  Date: 12/4/2022
  Time: 5:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All results</title>
    <link rel="stylesheet" href="styles/header.css">

</head>
<body>
<%@include file="header.jsp" %>

<%Map<String, List<RaceResults>> racesResults = (Map<String, List<RaceResults>>) request.getAttribute("racesResults");%>
<%List<Races> racesList = (List<Races>) request.getAttribute("generatedRaces");%>
<%List<Horses> horseList = (List<Horses>) request.getAttribute("horseList");%>

<br><br><br>
<table id="history">
    <center><h1>All races results</h1></center>
    <th>Race names</th>
    <%for (Horses horses : horseList) {%>
    <th><%=horses.getName()%>
    </th>
    <%
        }
    %>


    <%
        for (int i = 0; i < racesList.size(); i++) {
            List<RaceResults> resultsList = racesResults.get(racesList.get(i).getName());
            if (resultsList != null) {%>
    <tr>

        <td>
            <%=racesList.get(i).getName()%>
        </td>
        <%
            for (RaceResults result : resultsList) {
        %>
        <td>
            <%=result.getPosition()%>
        </td>
        <%
                }
            }
        %>
    </tr>
    <%
        }
    %>
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
