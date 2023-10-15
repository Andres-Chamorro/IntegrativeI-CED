package customExceptions;

@SuppressWarnings("serial")
public class EmptyStackException extends RuntimeException {
	public EmptyStackException(String message) {
		super(message);
	}
}
