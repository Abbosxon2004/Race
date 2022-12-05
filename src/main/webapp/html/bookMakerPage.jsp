<%@ page import="com.example.race.beans.Races" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.race.beans.Horses" %><%--
  Created by IntelliJ IDEA.
  User: Abbosxon
  Date: 12/3/2022
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book maker</title>
    <link rel="stylesheet" href="./styles/header.css">


</head>
<body>
<%List<Races> racesList = (List<Races>) request.getAttribute("openRaces");%>
<%List<Horses> horsesList = (List<Horses>) request.getAttribute("horseList");%>
<%@include file="header.jsp" %>
<br><br><br><br><br><br>
<center>
    <form action="bookMakerPage" method="post">
        <p style="color:red;font-size:20px;background-color:powderblue;">
            <%
                if (request.getAttribute("message") != null) {
            %>
            <%=request.getAttribute("message")%>
            <br>
            <%}%>
        </p>

        <label for="races">Choose a Race:</label>
        <select name="race_name" id="races">
            <optgroup label="Open Races">
                <%
                    for (int i = 0; i < racesList.size(); i++) {%>
                <option value='<%=((Races) racesList.get(i)).getName()%>'><%= ((Races) racesList.get(i)).getName()%>
                </option>
                <%}%>
            </optgroup>
        </select>

        <label for="horses">Choose a Horse:</label>
        <select name="horse_name" id="horses">
            <optgroup label="Horse list">
                <% for (int i = 0; i < horsesList.size(); i++) {%>
                <option value='<%=((Horses) horsesList.get(i)).getName()%>'><%= ((Horses) horsesList.get(i)).getName()%>
                </option>
                <%}%>
            </optgroup>
        </select>

        <br><br>
        <div class="form-field">
            <label for="bookMaker">Set coefficient for horse</label>
            <input type="number" id="bookMaker" name="coefficient_degree" placeholder="Coefficient degree" required/>
        </div>


        <br><br>
        <input type="submit" value="Submit" style="height:40px; width:90px">
    </form>
</center>
</body>
</html>
