package configuration;

import controllers.routes;
import models.Perfil;
import models.Usuario;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class Secured extends Security.Authenticator{
    @Override
    public String getUsername( Context ctx ){
    	if( ctx.request().path().equalsIgnoreCase( "/configuracion/login/getcampos" ) ) return "" ;
    	if( ctx.request().path().equalsIgnoreCase( "/configuracion/general/getdatosmantenimiento" ) ) return "" ;
        return ctx.session().get( "username" ) ;
    }

    @Override
    public Result onUnauthorized( Context ctx ){
        return redirect( routes.HomeController.loginForm() ) ;
    }
   
    public static boolean hasPermission(){
    	String path = Context.current().request().path() ;
    	Integer idperfil = Integer.parseInt( Context.current().session().get( "idperfil" ) ) ;
    	return Perfil.hasPermission( idperfil , path ) ;
    }
    
    public static void initSession( Usuario user , Context ctx ){
    	ctx.session().put( "idusuario" ,  user.idusuario + "" ) ;
    	ctx.session().put( "nombrecompleto" ,  user.nombre + " " + user.apellidopaterno + ( user.apellidomaterno != null ? " " + user.apellidomaterno : "" ) ) ;
    	ctx.session().put( "idperfil" , user.perfil.idperfil + "" ) ;
		ctx.session().put( "username" , user.nombreusuario ) ;
		ctx.session().put( "lang" ,  "esp" ) ;
    }
}