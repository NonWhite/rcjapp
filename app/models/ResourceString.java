package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@SuppressWarnings( "serial" )
@Entity
public class ResourceString extends Model {
	@Id
	public Integer idresource ;
	
	public String keystring ;
	
	public String esp ;
	
	public String eng ;
	
	public static Finder<Integer,ResourceString> find = new Finder<>( Integer.class , ResourceString.class ) ;
	
	public static List<ResourceString> list(){
		return find.all() ;
	}
}