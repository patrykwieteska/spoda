package pl.pw.spoda.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String value) {
        super("Email '"+value+"' already exists!");
    }
}
