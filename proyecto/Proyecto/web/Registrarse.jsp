
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Inicio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"  crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>

        <jsp:useBean  id="sesion" class="beans.BeanSesion" scope="session"></jsp:useBean>
        <jsp:include page="Templates/Header.jsp"></jsp:include>
        <div>
            <main>
                <div class="container">
                    <form class="form-horizontal" role="form" action="Registrar"  enctype="multipart/form-data" method="POST" >
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">
                                <h2>Crear nueva cuenta</h2>
                                <hr>
                            </div>
                        </div>
                          <div class="row">
                            <div class="col-md-3 field-label-responsive">
                                <label for="name">ID Usuario</label>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-user"></i></div>
                                        <input type="text" name="id" class="form-control" id="name"
                                               placeholder="ID" required autofocus>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-control-feedback">
                                    <span class="text-danger align-middle">
                                        <!-- Put name validation error messages here -->
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3 field-label-responsive">
                                <label for="name">Nombre Usuario</label>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-user"></i></div>
                                        <input type="text" name="name" class="form-control" id="name"
                                               placeholder="Nombre" required autofocus>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-control-feedback">
                                    <span class="text-danger align-middle">
                                        <!-- Put name validation error messages here -->
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3 field-label-responsive">
                                <label for="email">Correo Electrónico</label>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-at"></i></div>
                                        <input type="text" name="email" class="form-control" id="email"
                                               placeholder="you@example.com" required autofocus>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-control-feedback">
                                    <span class="text-danger align-middle">
                                        <!-- Put e-mail validation error messages here -->
                                    </span>
                                </div>
                            </div>
                        </div>
                        
                        
                         <div class="row">
                            <div class="col-md-3 field-label-responsive">
                                <label for="email">Imagen <em style="color: green"> .jpg,.gif, .png,.jpeg</em></label>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-at"></i></div>
                                         <input type="file" accept="image/*" id="route" name="route" placeholder="Photography route"autocomplete="on"  required />
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-control-feedback">
                                    <span class="text-danger align-middle">
                                        <!-- Put e-mail validation error messages here -->
                                    </span>
                                </div>
                            </div>
                        </div>
                                                              
                                        
                                        
                        
                        <div class="row">
                            <div class="col-md-3 field-label-responsive">
                                <label for="password">Contraseña</label>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group has-danger">
                                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-key"></i></div>
                                        <input type="password" name="password" class="form-control" id="password"
                                               placeholder="Password" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-control-feedback">
                                    <span class="text-danger align-middle">
                                      
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3 field-label-responsive">
                                <label for="password">Confirmar contraseña</label>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                        <div class="input-group-addon" style="width: 2.6rem">
                                            <i class="fa fa-repeat"></i>
                                        </div>
                                        <input type="password" name="password-confirmation" class="form-control"
                                               id="password-confirm" placeholder="Password" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5 ml-3"></div>
                            <div class="col-md-1 ml-3">
                                <a  href="Index.jsp" class="btn btn-danger"><i class="fa fa-close"></i> Cancelar</a>
                            </div>
                             <div class="col-md-1 mr-3">
                                <a  href="Login.jsp" class="btn btn-info"><i class="fa fa-close"></i> Iniciar sesión</a>
                            </div>
                                                      
                            <div class="col-md-1 ml-3">
                                <button type="submit" class="btn btn-success"><i class="fa fa-user-plus"></i> Crear cuenta</button>
                            </div>
                        </div>
                    </form>
                </div>
            </main>

        </div>


        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"  crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"  crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </body>
</html>

