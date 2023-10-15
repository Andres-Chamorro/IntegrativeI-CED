package customExceptions;

@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(String message) {
        super(message);
    }
}