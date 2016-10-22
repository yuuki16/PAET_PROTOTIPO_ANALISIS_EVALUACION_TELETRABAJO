<%-- 
    Document   : DI_DIA_JSP
    Created on : 17-oct-2016, 19:40:24
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mantenimiento de Días</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
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
        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Mantenimiento de Días</h3></div>
                <div class="panel-body">
                    <center>
                        <button type="button" class="btn btn-primary centered" data-toggle="modal" data-target="#formularioAdministrar" id="btMostarForm">Insertar Día</button>
                    </center><br>
                    <!-- BUSQUEDA -->
                    <div class="col-sm-12">
                        <form role="form" onsubmit="return false;" id="formPersonas" class="form-horizontal centered">
                            <div class="form-group" id="groupdiCodigo">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por código:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="diCodigo" placeholder="Digite el código del día">
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaDiCodigo">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                            <div class="form-group" id="groupdiDescripcion">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por nombre:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="diDescripcion" placeholder="Digite el nombre del día">
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaDiDescripcion">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- TABLA -->

                    <table class="table table-hover table-condensed" id="tablaDias"></table>

                </div>
                <div class="panel-footer">Nota: Acciones validas dependeran del rol del usuario</div>
            </div>
        </div>
        
        <!-- FORMULARIO -->
        <div class="modal fade" id="formularioAdministrar" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" id="myModalTitle"> Administrar Dias</h4>
                    </div>
                    <div class="modal-body" id="myModalMessage">
                        <form role="form" onsubmit="return false;" id="formDias">
                            <div class="form-group" id="groupCodigo">
                                <label for="codigo">Código:</label>
                                <input type="text" class="form-control" id="codigo" autofocus="true" placeholder="Código">
                            </div>

                            <div class="form-group" id="groupDescripcion">
                                <label for="descripcion">Descripción:</label>
                                <input type="text" class="form-control" id="descripcion" placeholder="Descripción" >
                            </div>
                            
                            <div class="form-group">
                                <input type="hidden" value="agregarDia" id="diasAction"/>
                                
                                    <button type="submit" class="btn btn-primary" id="guardar">Guardar</button>
                              
                                <button type="reset" class="btn btn-danger" id="cancelar">Cancelar</button>
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
            </div>
        </div>
        
        <!-- Mensajes -->
        <div class="modal fade" id="modalMensajes" role="dialog">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
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
