<%-- 
    Document   : HTMParam
    Created on : 26/11/2018, 07:37:47 PM
    Author     : BDADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form name="form" action="<%= request.getRequestURI()%>" method="get">
            <label>Coco</label>
            <input type="checkbox" name="peli" value="COCO" />
            <label>HARRY POTER</label>
            <input type="checkbox" name="peli" value="HARRY POTER" />
            <label>FURY</label>
            <input type="checkbox" name="peli" value="FURY" />
            <label>F F</label>
            <input type="checkbox" name="peli" value="F F" />
            <input type="submit" value="GO" name="submit" />
        </form>
        <h2>SELECCIONÓ:</h2>
        <ul>
        <%
            String[] pelis = request.getParameterValues("peli");
            if (pelis != null)
                for (int i = 0; i < pelis.length; i++) {
        %>

        <li> <%=pelis[i]%></li>
        <%
            }
        %>
        </ul>
    </body>
</html>
