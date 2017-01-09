<%-- 
    Document   : ADMINISTRAR_ESTADOS_SOLICITUD_JSP
    Created on : 11-nov-2016, 12:43:18
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Administrar Proceso de Finalización</title>

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
        <script src="js/ADMINISTRAR_ESTADOS_FINALIZACION_JS.js" type="text/javascript"></script>

    </head>
    <body>
        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Administrar Proceso de Finalización</h3></div>
                <div class="panel-body">
                    <div class="row">
                        <section>
                            <div class="wizard">
                                <div class="wizard-inner">
                                    <div class="connecting-line"></div>
                                    <ul class="nav nav-tabs" role="tablist" id="estados">

                                    </ul>
                                </div>
                                <div class="tab-content">
                                    <!--PANEL ESTADO 2-->
                                    <div class="tab-pane" role="tabpanel" id="step2">
                                        <h3>Carta de finalización de teletrabajo</h3>
                                        <p>Paso 2 de 5</p>
                                        <ul class="list-inline">
                                            <li>
                                                <h5>Adjuntar nota de finalización de teletrabajo</h5>
                                            </li>
                                            <br>
                                            <li>
                                                <br>
                                                <label class="btn btn-info btn-file">
                                                    Adjuntar archivo <input type="file" style="display: none;" id="nota">
                                                </label>
                                                <label id="archivonota"></label>
                                            </li>
                                            <li>
                                                <br>
                                                <h5>Observación:</h5>
                                                <textarea class="form-control" rows="5" id="observacion2" style="width: 830px"></textarea>
                                            </li>
                                            <br>
                                            <li><button type="button" class="btn btn-success next-step" style="position: absolute; right: 100px" id="guardar2">Avanzar Proceso</button></li>
                                        </ul>
                                    </div>
                                    <!--PANEL ESTADO 3-->
                                    <div class="tab-pane" role="tabpanel" id="step3">
                                        <h3>Desconexión</h3>
                                        <p>Paso 3 de 5</p>
                                        <ul class="list-inline">
                                            <li>
                                                <h5>Confirmar desconexión.</h5>
                                            </li>
                                            <br>
                                            <li>
                                                <br>
                                                <h5>Observación:</h5>
                                                <textarea class="form-control" rows="5" id="observacion3" style="width: 830px"></textarea>
                                            </li>
                                            <br>
                                            <li><button type="button" class="btn btn-success next-step" style="position: absolute; right: 100px" id="guardar3">Avanzar Proceso</button></li>
                                        </ul>
                                    </div>
                                    <!--PANEL ESTADO 4-->
                                    <div class="tab-pane" role="tabpanel" id="step4">
                                        <h3>Reasignación del espacio físico</h3>
                                        <p>Paso 4 de 5</p>
                                        <ul class="list-inline">
                                            <li>
                                                <h5>Espacio físico asignado:</h5>
                                            </li>
                                            <br>
                                            <li>
                                                <br>
                                                <input type="text" id="espacioFisico" class="form-control">
                                            </li>
                                            <li>
                                                <br>
                                                <h5>Observación:</h5>
                                                <textarea class="form-control" rows="5" id="observacion4" style="width: 830px"></textarea>
                                            </li>
                                            <br>
                                            <li><button type="button" class="btn btn-success next-step" style="position: absolute; right: 100px" id="guardar4">Avanzar Proceso</button></li>
                                        </ul>
                                    </div>
                                    <!--PANEL ESTADO 5-->
                                    <div class="tab-pane" role="tabpanel" id="step5">
                                        <h3>Encuesta del teletrabajador</h3>
                                        <p>Paso 5 de 5</p>
                                        <ul class="list-inline">
                                            <li>
                                                <h5>Adjuntar encuesta realizada por el teletrabajador</h5>
                                            </li>
                                            <br>
                                            <li>
                                                <br>
                                                <label class="btn btn-info btn-file">
                                                    Adjuntar archivo <input type="file" style="display: none;" id="encuesta">
                                                </label>
                                                <label id="archivoencuesta"></label>
                                            </li>
                                            <li>
                                                <br>
                                                <h5>Observación:</h5>
                                                <textarea class="form-control" rows="5" id="observacion5" style="width: 830px"></textarea>
                                            </li>
                                            <br>
                                            <li><button type="button" class="btn btn-success next-step" style="position: absolute; right: 100px" id="guardar5">Finalizar Proceso</button></li>
                                        </ul>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="form-group height25" >
                                        <div class="alert alert-success hiddenDiv" id="mensajeResult">
                                            <strong id="mensajeResultNeg">Info!</strong> 
                                            <span id="mensajeResultText">This alert box could indicate a neutral informative change or action.</span>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </section>
                    </div>
                </div>
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
