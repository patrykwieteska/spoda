package pl.pw.spoda.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pw.spoda.database.entities.UserData;
import pl.pw.spoda.database.enums.UserRole;
import pl.pw.spoda.repository.UserRepository;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class TestConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String TEST_USER = "admin";

    @ConditionalOnProperty(value="test.user.enabled")
    @EventListener(ApplicationReadyEvent.class)
    public void saveUser() {

        UserData userData = UserData.builder()
                .email( TEST_USER)
                .password( passwordEncoder.encode( TEST_USER ) )
                .role( UserRole.ROLE_USER )
                .build();

        userData.setCreatedBy( TEST_USER);
        userData.setCreationDate( LocalDateTime.now() );
        userData.setLastModificationDate( LocalDateTime.now() );

        userRepository.save( userData );
    }
}
