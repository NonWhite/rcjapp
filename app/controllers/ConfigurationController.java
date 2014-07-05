package controllers;

import helpers.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

import models.Accion;
import models.AreaTrabajo;
import models.Modulo;
import models.Perfil;
import models.PrivilegioSeguridad;
import models.PrivilegioSeguridadId;
import models.ResourceString;
import models.TipoDocumento;
import models.Vista;
import configuration.Secured;
import play.mvc.* ;
import views.html.configuration.* ;
import views.html.* ;

@Security.Authenticated( Secured.class )
public class ConfigurationController extends Controller {
	public static HashMap<String,Integer> mapModulos = new HashMap<String,Integer>() ;
	public static HashMap<String,Integer> mapVistas = new HashMap<String,Integer>() ;
	public static HashMap<String,Integer> mapAcciones = new HashMap<String,Integer>() ;
	public static HashMap<String,HashMap<String,String>> resourcesString = new HashMap<>() ;
	
	public static String getResourceString( String resource ){
		String lang = session( "lang" ) ;
		String resp = ( resourcesString.get( resource ) != null ? resourcesString.get( resource ).get( lang ) : resource ) ; 
		return resp ;
	}
	
	public static void loadResourcesString(){
		List<ResourceString> lst = ResourceString.list() ;
		for( ResourceString rs : lst ){
			HashMap<String,String> languages = new HashMap<String,String>() ;
			languages.put( "esp" , rs.esp ) ;
			languages.put( "eng" , rs.eng ) ;
			resourcesString.put( rs.keystring , languages ) ;
		}
	}
	
	public static void loadRoutesMap(){
		List<Modulo> lstModulos = Modulo.list() ;
		for( Modulo modulo : lstModulos ){
			mapModulos.put( modulo.nombreruta , modulo.idmodulo ) ;
			Set<Vista> lstVistas = modulo.vistas ;
			for( Vista vista : lstVistas ){
				mapVistas.put( vista.nombreruta ,  vista.idvista ) ;
				Set<Accion> lstAcciones = vista.acciones ;
				for( Accion accion : lstAcciones ) mapAcciones.put( accion.nombreruta , accion.idaccion ) ;
			}
		}
	}
	
	// AJAX CALL
	public static Result changeLanguage(){
		JsonNode json = request().body().asJson() ;
		System.out.println( json.get( "lang" ) ) ;
		session( "lang" , json.get( "lang" ).asText() ) ;
		return ok( Utils.getGenericResponse() ) ;
	}

	public static Result indexPerfil(){
		if( !Secured.hasPermission() ) return ok( notallowed.render() ) ;
		return ok( listPerfil.render() ) ;
	}
	
	public static Result createPerfilGet(){
		if( !Secured.hasPermission() ) return ok( notallowed.render() ) ;
		return ok( createPerfil.render() ) ;
	}
	
	// AJAX CALL
	public static Result createPerfilPost(){
		JsonNode json = request().body().asJson() ;
		Perfil perfil = new Perfil() ;
		perfil.nombreperfil = json.get( "nombreperfil" ).asText() ;
		if( Perfil.exists( perfil.nombreperfil , -1 ) ) return ok( Utils.getGenericResponse() ) ; 
		perfil.estado = 1 ;
		Perfil.create( perfil ) ;
		
		JsonNode lstModulos = json.get( "modulos" ) ;
		for( JsonNode modulo : lstModulos ){
			Integer idmodulo = modulo.get( "idmodulo" ).asInt() ;
			JsonNode lstVistas = modulo.get( "vistas" ) ;
			for( JsonNode vista : lstVistas ){
				Integer idvista = vista.get( "idvista" ).asInt() ;
				JsonNode lstAcciones = vista.get( "acciones" ) ;
				for( JsonNode accion : lstAcciones ){
					Integer idaccion = accion.get( "idaccion" ).asInt() ;
					Integer estado = ( accion.get( "estado" ).asBoolean() ? 1 : 0 ) ;
					PrivilegioSeguridad privSeg = new PrivilegioSeguridad() ;
					PrivilegioSeguridadId idPrivSeg = new PrivilegioSeguridadId( perfil.idperfil , idmodulo , idvista , idaccion ) ;
					privSeg.idprivilegioseguridad = idPrivSeg ;
					privSeg.estado = estado ;
					PrivilegioSeguridad.create( privSeg ) ;
				}
			}
		}
		return ok( Utils.toJson( perfil.idperfil ) ) ;
	}
	
	public static Result editPerfilGet( Integer idperfil ){
		return ok( editPerfil.render( idperfil ) ) ;
	}
	
	public static Result editPerfilPost(){
		JsonNode json = request().body().asJson() ;
		Perfil perfil = Perfil.getPerfilById( json.get( "idperfil" ).asInt() ) ;
		perfil.nombreperfil = json.get( "nombreperfil" ).asText() ;
		if( Perfil.exists( perfil.nombreperfil , perfil.idperfil ) ) return ok( Utils.getGenericResponse() ) ;
		perfil.estado = json.get( "estado" ).asInt() ;
		Perfil.update( perfil ) ;
		
		JsonNode lstModulos = json.get( "modulos" ) ;
		for( JsonNode modulo : lstModulos ){
			Integer idmodulo = modulo.get( "idmodulo" ).asInt() ;
			JsonNode lstVistas = modulo.get( "vistas" ) ;
			for( JsonNode vista : lstVistas ){
				Integer idvista = vista.get( "idvista" ).asInt() ;
				JsonNode lstAcciones = vista.get( "acciones" ) ;
				for( JsonNode accion : lstAcciones ){
					Integer idaccion = accion.get( "idaccion" ).asInt() ;
					Integer estado = ( accion.get( "estado" ).asBoolean() ? 1 : 0 ) ;
					PrivilegioSeguridad privSeg = PrivilegioSeguridad.getPrivilegioByIds( perfil.idperfil , idmodulo , idvista , idaccion ) ;
					privSeg.estado = estado ;
					PrivilegioSeguridad.update( privSeg ) ;
				}
			}
		}
		return ok( Utils.toJson( perfil.idperfil ) ) ;
	}
	
	public static Result deletePerfil( Integer idperfil ){
		if( !Secured.hasPermission() ) return ok( notallowed.render() ) ;
		Perfil.delete( idperfil ) ;
		return redirect( routes.ConfigurationController.indexPerfil() ) ;
	}

	// AJAX CALL
	public static Result getPerfilStructure(){
		return ok( Utils.toJson( Perfil.getPerfilStructure() ) ) ;
	}
	
	// AJAX CALL
	public static Result getPerfilData(){
		JsonNode json = request().body().asJson() ;
		return ok( Utils.toJson( Perfil.getPerfilData( json.get( "idperfil" ).asInt() ) ) ) ;
	}
	
    // AJAX CALL
    public static Result listPerfiles(){
    	List<Perfil> lstPerfiles = Perfil.list() ;
    	return ok( Utils.toJson( lstPerfiles ) ) ;
    }
    
    // AJAX CALL
    public static Result listTipoDocumentos(){
    	List<TipoDocumento> lstTipoDocumento = TipoDocumento.list() ;
    	return ok( Utils.toJson( lstTipoDocumento ) ) ;
    }
	
    // AJAX CALL
    public static Result listAreaTrabajo(){
    	List<AreaTrabajo> lstAreaTrabajo = AreaTrabajo.list() ;
    	return ok( Utils.toJson( lstAreaTrabajo ) ) ;
    }
}