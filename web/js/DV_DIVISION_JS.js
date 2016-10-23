$(document).ready(function () {
    consultarDivisiones();
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

    $("#btBusquedaDvCodigo, #btBusquedaDvDescripcion, #btBusquedaGrGerencia").click(function () {
        buscar(this.id);
    });

    $("#btLimpiarBusqueda").click(function () {
        limpiarBusqueda();
    });
});

function consultarDivisiones() {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la información de divisiones en la base de datos");
    //Se envia la información por ajax
    $.ajax({
        url: 'DV_DIVISION_Servlet',
        data: {
            accion: "consultarDivisiones"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de las divisiones en la base de datos");
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
    $("#tablaDivisiones").html("");

    //muestra el enzabezado de la tabla
    var head = $("<thead />");
    var row = $("<tr />");
    head.append(row);
    $("#tablaDivisiones").append(head);
    row.append($("<th><b>CÓDIGO</b></th>"));
    row.append($("<th><b>DESCRIPCIÓN</b></th>"));
    row.append($("<th><b>ESTADO</b></th>"));
    row.append($("<th><b>GERENCIA</b></th>"));
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
    row.append($("<td>" + rowData.dvCodigo + "</td>"));
    row.append($("<td>" + rowData.dvDescripcion + "</td>"));
    if (rowData.dvEstado === "A") {
        row.append($("<td> Activo </td>"));
    }else if (rowData.dvEstado === "I") {
        row.append($("<td> Inactivo </td>"));
    }
    row.append($("<td>" + rowData.grGerencia + "</td>"));
    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarDivisionByCodigo(\'' + rowData.dvCodigo + '\');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>' +
            '</button></td>'));
}

function consultarDivisionByCodigo(dvCodigo) {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la división seleccionada");

    $.ajax({
        url: 'DV_DIVISION_Servlet',
        data: {
            accion: "consultarDivisionByCodigo",
            dvCodigo: dvCodigo
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
            $("#divisionesAction").val("modificarDivision");

            //se carga la información en el formulario
            $("#codigo").val(data.dvCodigo);
            $("#descripcion").val(data.dvDescripcion);
            $("#estado").val(data.dvEstado);
            $("#gerencia").val(data.grGerencia);
        },
        type: 'POST',
        dataType: "json"
    });
}

function limpiarForm() {
    //setea el focus del formulario
    $('#descripcion').focus();

    //se cambia la accion por agregarDivision
    $("#divisionesAction").val("agregarDivision");

    //esconde el div del mensaje
    mostrarMensaje("hiddenDiv", "", "");

    //Resetear el formulario
    $('#formDivisiones').trigger("reset");
}

function guardar() {

    if (validar()) {
        //Se envia la información por ajax
        $.ajax({
            url: 'DV_DIVISION_Servlet',
            data: {
                accion: $("#divisionesAction").val(),
                dvCodigo: $("#codigo").val(),
                dvDescripcion: $("#descripcion").val(),
                dvEstado: $("#estado").val(),
                grGerencia: $("#gerencia").val()
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
                    consultarDivisiones();
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
    $("#groupGerencia").removeClass("has-error");
    
    if ($("#descripcion").val() === "") {
        $("#groupDescripcion").addClass("has-error");
        validacion = false;
    }
    if ($("#estado").val() === "") {
        $("#groupEstado").addClass("has-error");
        validacion = false;
    }
    if ($("#gerencia").val() === "") {
        $("#groupGerencia").addClass("has-error");
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

    if (idBoton === "btBusquedaDVCodigo") {
        if (validarBusqueda("dvCodigo")) {
            enviarBusqueda("dvCodigo", $("#dvCodigo").val());
        }
    } else if (idBoton === "btBusquedaDvDescripcion") {
        if (validarBusqueda("dvDescripcion")) {
            enviarBusqueda("dvDescripcion", $("#dvDescripcion").val());
        }
    } else if (idBoton === "btBusquedaGrGerencia") {
        if (validarBusqueda("grGerencia")) {
            enviarBusqueda("grGerencia", $("#grGerencia").val());
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

function enviarBusqueda(campo, valor) {

    mostrarModal("modalMensajes", "Espere por favor..", "Consultando las divisiones");

    $.ajax({
        url: 'DV_DIVISION_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: campo,
            valor: valor
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
    consultarDivisiones();

    //Limpiar txt
     $('#dvCodigo').val("");
     $('#dvDescripcion').val("");
     $('#grGerencia').val("");
}

function consultarGerencias()
{
    $.ajax({
        url: 'DV_DIVISION_Servlet',
        data: {
            accion: "consultarGerencias"
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
        $("#gerencia").append($("<option value=\""+dataJson[i].grCodigo+"\">"+dataJson[i].grDescripcion+"</option>"));
    }
}