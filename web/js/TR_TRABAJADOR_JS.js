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
    
    $("#guardarCorreo").click(function () {
        guardarCorreo();
    });
    
    $("#guardarTelefono").click(function () {
        guardarTelefono();
    });

    $("#cancelar").click(function () {
        limpiarForm();
        $("#formularioAdministrarTrabajador").modal("hide");
    });
    
    $("#cancelarCorreo").click(function () {
        limpiarFormCorreo();
        $("#formularioAdministrarCorreo").modal("hide");
    });
    
    $("#cancelarTelefono").click(function () {
        limpiarFormTelefono();
        $("#formularioAdministrarTelefono").modal("hide");
    });

    $("#btBusquedaTrUsuario, #btBusquedaTrCedula, #btBusquedaTrNombre, #btBusquedaTrJefatura, #btBusquedaPtPuesto").click(function () {
        buscar(this.id);
    });

    $("#btLimpiarBusqueda").click(function () {
        limpiarBusqueda();
    });
    
    $("#guardarInfo").click(function () {
        $('#formularioAdministrarInformacion').modal('hide');
    });
});

function consultarTrabajadores() {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los trabajadores");
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
    row.append($("<th><b>INFORMACIÓN ADICIONAL</th>"));
    row.append($("<th><b>MODIFICAR</th>"));

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
    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarInformacionByCodigo(\'' + rowData.trUsuario + '\');">' +
            '<span class="glyphicon glyphicon-list-alt"></span>' +
            '</button></td>'));
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
    $('#formularioAdministrarTrabajador').trigger("reset");
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
                    $("#formularioAdministrarTrabajador").modal("hide");
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

