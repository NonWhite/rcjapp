$( document ).ready( function(){
	$( "#btn_create_perfil" ).click( function(){
		createPerfil() ;
	} ) ;
	$( "#cbx_modulo" ).change( function(){
		var len = $( "#all-views").children().length ;
		for(var i = 1 ; i <= len ; i++) $( "#modulo-" + i ).hide() ;
		$( "#modulo-" + $( "#cbx_modulo" ).val() ).show() ;
	} ) ;
	loadPerfilData() ;
} ) ;

function loadPerfilData(){
	$.ajax( {
		type: "POST" ,
		url: "/administracion/perfil/estructura" ,
		data: JSON.stringify( {} ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			fillData( data ) ;
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}

function fillData( data ){
	var formulario = $( "#all-views" ) ;
	var lstModulos = data ;
	for( var i = 0 ; i < lstModulos.length ; i++){
		$( "#cbx_modulo" ).append( $( "<option>" ).val( lstModulos[ i ].idmodulo ).html( lstModulos[ i ].nombremodulo ) ) ;
		var modulo = $( "<div>" , { id: "modulo-" + ( i + 1 ) , name: lstModulos[ i ].idmodulo , class: "tabs-group" } ) ;
		formulario.append( modulo ) ;
		modulo.hide() ;
		var lista = $( "<ul>" , { id: "lista-" + ( i + 1 ) } ) ;
		var lstVistas = lstModulos[ i ].vistas ;
		for( var j = 0 ; j < lstVistas.length ; j++) lista.append( $( "<li>" ).append( $( "<a>" ).attr( "href" , "#vista-" + (i+1) + "-" + (j+1) ).text( lstVistas[ j ].nombrevista ) ) ) ;
		modulo.append( lista ) ;
		for( var j = 0 ; j < lstVistas.length ; j++){
			var vista = $( "<div>" , { id: "vista-" + (i+1) + "-" + (j+1) , name: lstVistas[ j ].idvista } ) ;
			var lstAcciones = lstVistas[ j ].acciones ;
			for( var k = 0 ; k < lstAcciones.length ; k++ ) vista.append( $( "<input>" , { name: lstAcciones[ k ].idaccion } ).attr( "type" , "checkbox" ) ).append( lstAcciones[ k ].descripcion ).append( "<br>" ) ;
			modulo.append( vista ) ;
		}
	}
	$( "#modulo-" + $( "#cbx_modulo" ).val() ).show() ;
	$( ".tabs-group" ).tabs() ;
}

function createPerfil(){
	var send = getFormData() ;
	$.ajax( {
		type: "POST" ,
		url: "/administracion/perfil/crear" ,
		data: JSON.stringify( send ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			if( data.status == "OK" ){
				alert( "Ya existe un perfil con ese nombre" ) ;
				return ;
			}
			window.location = "/administracion/perfil/ver" ;
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}

function getFormData(){
	var lstModulos = [] ;
	var modulos = $( "#all-views" ).children() ;
	for( var i = 0 ; i < modulos.length ; i++){
		var vistas = $( modulos[ i ] ).children( "div" ) ;
		var lstVistas = [] ;
		for( var j = 0 ; j < vistas.length ; j++){
			var acciones = $( vistas[ j ] ).children( "input" ) ;
			var lstAcciones = [] ;
			for( var k = 0 ; k < acciones.length ; k++){
				lstAcciones.push( {
					idaccion: $( acciones[ k ] ).attr( "name" ) ,
					estado: $( acciones[ k ] ).is( ":checked" )
				} ) ;
			}
			lstVistas.push( {
				idvista: $( vistas[ j ] ).attr( "name" ) ,
				acciones: lstAcciones
			} ) ;
		}
		lstModulos.push( {
			idmodulo: $( modulos[ i ] ).attr( "name" ) ,
			vistas: lstVistas
		} ) ;
	}
	var data = {
		nombreperfil: $( "#nombreperfil" ).val() ,
		modulos: lstModulos
	} ;
	return data ;
}