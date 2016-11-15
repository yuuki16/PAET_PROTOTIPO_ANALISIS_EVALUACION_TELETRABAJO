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
var psProcesoSolicitud, slSolicitud, trTrabajador, ttTeletrabajador;

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

    //acciones agregar/borrar fila
    var i = 1;
    $("#add_row").click(function () {
        $('#act' + i).html('<td><input type="text" id="actividad' + i + '" style="width: 200px"></td>' +
                '<td><input type="radio" name="alineamiento' + i + '" value="A"></td>' +
                '<td><input type="radio" name="alineamiento' + i + '" value="N"></td>' +
                '<td><input type="radio" name="alineamiento' + i + '" value="D"></td>' +
                '<td><input type="radio" name="alineamiento' + i + '" value="V"></td>' +
                '<td><input type="checkbox" value="1" name="caracteristicas' + i + '" onclick="verificarActividad(' + i + ')"></td>' +
                '<td><input type="checkbox" value="2" name="caracteristicas' + i + '" onclick="verificarActividad(' + i + ')"></td>' +
                '<td><input type="checkbox" value="21" name="caracteristicas' + i + '" onclick="verificarActividad(' + i + ')"></td>' +
                '<td><input type="checkbox" value="22" name="caracteristicas' + i + '" onclick="verificarActividad(' + i + ')"></td>' +
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
        resultadoFactores();
    });

    $("#aprobarSolicitud").click(function () {
        $("#diasTeletrabajo").show();
        $("#labelDenegada").html("");
        $("#resultadoSolicitud").val("A");
    });

    $("#negarSolicitud").click(function () {
        $("#labelDenegada").html("La solicitud será denegada.");
        $("#diasTeletrabajo").hide();
        $("#resultadoSolicitud").val("R");
    });

    $("#salarioTotal").change(function () {
        var salarioTotal, salarioLey, salarioTeletrabajo, porciento;

        salarioTotal = $("#salarioTotal").val();
        porciento = salarioTotal * 0.1524;
        salarioLey = parseInt(salarioTotal - porciento);
        $("#salarioRebajosLey").val(salarioLey);
        salarioTeletrabajo = parseInt(salarioLey / 20 * $("#diasTeletrabajando").val());
        $("#salarioTeletrabajo").val(salarioTeletrabajo);
    });

    //evento botones avanzar proceso
    $("#guardar2").click(function () {
        var solicitud = getParameterByName('solicitud', null);
        if (solicitud) {
            if (validarDocumentacion("notaGerente")) {
                consultarEstadosBySolicitud(2);

            } else
            {
                alert("Debe adjuntar la nota del gerente para continuar");
            }

        }
    });

    $("#guardar3").click(function () {
        if (validarDocumentacion("documentoPsicometrico")) {
            if (validarDocumentacion("documentoPsicosocial")) {
                guardarDocumentacion("documentoPsicometrico");
                guardarDocumentacion("documentoPsicosocial");
                avanzarPsProcesoSolicitud(4, 3);
            } else
            {
                alert("Debe adjuntar el resultado del análisis psicosocial para poder continuar.");
            }
        } else
        {
            alert("Debe adjuntar el resultado del análisis psicométrico para poder continuar.");
        }
    });

    $("#guardar4").click(function () {
        if (validarAprobacion()) {
            if (($("#resultadoSolicitud").val() === "A" && validarDias()) || $("#resultadoSolicitud").val() === "R") {
                modificarSolicitud();
            } else
            {
                alert("Debe seleccionar al menos un día de teletrabajo.");
            }
        } else
        {
            alert("Debe de aprobar o denegar la solicitud antes de continuar.");
        }
    });

    $("#guardar5").click(function () {
        if (validarDocumentacion("requerimientos")) {
            guardarDocumentacion("requerimientos");
            avanzarPsProcesoSolicitud(6, 5);
        } else
        {
            alert("Debe de adjuntar el formulario de requerimientos tecnológicos antes de continuar.");
        }
    });

    $("#guardar6").click(function () {
        if (validarDocumentacion("visita")) {
            if (validarCalculoAhorro()) {
                guardarCalculoAhorro();
            } else
            {
                alert("Debe ingresar la información correspondiente al cálculo de ahorro para poder continuar.");
            }
        } else
        {
            alert("Debe de adjuntar el documento de la visita realizada antes de continuar.");
        }
    });
    
    $("#guardar7").click(function () {
        if (validarDocumentacion("adenda")) {
            guardarDocumentacion("adenda");
            modificarPsProcesoSolicitud(7);
            window.location = 'ADMINISTRAR_PROCESO_SOLICITUD_JSP.jsp';
        } else
        {
        alert("Debe de adjuntar la adenda firmada para finalizar el proceso.");
        }
    });

    //previsualizar archivo adjuntado
    $('#notaGerente').on('fileselect', function (event, numFiles, label) {
        $("#archivo").html();
        $("#archivo").html(label);
    });

    $('#documentoPsicometrico').on('fileselect', function (event, numFiles, label) {
        $("#archivoPsicometrico").html();
        $("#archivoPsicometrico").html(label);
    });

    $('#documentoPsicosocial').on('fileselect', function (event, numFiles, label) {
        $("#archivoPsicosocial").html();
        $("#archivoPsicosocial").html(label);
    });

    $('#requerimientos').on('fileselect', function (event, numFiles, label) {
        $("#archivorequerimientos").html();
        $("#archivorequerimientos").html(label);
    });

    $('#visita').on('fileselect', function (event, numFiles, label) {
        $("#archivoVisita").html();
        $("#archivoVisita").html(label);
    });
    
    $('#adenda').on('fileselect', function (event, numFiles, label) {
        $("#archivoadenda").html();
        $("#archivoadenda").html(label);
    });
});

