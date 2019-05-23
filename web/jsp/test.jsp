<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="grid.css" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value="/jsp/css/userPage.css"/>">
    <link rel="stylesheet" href="<c:url value="/jsp/test.jsp"/>">

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


<table class=" tab_esp table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>
            <input type="text" class="form-control" id="title" placeholder="Title">
        </th>
        <th>
            <input type="text" class="form-control" id="content" placeholder="Title">
        </th>

        <th class='center'>
            <labeL>Duration</label>
        </th>
        <th>
            <select class="form-control" id="esp_para">
                <option>PARAMETRIZADO</option>
                <option value='1'>Finish</option>
                <%-- <option value='2'>NAO</option>--%>
            </select>
        </th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class='esp_nm'>название</td>
        <td class='esp_nm'>контент</td>

        <td><input type='text' class='form-control'></input></td>
        <td class='esp_para center'>
            <button class='btn btn-danger'>NAO</button>
        </td>
    </tr>
    <tr>
        <td class='esp_nm'>название</td>
        <td class='esp_nm'>контент</td>
        <td><input type='text' class='form-control'></input></td>
        <td class='esp_para center'>
            <button class='btn btn-success'>SIM</button>
        </td>
    </tr>
    <tr>
        <td class='esp_nm'>название</td>
        <td class='esp_nm'>контент</td>
        <td><input type='text' class='form-control'></input></td>

        <td class='esp_para center'>
            <button class='btn btn-danger'>NAO</button>
        </td>
    </tr>
    <tr>
        <td class='esp_nm'>название</td>
        <td class='esp_nm'>контент</td>
        <td><input type='text' class='form-control'></input></td>

        <td class='esp_para center'>
            <button class='btn btn-success'>SIM</button>
        </td>
    </tr>
    <tr>
        <td class='esp_nm'>название</td>
        <td class='esp_nm'>контент</td>
        <td><input type='text' class='form-control'></input></td>

        <td class='esp_para center'>
            <button class='btn btn-success'>SIM</button>
        </td>
    </tr>
    <tr>
        <td class='esp_nm'>название</td>
        <td class='esp_nm'>контент</td>


        <td class='esp_para center'>
            <button class='btn btn-success'>SIM</button>
        </td>
    </tr>
    </tbody>
</table>
