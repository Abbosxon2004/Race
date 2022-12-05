<%@page import="java.util.Calendar" %>
<%@ page import="com.example.race.beans.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.race.beans.History" %>
<%@ page import="com.example.race.beans.Races" %>
<%@ page import="com.example.race.beans.Horses" %>

<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="styles/header.css">
</head>
<body>


<myTags:formatDate date="<%=Calendar.getInstance().getTime() %>" format="dd-MM-YYYY"/>

<%@include file="header.jsp" %>

<%User user = (User) request.getAttribute("user");%>
<%List<History> historyList = (List<History>) request.getAttribute("winningHistory");%>
<%List<Races> racesList = (List<Races>) request.getAttribute("openRaces");%>
<%List<Horses> horsesList = (List<Horses>) request.getAttribute("horseList");%>

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
<br>


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

<h1>
    <center>Bet money for your future on Horses Race</center>
</h1>
<br>

<center>
    <h3>
        <form action="bettingMoney" method="post">
            <p style="color:red;font-size:20px;background-color:powderblue;">
                <%
                    if (request.getAttribute("message") != null) {
                %>
                <%=request.getAttribute("message")%><br>
                <%
                    }
                %>
            </p>

            <label for="races">Choose a Race:</label>
            <select name="race_name" id="races">
                <optgroup label="Open Races">
                    <%
                        for (int i=0;i<racesList.size();i++) {%>
                    <option value='<%=((Races)racesList.get(i)).getName()%>'><%=racesList.get(i).getName()%>
                    </option>
                    <%}%>
                </optgroup>
            </select>

            <br><br>
            <div class="form-field">
                <label for="betting_money">How much money you want to bet!</label>
                <input type="number" id="betting_money" name="betting_money" max="<%=user.getBalance()%>"
                       placeholder="Money amount" required/>
            </div>

            <br><br>
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
            <label for="position">Bet for Positions:</label>
            <select name="horse_position" id="position">
                <optgroup label="Position">
                    <option  value="first_three_winners">First 3 positions</option>
                    <% for (Horses horse : horsesList) {%>
                    <option  value=<%=horse.getId()%>><%=horse.getId()%>
                        place
                    </option>
                    <%}%>
                </optgroup>
            </select>

            <br><br>
            <input type="submit" value="Submit" style="height:40px; width:90px">
        </form>
    </h3>
</center>


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

<br>
<h1>
    <center>All your races and winning money history</center>
</h1>
<br>
<table id="history">
    <th>Race name</th>
    <th>Won money</th>

    <%
        for (int i = 0; i < historyList.size(); i++) {%>
    <tr>
        <td><%= ((History) historyList.get(i)).getRace() %>
        </td>
        <td><%= ((History) historyList.get(i)).getWinMoney() %>
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