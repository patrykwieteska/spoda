package pl.pw.spoda.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pw.spoda.commons.ErrorMessage;
import pl.pw.spoda.exceptions.ParticipantAlreadyExistsException;
import pl.pw.spoda.exceptions.ParticipantNotFoundException;

@ControllerAdvice
public class ParticipantAdvice {

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage participantNotFoundHandler(ParticipantNotFoundException e) {
        return new ErrorMessage( e );
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage participantAlreadyExists(ParticipantAlreadyExistsException e) {
        return new ErrorMessage( e );
    }
}
