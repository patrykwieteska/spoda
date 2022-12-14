package pl.pw.spoda.exceptions;

public class PasswordAreNotTheSameException extends RuntimeException {

    public PasswordAreNotTheSameException() {
        super("Passwords are not the same!");
    }
}