$(document).on('change', '#notaGerente', function () {
    var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect', [numFiles, label]);
});

$(document).on('change', '#documentoPsicometrico', function () {
    var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect', [numFiles, label]);
});

$(document).on('change', '#documentoPsicosocial', function () {
    var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect', [numFiles, label]);
});

$(document).on('change', '#requerimientos', function () {
    var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect', [numFiles, label]);
});

$(document).on('change', '#visita', function () {
    var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect', [numFiles, label]);
});

$(document).on('change', '#adenda', function () {
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

function validarAprobacion()
{
    var validacion = true;

    if ($("#resultadoSolicitud").val() === "") {
        validacion = false;
    }

    return validacion;
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
    slSolicitud = solicitud;
    consultarSolicitudByCodigo(false);
    consultarEstadosBySolicitud(1);
}

function consultarEstadosBySolicitud(funcion)
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
        if (dataJson[i].psEstado === "P") {
            psProcesoSolicitud = dataJson[i].psCodigo;
            $("#" + dataJson[i].esEstado).removeClass();
            $("#" + dataJson[i].esEstado).addClass("active");
            consultarEstadoByCodigo(dataJson[i].esEstado);
            if (dataJson[i].esEstado === 2) {
                consultarFactoresComplementarios();
                consultarCaracteristicas();
            } else if (dataJson[i].esEstado === 4) {
                consultarEstadosBySolicitud(3);
                $("#diasTeletrabajo").hide();
            } else if (dataJson[i].esEstado > 4) {
                consultarTeletrabajadorByTrabajador();
                if (dataJson[i].esEstado === 6) {
                    consultarDiasTeletrabajadorByTeletrabajador();
                }
            }
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
    var nombre = 0;
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].fcEstado === "A") {
            $("#factoresComplementarios").append('<tr>' +
                    '<td name="' + dataJson[i].fcCodigo + '" id="descripcion' + nombre + '">' + dataJson[i].fcDescripcion + '</td>' +
                    '<td><input type="radio" name="factor' + nombre + '" value="20"></td>' +
                    '<td><input type="radio" name="factor' + nombre + '" value="40"></td>' +
                    '<td><input type="radio" name="factor' + nombre + '" value="60"></td>' +
                    '<td><input type="radio" name="factor' + nombre + '" value="80"></td>' +
                    '<td><input type="radio" name="factor' + nombre + '" value="100"></td>' +
                    '</tr>');
            nombre++;
        }
    }
}

