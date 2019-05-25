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
<form action="" method="get">
    <input type="submit" value="Activity Requests"/>
    <input type="hidden" name="command" value="admin_activity_requests">

</form>

<form action="RequestServlet" method="get">

    <input type="submit" name="submit" value="Old Activity" class="button"/>
</form>

<table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th class="th-sm">Title

        </th>
        <th class="th-sm">Content

        </th>
        <th class="th-sm">Duration

        </th>
        <th class="th-sm">Bottom

        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${activityRequest}" var="activityRequest">
        <tr>
            <td>${activityRequest.title}</td>
            <td>${activityRequest.content}</td>
            <td>${activityRequest.duration}</td>
            <td>
                <form action="" method="get">
                        <%--  <input type="hidden" name="id" value="${activity.id}"/>--%>
                    <input type="submit" value="Remove" name="remove">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>

    <tfoot>
    <tr>
        <th>Title
        </th>
        <th>Content
        </th>
        <th>Duration
        </th>
        <th>Bottom
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
