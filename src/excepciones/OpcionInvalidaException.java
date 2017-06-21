package excepciones;

public class OpcionInvalidaException extends RuntimeException {
	public OpcionInvalidaException(String message) {
		super(message);
	}
	
	public OpcionInvalidaException(String message, Throwable cause) {
		super(message, cause);
	}

}
