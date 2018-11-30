
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean  id="sesion" class="beans.BeanSesion" scope="session"> </jsp:useBean>

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

    </head>
    <body>


        <jsp:include page="Templates/Header.jsp"></jsp:include>
            <div>
                <main>
                    <div class="container">
                        <div class="row"> 
                            <h5><i>Bienvenido:</i>
                                <b>
                                    <c:if test="${not empty email}">
                                    <h1>${email}</h1>
                                </c:if>
                            </b>
                        </h5>
                    </div>
                    <div class="row"> 
                        <div class="col-7"> 
                            <div class="messaging">
                                <div class="inbox_msg">
                                    <div class="">
                                        <div class="headind_srch">
                                            <div class="recent_heading">
                                                <h4>Chat</h4>

                                            </div>                                  
                                        </div>
                                        <div class="inbox_chat">                                           
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

                                    </div>
                                </div> 
                            </div> 
                        </div> 
                        <div class="col-5"> 

                            <div class="row">
                                <h3><i>Usuarios conectados:</i></h3>
                            </div>
                            <div id="reftabla" class="row">
                                ${lib:getUsuariosConectadosHTML(1)}
                            </div>

                            <div class="row">
                                <h3><i>Usuarios desconectados:</i></h3>
                            </div>
                            <div id="reftabla" class="row">
                                ${lib:getUsuariosConectadosHTML(0)}
                            </div>
                            <button type="button" class="btn btn-sm btn-primary">
                                Total Sesiones <span class="badge badge-danger">6</span>
                                <span class="sr-only">unread messages</span>
                            </button>
                        </div>
                    </div>
                </div>

            </main>

        </div>

        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"  crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"  crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </body>
</html>

