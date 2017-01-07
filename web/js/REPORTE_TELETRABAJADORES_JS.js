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
$(function ()
{
    $("#generarReporte").click(function () {
        if ($("#reporte").val() !== "") {
            $.ajax({
                url: 'REPORTE_Servlet',
                data: {
                    accion: "mostrarReporte",
                    reporte: $("#reporte").val(),
                    filtro: $("#filtro").val()
                },
                error: function () { //si existe un error en la respuesta del ajax
                    mostrarMensaje("alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
                },
                success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                    var respuestaTxt = data.substring(2);
                    var tipoRespuesta = data.substring(0, 2);
                    if (tipoRespuesta === "C~") {
                        move();
                        //se redirecciona en JavaScript
                        setTimeout(function () {
                            window.location = "REPORTE_TELETRABAJADORES_JASPER_JSP.jsp";
                        }, 2000);


                    } else {
                        if (tipoRespuesta === "E~") {
                            mostrarMensaje("alert alert-danger", respuestaTxt, "Error!");
                        } else {
                            mostrarMensaje("alert alert-danger", "Se genero un error, contacte al administrador", "Error!");
                        }
                    }

                },
                dataType: "text",
                type: 'POST'
            });

        } else
        {
            alert("Seleccione el resporte que desea generar.");
        }

    });
});

function move() {
    $(".progress").css("display", "block");
    var elem = document.getElementById("myBar");
    var width = 1;
    var id = setInterval(frame, 220);
    function frame() {
        if (width >= 100) {
            clearInterval(id);
        } else {
            width++;
            elem.style.width = width + '%';
        }
    }
}

$(document).on('change', '#reporte', function () {
    var reporte = $(this).val();
    if (reporte === "reporteTeletrabajadoresArea") {
        consultarAreas();
    } else if (reporte === "reporteTeletrabajadoresDireccion") {
        consultarDirecciones();
    } else if (reporte === "reporteTeletrabajadoresDivision") {
        consultarDivisiones();
    } else if (reporte === "reporteTeletrabajadoresGerencia") {
        consultarGerencias();
    } else if (reporte === "reporteTeletrabajadoresModalidad") {
        pintarModalidades();
    } else if (reporte === "reporteTeletrabajadoresPuesto") {
        consultarPuestos();
    } else if (reporte === "reporteTeletrabajadoresSexo") {
        pintarSexos();
    }
});

function mostrarMensaje(classCss, msg, neg) {
    //se le eliminan los estilos al mensaje
    $("#mesajeResult").removeClass();

    //se setean los estilos
    $("#mesajeResult").addClass(classCss);

    //se muestra la capa del mensaje con los parametros del metodo
    $("#mesajeResult").fadeIn("slow");
    $("#mesajeResultNeg").html(neg);
    $("#mesajeResultText").html(msg);
    $("#mesajeResultText").html(msg);
}

function dibujarOpciones(dataJson, reporte)
{
    $("#filtro").html("");
    $("#filtro").append($('<option value="" selected="selected"></option>'));
    for (var i = 0; i < dataJson.length; i++) {
        if (reporte === "area") {
            if (dataJson[i].arEstado === "A") {
                $("#filtro").append($("<option value=\"" + dataJson[i].arCodigo + "\">" + dataJson[i].arDescripcion + "</option>"));
            }
        } else if (reporte === "direccion")
        {
            if (dataJson[i].drEstado === "A") {
                $("#filtro").append($("<option value=\"" + dataJson[i].drCodigo + "\">" + dataJson[i].drDescripcion + "</option>"));
            }
        } else if (reporte === "division")
        {
            if (dataJson[i].dvEstado === "A") {
                $("#filtro").append($("<option value=\"" + dataJson[i].dvCodigo + "\">" + dataJson[i].dvDescripcion + "</option>"));
            }
        } else if (reporte === "gerencia")
        {
            if (dataJson[i].grEstado === "A") {
                $("#filtro").append($("<option value=\"" + dataJson[i].grCodigo + "\">" + dataJson[i].grDescripcion + "</option>"));
            }
        } else if (reporte === "puesto")
        {
            if (dataJson[i].ptEstado === "A") {
                $("#filtro").append($("<option value=\"" + dataJson[i].ptCodigo + "\">" + dataJson[i].ptDescripcion + "</option>"));
            }
        }
    }
}

//areas
function consultarAreas()
{
    $.ajax({
        url: 'AR_AREA_Servlet',
        data: {
            accion: "consultarAreas"
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarOpciones(data, "area");
        },
        type: 'POST',
        dataType: "json"
    });
}

//direcciones
function consultarDirecciones()
{
    $.ajax({
        url: 'DR_DIRECCION_Servlet',
        data: {
            accion: "consultarDirecciones"
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarOpciones(data, "direccion");
        },
        type: 'POST',
        dataType: "json"
    });
}

//Divisiones
function consultarDivisiones()
{
    $.ajax({
        url: 'DV_DIVISION_Servlet',
        data: {
            accion: "consultarDivisiones"
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarOpciones(data, "division");
        },
        type: 'POST',
        dataType: "json"
    });
}

//gerencias
function consultarGerencias()
{
    $.ajax({
        url: 'GR_GERENCIA_Servlet',
        data: {
            accion: "consultarGerencias"
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarOpciones(data, "gerencia");
        },
        type: 'POST',
        dataType: "json"
    });
}

//modalidad
function pintarModalidades()
{
    $("#filtro").html("");
    $("#filtro").append($('<option value="" selected="selected"></option>'));
}

//puestos
function consultarPuestos()
{
    $.ajax({
        url: 'PT_PUESTO_Servlet',
        data: {
            accion: "consultarPuestos"
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarOpciones(data, "puesto");
        },
        type: 'POST',
        dataType: "json"
    });
}

//sexos
function pintarSexos()
{
    $("#filtro").html("");
    $("#filtro").append($('<option value="" selected="selected"></option>'));
    $("#filtro").append($('<option value="FEM">Femenino</option>'));
    $("#filtro").append($('<option value="MAS">Masculino</option>'));
}

