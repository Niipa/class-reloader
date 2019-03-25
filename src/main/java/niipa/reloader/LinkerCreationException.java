package niipa.reloader;

public class LinkerCreationException extends RuntimeException {

  public LinkerCreationException() {
  }

  public LinkerCreationException(String message) {
    super(message);
  }

  public LinkerCreationException(String message, Throwable cause) {
    super(message, cause);
  }

  public LinkerCreationException(Throwable cause) {
    super(cause);
  }

  public LinkerCreationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