function resultadoFactores()
{
    var rowCount = $('#tablaFactores tr').length;
    var todos = false;
    var sumatoria = 0;
    var resultadoFactores = 0;
    var cantidadFactores = rowCount - 1;
    for (var i = 0; i < cantidadFactores; i++) {
        if ($('input[name=factor' + i + ']:checked').length > 0) {
            todos = true;
        } else
        {
            todos = false;
            alert("Seleccione una opción para todos los factores.");
            break;
        }
    }

    if (todos) {
        for (var i = 0; i < cantidadFactores; i++) {
            sumatoria += parseInt($('input[name=factor' + i + ']:checked').val());
        }

        resultadoFactores = sumatoria / cantidadFactores;
        $("#puntajeFactores").val(resultadoFactores);
        if (resultadoFactores > 75) {
            $("#puntajeFactoresColor").css("background", "#42f448");
            $("#recomendacionFactores").empty();
            $("#recomendacionFactores").append("Los aspectos de gestión relacionados con el puesto FAVORECEN la aplicación de la MODALIDAD DE TELETRABAJO");
        } else if (resultadoFactores < 25) {
            $("#puntajeFactoresColor").css("background", "#e01a1a");
            $("#recomendacionFactores").empty();
            $("#recomendacionFactores").append("Los aspectos de gestión relacionados con el puesto, evidencian la necesidad de aplicar MEJORAS para la aplicación de la MODALIDAD DE TELETRABAJO");
        } else
        {
            $("#puntajeFactoresColor").css("background", "#f7f71b");
            $("#recomendacionFactores").empty();
            $("#recomendacionFactores").append("Los aspectos de gestión relacionados con el puesto indican SUSCEPTIBILIDAD de aplicar la MODALIDAD DE TELETRABAJO");
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
    } else
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
        if (resultadoActividades === 1) {
            $("#puntajeActividadesColor").css("background", "#42f448");
            $("#modalidad").val("TOTAL");
            $("#dias").val("5 DÍAS");
            $("#tipoPuesto").val("ES TELETRABAJABLE");
        } else if (resultadoActividades < 0.2) {
            $("#puntajeActividadesColor").css("background", "#e01a1a");
            $("#modalidad").val("N/A");
            $("#dias").val("0 DÍAS");
            $("#tipoPuesto").val("NO ES TELETRABAJABLE");
        } else
        {
            $("#puntajeActividadesColor").css("background", "#f7f71b");
            $("#modalidad").val("PARCIAL");
            $("#tipoPuesto").val("ES TELETRABAJABLE");
            if (resultadoActividades >= 0.2 && resultadoActividades < 0.4) {
                $("#dias").val("1 DÍA");
            } else if (resultadoActividades >= 0.4 && resultadoActividades < 0.6) {
                $("#dias").val("2 DÍAS");
            } else if (resultadoActividades >= 0.6 && resultadoActividades < 0.8) {
                $("#dias").val("3 DÍAS");
            } else if (resultadoActividades >= 0.8 && resultadoActividades < 1) {
                $("#dias").val("4 DÍAS");
            }
        }
    }
}

function guardarActividades(anCodigo)
{
    var rowCount = $('#tablaActividades tr').length;
    var alineamiento;
    var acCodigo;
    for (var i = 0; i < rowCount - 1; i++) {
        alineamiento = $('input[name=alineamiento' + i + ']:checked').val();
        $.ajax({
            async: false,
            url: 'AC_ACTIVIDAD_Servlet',
            data: {
                accion: "agregarActividad",
                acDescripcion: $("#actividad" + i).val(),
                acAlineamiento: alineamiento
            },
            error: function () { //si existe un error en la respuesta del ajax
                mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
            },
            success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                acCodigo = data;
                guardarActividadAnalisis(acCodigo, anCodigo);
                guardarActividadCaracteristica(acCodigo, i);
            },
            type: 'POST'
        });
    }
}

