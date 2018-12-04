<%-- 
    Document   : CheckOutPage
    Created on : 03/12/2018, 05:53:30 PM
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
        <h1>CheckOutPage!</h1>
        <%
        if(session.getAttribute("user")!=null){
            response.sendRedirect("index.html");
        }
        String userName=null;
        String sessionId=null;
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("user")){
                        userName=cookie.getValue();
                    }
                    if(cookie.getName().equals("JSESSIONID")){
                        sessionId=cookie.getValue();
                    }
                }
        }
        %>
        <h1>hOLA Usuario <%=userName%>, Salir de la sesion?  <%=sessionId%></h1>
        </br>
         Usuario <%=userName%>
         <a href="CheckOutPage.jsp"> Checkout</a>
          <form action="Logout" method="POST">
                <input type="submit" value="Logout" name="submit" />
            </form>
    </body>
</html>
