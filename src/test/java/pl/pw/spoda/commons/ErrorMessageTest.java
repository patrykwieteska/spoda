package pl.pw.spoda.commons;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorMessageTest {
    @Test
    public void errorMessageTest() {
        Exception exception = new Exception("message");
        exception.setStackTrace( new StackTraceElement[]{} );
        ErrorMessage errorMessage = new ErrorMessage( exception ) ;
        assertThat(errorMessage.getMessage()).isEqualTo( "message" );
    }
}