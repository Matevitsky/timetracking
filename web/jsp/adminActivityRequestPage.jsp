<%@include file="adminHeader.jsp" %>

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
    <link href="../css/modules/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="../css/modules/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="../css/style.css" rel="stylesheet">

    <!-- MDBootstrap Datatables  -->
    <link href="../css/addons/datatables.min.css" rel="stylesheet">


</head>

<body>
<form action="" method="get">
    <input type="submit" name="submit" value="Activity Requests" class="button"/>
    <input type="hidden" name="command" value="admin_new_activity">


</form>


<form action="" method="get">

    <input type="submit" name="submit" value="Old Activity" class="button"/>
</form>

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

                    <form action="/" method="get">

                        Please select an element:


                        <select id="unAssignedActivityList" name="selectedRecord">

                            <c:forEach var="unAssignedActivityList" items="${unAssignedActivityList}">

                                <option value="${unAssignedActivityList}">${unAssignedActivityList.title}</option>

                            </c:forEach>

                        </select>
                        <input type="hidden" name="userId" value= ${user.id}>
                            <%--  <input type="submit" value="Submit" align="middle">--%>


                        <form action="/" method="get">
                            <input type="submit" value="Assign task">
                            <input type="hidden" name="command" value="assign_activity_command">

                        </form>


                    </form>

                        <%-- <div class="dropdown open">
                             <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                                     data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                 Available Activity
                             </button>


                                 <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">

                                     <c:forEach items="${unAssignedActivityList}" var="unAssignedActivityList">
                                         <a class="dropdown-item">${unAssignedActivityList.title}</a>
                                     </c:forEach>

                                 </div>
                         </div>--%>


                </td>


            </form>

        </tr>
    </c:forEach>
    </tbody>

    <tfoot>
    <tr>
        <th class="th-sm">User Name

        </th>
        <th>
            Activity Requests

        </th>
    </tr>
    </tfoot>
</table>

<script>
    $(".dropdown-menu li a").click(function () {
        var selText = $(this).text();
        $(this).parents('.btn-group').find('.dropdown-toggle').html(selText + ' <span class="caret"></span>');
    });
</script>
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
