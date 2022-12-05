<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Race</title>
    <link rel="stylesheet" href="./styles/login.css">
    <link rel="stylesheet" href="./styles/header.css">
</head>
<%@include file="header.jsp" %>


<body>

<div id="for_body">
    <form class="createRace" action="createRace" method="post">
        <p style="color:red;font-size:20px;background-color:powderblue;">
            <%
                if (request.getAttribute("message") != null) {
            %>
            <%=request.getAttribute("message")%><br>
            <%
                }
            %>
        </p>

        <div class="form-field">
            <input type="text" id="name" name="name" placeholder="Please write full caption for Race!" required/>
        </div>

        <div class="form-field">
            <input type="date" id="startDate" name="startDate" placeholder="Race will start!" required/></div>

        <div class="form-field">
            <button class="btn" type="submit">Create Race!!!</button>
        </div>
    </form>
</div>
</body>
</html>