//analisis puesto
function guardarAnalisisPuesto(psProcesoSolicitud)
{
    var puntajeActividades, diasRecomendados, modalidad, tipoPuesto;
    var anCodigo;
    if (validarAnalisisPuesto()) {

        puntajeActividades = $("#puntajeActividades").val();
        puntajeActividades = puntajeActividades.slice(0, 3);
        puntajeActividades = puntajeActividades.replace('.', '');
        puntajeActividades = puntajeActividades.replace('%', '');
        diasRecomendados = $("#dias").val();
        diasRecomendados = diasRecomendados.slice(0, 1);
        modalidad = $("#modalidad").val();
        if (modalidad === "TOTAL") {
            modalidad = "T";
        } else if (modalidad === "PARCIAL") {
            modalidad = "P";
        }
        tipoPuesto = $("#tipoPuesto").val();
        if (tipoPuesto === "ES TELETRABAJABLE") {
            tipoPuesto = "T";
        } else if (tipoPuesto === "NO ES TELETRABAJABLE") {
            tipoPuesto = "N";
        }

        $.ajax({
            async: false,
            url: 'AN_ANALISIS_PUESTO_Servlet',
            data: {
                accion: "agregarAnalisisPuesto",
                anResultadoFactores: $("#puntajeFactores").val(),
                anResultadoActividades: puntajeActividades,
                psProcesoSolicitud: psProcesoSolicitud,
                anDiasRecomendados: diasRecomendados,
                anModalidad: modalidad,
                anTipoPuesto: tipoPuesto
            },
            error: function () { //si existe un error en la respuesta del ajax
                mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
            },
            success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                anCodigo = data;
                guardarActividades(anCodigo);
                guardarFactoresAnalisis(anCodigo);
                guardarDocumentacion("notaGerente");
                avanzarPsProcesoSolicitud(3, 2);
                ocultarMensaje();
            },
            type: 'POST'
        });
    } else
    {
        alert("Es indispensable realizar el análisis de puesto completo para avanzar en el proceso.");
    }
}

function validarAnalisisPuesto()
{
    var validacion = true;
    if ($("#puntajeActividades").val() === "") {
        validacion = false;
    }
    if ($("#puntajeFactores").val() === "") {
        validacion = false;
    }

    return validacion;
}

function consultarAnalisisPuestoByProcesoSolicitud(dataJson)
{
    var numeroProceso;
    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].esEstado === 2) {
            numeroProceso = dataJson[i].psCodigo;
        }
    }

    if (numeroProceso) {
        $.ajax({
            async: false,
            url: 'AN_ANALISIS_PUESTO_Servlet',
            data: {
                accion: "consultaDinamica",
                campo: "psProcesoSolicitud",
                valor: numeroProceso,
                unico: true
            },
            error: function () { //si existe un error en la respuesta del ajax
                cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
            },
            success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                var resultadoFactores = data[0].anResultadoFactores;
                $("#resultadoFactores").val(resultadoFactores);
                if (resultadoFactores > 75) {
                    $("#resultadoFactoresColor").css("background", "#42f448");
                    $("#recomendacionResultadoFactores").empty();
                    $("#recomendacionResultadoFactores").append("Los aspectos de gestión relacionados con el puesto FAVORECEN la aplicación de la MODALIDAD DE TELETRABAJO");
                } else if (resultadoFactores < 25) {
                    $("#resultadoFactoresColor").css("background", "#e01a1a");
                    $("#recomendacionResultadoFactores").empty();
                    $("#recomendacionResultadoFactores").append("Los aspectos de gestión relacionados con el puesto, evidencian la necesidad de aplicar MEJORAS para la aplicación de la MODALIDAD DE TELETRABAJO");
                } else
                {
                    $("#resultadoFactoresColor").css("background", "#f7f71b");
                    $("#recomendacionResultadoFactores").empty();
                    $("#recomendacionResultadoFactores").append("Los aspectos de gestión relacionados con el puesto indican SUSCEPTIBILIDAD de aplicar la MODALIDAD DE TELETRABAJO");
                }
                var resultadoActividades = data[0].anResultadoActividades;
                $("#resultadoActividades").val(resultadoActividades + "%");
                if (resultadoActividades === 100) {
                    $("#resultadoActividadesColor").css("background", "#42f448");
                } else if (resultadoActividades < 20) {
                    $("#resultadoActividadesColor").css("background", "#e01a1a");
                } else
                {
                    $("#resultadoActividadesColor").css("background", "#f7f71b");
                }
                $("#diasResultado").val(data[0].anDiasRecomendados + " DIAS");
                var modalidad = data[0].anModalidad;
                if (modalidad === "T") {
                    $("#modalidadResultado").val("TOTAL");
                } else if (modalidad === "P") {
                    $("#modalidadResultado").val("PARCIAL");
                }
                var tipoPuesto = data[0].anTipoPuesto;
                if (tipoPuesto === "T") {
                    $("#tipoPuestoResultado").val("ES TELETRABAJABLE");
                } else if (tipoPuesto === "N") {
                    $("#tipoPuestoResultado").val("NO ES TELETRABAJABLE");
                }
            },
            type: 'POST',
            dataType: "json"
        });
    }
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

