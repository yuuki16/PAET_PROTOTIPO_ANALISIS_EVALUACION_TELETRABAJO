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
                                    <input type="text" class="form-control" id="trUsuario" placeholder="Digite el usuario" maxlength="20">
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
                                    <input type="number" class="form-control" id="trCedula" placeholder="Digite la cédula del trabajador" min="100000000" max="9999999999999999999">
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
                                    <input type="text" class="form-control" id="trNombre" placeholder="Digite el nombre del trabajador" maxlength="150">
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
                
                <center>
                    <div class="alert alert-danger" style="width: 800px; display: none" id="alert">
                        <p id="alertMsg"></p>
                    </div>
                </center>
                
                <div class="panel-footer">Nota: Acciones validas dependeran del rol del usuario</div>
            </div>
        </div>

        <!-- FORMULARIO TRABAJADORES-->
        <div class="modal fade" id="formularioAdministrarTrabajador" role="dialog" style="overflow-y: scroll;">
            <div class="modal-dialog modal-lg" style='width: 500px;'>
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalTitle"> Administrar Trabajadores</h4>
                    </div>
                    <div class="modal-body" id="myModalMessage">
                        <form role="form" onsubmit="return false;" id="formTrabajadores">
                            <div class="form-group" id="groupUsuario">
                                <label for="usuario">Usuario:</label>
                                <input type="text" class="form-control" id="usuario" autofocus="true" placeholder="Nombre de Usuario" maxlength="20" style='width: 230px;' autocomplete="off">
                            </div>

                            <div class="form-group" id="groupCedula">
                                <label for="cedula">Cédula:</label>
                                <input type="number" class="form-control" id="cedula" placeholder="102340567" min="100000000" max="99999999999999999999" style='width: 230px;'>
                            </div>

                            <div class="form-group" id="groupNombre">
                                <label for="nombre">Nombre:</label>
                                <input type="text" class="form-control" id="nombre" placeholder="Nombre del Trabajador" maxlength="50" style='width: 430px;' autocomplete="off">
                            </div>

                            <div class="form-group" id="groupApellido1">
                                <label for="apellido1">Primer Apellido:</label>
                                <input type="text" class="form-control" id="apellido1" placeholder="Primer Apellido del Trabajador" maxlength="50" style='width: 430px;' autocomplete="off">
                            </div>

                            <div class="form-group" id="groupApellido2">
                                <label for="apellido2">Segundo Apellido:</label>
                                <input type="text" class="form-control" id="apellido2" placeholder="Segundo Apellido del Trabajador" maxlength="50" style='width: 430px;' autocomplete="off">
                            </div>

                            <div class="form-group" id="groupSexo">
                                <label for="sexo">Sexo:</label>
                                <select class="form-control" id="sexo" style='width: 150px;'>
                                    <option value="" selected="selected"></option>
                                    <option value="MAS">Masculino</option>
                                    <option value="FEM">Femenino</option>
                                </select>
                            </div>

                            <div class="form-group" id="groupFechaIngreso" style='width: 230px;'>
                                <label for="fechaIngreso">Fecha de Ingreso a la Empresa:</label>
                                <div id="fechaIngreso" class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
                                    <input class="form-control" type="text" value="" readonly placeholder="dd/mm/aaaa" id="fechaIngresoText">
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>

                            <div class="form-group" id="groupFechaEntrada" style='width: 230px;'>
                                <label for="fechaEntrada">Fecha de Entrada al Puesto:</label>
                                <div id="fechaEntrada" class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
                                    <input class="form-control" type="text" value="" readonly placeholder="dd/mm/aaaa" id="fechaEntradaText">
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                            
                            <div class="form-group" id="groupEstado">
                                <label for="estado">Estado:</label>
                                <select class="form-control" id="estado" style='width: 150px;'>
                                    <option value="A" selected="selected">Activo</option>
                                    <option value="I">Inactivo</option>
                                </select>
                            </div>

                            <div class="form-group" id="groupJefatura">
                                <label for="jefatura">Jefatura:</label>
                                <select class="form-control" id="jefatura" style='width: 230px;'> 
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>

                            <div class="form-group" id="groupPuesto" style='width: 430px;'>
                                <label for="puesto">Puesto:</label>
                                <select class="form-control" id="puesto">
                                    <option value="" selected="selected"></option>
                                </select>
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
        <div class="modal fade" id="formularioAdministrarInformacion" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalTitle"> Administrar Información del Trabajador</h4>
                    </div>
                    <div class="modal-body" id="formularioAdministrarInformacionBody">
                        <form class="form-horizontal" role="form">
                            <div class="panel-group" id="accordion">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                                Correos
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseOne" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <div id="btnInsertarCorreo"></div>
                                            <br>
                                            <table class="table table-bordered table-hover" id="tablaCorreos"></table>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                                Teléfonos
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseTwo" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <div id="btnInsertarTelefono"></div>
                                            <br>
                                            <table class="table table-bordered table-hover" id="tablaTelefonos"></table>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                                Direcciones
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseThree" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <div id="btnInsertarDireccionFisica"></div>
                                            <br>
                                            <table class="table table-bordered table-hover" id="tablaDireccionesFisicas"></table>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10" id="btnGuardarInfoTrabajador">
                                    <button type="submit" class="btn btn-primary" id="guardarInfo">Guardar</button>
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
                        <h4 class="modal-title" id="modalMensajesTitle">Modal Header</h4>
                    </div>
                    <div class="modal-body" id="modalMensajesMessage">
                        <p>This is a small modal.</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- FORM CORREO -->
        <div class="modal fade" id="formularioAdministrarCorreo" role="dialog">
            <div class="modal-dialog modal-sm" style='width: 910px;'>
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="formAdministrarCorreoTitle">Administrar Correos</h4>
                    </div>
                    <div class="modal-body" id="formAdministrarCorreoMessage">
                        <form role="form" onsubmit="return false;" id="formCorreos">
                            <div class="form-group" id="groupCodigoCorreo" style="display: none">
                                <input type="text" class="form-control" id="codigoCorreo" autofocus="true" placeholder="Código" readonly="true">
                            </div>
