package customExceptions;

@SuppressWarnings("serial")
public class NoSuchElementException extends RuntimeException {
	public NoSuchElementException(String message) {
		super(message);
	}
}