function avanzarPsProcesoSolicitud(estadoSiguiente, numeroObservacion)
{
    var observacion = $("#observacion" + numeroObservacion).val();

    $.ajax({
        async: false,
        url: 'PS_PROCESO_SOLICITUD_Servlet',
        data: {
            accion: "avanzarProcesoSolicitud",
            psProcesoSolicitud: psProcesoSolicitud,
            psObservacion: observacion,
            psEstadoNuevo: estadoSiguiente,
            slSolicitud: slSolicitud
        },
        error: function () { //si existe un error en la respuesta del ajax
            mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            psProcesoSolicitud = data;
            if (estadoSiguiente === 4) {
                consultarEstadosBySolicitud(3);
                $("#diasTeletrabajo").hide();
            }
            var $active = $('.wizard .nav-tabs li.active');
            ($active).addClass("disabled");
            $active.next().removeClass('disabled');
            nextTab($active);
        },
        type: 'POST'
    });
}

function modificarPsProcesoSolicitud(estado)
{
    var observacion = $("#observacion"+estado).val();

    $.ajax({
        asyn: false,
        url: 'PS_PROCESO_SOLICITUD_Servlet',
        data: {
            accion: "modificarProcesoSolicitud",
            psProcesoSolicitud: psProcesoSolicitud,
            psObservacion: observacion
        },
        error: function () { //si existe un error en la respuesta del ajax
            mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            window.location = 'ADMINISTRAR_PROCESO_SOLICITUD_JSP.jsp';
        },
        type: 'POST'
    });
}

