
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            double num = Math.random();
            if (num > 0.95) {


        %>
        <p>Andas con suerte!!</p><p>(<%=num%>)</p>
        <%
        } else if (num < 0.20) {
        %>
        <p>Mejor sigue intentando!!</p><p>(<%=num%>)</p>
        <%
        } else
        %>
        <p>Un poco de lo común</p>
        <a href="<%= request.getRequestURI() %>">Try again!</a>
    </body>
</html>
