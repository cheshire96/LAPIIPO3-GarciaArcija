package excepciones;

public class ListadoException extends RuntimeException{
	public ListadoException(String message) {
		super(message);
	}
	
	public ListadoException(String message, Throwable cause) {
		super(message, cause);
	}
}
