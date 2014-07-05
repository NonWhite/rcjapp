package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PrivilegioSeguridadId implements Serializable {
	@Column( name = "idperfil" )
	public Integer idperfil ;
	
	@Column( name = "idmodulo" )
	public Integer idmodulo ;
	
	@Column( name = "idvista" )
	public Integer idvista ;
	
	@Column( name = "idaccion" )
	public Integer idaccion ;
	
	public PrivilegioSeguridadId( Integer idperfil , Integer idmodulo , Integer idvista , Integer idaccion ){
		this.idperfil = idperfil ;
		this.idmodulo = idmodulo ;
		this.idvista = idvista ;
		this.idaccion = idaccion ;
	}
	
	public boolean equals( Object obj ){
		return super.equals( obj ) ;
	}
	
	public int hashCode(){
		return super.hashCode() ;
	}
}