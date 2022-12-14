package pl.pw.spoda.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pw.spoda.commons.ErrorMessage;
import pl.pw.spoda.exceptions.EmailAlreadyExistsException;
import pl.pw.spoda.exceptions.PasswordAreNotTheSameException;

@ControllerAdvice
public class UserAdvice {

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage emailAlreadyExistsHandler(EmailAlreadyExistsException e) {
        return new ErrorMessage( e );
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage passwordAreNotTheSameHandler(PasswordAreNotTheSameException e) {
        return new ErrorMessage( e );
    }
}
