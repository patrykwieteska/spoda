package pl.pw.spoda.aspects.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pw.spoda.commons.ErrorMessage;

@ControllerAdvice
public class ParticipantNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage participantNotFoundHandler(ParticipantNotFoundException e) {
        return new ErrorMessage(e, HttpStatus.NOT_FOUND);
    }
}
