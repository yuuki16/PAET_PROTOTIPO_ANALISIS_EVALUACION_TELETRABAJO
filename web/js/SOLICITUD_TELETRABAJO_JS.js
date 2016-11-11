$(document).ready(function () {
    consultarGerencias();
    consultarDivisiones();
    consultarDirecciones();
    consultarAreas();
    consultarPuestos();
    consultarTrabajadores();
    consultarDias();
    consultarEquipoTecnologico();
});

$(function () {

    $("#btBusquedaCandidato").click(function () {
        consultarTrabajadorByCedula($("#trCandidato").val());
    });

    $("#btLimpiarBusqueda").click(function () {
        $('#formSolicitudTeletrabajo').trigger("reset");
    });

    $("#guardar").click(function () {
        guardar();
    });

    $("#tiempoIndefinido").change(function () {
        if (this.checked) {
            $('#tiempo').attr('readonly', true);
            $('#tiempo').val("");
        } else
        {
            $('#tiempo').attr('readonly', false);
            $('#tiempo').val("1");
        }
    });
});

//trabajadores
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
            ocultarModal("modalMensajes");
            dibujarComboTrabajadores(data);
        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarComboTrabajadores(dataJson) {

    for (var i = 0; i < dataJson.length; i++) {
        $("#jefatura").append($("<option value=\"" + dataJson[i].trUsuario + "\">" + dataJson[i].trUsuario + " - " + dataJson[i].trNombre + " " + dataJson[i].trApellido1 + " " + dataJson[i].trApellido2 + "</option>"));
    }
}

function consultarTrabajadorByCedula(trCedula) {

    mostrarModal("modalMensajes", "Espere por favor..", "Consultando los trabajadores");

    $.ajax({
        url: 'TR_TRABAJADOR_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "trCedula",
            valor: trCedula,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // se oculta el mensaje de espera
            ocultarModal("modalMensajes");
            $('#formSolicitudTeletrabajo').trigger("reset");

            //se carga la información en el formulario
            if (data[0].trEstado === "A") {
                consultarPuestoByCodigo(data[0].ptPuesto);
                $("#jefatura").val(data[0].trJefatura);
                $("#usuario").val(data[0].trUsuario);
                $("#nombre").val(data[0].trNombre + " " + data[0].trApellido1 + " " + data[0].trApellido2);
                $("#cedula").val(data[0].trCedula);
                $("#puesto").val(data[0].ptPuesto);
                $("#fechaEntradaPuesto").val(data[0].trPtFechaEntrada);
                $("#fechaIngreso").val(data[0].trFechaIngreso);
            }

            ocultarModal("modalMensajes");
        },
        type: 'POST',
        dataType: "json"
    });

    ocultarModal("modalMensajes");
}

//gerencias
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
            dibujarComboGerencias(data);
            // se oculta el modal esta funcion se encuentra en el utils.js
            ocultarModal("modalMensajes");

        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarComboGerencias(dataJson) {

    for (var i = 0; i < dataJson.length; i++) {
        $("#gerencia").append($("<option value=\"" + dataJson[i].grCodigo + "\">" + dataJson[i].grDescripcion + "</option>"));
    }
}

//divisiones
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
            dibujarComboDivisiones(data);
            // se oculta el modal esta funcion se encuentra en el utils.js
            ocultarModal("modalMensajes");

        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarComboDivisiones(dataJson) {

    for (var i = 0; i < dataJson.length; i++) {
        $("#division").append($("<option value=\"" + dataJson[i].dvCodigo + "\">" + dataJson[i].dvDescripcion + "</option>"));
    }
}

//direcciones
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
            dibujarComboDirecciones(data);
            // se oculta el modal esta funcion se encuentra en el utils.js
            ocultarModal("modalMensajes");

        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarComboDirecciones(dataJson) {

    for (var i = 0; i < dataJson.length; i++) {
        $("#direccion").append($("<option value=\"" + dataJson[i].drCodigo + "\">" + dataJson[i].drDescripcion + "</option>"));
    }
}

//areas
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
            dibujarComboAreas(data);
            // se oculta el modal esta funcion se encuentra en el utils.js
            ocultarModal("modalMensajes");

        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarComboAreas(dataJson) {

    for (var i = 0; i < dataJson.length; i++) {
        $("#area").append($("<option value=\"" + dataJson[i].arCodigo + "\">" + dataJson[i].arDescripcion + "</option>"));
    }
}

//puestos
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
            dibujarComboPuestos(data);
            // se oculta el modal esta funcion se encuentra en el utils.js
            ocultarModal("modalMensajes");

        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarComboPuestos(dataJson) {

    for (var i = 0; i < dataJson.length; i++) {
        $("#puesto").append($("<option value=\"" + dataJson[i].ptCodigo + "\">" + dataJson[i].ptDescripcion + "</option>"));
    }
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
            //se carga la información en el formulario
            $("#gerencia").val(data.grGerencia);
            $("#division").val(data.dvDivision);
            $("#direccion").val(data.drDireccion);
            $("#area").val(data.arArea);

            ocultarModal("modalMensajes");
        },
        type: 'POST',
        dataType: "json"
    });

    ocultarModal("modalMensajes");
}

