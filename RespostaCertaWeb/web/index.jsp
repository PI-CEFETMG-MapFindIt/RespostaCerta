<%--Author:Vitor --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Redirect</title>
    </head>
    <body>
        <jsp:forward page="/ControllerServlet">
            <jsp:param name="control" value=""/>
        </jsp:forward>
    </body>
</html>
