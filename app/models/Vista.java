package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

@SuppressWarnings( "serial" )
@Entity
public class Vista extends Model {
	@Id
	public Integer idvista ;
	
	public String nombrevista ;
	
	public String nombreruta ;
	
	@ManyToMany( fetch = FetchType.LAZY , cascade = CascadeType.ALL )
	@JoinTable( name = "vista_accion" ,
			joinColumns = { @JoinColumn( name = "idvista" , referencedColumnName = "idvista" ) } ,
			inverseJoinColumns = { @JoinColumn( name = "idaccion" , referencedColumnName = "idaccion" ) } )
	public Set<Accion> acciones = new HashSet<Accion>( 0 ) ;
	
	public static Finder<Integer,Vista> find = new Finder<>( Integer.class , Vista.class ) ;

	public static Vista getVistaById( Integer idvista ){
		return find.byId( idvista ) ;
	}
	
	public static List<Vista> list(){
		return find.all() ;
	}
}