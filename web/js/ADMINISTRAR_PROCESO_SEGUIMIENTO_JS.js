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
var pgProcesoSeguimiento, trTrabajador, ttTeletrabajador;

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

function redirect(teletrabajador, numero)
{
    window.location = 'ADMINISTRAR_ESTADOS_SEGUIMIENTO_JSP.jsp?teletrabajador=' + teletrabajador + '&numero=' + numero;
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
                ttTeletrabajador = data[0].ttCodigo;
                consultarProcesosByTeletrabajador(data[0].ttCodigo, nombre);
            } else
            {
                mostrarAlerta("El trabajador seleccionado no es un teletrabajador.");
            }

        },
        type: 'POST',
        dataType: "json"
    });
}

function consultarTeletrabajadorByCodigo(fechaEntrada, teletrabajador, numero)
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
            consultarTrabajadorByUsuario(fechaEntrada, data.trTrabajador, numero, teletrabajador);
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
                // se oculta el mensaje de espera
                trTrabajador = data[0].trUsuario;
                consultarTeletrabajadorByTrabajador(data[0].trUsuario, data[0].trNombre + ' ' + data[0].trApellido1 + ' ' + data[0].trApellido2);
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

function consultarTrabajadorByUsuario(fecha, usuario, numero, teletrabajador)
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
                    '  <h2><a onclick="consultarEstadosByTeletrabajador(\'' + teletrabajador + '\', \'' + numero + '\')">Revisar Proceso</a></h2>' +
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
        url: 'PG_PROCESO_SEGUIMIENTO_Servlet',
        data: {
            accion: "consultarProcesosSeguimiento"
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // Sorting: typeof json === Array

            var sorted = data.sort(function (a, b) {
                var fecha1 = Date.parse(a.pgFecha);
                var fecha2 = Date.parse(b.pgFecha);
                
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
    var numero;
    var numeroMayor = 0;

    if (dataProcesos.length > 0) {
        for (var i = 0; i < dataProcesos.length; i++) {
            if (dataProcesos[i].pgNumero > numeroMayor) {
                numeroMayor = dataProcesos[i].pgNumero;
            }
            if (dataProcesos[i].pgEstado === "P") {
                procesoPendiente = true;
                numero = dataProcesos[i].pgNumero;
            }
        }

        if (procesoPendiente) {
            for (var i = 0; i < dataProcesos.length; i++) {
                if (dataProcesos[i].esEstado === 8 && dataProcesos[i].pgNumero === numero) {
                    $("#procesos").append($('<article class="timeline-entry">' +
                            '<div class="timeline-entry-inner">' +
                            '<div class="timeline-icon bg-secondary">' +
                            ' <i class="entypo-suitcase"></i>' +
                            '</div>' +
                            ' <div class="timeline-label">' +
                            '<p>' + nombre + '</p>' +
                            '<p>' + dataProcesos[i].pgFecha + '</p>' +
                            '  <h2><a onclick="consultarEstadosByTeletrabajador(\'' + ttTeletrabajador + '\', \'' + numero + '\')">Verificar Proceso</a></h2>' +
                            ' </div>' +
                            ' </div>' +
                            '</article>'));
                }
            }
        } else
        {
            $("#procesos").append($('<button class="btn btn-info" onclick="insertarProcesoSeguimiento(\'' + ttTeletrabajador + '\', \'' + numeroMayor + 1 + '\')" style="position: relative; left: 500px">Iniciar Proceso de Seguimiento</button>'));
        }

        $("#procesos").show();
    } else
    {
        $("#procesos").append($('<button class="btn btn-info" onclick="insertarProcesoSeguimiento(\'' + ttTeletrabajador + '\', \'' + 1 + '\')" style="position: relative; left: 500px">Iniciar Proceso de Seguimiento</button>'));
    }
}

function dibujarTodosProcesos(dataProcesos)
{    
    for (var i = 0; i < dataProcesos.length; i++) {
        if (dataProcesos[i].pgEstado === "P") {
            for (var j = 0; j < dataProcesos.length; j++) {
                if (dataProcesos[j].esEstado === 41 && dataProcesos[j].pgNumero === dataProcesos[i].pgNumero && dataProcesos[j].ttTeletrabajador === dataProcesos[i].ttTeletrabajador) {
                   consultarTeletrabajadorByCodigo(dataProcesos[j].pgFecha, dataProcesos[j].ttTeletrabajador, dataProcesos[j].pgNumero);
                }
            }
        }
    }
}

function consultarProcesosByTeletrabajador(ttTeletrabajador, nombre)
{
    $.ajax({
        async: false,
        url: 'PG_PROCESO_SEGUIMIENTO_Servlet',
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
            // se oculta el mensaje de espera
            dibujarProcesos(data, nombre);
        },
        type: 'POST',
        dataType: "json"
    });
}

function insertarProcesoSeguimiento(teletrabajador, numero)
{
    $.ajax({
        async: false,
        url: 'PG_PROCESO_SEGUIMIENTO_Servlet',
        data: {
            accion: "agregarProcesoSeguimiento",
            pgNumero: numero,
            ttTeletrabajador: teletrabajador
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // se oculta el mensaje de espera
            location.reload();
        },
        type: 'POST',
        dataType: "text"
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

function consultarEstadosByTeletrabajador(teletrabajador, numero)
{
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los estados del proceso");

    $.ajax({
        async: false,
        url: 'PG_PROCESO_SEGUIMIENTO_Servlet',
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
            for (var i = 0; i < data.length; i++) {
                if (data[i].pgNumero === parseInt(numero)) {
                    pintarEstados(data, teletrabajador, numero);
                }
            }

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

function pintarEstados(dataJson, teletrabajador, numero)
{
    limpiarEstados();

    var element = document.getElementsByName("borrar"), index;

    for (index = element.length - 1; index >= 0; index--) {
        element[index].parentNode.removeChild(element[index]);
    }
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].pgEstado === "P") {
            $("#" + dataJson[i].esEstado + "Circulo").removeClass();
            $("#" + dataJson[i].esEstado + "Circulo").addClass("timeline-icon pending");
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Fecha de Entrada: ' + dataJson[i].pgFecha + '</p>');
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Pendiente</p>');
        } else if (dataJson[i].pgEstado === "F") {
            $("#" + dataJson[i].esEstado + "Circulo").removeClass();
            $("#" + dataJson[i].esEstado + "Circulo").addClass("timeline-icon finished");
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Fecha de Entrada: ' + dataJson[i].pgFecha + '</p>');
            $("#" + dataJson[i].esEstado).append('<p name="borrar"> Fecha Finalización: ' + dataJson[i].pgFechaAtendido + '</p>');
        }
    }

    var element = document.getElementById("AdministrarProceso");
    if (element) {
        element.parentNode.removeChild(element);
    }
    $("#estados").append('<button type="button" class="btn btn-info" id="AdministrarProceso" onclick="redirect(' + teletrabajador + ', ' + numero + ')">Administrar Proceso</button>');

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




