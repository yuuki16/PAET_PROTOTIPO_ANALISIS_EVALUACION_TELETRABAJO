<%-- 
    Document   : ADMINISTRAR_ESTADOS_SOLICITUD_JSP
    Created on : 11-nov-2016, 12:43:18
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Administrar Proceso de Solicitud</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- Referencias -->
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/utils.js" type="text/javascript"></script>
        <script src="js/ADMINISTRAR_ESTADOS_SOLICITUD_JS.js" type="text/javascript"></script>

    </head>
    <body>
        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Administrar Proceso de Solicitud</h3></div>
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
                                        <h3>Análisis de Puesto y Aprobación del Gerente</h3>
                                        <p>Paso 2 de 8</p>
                                        <ul class="list-inline">
                                            <li>
                                                <h4>Análisis de puesto</h4>
                                            </li>
                                            <br>
                                            <li>
                                                <div>
                                                    <h5>Actividades del Puesto</h5>
                                                    <h6>Defina las actividades del puesto por evaluar en la siguiente tabla, proceda a realizar el alineamiento de cada actividad a los objetivos.</h6>
                                                    <table class="table" id="tablaActividades">
                                                        <thead>
                                                            <tr id="caracteristicas">
                                                                <th>Actividades</th>
                                                                <th style="background-color: #056d2b; font-size: 10px">Objetivo Área</th>
                                                                <th style="background-color: #056d2b; font-size: 10px">Objetivo N1</th>
                                                                <th style="background-color: #056d2b; font-size: 10px">Objetivo Dirección</th>
                                                                <th style="background-color: #056d2b; font-size: 10px">Objetivo División</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id= "actividades">
                                                            <tr id="act0">
                                                                <td><input type="text" id="actividad0" style="width: 200px"></td>
                                                                <td><input type="radio" name="alineamiento0" value="A"></td>
                                                                <td><input type="radio" name="alineamiento0" value="N"></td>
                                                                <td><input type="radio" name="alineamiento0" value="D"></td>
                                                                <td><input type="radio" name="alineamiento0" value="V"></td>
                                                                <td><input type="checkbox" value="1" name="caracteristicas0" onclick="verificarActividad('0')"></td>
                                                                <td><input type="checkbox" value="2" name="caracteristicas0" onclick="verificarActividad('0')"></td>
                                                                <td><input type="checkbox" value="21" name="caracteristicas0" onclick="verificarActividad('0')"></td>
                                                                <td><input type="checkbox" value="22" name="caracteristicas0" onclick="verificarActividad('0')"></td>
                                                                <td><input type="text" id="resultado0" style="width: 50px" readonly="true"></td>
                                                            </tr>
                                                            <tr id='act1'></tr>
                                                        </tbody>
                                                    </table>
                                                    <a id="add_row" class="btn btn-default pull-left">Agregar Fila</a><a id='delete_row' class="pull-right btn btn-default">Borrar Fila</a>
                                                </div>
                                            </li>
                                            <li>
                                                <style>
                                                    table { 
                                                        width: 100%; 
                                                        border-collapse: collapse; 
                                                    }

                                                    th { 
                                                        background: #1b92a6; 
                                                        color: white; 
                                                        font-weight: bold; 
                                                    }
                                                    td, th { 
                                                        padding: 6px; 
                                                        border: 1px solid #ccc; 
                                                        text-align: left; 
                                                    }

                                                    @media 
                                                    only screen and (max-width: 760px),
                                                    (min-device-width: 768px) and (max-device-width: 1024px)  {

                                                        .responsive-table-input-matrix {
                                                            display: block;
                                                            position: relative;
                                                            width: 100%;

                                                            &:after {
                                                                clear: both;
                                                                content: '';
                                                                display: block;
                                                                font-size: 0;
                                                                height: 0;
                                                                visibility: hidden;
                                                            }

                                                            tbody {
                                                                display: block;
                                                                overflow-x: auto;
                                                                position: relative;
                                                                white-space: nowrap;
                                                                width: auto;


                                                                tr {
                                                                    display: inline-block;
                                                                    vertical-align: top;

                                                                    td {
                                                                        display: block;
                                                                        text-align: center;

                                                                        &:first-child {
                                                                            text-align: left;
                                                                        }
                                                                    }
                                                                }
                                                            }

                                                            thead {
                                                                display: block;
                                                                float: left;
                                                                margin-right: 10px;

                                                                &:after {
                                                                    clear: both;
                                                                    content: "";
                                                                    display: block;
                                                                    font-size: 0;
                                                                    height: 0;
                                                                    visibility: hidden;
                                                                }

                                                                th:first-of-type {
                                                                    height: 1.4em;
                                                                }

                                                                th {
                                                                    display: block;
                                                                    text-align: right;

                                                                    &:first-child {
                                                                        text-align: right;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                </style>
                                                <div>
                                                    <h5>Factores Complementarios</h5>
                                                    <h6>Lea cada una de las proposiciones y marque la opción que más se ajusta.</h6>
                                                    <table class="responsive-table-input-matrix" id="tablaFactores">
                                                        <thead>
                                                            <tr>
                                                                <th></th>
                                                                <th>Total en Desacuerdo</th>
                                                                <th>En Desacuerdo</th>
                                                                <th>Neutro</th>
                                                                <th>De Acuerdo</th>
                                                                <th>Totalmente de Acuerdo</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id= "factoresComplementarios"></tbody>
                                                    </table>
                                                </div>
                                            </li>
                                            <br>
                                            <li>
                                                <h5>Resultado Automático del Análisis</h5>
                                                <h6>Criterio de evaluación decisivo en la identificación de puestos teletrabajables</h6>
                                                <button type="button" class="btn btn-info" id="generarAnalisis">Realizar Análisis Automático</button>

                                                <div class="col-sm-4">  
                                                    <div id="puntajeActividadesColor" class="colorResultado"></div>
                                                    <label for="puntajeActividades">Puntaje Actividades:</label>
                                                    <input type="text" class="form-control" id="puntajeActividades" readonly="true">
                                                    <div id="puntajeFactoresColor" class="colorResultado"></div>
                                                    <label for="puntajeFactores">Puntaje Factores:</label>
                                                    <input type="text" class="form-control" id="puntajeFactores" readonly="true">
                                                </div>
                                                <div class="col-sm-4">
                                                    <h6>Recomendación Automática Según Actividades Teletrabajables</h6>
                                                    <label for="tipoPuesto">Tipo de Puesto:</label>
                                                    <input type="text" class="form-control" id="tipoPuesto" readonly="true">
                                                    <label for="modalidad">Modalidad:</label>
                                                    <input type="text" class="form-control" id="modalidad" readonly="true">
                                                    <label for="dias">Cantidad de Días </label>
                                                    <input type="text" class="form-control" id="dias" readonly="true">
                                                </div>
                                                <div class="col-sm-4">
                                                    <h6>Recomendación Automática Según Aspectos de Gestión del Puesto</h6>
                                                    <label for="resultadoFactoresProsa" style="font-weight: 100" id="recomendacionFactores">Los aspectos de gestión relacionados con el puesto:</label>
                                                </div>

                                            </li>
                                            <br>
                                            <br>
                                            <li>
                                                <h5>Adjuntar nota de aprobación del gerente</h5>
                                            </li>
                                            <br>
                                            <li>
                                                <br>
                                                <label class="btn btn-info btn-file">
                                                    Adjuntar archivo <input type="file" style="display: none;" id="notaGerente" name="filetoupload">
                                                </label>
                                                <label id="archivo"></label>
                                            </li>
                                            <br>
                                            <li>
                                                <br>
                                                <h5>Observación:</h5>
                                                <textarea class="form-control" rows="5" id="observacion2" style="width: 830px"></textarea>
                                            </li>
                                            <br>
                                            <br>
                                            <li><button type="button" class="btn btn-success next-step" style="position: absolute; right: 100px" id="guardar2">Avanzar Proceso</button></li>
                                        </ul>
                                    </div> 
                                    <!--PANEL ESTADO 3-->
                                    <div class="tab-pane" role="tabpanel" id="step3">
                                        <h3>Análisis Psicométrico y Psicosocial</h3>
                                        <p>Paso 3 de 8</p>
                                        <ul class="list-inline">
                                            <li>
                                                <h5>Adjuntar resultado del análisis psicométrico</h5>
                                            </li>
                                            <br>
                                            <li>
                                                <br>
                                                <label class="btn btn-info btn-file">
                                                    Adjuntar archivo <input type="file" style="display: none;" id="documentoPsicometrico">
                                                </label>
                                                <label id="archivoPsicometrico"></label>
                                            </li>
                                            <br>
                                            <br>
                                            <li>
                                                <h5>Adjuntar resultado del análisis psicosocial</h5>
                                            </li>
                                            <br>
                                            <li>
                                                <br>
                                                <label class="btn btn-info btn-file">
                                                    Adjuntar archivo <input type="file" style="display: none;" id="documentoPsicosocial">
                                                </label>
                                                <label id="archivoPsicosocial"></label>
                                            </li>
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
                                        <h3>Aprobar la solicitud</h3>
                                        <p>Paso 4 de 8</p>
                                        <ul class="list-inline">
                                            <li>
                                                <h5>Resultados del análisis</h5>
                                            </li>
                                            <br>
                                            <li>
                                                <div class="col-sm-4" style="position: relative; width: 300px">  
                                                    <div id="resultadoActividadesColor" class="colorResultado"></div>
                                                    <label for="resultadoActividades">Resultado de las actividades:</label>
                                                    <input type="text" class="form-control" id="resultadoActividades" readonly="true">
                                                    <div id="resultadoFactoresColor" class="colorResultado"></div>
                                                    <label for="resultadoFactores">Resultado de los Factores:</label>
                                                    <input type="text" class="form-control" id="resultadoFactores" readonly="true">
                                                </div>
                                                <div class="col-sm-4" style="position: relative; width: 300px">
                                                    <h6>Recomendación de Análisis</h6>
                                                    <label for="tipoPuestoResultado">Tipo de Puesto:</label>
                                                    <input type="text" class="form-control" id="tipoPuestoResultado" readonly="true">
                                                    <label for="modalidadResultado">Modalidad:</label>
                                                    <input type="text" class="form-control" id="modalidadResultado" readonly="true">
                                                    <label for="diasResultado">Cantidad de Días </label>
                                                    <input type="text" class="form-control" id="diasResultado" readonly="true">
                                                    <label style="font-weight: 100" id="recomendacionResultadoFactores"></label>
                                                </div>
                                            </li>
                                            <br>
                                            <br>
                                            <li>
                                                <h5>Aprobar o Denegar Solicitud</h5>
                                                <button type="button" class="btn btn-success" id="aprobarSolicitud">Aprobar Solicitud</button>
                                                <button type="button" class="btn btn-danger" id="negarSolicitud">Denegar Solicitud</button>
                                                <input type="text" id="resultadoSolicitud" style="display: none">
                                            </li>
                                            <br>
                                            <br>
                                            <br>
                                            <li>
                                                <div id="diasTeletrabajo">
                                                    <label for="diasTeletrabajo">Definir los días en Teletrabajo:</label>
                                                    <label class="checkbox-inline"><input type="checkbox" name="dia" value="L">Lunes</label>
                                                    <label class="checkbox-inline"><input type="checkbox" name="dia" value="M">Martes</label>
                                                    <label class="checkbox-inline"><input type="checkbox" name="dia" value="G">Miércoles</label>
                                                    <label class="checkbox-inline"><input type="checkbox" name="dia" value="J">Jueves</label>
                                                    <label class="checkbox-inline"><input type="checkbox" name="dia" value="V">Viernes</label>
                                                </div>
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
                                    <div class="tab-pane" role="tabpanel" id="complete">
                                        <h3>Complete</h3>
                                        <p>You have successfully completed all steps.</p>
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
