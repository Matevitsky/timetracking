<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">


    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>


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
    <!-- Custom styles for this template -->
    <link href="navbar-top.css" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value="../css/createActivity.css"/>">

</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">

    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <form action="" method="get">
                <input type="submit" value="Activity Requests" class="btn btn-secondary btn-lg"/>
                <input type="hidden" name="command" value="admin_activity_requests">

            </form>

            <form action="" method="get">

                <input type="submit" name="submit" value="Finished Activity" class="btn btn-secondary btn-lg"/>
                <input type="hidden" name="command" value="admin_get_finished_activities">
            </form>

            <form action="" method="get">

                <input type="submit" name="submit" value="Main Page" class="btn btn-secondary btn-lg"/>
                <input type="hidden" name="command" value="admin_main_page">
            </form>

            <form action="" method="get" class="nav navbar-nav navbar-right">

                <input type="submit" name="submit" value="Logout" class="btn btn-danger"/>
                <input type="hidden" name="command" value="logout">
            </form>

        </ul>
    </div>

</nav>

<body>
