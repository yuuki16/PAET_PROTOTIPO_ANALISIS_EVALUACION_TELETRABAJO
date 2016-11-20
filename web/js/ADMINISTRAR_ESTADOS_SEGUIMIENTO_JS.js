/* 
 * Copyright (C) 2016 Michelle
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
var pgProcesoSeguimiento, ttTeletrabajador, trTrabajador, pgNumero, evResultado, evLogro, evEvaluacion;

$(document).ready(function () {
    //Genera el datapicker
    $('#fechaLimite').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#fechaLimite').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#fechaDesde').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#fechaHasta').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    consultarEstadosByProceso();
    //acciones barra proceso
    $('.nav-tabs > li a[title]').tooltip();

    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
        var $target = $(e.target);
        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    //acciones agregar/borrar fila
    var i = 1;
    $("#add_row").click(function () {
        $('#mt' + i).html('<td>' + parseInt(i + 1) + '</td>' +
                '<td><input type="text" id="descripcion' + i + '" style="width: 200px"></td>' +
                '<td><input type="number" class="cuanto" id="limite' + i + '" style="width: 50px"></td>' +
                '<td><input type="number" class="cuanto" name="peso" id="peso' + i + '" style="width: 50px"></td>' +
                '<td><input type="number" class="cuanto" id="limiteSobre' + i + '" style="width: 50px"></td>' +
                '<td><input type="number" name="pesoSobre" readonly="true" id="pesoSobre' + i + '" style="width: 50px; color: red"></td>' +
                '<td><input type="number" class="cuanto" id="limiteBajo' + i + '" style="width: 50px"></td>' +
                '<td><input type="number" name="pesoBajo" readonly="true" id="pesoBajo' + i + '" style="width: 50px; color: red"></td>' +
                '<td><select class="form-control" id="logica' + i + '" style="width: 90px">' +
                '<option value="MAS" selected="selected">Más es más</option>' +
                '<option value="MEN">Menos es más</option>' +
                '</select></td>' +
                '<td><input type="number" class="cuanto" id="logro' + i + '" style="width: 50px"></td>' +
                '<td><input type="number" name="porcentaje" readonly="true" id="porcentaje' + i + '" style="width: 50px; color: red"></td>');
        $('#tablaMetas').append('<tr id="mt' + (i + 1) + '"></tr>');
        i++;
    });

    $("#delete_row").click(function () {
        if (i > 1) {
            $("#mt" + (i - 1)).html('');
            i--;
        }
    });

    //evento botones avanzar proceso
    $("#guardar2").click(function () {

        if (validarDocumentacion("auditoria")) {
            guardarDocumentacion("auditoria");
            avanzarPgProcesoSeguimiento(9, 2);
        } else
        {
            alert("Debe adjuntar el archivo de auditoría para poder continuar.");
        }
    });

    $("#guardar3").click(function () {
        if (validarRecomendacion()) {
            guardarRecomendacion();
        } else
        {
            alert("Debe ingresar una recomendación para poder continuar.");
        }
    });

    $("#guardar4").click(function () {
        if (validarPeriodoEvaluacion()) {
            if (validarEvaluacion()) {
                if (validarMetas()) {
                    guardarEvaluacion();
                } else
                {
                    alert("Ingrese la información para todas las metas");
                }
            } else
            {
                alert("Genere la evaluación antes de continuar");
            }
        } else
        {
            alert("Ingrese un período para la evaluación");
        }
    });

    $('#auditoria').on('fileselect', function (event, numFiles, label) {
        $("#archivoauditoria").html();
        $("#archivoauditoria").html(label);
    });
});

$(document).on('change', '#auditoria', function () {
    var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect', [numFiles, label]);
});

$(document).on('change', '.cuanto', function () {
    var id = $(this).attr('id');
    var indice = id.replace('limite', '');
    var indice = indice.replace('Sobre', '');
    var indice = indice.replace('Bajo', '');
    var indice = indice.replace('peso', '');
    var indice = indice.replace('logro', '');
    var logroSobre, logroBajo, limiteSobre, limiteBajo, peso, logroAlcanzado, logro, limite;

    if ($("#descripcion" + indice).val().trim() !== "") {
        if ($("#limite" + indice).val().trim() !== "") {
            limite = $("#limite" + indice).val();
            if ($("#peso" + indice).val().trim() !== "") {
                peso = $("#peso" + indice).val();
                calcularTotalPeso();
                if ($("#limiteSobre" + indice).val().trim() !== "") {
                    limiteSobre = $("#limiteSobre" + indice).val();
                    if ($("#limiteBajo" + indice).val().trim() !== "") {
                        limiteBajo = $("#limiteBajo" + indice).val();
                        if ($("#logro" + indice).val().trim() !== "") {
                            logro = $("#logro" + indice).val();
                            if ($("#logica" + indice).val() === "MAS") {
                                logroSobre = parseInt(limiteSobre) / parseInt(limite) * parseInt(peso);
                                $("#pesoSobre" + indice).val(Math.round(logroSobre * 10) / 10);
                                calcularTotalLogroSobre();
                                logroBajo = parseInt(limiteBajo) / parseInt(limite) * parseInt(peso);
                                $("#pesoBajo" + indice).val(Math.round(logroBajo * 10) / 10);
                                calcularTotalLogroBajo();
                                logroAlcanzado = parseInt(logro) / parseInt(limite) * parseInt(peso);
                                $("#porcentaje" + indice).val(Math.round(logroAlcanzado * 10) / 10);
                                calcularTotalLogroAlcanzado();
                            } else if ($("#logica" + indice).val() === "MEN") {
                                logroSobre = ((parseInt(limite) - parseInt(limiteSobre)) / parseInt(limite) * parseInt(peso)) + parseInt(peso);
                                $("#pesoSobre" + indice).val(Math.round(logroSobre * 10) / 10);
                                logroBajo = ((parseInt(limite) - parseInt(limiteBajo)) / parseInt(limite) * parseInt(peso)) + parseInt(peso);
                                $("#pesoBajo" + indice).val(Math.round(logroBajo * 10) / 10);
                                logroAlcanzado = ((parseInt(limite) - parseInt(logro)) / parseInt(limite) * parseInt(peso)) + parseInt(peso);
                                $("#porcentaje" + indice).val(Math.round(logroAlcanzado * 10) / 10);
                            }
                        }
                    }
                }
            }
        }
    } else
    {
        alert("Para generar el evaluación de la meta ingresa su descripción.");
    }
});

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}

function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}

function mostrarMensaje(modal, classCss, msg, neg) {
//se le eliminan los estilos al mensaje
    $("#" + modal).removeClass();
    //se setean los estilos
    $("#" + modal).addClass(classCss);
    //se muestra la capa del mensaje con los parametros del metodo
    $("#" + modal).fadeIn("slow");
    $("#" + modal + "Neg").html(neg);
    $("#" + modal + "Text").html(msg);
    $("#" + modal + "Text").html(msg);
}

function ocultarMensaje() {
    $("#mensajeResult").removeClass();
    //se setean los estilos
    $("#mensajeResult").addClass("alert alert-success hiddenDiv");
}

function calcularTotalPeso()
{
    var pesos = document.getElementsByName('peso');
    var peso = 0, pesoTotal = 0, pesoSobreMin = 0, pesoAcepMax = 0;
    for (var i = 0; i < pesos.length; i++) {
        if (pesos[i].value.trim() !== "") {
            peso = pesos[i].value;
            pesoTotal = parseFloat(pesoTotal) + parseFloat(peso);
        }
    }

    $("#pesoTotal").val(pesoTotal);
    pesoSobreMin = parseFloat(pesoTotal) + 0.01;
    $("#minAlto").val(pesoSobreMin);
    $("#minEst").val(pesoTotal);
    pesoAcepMax = parseFloat(pesoTotal) - 0.01;
    $("#maxAcep").val(pesoAcepMax);
}

function calcularTotalLogroSobre()
{
    var pesos = document.getElementsByName('pesoSobre');
    var peso = 0, pesoTotal = 0, pesoAltoMax = 0;
    for (var i = 0; i < pesos.length; i++) {
        if (pesos[i].value.trim() !== "") {
            peso = pesos[i].value;
            pesoTotal = parseFloat(pesoTotal) + parseFloat(peso);
        }
    }

    $("#pesoSobreTotal").val(pesoTotal);
    $("#minSobre").val(pesoTotal);
    pesoAltoMax = parseFloat(pesoTotal) - 0.01;
    $("#maxAlto").val(pesoAltoMax);
}

function calcularTotalLogroBajo()
{
    var pesos = document.getElementsByName('pesoBajo');
    var peso = 0, pesoTotal = 0, pesoAcepMin = 0;
    for (var i = 0; i < pesos.length; i++) {
        if (pesos[i].value.trim() !== "") {
            peso = pesos[i].value;
            pesoTotal = parseFloat(pesoTotal) + parseFloat(peso);
        }
    }

    $("#pesoBajoTotal").val(pesoTotal);
    pesoAcepMin = parseFloat(pesoTotal) + 0.021;
    $("#minAcep").val(pesoAcepMin);
    $("#maxBajo").val(pesoTotal);
}

function calcularTotalLogroAlcanzado()
{
    var pesos = document.getElementsByName('porcentaje');
    var peso = 0, pesoTotal = 0;
    for (var i = 0; i < pesos.length; i++) {
        if (pesos[i].value.trim() !== "") {
            peso = pesos[i].value;
            pesoTotal = parseFloat(pesoTotal) + parseFloat(peso);
        }
    }

    $("#porcentajeTotal").val(Math.round(pesoTotal * 10) / 10);

    var colores = document.getElementsByClassName('colorResultado');
    var id;
    for (var i = 0; i < colores.length; i++) {
        colores[i].innerHTML = "";
    }

    pintarLogroAlcanzado();
}

function pintarLogroAlcanzado()
{
    var logroAlcanzado = $("#porcentajeTotal").val();
    var minSobre = $("#minSobre").val();
    var minAlto = $("#minAlto").val();
    var maxAlto = $("#maxAlto");
    var est = $("#minEst").val();
    var minAcep = $("#minAcep").val();
    var maxAcep = $("#maxAcep").val();
    var minBajo = $("#minBajo").val();
    var maxBajo = $("#maxBajo").val();

    if (parseFloat(logroAlcanzado) >= parseFloat(minSobre)) {
        $("#colorSobre").html(logroAlcanzado);
        $("#causayaccionmejora").hide();
    } else if (parseFloat(logroAlcanzado) > parseFloat(est) && parseFloat(logroAlcanzado) < parseFloat(minSobre)) {
        $("#colorAlto").html(logroAlcanzado);
        $("#causayaccionmejora").hide();
    } else if (parseFloat(logroAlcanzado) === parseFloat(est)) {
        $("#colorEst").html(logroAlcanzado);
        $("#causayaccionmejora").hide();
    } else if (parseFloat(logroAlcanzado) > parseFloat(maxBajo) && parseFloat(logroAlcanzado) < parseFloat(est)) {
        $("#colorAcep").html(logroAlcanzado);
        $("#causayaccionmejora").show();
    } else if (parseFloat(logroAlcanzado) <= parseFloat(maxBajo)) {
        $("#colorBajo").html(logroAlcanzado);
        $("#causayaccionmejora").show();
    }
}

function mostrarMensaje(modal, classCss, msg, neg) {
//se le eliminan los estilos al mensaje
    $("#" + modal).removeClass();
    //se setean los estilos
    $("#" + modal).addClass(classCss);
    //se muestra la capa del mensaje con los parametros del metodo
    $("#" + modal).fadeIn("slow");
    $("#" + modal + "Neg").html(neg);
    $("#" + modal + "Text").html(msg);
    $("#" + modal + "Text").html(msg);
}

function ocultarMensaje() {
    $("#mensajeResult").removeClass();
    //se setean los estilos
    $("#mensajeResult").addClass("alert alert-success hiddenDiv");
}

//estados
function consultarEstadosByProceso()
{
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los estados del proceso");
    $.ajax({
        async: false,
        url: 'ES_ESTADO_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "esProceso",
            valor: "G",
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            var sorted = data.sort(function (a, b) {
                if (a.esSecuencia > b.esSecuencia) {
                    return 1;
                }
                if (a.esSecuencia < b.esSecuencia) {
                    return -1;
                }

                return 0;
            });
            dibujarEstados(sorted);
            ocultarModal("modalMensajes");
        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarEstados(dataJson)
{
    for (var i = 0; i < dataJson.length; i++) {
        $("#estados").append($('<li role="presentation" class="disabled" id="' + dataJson[i].esCodigo + '">' +
                '<a href="#step' + dataJson[i].esSecuencia + '" data-toggle="tab" aria-controls="step' + dataJson[i].esSecuencia + '" role="tab" title="' + dataJson[i].esDescripcion + '">' +
                '<span class="round-tab">' +
                '<i class="glyphicon glyphicon-folder-open"></i>' +
                '</span>' +
                '</a>' +
                '</li>'));
    }

    var teletrabajador = getParameterByName('teletrabajador', null);
    ttTeletrabajador = teletrabajador;
    var numero = getParameterByName('numero', null);
    pgNumero = numero;
    consultarTeletrabajadorByCodigo(false);
    consultarEstadosByNumero(1);
}

function consultarEstadosByNumero(funcion)
{
    $.ajax({
        async: false,
        url: 'PG_PROCESO_SEGUIMIENTO_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "pgNumero",
            valor: pgNumero,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            switch (funcion)
            {
                case 1:
                    pintarEstadoPendiente(data);
                    break;
                case 2:
                    revisarProcesoSolicitud(data);
                    break;
                case 3:
                    consultarAnalisisPuestoByProcesoSolicitud(data);
                    break;
            }
        },
        type: 'POST',
        dataType: "json"
    });
}

function pintarEstadoPendiente(dataJson)
{
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].pgEstado === "P" && dataJson[i].ttTeletrabajador === parseInt(ttTeletrabajador)) {
            pgProcesoSeguimiento = dataJson[i].pgCodigo;
            $("#" + dataJson[i].esEstado).removeClass();
            $("#" + dataJson[i].esEstado).addClass("active");
            consultarEstadoByCodigo(dataJson[i].esEstado);
            $("#causayaccionmejora").hide();
        }
    }
}

function consultarEstadoByCodigo(esCodigo)
{
    $.ajax({
        async: false,
        url: 'ES_ESTADO_Servlet',
        data: {
            accion: "consultarEstadoByCodigo",
            esCodigo: esCodigo
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            $("#step" + data.esSecuencia).removeClass();
            $("#step" + data.esSecuencia).addClass("tab-pane active");
        },
        type: 'POST',
        dataType: "json"
    });
}

//proceso solicitud
function revisarProcesoSolicitud(dataJson)
{
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].esEstado === 2) {
            guardarAnalisisPuesto(dataJson[i].psCodigo);
        }
    }
}

function avanzarPgProcesoSeguimiento(estadoSiguiente, numeroObservacion)
{
    var observacion = $("#observacion" + numeroObservacion).val();

    $.ajax({
        async: false,
        url: 'PG_PROCESO_SEGUIMIENTO_Servlet',
        data: {
            accion: "avanzarProcesoSeguimiento",
            pgCodigo: pgProcesoSeguimiento,
            pgNumero: pgNumero,
            pgObservacion: observacion,
            pgEstadoNuevo: estadoSiguiente,
            ttTeletrabajador: ttTeletrabajador
        },
        error: function () { //si existe un error en la respuesta del ajax
            mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            pgProcesoSeguimiento = data;
            mostrarMensaje("mensajeResult", "alert alert-success", "Proceso avanzado exitosamente.", "Correcto!");
            var $active = $('.wizard .nav-tabs li.active');
            ($active).addClass("disabled");
            $active.next().removeClass('disabled');
            nextTab($active);
            ocultarMensaje();
        },
        type: 'POST'
    });
}

function modificarPgProcesoSeguimiento()
{
    var observacion = $("#observacion4").val();

    $.ajax({
        asyn: false,
        url: 'PG_PROCESO_SEGUIMIENTO_Servlet',
        data: {
            accion: "modificarProcesoSeguimiento",
            pgProcesoSeguimiento: pgProcesoSeguimiento,
            pgObservacion: observacion
        },
        error: function () { //si existe un error en la respuesta del ajax
            mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            window.location = 'ADMINISTRAR_PROCESO_SEGUIMIENTO_JSP.jsp';
        },
        type: 'POST'
    });
}

//documentacion
function guardarDocumentacion(nombreCampo)
{
    var datos = new FormData();
    datos.append('file', document.getElementById(nombreCampo).files[0]);
    datos.append('proceso', 'G');
    datos.append('psProcesoSolicitud', pgProcesoSeguimiento);
    $.ajax({
        asyn: false,
        url: 'DC_DOCUMENTACION_Servlet',
        processData: false,
        contentType: false,
        dataType: 'json',
        cache: false,
        data: datos,
        error: function () { //si existe un error en la respuesta del ajax

        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data

        },
        type: 'POST'
    });
}

function validarDocumentacion(archivo)
{
    var validacion = true;

    if (document.getElementById(archivo).files.length === 0) {
        validacion = false;
    }

    return validacion;
}

//teletrabajador
function consultarTeletrabajadorByCodigo(estado4)
{
    $.ajax({
        async: false,
        url: 'TT_TELETRABAJADOR_Servlet',
        data: {
            accion: "consultarTeletrabajadorByCodigo",
            ttCodigo: ttTeletrabajador
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            trTrabajador = data.trTrabajador;
            if (estado4) {
                guardarTeletrabajador(data.trTrabajador);
            }
        },
        type: 'POST',
        dataType: "json"
    });
}

function consultarTeletrabajadorByTrabajador()
{
    $.ajax({
        async: false,
        url: 'TT_TELETRABAJADOR_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "trTrabajador",
            valor: trTrabajador,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            for (var i = 0; i < data.length; i++) {
                if (data[i].ttEstado === "A") {
                    ttTeletrabajador = data[i].ttCodigo;
                }
            }
        },
        type: 'POST',
        dataType: "json"
    });
}

//recomendacion
function guardarRecomendacion()
{
    $.ajax({
        async: false,
        url: 'PG_PROCESO_SEGUIMIENTO_Servlet',
        data: {
            accion: "guardarRecomendacion",
            reFechaLimite: $("#fechaLimite").data('date'),
            reDescripcion: $("#descripcionrecomendacion").val(),
            pgProcesoSeguimiento: pgProcesoSeguimiento
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            avanzarPgProcesoSeguimiento(10, 3);
        },
        type: 'POST',
        dataType: "text"
    });
}

function validarRecomendacion()
{
    var validacion = true;

    if ($("#descripcionrecomendacion").val().trim() === "") {
        validacion = false;
    }

    if ($("#fechaLimite").data('date') === "") {
        validacion = false;
    }

    return validacion;
}

//evaluacion
function validarEvaluacion()
{
    var validacion = false;
    var colores = document.getElementsByClassName('colorResultado');
    var cuadrante;
    evResultado = 0;
    evLogro = "";

    for (var i = 0; i < colores.length; i++) {
        if (colores[i].innerHTML !== "") {
            cuadrante = colores[i].id;
            evResultado = colores[i].innerHTML;
            validacion = true;
        }
    }

    if (cuadrante === "colorBajo" || cuadrante === "colorAcep") {
        if ($("#causa").val().trim() === "") {
            alert("Ingrese una causa para el resultado obtenido");
        } else if ($("#recomendacion").val().trim() === "") {
            alert("Ingrese una recomendación para mejorar el resultado obtenido");
        }
        if (cuadrante === "colorBajo") {
            evLogro = "B";
        } else if (cuadrante === "colorAcep")
        {
            evLogro = "C";
        }
    } else if (cuadrante === "colorEst")
    {
        evLogro = "E";
    } else if (cuadrante === "colorAlto")
    {
        evLogro = "A";
    } else if (cuadrante === "colorSobre")
    {
        evLogro = "S";
    }

    return validacion;
}

function validarPeriodoEvaluacion()
{
    var validacion = true;
    var difference = 0;
    var fechaDesde, fechaHasta;

    if ($("#fechaDesde").data('date') === "") {
        validacion = false;
    }

    if ($("#fechaHasta").data('date') === "") {
        validacion = false;
    }

    return validacion;
}

function guardarEvaluacion()
{
    $.ajax({
        async: false,
        url: 'EV_EVALUACION_Servlet',
        data: {
            accion: "agregarEvaluacion",
            evFechaDesde: $("#fechaDesde").data('date'),
            evFechaHasta: $("#fechaHasta").data('date'),
            evLogro: evLogro,
            evResultado: evResultado,
            pgProcesoSeguimiento: pgProcesoSeguimiento
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            mostrarMensaje("mensajeResult", "alert alert-success", "Se agregó la evaluación exitosamente.", "Correcto!");
            evEvaluacion = data;
            guardarMetas(data);
            if (evLogro === "B" || evLogro === "C") {
                guardarCausa();
                guardarAccionMejora();
            }
            modificarPgProcesoSeguimiento();
        },
        type: 'POST',
        dataType: "text"
    });
}

//metas
function validarMetas()
{
    var validacion = true;
    var rowCount = $('#tablaMetas tr').length;
    var cantidadMetas = 0;
    for (var i = 0; i < rowCount - 3; i++) {
        if ($("#porcentaje" + i).val() === "") {
            validacion = false;
        }
    }
    return validacion;
}

function guardarMetas(evEvaluacion)
{
    var rowCount = $('#tablaMetas tr').length;
    var mtDescripcion, mtLimite, mtPeso, mtLimiteSobresaliente, mtLimiteBajo, mtPorcentajeSobresaliente, mtPorcentajeBajo, mtLogroAlcanzado, mtPorcentajeAlcanzado;

    for (var i = 0; i < rowCount - 3; i++) {
        mtDescripcion = $("#descripcion" + i).val();
        mtLimite = $("#limite" + i).val();
        mtPeso = $("#peso" + i).val();
        mtLimiteSobresaliente = $("#limiteSobre" + i).val();
        mtLimiteBajo = $("#limiteBajo" + i).val();
        mtPorcentajeSobresaliente = $("#pesoSobre" + i).val();
        mtPorcentajeBajo = $("#pesoBajo" + i).val();
        mtLogroAlcanzado = $("#logro" + i).val();
        mtPorcentajeBajo = $("#pesoBajo" + i).val();
        mtPorcentajeAlcanzado = $("#porcentaje" + i).val();
        $.ajax({
            async: false,
            url: 'EV_EVALUACION_Servlet',
            data: {
                accion: "agregarMeta",
                mtDescripcion: mtDescripcion,
                mtLimite: mtLimite,
                mtPeso: mtPeso,
                mtLimiteSobresaliente: mtLimiteSobresaliente,
                mtLimiteBajo: mtLimiteBajo,
                mtPorcentajeSobresaliente: mtPorcentajeSobresaliente,
                mtPorcentajeBajo: mtPorcentajeBajo,
                evEvaluacion: evEvaluacion,
                mtLogroAlcanzado: mtLogroAlcanzado,
                mtPorcentajeAlcanzado: mtPorcentajeAlcanzado
            },
            error: function () { //si existe un error en la respuesta del ajax
                cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
            },
            success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                var respuestaTxt = data.substring(2);
                var tipoRespuesta = data.substring(0, 2);
                if (tipoRespuesta === "C~") {
                    mostrarMensaje("mensajeResult", "alert alert-success", respuestaTxt, "Correcto!");
                } else {
                    if (tipoRespuesta === "E~") {
                        mostrarMensaje("mensajeResult", "alert alert-danger", respuestaTxt, "Error!");
                    } else {
                        mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador", "Error!");
                    }
                }
            },
            type: 'POST',
            dataType: "text"
        });
    }
}

//causa
function guardarCausa()
{
    $.ajax({
        async: false,
        url: 'EV_EVALUACION_Servlet',
        data: {
            accion: "agregarCausa",
            cuDescripcion: $("#causa").val(),
            evEvaluacion: evEvaluacion
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            mostrarMensaje("mensajeResult", "alert alert-success", "Se agregó la causa exitosamente.", "Correcto!");
        },
        type: 'POST',
        dataType: "text"
    });
}

//accion mejora
function guardarAccionMejora()
{
    $.ajax({
        async: false,
        url: 'EV_EVALUACION_Servlet',
        data: {
            accion: "agregarAccionMejora",
            amDescripcion: $("#recomendacion").val(),
            evEvaluacion: evEvaluacion
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            mostrarMensaje("mensajeResult", "alert alert-success", "Se agregó la acción de mejora exitosamente.", "Correcto!");
        },
        type: 'POST',
        dataType: "text"
    });
}