package models;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import controllers.ConfigurationController;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@SuppressWarnings("serial")
@Entity
public class Perfil extends Model {
	@Id
	@Column( name = "idperfil" )
	public Integer idperfil ;
	
	@Required
	@Column( name = "nombreperfil" )
	public String nombreperfil ;
	
	public Integer estado ;
	
	public static Finder<Integer,Perfil> find = new Finder<>( Integer.class , Perfil.class ) ;
	
	public static List<Perfil> list(){
		return find.all() ;
	}

	public static Perfil getPerfilById( Integer idperfil ){
		return find.byId( idperfil ) ;
	}
	
	public static boolean hasPermission( Integer idperfil , String path ){
		String[] route = path.split( "/" ) ;
		Integer idmodulo = ConfigurationController.mapModulos.get( route[ 1 ] ) ;
		Integer idvista = ConfigurationController.mapVistas.get( route[ 2 ] ) ;
		Integer idaccion = ConfigurationController.mapAcciones.get( route[ 3 ] ) ;
		PrivilegioSeguridad privSeg = PrivilegioSeguridad.getPrivilegioByIds( idperfil , idmodulo , idvista, idaccion ) ;
		return privSeg != null && privSeg.estado == 1 ;
	}
	
	public static List<Modulo> getPerfilStructure(){
		return Modulo.list() ;
	}
	
	public static Object getPerfilData( Integer idperfil ){
		Perfil perfil = getPerfilById( idperfil ) ;
		HashMap<String,Object> data = new HashMap<>() ;
		data.put( "idperfil" ,  perfil.idperfil ) ;
		data.put( "nombreperfil" , perfil.nombreperfil ) ;
		data.put( "estado" ,  perfil.estado ) ;
		data.put( "permisos" , PrivilegioSeguridad.listPermisosByPerfil( idperfil ) ) ;
		return data ;
	}
	
	public static void create( Perfil perfil ){
		perfil.save() ;
	}
	
	public static void update( Perfil perfil ){
		perfil.update() ;
	}
	
	public static void delete( Integer idperfil ){
		Perfil perfil = getPerfilById( idperfil ) ;
		perfil.estado = 0 ;
		perfil.update() ;
	}
	
	public static boolean exists( String nombre , Integer idperfil ){
		Perfil perfil = find.where().eq( "nombreperfil" ,  nombre ).ne( "idperfil" ,  idperfil ).findUnique() ;
		return ( perfil != null ) ;
	}
}