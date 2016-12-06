<%-- 
    Document   : PT_PUESTO_JSP
    Created on : 26-oct-2016, 20:29:01
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mantenimiento de Puestos</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- Referencias -->
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/utils.js" type="text/javascript"></script>
        <script src="js/PT_PUESTO_JS.js" type="text/javascript"></script>  
    </head>
    <body>
        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Mantenimiento de Puestos</h3></div>
                <div class="panel-body">
                    <center>
                        <button type="button" class="btn btn-success centered" data-toggle="modal" data-target="#formularioAdministrar" id="btMostrarForm">Insertar Puesto</button>
                        <button type="button" class="btn btn-default centered" data-toggle="modal" id="btLimpiarBusqueda">Limpiar Búsqueda</button>
                    </center><br>
                    <!-- BUSQUEDA -->
                    <div class="col-sm-12">
                        <form role="form" onsubmit="return false;" id="formBusqueda" class="form-horizontal centered">
                            <div class="form-group" id="groupptCodigo">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por código:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="ptCodigo" placeholder="Digite el código del puesto" maxlength="3">
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaPtCodigo">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                            <div class="form-group" id="groupptDescripcion">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por descripción:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="ptDescripcion" placeholder="Digite el nombre del puesto" maxlength="50">
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaPtDescripcion">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                            
                            <div class="form-group" id="groupgrGerencia">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por gerencia:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <select class="form-control" id="grGerencia">
                                        <option value="" selected="selected"></option>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaGrGerencia">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                            
                            <div class="form-group" id="groupdvDivision">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por división:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <select class="form-control" id="dvDivision">
                                        <option value="" selected="selected"></option>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaDvDivision">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                            
                            <div class="form-group" id="groupdrDireccion">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por dirección:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <select class="form-control" id="drDireccion">
                                        <option value="" selected="selected"></option>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaDrDireccion">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                            
                            <div class="form-group" id="grouparArea">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por área:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <select class="form-control" id="arArea">
                                        <option value="" selected="selected"></option>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaArArea">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                            
                        </form>
                    </div>
                    <!-- TABLA -->

                    <table class="table table-hover table-condensed" id="tablaPuestos"></table>

                </div>
                
                <center>
                    <div class="alert alert-danger" style="width: 800px; display: none" id="alert">
                        <p id="alertMsg"></p>
                    </div>
                </center>
                
                <div class="panel-footer">Nota: Acciones validas dependeran del rol del usuario</div>
            </div>
        </div>

        <!-- FORMULARIO -->
        <div class="modal fade" id="formularioAdministrar" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" id="myModalTitle"> Administrar Puestos</h4>
                    </div>
                    <div class="modal-body" id="myModalMessage">
                        <form role="form" onsubmit="return false;" id="formPuestos">
                            <div class="form-group" id="groupCodigo">
                                <label for="codigo">Código:</label>
                                <input type="text" class="form-control" id="codigo" autofocus="true" placeholder="Código" maxlength="3">
                            </div>

                            <div class="form-group" id="groupDescripcion">
                                <label for="descripcion">Descripción:</label>
                                <input type="text" class="form-control" id="descripcion" placeholder="Descripción" maxlength="50">
                            </div>

                            <div class="form-group" id="groupEstado">
                                <label for="estado">Estado:</label>
                                <select class="form-control" id="estado">
                                    <option value="A" selected="selected">Activo</option>
                                    <option value="I">Inactivo</option>
                                </select>
                            </div>

                            <div class="form-group" id="groupGerencia">
                                <label for="gerencia">Gerencia:</label>
                                <select class="form-control" id="gerencia">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>
                            
                            <div class="form-group" id="groupDivision">
                                <label for="division">División:</label>
                                <select class="form-control" id="division">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>
                            
                            <div class="form-group" id="groupDireccion">
                                <label for="direccion">Dirección:</label>
                                <select class="form-control" id="direccion">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>
                            
                            <div class="form-group" id="groupArea">
                                <label for="area">Área:</label>
                                <select class="form-control" id="area">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <input type="hidden" value="agregarPuesto" id="puestosAction"/>

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




