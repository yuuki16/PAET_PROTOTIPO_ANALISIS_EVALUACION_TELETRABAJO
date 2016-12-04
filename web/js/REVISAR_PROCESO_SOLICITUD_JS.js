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
$(document).ready(function () {
    $("#procesos").hide();
    $("#estados").hide();
    consultarEstadosByProceso();
});

$(function () {

    $("#btBusquedaTrCedula").click(function () {
        $("#procesos").html("");
        $("#estados").hide();
        ocultarAlerta();
        consultarTrabajadorByCedula($("#trCedula").val());
    });

    $("#btLimpiarBusqueda").click(function () {
        $("#procesos").hide();
        $("#estados").hide();
        ocultarAlerta();
    });
});

//solicitud
function consultarSolicitudesByTrabajador(trTrabajador)
{
    $.ajax({
        async: false,
        url: 'SL_SOLICITUD_Servlet',
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
            consultarProcesos(data);
        },
        type: 'POST',
        dataType: "json"
    });
}

//trabajador
function consultarTrabajadorByCedula(trCedula) {

    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los procesos del trabajador");

    $.ajax({
        async: false,
        url: 'TR_TRABAJADOR_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "trCedula",
            valor: trCedula,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            if (data.length > 0) {
                consultarSolicitudesByTrabajador(data[0].trUsuario);
            } else
            {
                mostrarAlerta("La cédula proporcionada no corresponde a ninguno de nuestros clientes.");
            }
            ocultarModal("modalMensajes");

        },
        type: 'POST',
        dataType: "json"
    });
}

//proceso solicitud
function consultarProcesos(dataSolicitudes)
{
    $.ajax({
        async: false,
        url: 'PS_PROCESO_SOLICITUD_Servlet',
        data: {
            accion: "consultarProcesosSolicitud"
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // Sorting: typeof json === Array

            var sorted = data.sort(function (a, b) {
                if (a.psFechaEntrada < b.psFechaEntrada) {
                    return 1;
                }
                if (a.psFechaEntrada > b.psFechaEntrada) {
                    return -1;
                }

                return 0;
            });

            dibujarProcesos(sorted, dataSolicitudes);
        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarProcesos(dataProcesos, dataSolicitudes)
{

    if (dataSolicitudes.length > 0) {

        for (var i = 0; i < dataProcesos.length; i++) {
            for (var j = 0; j < dataSolicitudes.length; j++) {
                if (dataProcesos[i].slSolicitud === dataSolicitudes[j].slCodigo) {
                    if (dataProcesos[i].esEstado === 1) {
                        $("#procesos").append($('<article class="timeline-entry">' +
                                '<div class="timeline-entry-inner">' +
                                '<div class="timeline-icon bg-secondary">' +
                                ' <i class="entypo-suitcase"></i>' +
                                '</div>' +
                                ' <div class="timeline-label">' +
                                '<p>' + dataProcesos[i].psFechaEntrada + '</p>' +
                                '<br>' +
                                '  <h2><a onclick="consultarEstadosByProcesoSolicitud(\'' + dataProcesos[i].slSolicitud + '\')">Verificar Proceso</a></h2>' +
                                ' </div>' +
                                ' </div>' +
                                '</article>'));
                    }
                }
            }
        }

        $("#procesos").show();
    } else
    {
        alert("El trabajador no posee ningún proceso de solicitud.");
    }
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
            valor: "S",
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarEstados(data);
            ocultarModal("modalMensajes");
        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarEstados(dataJson)
{
    var secuencia = 1;
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].esSecuencia === secuencia) {
            $("#estados").append($('<article class="timeline-entry">' +
                    '<div class="timeline-entry-inner">' +
                    '<div name="circulo" class="timeline-icon bg-gray" id="' + dataJson[i].esSecuencia + 'Circulo"' +
                    '<i class="entypo-suitcase"></i>' +
                    '</div>' +
                    '<div class="timeline-label" id="' + dataJson[i].esSecuencia + '">' +
                    '<p>' + dataJson[i].esDescripcion + '</p>' +
                    '</div>' +
                    '</div>' +
                    '</article>'));
            secuencia++;
        }
    }
}

function limpiarEstados()
{
    var circulos = document.getElementsByName("circulo");
    for (i = circulos.length - 1; i >= 0; i--) {
        circulos[i].className = "";
        circulos[i].className = "timeline-icon bg-gray";
    }
}

function consultarEstadosByProcesoSolicitud(slSolicitud)
{
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los estados del proceso");

    $.ajax({
        async: false,
        url: 'PS_PROCESO_SOLICITUD_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "slSolicitud",
            valor: slSolicitud,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // se oculta el mensaje de espera

            pintarEstados(data);
            ocultarModal("modalMensajes");
        },
        type: 'POST',
        dataType: "json"
    });
}

function pintarEstados(dataJson)
{
    limpiarEstados();

    var element = document.getElementsByName("borrar"), index;

    for (index = element.length - 1; index >= 0; index--) {
        element[index].parentNode.removeChild(element[index]);
    }
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].psEstado === "P") {
            $("#" + dataJson[i].esEstado + "Circulo").removeClass();
            $("#" + dataJson[i].esEstado + "Circulo").addClass("timeline-icon pending");
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Fecha de Entrada: ' + dataJson[i].psFechaEntrada + '</p>');
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Pendiente</p>');
        } else if (dataJson[i].psEstado === "F") {
            $("#" + dataJson[i].esEstado + "Circulo").removeClass();
            $("#" + dataJson[i].esEstado + "Circulo").addClass("timeline-icon finished");
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Fecha de Entrada: ' + dataJson[i].psFechaEntrada + '</p>');
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Fecha Finalización: ' + dataJson[i].psFechaAtendido + '</p>');
        }
    }

    $("#estados").show();
}

function mostrarAlerta(mensaje)
{
    $("#alert").css("display", "block");
    $("#alertMsg").html("");
    $("#alertMsg").html(mensaje);
}

function ocultarAlerta()
{
     $("#alert").css("display", "none");
    $("#alertMsg").html("");
}

