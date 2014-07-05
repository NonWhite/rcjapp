package controllers;

import helpers.Utils;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import configuration.Secured;
import models.AreaTrabajo;
import models.Perfil;
import models.TipoDocumento;
import models.Usuario;
import play.mvc.*;
import views.html.user.*;
import views.html.* ;

@Security.Authenticated( Secured.class )
public class UserController extends Controller {
    public static Result home(){
    	return redirect( session( "paginainicio" ) ) ;
    }
    
    public static Result index(){
    	if( !Secured.hasPermission() ) return ok( notallowed.render() ) ;
    	return ok( index.render() ) ;
    }
    
    // AJAX CALL
    public static Result listUsers(){
    	List<Usuario> usuarios = Usuario.protectData( Usuario.list() ) ;
    	return ok( Utils.toJson( usuarios ) ) ;
    }
    
    public static Result createUserGet(){
    	if( !Secured.hasPermission() ) return ok( notallowed.render() ) ;
    	return ok( create.render() ) ;
    }
    
    // AJAX CALL
    public static Result createUserPost(){
    	JsonNode json = request().body().asJson() ;
    	Perfil perfil = Perfil.getPerfilById( json.get( "idperfil" ).asInt() ) ;
    	
    	Usuario user = new Usuario() ;
		user.esinterno = json.get( "esinterno" ).asInt() ;
		user.nombre = json.get( "nombre" ).asText() ;
		user.apellidopaterno = json.get( "appaterno" ).asText() ;
		user.apellidomaterno = json.get( "apmaterno" ).asText() ;
		user.tipodocumento = TipoDocumento.getTipoDocumentoById( json.get( "idtipodocumento" ).asInt() ) ;
		user.nrodocumento = json.get( "nrodocumento" ).asText() ;
		user.correo = json.get( "correo" ).asText() ;
		user.areatrabajo = AreaTrabajo.getAreaTrabajoById( json.get( "idareatrabajo" ).asInt() ) ;
		user.nombreusuario = json.get( "user" ).asText() ;
		user.password = json.get( "password" ).asText() ;
		user.perfil = perfil ;
		user.estado = 1 ;
		user.estadoconexion = Usuario.ESTADO_CONEXION_NO_CONECTADO ;
		if( Usuario.exists( user.nombreusuario , user.nrodocumento , -1 ) ) return ok( Utils.getGenericResponse() ) ;
		Usuario.create( user ) ;
		return ok( Utils.toJson( user.idusuario ) ) ;
    }
    
    public static Result editUserGet( Integer idusuario ){
    	if( !Secured.hasPermission() ) return ok( notallowed.render() ) ;
    	return ok( edit.render( idusuario ) ) ;
    }
    
    // AJAX CALL
    public static Result editUserPost(){
    	JsonNode json = request().body().asJson() ;
    	Usuario user = Usuario.getUsuarioById( json.get( "idusuario" ).asInt() ) ;
    	user.esinterno = json.get( "esinterno" ).asInt() ;
    	user.nombre = json.get( "nombre" ).asText() ;
    	user.apellidopaterno = json.get( "appaterno" ).asText() ;
    	user.apellidomaterno = json.get( "apmaterno" ).asText() ;
    	user.tipodocumento = TipoDocumento.getTipoDocumentoById( json.get( "idtipodocumento" ).asInt() ) ;
    	user.nrodocumento = json.get( "nrodocumento" ).asText() ;
    	user.correo = json.get( "correo" ).asText() ;
    	user.areatrabajo = AreaTrabajo.getAreaTrabajoById( json.get( "idareatrabajo" ).asInt() ) ;
    	user.estado = json.get( "estado" ).asInt() ;
    	user.perfil = Perfil.getPerfilById( json.get( "idperfil" ).asInt() ) ;
    	String password = json.get( "password" ).asText() ;
    	if( password != null && !password.isEmpty() ) user.password = password ;
    	if( Usuario.exists( user.nombreusuario , user.nrodocumento , user.idusuario ) ) return ok( Utils.getGenericResponse() ) ;
    	user.update() ;
    	return ok( Utils.toJson( user.idusuario ) ) ;
    }
    
    public static Result deleteUser( Integer idusuario ){
    	if( !Secured.hasPermission() ) return ok( notallowed.render() ) ;
    	Usuario.delete( idusuario ) ;
    	return redirect( routes.UserController.index() ) ;
    }
    
    // AJAX CALL
    public static Result getUsuario(){
    	JsonNode json = request().body().asJson() ;
		Usuario usuario = Usuario.getUsuarioById( json.get( "idusuario" ).asInt() ).protectInformation() ;
		return ok( Utils.toJson( usuario ) ) ;
    }
}