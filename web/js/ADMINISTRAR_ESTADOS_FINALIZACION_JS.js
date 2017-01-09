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
var pfProcesoFinalizacion, ttTeletrabajador;

$(document).ready(function () {
    consultarEstadosByProceso();
    //acciones barra proceso
    $('.nav-tabs > li a[title]').tooltip();

    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
        var $target = $(e.target);
        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    //evento botones avanzar proceso
    $("#guardar2").click(function () {
        if (validarDocumentacion("nota")) {
            guardarDocumentacion("nota");
            //avanzarPfProcesoFinalizacion(12, 2);
        } else
        {
            alert("Debe adjuntar la nota de finalización de teletrabajo para poder continuar.");
        }
    });

    $("#guardar3").click(function () {
        avanzarPfProcesoFinalizacion(13, 3);
    });

    $("#guardar4").click(function () {
        if (validarEspacioFisico()) {
            avanzarPfProcesoFinalizacion(14, 4);
        } else
        {
            alert("Ingrese el espacio físico asignado.");
        }
    });

    $("#guardar5").click(function () {
        if (validarDocumentacion("encuesta")) {
            guardarDocumentacion("encuesta");
            modificarPfProcesoFinalizacion();
        } else
        {
            alert("Debe adjuntar la encuesta para poder continuar.");
        }
    });

    $('#nota').on('fileselect', function (event, numFiles, label) {
        $("#archivonota").html();
        $("#archivonota").html(label);
    });
    
    $('#encuesta').on('fileselect', function (event, numFiles, label) {
        $("#archivoencuesta").html();
        $("#archivoencuesta").html(label);
    });
});

$(document).on('change', '#nota', function () {
    var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect', [numFiles, label]);
});

$(document).on('change', '#encuesta', function () {
    var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect', [numFiles, label]);
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
            valor: "F",
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
    consultarEstadosByTeletrabajador(ttTeletrabajador);
}

function consultarEstadosByTeletrabajador(teletrabajador)
{
    $.ajax({
        async: false,
        url: 'PF_PROCESO_FINALIZACION_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "ttTeletrabajador",
            valor: teletrabajador,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
           pintarEstadoPendiente(data);
        },
        type: 'POST',
        dataType: "json"
    });
}

function pintarEstadoPendiente(dataJson)
{
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].pfEstado === "P" && dataJson[i].ttTeletrabajador === parseInt(ttTeletrabajador)) {
            pfProcesoFinalizacion = dataJson[i].pfCodigo;
            $("#" + dataJson[i].esEstado).removeClass();
            $("#" + dataJson[i].esEstado).addClass("active");
            consultarEstadoByCodigo(dataJson[i].esEstado);
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

function avanzarPfProcesoFinalizacion(estadoSiguiente, numeroObservacion)
{
    var observacion = $("#observacion" + numeroObservacion).val();

    $.ajax({
        async: false,
        url: 'PF_PROCESO_FINALIZACION_Servlet',
        data: {
            accion: "avanzarProcesoFinalizacion",
            pfCodigo: pfProcesoFinalizacion,
            pfObservacion: observacion,
            pfEstadoNuevo: estadoSiguiente,
            ttTeletrabajador: ttTeletrabajador
        },
        error: function () { //si existe un error en la respuesta del ajax
            mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            pfProcesoFinalizacion = data;
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

function modificarPfProcesoFinalizacion()
{
    var observacion = $("#observacion5").val();

    $.ajax({
        asyn: false,
        url: 'PF_PROCESO_FINALIZACION_Servlet',
        data: {
            accion: "modificarProcesoFinalizacion",
            pfProcesoFinalizacion: pfProcesoFinalizacion,
            pfObservacion: observacion
        },
        error: function () { //si existe un error en la respuesta del ajax
            mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            window.location = 'ADMINISTRAR_PROCESO_FINALIZACION_JSP.jsp';
        },
        type: 'POST'
    });
}

//documentacion
function guardarDocumentacion(nombreCampo)
{
    var datos = new FormData();
    datos.append('file', document.getElementById(nombreCampo).files[0]);
    datos.append('proceso', 'F');
    datos.append('psProcesoSolicitud', pfProcesoFinalizacion);
    $.ajax({
        asyn: false,
        url: 'DO_DOCUMENTO_Servlet',
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

function validarEspacioFisico()
{
    var validacion = true;
    
    if ($("#espacioFisico").val() === "") {
        validacion = false;
    }
    
    return validacion;
}

