<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <div id="wrapper">

        <form name="login-form" class="login-form" action="/login" method="post">

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
</head>
<body>

</body>
</html>


<%--
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Guru Registration Form</title>
</head>
<body>
<h1>Login Form</h1>
<form action="${pageContext.request.contextPath}/login" method="post">
    <table style="with: 50%">

        <tr>
            <td>Email</td>
            <td><input type="text" name="email"/></td>
        </tr>

        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>

    </table>
    <input type="submit" value="Submit"/></form>
</body>
</html>--%>
