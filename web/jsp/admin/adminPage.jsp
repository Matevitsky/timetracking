<%@include file="adminHeader.jsp" %>
<head>
    <link href="../../css/addons/datatables.min.css" rel="stylesheet">
    <title>Admin Main Page</title>
</head>


<div class="container">

    <form id="contact" action="/app" method="get">
        <input type="hidden" name="command" value="admin_create_new_activity">

        <h3><fmt:message bundle="${common}" key="create.activity"/></h3>
        <fieldset>
            <input name="title" input placeholder=
            <fmt:message bundle="${common}" key="title"/> type="text" tabindex="Title" required autofocus>

        </fieldset>

        <fieldset>
            <input name="description" textarea
                   placeholder=<fmt:message bundle="${common}" key="type.activity.description"/>" tabindex=" Description"
                   required></inputtextarea>

        </fieldset>

        <fieldset>
            <button name="submit" type="submit" id="contact-submit" class="btn btn-secondary btn-lg">
                <fmt:message bundle="${common}" key="create.activity"/>
            </button>
        </fieldset>

    </form>
</div>


<table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th class="th-sm"><fmt:message bundle="${common}" key="title"/></th>
        <th class="th-sm"><fmt:message bundle="${common}" key="description"/></th>
        <th class="th-sm"><fmt:message bundle="${common}" key="time"/></th>

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
        <th class="th-sm"><fmt:message bundle="${common}" key="title"/></th>
        <th class="th-sm"><fmt:message bundle="${common}" key="description"/></th>
        <th class="th-sm"><fmt:message bundle="${common}" key="time"/></th>
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


<!-- MDBootstrap Datatables -->
<script type="text/javascript" src="js/addons/datatables.min.js"></script>


</body>
</html>

