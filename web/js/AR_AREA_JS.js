$(document).ready(function () {
    consultarAreas();
    consultarDirecciones();
});

$(function () {

    $("#guardar").click(function () {
        guardar();
    });

    $("#cancelar").click(function () {
        limpiarForm();
        $("#formularioAdministrar").modal("hide");
    });

    $("#btBusquedaArCodigo, #btBusquedaArDescripcion, #btBusquedaDrDireccion").click(function () {
        ocultarAlerta();
        buscar(this.id);
    });

    $("#btLimpiarBusqueda").click(function () {
        ocultarAlerta();
        limpiarBusqueda();
    });

    $('#arCodigo').on('input', function () {
        if ($(this).val().length > 38) {
            $(this).val($(this).val().slice(0, 38));
        }
    });

    $('#formularioAdministrar').on('hidden.bs.modal', function (e) {
        //setea el focus del formulario
        $('#arCodigo').focus();

        //se cambia la accion por agregarPersona
        $("#areasAction").val("agregarArea");

        //esconde el div del mensaje
        mostrarMensaje("hiddenDiv", "", "");

        //Resetear el formulario
        $('#formAreas').trigger("reset");

        $("#groupDescripcion").removeClass("has-error");
    });
});

function consultarAreas() {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la información de áreas en la base de datos");
    //Se envia la información por ajax
    $.ajax({
        url: 'AR_AREA_Servlet',
        data: {
            accion: "consultarAreas"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de las áreas en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarTabla(data);
            // se oculta el modal esta funcion se encuentra en el utils.js
            ocultarModal("modalMensajes");

        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarTabla(dataJson) {
    //limpia la información que tiene la tabla
    $("#tablaAreas").html("");

    //muestra el enzabezado de la tabla
    var head = $("<thead />");
    var row = $("<tr />");
    head.append(row);
    $("#tablaAreas").append(head);
    row.append($("<th><b>CÓDIGO</b></th>"));
    row.append($("<th><b>DESCRIPCIÓN</b></th>"));
    row.append($("<th><b>ESTADO</th>"));
    row.append($("<th><b>DIRECCION</th>"));

    //carga la tabla con el json devuelto
    for (var i = 0; i < dataJson.length; i++) {
        dibujarFila(dataJson[i]);
    }
}

function dibujarFila(rowData) {
    //Cuando dibuja la tabla en cada boton se le agrega la funcionalidad de cargar o eliminar la informacion
    var row = $("<tr />");
    $("#tablaAreas").append(row);
    row.append($("<td>" + rowData.arCodigo + "</td>"));
    row.append($("<td>" + rowData.arDescripcion + "</td>"));
    row.append($("<td>" + rowData.arEstado + "</td>"));
    row.append($("<td>" + rowData.drDireccion + "</td>"));
    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarAreaByCodigo(\'' + rowData.arCodigo + '\');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>' +
            '</button></td>'));
}

function consultarAreaByCodigo(arCodigo) {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando el área seleccionada");

    $.ajax({
        url: 'AR_AREA_Servlet',
        data: {
            accion: "consultarAreaByCodigo",
            arCodigo: arCodigo
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
            $("#areasAction").val("modificarArea");

            //se carga la información en el formulario
            $("#codigo").val(data.arCodigo);
            $("#descripcion").val(data.arDescripcion);
            $("#estado").val(data.arEstado);
            $("#direccion").val(data.drDireccion);
        },
        type: 'POST',
        dataType: "json"
    });
}

function limpiarForm() {
    //setea el focus del formulario
    $('#arCodigo').focus();

    //se cambia la accion por agregarPersona
    $("#areasAction").val("agregarArea");

    //esconde el div del mensaje
    mostrarMensaje("hiddenDiv", "", "");

    //Resetear el formulario
    $('#formAreas').trigger("reset");

    $("#groupDescripcion").removeClass("has-error");
}

function guardar() {

    if (validar()) {
        //Se envia la información por ajax
        $.ajax({
            url: 'AR_AREA_Servlet',
            data: {
                accion: $("#areasAction").val(),
                arCodigo: $("#codigo").val(),
                arDescripcion: $("#descripcion").val(),
                arEstado: $("#estado").val(), //estado ddl
                drDireccion: $("#direccion").val() //direccion ddl
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
                    consultarAreas();
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
    $("#groupDireccion").removeClass("has-error");
    $("#groupEstado").removeClass("has-error");

    //validar campos
    if ($("#descripcion").val() === "") {
        $("#groupDescripcion").addClass("has-error");
        validacion = false;
    }
    if ($("#direccion").val() === "") {
        $("#groupDireccion").addClass("has-error");
        validacion = false;
    }
    //validaciones ddl

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

    if (idBoton === "btBusquedaArCodigo") {
        if (validarBusqueda("arCodigo")) {
            enviarBusqueda("arCodigo", $("#arCodigo").val(), true);
        }
    } else if (idBoton === "btBusquedaArDescripcion") {
        if (validarBusqueda("arDescripcion")) {
            enviarBusqueda("arDescripcion", $("#arDescripcion").val(), false);
        }
    } else if (idBoton === "btBusquedaDrDireccion") {
        if (validarBusqueda("drDireccion")) {
            enviarBusqueda("drDireccion", $("#drDireccion").val(), false);
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

    mostrarModal("modalMensajes", "Espere por favor..", "Consultando las áreas");

    $.ajax({
        url: 'AR_AREA_Servlet',
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
                    if (a.arDescripcion > b.arDescripcion) {
                        return 1;
                    }
                    if (a.arDescripcion < b.arDescripcion) {
                        return -1;
                    }

                    return 0;
                });

                dibujarTabla(sorted);
            } else
            {
                mostrarAlerta("No existen datos para el filtro aplicado.");
                //redibujar la tabla
                dibujarTabla(data);
            }
        },
        type: 'POST',
        dataType: "json"
    });
}

function limpiarBusqueda() {

    //Consultar todos las áreas
    consultarAreas();

    //Limpiar txt
    $('#arCodigo').val("");
    $('#arDescripcion').val("");
    $('#drDireccion').val("");
}

function consultarDirecciones()
{
    $.ajax({
        url: 'DR_DIRECCION_Servlet',
        data: {
            accion: "consultarDirecciones"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de las direcciones en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarCombo(data);
        },
        type: 'POST',
        dataType: "json"
    });

}

function dibujarCombo(dataJson) {

    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].drEstado === "A") {
            $("#direccion").append($("<option value=\"" + dataJson[i].drCodigo + "\">" + dataJson[i].drDescripcion + "</option>"));
        }
    }

    for (var i = 0; i < dataJson.length; i++) {
        if (dataJson[i].drEstado === "A") {
            $("#drDireccion").append($("<option value=\"" + dataJson[i].drCodigo + "\">" + dataJson[i].drDescripcion + "</option>"));
        }
    }
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
