<%-- 
    Document   : TR_TRABAJADOR_JSP
    Created on : 27-oct-2016, 21:50:28
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mantenimiento de Trabajadores</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- Referencias -->
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <link href="css/datetimepicker.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/datetimepicker.js" type="text/javascript"></script>
        <script src="js/utils.js" type="text/javascript"></script>
        <script src="js/TR_TRABAJADOR_JS.js" type="text/javascript"></script>  
    </head>
    <body>
        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Mantenimiento de Trabajadores</h3></div>
                <div class="panel-body">
                    <center>
                        <button type="button" class="btn btn-success centered" data-toggle="modal" data-target="#formularioAdministrarTrabajador" id="btMostrarForm">Insertar Trabajador</button>
                        <button type="button" class="btn btn-default centered" data-toggle="modal" id="btLimpiarBusqueda">Limpiar Búsqueda</button>
                    </center><br>
                    <!-- BUSQUEDA -->
                    <div class="col-sm-12">
                        <form role="form" onsubmit="return false;" id="formBusqueda" class="form-horizontal centered">
                            <div class="form-group" id="grouptrUsuario">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por usuario:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="trUsuario" placeholder="Digite el usuario">
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaTrUsuario">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                            <div class="form-group" id="grouptrCedula">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por cédula:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="trCedula" placeholder="Digite la cédula del trabajador">
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaTrCedula">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                            <div class="form-group" id="grouptrNombre">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por nombre:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="trNombre" placeholder="Digite el nombre del trabajador">
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaTrNombre">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                            <div class="form-group" id="grouptrJefatura">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por jefatura:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <select class="form-control" id="trJefatura">
                                        <option value="" selected="selected"></option>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaTrJefatura">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                            <div class="form-group" id="groupptPuesto">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar por puesto:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <select class="form-control" id="ptPuesto">
                                        <option value="" selected="selected"></option>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaPtPuesto">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- TABLA -->

                    <table class="table table-hover table-condensed" id="tablaTrabajadores"></table>

                </div>
                <div class="panel-footer">Nota: Acciones validas dependeran del rol del usuario</div>
            </div>
        </div>

        <!-- FORMULARIO TRABAJADORES-->
        <div class="modal fade" id="formularioAdministrarTrabajador" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" id="myModalTitle"> Administrar Trabajadores</h4>
                    </div>
                    <div class="modal-body" id="myModalMessage">
                        <form role="form" onsubmit="return false;" id="formTrabajadores">
                            <div class="form-group" id="groupUsuario">
                                <label for="usuario">Usuario:</label>
                                <input type="text" class="form-control" id="usuario" autofocus="true" placeholder="Usuario">
                            </div>

                            <div class="form-group" id="groupCedula">
                                <label for="cedula">Cédula:</label>
                                <input type="text" class="form-control" id="cedula" placeholder="Cédula" >
                            </div>

                            <div class="form-group" id="groupNombre">
                                <label for="nombre">Nombre:</label>
                                <input type="text" class="form-control" id="nombre" placeholder="Nombre" >
                            </div>

                            <div class="form-group" id="groupApellido1">
                                <label for="apellido1">Primer Apellido:</label>
                                <input type="text" class="form-control" id="apellido1" placeholder="Primer Apellido" >
                            </div>

                            <div class="form-group" id="groupApellido2">
                                <label for="apellido2">Segundo Apellido:</label>
                                <input type="text" class="form-control" id="apellido2" placeholder="Segundo Apellido" >
                            </div>

                            <div class="form-group" id="groupSexo">
                                <label for="sexo">Sexo:</label>
                                <select class="form-control" id="sexo">
                                    <option value="" selected="selected"></option>
                                    <option value="MAS">Masculino</option>
                                    <option value="FEM">Femenino</option>
                                </select>
                            </div>

                            <div class="form-group" id="groupFechaIngreso">
                                <label for="fechaIngreso">Fecha de Ingreso a la Empresa:</label>
                                <div id="fechaIngreso" class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
                                    <input class="form-control" type="text" value="" readonly placeholder="dd/mm/aaaa" id="fechaIngresoText">
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>

                            <div class="form-group" id="groupEstado">
                                <label for="estado">Estado:</label>
                                <select class="form-control" id="estado">
                                    <option value="A" selected="selected">Activo</option>
                                    <option value="I">Inactivo</option>
                                </select>
                            </div>

                            <div class="form-group" id="groupJefatura">
                                <label for="jefatura">Jefatura:</label>
                                <select class="form-control" id="jefatura">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>

                            <div class="form-group" id="groupPuesto">
                                <label for="puesto">Puesto:</label>
                                <select class="form-control" id="puesto">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>

                            <div class="form-group" id="groupFechaEntrada">
                                <label for="fechaEntrada">Fecha de Entrada al Puesto:</label>
                                <div id="fechaEntrada" class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
                                    <input class="form-control" type="text" value="" readonly placeholder="dd/mm/aaaa" id="fechaEntradaText">
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>

                            <div class="form-group">
                                <input type="hidden" value="agregarTrabajador" id="trabajadoresAction"/>

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

        <!-- FORMULARIO EXTRAS-->
        <div class="modal fade" id="formularioAdministrarInformación" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" id="myModalTitle"> Administrar Información del Trabajador</h4>
                    </div>
                    <div class="modal-body" id="myModalMessage">
                        <h3>Correos</h3>
                        <button type="button" class="btn btn-success">Insertar Correo</button>
                        <table class="table table-bordered table-hover" id="tablaCorreos"></table>
                        <h3>Teléfonos</h3>
                        <button type="button" class="btn btn-success">Insertar Teléfono</button>
                        <table class="table table-bordered table-hover" id="tablaTelefonos"></table>
                        <h3>Direcciones</h3>
                        <button type="button" class="btn btn-success">Insertar Dirección</button>
                        <table class="table table-bordered table-hover" id="tablaDirecciones"></table>
                    </div>
                    <input type="hidden" value="agregarInformacionTrabajador" id="informacionTrabajadoresAction"/>

                    <button type="submit" class="btn btn-primary" id="guardarInfo">Guardar</button>
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





