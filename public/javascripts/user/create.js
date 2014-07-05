$( document ).ready( function(){
	init() ;
	validations() ;
} ) ;

function createUser(){
	var send = { nombre: $( "#nombre" ).val() , 
				appaterno: $( "#appaterno" ).val() ,
				apmaterno: $( "#apmaterno" ).val() ,
				idtipodocumento: $( "#cbx_tipo_doc" ).val() ,
				nrodocumento: $( "#nro_documento" ).val() ,
				correo: $( "#mail" ).val() ,
				idareatrabajo: $( "#cbx_area_trabajo" ).val() ,
				user: $( "#username" ).val() ,
				password: $( "#password" ).val() ,
				idperfil: $( "#cbx_perfil" ).val() ,
				cantidad: $( "#cantidad" ).val() ,
				esinterno: $( "#cbx_origen" ).val() } ;
	$.ajax( {
		type: "POST" ,
		url: "/administracion/usuario/crear" ,
		data: JSON.stringify( send ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			if( data.status == "OK" ){
				alert( "Ya existe un usuario con este nombre y/o nro de documento" ) ;
				return ;
			}
			window.location = "/administracion/usuario/ver"
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}

function validations(){
	$( "#createform" ).validate( {
		rules: {
			"nombre" : "required" ,
			"appaterno" : "required" ,
			"cbx_tipo_doc" : "required" ,
			"nro_documento" : "required" ,
			"mail" : { required: true , email: true } ,
			"cbx_area_trabajo" : "required" ,
			"username" : "required" ,
			"password" : "required" ,
			"cbx_perfil" : "required" ,
			"cbx_origen" : "required"
		} ,
		messages: {
			"nombre" : "Debe ingresar el nombre" ,
			"appaterno" : "Debe ingresar el apellido paterno" ,
			"cbx_tipo_doc" : "Debe seleccionar un tipo de documento de identidad" ,
			"nro_documento" : "Debe ingresar su número de documento" ,
			"mail" : { required: "Debe ingresar un correo electrónico" , email: "Debe ingresar un correo en formato correcto" } ,
			"cbx_area_trabajo" : "Debe seleccionar un área de trabajo" ,
			"username" : "Debe ingresar un nombre de usuario" ,
			"password" : "Debe ingresar una contraseña de usuario" ,
			"cbx_perfil" : "Debe seleccionar un perfil" ,
			"cbx_origen" : "Debe seleccionar el origen del usuario"
		} ,
		errorElement: "em" ,
		debug: true ,
		submitHandler: function(){
			createUser() ;
		}
	} ) ;
}