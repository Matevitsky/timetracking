<%@include file="adminHeader.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">

    <title>Activity Requests</title>


    <!-- MDBootstrap Datatables  -->
    <link href="../../css/addons/datatables.min.css" rel="stylesheet">


</head>

<body>

<table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th class="th-sm">User Name

        </th>
        <th>
            Available Activity
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userForActivityRequestList}" var="user">
        <tr>

            <form>
                <td>${user.name}</td>

                <td>

                    <form action="/app" method="get">

                        Please select an element:


                        <select id="unAssignedActivityList" name="selectedRecord">

                            <c:forEach var="unAssignedActivityList" items="${unAssignedActivityList}">

                                <option value="${unAssignedActivityList}">${unAssignedActivityList.title}</option>

                            </c:forEach>

                        </select>
                        <input type="hidden" name="userId" value= ${user.id}>
                            <%--  <input type="submit" value="Submit" align="middle">--%>


                        <form action="/app" method="get">
                            <input type="submit" value="Assign task">
                            <input type="hidden" name="command" value="assign_activity_command">

                        </form>


                    </form>

                </td>


            </form>

        </tr>
    </c:forEach>
    </tbody>

    <tfoot>
    <tr>
        <th class="th-sm">User Name</th>

        <th>Activity Requests</th>
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


</body>

</html>
