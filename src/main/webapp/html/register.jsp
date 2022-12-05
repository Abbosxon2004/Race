<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="styles/register.css">
    <link rel="stylesheet" href="styles/header.css">
</head>
<body>
<div id="bg"></div>
<%@include file="header.jsp"%>

<div id="for_body">


    <form action="register" action="register" method="post">

        <p style="color:red;font-size:20px;background-color:powderblue;">
            Register and get 10000 USD money!!!
            <%
                if (request.getAttribute("message") != null) {
            %>
            <%=request.getAttribute("message")%><br>
            <%
                }
            %>
        </p>

        <div class="form-field">
            <input type="text" id="fullName" name="fullName" placeholder="FullName" required/>
        </div>

        <div class="form-field">
            <input type="text" id="username" name="username" placeholder="Username" required/>
        </div>

        <div class="form-field">
            <input type="password" id="password" name="password" placeholder="Password" required/></div>

        <div class="form-field">
            <button class="btn" type="submit">Register</button>
        </div>
    </form>
</div>


</body>
</html>