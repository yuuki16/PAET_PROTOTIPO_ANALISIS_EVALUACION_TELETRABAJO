$(document).ready(function () {
    consultarDias();
});

$(function () {

    $("#guardar").click(function () {
        guardar();
    });

    $("#cancelar").click(function () {
        limpiarForm();
        $("#formularioAdministrar").modal("hide");
    });

    $(".btn btn-info centered busqueda").click(function () {
        buscar(this.id);
    });
});

function consultarDias() {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la información de días en la base de datos");
    //Se envia la información por ajax
    $.ajax({
        url: 'DI_DIA_Servlet',
        data: {
            accion: "consultarDias"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de los días en la base de datos");
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
    $("#tablaDias").html("");

    //muestra el enzabezado de la tabla
    var head = $("<thead />");
    var row = $("<tr />");
    head.append(row);
    $("#tablaDias").append(head);
    row.append($("<th><b>CÓDIGO</b></th>"));
    row.append($("<th><b>DESCRIPCIÓN</b></th>"));
    row.append($("<th><b>ACCIÓN</th>"));

    //carga la tabla con el json devuelto
    for (var i = 0; i < dataJson.length; i++) {
        dibujarFila(dataJson[i]);
    }
}

function dibujarFila(rowData) {
    //Cuando dibuja la tabla en cada boton se le agrega la funcionalidad de cargar o eliminar la informacion
    var row = $("<tr />");
    $("#tablaDias").append(row);
    row.append($("<td>" + rowData.diCodigo + "</td>"));
    row.append($("<td>" + rowData.diDescripcion + "</td>"));
    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarDiaByCodigo(\'' + rowData.diCodigo + '\');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>' +
            '</button></td>'));
}

function consultarDiaByCodigo(diCodigo) {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando el día seleccionado");

    $.ajax({
        url: 'DI_DIA_Servlet',
        data: {
            accion: "consultarDiaByCodigo",
            diCodigo: diCodigo
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
            //se indicar que la cédula es solo readOnly
            $("#diCodigo").attr('readonly', 'readonly');

            //se modificar el hidden que indicar el tipo de accion que se esta realizando
            $("#diasAction").val("modificarDia");

            //se carga la información en el formulario
            $("#diCodigo").val(data.diCodigo);
            $("#diDescripcion").val(data.diDescripcion);
        },
        type: 'POST',
        dataType: "json"
    });
}

function limpiarForm() {
    //setea el focus del formulario
    $('#diCodigo').focus();
    $("#diCodigo").removeAttr("readonly"); //elimina el atributo de solo lectura

    //se cambia la accion por agregarPersona
    $("#diasAction").val("agregarDia");

    //esconde el div del mensaje
    mostrarMensaje("hiddenDiv", "", "");

    //Resetear el formulario
    $('#formDias').trigger("reset");
}

function guardar() {

    if (validar()) {
        //Se envia la información por ajax
        $.ajax({
            url: 'DI_DIA_Servlet',
            data: {
                accion: $("#diasAction").val(),
                diCodigo: $("#codigo").val(),
                diDescripcion: $("#descripcion").val()
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
                    consultarDias();
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
    $("#groupCodigo").removeClass("has-error");
    $("#groupDescripcion").removeClass("has-error");

    //validar campos
    if ($("#codigo").val() === "") {
        $("#groupCodigo").addClass("has-error");
        validacion = false;
    }
    if ($("#descripcion").val() === "") {
        $("#groupDescripcion").addClass("has-error");
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

    if (idBoton === "btBusquedaDiCodigo") {
        if (validarBusqueda("diCodigo")) {
            mostrarModal("modalMensajes", "Espere por favor..", "Consultando los días");

            $.ajax({
                url: 'DI_DIA_Servlet',
                data: {
                    accion: "consultaDinamica",
                    campo: "diCodigo",
                    valor: $("#diCodigo").val()
                },
                error: function () { //si existe un error en la respuesta del ajax
                    cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
                },
                success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                    // se oculta el mensaje de espera
                    ocultarModal("modalMensajes");
                    
                    //redibujar la tabla
                    dibujarTabla(data);
                },
                type: 'POST',
                dataType: "json"
            });
        }
    } else if (idBoton === "btBusquedaDiDescripcion") {
        if (validarBusqueda("diDescripcion")) {

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