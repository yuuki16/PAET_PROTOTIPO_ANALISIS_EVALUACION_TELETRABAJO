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
    consultarEstadosByProceso();

    //acciones barra proceso
    $('.nav-tabs > li a[title]').tooltip();

    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

        var $target = $(e.target);

        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    $(".next-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        ($active).addClass("disabled");
        $active.next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        prevTab($active);

    });

    consultarFactoresComplementarios();
    consultarCaracteristicas();

    //acciones agregar/borrar fila
    var i = 1;
    $("#add_row").click(function () {
        $('#act' + i).html('<td><input type="text" id="actividad' + i + '" style="width: 200px"></td>' +
                '<td><input type="radio" name="alineamiento' + i + '"></td>' +
                '<td><input type="radio" name="alineamiento' + i + '"></td>' +
                '<td><input type="radio" name="alineamiento' + i + '"></td>' +
                '<td><input type="radio" name="alineamiento' + i + '"></td>' +
                '<td><input type="checkbox" value="' + i + '" name="caracteristicas' + i + '" onclick="verificarActividad(' + i + ')"></td>' +
                '<td><input type="checkbox" value="' + i + '" name="caracteristicas' + i + '" onclick="verificarActividad(' + i + ')"></td>' +
                '<td><input type="checkbox" value="' + i + '" name="caracteristicas' + i + '" onclick="verificarActividad(' + i + ')"></td>' +
                '<td><input type="checkbox" value="' + i + '" name="caracteristicas' + i + '" onclick="verificarActividad(' + i + ')"></td>' +
                '<td><input type="text" id="resultado' + i + '" style="width: 50px" readonly="true"></td>');

        $('#tablaActividades').append('<tr id="act' + (i + 1) + '"></tr>');
        i++;
    });

    $("#delete_row").click(function () {
        if (i > 1) {
            $("#act" + (i - 1)).html('');
            i--;
        }
    });

    $("#generarAnalisis").click(function () {
        resultadoActividades();
    });

});

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}

function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
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

    var solicitud = getParameterByName('solicitud', null);
    consultarEstadosBySolicitud(solicitud);
}

function consultarEstadosBySolicitud(slSolicitud)
{
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
            pintarEstadoPendiente(data);
        },
        type: 'POST',
        dataType: "json"
    });
}

function pintarEstadoPendiente(dataJson)
{
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].psEstado === "P") {
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

//factores complementarios
function consultarFactoresComplementarios()
{
    $.ajax({
        url: 'FC_FACTOR_COMPLEMENTARIO_Servlet',
        data: {
            accion: "consultarFactoresComplementarios"
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarFactoresComplementarios(data);
        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarFactoresComplementarios(dataJson)
{
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].fcEstado === "A") {
            $("#factoresComplementarios").append('<tr>' +
                    '<td>' + dataJson[i].fcDescripcion + '</td>' +
                    '<td><input type="radio" name="' + dataJson[i].fcCodigo + '"></td>' +
                    '<td><input type="radio" name="' + dataJson[i].fcCodigo + '"></td>' +
                    '<td><input type="radio" name="' + dataJson[i].fcCodigo + '"></td>' +
                    '<td><input type="radio" name="' + dataJson[i].fcCodigo + '"></td>' +
                    '<td><input type="radio" name="' + dataJson[i].fcCodigo + '"></td>' +
                    '</tr>');
        }
    }
}

//caracteristicas
function consultarCaracteristicas()
{
    $.ajax({
        url: 'CA_CARACTERISTICA_Servlet',
        data: {
            accion: "consultarCaracteristicas"
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarCaracteristicas(data);
        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarCaracteristicas(dataJson)
{
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].caEstado === "A") {
            $("#caracteristicas").append('<th style="font-size: 10px" value="' + dataJson[i].caCodigo + '">' + dataJson[i].caDescripicion + '</th>');
        }
    }

    $("#caracteristicas").append('<th style="font-size: 10px" id="resultado"> Teletrabajable </th>');
}

//actividades
function verificarActividad(numeroCheckboxes)
{
    var checkboxes = document.getElementsByName("caracteristicas" + numeroCheckboxes);
    var todosSeleccionados = false;
    var seleccionados = 0;

    for (var i = 0, l = checkboxes.length; i < l; i++)
    {
        if (checkboxes[i].checked)
        {
            seleccionados++;
        }
    }

    if (seleccionados === checkboxes.length) {
        todosSeleccionados = true;
    }

    if ($("#actividad" + numeroCheckboxes).val() === "") {
        alert("Inserte la descripción de la actividad si desea que sea analizada.");
    } else
    {
        if ($('input[name=alineamiento' + numeroCheckboxes + ']:checked').length > 0) {
            if (todosSeleccionados) {
                $("#resultado" + numeroCheckboxes).val("Si");
            } else
            {
                $("#resultado" + numeroCheckboxes).val("No");
            }
        } else
        {
            alert("Escoja el alineamiento de la actividad si desea que sea analizada.");
        }

    }
}

function resultadoActividades()
{
    var rowCount = $('#tablaActividades tr').length;
    var cantidadActividades = 0;
    var actividadesTeletrabajables = 0;
    var resultadoActividades = 0;
    var resultadoActividadesFormat;

    if (rowCount - 3 === 0) {
        alert("Inserte más de una actividad");
    } 
    else
    {
        cantidadActividades = rowCount - 2;
        for (var i = 0; i < cantidadActividades; i++) {
            if ($("#resultado" + i).val() === "Si") {
                actividadesTeletrabajables++;
            }
        }
        
        resultadoActividades = actividadesTeletrabajables / cantidadActividades;
        resultadoActividadesFormat = (resultadoActividades * 100) + "%";
        $("#puntajeActividades").val(resultadoActividadesFormat);
    }
}