function mostrarMensaje(modal, classCss, msg, neg) {
    //se le eliminan los estilos al mensaje
    $("#"+modal).removeClass();

    //se setean los estilos
    $("#"+modal).addClass(classCss);

    //se muestra la capa del mensaje con los parametros del metodo
    $("#"+modal).fadeIn("slow");
    $("#"+modal+"Neg").html(neg);
    $("#"+modal+"Text").html(msg);
    $("#"+modal+"Text").html(msg);
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
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los puestos");
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

function consultarInformacionByCodigo(trUsuario){
    
    consultarCorreosByTrabajador(trUsuario);
    consultarTelefonosByTrabajador(trUsuario);
    consultarDireccionesFisicasByTrabajador(trUsuario);
    dibujarInsertarBotones(trUsuario);
    //se muestra el formulario
    $("#formularioAdministrarInformacion").modal();
}

function consultarCorreosByTrabajador(trUsuario){
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los correos");
    $.ajax({
        url: 'CR_CORREO_Servlet',
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
            // se oculta el mensaje de espera
            ocultarModal("modalMensajes");

            //redibujar la tabla
            dibujarTablaCorreos(data);
        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarTablaCorreos(dataJson){
    $("#tablaCorreos").html("");

    //muestra el enzabezado de la tabla
    var head = $("<thead />");
    var row = $("<tr />");
    head.append(row);
    $("#tablaCorreos").append(head);
    row.append($('<th style="display: none"><b>CÓDIGO</b></th>'));
    row.append($("<th><b>CORREO</b></th>"));
    row.append($("<th><b>ESTADO</b></th>"));
    row.append($("<th><b>MODIFICAR</th>"));

    //carga la tabla con el json devuelto
    for (var i = 0; i < dataJson.length; i++) {
        dibujarFilaCorreos(dataJson[i]);
    }
}

function dibujarFilaCorreos(rowData){
    var row = $("<tr />");
    $("#tablaCorreos").append(row);
    row.append($('<td style="display: none">' + rowData.crCodigo + '</td>'));
    row.append($("<td>" + rowData.crCorreo + "</td>"));
    if (rowData.crEstado === "A") {
        row.append($("<td> Activo </td>"));
    }else if (rowData.crEstado === "I") {
        row.append($("<td> Inactivo </td>"));
    }
    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarCorreoByCodigo(\'' + rowData.crCodigo + '\');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>' +
            '</button></td>'));
}

function consultarTelefonosByTrabajador(trUsuario){
    
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los teléfonos");
    $.ajax({
        url: 'TL_TELEFONO_Servlet',
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
            // se oculta el mensaje de espera
            ocultarModal("modalMensajes");

            //redibujar la tabla
            dibujarTablaTelefonos(data);
        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarTablaTelefonos(dataJson){
    $("#tablaTelefonos").html("");

    //muestra el enzabezado de la tabla
    var head = $("<thead />");
    var row = $("<tr />");
    head.append(row);
    $("#tablaTelefonos").append(head);
    row.append($('<th style="display: none"><b>CÓDIGO</b></th>'));
    row.append($("<th><b>TELÉFONO</b></th>"));
    row.append($("<th><b>DESCRIPCIÓN</b></th>"));
    row.append($("<th><b>ESTADO</b></th>"));
    row.append($("<th><b>MODIFICAR</th>"));

    //carga la tabla con el json devuelto
    for (var i = 0; i < dataJson.length; i++) {
        dibujarFilaTelefonos(dataJson[i]);
    }
}

function dibujarFilaTelefonos(rowData){
    var row = $("<tr />");
    $("#tablaTelefonos").append(row);
    row.append($('<td style="display: none">' + rowData.tlCodigo + '</td>'));
    row.append($("<td>" + rowData.tlTelefono + "</td>"));
    row.append($("<td>" + rowData.tlDescripcion + "</td>"));
    if (rowData.tlEstado === "A") {
        row.append($("<td> Activo </td>"));
    }else if (rowData.tlEstado === "I") {
        row.append($("<td> Inactivo </td>"));
    }
    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarTelefonoByCodigo(\'' + rowData.tlCodigo + '\');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>' +
            '</button></td>'));
}

function consultarDireccionesFisicasByTrabajador(trUsuario){
    
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando las direcciones físicas");
    $.ajax({
        url: 'DF_DIRECCION_FISICA_Servlet',
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
            // se oculta el mensaje de espera
            ocultarModal("modalMensajes");

            //redibujar la tabla
            dibujarTablaDireccionesFisicas(data);
        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarTablaDireccionesFisicas(dataJson){
    $("#tablaDirecciones").html("");

    //muestra el enzabezado de la tabla
    var head = $("<thead />");
    var row = $("<tr />");
    head.append(row);
    $("#tablaDirecciones").append(head);
    row.append($("<th><b>CÓDIGO</b></th>"));
    row.append($("<th><b>DIRECCIÓN</b></th>"));
    row.append($("<th><b>ESTADO</b></th>"));
    row.append($("<th><b>DISTRITO</b></th>"));
    row.append($("<th><b>MODIFICAR</th>"));

    //carga la tabla con el json devuelto
    for (var i = 0; i < dataJson.length; i++) {
        dibujarFilaDireccionesFisicas(dataJson[i]);
    }
}

function dibujarFilaDireccionesFisicas(rowData){
    var row = $("<tr />");
    $("#tablaDirecciones").append(row);
    row.append($("<td>" + rowData.dfCodigo + "</td>"));
    row.append($("<td>" + rowData.dfDireccion + "</td>"));
    if (rowData.dfEstado === "A") {
        row.append($("<td> Activo </td>"));
    }else if (rowData.dfEstado === "I") {
        row.append($("<td> Inactivo </td>"));
    }
    row.append($("<td>" + rowData.dsDistrito + "</td>"));
    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarDireccionFisicaByCodigo(\'' + rowData.dfCodigo + '\');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>' +
            '</button></td>'));
}

function dibujarInsertarBotones(trUsuario){
    
    $("#btnInsertarCorreo").html("");
    $("#btnInsertarCorreo").append($('<td><button type="button" class="btn btn-success" id="btnMostrarCorreoForm" onclick="insertarCorreo(\'' + trUsuario + '\');">Insertar Correo</button></td>'));
    
    $("#btnInsertarTelefono").html("");
    $("#btnInsertarTelefono").append($('<td><button type="button" class="btn btn-success" id="btnMostrarTelefonoForm" onclick="insertarTelefono(\'' + trUsuario + '\');">Insertar Teléfono</button></td>'));
}

function insertarCorreo(trUsuario){
    $("#correoTrabajador").val(trUsuario);
    $('#formularioAdministrarCorreo').modal('show');
}

function insertarTelefono(trUsuario){
    $("#telefonoTrabajador").val(trUsuario);    
    $('#formularioAdministrarTelefono').modal('show');
}

function guardarCorreo(){
    if (validarCorreo()) {
        //Se envia la información por ajax
        $.ajax({
            url: 'CR_CORREO_Servlet',
            data: {
                accion: $("#correosAction").val(),
                crCodigo: $("#codigoCorreo").val(),
                crCorreo: $("#correoCorreo").val(),
                crEstado: $("#estadoCorreo").val(),
                trTrabajador: $("#correoTrabajador").val()
            },
            error: function () { //si existe un error en la respuesta del ajax
                mostrarMensaje("mensajeResultCorreo", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
            },
            success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                var respuestaTxt = data.substring(2);
                var tipoRespuesta = data.substring(0, 2);
                if (tipoRespuesta === "C~") {
                    mostrarMensaje("mensajeResultCorreo", "alert alert-success", respuestaTxt, "Correcto!");
                    $("#formularioAdministrarCorreo").modal("hide");
                    consultarCorreosByTrabajador($("#correoTrabajador").val());
                    limpiarFormCorreo();
                } else {
                    if (tipoRespuesta === "E~") {
                        mostrarMensaje("mensajeResultCorreo", "alert alert-danger", respuestaTxt, "Error!");
                    } else {
                        mostrarMensaje("mensajeResultCorreo", "alert alert-danger", "Se genero un error, contacte al administrador", "Error!");
                    }
                }

            },
            type: 'POST'
        });
    } else {
        mostrarMensaje("mensajeResultCorreo", "alert alert-danger", "Debe digitar los campos del formulario", "Error!");
    }
}

function limpiarFormCorreo(){
    //setea el focus del formulario
    $('#correoCorreo').focus();

    //se cambia la accion por agregarDivision
    $("#correosAction").val("agregarCorreo");

    //esconde el div del mensaje
    mostrarMensaje("mensajeResultCorreo", "hiddenDiv", "", "");

    //Resetear el formulario
    $('#formCorreos').trigger("reset");
    
}

function consultarCorreoByCodigo(crCodigo){
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando el correo seleccionado");

    $.ajax({
        url: 'CR_CORREO_Servlet',
        data: {
            accion: "consultarCorreoByCodigo",
            crCodigo: crCodigo
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // se oculta el mensaje de espera
            ocultarModal("modalMensajes");
            limpiarFormCorreo();

            //se muestra el formulario
            $("#formularioAdministrarCorreo").modal();

            //************************************************************************
            //carga información en el formulario
            //************************************************************************
            
            //se modificar el hidden que indicar el tipo de accion que se esta realizando
            $("#correosAction").val("modificarCorreo");

            //se carga la información en el formulario
            $("#codigoCorreo").val(data.crCodigo);
            $("#correoCorreo").val(data.crCorreo);
            $("#estadoCorreo").val(data.crEstado);
            $("#correoTrabajador").val(data.trTrabajador);
        },
        type: 'POST',
        dataType: "json"
    });
}

function validarCorreo(){
    
    var validacion = true;

    //quitar errores
    $("#groupCorreoCorreo").removeClass("has-error");
    $("#groupEstadoCorreo").removeClass("has-error");
    
    if ($("#correoCorreo").val() === "") {
        $("#groupCorreoCorreo").addClass("has-error");
        validacion = false;
    }
    if ($("#estadoCorreo").val() === "") {
        $("#groupEstadoCorreo").addClass("has-error");
        validacion = false;
    }
    return validacion;
}

function guardarTelefono(){
    if (validarTelefono()) {
        //Se envia la información por ajax
        $.ajax({
            url: 'TL_TELEFONO_Servlet',
            data: {
                accion: $("#telefonosAction").val(),
                tlCodigo: $("#telefonoCodigo").val(),
                tlTelefono: $("#telefonoTelefono").val(),
                tlDescripcion: $("#telefonoDescripcion").val(),
                tlEstado: $("#telefonoEstado").val(),
                trTrabajador: $("#telefonoTrabajador").val()
            },
            error: function () { //si existe un error en la respuesta del ajax
                mostrarMensaje("mensajeResultTelefono", "alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
            },
            success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                var respuestaTxt = data.substring(2);
                var tipoRespuesta = data.substring(0, 2);
                if (tipoRespuesta === "C~") {
                    mostrarMensaje("mensajeResultTelefono", "alert alert-success", respuestaTxt, "Correcto!");
                    $("#formularioAdministrarTelefono").modal("hide");
                    consultarTelefonosByTrabajador($("#telefonoTrabajador").val());
                    limpiarFormTelefono();
                } else {
                    if (tipoRespuesta === "E~") {
                        mostrarMensaje("mensajeResultTelefono", "alert alert-danger", respuestaTxt, "Error!");
                    } else {
                        mostrarMensaje("mensajeResultTelefono", "alert alert-danger", "Se genero un error, contacte al administrador", "Error!");
                    }
                }

            },
            type: 'POST'
        });
    } else {
        mostrarMensaje("mensajeResultTelefono", "alert alert-danger", "Debe digitar los campos del formulario", "Error!");
    }
}

function limpiarFormTelefono(){
    //setea el focus del formulario
    $('#telefonoTelefono').focus();

    //se cambia la accion por agregarDivision
    $("#telefonosAction").val("agregarTelefono");

    //esconde el div del mensaje
    mostrarMensaje("mensajeResultTelefono", "hiddenDiv", "", "");

    //Resetear el formulario
    $('#formTelefonos').trigger("reset");
}

function consultarTelefonoByCodigo(tlCodigo){
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando el teléfono seleccionado");

    $.ajax({
        url: 'TL_TELEFONO_Servlet',
        data: {
            accion: "consultarTelefonoByCodigo",
            tlCodigo: tlCodigo
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // se oculta el mensaje de espera
            ocultarModal("modalMensajes");
            limpiarFormTelefono();

            //se muestra el formulario
            $("#formularioAdministrarTelefono").modal();

            //************************************************************************
            //carga información en el formulario
            //************************************************************************
            
            //se modificar el hidden que indicar el tipo de accion que se esta realizando
            $("#telefonosAction").val("modificarTelefono");

            //se carga la información en el formulario
            $("#telefonoCodigo").val(data.tlCodigo);
            $("#telefonoTelefono").val(data.tlTelefono);
            $("#telefonoDescripcion").val(data.tlDescripcion);
            $("#telefonoEstado").val(data.tlEstado);
            $("#telefonoTrabajador").val(data.trTrabajador);
        },
        type: 'POST',
        dataType: "json"
    });
}

function validarTelefono(){
    
    var validacion = true;

    //quitar errores
    $("#groupTelefonoTelefono").removeClass("has-error");
    $("#groupTelefonoDescripcion").removeClass("has-error");
    $("#groupTelefonoEstado").removeClass("has-error");
    
    if ($("#telefonoTelefono").val() === "") {
        $("#groupTelefonoTelefono").addClass("has-error");
        validacion = false;
    }
    if ($("#telefonoDescripcion").val() === "") {
        $("#groupTelefonoDescripcion").addClass("has-error");
        validacion = false;
    }
    if ($("#telefonoEstado").val() === "") {
        $("#groupTelefonoEstado").addClass("has-error");
        validacion = false;
    }
    return validacion;
}