<%-- 
    Document   : index
    Created on : 22-oct-2016, 11:21:34
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>PAET - Prototipo funcional para el análisis y evaluación de puestos de teletrabajo</title>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- Referencias -->
        <link href="css/css.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>

        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <!-- Encabezado -->
            <header class="jumbotron hero-spacer">
                <h1>PAET</h1>
                <p>Prototipo funcional para el análisis y evaluacion de puestos de teletrabajo.</p>
                </p>
            </header>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <h3>Beneficios del Teletrabajo</h3>
                </div>
            </div>
            <!-- Beneficios del Teletrabajo -->
            <div class="row text-center">

                <div class="col-md-3 col-sm-6 hero-feature">
                    <div class="thumbnail">
                        <img src="Images/Money.png" alt="Ahorro" style="width: 150px; height: 150px">
                        <div class="caption">
                            <h3>Ahorro Monetario</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-6 hero-feature">
                    <div class="thumbnail">
                        <img src="Images/Paperwork.png" alt="Papeleo" style="width: 150px; height: 150px">
                        <div class="caption">
                            <h3>Feature Label</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-6 hero-feature">
                    <div class="thumbnail">
                        <img src="Images/People.png" alt="Personal" style="width: 150px; height: 150px">
                        <div class="caption">
                            <h3>Mayor Capacidad Personal</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-6 hero-feature">
                    <div class="thumbnail">
                        <img src="Images/Time.png" alt="Tiempo" style="width: 150px; height: 150px">
                        <div class="caption">
                            <h3>Feature Label</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        </div>
                    </div>
                </div>

            </div>
            <hr>
            <!-- Firma -->
            <footer>
                <div class="row">
                    <div class="col-lg-12">
                        <p>Proyecto Final de Graduación - Michelle Monge Arguedas - 2016</p>
                    </div>
                </div>
            </footer>

        </div>
    </body>
</html>
