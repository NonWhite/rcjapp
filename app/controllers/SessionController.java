package controllers;

import java.util.HashMap;
import java.util.List;

import models.UserSessionContainer;
import models.Usuario;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.js.* ;

public class SessionController extends Controller {
	public static HashMap<String,UserSessionContainer> sessions = new HashMap<>() ;
	
	public static Result initJs(){
		return ok( template.render() ) ;
	}
	
	public static WebSocket<JsonNode> sessionWSroot(){
		return null ;
	}
	
	public static WebSocket<JsonNode> sessionWS( final String uuid ){
		final String idusuario = session( "idusuario" ) ;
		return new WebSocket<JsonNode>(){
			public void onReady( WebSocket.In<JsonNode> in , WebSocket.Out<JsonNode> out ){
				try{
					System.out.println("Usuario <" + idusuario + ">, Old size <" + sessions.get(idusuario).getMembers().size() + ">");
					UserSessionContainer.join( sessions.get( idusuario ) ,  in ,  out ,  uuid ) ;
				}catch( NullPointerException nullEx) {
					System.out.println("Sesion cerrada, no se puede unir como participante.");
				} catch( Exception ex ){
					ex.printStackTrace() ;
				}
			}
		} ;
	}
	
	public static void sendMessageToAll( String msg , int tipo , Object obj ){
		List<Usuario> lstUsuario = Usuario.getConnectedUsers() ;
		for( Usuario usr : lstUsuario ) sessions.get( usr.idusuario + "" ).getUserSession().notifyAll( msg , tipo , obj ) ;
	}
	
	public static void sendMessageToAll(String msg) {
		
	}
	
	public static void sendMessageTo(int idUsuario, String msg) {
		sessions.get(idUsuario + "").getUserSession().notifyAll(msg, -1, null);
	}
	
	public static void sendMessageTo(int idUsuario, String msg, int tipo, Object obj) {
		sessions.get(idUsuario + "").getUserSession().notifyAll(msg, tipo, obj);
	}
}