package models;

import static akka.pattern.Patterns.ask;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import models.UserSession.Join;
import models.UserSession.Quit;
import play.libs.Akka;
import play.libs.Json;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.WebSocket;
import play.mvc.WebSocket.Out;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class UserSessionContainer {
	private Map<String, InstanciaConexion> members ;
	private ActorRef room ;
	private UserSession userSession ;
	private int idUsuario;

	public UserSessionContainer(int idUsuario){
		this.idUsuario = idUsuario;
		members = new HashMap<String, InstanciaConexion>() ;
		room = Akka.system().actorOf( UserSession.props( this ) ) ;
	}
	
    public static void join( final UserSessionContainer crc , WebSocket.In<JsonNode> in , WebSocket.Out<JsonNode> out, final String uuid ) throws Exception{
        String result = (String)Await.result( ask( crc.room , new Join( uuid , out ) , 60000 ) , Duration.create( 60 , TimeUnit.SECONDS ) ) ;
        if( "OK" .equals( result ) ){
            in.onMessage( new Callback<JsonNode>(){
               public void invoke( JsonNode event ){ // TODO: recibir mensajes :D .. si es que hay :(
               } 
            } ) ;
            in.onClose( new Callback0(){
               public void invoke(){
            	   crc.room.tell( new Quit( uuid ) , null ) ;
               }
            } ) ;
        }else{
            ObjectNode error = Json.newObject() ;
            error.put( "error" , result ) ;
            out.write( error ) ;
        }
    }
    
    public ActorRef getRoom(){
    	return room ;
    }
    
    public UserSession getUserSession(){
    	System.out.println( userSession ) ;
    	return userSession ;
    }
    
    public void setUserSession( UserSession us ){
    	this.userSession = us ;
    }

    public Map<String, InstanciaConexion> getMembers(){
    	return members ;
    }
    
    public void addMember( String uuid , InstanciaConexion i ){
    	members.put( uuid , i ) ;
    }
    
    public int getIdUsuario() {
    	return idUsuario;
    }
    
    public static class InstanciaConexion {
    	final public Out<JsonNode> ws ;
    	public InstanciaConexion( Out<JsonNode> ws ){
    		this.ws = ws ;
    	}
    }
}