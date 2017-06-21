package excepciones;

public class BaseDatosException extends RuntimeException{
	public BaseDatosException(String message) {
		super(message);
	}
	
	public BaseDatosException(String message, Throwable cause) {
		super(message, cause);
	}

}
