<%@include file="adminHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Finished activity</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Material Design Bootstrap</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="../../css/modules/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->


    <!-- MDBootstrap Datatables  -->
    <link href="../../css/addons/datatables.min.css" rel="stylesheet">
</head>
<body>

<table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th class="th-sm">Title</th>
        <th class="th-sm">Content</th>
        <th class="th-sm">Duration</th>

    </tr>

    </thead>
    <tbody>
    <c:forEach items="${activityList}" var="activity">

        <tr>
            <form action="/app" method="get">
                <td>${activity.title}</td>
                <td>${activity.description}</td>
                <td>${activity.duration}

                    <input type="hidden" name="command" value="admin_remove_activity">
                    <input type="hidden" name="id" value="${activity.id}"/>
                    <input type="submit" align="center" value="Remove" name="remove">

                </td>
            </form>


        </tr>
    </c:forEach>
    </tbody>

    <tfoot>
    <tr>
        <th class="th-sm">Title</th>
        <th class="th-sm">Content</th>
        <th class="th-sm">Duration</th>
    </tr>
    </tfoot>
</table>

<script>
    $(".dropdown-menu li a").click(function () {
        var selText = $(this).text();
        $(this).parents('.btn-group').find('.dropdown-toggle').html(selText + ' <span class="caret"></span>');
    });
</script>

<script>$(document).ready(function () {
    $('#dtBasicExample').DataTable();
    $('.dataTables_length').addClass('bs-select');
});</script>


<!-- MDBootstrap Datatables  -->
<script type="text/javascript" src="../js/addons/datatables.min.js"></script>


</body>
</html>
