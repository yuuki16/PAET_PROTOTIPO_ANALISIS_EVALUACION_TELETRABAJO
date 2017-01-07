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
var pfProcesoFinalizacion, trTrabajador, ttTeletrabajador;

$(document).ready(function () {
    $("#estados").hide();
    consultarProcesos();
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
        consultarProcesos();
    });
});

function redirect(teletrabajador)
{
    window.location = 'ADMINISTRAR_ESTADOS_FINALIZACION_JSP.jsp?teletrabajador=' + teletrabajador;
}

//teletrabajador
function consultarTeletrabajadorByTrabajador(trUsuario, nombre)
{
    $.ajax({
        async: false,
        url: 'TT_TELETRABAJADOR_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "trTrabajador",
            valor: trUsuario,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].ttEstado === "A") {
                        ttTeletrabajador = data[0].ttCodigo;
                        consultarProcesosByTeletrabajador(data[0].ttCodigo, nombre);
                    }
                }
            } else
            {
                mostrarAlerta("El trabajador seleccionado no es un teletrabajador.");
            }

        },
        type: 'POST',
        dataType: "json"
    });
}

function consultarTeletrabajadorByCodigo(fechaEntrada, teletrabajador)
{
    $.ajax({
        async: false,
        url: 'TT_TELETRABAJADOR_Servlet',
        data: {
            accion: "consultarTeletrabajadorByCodigo",
            ttCodigo: teletrabajador
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            consultarTrabajadorByUsuario(fechaEntrada, data.trTrabajador, teletrabajador);
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
            if (data.length > 0) {
                if (data[0].trEstado === "A") {
                    trTrabajador = data[0].trUsuario;
                    consultarTeletrabajadorByTrabajador(data[0].trUsuario, data[0].trNombre + ' ' + data[0].trApellido1 + ' ' + data[0].trApellido2);
                }
                else
                {
                    mostrarAlerta("La cédula ingresada no corresponde a ninguno de nuestros trabajadores activos.");
                }
            } else
            {
                mostrarAlerta("La cédula ingresada no corresponde a ninguno de nuestros trabajadores activos.");
            }
            ocultarModal("modalMensajes");
        },
        type: 'POST',
        dataType: "json"
    });
}

function consultarTrabajadorByUsuario(fecha, usuario, teletrabajador)
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
                    '  <h2><a onclick="consultarEstadosByTeletrabajador(\'' + teletrabajador + '\')">Revisar Proceso</a></h2>' +
                    ' </div>' +
                    ' </div>' +
                    '</article>'));
            ocultarModal("modalMensajes");
        },
        type: 'POST',
        dataType: "json"
    });

}

//proceso seguimiento
function consultarProcesos()
{
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los procesos de seguimiento");

    $.ajax({
        async: false,
        url: 'PF_PROCESO_FINALIZACION_Servlet',
        data: {
            accion: "consultarProcesosFinalizacion"
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // Sorting: typeof json === Array

            var sorted = data.sort(function (a, b) {
                var fecha1 = Date.parse(a.pfFecha);
                var fecha2 = Date.parse(b.pfFecha);

                if (fecha1 < fecha2) {
                    return 1;
                }
                if (fecha1 > fecha2) {
                    return -1;
                }

                return 0;
            });

            dibujarTodosProcesos(sorted);

        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarProcesos(dataProcesos, nombre)
{
    var procesoPendiente = false;

    if (dataProcesos.length > 0) {
        for (var i = 0; i < dataProcesos.length; i++) {
            if (dataProcesos[i].pfEstado === "P") {
                procesoPendiente = true;
            }
        }

        if (procesoPendiente) {
            for (var i = 0; i < dataProcesos.length; i++) {
                if (dataProcesos[i].esEstado === 61) {
                    $("#procesos").append($('<article class="timeline-entry">' +
                            '<div class="timeline-entry-inner">' +
                            '<div class="timeline-icon bg-secondary">' +
                            ' <i class="entypo-suitcase"></i>' +
                            '</div>' +
                            ' <div class="timeline-label">' +
                            '<p>' + nombre + '</p>' +
                            '<p>' + dataProcesos[i].pfFecha + '</p>' +
                            '  <h2><a onclick="consultarEstadosByTeletrabajador(\'' + ttTeletrabajador + '\')">Revisar Proceso</a></h2>' +
                            ' </div>' +
                            ' </div>' +
                            '</article>'));
                }
            }
        }

        $("#procesos").show();
    }
}

function dibujarTodosProcesos(dataProcesos)
{
    for (var i = 0; i < dataProcesos.length; i++) {
        if (dataProcesos[i].pfEstado === "P") {
            for (var j = 0; j < dataProcesos.length; j++) {
                if (dataProcesos[j].esEstado === 61 && dataProcesos[j].ttTeletrabajador === dataProcesos[i].ttTeletrabajador) {
                    consultarTeletrabajadorByCodigo(dataProcesos[j].pfFecha, dataProcesos[j].ttTeletrabajador);
                }
            }
        }
    }
}

function consultarProcesosByTeletrabajador(ttTeletrabajador, nombre)
{
    $.ajax({
        async: false,
        url: 'PF_PROCESO_FINALIZACION_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "ttTeletrabajador",
            valor: ttTeletrabajador,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            if (data.length > 0) {
                dibujarProcesos(data, nombre);
            }
            else
            {
                mostrarAlerta("El trabajador no posee ningún proceso de finalización.");
            }
        },
        type: 'POST',
        dataType: "json"
    });
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
        $("#estados").append($('<article class="timeline-entry">' +
                '<div class="timeline-entry-inner">' +
                '<div name="circulo" class="timeline-icon bg-gray" id="' + dataJson[i].esCodigo + 'Circulo"' +
                '<i class="entypo-suitcase"></i>' +
                '</div>' +
                '<div class="timeline-label" id="' + dataJson[i].esCodigo + '">' +
                '<p>' + dataJson[i].esDescripcion + '</p>' +
                '</div>' +
                '</div>' +
                '</article>'));
    }
}

function consultarEstadosByTeletrabajador(teletrabajador)
{
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los estados del proceso");

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
            pintarEstados(data, teletrabajador);
            
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

function pintarEstados(dataJson, teletrabajador)
{
    limpiarEstados();

    var element = document.getElementsByName("borrar"), index;

    for (index = element.length - 1; index >= 0; index--) {
        element[index].parentNode.removeChild(element[index]);
    }
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].pfEstado === "P") {
            $("#" + dataJson[i].esEstado + "Circulo").removeClass();
            $("#" + dataJson[i].esEstado + "Circulo").addClass("timeline-icon pending");
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Fecha de Entrada: ' + dataJson[i].pfFecha + '</p>');
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Pendiente</p>');
        } else if (dataJson[i].pfEstado === "F") {
            $("#" + dataJson[i].esEstado + "Circulo").removeClass();
            $("#" + dataJson[i].esEstado + "Circulo").addClass("timeline-icon finished");
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Fecha de Entrada: ' + dataJson[i].pfFecha + '</p>');
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Fecha Finalización: ' + dataJson[i].pfFechaAtendido + '</p>');
        }
    }

    var element = document.getElementById("AdministrarProceso");
    if (element) {
        element.parentNode.removeChild(element);
    }
    $("#estados").append('<button type="button" class="btn btn-info" id="AdministrarProceso" onclick="redirect(' + teletrabajador + ')">Administrar Proceso</button>');

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




