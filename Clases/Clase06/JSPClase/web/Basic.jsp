

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World! Página BASIC</h1>
        <%=Math.sqrt(2)%>
         <c:if test="${not empty msm}">
            <h1>${msm}</h1>
        </c:if>
       
    </body>
</html>
