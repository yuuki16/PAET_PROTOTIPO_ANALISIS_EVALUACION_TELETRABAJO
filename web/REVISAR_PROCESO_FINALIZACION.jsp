<%-- 
    Document   : REVISAR_PROCESO_SEGUIMIENTO
    Created on : 01-dic-2016, 21:46:04
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Verificar Proceso de Seguimiento de Teletrabajo</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- Referencias -->
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/utils.js" type="text/javascript"></script>
        <script src="js/REVISAR_PROCESO_SEGUIMIENTO_JS.js" type="text/javascript"></script>  
    </head>
    <body>
        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Verificar Proceso de Seguimiento de Teletrabajo</h3></div>
                <div class="panel-body">
                    <!-- BUSQUEDA -->
                    <div class="col-sm-12">
                        <form role="form" onsubmit="return false;" id="formBusqueda" class="form-horizontal centered">
                            <div class="form-group" id="grouptrCedula">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar procesos por cédula del trabajador:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" id="trCedula" placeholder="Digite la cédula del trabajador">
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaTrCedula">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                    <button type="button" class="btn btn-default centered" data-toggle="modal" id="btLimpiarBusqueda">Limpiar Búsqueda</button>        
                                </div>
                            </div>
                        </form>
                    </div>

                    <!-- Procesos -->
                    <div class="row" id="proceso">
                        <!-- Procesos -->
                        <div class="col-sm-4">
                            <div class="timeline-centered" id ="procesos"></div>
                        </div>
                        <!-- Estados -->
                        <div class="col-sm-4" >
                            <div class="timeline-centered" id="estados">
                            </div>
                        </div>
                    </div>
                </div>

                <center>
                    <div class="alert alert-danger" style="width: 800px; display: none" id="alert">
                        <p id="alertMsg"></p>
                    </div>
                </center>

                <div class="panel-footer"></div>
            </div>
        </div>
    </body>
</html>

