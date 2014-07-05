package helpers;

public class GenericResponse {
	public String status ;
	
	public GenericResponse(){
		this.status = "OK" ;
	}
	
	public GenericResponse( String status ){
		this.status = status ;
	}
}
