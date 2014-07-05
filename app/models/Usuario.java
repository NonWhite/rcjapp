package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@SuppressWarnings("serial")
@Entity
public class Usuario extends Model {
	
	public final static int ESTADO_CONEXION_NO_CONECTADO = 0 ;
	public final static int ESTADO_CONEXION_CONECTADO = 1 ;
	
	@Id
	public Integer idusuario ;
	
	@Required
	public String nombreusuario ;
	
	@Required
	public String password ;
	
	@Required
	public String nombre ;
	
	@Required
	public String apellidopaterno ;
	
	public String apellidomaterno ;
	
	public String correo ;
	
	@Required
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "idperfil" )
	public Perfil perfil ;
	
	@Required
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "idtipodocumento" )
	public TipoDocumento tipodocumento ;
	
	@Required
	public String nrodocumento ;
	
	@Required
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "idareatrabajo" )
	public AreaTrabajo areatrabajo ;
	
	public Integer estado ;
	
	public Integer estadoconexion ;
	
	public Integer esinterno ;
	
	public static Finder<Integer,Usuario> find = new Finder<Integer,Usuario>( Integer.class , Usuario.class ) ;

	public static List<Usuario> list() {
		return find.where().ge( "estado" ,  0 ).findList() ;
	}

	public static Usuario getUsuarioById( Integer idusuario ){
		return find.byId( idusuario ) ;
	}

	public static void create( Usuario usuario ){
		usuario.save() ;
	}

	public static void delete( Integer id ){
		Usuario user = find.byId( id ) ;
		user.estado = 0 ;
		user.update() ;
	}
	
	public static Usuario login( String valorcampo1, String valorcampo2 ){
		List<ParametroSistema> lstCampos = ParametroSistema.listDatosAccesoActivos() ;
		Usuario user = find.fetch( "perfil" ).where().eq( lstCampos.get( 0 ).valortexto ,  valorcampo1 ).eq( lstCampos.get( 1 ).valortexto ,  valorcampo2 ).eq( "estado" , 1 ).findUnique() ;
		return user ;
	}
	
	@Override
	public String toString(){
		String user = "" ;
		user += "ID: " + this.idusuario + "\n" ;
		user += "nombreusuario: " + this.nombreusuario + "\n" ;
		user += "pass: " + this.password + "\n" ;
		user += "estado: " + this.estado + "\n" ;
		user += "estadoconexion: " + this.estadoconexion + "\n";
		user += "perfil: " + this.perfil.nombreperfil + "\n" ;
		return user ;
	}
	
	public String getFullName() {
		return nombre + " " + apellidopaterno + " " + apellidomaterno;
	}
	
	public static boolean exists( String username , String nrodoc , Integer idusuario ){
		username = "\"" + username + "\"" ;
		nrodoc = "\"" + nrodoc + "\"" ;
		List<Usuario> lstUsuario = find.where().raw( "idusuario != " + idusuario + " and ( nombreusuario = " + username + " or nrodocumento = " + nrodoc + " )" ).findList() ;
		return ( lstUsuario != null && lstUsuario.size() > 0 ) ;
	}
	
	public static List<Usuario> protectData( List<Usuario> lst ){
		for( Usuario usr : lst ) usr.protectInformation(); ;
		return lst ;
	}
	
	public Usuario protectInformation(){
		password = "" ;
		return this ;
	}
	
	public static List<Usuario> getConnectedUsers(){
		return Usuario.find.where().eq( "estadoconexion" ,  1 ).findList() ;
	}
}