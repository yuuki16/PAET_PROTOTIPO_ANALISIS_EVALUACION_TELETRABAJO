var trTrabajador, ttTeletrabajador;

$(function () {

    $("#btBusquedaTeletrabajador").click(function () {
        ocultarAlerta();
        consultarTrabajadorByCedula($("#trTeletrabajador").val());
    });

    $("#btLimpiarBusqueda").click(function () {
        $('#formSolicitudFinalizacion').trigger("reset");
        ocultarAlerta();
    });

    $("#guardar").click(function () {
        ocultarAlerta();
        consultarProcesoFinalizacionByTeletrabajador(ttTeletrabajador);
    });
});

//trabajadores
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
            $('#formSolicitudFinalizacion').trigger("reset");

            if (data.length > 0) {
                //se carga la información en el formulario
                if (data[0].trEstado === "A") {
                    trTrabajador = data[0].trUsuario;
                    consultarTeletrabajadorByTrabajador(data[0].trUsuario, data[0].trNombre + ' ' + data[0].trApellido1 + ' ' + data[0].trApellido2);
                } else
                {
                    mostrarAlerta("La cédula proporcionada no corresponde a ninguno de nuestros trabajadores activos.");
                }
            } else
            {
                mostrarAlerta("La cédula proporcionada no corresponde a ninguno de nuestros trabajadores activos.");
            }
            // se oculta el mensaje de espera
            ocultarModal("modalMensajes");
        },
        type: 'POST',
        dataType: "json"
    });
}

//solicitud
function guardar() {
    
    if (validar()) {
        
        //Se envia la información por ajax
        $.ajax({
            async: false,
            url: 'PF_PROCESO_FINALIZACION_Servlet',
            data: {
                accion: "agregarProcesoFinalizacion",
                ttTeletrabajador: ttTeletrabajador
            },
            error: function () { //si existe un error en la respuesta del ajax
                mostrarMensaje("alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
            },
            success: function (data) {
                if (data !== null) {
                    var tipoRespuesta = data.substring(0, 2);
                    var respuestaTxt = data.substring(2);
                    if (tipoRespuesta === "E~") {   
                        mostrarMensaje("alert alert-danger", respuestaTxt, "Error!");
                    } else
                    {
                        $('#formSolicitudFinalizacion').trigger("reset");
                        mostrarMensaje("alert alert-success", respuestaTxt, "Correcto!");
                    }
                } else {
                    mostrarMensaje("alert alert-danger", "Se genero un error, contacte al administrador", "Error!");
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

    if ($("#usuario").val() === "") {
        validacion = false;
        alert("Para enviar la solicitud debe de buscar a un teletrabajador.");
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

function consultarProcesoFinalizacionByTeletrabajador(teletrabajador)
{
    $.ajax({
        async: false,
        url: 'PF_PROCESO_FINALIZACION_Servlet',
        data: {
            accion: "consultaDinamica",
            campo: "ttTeletrabajador",
            valor: teletrabajador,
            unico: true
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("modalMensajes", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            if (data.length > 0) {
                mostrarAlerta("El teletrabajador ya posee un proceso de solicitud pendiente");
            } else
            {
                guardar();
            }
        },
        type: 'POST',
        dataType: "json"
    });
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

//teletrabajador
function consultarTeletrabajadorByTrabajador(trUsuario, nombre)
{
    var activo = false; 
    
    $.ajax({
        async: false,
        url: 'TT_TELETRABAJADOR_Servlet',
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
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].ttEstado === "A") {
                        ttTeletrabajador = data[0].ttCodigo;
                        $("#usuario").val(trUsuario);
                        $("#nombre").val(nombre);
                        activo = true;
                    }
                }
                
                if (!activo) {
                    mostrarAlerta("El trabajador seleccionado no es un teletrabajador.");
                }
            } else
            {
                mostrarAlerta("El trabajador seleccionado no es un teletrabajador.");
            }

        },
        type: 'POST',
        dataType: "json"
    });
}