package helpers;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	public static JsonNode toJson( Object obj ){
		return new ObjectMapper().valueToTree( obj ) ;
	}
	
	public static JsonNode getGenericResponse(){
		return toJson( new GenericResponse() ) ;
	}
	
	public static JsonNode getGenericResponse( String status ){
		return toJson( new GenericResponse( status ) ) ;
	}

	public static String encrypt( String pass ){
		try{
			MessageDigest md = MessageDigest.getInstance( "MD5" ) ;
			md.update( pass.getBytes() ) ;
			byte[] digest = md.digest() ;
			StringBuffer sb = new StringBuffer() ;
			for( byte b : digest ) sb.append( String.format( "%02x" , b & 0xff ) ) ;
			return sb.toString() ;
		}catch( Exception e ){
			return pass ;
		}
	}
	
	public static Date strToDate( String f ){
		Date d ;
		try{
			d = new SimpleDateFormat( "dd/MM/yyyy" ).parse( f ) ;
		} catch( Exception e ){
			d = null ;
		}
		return d ;
	}
	
	public static String formatDate( Date date ){
		return new SimpleDateFormat( "yyyy-MM-dd'T'hh:mm" ).format( date ) ;
	}
	
	public static double round( double d , int numPlaces ){
		int n = (int) Math.pow( 10 , numPlaces ) ;
		return (double) Math.round( d * n ) / n ;
	}
}