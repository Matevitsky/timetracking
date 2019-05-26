<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Time Tracking</title>

    <link rel="stylesheet" href="<c:url value="../css/MyCss.css"/>">

</head>
<body>
<div id="wrapper">

    <form name="login-form" class="login-form" action="" method="post">
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
            <form>

                <input type="submit" name="command" value="Login" class="button"/>
            </form>
            <form>
                <input type="submit" value="Register" class="register"/>
            </form>
        </div>

    </form>

</div>
<div class="gradient"></div>
</body>
</html>

