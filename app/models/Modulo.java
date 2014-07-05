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
public class Modulo extends Model {
	@Id
	public Integer idmodulo ;
	
	public String nombremodulo ;
	
	public String nombreruta ;
	
	@ManyToMany( fetch = FetchType.LAZY , cascade = CascadeType.ALL )
	@JoinTable( name = "modulo_vista" ,
			joinColumns = { @JoinColumn( name = "idmodulo" , referencedColumnName = "idmodulo" ) } ,
			inverseJoinColumns = { @JoinColumn( name = "idvista" , referencedColumnName = "idvista" ) } )
	public Set<Vista> vistas = new HashSet<Vista>( 0 ) ;
	
	public static Finder<Integer,Modulo> find = new Finder<>( Integer.class , Modulo.class ) ;
	
	public static Modulo getModuloById( Integer idmodulo ){
		return find.byId( idmodulo ) ;
	}
	
	public static List<Modulo> list(){
		return find.all() ;
	}
}
