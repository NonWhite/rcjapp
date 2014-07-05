function init(){
	loadPerfil() ;
	loadTipoDocumento() ;
	loadAreaTrabajo() ;
}

function loadPerfil(){
	$.ajax( {
		type: "POST" ,
		url: "/administracion/perfil/listar" ,
		data: JSON.stringify( {} ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			fillPerfil( data ) ;
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}

function fillPerfil( data ){
	for( var i = 0 ; i < data.length ; i++ ){
		$( "#cbx_perfil" ).append(
			$( "<option>" )
				.val( data[ i ].idperfil )
				.html( data[ i ].nombreperfil )
		) ;
	}
}

function loadTipoDocumento(){
	$.ajax( {
		type: "POST" ,
		url: "/configuracion/tipodocumento/listar" ,
		data: JSON.stringify( {} ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			fillTipoDocumento( data ) ;
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}

function fillTipoDocumento( data ){
	for( var i = 0 ; i < data.length ; i++ ){
		$( "#cbx_tipo_doc" ).append(
			$( "<option>" )
				.val( data[ i ].idtipodocumento )
				.html( data[ i ].nombretipodocumento )
		) ;
	}
}

function loadAreaTrabajo(){
	$.ajax( {
		type: "POST" ,
		url: "/configuracion/areatrabajo/listar" ,
		data: JSON.stringify( {} ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			fillAreaTrabajo( data ) ;
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}

function fillAreaTrabajo( data ){
	for( var i = 0 ; i < data.length ; i++ ){
		$( "#cbx_area_trabajo" ).append(
			$( "<option>" )
				.val( data[ i ].idareatrabajo )
				.html( data[ i ].nombreareatrabajo )
		) ;
	}
}