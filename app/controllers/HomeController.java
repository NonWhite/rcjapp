package controllers;

import helpers.Utils;

import com.fasterxml.jackson.databind.JsonNode;

import configuration.Secured;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Result;
import models.ParametroSistema;
import models.UserSessionContainer;
import models.Usuario;
import views.html.home.* ;

public class HomeController extends Controller {
	public static Result home(){
		return ( session( "nombrecompleto" ) == null ? ok( login.render() ) : ok( index.render() ) ) ;
	}
	
	public static Result loginForm(){
		return ( session( "nombrecompleto" ) == null ? ok( login.render() ) : redirect( routes.HomeController.home() ) ) ;
	}
	
	public static Result welcomeView(){
		return ok( welcome.render() ) ;
	}
	
	// AJAX CALL
	public static Result loginRequest(){
		JsonNode json = request().body().asJson() ;
		String valorcampo1 = json.get( "campo1" ).asText() ;
		String valorcampo2 = json.get( "campo2" ).asText() ;
		Usuario user = Usuario.login( valorcampo1 , valorcampo2 ) ;
		if( user != null ){
			SessionController.sessions.put( user.idusuario + "" ,  new UserSessionContainer(user.idusuario) ) ;
			Secured.initSession( user , Context.current() ) ;
			ParametroSistema.updateConectados() ;
			return ok( Utils.toJson( user ) ) ;
		}
		return ok() ;
	}
	
	public static Result logout(){
		session().clear() ;
		return redirect( routes.HomeController.loginForm() ) ;
	}
}