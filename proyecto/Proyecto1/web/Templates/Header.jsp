

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <header>
        <div class="navbar-fixed-top " >
            <nav class="navbar navbar-expand-sm navbar-light  font-weight-bold margenes-header bg-dark">
                <a class="navbar-brand" href="#">
                    <img src="http://www.sidekick.cl/wp-content/uploads/2015/07/sitio-web.png" width="50" height="50" alt="Logo" />
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarsExample04">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="Index.jsp">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="Chat.jsp"><i class="fa fa-envelope mr-2"></i>Chat</a>
                        </li>
                         <%

                            if (request.getSession(true).getAttribute("usuario") == null) {

                        %>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="Login.jsp"><i class="fa fa-user mr-2"></i>Login</a>
                        </li>
                        <%} 
                        %>
                       
                    </ul>
                    <form class="form-inline my-2 my-md-0">

                        <%

                            if (request.getSession(true).getAttribute("usuario") != null) {

                        %>
                        <a class="navbar-brand text-white" href="Logout">
                            <img src="images/exit.png"  height="30" alt="Logo" />Logout
                        </a>
                        <%} else {
                        %>
                        <a class="navbar-brand text-white" href="Registrarse.jsp">
                            <img src="images/add-user.png" width="50" height="50" alt="Logo" />Registrarse
                        </a>
                        <%
                            }
                        %>
                    </form>
                </div>
            </nav>
        </div>
    </header>

</html>