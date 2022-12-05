<%@ page import="com.example.race.beans.Races" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Abbosxon
  Date: 12/4/2022
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Generate Race</title>
    <link rel="stylesheet" href="styles/header.css">

</head>
<body>
<div>
    <%@include file="header.jsp" %>
</div>
<%List<Races> racesList = (List<Races>) request.getAttribute("openRaces");%>
<br><br><br>
<center>
    <div>
        <h1>Only admins can generate race results</h1>

        <br>

        <form action="generateRace" method="post">
            <p style="color:red;font-size:20px;background-color:powderblue;">
                <%
                    if (request.getAttribute("message") != null) {
                %>
                <%=request.getAttribute("message")%><br>
                <%
                    }
                %>
            </p>

            <label for="races">Choose a Race and generate it:</label>
            <select name="race_name" id="races">
                <optgroup label="Open Races">
                    <%
                        for (int i = 0; i < racesList.size(); i++) {%>
                    <option value='<%=racesList.get(i).getName()%>'><%= racesList.get(i).getName()%>
                    </option>
                    <%}%>
                </optgroup>
            </select>

            <br><br>
            <input type="submit" value="Generate" style="height:40px; width:90px">

            <%
                if (String.valueOf(request.getAttribute("message")).equals("Race successfully generated.You can see it!")) {
            %>

            <button>
                <a href="racesResults" style="text-decoration: none; padding: 40px 50px">Results</a>
            </button>

            <%
                }
            %>
        </form>
    </div>
</center>
</body>
</html>