//actividadAnalisis
function guardarActividadAnalisis(acCodigo, anCodigo)
{
    $.ajax({
        async: false,
        url: 'AC_AN_ACTIVIDAD_ANALISIS_Servlet',
        data: {
            accion: "agregarActividadAnalisis",
            acActividad: acCodigo,
            anAnalisis: anCodigo
        },
        error: function () { //si existe un error en la respuesta del ajax
            mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
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
        type: 'POST'
    });
}

//actividadCaracteristica
function guardarActividadCaracteristica(acCodigo, i)
{
    var checkboxes = document.getElementsByName("caracteristicas" + i);
    var value, teletrabajable;
    for (var i = 0; i < checkboxes.length; i++) {
        value = checkboxes[i].value;
        if (checkboxes[i].checked)
        {
            teletrabajable = "S";
        } else
        {
            teletrabajable = "N";
        }

        $.ajax({
            async: false,
            url: 'AC_CA_ACTIVIDAD_CARACTERISTICA_Servlet',
            data: {
                accion: "agregarActividadCaracteristica",
                acActividad: acCodigo,
                caCaracteristica: value,
                acCaTeletrabajable: teletrabajable
            },
            error: function () { //si existe un error en la respuesta del ajax
                mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
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
            type: 'POST'
        });
    }
}

//factoresAnalisis
function guardarFactoresAnalisis(anCodigo)
{
    var rowCount = $('#tablaFactores tr').length;
    var grado, fcFactor;

    for (var i = 0; i < rowCount - 1; i++) {
        fcFactor = $("#descripcion" + i).attr('name');
        grado = parseInt($('input[name=factor' + i + ']:checked').val());
        switch (grado) {
            case 20:
                grado = 0;
                break;
            case 40:
                grado = 1;
                break;
            case 60:
                grado = 2;
                break;
            case 80:
                grado = 3;
                break;
            case 100:
                grado = 4;
                break;
            default:
                alert("Error obteniendo los grados del factor.");
        }

        $.ajax({
            async: false,
            url: 'FC_AN_FACTOR_ANALISIS_Servlet',
            data: {
                accion: "agregarFactorAnalisis",
                fcAnGrado: grado,
                fcFactor: fcFactor,
                anAnalisis: anCodigo
            },
            error: function () { //si existe un error en la respuesta del ajax
                mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
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
            type: 'POST'
        });
    }
}

//documentacion
function guardarDocumentacion(nombreCampo)
{
    var datos = new FormData();
    datos.append('file', document.getElementById(nombreCampo).files[0]);
    datos.append('psProcesoSolicitud', psProcesoSolicitud);
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

//dias
function validarDias()
{
    var chckbxDias = document.getElementsByName("dia");
    var validar = false;

    for (var i = 0, l = chckbxDias.length; i < l; i++)
    {
        if (chckbxDias[i].checked)
        {
            validar = true;
            break;
        }
    }

    return validar;
}

//solicitud
function modificarSolicitud()
{
    var resultado = $("#resultadoSolicitud").val();

    $.ajax({
        async: false,
        url: 'SL_SOLICITUD_Servlet',
        data: {
            accion: "modificarSolicitud",
            slCodigo: slSolicitud,
            slResultado: resultado
        },
        error: function () { //si existe un error en la respuesta del ajax
            mostrarMensaje("mensajeResult", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            if (resultado === "A") {
                consultarSolicitudByCodigo(true);
            } else if (resultado === "R") {
                modificarPsProcesoSolicitud(4);
            }
        },
        type: 'POST'
    });
}

function consultarSolicitudByCodigo(estado4)
{
    $.ajax({
        async: false,
        url: 'SL_SOLICITUD_Servlet',
        data: {
            accion: "consultarSolicitudByCodigo",
            slCodigo: slSolicitud
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

//teletrabajador
function guardarTeletrabajador(trTrabajador)
{
    $.ajax({
        async: false,
        url: 'TT_TELETRABAJADOR_Servlet',
        data: {
            accion: "agregarTeletrabajador",
            trTrabajador: trTrabajador,
            slSolicitud: slSolicitud
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            ttTeletrabajador = data;
            guardarDiasTeletrabajador(data);
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

//diasTeletrabajador
function guardarDiasTeletrabajador(ttTeletrabajador)
{
    var chckbxDias = document.getElementsByName("dia");
    var dia;

    for (var i = 0, l = chckbxDias.length; i < l; i++)
    {
        if (chckbxDias[i].checked)
        {
            dia = chckbxDias[i].value;

            $.ajax({
                async: false,
                url: 'TT_TELETRABAJADOR_Servlet',
                data: {
                    accion: "agregarDiasTeletrabajador",
                    ttTeletrabajador: ttTeletrabajador,
                    diDia: dia
                },
                error: function () { //si existe un error en la respuesta del ajax
                    cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
                },
                success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                    avanzarPsProcesoSolicitud(5, 4);
                },
                type: 'POST',
                dataType: "text"
            });
        }
    }
}

function consultarDiasTeletrabajadorByTeletrabajador()
{
    $.ajax({
        async: false,
        url: 'TT_TELETRABAJADOR_Servlet',
        data: {
            accion: "consultarDiasTeletrabajador",
            campo: "ttTeletrabajador",
            valor: ttTeletrabajador,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            var dias = parseInt(data.length * 4);
            $("#diasTeletrabajando").val(dias);
        },
        type: 'POST',
        dataType: "json"
    });
}

//calculoAhorro
function validarCalculoAhorro()
{
    var validacion = true;

    if ($("#salarioTotal").val() === "") {
        validacion = false;
    }

    if ($("#salarioTeletrabajo").val() === "") {
        validacion = false;
    }

    if ($("#costoUnidad").val().trim() === "") {
        validacion = false;
    }

    return validacion;
}

function guardarCalculoAhorro()
{
    $.ajax({
        async: false,
        url: 'CH_CALCULO_AHORRO_Servlet',
        data: {
            accion: "agregarCalculoAhorro",
            chSalarioTotal: $("#salarioTotal").val(),
            chSalarioLey: $("#salarioRebajosLey").val(),
            chCostoEventos: $("#costoUnidad").val(),
            psProcesoSolicitud: psProcesoSolicitud
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            guardarDocumentacion("visita");
            avanzarPsProcesoSolicitud(7, 6);
        },
        type: 'POST',
        dataType: "text"
    });
}