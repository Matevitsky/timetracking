<%@include file="userHeader.jsp" %>
<body>
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
    <c:forEach items="${activityList}" var="activity">
        <tr>
            <td>${activity.title}</td>
            <td>${activity.description}</td>
            <td>

                <form action="/app" method="get">
                    <input name="duration" input placeholder="Duration" type="text" tabindex="duration" required
                           autofocus>
                    <input type="hidden" name="command" value="user_activity_remove_request">
                    <input type="hidden" name="id" value="${activity.id}"/>
                    <input type="submit" value="Remove" name="remove">
                </form>
            </td>
            <td>

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
