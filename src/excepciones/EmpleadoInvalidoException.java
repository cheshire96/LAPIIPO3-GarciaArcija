package excepciones;

public class EmpleadoInvalidoException extends RuntimeException{
	public EmpleadoInvalidoException(String message) {
		super(message);
	}
	
	public EmpleadoInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}
}
