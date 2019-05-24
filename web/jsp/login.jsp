<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="<c:url value="../css/MyCss.css"/>">
    <%--    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css"/>">--%>
</head>
<body>
<div id="wrapper">

    <form name="login-form" class="login-form" action="/app" method="post">
        <input type="hidden" name="command" value="login">
        <div class="header">
            <h1>Login Form</h1>
            <span>Fill out the form below to login to my super awesome imaginary control panel.</span>
        </div>

        <div class="content">
            <input name="email" type="text" class="input email" placeholder="Email "/>
            <div class="user-icon"></div>
            <input name="password" type="password" class="input password" placeholder="Password"/>
            <div class="pass-icon"></div>
        </div>

        <div class="footer">

            <input type="submit" name="submit" value="Login" class="button"/>
            <input type="submit" name="submit" value="Register" class="register"/>
        </div>

    </form>

</div>
<div class="gradient"></div>
</body>
</html>

