package pl.pw.spoda.commons;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Slf4j
public class ErrorMessage {
    private String message;
    private LocalDateTime timestamp;
    private Integer status;

    public <T extends Exception> ErrorMessage(T exception, HttpStatus status) {
        this.setStatus( status.value() );
        this.setMessage( exception.getMessage() );
        this.setTimestamp( LocalDateTime.now() );
        log.error( this.getMessage(), exception );
    }
}


