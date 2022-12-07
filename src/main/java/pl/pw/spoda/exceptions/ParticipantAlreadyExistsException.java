package pl.pw.spoda.exceptions;

public class ParticipantAlreadyExistsException extends RuntimeException{

    public ParticipantAlreadyExistsException(String value) {
        super("Participant with alias '"+value+"' already exists!");
    }
}
