package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@SuppressWarnings( "serial" )
@Entity
public class Accion extends Model {
	@Id
	public Integer idaccion ;
	
	public String nombreruta ;
	
	public String descripcion ;
	
	public static Finder<Integer,Accion> find = new Finder<>( Integer.class , Accion.class ) ;
	
	public static Accion getAccionById( Integer idaccion ){
		return find.byId( idaccion ) ;
	}
	
	public static List<Accion> list(){
		return find.all() ;
	}
}