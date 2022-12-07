package pl.pw.spoda.exceptions;

public class ParticipantNotFoundException extends RuntimeException{

    public ParticipantNotFoundException(Integer id) {
        super("Cannot find user with id = " + id);
    }
}
