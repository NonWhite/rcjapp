package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@SuppressWarnings( "serial" )
@Entity
public class TipoDocumento extends Model {
	@Id
	public Integer idtipodocumento ;
	
	public String nombretipodocumento ;
	
	public static Finder<Integer,TipoDocumento> find = new Finder<>( Integer.class , TipoDocumento.class ) ;
	
	public static TipoDocumento getTipoDocumentoById( Integer idtipo ){
		return find.byId( idtipo ) ;
	}
	
	public static List<TipoDocumento> list(){
		return find.all() ;
	}
}