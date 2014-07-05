package models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import play.db.ebean.Model;

@SuppressWarnings( "serial" )
@Entity
public class PrivilegioSeguridad extends Model {
	@EmbeddedId
	public PrivilegioSeguridadId idprivilegioseguridad ;

	public Integer estado ;
	
	public static Finder<PrivilegioSeguridadId,PrivilegioSeguridad> find = new Finder<>( PrivilegioSeguridadId.class , PrivilegioSeguridad.class ) ;
	
	public static void create( PrivilegioSeguridad privilegioSeguridad ){
		privilegioSeguridad.save() ;
	}
	
	public static void update( PrivilegioSeguridad privilegioSeguridad ){
		privilegioSeguridad.update() ;
	}
	
	public static PrivilegioSeguridad getPrivilegioByIds( Integer idperfil , Integer idmodulo , Integer idvista , Integer idaccion ){
		return find.where().eq( "idperfil" ,  idperfil ).eq( "idmodulo" ,  idmodulo ).eq( "idvista" , idvista ).eq( "idaccion" ,  idaccion ).findUnique() ;
	}
	
	public static Object listPermisosByPerfil( Integer idperfil ){
		return find.where().eq( "idperfil" ,  idperfil ).findList() ;
	}
}