package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@SuppressWarnings( "serial" )
@Entity
public class ParametroSistema extends Model {
	public static Integer TIPO_PARAMETRO_DATOS_ACCESO = 1 ;
	public static Integer TIPO_PARAMETRO_DATOS_PROPIETARIO = 2 ;
	public static Integer TIPO_PARAMETRO_DATOS_GENERALES = 3 ;
	public static Integer TIPO_PARAMETRO_DATOS_MANTENIMIENTO = 4 ;
	
	public static Integer ID_PARAMETRO_NUMERO_CONECTADOS = 16 ;
	public static Integer ID_PARAMETRO_BLOQUEO_LOGIN = 17 ;
	
	@Id
	public Integer idparametrosistema ;
	
	public String nombreparametro ;
	
	public Integer valornumerico ;
	
	public String valortexto ;
	
	public Integer tipoparametro ;
	
	public static Finder<Integer,ParametroSistema> find = new Finder<>( Integer.class , ParametroSistema.class ) ;
	
	public static void update( ParametroSistema parametro ){
		parametro.update() ;
	}

	public static ParametroSistema getParametroById( Integer idparametro ){
		return find.byId( idparametro ) ;
	}
	
	public static List<ParametroSistema> listByTipoParametro( Integer tipoparametro ){
		return find.where().eq( "tipoparametro" ,  tipoparametro ).findList() ;
	}
	
	public static List<ParametroSistema> listAllParametros(){
		return find.all() ;
	}

	public static List<ParametroSistema> listDatosAccesoActivos(){
		return find.where().eq( "tipoparametro" ,  1 ).eq( "valornumerico" , 1 ).findList() ;
	}
	
	public static void updateConectados(){
		ParametroSistema conectados = getParametroById( ID_PARAMETRO_NUMERO_CONECTADOS ) ;
		conectados.valornumerico = Usuario.getConnectedUsers().size() ;
		conectados.update() ;
	}
}