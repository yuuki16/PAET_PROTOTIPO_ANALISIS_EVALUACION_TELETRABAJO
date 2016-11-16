<%-- 
    Document   : ADMINISTRAR_ESTADOS_SOLICITUD_JSP
    Created on : 11-nov-2016, 12:43:18
    Author     : Michelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Administrar Proceso de Seguimiento</title>

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
        <!-- <script src="js/ADMINISTRAR_ESTADOS_SOLICITUD_JS.js" type="text/javascript"></script>-->

    </head>
    <body>
        <%@ include file="/HeaderJSP.jsp" %>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading"><h3>Administrar Proceso de Seguimiento</h3></div>
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
                                        <h3>Archivos de Auditoría</h3>
                                        <p>Paso 2 de 4</p>
                                        <ul class="list-inline">
                                            <li>
                                                <h5>Adjuntar archivos de auditoría</h5>
                                            </li>
                                            <br>
                                            <li>
                                                <br>
                                                <label class="btn btn-info btn-file">
                                                    Adjuntar archivo <input type="file" style="display: none;" id="auditoria">
                                                </label>
                                                <label id="archivoauditoria"></label>
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
                                        <h3>Recomendación</h3>
                                        <p>Paso 3 de 4</p>
                                        <ul class="list-inline">
                                            <li>
                                                <h5>Emitir una recomendación</h5>
                                            </li>
                                            <br>
                                            <li>
                                                <label for="descripcionrecomendacion">Recomendación:</label>
                                                <input type="text" class="form-control" id="descripcionrecomendacion">
                                                <label for="fechaLímite">Fecha Límite:</label>
                                                <div id="fechaLímite" class="input-group date form_date" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
                                                    <input class="form-control" type="text" value="" readonly placeholder="dd/mm/aaaa" id="fechaLímiteText">
                                                    <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    </span>
                                                </div>
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
                                        <h3>Evaluación de metas</h3>
                                        <p>Paso 4 de 4</p>
                                        <ul class="list-inline">
                                            <li>
                                                <h5>Resultados del análisis</h5>
                                            </li>
                                            <br>
                                            <li>
                                                <div>
                                                    <h5>Definición de metas</h5>
                                                    <h6>Defina las del teletrabajador a evaluar</h6>
                                                    <table class="table" id="tablaMetas">
                                                        <thead>
                                                            <tr>
                                                                <th>Número de Meta</th>
                                                                <th>Meta</th>
                                                                <th>Cuanto de la Meta</th>
                                                                <th>% Peso Relativo</th>
                                                                <th>Logro Sobresaliente</th>
                                                                <th>% Logro Sobresaliente</th>
                                                                <th>Logro Bajo</th>
                                                                <th>% Logro Bajo</th>
                                                                <th>Lógica</th>
                                                                <th>Resultado Total Alcanzado</th>
                                                                <th>% Logro Alcanzado</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id= "metasDetalle">
                                                            <tr id="mt0">
                                                                <td>1<td>
                                                                <td><input type="text" id="limite0" style="width: 200px"></td>
                                                                <td><input type="text" id="peso0" style="width: 100px"></td>
                                                                <td><input type="text" id="limiteSobre0" style="width: 100px"></td>
                                                                <td><input type="text" id="pesoSobre0" style="width: 100px"></td>
                                                                <td><input type="text" id="limiteBajo0" style="width: 100px"></td>
                                                                <td><input type="text" id="pesoBajo0" style="width: 100px"></td>
                                                                <td><select class="form-control" id="logica">
                                                                        <option value="MAS" selected="selected">Más es más</option>
                                                                        <option value="MEN">Menos es más</option>
                                                                    </select></td>
                                                                <td><input type="text" id="logro0" style="width: 100px"></td>
                                                                <td><input type="text" id="porcentaje0" style="width: 100px"></td>
                                                            </tr>
                                                            <tr id='mt1'></tr>
                                                        </tbody>
                                                    </table>
                                                    <a id="add_row" class="btn btn-default pull-left">Agregar Fila</a><a id='delete_row' class="pull-right btn btn-default">Borrar Fila</a>
                                                </div>
                                            </li>
                                            <br>
                                            <br>
                                            <li>
                                                <h5>Cuadrante Alcanzado</h5>
                                                <table id="cuadrante">
                                                    <thead>
                                                        <tr>
                                                            <th>Tipo de Logro</th>
                                                            <th>Rango Mínimo</th>
                                                            <th>Rango Máximo</th>
                                                            <th>Cuadrante de Logro</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>Logro Sobresaliente</td>
                                                            <td><input type="text" id="minSobre" style="width: 100px" readonly="true"></td>
                                                            <td>></td>
                                                            <td><div id="colorSobre" class="colorResultado" style="color: #08c"></div></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Logro Alto</td>
                                                            <td><input type="text" id="minAlto" style="width: 100px" readonly="true"></td>
                                                            <td><input type="text" id="maxAlto" style="width: 100px" readonly="true"></td>
                                                            <td><div id="colorAlto" class="colorResultado" style="color: #f49e42"></div></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Logro Estándar de la Meta</td>
                                                            <td><input type="text" id="minEst" style="width: 100px" readonly="true"></td>
                                                            <td><input type="text" id="maxEst" style="width: 100px" readonly="true"></td>
                                                            <td><div id="colorEst" class="colorResultado" style="color: #83f442"></div></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Logro Aceptable</td>
                                                            <td><input type="text" id="minAcep" style="width: 100px" readonly="true"></td>
                                                            <td><input type="text" id="maxAcep" style="width: 100px" readonly="true"></td>
                                                            <td><div id="colorAcep" class="colorResultado" style="color: #f9f343"></div></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Logro Bajo</td>
                                                            <td><input type="text" id="minBajo" style="width: 100px" readonly="true"></td>
                                                            <td><input type="text" id="maxBajo" style="width: 100px" readonly="true"></td>
                                                            <td><div id="colorBajo" class="colorResultado" style="color: #f90000"></div></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </li>
                                            <br>
                                            <br>
                                            <li>
                                                <h5>Definición de los Cuadrantes</h5>
                                                <table>
                                                    <thead>
                                                        <tr>
                                                            <th>Cuadrante</th>
                                                            <th>Definición</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>Logro Sobresaliente</td>
                                                            <td>Resultado igual o superior al parámetro definido para logro sobresaliente de metas</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Logro Alto</td>
                                                            <td>Resultado mayor al de la meta, pero no alcanza el nivel de logro sobresaliente</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Logro Estándar de la Meta</td>
                                                            <td>Cuando el resultado es igual al establecido en la meta</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Logro Aceptable</td>
                                                            <td>Resultado menor al de la meta, pero mayor al nivel de logro bajo de meta</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Logro Bajo</td>
                                                            <td>Resultado igual o menor al parámetro definido para bajo logro de metas</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </li>
                                            <br>
                                            <br>
                                            <br>
                                            <li id="causayaccionmejora">
                                                <h5>Análisis de Causas y Acciones de Mejora</h5>
                                                <h6>Descripción de las Incidencias</h6>
                                            <h7>En caso de alcance de un Logro de Metas Bajo o Aceptable favor describa las incidencias o causas que lo propiciaron</h7>
                                            <div class="form-group">
                                                <textarea class="form-control" rows="5" id="causa"></textarea>
                                            </div>
                                            <h6>Descripción de la acción de mejora</h6>
                                            <h7>Favor describa las acciones de mejora que aplicará para el proximo periodo de evaluación</h7>
                                            <div class="form-group">
                                                <textarea class="form-control" rows="5" id="recomendacion"></textarea>
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
