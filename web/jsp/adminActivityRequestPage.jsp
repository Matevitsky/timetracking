<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Material Design Bootstrap</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="../css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="../css/style.css" rel="stylesheet">

    <!-- MDBootstrap Datatables  -->
    <link href="../css/addons/datatables.min.css" rel="stylesheet">


</head>

<body>
<form action="/app?command=admin_activity_request" method="get">
    <input type="hidden" name="command" value="admin_new_activity">

    <input type="submit" name="submit" value="Activity Requests" class="button"/>
</form>

<form action="RequestServlet" method="get">

    <input type="submit" name="submit" value="Old Activity" class="button"/>
</form>
<table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th class="th-sm">User Name

        </th>
        <th>
            Available Activity

        <th class="th-sm">Bottom

        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userForActivityRequestList}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>
                <div class="dropdown open">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Available Activity
                    </button>

                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">

                        <c:forEach items="${unAssignedActivityList}" var="unAssignedActivityList">
                            <a class="dropdown-item">${unAssignedActivityList.title}</a>
                        </c:forEach>

                            <%-- <a class="dropdown-item" href="#">Action</a>
                             <a class="dropdown-item" href="#">Another action</a>
                             <a class="dropdown-item" href="#">Something else here</a>--%>
                    </div>
                </div>
            </td>
            <td>
                <form action="/app" method="get">
                        <%--  <input type="hidden" name="id" value="${activity.id}"/>--%>
                    <input type="submit" value="Assign task" name="assign">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>

    <tfoot>
    <tr>
        <th class="th-sm">User Name

        </th>
        <th>
            Activity Requests

        <th class="th-sm">Bottom

        </th>
    </tr>
    </tfoot>
</table>
<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="js/jquery-3.4.0.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="js/mdb.min.js"></script>

<!-- MDBootstrap Datatables  -->
<script type="text/javascript" src="js/addons/datatables.min.js"></script>

<script>$(document).ready(function () {
    $('#dtBasicExample').DataTable();
    $('.dataTables_length').addClass('bs-select');
});</script>
</body>

</html>
