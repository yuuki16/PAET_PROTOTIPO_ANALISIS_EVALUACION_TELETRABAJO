<%-- 
    Document   : REPORTE_TELETRABAJADORES_JSP
    Created on : 16-nov-2016, 23:27:15
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Reporte de Teletrabajadores</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- Referencias -->
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/utils.js" type="text/javascript"></script>
        <script src="js/REPORTE_TELETRABAJADORES_JS.js" type="text/javascript"></script>
    </head>
    <body>
        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Reporte de Teletrabajadores</h3></div>
                <div class="panel-body">
                    <form role="form" onsubmit="return false;" id="formBusqueda" class="form-horizontal centered">
                        <div class="form-group">
                            <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                <p><b>Reporte de Teletrabajadores por:</b></p>
                            </div>
                            <div class="col-sm-4">
                                <select class="form-control" id="reporte">
                                    <option value="" selected="selected"></option>
                                    <option value="reporteTeletrabajadoresArea">Área</option>
                                    <option value="reporteTeletrabajadoresDireccion">Dirección</option>
                                    <option value="reporteTeletrabajadoresDivision">División</option>
                                    <option value="reporteTeletrabajadoresGerencia">Gerencia</option>
                                    <option value="reporteTeletrabajadoresModalidad">Modalidad</option>
                                    <option value="reporteTeletrabajadoresSexo">Puesto</option>
                                    <option value="reporteTeletrabajadoresSexo">Sexo</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                <p><b>Filtro:</b></p>
                            </div>
                            <div class="col-sm-4">
                                <select class="form-control" id="filtro">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>
                        </div>
                        <center>
                            <button type="button" class="btn btn-success centered" data-toggle="modal" id="generarReporte">
                                Generar Reporte <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </center>
                        <div class="form-group height25" >
                                <div class="alert alert-success hiddenDiv" id="mesajeResult">
                                    <strong id="mesajeResultNeg">Info!</strong> 
                                    <span id="mesajeResultText">This alert box could indicate a neutral informative change or action.</span>
                                </div>
                            </div>
                    </form>
                </div>
                <div class="panel-footer">Nota: Acciones validas dependeran del rol del usuario</div>
            </div>
        </div>
    </body>
</html>
