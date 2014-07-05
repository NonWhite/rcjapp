import java.lang.reflect.Method;

import controllers.ConfigurationController;
import play.Application;
import play.GlobalSettings;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.SimpleResult;
import play.mvc.Http.Request;
import play.mvc.Http.RequestHeader;
import views.html.* ;
import static play.mvc.Results.* ;

public class Global extends GlobalSettings {
	@SuppressWarnings( "rawtypes" )
	@Override
	public Action onRequest( Request request , Method method ){
		System.out.println( "Ejecutando desde " + request.remoteAddress() + " : " + request.path() + "( " + request.method() + " )" + " -> " + method.getName() ) ;
		return super.onRequest( request , method ) ;
	}
	
	@Override
	public void onStart( Application app ){
		super.onStart( app ) ;
		try{
			ConfigurationController.loadRoutesMap() ;
			ConfigurationController.loadResourcesString() ;
		}catch( Exception e ){
			e.printStackTrace() ;
		}
	}
	
	@Override
	public Promise<SimpleResult> onError( RequestHeader arg0 , Throwable arg1 ){
		return Promise.<SimpleResult>pure( internalServerError( errorPage.render() ) ) ; // Custom error page
//		return super.onError( arg0 , arg1 ) ; // Original
	}
	
	@Override
	public Promise<SimpleResult> onBadRequest( RequestHeader arg0 , String arg1 ){
		return Promise.<SimpleResult>pure( internalServerError( errorPage.render() ) ) ; // Custom error page
//		return super.onBadRequest( arg0 , arg1 ) ; // Original
	}
	
	
	@Override
	public Promise<SimpleResult> onHandlerNotFound( RequestHeader arg0 ){
		return Promise.<SimpleResult>pure( internalServerError( errorPage.render() ) ) ; // Custom error page
//		return super.onHandlerNotFound( arg0 ) ; // Original
	}
	
	@Override
	public void onStop( Application app ){
		super.onStop( app ) ;
	}
}