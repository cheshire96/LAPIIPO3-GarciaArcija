package excepciones;

public class ClienteInvalidoException extends RuntimeException{
	public ClienteInvalidoException(String message) {
		super(message);
	}
	
	public ClienteInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

}
