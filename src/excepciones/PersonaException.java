package excepciones;

public class PersonaException extends RuntimeException{
	public PersonaException(String message) {
		super(message);
	}
	
	public PersonaException(String message, Throwable cause) {
		super(message, cause);
	}

}
