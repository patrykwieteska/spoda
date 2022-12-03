package pl.pw.spoda.aspects.exceptions;

public class ParticipantNotFoundException extends RuntimeException{

    public ParticipantNotFoundException(String user) {
        super("There is no user: "+user);
    }
}
