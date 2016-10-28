$(document).ready(function () {
    consultarPuestos();
    consultarAreas();
    consultarDirecciones();
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

    $("#btBusquedaPtCodigo, #btBusquedaPtDescripcion, #btBusquedaArArea, #btBusquedaDrDireccion, #btBusquedaDvDivision, #btBusquedaGrGerencia").click(function () {
        buscar(this.id);
    });

    $("#btLimpiarBusqueda").click(function () {
        limpiarBusqueda();
    });
});

function consultarPuestos() {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la información de puestos en la base de datos");
    //Se envia la información por ajax
    $.ajax({
        url: 'PT_PUESTO_Servlet',
        data: {
            accion: "consultarPuestos"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de los puestos en la base de datos");
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
    $("#tablaPuestos").html("");

    //muestra el enzabezado de la tabla
    var head = $("<thead />");
    var row = $("<tr />");
    head.append(row);
    $("#tablaPuestos").append(head);
    row.append($("<th><b>CÓDIGO</b></th>"));
    row.append($("<th><b>DESCRIPCIÓN</b></th>"));
    row.append($("<th><b>ESTADO</b></th>"));
    row.append($("<th><b>ÁREA</b></th>"));
    row.append($("<th><b>DIRECCIÓN</b></th>"));
    row.append($("<th><b>DIVISIÓN</b></th>"));
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
    $("#tablaPuestos").append(row);
    row.append($("<td>" + rowData.ptCodigo + "</td>"));
    row.append($("<td>" + rowData.ptDescripcion + "</td>"));
    if (rowData.ptEstado === "A") {
        row.append($("<td> Activo </td>"));
    }else if (rowData.ptEstado === "I") {
        row.append($("<td> Inactivo </td>"));
    }
    if (rowData.hasOwnProperty('arArea')) {
        row.append($("<td>" + rowData.arArea + "</td>"));
    }
    else
    {
        row.append($("<td> NA </td>"));
    }
    if (rowData.hasOwnProperty('drDireccion')) {
        row.append($("<td>" + rowData.drDireccion + "</td>"));
    }
    else
    {
        row.append($("<td> NA </td>"));
    }
    if (rowData.hasOwnProperty('dvDivision')) {
        row.append($("<td>" + rowData.dvDivision + "</td>"));
    }
    else
    {
        row.append($("<td> NA </td>"));
    }
    if (rowData.hasOwnProperty('grGerencia')) {
        row.append($("<td>" + rowData.grGerencia + "</td>"));
    }
    else
    {
        row.append($("<td> NA </td>"));
    }
    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarPuestoByCodigo(\'' + rowData.ptCodigo + '\');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>' +
            '</button></td>'));
}

function consultarPuestoByCodigo(ptCodigo) {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando el puesto seleccionado");

    $.ajax({
        url: 'PT_PUESTO_Servlet',
        data: {
            accion: "consultarPuestoByCodigo",
            ptCodigo: ptCodigo
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
            $("#codigo").attr('readonly', 'readonly');
            //se modificar el hidden que indicar el tipo de accion que se esta realizando
            $("#puestosAction").val("modificarPuesto");

            //se carga la información en el formulario
            $("#codigo").val(data.ptCodigo);
            $("#descripcion").val(data.ptDescripcion);
            $("#estado").val(data.ptEstado);
            $("#area").val(data.arArea);
            $("#direccion").val(data.drDireccion);
            $("#division").val(data.dvDivision);
            $("#gerencia").val(data.grGerencia);
        },
        type: 'POST',
        dataType: "json"
    });
}

function limpiarForm() {
    //setea el focus del formulario
    $('#codigo').focus();
    $("#codigo").removeAttr("readonly"); 

    //se cambia la accion por agregarDivision
    $("#puestosAction").val("agregarPuesto");

    //esconde el div del mensaje
    mostrarMensaje("hiddenDiv", "", "");

    //Resetear el formulario
    $('#formPuestos').trigger("reset");
}

function guardar() {

    if (validar()) {
        //Se envia la información por ajax
        $.ajax({
            url: 'PT_PUESTO_Servlet',
            data: {
                accion: $("#puestosAction").val(),
                ptCodigo: $("#codigo").val(),
                ptDescripcion: $("#descripcion").val(),
                ptEstado: $("#estado").val(),
                arArea: $("#area").val(),
                drDireccion: $("#direccion").val(),
                dvDivision: $("#division").val(),
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
                    consultarPuestos();
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

    if (idBoton === "btBusquedaPtCodigo") {
        if (validarBusqueda("ptCodigo")) {
            enviarBusqueda("ptCodigo", $("#ptCodigo").val(), true);
        }
    } else if (idBoton === "btBusquedaPtDescripcion") {
        if (validarBusqueda("ptDescripcion")) {
            enviarBusqueda("ptDescripcion", $("#ptDescripcion").val(), false);
        }
    } else if (idBoton === "btBusquedaArArea") {
        if (validarBusqueda("arArea")) {
            enviarBusqueda("arArea", $("#arArea").val(), false);
        }
    }else if (idBoton === "btBusquedaDrDireccion") {
        if (validarBusqueda("drDireccion")) {
            enviarBusqueda("drDireccion", $("#drDireccion").val(), false);
        }
    }else if (idBoton === "btBusquedaDvDivision") {
        if (validarBusqueda("dvDivision")) {
            enviarBusqueda("dvDivision", $("#dvDivision").val(), false);
        }
    }else if (idBoton === "btBusquedaGrGerencia") {
        if (validarBusqueda("grGerencia")) {
            enviarBusqueda("grGerencia", $("#grGerencia").val(), false);
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

    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los puestos");

    $.ajax({
        url: 'PT_PUESTO_Servlet',
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
    consultarPuestos();

    //Limpiar txt
     $('#ptCodigo').val("");
     $('#ptDescripcion').val("");
     $('#arArea').val("");
     $('#drDireccion').val("");
     $('#dvDivision').val("");
     $('#grGerencia').val("");
}

function consultarAreas()
{
    $.ajax({
        url: 'AR_AREA_Servlet',
        data: {
            accion: "consultarAreas"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de las áreas en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarComboAreas(data);
        },
        type: 'POST',
        dataType: "json"
    });   
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
            dibujarComboDirecciones(data);
        },
        type: 'POST',
        dataType: "json"
    });   
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
            dibujarComboDivisiones(data);
        },
        type: 'POST',
        dataType: "json"
    });   
}

function consultarGerencias()
{
    $.ajax({
        url: 'GR_GERENCIA_Servlet',
        data: {
            accion: "consultarGerencias"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de las divisiones en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarComboGerencias(data);
        },
        type: 'POST',
        dataType: "json"
    });   
}

function dibujarComboAreas(dataJson){
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#area").append($("<option value=\""+dataJson[i].arCodigo+"\">"+dataJson[i].arDescripcion+"</option>"));
    }
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#arArea").append($("<option value=\""+dataJson[i].arCodigo+"\">"+dataJson[i].arDescripcion+"</option>"));
    }
}

function dibujarComboDirecciones(dataJson){
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#direccion").append($("<option value=\""+dataJson[i].drCodigo+"\">"+dataJson[i].drDescripcion+"</option>"));
    }
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#drDireccion").append($("<option value=\""+dataJson[i].drCodigo+"\">"+dataJson[i].drDescripcion+"</option>"));
    }
}

function dibujarComboDivisiones(dataJson){
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#division").append($("<option value=\""+dataJson[i].dvCodigo+"\">"+dataJson[i].dvDescripcion+"</option>"));
    }
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#dvDivision").append($("<option value=\""+dataJson[i].dvCodigo+"\">"+dataJson[i].dvDescripcion+"</option>"));
    }
}

function dibujarComboGerencias(dataJson){
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#gerencia").append($("<option value=\""+dataJson[i].grCodigo+"\">"+dataJson[i].grDescripcion+"</option>"));
    }
    
    for (var i = 0; i < dataJson.length; i++) {
        $("#grGerencia").append($("<option value=\""+dataJson[i].grCodigo+"\">"+dataJson[i].grDescripcion+"</option>"));
    }
}



