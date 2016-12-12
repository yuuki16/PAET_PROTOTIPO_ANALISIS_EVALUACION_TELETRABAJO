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
    $("#estados").hide();
    consultarProcesos(null, false, null);
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
        $("#procesos").html("");
        $("#estados").hide();
        ocultarAlerta();
        consultarProcesos(null, false, null);
    });
});

function redirect(solicitud)
{
    window.location = 'ADMINISTRAR_ESTADOS_SOLICITUD_JSP.jsp?solicitud=' + solicitud;
}

//solicitud
function consultarSolicitudesByTrabajador(trTrabajador, nombre)
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
            consultarProcesos(data, true, nombre);
        },
        type: 'POST',
        dataType: "json"
    });
}

//trabajador
function consultarTrabajadorByCedula(trCedula)
{

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
            // se oculta el mensaje de espera
            if (data.length > 0) {
                consultarSolicitudesByTrabajador(data[0].trUsuario, data[0].trNombre + ' ' + data[0].trApellido1 + ' ' + data[0].trApellido2);
            } else
            {
                mostrarAlerta("La cédula ingresada no corresponde a ninguno de nuestros trabajadores.");
            }

            ocultarModal("modalMensajes");
        },
        type: 'POST',
        dataType: "json"
    });
}

function consultarTrabajadorBySolicitud(fechaEntrada, solicitud)
{
    $.ajax({
        async: false,
        url: 'SL_SOLICITUD_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "slCodigo",
            valor: solicitud,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            consultarTrabajadorByUsuario(fechaEntrada, solicitud, data[0].trTrabajador);
        },
        type: 'POST',
        dataType: "json"
    });
}

function consultarTrabajadorByUsuario(fecha, solicitud, usuario)
{
    $.ajax({
        async: false,
        url: 'TR_TRABAJADOR_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "trUsuario",
            valor: usuario,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            $("#procesos").append($('<article class="timeline-entry">' +
                    '<div class="timeline-entry-inner">' +
                    '<div class="timeline-icon bg-secondary">' +
                    ' <i class="entypo-suitcase"></i>' +
                    '</div>' +
                    ' <div class="timeline-label">' +
                    '<p>' + data[0].trNombre + ' ' + data[0].trApellido1 + ' ' + data[0].trApellido2 + '</p>' +
                    '<p>' + fecha + '</p>' +
                    '<br>' +
                    '  <h2><a onclick="consultarEstadosByProcesoSolicitud(\'' + solicitud + '\')">Revisar Proceso</a></h2>' +
                    ' </div>' +
                    ' </div>' +
                    '</article>'));
            ocultarModal("modalMensajes");
        },
        type: 'POST',
        dataType: "json"
    });

}

//proceso solicitud
function consultarProcesos(dataSolicitudes, porCedula, nombre)
{
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los procesos de solicitud");

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
            var sorted = data.sort(function (a, b) {
                var fecha1 = Date.parse(a.psFechaEntrada);
                var fecha2 = Date.parse(b.psFechaEntrada);

                if (fecha1 < fecha2) {
                    return 1;
                }
                if (fecha1 > fecha2) {
                    return -1;
                }

                return 0;
            }
            );
            if (porCedula) {
                dibujarProcesos(sorted, dataSolicitudes, nombre);
            } else
            {
                dibujarTodosProcesos(sorted);
            }


        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarProcesos(dataProcesos, dataSolicitudes, nombre)
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
                                '<p>' + nombre + '</p>' +
                                '<p>' + dataProcesos[i].psFechaEntrada + '</p>' +
                                '<br>' +
                                '  <h2><a onclick="consultarEstadosByProcesoSolicitud(\'' + dataProcesos[i].slSolicitud + '\')">Revisar Proceso</a></h2>' +
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
        mostrarAlerta("El trabajador no posee ningún proceso de solicitud.");
    }
}

function dibujarTodosProcesos(dataProcesos)
{
    for (var i = 0; i < dataProcesos.length; i++) {
        if (dataProcesos[i].psEstado === "P") {
            for (var j = 0; j < dataProcesos.length; j++) {
                if (dataProcesos[j].esEstado === 1 && dataProcesos[j].slSolicitud === dataProcesos[i].slSolicitud) {
                    consultarTrabajadorBySolicitud(dataProcesos[i].psFechaEntrada, dataProcesos[i].slSolicitud);
                }
            }
        }
    }
}

//estados
function consultarEstadosByProceso()
{
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
            pintarEstados(data, slSolicitud);
            ocultarModal("modalMensajes");
        },
        type: 'POST',
        dataType: "json"
    });
}

function limpiarEstados()
{
    var circulos = document.getElementsByName("circulo");
    for (i = circulos.length - 1; i >= 0; i--) {
        circulos[i].className = "";
        circulos[i].className = "timeline-icon bg-gray";
    }
}

function pintarEstados(dataJson, solicitud)
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

    var element = document.getElementById("AdministrarProceso");
    if (element) {
        element.parentNode.removeChild(element);
    }
    $("#estados").append('<button type="button" class="btn btn-info" id="AdministrarProceso" onclick="redirect(' + solicitud + ')">Administrar Solicitud</button>');

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



