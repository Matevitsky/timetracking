<%@include file="adminHeader.jsp" %>



<div class="container">

    <form id="contact" action="" method="get">
        <input type="hidden" name="command" value="admin_create_new_activity">

        <h3>Create activity</h3>
        <fieldset>
            <input name="title" input placeholder="Title" type="text" tabindex="Title" required autofocus>

        </fieldset>

        <fieldset>
            <input name="description" textarea placeholder="Type activity description." tabindex="Description"
                   required></inputtextarea>

        </fieldset>

        <fieldset>
            <button name="submit" type="submit" id="contact-submit" class="btn btn-secondary btn-lg">Create activity
            </button>
        </fieldset>

    </form>
</div>



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



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="/docs/4.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o"
        crossorigin="anonymous"></script>
</body>
</html>

