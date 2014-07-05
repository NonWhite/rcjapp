package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@SuppressWarnings( "serial" )
@Entity
public class AreaTrabajo extends Model {
	@Id
	public Integer idareatrabajo ;
	
	@Required
	public String nombreareatrabajo ;
	
	public static Finder<Integer,AreaTrabajo> find = new Finder<>( Integer.class , AreaTrabajo.class ) ;
	
	public static void create( AreaTrabajo area ){
		area.save() ;
	}

	public static AreaTrabajo getAreaTrabajoById( Integer idareatrabajo ){
		return find.byId( idareatrabajo ) ;
	}
	
	public static List<AreaTrabajo> list(){
		return find.all() ;
	}
	
	public static List<AreaTrabajo> search( String nombre ){
		return find.where().like( "nombreareatrabajo" ,  nombre ).findList() ;
	}
}