<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
    <link rel="stylesheet" href="styles/login.css">
    <link rel="stylesheet" href="styles/header.css">
</head>
<body>
<div id="bg"></div>
<%@include file="header.jsp"%>

<div id="for_body">

    <form class="login" action="login" method="post">
        <p style="color:red;font-size:20px;background-color:powderblue;">
        </p>

        <%
            if (request.getAttribute("error") != null) {
        %>
        <%=request.getAttribute("error")%><br>
        <%
            }
        %>
        <div class="form-field">
            <input type="text" id="username" name="username" placeholder="Username" required/>
        </div>

        <div class="form-field">
            <input type="password" id="password" name="password" placeholder="Password" required/></div>

        <div class="form-field">
            <button class="btn" type="submit">Log in</button>
        </div>
    </form>
</div>


</body>
</html>