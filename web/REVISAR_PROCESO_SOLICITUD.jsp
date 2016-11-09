<%-- 
    Document   : REVISAR_PROCESO_SOLICITUD
    Created on : 08-nov-2016, 23:14:39
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Verificar Proceso de Solicitud de Teletrabajo</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- Referencias -->
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/utils.js" type="text/javascript"></script>
        <script src="js/REVISAR_PROCESO_SOLICITUD.js" type="text/javascript"></script>  
    </head>
    <body>
        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Verificar Proceso de Solicitud de Teletrabajo</h3></div>
                <div class="panel-body">
                    <!-- BUSQUEDA -->
                    <div class="col-sm-12">
                        <form role="form" onsubmit="return false;" id="formBusqueda" class="form-horizontal centered">
                            <div class="form-group" id="grouptrCedula">
                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                    <p><b>Buscar procesos por cédula del trabajador:</b></p>
                                </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="trCedula" placeholder="Digite la cédula del trabajador">
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info centered busqueda" data-toggle="modal" id="btBusquedaTrCedu">
                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                    <button type="button" class="btn btn-default centered" data-toggle="modal" id="btLimpiarBusqueda">Limpiar Búsqueda</button>        
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Procesos -->
        <div class="row" id="proceso" >
            <!-- Procesos -->
            <div class="col-sm-4">
                <div class="timeline-centered">
                    <article class="timeline-entry">

                        <div class="timeline-entry-inner">

                            <div class="timeline-icon bg-secondary">
                                <i class="entypo-suitcase"></i>
                            </div>

                            <div class="timeline-label">
                                <p>09 - Octubre - 2016</p>
                                <h2><a href="#">Verificar Proceso</a></h2>
                            </div>
                        </div>

                    </article>

                    <article class="timeline-entry">

                        <div class="timeline-entry-inner">

                            <div class="timeline-icon bg-secondary">
                                <i class="entypo-suitcase"></i>
                            </div>

                            <div class="timeline-label">
                                <p>03 - Marzo - 2010</p>
                                <h2><a href="#">Verificar Proceso</a></h2>
                            </div>
                        </div>
                    </article>
                </div>
            </div>
            <!-- Estados -->
            <div class="col-sm-4">
                <div class="timeline-centered">
                    <article class="timeline-entry">

                        <div class="timeline-entry-inner">

                            <div class="timeline-icon finished">
                                <i class="entypo-suitcase"></i>
                            </div>

                            <div class="timeline-label">
                                <p>Estado 1</p>
                                <p>Entrada: 05 - Octubre - 2016</p>
                                <p>Finalizado: 06 - Octubre - 2016</p>
                            </div>
                        </div>

                    </article>

                    <article class="timeline-entry">

                        <div class="timeline-entry-inner">

                            <div class="timeline-icon finished">
                                <i class="entypo-suitcase"></i>
                            </div>

                            <div class="timeline-label">
                                <p>Estado 2</p>
                                <p>Entrada: 05 - Octubre - 2016</p>
                                <p>Finalizado: 06 - Octubre - 2016</p>
                            </div>
                        </div>

                    </article>

                    <article class="timeline-entry">

                        <div class="timeline-entry-inner">

                            <div class="timeline-icon pending">
                                <i class="entypo-suitcase"></i>
                            </div>

                            <div class="timeline-label">
                                <p>Estado 3</p>
                                <p>Entrada: 05 - Octubre - 2016</p>
                                <p>Pendiente</p>
                            </div>
                        </div>

                    </article>
                    <article class="timeline-entry">

                        <div class="timeline-entry-inner">

                            <div class="timeline-icon bg-gray">
                                <i class="entypo-suitcase"></i>
                            </div>

                            <div class="timeline-label">
                                <p>Estado 4</p>
                            </div>
                        </div>

                    </article>
                    <article class="timeline-entry">

                        <div class="timeline-entry-inner">

                            <div class="timeline-icon bg-gray">
                                <i class="entypo-suitcase"></i>
                            </div>

                            <div class="timeline-label">
                                <p>Estado 5</p>
                            </div>
                        </div>

                    </article>
                    <article class="timeline-entry">

                        <div class="timeline-entry-inner">

                            <div class="timeline-icon bg-gray">
                                <i class="entypo-suitcase"></i>
                            </div>

                            <div class="timeline-label">
                                <p>Estado 6</p>
                            </div>
                        </div>

                    </article>
                    <article class="timeline-entry">

                        <div class="timeline-entry-inner">

                            <div class="timeline-icon bg-gray">
                                <i class="entypo-suitcase"></i>
                            </div>

                            <div class="timeline-label">
                                <p>Estado 7</p>
                            </div>
                        </div>

                    </article>

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
