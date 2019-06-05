<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/jsp/user/i18n.jsp" %>
<!doctype html>
<html>
<head>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Time Tracking User Page</title>

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Your custom styles (optional) -->
    <link href="css/style.css" rel="stylesheet">

    <!-- MDBootstrap Datatables  -->
    <link href="css/addons/datatables.min.css" rel="stylesheet">


    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>


    <title>User Page</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/navbar-static/">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


    <%--<link rel="stylesheet" href="<c:url value="../../css/createActivity.css"/>">--%>


</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">

    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">

            <form action="/app" method="get">
                <input type="submit" value="<fmt:message bundle="${common}" key="request.activity"/>"
                       data-target="#modal-1" class="btn btn-secondary btn-lg"/>
                <input type="hidden" name="command" value="user_request_activity">

            </form>
            <font color="green"><c:out value="${alert}"/></font>
        </ul>
    </div>


    <form action="/app" method="POST">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="uri" value="${pageContext.request.requestURI}">
        <select class="selectpicker picker" data-size="3" data-style="btn-info" style="width: 60%" name="locale"
                onchange="submit()">
            <option data-content='<span class="flag-icon flag-icon-us"></span> ENGLISH'
                    value="en-US" ${locale == 'en-US' ? 'selected' : ''}><fmt:message bundle="${common}"
                                                                                      key="language.en"/></option>

            <option data-content='<span class="flag-icon flag-icon-ru"></span> RUSSIAN'
                    value="ru-RU" ${locale == 'ru-RU' ? 'selected' : ''}><fmt:message bundle="${common}"
                                                                                      key="language.ru"/></option>
        </select>
    </form>


    <form action="/app" method="get" class="nav navbar-nav">

        <input type="submit" name="logout" value="<fmt:message bundle="${common}" key="log.out"/>"
               class="btn btn-danger">
        <input type="hidden" name="command" value="logout">
    </form>

</nav>
<body>
