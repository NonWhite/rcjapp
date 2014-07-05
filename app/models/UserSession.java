package models;

import helpers.Utils ;

import java.util.Date;

import models.UserSessionContainer.InstanciaConexion;
import play.libs.Json;
import play.mvc.WebSocket;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class UserSession extends UntypedActor{
	private UserSessionContainer container ;
    
    public static Props props( final UserSessionContainer container ){
    	return Props.create( new Creator<UserSession>(){
	    	private static final long serialVersionUID = 1L ;
	    	@Override
	    	public UserSession create() throws Exception{
	    		return new UserSession( container ) ;
	    	}
    	} ) ;
	}
    
    public UserSession( UserSessionContainer container ){
    	this.container = container ;
    	this.container.setUserSession( this ) ;
    }

    public void onReceive( Object message ) throws Exception{
    	System.out.println( message ) ;
        if( message instanceof Join ){
            Join join = (Join)message ;
            if( container.getMembers().containsKey( join.uuid ) )
                getSender().tell( "This uuid is already used" , getSelf() ) ;
            else{
            	container.addMember( join.uuid , new InstanciaConexion( join.channel ) ) ;
            	getSender().tell( "OK" , getSelf() ) ; // TODO: ver que hace
            	System.out.println("Se agrego una nueva instancia al usuario <" + container.getIdUsuario() + ">. Size <" + container.getMembers().size() + ">");
            }
        }else if( message instanceof Quit ){
        	Quit quit = (Quit)message ;
            container.getMembers().remove( quit.uuid ) ;
        }else{
            unhandled( message ) ;
        }
    }
    
    public void notifyAll( String text, int tipo, Object obj ){
    	Date now = new Date();
        for( String destinatario : container.getMembers().keySet() ){
            sendToWS( now.getTime(), text , destinatario, tipo, obj ) ;
        }
    }
    
    public void sendToWS( long timeMs, String text , String destinatario, int tipo, Object obj){
    	InstanciaConexion ic = container.getMembers().get( destinatario ) ;
    	ObjectNode event = Json.newObject() ;
    	event.put( "fecha" , timeMs );
    	event.put( "message" , text ) ;
    	event.put( "tipo" , tipo );
    	event.put( "obj" , Utils.toJson(obj));
        ic.ws.write( event ) ;
    }
    
    public void finalizar() {
    	for( String destinatario : container.getMembers().keySet() ) container.getMembers().get( destinatario ).ws.close() ;
    	container.getMembers().clear() ;
    }
    
    public static class Join{
    	final String uuid ;
        final WebSocket.Out<JsonNode> channel ;
        public Join( String uuid , WebSocket.Out<JsonNode> channel ){
        	this.uuid = uuid ;
            this.channel = channel ;
        }
    }
    
    public static class Quit{
    	final String uuid ;
        public Quit( String uuid ){
        	this.uuid = uuid ;
        }
    }
}