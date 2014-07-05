$(document).ready( function(){
	$("#btn_create_user").click( function(){
		window.location = "/administracion/usuario/crear" ;
	} ) ;
	loadUsers() ;
} ) ;

function testAlert() {
	alert("test test test test test test test test test test test test");
}

function loadUsers(){
	$.ajax({
		type: "POST" ,
		url: "/administracion/usuario/listar" ,
		data: JSON.stringify( {} ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			fillTable( data ) ;
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}

function fillTable( data ){
	var estado = [ "Inactivo" , "Activo" ] ;
	var origen = [ "Externo" , "Interno" ] ;
	for( var i = 0 ; i < data.length ; i++){
		$( "#list_users" ).append(
				$("<tr>")
					.append( $( "<td>" ).text( data[ i ].idusuario ) )
					.append( $( "<td>" ).text( data[ i ].nombreusuario ) )
					.append( $( "<td>" ).text( data[ i ].perfil.nombreperfil ) )
					.append( $( "<td>" ).text( origen[ data[ i ].esinterno ] ) )
					.append( $( "<td>" ).text( estado[ data[ i ].estado ] ) )
					.append( $( "<td>" , { id: "botones_"+data[ i ].idusuario } ) )
					) ;
				$("#botones_"+data[ i ].idusuario)
					.append( $( "<a>" ).text( "Editar" ).attr( "href" , "/administracion/usuario/editar/" + data[ i ].idusuario ).button() )
					.append( $( "<button>" , { class: "deleteuser" , name: i } ).text( "Eliminar" ).attr( "href" , "/administracion/usuario/eliminar/" + data[ i ].idusuario ).button() )
	}
	$( ".deleteuser" ).click( function(){
		var pos = $( this ).attr( "name" ) ;
		if( data[ pos ].estadoconexion == 1 ){
			alert( "No puede eliminarse porque el usuario se encuentra conectado." ) ;
			return ;
		}
		var x = confirm( "¿Seguro que desea eliminar a este usuario?" ) ;
		if( x ) window.location = $( this ).attr( "href" ) ;
	} ) ;
}
