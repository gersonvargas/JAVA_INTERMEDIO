<%-- 
    Document   : LoginSuccess
    Created on : 03/12/2018, 05:52:45 PM
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
        <%
            String user = null;
            if (session.getAttribute("user") == null) {
                response.sendRedirect("index.html");
            } else {
                user = (String) session.getAttribute("user");
            }
            String userName = null;
            String sessionId = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        userName = cookie.getValue();
                    }
                    if (cookie.getName().equals("JSESSIONID")) {
                        sessionId = cookie.getValue();
                    }
                }
            }
            String[] userConnected = (String[]) session.getAttribute("USERS");
            if (userConnected != null) {

                for (int i = 0; i < userConnected.length; i++) {


        %>
        <h4><%=userConnected[i]%></h4>
        <%}
            }%>
        <h1>hOLA Usuario <%=userName%>, login exitoso. Su ID de session es:  <%=sessionId%></h1>

        </br>
        Usuario <%=userName%>
        <a href="CheckOutPage.jsp"> Checkout</a>
        <form action="Logout" method="POST">
            <input type="submit" value="Logout" name="submit" />
        </form>
    </body>
</html>
