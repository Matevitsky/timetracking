<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/user/i18n.jsp" %>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <link href="/css/loginForm.css">

    <title>Time Tracker Login Page</title>


</head>
<body>


<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link">Login/Вход</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Register/Регистрация</a>

                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="/app" method="post" role="form"
                                  style="display: block;">
                                <input type="hidden" name="command" value="login">
                                <div class="form-group">
                                    <input type="text" name="email" id="email" tabindex="1" class="form-control"
                                           placeholder="Email" value=""><span id="loginValid"></span>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2"
                                           class="form-control" placeholder="Password">
                                </div>
                                <font color="red"><c:out value="${error}"/></font>
                                <div class="form-group text-center">
                                    <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                    <label for="remember"> Remember Me/Запомнить</label>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4"
                                                   class="btn btn-primary btn-lg btn-block" value="Log In/Войти"
                                                   disabled>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-lg-12">

                                        </div>
                                    </div>
                                </div>

                                <script src="/js/loginEmailValidation.js"></script>
                            </form>


                            <form id="register-form" action="/app" method="post"
                                  role="form" style="display: none;">
                                <input type="hidden" name="command" value="register">
                                <div class="form-group">
                                    <input type="text" name="username" id="usernameRegistration" tabindex="1"
                                           class="form-control"
                                           placeholder="Username" value="">
                                </div>
                                <div class="form-group">
                                    <input type="emailRegistration" name="emailRegistration" id="emailRegistration"
                                           tabindex="1"
                                           class="form-control"
                                           placeholder="Email Address" value=""><span id="registrationValid"></span>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="passwordRegistration" tabindex="2"
                                           class="form-control" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="confirm-password" id="confirm-password" tabindex="2"
                                           class="form-control" placeholder="Confirm Password">
                                </div>
                                <font color="red"><c:out value="${error}"/></font>
                                <div class="form-group">
                                    <div class="row">

                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit"
                                                   tabindex="4" class="btn btn-primary btn-lg btn-block"
                                                   value="Register Now" disabled>
                                        </div>
                                    </div>
                                </div>

                                <script src="/js/registrationEmailValidation.js"></script>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<script> $(function () {

    $('#login-form-link').click(function (e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function (e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

});
</script>

</body>

</html>