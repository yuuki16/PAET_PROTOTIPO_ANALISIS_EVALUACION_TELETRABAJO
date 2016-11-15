<%-- 
    Document   : SOLICITUD_TELETRABAJO
    Created on : 30-oct-2016, 20:03:38
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Formulario Solicitud de Teletrabajo</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- Referencias -->
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/utils.js" type="text/javascript"></script>
        <script src="js/SOLICITUD_TELETRABAJO_JS.js" type="text/javascript"></script>  
    </head>
    <body>
        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Formulario Solicitud de Teletrabajo</h3></div>
                <div class="panel-body">
                    <div class="col-sm-12">
                        <form role="form" onsubmit="return false;" id="formSolicitudTeletrabajo">
                            <div class="form-group" id="groupCandidato">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar datos del Candidato/a:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <input type="number" class="form-control" id="trCandidato" placeholder="Digite la cédula del candidato a verificar">
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaCandidato">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                    <button type="button" class="btn btn-default centered" data-toggle="modal" id="btLimpiarBusqueda">Limpiar Búsqueda</button>
                                </div>
                            </div>

                            <div class="form-group" id="groupGerencia">
                                <label for="gerencia">Gerencia:</label>
                                <select class="form-control" id="gerencia" readonly="true">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>

                            <div class="form-group" id="groupDivision">
                                <label for="division">División:</label>
                                <select class="form-control" id="division" readonly="true">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>

                            <div class="form-group" id="groupGerencia">
                                <label for="direccion">Dirección:</label>
                                <select class="form-control" id="direccion" readonly="true">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>

                            <div class="form-group" id="groupArea">
                                <label for="area">Área:</label>
                                <select class="form-control" id="area" readonly="true">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>

                            <div class="form-group" id="groupJefaturaInmediata">
                                <label for="jefatura">Jefatura Inmediata:</label>
                                <select class="form-control" id="jefatura">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>

                            <div class="form-group" id="groupUsuario" style="display: none">
                                <input type="text" class="form-control" id="usuario" readonly="true">
                            </div>

                            <div class="form-group" id="groupNombre">
                                <label for="nombre">Nombre Candidato/a:</label>
                                <input type="text" class="form-control" id="nombre" readonly="true">
                            </div>

                            <div class="form-group" id="groupCedula">
                                <label for="cedula">Cédula:</label>
                                <input type="text" class="form-control" id="cedula" readonly="true">
                            </div>

                            <div class="form-group" id="groupPuesto">
                                <label for="puesto">Puesto:</label>
                                <select class="form-control" id="puesto" readonly="true">
                                    <option value="" selected="selected"></option>
                                </select>
                            </div>

                            <div class="form-group" id="groupFechaEntradaPuesto">
                                <label for="fechaEntradaPuesto">Fecha de entrada al puesto:</label>
                                <input type="text" class="form-control" id="fechaEntradaPuesto" readonly="true">
                            </div>

                            <div class="form-group" id="groupFechaIngreso">
                                <label for="fechaIngreso">Fecha de Ingreso al I.C.E.:</label>
                                <input type="text" class="form-control" id="fechaIngreso" readonly="true">
                            </div>

                            <div class="form-group" id="groupJustificacion">
                                <label for="justificacion">Justificación para ingresar a la Modalidad de Teletrabajo</label>
                                <textarea class="form-control" rows="5" id="justificacion"></textarea>
                            </div>

                            <div class="form-group" id="groupBeneficios">
                                <label for="beneficios">Beneficios que recibiría la Institución:</label>
                                <textarea class="form-control" rows="5" id="beneficios"></textarea>
                            </div>

                            <div class="form-group" id="groupModalidad">
                                <label for="modalidad">Modalidad:</label>
                                <select class="form-control" id="modalidad">
                                    <option value="" selected="selected"></option>
                                    <option value="C">Casa</option>
                                </select>
                            </div>

                            <div class="form-group" id="groupTiempo">
                                <label for="tiempo">Tiempo:</label>
                                <input id="tiempo" type="number" min="1"/>meses
                                <input type="checkbox" id="tiempoIndefinido" value="SIN">Tiempo Indefinido<br>
                            </div>

                            <div class="form-group" id="groupDias">
                                <label for="dias">Definir los días en Teletrabajo:</label>
                            </div>

                            <div class="form-group" id="groupConectividad">
                                <label for="conectividad">Conectividad:</label>
                                <select class="form-control" id="conectividad">
                                    <option value="" selected="selected"></option>
                                    <option value="I">Institucional</option>
                                    <option value="P">Personal</option>
                                </select>
                            </div>

                            <div class="form-group" id="groupTelefonia">
                                <label for="telefonia">Comunicación Telefónica:</label>
                                <select class="form-control" id="telefonia">
                                    <option value="" selected="selected"></option>
                                    <option value="I">Institucional</option>
                                    <option value="P">Personal</option>
                                </select>
                            </div>

                            <div class="form-group" id="groupEquipoTecnologico">
                                <label for="equipoTecnologico">Equipo Tecnológico:</label>
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
