package za.co.example.core.directory;

public class DirectoryTransformationException extends Exception {
    public DirectoryTransformationException() {
        super();
    }

    public DirectoryTransformationException(String message) {
        super(message);
    }

    public DirectoryTransformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DirectoryTransformationException(Throwable cause) {
        super(cause);
    }

    protected DirectoryTransformationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
