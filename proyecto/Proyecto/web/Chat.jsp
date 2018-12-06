
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="lib" uri="/WEB-INF/tlds/lib.tld"%>


<!DOCTYPE html>

<html>
    <head>
        <title>CHAT</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <link href="css/mensajes.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"  crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="js/LoadJson.js" type="text/javascript"></script>
        <script src="js/scripts.js" type="text/javascript"></script>
        <%

            // Verifica si la sesión ha expirado, usando un tiempo menor
            // al especificado en la aplicación.
            HttpSession sesion = request.getSession(true);
            long transcurrido = System.currentTimeMillis() - sesion.getLastAccessedTime();
            if (transcurrido > (1000 * 60 * 5)) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
            if (request.getSession(true).getAttribute("usuario") == null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        %>
    </head>
    <body>


        <jsp:include page="Templates/Header.jsp"></jsp:include>
            <div>
                <main>
                    <div class="container">
                        <div class="row"> 
                            <h5><i>Bienvenido:</i>

                                <c:if test="${not empty usuario}">
                                ${usuario}
                            </c:if>

                        </h5>
                    </div>
                    <div class="row"> 
                        <div class="col-4"> 
                            <div class="messaging">
                                <div class="inbox_msg">
                                    <div class="headind_srch">
                                        <div class="recent_heading">
                                            <h4>Chat</h4>
                                        </div>                                  
                                    </div>
                                    <div class="inbox_chat " id="refComment">                                           
                                        ${lib:getComentariosHTML(1)}
                                    </div>
                                    <div class="type_msg">
                                        <form action="Comentario" method="POST">
                                            <div class="input_msg_write">
                                                <input type="number" value="1" name="tipo" hidden></inpu>
                                                <textarea class="form-control"  name="descripcion" placeholder="Comentar" required></textarea>
                                                <button class="msg_send_btn" type="submit"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
                                            </div>
                                        </form>
                                    </div>

                                     <div class="headind_srch">
                                        <div class="recent_heading">
                                            <h4>Personal</h4>
                                        </div>                                  
                                    </div>                                 
                                    <div class="inbox_chat" id="refComment">                                           
                                        <%
                                            String usuario_para = request.getParameter("usuario_para");
                                            String usuario_de = (String) request.getSession(true).getAttribute("email");
                                            if (usuario_para != null) {
                                        %>
                                        <%=Model.Gestor.getComentariosPersonalesHTML(usuario_de, usuario_para)%>
                                        <%}%>
                                    </div>
                                </div> 
                            </div> 

                        </div> 
                        <div class="col-8"> 

                            <div class="row ">
                                <h3><i>Usuarios conectados:</i></h3>
                            </div>
                            <div id="refUserConneted" class="row">
                                ${lib:getUsuariosConectadosHTML(1)}
                            </div>

                            <div class="row ">
                                <h3><i>Usuarios desconectados:</i></h3>
                            </div>
                            <div id="refUserDisconneted" class="row">
                                ${lib:getUsuariosConectadosHTML(0)}
                            </div>


                            <button type="button" class="btn btn-sm btn-primary">
                                Total Usuarios <span class="badge badge-danger"><%=Model.Modelo.getNumeroUsuarios()%></span>
                                <span class="sr-only">unread messages</span>
                            </button>
                        </div>
                    </div>
                </div>

            </main>

        </div>

        <script>
            inicializar();
            obtenerUsuariosConectados();
        </script>
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"  crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"  crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </body>
</html>

