<%@include file="userHeader.jsp" %>
<body>
<table id="userTable" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">

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
            <td>${activity.title}</td>
            <td>${activity.description}</td>
            <td>

                <form action="/app" method="get">
                    <input type="time" id="duration" name="duration" input placeholder=
                        <fmt:message bundle="${common}" key="time"/>
                            min="59:00" max="23:00" required autofocus/>


                    <input type="hidden" name="command" value="user_activity_remove_request">
                    <input type="hidden" name="id" value="${activity.id}"/>
                    <input type="submit" value="<fmt:message bundle="${common}" key="delete.activity"/>" name="remove">
                </form>

            </td>
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

<!-- SCRIPTS -->

<!-- Bootstrap tooltips -->
<script type="text/javascript" src="/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="/js/mdb.min.js"></script>

<script>
    $(".dropdown-menu li a").click(function () {
        var selText = $(this).text();
        $(this).parents('.btn-group').find('.dropdown-toggle').html(selText + ' <span class="caret"></span>');
    });
</script>

<script>$(document).ready(function () {
    $('#userTable').DataTable();
    $('.dataTables_length').addClass('bs-select');
});</script>


<!-- MDBootstrap Datatables -->
<script type="text/javascript" src="/js/addons/datatables.min.js"></script>


</body>

</html>
