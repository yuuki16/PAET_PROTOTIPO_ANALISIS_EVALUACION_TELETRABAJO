<%-- 
    Document   : SOLICITUD_FINALIZACION
    Created on : 30-oct-2016, 20:03:38
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Formulario Solicitud de Finalización de Teletrabajo</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- Referencias -->
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/utils.js" type="text/javascript"></script>
        <script src="js/SOLICITUD_FINALIZACION_JS.js" type="text/javascript"></script>  
    </head>
    <body>
        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Formulario Solicitud de Finalización de Teletrabajo</h3></div>
                <div class="panel-body">
                    <div class="col-sm-12">
                        <form role="form" onsubmit="return false;" id="formSolicitudFinalizacion">
                            <div class="form-group" id="groupTeletrabajador">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar datos del Teletrabajador:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" id="trTeletrabajador" placeholder="Digite la cédula del teletrabajador a verificar">
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaTeletrabajador">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                    <button type="button" class="btn btn-default centered" data-toggle="modal" id="btLimpiarBusqueda">Limpiar Búsqueda</button>
                                </div>
                            </div>
                            
                            <div class="form-group" id="groupUsuario" style="display: none">
                                <input type="text" class="form-control" id="usuario" readonly="true">
                            </div>

                            <div class="form-group" id="groupNombre">
                                <label for="nombre">Nombre Candidato/a:</label>
                                <input type="text" class="form-control" id="nombre" readonly="true">
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary" id="guardar">Guardar</button>
                            </div>

                            <div class="form-group height25" >
                                <div class="alert alert-success hiddenDiv" id="mensajeResult">
                                    <strong id="mensajeResultNeg">Info!</strong> 
                                    <span id="mensajeResultText">This alert box could indicate a neutral informative change or action.</span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                
                <center>
                    <div class="alert alert-danger" style="width: 800px; display: none" id="alert">
                        <p id="alertMsg"></p>
                    </div>
                </center>
            </div>
        </div>

        <!-- Mensajes -->
        <div class="modal fade" id="modalMensajes" role="dialog">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="modalMensajesTitle">Modal Header</h4>
                    </div>
                    <div class="modal-body" id="modalMensajesMessage">
                        <p>This is a small modal.</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
