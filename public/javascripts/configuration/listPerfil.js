$( document ).ready( function(){
	$( "#btn_create_perfil" ).click( function(){
		window.location = "/administracion/perfil/crear" ;
	} ) ;
	loadPerfiles() ;
} ) ;

function loadPerfiles(){
	$.ajax( {
		type: "POST" ,
		url: "/administracion/perfil/listar" ,
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
	var estados = [ "Inactivo" , "Activo" ] ;
	for( var i = 0 ; i < data.length ; i++){
		$( "#list_perfiles" ).append(
				$( "<tr>" )
					.append( $( "<td>" ).text( data[ i ].idperfil ) )
					.append( $( "<td>" ).text( data[ i ].nombreperfil) )
					.append( $( "<td>" ).text( estados[ data[ i ].estado ] ) )
					.append( $( "<td>" , { id: "botones_"+data[ i ].idperfil } ) )
				);

				$("#botones_"+data[ i ].idperfil)
					.append( $( "<a>" ).text( "Editar" ).attr( "href" , "/administracion/perfil/editar/" + data[ i ].idperfil ).button() )
					.append( $( "<a>" ).text( "Eliminar" ).attr( "href" , "/administracion/perfil/eliminar/" + data[ i ].idperfil ).button() )  ;
	}
}