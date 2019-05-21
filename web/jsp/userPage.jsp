<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="grid.css" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value="/jsp/css/Myscss.css"/>">

    <%-- <link href="../css/Mycss.css" rel="stylesheet" type="text/css">--%>

</head>
<body>

<table border="1px">
    <tr>
        <th>Content</th>
        <th>Duration</th>
    </tr>

    <c:forEach var="activity" items="${activityList}">
        <tr>
            <td><c:out value="${activity.content}"/></td>
            <td><c:out value="${activity.duration}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