º   
                            <div class="form-group" id="groupCorreoCorreo">
                                <label for="correoCorreo">Correo:</label>
                                <input type="email" class="form-control" id="correoCorreo" placeholder="correo@ice.go.cr" maxlength="100" autocomplete="off">
                            </div>
                            
                            <div class="form-group" id="groupEstadoCorreo">
                                <label for="estadoCorreo">Estado:</label>
                                <select class="form-control" id="estadoCorreo" style='width: 150px;'>
                                    <option value="A" selected="selected">Activo</option>
                                    <option value="I">Inactivo</option>
                                </select>
                            </div>
                            
                            <div class="form-group" id="groupTrabajador" style="display: none">
                                <input type="text" class="form-control" id="correoTrabajador">
                            </div>
                            
                            <div class="form-group">
                                <input type="hidden" value="agregarCorreo" id="correosAction"/>

                                <button type="submit" class="btn btn-primary" id="guardarCorreo">Guardar</button>

                                <button type="reset" class="btn btn-danger" id="cancelarCorreo">Cancelar</button>
                            </div>

                            <div class="form-group height25" >
                                <div class="alert alert-success hiddenDiv" id="mensajeResultCorreo">
                                    <strong id="mensajeResultCorreoNeg">Info!</strong> 
                                    <span id="mensajeResultCorreoText">This alert box could indicate a neutral informative change or action.</span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- FORM TELEFONO -->
        <div class="modal fade" id="formularioAdministrarTelefono" role="dialog">
            <div class="modal-dialog modal-sm" style='width: 280px;'>
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="formularioAdministrarTelefonoTitle">Administrar Teléfonos</h4>
                    </div>
                    <div class="modal-body" id="formularioAdministrarTelefonoMessage">
                        <form role="form" onsubmit="return false;" id="formTelefonos">
                            <div class="form-group" id="groupTelefonoCodigo" style="display: none">
                                <input type="text" class="form-control" id="telefonoCodigo" autofocus="true" placeholder="Código" readonly="true">
                            </div>

                            <div class="form-group" id="groupTelefonoTelefono">
                                <label for="telefonoTelefono">Teléfono:</label>
                                <input type="number" class="form-control" id="telefonoTelefono" placeholder="Número Telefónico" min="11111111" max="99999999999999999999" style='width: 230px;' autocomplete="off">
                            </div>
                            
                            <div class="form-group" id="groupTelefonoDescripcion">
                                <label for="telefonoDescripcion">Descripción:</label>
                                <input type="text" class="form-control" id="telefonoDescripcion" placeholder="Descripción" maxlength="20" style='width: 230px;' autocomplete="off">
                            </div>
                            
                            <div class="form-group" id="groupTelefonoEstado">
                                <label for="telefonoEstado">Estado:</label>
                                <select class="form-control" id="telefonoEstado" style='width: 150px;'>
                                    <option value="A" selected="selected">Activo</option>
                                    <option value="I">Inactivo</option>
                                </select>
                            </div>
                            
                            <div class="form-group" id="groupTelefonoTrabajador" style="display: none">
                                <input type="text" class="form-control" id="telefonoTrabajador">
                            </div>
                            
                            <div class="form-group">
                                <input type="hidden" value="agregarTelefono" id="telefonosAction"/>

                                <button type="submit" class="btn btn-primary" id="guardarTelefono">Guardar</button>

                                <button type="reset" class="btn btn-danger" id="cancelarTelefono">Cancelar</button>
                            </div>

                            <div class="form-group height25" >
                                <div class="alert alert-success hiddenDiv" id="mensajeResultTelefono">
                                    <strong id="mensajeResultTelefonoNeg">Info!</strong> 
                                    <span id="mensajeResultTelefonoText">This alert box could indicate a neutral informative change or action.</span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- FORM DIRECCION -->
        <div class="modal fade" id="formularioAdministrarDireccionFisica" role="dialog">
            <div class="modal-dialog modal-sm" style='width: 500px;'>
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="formularioAdministrarDireccionFisicaTitle">Administrar Dirección</h4>
                    </div>
                    <div class="modal-body" id="formularioAdministrarDireccionFisicaMessage">
                        <form role="form" onsubmit="return false;" id="formDireccionesFisicas">
                            <div class="form-group" id="groupDireccionFisicaCodigo" style="display: none">
                                <input type="text" class="form-control" id="direccionFisicaCodigo" autofocus="true">
                            </div>
                            
                            <div class="form-group" id="groupProvincia">
                                <label for="provincia">Provincia:</label>
                                <select class="form-control" id="provincia" style='width: 230px;'>
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>
                            
                            <div class="form-group" id="groupCanton">
                                <label for="canton">Cantón:</label>
                                <select class="form-control" id="canton" style='width: 230px;'>
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>
                            
                            <div class="form-group" id="groupDistrito">
                                <label for="distrito">Distrito:</label>
                                <select class="form-control" id="distrito">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>

                            <div class="form-group" id="groupDireccionFisicaDireccion">
                                <label for="direccionFisicaDireccion">Dirección exacta:</label>
                                <input type="text" class="form-control" id="direccionFisicaDireccion" placeholder="Dirección exacta" maxlength="100">
                            </div>
                            
                            <div class="form-group" id="groupDireccionFisicaEstado">
                                <label for="direccionFisicaEstado">Estado:</label>
                                <select class="form-control" id="direccionFisicaEstado" style='width: 150px;'>
                                    <option value="A" selected="selected">Activo</option>
                                    <option value="I">Inactivo</option>
                                </select>
                            </div>
                            
                            <div class="form-group" id="groupDireccionFisicaTrabajador" style="display: none">
                                <input type="text" class="form-control" id="direccionFisicaTrabajador">
                            </div>
                            
                            <div class="form-group">
                                <input type="hidden" value="agregarDireccionFisica" id="direccionesFisicasAction"/>

                                <button type="submit" class="btn btn-primary" id="guardarDireccionFisica">Guardar</button>

                                <button type="reset" class="btn btn-danger" id="cancelarDireccionFisica">Cancelar</button>
                            </div>

                            <div class="form-group height25" >
                                <div class="alert alert-success hiddenDiv" id="mensajeResultDireccionFisica">
                                    <strong id="mensajeResultDireccionFisicaNeg">Info!</strong> 
                                    <span id="mensajeResultDireccionFisicaText">This alert box could indicate a neutral informative change or action.</span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>





