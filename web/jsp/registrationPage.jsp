<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Time Tracking Registration page</title>

    <link rel="stylesheet" href="<c:url value="../css/MyCss.css"/>">

</head>
<body>
<div id="wrapper">

    <form name="login-form" class="login-form" action="" method="post">
        <input type="hidden" name="command" value="login">
        <div class="header">
            <h1>Login Form</h1>
            <span>Fill out the form below to Registration.</span>
        </div>

        <div class="content">
            <input name="name" type="text" class="input password" placeholder="Name"/>
            <input name="email" type="text" class="input password" placeholder="Email "/>
            <input name="password" type="password" class="input password" placeholder="Password"/>

        </div>

        <div class="footer">
            <form>
                <input type="submit" name="command" value="Register" class="button"/>
            </form>

        </div>

    </form>

</div>
<div class="gradient"></div>
</body>
</body>
</html>