<%-- 
    Document   : Login
    Created on : 27/11/2018, 08:27:45 PM
    Author     : BDADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>LOG IN</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <link href="css/mensajes.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"  crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    </head>
    <body>
        <jsp:useBean  id="sesion" class="beans.BeanSesion" scope="session"></jsp:useBean>
        <jsp:include page="Templates/Header.jsp"></jsp:include>
        <div>

            <h2 class="text-center">Login Form</h2>
            <div class="row justify-content-center">
                <div class="col-12 col-md-8 col-lg-6 pb-5">
                    <!--Form with header-->

                    <form action="Login" method="POST">
                        <div class="card border-primary rounded-0">
                            <div class="card-header p-0">
                                <div class="bg-info text-white text-center py-2">
                                    <h3><i class="fa fa-user"></i> CUENTA</h3>
                                    <p class="m-0">Ingresar</p>
                                </div>
                            </div>
                            <div class="card-body p-3">

                                <!--Body-->

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-envelope text-info"></i></div>
                                        </div>
                                        <input type="email" class="form-control" id="nombre" name="email" placeholder="ejemplo@gmail.com" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fa fa-key"></i></div>
                                        </div>
                                        <input type="password" class="form-control" id="nombre" name="password" placeholder="****" required>
                                    </div>
                                </div>


                                <div class="text-center">
                                    <input type="submit" value="Login" class="btn btn-info btn-block rounded-0 py-2">
                                </div>
                            </div>

                        </div>
                    </form>
                    <!--Form with header-->


                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"  crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"  crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </body>
</html>
