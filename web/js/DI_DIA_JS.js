$(document).ready( function(){
    consultarDias();    
});

function consultarDias() {
    //mostrarModal("myModal", "Espere por favor..", "Consultando la información de días en la base de datos");
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
            //ocultarModal("myModal");

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
    //row.append($("<th><b>ACCIÓN</th>"));
    
    //carga la tabla con el json devuelto
    for (var i = 0; i < dataJson.length; i++) {
        dibujarFila(dataJson[i]);
    }
}

function dibujarFila(rowData) {
    //Cuando dibuja la tabla en cada boton se le agrega la funcionalidad de cargar o eliminar la informacion
    //de una persona
    
    var row = $("<tr />");
    $("#tablaDias").append(row); 
    row.append($("<td>" + rowData.diCodigo + "</td>"));
    row.append($("<td>" + rowData.diDescripcion + "</td>"));
//    row.append($('<td><button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="consultarPersonaByID('+rowData.pkCedula+');">'+
//                        '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>'+
//                    '</button>'+
//                    '<button type="button" class="btn btn-default btn-xs" aria-label="Left Align" onclick="confirmar();">'+
//                        '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>'+
//                    '</button></td>'));
}

