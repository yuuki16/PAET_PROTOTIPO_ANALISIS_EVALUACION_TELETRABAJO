<%-- 
    Document   : HeaderJSP
    Created on : 17-oct-2016, 19:51:36
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- Referencias -->
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/utils.js" type="text/javascript"></script>
        <script src="js/DI_DIA_JS.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div class="page-header">
                <h1>PAET <small>Prototipo funcional para el análisis y evaluación de puestos de teletrabajo</small></h1>
            </div>            
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <!--Columna-->
                        <div class="col-md-4">
                            <div class="dropdown">
                                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Menú Principal
                                    <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                        <li><a href="#">Mantenimiento Personas</a></li>
                                        <li><a href="#">Mantenimiento Teléfonos</a></li>
                                        <li><a href="#">Mantenimiento Direcciones</a></li>
                                        <li class="divider"></li>
                                    <li><a href="Logout">Cerrar Sesión</a></li>
                                </ul>
                            </div>
                        </div>
                        <!--Columna-->
                        <div class="col-md-4" style="text-align: center">
                            <button type="button" class="btn btn-info centered" data-toggle="modal" data-target="#myModalSesion" id="btMostarSesion">Mostrar datos de la sesión</button>
                        </div>
                        <!--Columna-->
                        <div class="col-md-4" style="text-align: right;">
                            <a class="btn btn-warning" href="Logout" role="button">
                                <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                                Cerrar Sesión
                            </a>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
