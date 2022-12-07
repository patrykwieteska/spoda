package pl.pw.spoda.commons;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Slf4j
public class ErrorMessage {
    private String message;
    private LocalDateTime timestamp;

    public <T extends Exception> ErrorMessage(T exception) {
        this.setMessage( exception.getMessage() );
        this.setTimestamp( LocalDateTime.now() );
        log.error( this.getMessage(), exception );
    }
}


