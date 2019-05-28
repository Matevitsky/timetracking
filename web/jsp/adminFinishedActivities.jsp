<%@include file="adminHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Finished activity</title>
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
            <form action="" method="get">
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

</body>
</html>
