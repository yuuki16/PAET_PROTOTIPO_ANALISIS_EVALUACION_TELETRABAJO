$(document).ready(function () {
    consultarGerencias();
});

$(function () {

    $("#guardar").click(function () {
        guardar();
    });

    $("#cancelar").click(function () {
        limpiarForm();
        $("#formularioAdministrar").modal("hide");
    });

    $("#btBusquedaGrCodigo, #btBusquedaGrDescripcion").click(function () {
        ocultarAlerta();
        buscar(this.id);
    });

    $("#btLimpiarBusqueda").click(function () {
        ocultarAlerta();
        limpiarBusqueda();
    });
});

function consultarGerencias() {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la información de gerencias en la base de datos");
    //Se envia la información por ajax
    $.ajax({
        url: 'GR_GERENCIA_Servlet',
        data: {
            accion: "consultarGerencias"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de los días en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            var sorted = data.sort(function (a, b) {
                if (a.grDescripcion > b.grDescripcion) {
                    return 1;
                }
                if (a.grDescripcion < b.grDescripcion) {
                    return -1;
                }

                return 0;
            });
            dibujarTabla(sorted);
            // se oculta el modal esta funcion se encuentra en el utils.js
            ocultarModal("modalMensajes");

        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarTabla(dataJson) {
    //limpia la información que tiene la tabla
    $("#tablaGerencias").html("");

    //muestra el enzabezado de la tabla
    var head = $("<thead />");
    var row = $("<tr />");
    head.append(row);
    $("#tablaGerencias").append(head);
    row.append($("<th><b>CÓDIGO</b></th>"));
    row.append($("<th><b>DESCRIPCIÓN</b></th>"));
    row.append($("<th><b>ESTADO</b></th>"));
    row.append($("<th><b>ACCIÓN</th>"));

    //carga la tabla con el json devuelto
    for (var i = 0; i < dataJson.length; i++) {
        dibujarFila(dataJson[i]);
    }
}

function dibujarFila(rowData) {
    //Cuando dibuja la tabla en cada boton se le agrega la funcionalidad de cargar o eliminar la informacion
    var row = $("<tr />");
    $("#tablaGerencias").append(row);
    row.append($("<td>" + rowData.grCodigo + "</td>"));
    row.append($("<td>" + rowData.grDescripcion + "</td>"));
    if (rowData.grEstado === "A") {
        row.append($("<td> Activo </td>"));
    } else if (rowData.grEstado === "I") {
        row.append($("<td> Inactivo </td>"));
    }
    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarGerenciaByCodigo(\'' + rowData.grCodigo + '\');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>' +
            '</button></td>'));
}

function consultarGerenciaByCodigo(grCodigo) {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la gerencia seleccionada");

    $.ajax({
        url: 'GR_GERENCIA_Servlet',
        data: {
            accion: "consultarGerenciaByCodigo",
            grCodigo: grCodigo
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // se oculta el mensaje de espera
            ocultarModal("modalMensajes");
            limpiarForm();

            //se muestra el formulario
            $("#formularioAdministrar").modal();

            //************************************************************************
            //carga información en el formulario
            //************************************************************************
            //se modificar el hidden que indicar el tipo de accion que se esta realizando
            $("#gerenciasAction").val("modificarGerencia");

            //se carga la información en el formulario
            $("#codigo").val(data.grCodigo);
            $("#descripcion").val(data.grDescripcion);
            $("#estado").val(data.grEstado);
        },
        type: 'POST',
        dataType: "json"
    });
}

function limpiarForm() {
    //setea el focus del formulario
    $('#descripcion').focus();

    //se cambia la accion por agregarGerencia
    $("#gerenciasAction").val("agregarGerencia");

    //esconde el div del mensaje
    mostrarMensaje("hiddenDiv", "", "");

    //Resetear el formulario
    $('#formGerencias').trigger("reset");
}

function guardar() {

    if (validar()) {
        //Se envia la información por ajax
        $.ajax({
            url: 'GR_GERENCIA_Servlet',
            data: {
                accion: $("#gerenciasAction").val(),
                grCodigo: $("#codigo").val(),
                grDescripcion: $("#descripcion").val(),
                grEstado: $("#estado").val()
            },
            error: function () { //si existe un error en la respuesta del ajax
                mostrarMensaje("alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
            },
            success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                var respuestaTxt = data.substring(2);
                var tipoRespuesta = data.substring(0, 2);
                if (tipoRespuesta === "C~") {
                    mostrarMensaje("alert alert-success", respuestaTxt, "Correcto!");
                    $("#formularioAdministrar").modal("hide");
                    consultarGerencias();
                    limpiarForm();
                } else {
                    if (tipoRespuesta === "E~") {
                        mostrarMensaje("alert alert-danger", respuestaTxt, "Error!");
                    } else {
                        mostrarMensaje("alert alert-danger", "Se genero un error, contacte al administrador", "Error!");
                    }
                }

            },
            type: 'POST'
        });
    } else {
        mostrarMensaje("alert alert-danger", "Debe digitar los campos del formulario", "Error!");
    }
}

function validar() {

    var validacion = true;

    //quitar errores
    $("#groupDescripcion").removeClass("has-error");
    $("#groupEstado").removeClass("has-error");

    if ($("#descripcion").val() === "") {
        $("#groupDescripcion").addClass("has-error");
        validacion = false;
    }
    if ($("#estado").val() === "") {
        $("#groupEstado").addClass("has-error");
        validacion = false;
    }

    return validacion;
}

function mostrarMensaje(classCss, msg, neg) {
    //se le eliminan los estilos al mensaje
    $("#mensajeResult").removeClass();

    //se setean los estilos
    $("#mensajeResult").addClass(classCss);

    //se muestra la capa del mensaje con los parametros del metodo
    $("#mensajeResult").fadeIn("slow");
    $("#mensajeResultNeg").html(neg);
    $("#mensajeResultText").html(msg);
    $("#mensajeResultText").html(msg);
}

function buscar(idBoton) {

    if (idBoton === "btBusquedaGrCodigo") {
        if (validarBusqueda("grCodigo")) {
            enviarBusqueda("grCodigo", $("#grCodigo").val(), true);
        }
    } else if (idBoton === "btBusquedaGrDescripcion") {
        if (validarBusqueda("grDescripcion")) {
            enviarBusqueda("grDescripcion", $("#grDescripcion").val(), false);
        }
    }
}

function validarBusqueda(campo) {

    var validacion = true;

    //quitar errores
    $("#group" + campo).removeClass("has-error");

    //validar campos
    if ($("#" + campo).val() === "") {
        $("#group" + campo).addClass("has-error");
        validacion = false;
    }

    return validacion;
}

function enviarBusqueda(campo, valor, unico) {

    mostrarModal("modalMensajes", "Espere por favor..", "Consultando las gerencias");

    $.ajax({
        url: 'GR_GERENCIA_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: campo,
            valor: valor,
            unico: unico
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // se oculta el mensaje de espera
            ocultarModal("modalMensajes");

            if (data.length > 0) {
                var sorted = data.sort(function (a, b) {
                    if (a.grDescripcion > b.grDescripcion) {
                        return 1;
                    }
                    if (a.grDescripcion < b.grDescripcion) {
                        return -1;
                    }

                    return 0;
                });
                //redibujar la tabla
                dibujarTabla(sorted);
            } else
            {
                dibujarTabla(data);
                mostrarAlerta("No existen datos para el filtro aplicado.");
            }

        },
        type: 'POST',
        dataType: "json"
    });
}

function limpiarBusqueda() {

    //Consultar todos los días
    consultarGerencias();

    //Limpiar txt
    $('#grCodigo').val("");
    $('#grDescripcion').val("");
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