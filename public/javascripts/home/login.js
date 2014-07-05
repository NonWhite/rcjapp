$( document ).ready( function(){
	init() ;
} ) ;

function init(){
	$( "#btn_login" ).click( function(){ login() ; } ) ;
	$( "#campo_uno" ).focus() ;
	$( "#campo_uno" ).keypress( function( e ){ if( e.which == 13 ) $( "#btn_login" ).click() ; } ) ;
	$( "#campo_dos" ).keypress( function( e ){ if( e.which == 13 ) $( "#btn_login" ).click() ; } ) ;
}

function login(){
	var valorcampo1 = $( "#campo_uno" ).val() ;
	var valorcampo2 = $( "#campo_dos" ).val() ;
	var send = { campo1: valorcampo1, campo2: valorcampo2 } ;
	$.ajax( {
		type: "POST" ,
		url: "login" ,
		data: JSON.stringify( send ) ,
		dataType: "json" ,
		contentType: "application/json" ,
		success: function( data ){
			window.location = "/" ;
		} ,
		error: function( data ){
			window.location.reload() ;
			console.log( data ) ;
		}
	} ) ;
}