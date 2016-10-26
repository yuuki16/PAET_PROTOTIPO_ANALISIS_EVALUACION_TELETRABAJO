$(document).ready(function () {
    consultarDirecciones();
    consultarDivisiones();
});

$(function () {

    $("#guardar").click(function () {
        guardar();
    });

    $("#cancelar").click(function () {
        limpiarForm();
        $("#formularioAdministrar").modal("hide");
    });

    $("#btBusquedaDrCodigo, #btBusquedaDrDescripcion, #btBusquedaDvDivision").click(function () {
        buscar(this.id);
    });

    $("#btLimpiarBusqueda").click(function () {
        limpiarBusqueda();
    });
});

function consultarDirecciones() {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la información de direcciones en la base de datos");
    //Se envia la información por ajax
    $.ajax({
        url: 'DR_DIRECCION_Servlet',
        data: {
            accion: "consultarDirecciones"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de las direcciones en la base de datos");
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
    $("#tablaDirecciones").html("");

    //muestra el enzabezado de la tabla
    var head = $("<thead />");
    var row = $("<tr />");
    head.append(row);
    $("#tablaDirecciones").append(head);
    row.append($("<th><b>CÓDIGO</b></th>"));
    row.append($("<th><b>DESCRIPCIÓN</b></th>"));
    row.append($("<th><b>ESTADO</b></th>"));
    row.append($("<th><b>DIVISIÓN</b></th>"));
    row.append($("<th><b>ACCIÓN</th>"));

    //carga la tabla con el json devuelto
    for (var i = 0; i < dataJson.length; i++) {
        dibujarFila(dataJson[i]);
    }
}

function dibujarFila(rowData) {
    //Cuando dibuja la tabla en cada boton se le agrega la funcionalidad de cargar o eliminar la informacion
    var row = $("<tr />");
    $("#tablaDirecciones").append(row);
    row.append($("<td>" + rowData.drCodigo + "</td>"));
    row.append($("<td>" + rowData.drDescripcion + "</td>"));
    if (rowData.drEstado === "A") {
        row.append($("<td> Activo </td>"));
    }else if (rowData.drEstado === "I") {
        row.append($("<td> Inactivo </td>"));
    }
    row.append($("<td>" + rowData.dvDivision + "</td>"));
    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarDireccionByCodigo(\'' + rowData.drCodigo + '\');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>' +
            '</button></td>'));
}

function consultarDireccionByCodigo(drCodigo) {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la dirección seleccionada");

    $.ajax({
        url: 'DR_DIRECCION_Servlet',
        data: {
            accion: "consultarDireccionByCodigo",
            drCodigo: drCodigo
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
            $("#direccionesAction").val("modificarDireccion");

            //se carga la información en el formulario
            $("#codigo").val(data.drCodigo);
            $("#descripcion").val(data.drDescripcion);
            $("#estado").val(data.drEstado);
            $("#division").val(data.dvDivision);
        },
        type: 'POST',
        dataType: "json"
    });
}

function limpiarForm() {
    //setea el focus del formulario
    $('#descripcion').focus();

    //se cambia la accion por agregarDivision
    $("#direccionesAction").val("agregarDireccion");

    //esconde el div del mensaje
    mostrarMensaje("hiddenDiv", "", "");

    //Resetear el formulario
    $('#formDirecciones').trigger("reset");
}

function guardar() {

    if (validar()) {
        //Se envia la información por ajax
        $.ajax({
            url: 'DR_DIRECCION_Servlet',
            data: {
                accion: $("#direccionesAction").val(),
                dvCodigo: $("#codigo").val(),
                dvDescripcion: $("#descripcion").val(),
                dvEstado: $("#estado").val(),
                grGerencia: $("#division").val()
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
                    consultarDirecciones();
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
    $("#groupDivision").removeClass("has-error");
    
    if ($("#descripcion").val() === "") {
        $("#groupDescripcion").addClass("has-error");
        validacion = false;
    }
    if ($("#estado").val() === "") {
        $("#groupEstado").addClass("has-error");
        validacion = false;
    }
    if ($("#division").val() === "") {
        $("#groupDivision").addClass("has-error");
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

    if (idBoton === "btBusquedaDrCodigo") {
        if (validarBusqueda("drCodigo")) {
            enviarBusqueda("drCodigo", $("#drCodigo").val(), true);
        }
    } else if (idBoton === "btBusquedaDrDescripcion") {
        if (validarBusqueda("drDescripcion")) {
            enviarBusqueda("drDescripcion", $("#drDescripcion").val(), false);
        }
    } else if (idBoton === "btBusquedaDvDivision") {
        if (validarBusqueda("dvDivision")) {
            enviarBusqueda("dvDivision", $("#dvDivision").val(), false);
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

    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la direcciones");

    $.ajax({
        url: 'DR_DIRECCION_Servlet',
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

            //redibujar la tabla
            dibujarTabla(data);
        },
        type: 'POST',
        dataType: "json"
    });
}

function limpiarBusqueda() {

    //Consultar todos los divisiones
    consultarDirecciones();

    //Limpiar txt
     $('#drCodigo').val("");
     $('#drDescripcion').val("");
     $('#dvDivision').val("");
}

function consultarDivisiones()
{
    $.ajax({
        url: 'DV_DIVISION_Servlet',
        data: {
            accion: "consultarDivisiones"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de las divisiones en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarCombo(data);
        },
        type: 'POST',
        dataType: "json"
    });
    
}

function dibujarCombo(dataJson){
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#division").append($("<option value=\""+dataJson[i].dvCodigo+"\">"+dataJson[i].dvDescripcion+"</option>"));
    }
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#dvDivision").append($("<option value=\""+dataJson[i].dvCodigo+"\">"+dataJson[i].dvDescripcion+"</option>"));
    }
}