//dias
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
            dibujarCheckboxDias(data);
            // se oculta el modal esta funcion se encuentra en el utils.js
            ocultarModal("modalMensajes");

        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarCheckboxDias(dataJson) {

    var days = ["L", "M", "G", "J", "V"];

    for (var j = 0; j < days.length; j++) {
        for (var i = 0; i < dataJson.length; i++) {
            if (dataJson[i].diCodigo === days[j]) {
                $("#groupDias").append($('<label class="checkbox-inline"><input type="checkbox" name="dia" value="' + dataJson[i].diCodigo + '">' + dataJson[i].diDescripcion + '</label>'));
            }
        }
    }
}

function validarDias(){
    var chckbxDias = document.getElementsByName("dia"); 
    var validar = false;
    
    for(var i=0,l=chckbxDias.length;i<l;i++)
    {
        if(chckbxDias[i].checked)
        {
            validar=true;
            break;
        }
    }
    
    return validar;
}

//equipo tecnologico
function consultarEquipoTecnologico() {
    mostrarModal("modalMensajes", "Espere por favor..", "Consultando la información de equipo tecnológico en la base de datos");
    //Se envia la información por ajax
    $.ajax({
        url: 'ET_EQUIPO_TECNOLOGICO_Servlet',
        data: {
            accion: "consultarEquipoTecnologico"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de los días en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            dibujarCheckboxEquipoTecnologico(data);
            // se oculta el modal esta funcion se encuentra en el utils.js
            ocultarModal("modalMensajes");

        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarCheckboxEquipoTecnologico(dataJson) {

    for (var i = 0; i < dataJson.length; i++) {
        $("#groupEquipoTecnologico").append($('<label class="checkbox-inline"><input type="checkbox" name="equipoTecnologico" value="' + dataJson[i].etCodigo + '">' + dataJson[i].etDescripcion + '</label>'));
    }
}

function validarEquipoTecnologico()
{
    var chckbxEquipoTecnologico = document.getElementsByName("equipoTecnologico"); 
    var validar = false;
    
    for(var i=0,l=chckbxEquipoTecnologico.length;i<l;i++)
    {
        if(chckbxEquipoTecnologico[i].checked)
        {
            validar=true;
            break;
        }
    }
    
    return validar;
}

//solicitud
function guardar() {

    if (validar()) {
        //Se envia la información por ajax
        $.ajax({
            url: 'SL_SOLICITUD_Servlet',
            data: {
                accion: "agregarSolicitud",
                slJustificacion: $("#justificacion").val(),
                slFecha: Date.now(),
                slModalidad: $("#modalidad").val(),
                slTiempo: $("#tiempo").val(),
                slConectividad: $("#conectividad").val(),
                slTelefonia: $("#telefonia").val(),
                trTrabajador: $("#usuario").data('date'),
                slBeneficios: $("#beneficios").val(),
                slResultado: "P"
            },
            error: function () { //si existe un error en la respuesta del ajax
                mostrarMensaje("alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
            },
            success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                var respuestaTxt = data.substring(2);
                var tipoRespuesta = data.substring(0, 2);
                if (tipoRespuesta === "C~") {
                    mostrarMensaje("alert alert-success", respuestaTxt, "Correcto!");
                    $('#formSolicitudTeletrabajo').trigger("reset");
                    //guardarProcesoSolicitud();
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
    $("#groupJustificacion").removeClass("has-error");
    $("#groupBeneficios").removeClass("has-error");
    $("#groupModalidad").removeClass("has-error");
    $("#groupTiempo").removeClass("has-error");
    $("#groupDias").removeClass("has-error");
    $("#groupConectividad").removeClass("has-error");
    $("#groupTelefonia").removeClass("has-error");
    $("#groupEquipoTecnologico").removeClass("has-error");

    if ($("#usuario").val() === "") {
        validacion = false;
        alert("Para enviar la solicitud debe de buscar a un candidato.");
    }

    if ($("#justificacion").val() === "") {
        $("#groupJustificacion").addClass("has-error");
        validacion = false;
    }
    if ($("#beneficios").val() === "") {
        $("#groupBeneficios").addClass("has-error");
        validacion = false;
    }
    if ($("#modalidad").val() === "") {
        $("#groupModalidad").addClass("has-error");
        validacion = false;
    }
    if ($("#tiempo").val() === "") {
        $("#groupTiempo").addClass("has-error");
        validacion = false;
    }

    if (!validarDias()) {
        validacion = false;
        $("#groupDias").addClass("has-error");
    }

    if ($("#conectividad").val() === "") {
        $("#groupConectividad").addClass("has-error");
        validacion = false;
    }
    if ($("#telefonia").val() === "") {
        $("#groupTelefonia").addClass("has-error");
        validacion = false;
    }

    if (!validarEquipoTecnologico()) {
        validacion = false;
        $("#groupEquipoTecnologico").addClass("has-error");
    }

    return validacion;
}