var rutaWS = "@routes.SessionController.sessionWSroot().webSocketURL(request)" ;
var WS = window[ 'MozWebSocket' ] ? MozWebSocket : WebSocket ;
var socket ;
var perfil = null ;

$( function(){
	$( ".tabs-group" ).tabs() ;
	$( "#cbx_idioma" ).val( '@session().get( "lang" )' ) ;
	$( "#cbx_idioma" ).change( function(){
		changeLanguage() ;
	} ) ;
	loadStructure() ;
	socket = new WS( rutaWS += "sessionWS?uuid=" + uuid ) ;
	var receiveEvent = function(event) {
		var data = JSON.parse( event.data ) ;
		switch( data.tipo ){
			case 0: 
				showDialog( data.obj.message , data.obj.fecha , data.obj.tiempo ) ;
				break ;
			default:	alert( data.message ) ;
						try{
							addNotificacion( data.fecha , data.message ) ;
						} catch( e ){
							console.log( e ) ;
						}
		}
	} ;
	socket.onmessage = receiveEvent ;
} ) ;

function changeView( path ){
	$( "#contentView" ).attr( "src" , path ) ;
}

function showDialog( mensaje , fechaHora , tiempoFuera ){
	$( "#dialog_mensaje_alerta" ).text( mensaje + ". A las " + fechaHora + " durante " + tiempoFuera + " minutos." ) ;
	$( "#dialog_aviso_alerta" ).dialog( {
		width: 300 ,
		height: 200 ,
		modal: true ,
		buttons: {
			"Ok": function(){
				$( this ).dialog( "close" ) ;
			}
		} ,
		close: function(){
		}
	} ) ;
}

function loadStructure(){
	if( perfil == null ) perfil = $( "#menuperfil" ).val() ;
	$.ajax( {
		type: "POST" ,
		url: "/administracion/perfil/estructura" ,
		data: JSON.stringify( {} ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			var lstModulos = [] ;	
			for( var i = 0 ; i < data.length ; i++){
				var lstVistas = [] ;
				for( var j = 0 ; j < data[ i ].vistas.length ; j++){
					lstVistas.push( {
						id: data[ i ].vistas[ j ].idvista ,
						nombre: data[ i ].vistas[ j ].nombrevista ,
						ruta: data[ i ].vistas[ j ].nombreruta
					} ) ;
				}
				lstModulos.push( {
					id: data[ i ].idmodulo ,
					nombre: data[ i ].nombremodulo ,
					ruta: data[ i ].nombreruta ,
					vistas: lstVistas
				} ) ;
			}
			loadMenu( lstModulos ) ;
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}

function loadMenu( structure ){
	var send = { idperfil: perfil } ;
	var idAccionVer = 1 ;
	$.ajax( {
		type: "POST" ,
		url: "/administracion/perfil/data" ,
		data: JSON.stringify( send ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			$( "#menuperfil" ).remove() ;
			var permisos = data.permisos , lstPermisos = new Object() ;
			for( var i = 0 ; i < permisos.length ; i++){
				var mod = permisos[ i ].idprivilegioseguridad.idmodulo ;
				if( typeof( lstPermisos[ mod ] ) == 'undefined' ) lstPermisos[ mod ] = new Object() ;
				var vis = permisos[ i ].idprivilegioseguridad.idvista ;
				if( typeof( lstPermisos[ mod ][ vis ] ) == 'undefined' ) lstPermisos[ mod ][ vis ] = new Object() ;
				var acc = permisos[ i ].idprivilegioseguridad.idaccion ;
				if( typeof( lstPermisos[ mod ][ vis ][ acc ] ) == 'undefined' ) lstPermisos[ mod ][ vis ][ acc ] = permisos[ i ].estado ;
			}
			var lstMenu = [] ;
			for( var i = 0 ; i < structure.length ; i++){
				var mod = structure[ i ] ;
				var idmod = mod.id ;
				var lstVistas = [] ;
				for( var j = 0 ; j < mod.vistas.length ; j++){
					var vis = mod.vistas[ j ] ;
					var idvis = vis.id ;
					if( lstPermisos[ idmod ][ idvis ][ idAccionVer ] == 1 )
						lstVistas.push( {
							nombre: vis.nombre ,
							ruta: vis.ruta
						} ) ;
				}
				if( lstVistas.length > 0 )
					lstMenu.push( {
						nombre: mod.nombre ,
						ruta: mod.ruta ,
						vistas: lstVistas
					} ) ;
			}
			fillMenu( lstMenu ) ;
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}

function fillMenu( lstMenu ){
	var menu = $( "#menu" ) ;
	for( var i = 0 ; i < lstMenu.length ; i++){
		var modulo = lstMenu[ i ] ;
		var modTitle = $( "<h3>" , { id: "menu-mod-"+(i+1) } ).text( modulo.nombre ) ;
		var modContent = $( "<ul>" ) ;
		for( var j = 0 ; j < modulo.vistas.length ; j++){
			var vista = modulo.vistas[ j ] ;
			$( modContent ).append( $( "<li>" ).append( $( "<a>" , { class: "ruta-link" } ).attr( "src" , "/" + modulo.ruta + "/" + vista.ruta + "/ver" ).text( vista.nombre ) ) ) ;
		}
		$( menu ).append( modTitle ) ;
		$( menu ).append( $( "<div>" ).append( modContent ) ) ;
	}
	$( ".ruta-link" ).click( function(){
		changeView( $( this ).attr( "src" ) ) ;
	} ) ;
	$( menu ).accordion() ;
}

function changeLanguage(){
	var send = { lang: $( "#cbx_idioma" ).val() } ;
	$.ajax( {
		type: "POST" ,
		url: "/configuracion/idioma" ,
		data: JSON.stringify( send ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			var src = $( "#contentView" ).attr( "src" ) ;
			$( "#contentView" ).attr( "src" , "#" ) ;
			$( "#contentView" ).attr( "src" , src ) ;
			$( "#menu" ).empty() ; // TODO: Arreglar la vista
			loadStructure() ;
		} ,
		error: function( data ){
			console.log( data ) ;
		}
	} ) ;
}