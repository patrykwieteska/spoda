package pl.pw.spoda.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class DateService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public LocalDateTime getCurrentDate() {
        return LocalDateTime.parse( LocalDateTime.now(ZoneId.of("CET")).format( FORMATTER ) );
    }
}
