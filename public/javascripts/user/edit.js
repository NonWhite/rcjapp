$( document ).ready( function(){
	init() ;
	loadUser() ;
	$( "#btn_edit_user" ).click( function(){
		editUser() ;
	} ) ;
} ) ;

function loadUser(){
	var send = { idusuario: $( "#idusuario" ).val() }
	$.ajax( {
		type: "POST" ,
		url: "/administracion/usuario/get" ,
		data: JSON.stringify( send ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			fillUsuario( data ) ;
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}

function fillUsuario( data ){
	$( "#nombre" ).val( data.nombre ) ;
	$( "#appaterno" ).val( data.apellidopaterno ) ;
	$( "#apmaterno" ).val( data.apellidomaterno ) ;
	$( "#cbx_tipo_doc" ).val( data.tipodocumento.idtipodocumento ) ;
	$( "#nro_documento" ).val( data.nrodocumento ) ;
	$( "#mail" ).val( data.correo ) ;
	$( "#cbx_area_trabajo" ).val( data.areatrabajo.idareatrabajo ) ;
	$( "#username" ).val( data.nombreusuario ) ;
	$( "#cbx_perfil" ).val( data.perfil.idperfil ) ;
	if( data.iddatosagente != null ) $( "#cantidad" ).val( data.iddatosagente.cantidadMaximaChats ) ;
	$( "#cbx_estado" ).val( data.estado ) ;
	$( "#cbx_origen" ).val( data.esinterno ) ;
}

function editUser(){
	var send = { idusuario: $( "#idusuario" ).val() ,
				nombre: $( "#nombre" ).val() ,
				appaterno: $( "#appaterno" ).val() ,
				apmaterno: $( "#apmaterno" ).val() ,
				idtipodocumento: $( "#cbx_tipo_doc" ).val() ,
				nrodocumento: $( "#nro_documento" ).val() ,
				correo: $( "#mail" ).val() ,
				idareatrabajo: $( "#cbx_area_trabajo" ).val() ,
				password: $( "#password" ).val() ,
				idperfil: $( "#cbx_perfil" ).val() ,
				cantidad: $( "#cantidad" ).val() ,
				estado: $( "#cbx_estado" ).val() , 
				esinterno: $( "#cbx_origen" ).val() } ;
	$.ajax({
		type: "POST" ,
		url: "/administracion/usuario/editar" ,
		data: JSON.stringify( send ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			if( data.status == "OK" ){
				alert( "Ya existe un usuario con este nombre y/o nro de documento" ) ;
				return ;
			}
			window.location = "/administracion/usuario/ver" ;
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}