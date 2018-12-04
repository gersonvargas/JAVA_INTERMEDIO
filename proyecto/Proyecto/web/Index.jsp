
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
                <div id="carouselExampleIndicators" class="carousel slide mt-3" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img class="d-block w-100" src="images/banner-jobs.jpg" alt="First slide">
                            <div class="carousel-caption d-none d-md-block ">
                                <h5 class="text-danger">Chatear con los compas</h5>
                                <p class="text-info">Diversión...</p>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-100" src="images/banner-jobs4.jpg" alt="Second slide">
                             <div class="carousel-caption d-none d-md-block ">
                                <h5 class="text-danger">Mucho más</h5>
                                <p class="text-info">Hecha a volar la imaginación...</p>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-100" src="images/banner-jobs5.jpg" alt="Third slide">
                             <div class="carousel-caption d-none d-md-block ">
                                <h5 class="text-danger">Mensajes personales</h5>
                                <p class="text-info">Más...</p>
                            </div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>

                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </main>

        </div>


        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"  crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"  crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </body>
</html>

