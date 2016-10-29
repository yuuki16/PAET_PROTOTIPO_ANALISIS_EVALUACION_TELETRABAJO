$(document).ready(function () {
    consultarTrabajadores();
    consultarPuestos();
});

$(function () {
    //Genera el datapicker
    $('#fechaIngreso').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $('#fechaEntrada').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $("#guardar").click(function () {
        guardar();
    });

    $("#cancelar").click(function () {
        limpiarForm();
        $("#formularioAdministrar").modal("hide");
    });

    $("#btBusquedaTrUsuario, #btBusquedaTrCedula, #btBusquedaTrNombre, #btBusquedaTrJefatura, #btBusquedaPtPuesto").click(function () {
        buscar(this.id);
    });

    $("#btLimpiarBusqueda").click(function () {
        limpiarBusqueda();
    });

});

function consultarTrabajadores() {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la información de trabajadores en la base de datos");
    //Se envia la información por ajax
    $.ajax({
        url: 'TR_TRABAJADOR_Servlet',
        data: {
            accion: "consultarTrabajadores"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de los trabajadores en la base de datos");
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
    $("#tablaTrabajadores").html("");

    //muestra el enzabezado de la tabla
    var head = $("<thead />");
    var row = $("<tr />");
    head.append(row);
    $("#tablaTrabajadores").append(head);
    row.append($("<th><b>USUARIO</b></th>"));
    row.append($("<th><b>CÉDULA</b></th>"));
    row.append($("<th><b>NOMBRE</b></th>"));
    row.append($("<th><b>PRIMER APELLIDO</b></th>"));
    row.append($("<th><b>SEGUNDO APELLIDO</b></th>"));
    row.append($("<th><b>SEXO</b></th>"));
    row.append($("<th><b>FECHA INGRESO</b></th>"));
    row.append($("<th><b>ESTADO</b></th>"));
    row.append($("<th><b>JEFATURA</b></th>"));
    row.append($("<th><b>FECHA ENTRADA</b></th>"));
    row.append($("<th><b>PUESTO</b></th>"));
    row.append($("<th><b>ACCIÓN</th>"));

    //carga la tabla con el json devuelto
    for (var i = 0; i < dataJson.length; i++) {
        dibujarFila(dataJson[i]);
    }
}

function dibujarFila(rowData) {
    //Cuando dibuja la tabla en cada boton se le agrega la funcionalidad de cargar o eliminar la informacion
    var row = $("<tr />");
    $("#tablaTrabajadores").append(row);
    row.append($("<td>" + rowData.trUsuario + "</td>"));
    row.append($("<td>" + rowData.trCedula + "</td>"));
    row.append($("<td>" + rowData.trNombre + "</td>"));
    row.append($("<td>" + rowData.trApellido1 + "</td>"));
    row.append($("<td>" + rowData.trApellido2 + "</td>"));
    if (rowData.trSexo === "MAS") {
        row.append($("<td> MASCULINO </td>"));
    }else if (rowData.trSexo === "FEM") {
        row.append($("<td> FEMENINO </td>"));
    }
    row.append($("<td>" + rowData.trFechaIngreso + "</td>"));
    if (rowData.trEstado === "A") {
        row.append($("<td> Activo </td>"));
    }else if (rowData.trEstado === "I") {
        row.append($("<td> Inactivo </td>"));
    }
    row.append($("<td>" + rowData.trJefatura + "</td>"));
    row.append($("<td>" + rowData.trPtFechaEntrada + "</td>"));
    row.append($("<td>" + rowData.ptPuesto + "</td>"));
    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarTrabajadorByCodigo(\'' + rowData.trUsuario + '\');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>' +
            '</button></td>'));
}

function consultarTrabajadorByCodigo(trUsuario) {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando el trabajador seleccionado");

    $.ajax({
        url: 'TR_TRABAJADOR_Servlet',
        data: {
            accion: "consultarTrabajadorByCodigo",
            trUsuario: trUsuario
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
            $("#usuario").attr('readonly', 'readonly');
            //se modificar el hidden que indicar el tipo de accion que se esta realizando
            $("#trabajadoresAction").val("modificarTrabajador");

            //se carga la información en el formulario
            $("#usuario").val(data.trUsuario);
            $("#cedula").val(data.trCedula);
            $("#nombre").val(data.trNombre);
            $("#apellido1").val(data.trApellido1);
            $("#apellido2").val(data.trApellido2);
            $("#sexo").val(data.trSexo);
            $("#fechaIngreso").val(data.trFechaIngreso);
            $("#estado").val(data.trEstado);
            $("#jefatura").val(data.trJefatura);
            $("#puesto").val(data.ptPuesto);
            $("#fechaEntrada").val(data.trFechaEntrada);
        },
        type: 'POST',
        dataType: "json"
    });
}

function limpiarForm() {
    //setea el focus del formulario
    $('#usuario').focus();
    $("#usuario").removeAttr("readonly"); 

    //se cambia la accion por agregarDivision
    $("#trabajadoresAction").val("agregarTrabajador");

    //esconde el div del mensaje
    mostrarMensaje("hiddenDiv", "", "");

    //Resetear el formulario
    $('#formTrabajadores').trigger("reset");
}

function guardar() {

    if (validar()) {
        //Se envia la información por ajax
        $.ajax({
            url: 'TR_TRABAJADOR_Servlet',
            data: {
                accion: $("#trabajadoresAction").val(),
                trUsuario: $("#usuario").val(),
                trCedula: $("#cedula").val(),
                trNombre: $("#nombre").val(),
                trApellido1: $("#apellido1").val(),
                trApellido2: $("#apellido2").val(),
                trSexo: $("#sexo").val(),
                trFechaIngreso: $("#fechaIngreso").data('date'),
                trEstado: $("#estado").val(),
                trJefatura: $("#jefatura").val(),
                trFechaEntrada: $("#fechaEntrada").data('date'),
                ptPuesto: $("#puesto").val()
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
                    consultarTrabajadores();
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
    $("#groupUsuario").removeClass("has-error");
    $("#groupCedula").removeClass("has-error");
    $("#groupNombre").removeClass("has-error");
    $("#groupApellido1").removeClass("has-error");
    $("#groupSexo").removeClass("has-error");
    $("#groupFechaIngreso").removeClass("has-error");
    $("#groupEstado").removeClass("has-error");
    $("#groupPuesto").removeClass("has-error");
    $("#groupFechaEntrada").removeClass("has-error");
    
    if ($("#usuario").val() === "") {
        $("#groupUsuario").addClass("has-error");
        validacion = false;
    }
    if ($("#cedula").val() === "") {
        $("#groupCedula").addClass("has-error");
        validacion = false;
    }
    if ($("#nombre").val() === "") {
        $("#groupNombre").addClass("has-error");
        validacion = false;
    }
    if ($("#apellido1").val() === "") {
        $("#groupApellido1").addClass("has-error");
        validacion = false;
    }
    if ($("#sexo").val() === "") {
        $("#groupSexo").addClass("has-error");
        validacion = false;
    }
    if ($("#fechaIngreso").data('date') === "") {
        $("#groupFechaIngreso").addClass("has-error");
        validacion = false;
    }
    if ($("#estado").val() === "") {
        $("#groupEstado").addClass("has-error");
        validacion = false;
    }
    if ($("#puesto").val() === "") {
        $("#groupPuesto").addClass("has-error");
        validacion = false;
    }
    if ($("#fechaEntrada").data('date') === "") {
        $("#groupFechaEntrada").addClass("has-error");
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

    if (idBoton === "btBusquedaTrUsuario") {
        if (validarBusqueda("trUsuario")) {
            enviarBusqueda("trUsuario", $("#trUsuario").val(), true);
        }
    } else if (idBoton === "btBusquedaTrCedula") {
        if (validarBusqueda("trCedula")) {
            enviarBusqueda("trCedula", $("#trCedula").val(), true);
        }
    } else if (idBoton === "btBusquedaTrNombre") {
        if (validarBusqueda("trNombre")) {
            enviarBusqueda("trNombre", $("#trNombre").val(), false);
        }
    }else if (idBoton === "btBusquedaTrJefatura") {
        if (validarBusqueda("trJefatura")) {
            enviarBusqueda("trJefatura", $("#trJefatura").val(), false);
        }
    }else if (idBoton === "btBusquedaPtPuesto") {
        if (validarBusqueda("ptPuesto")) {
            enviarBusqueda("ptPuesto", $("#ptPuesto").val(), false);
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

    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los trabajadores");

    $.ajax({
        url: 'TR_TRABAJADOR_Servlet',
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
    consultarTrabajadores();

    //Limpiar txt
     $('#trUsuario').val("");
     $('#trCedula').val("");
     $('#trNombre').val("");
     $('#trJefatura').val("");
     $('#ptPuesto').val("");
}

function consultarPuestos() {
    //Se envia la información por ajax
    $.ajax({
        url: 'PT_PUESTO_Servlet',
        data: {
            accion: "consultarPuestos"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de los trabajadores en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarComboPuestos(data);
        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarComboPuestos(dataJson){
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#puesto").append($("<option value=\""+dataJson[i].ptCodigo+"\">"+dataJson[i].ptDescripcion+"</option>"));
    }
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#ptPuesto").append($("<option value=\""+dataJson[i].ptCodigo+"\">"+dataJson[i].ptDescripcion+"</option>"));
    }
}
