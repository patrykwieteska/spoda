package pl.pw.spoda.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.pw.spoda.database.entities.UserData;
import pl.pw.spoda.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
        httpSecurity.authorizeHttpRequests()
                .requestMatchers( "/api/v1/auth/token", "/api/v1/auth/sign-up").permitAll()
                .requestMatchers( HttpMethod.GET,"/api/v1/participants/**" ).permitAll()
                .anyRequest().authenticated();
        httpSecurity.addFilterBefore( jwtAuthFilter,UsernamePasswordAuthenticationFilter.class );
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            Optional<UserData> storedUser = userRepository.findByEmail( email );
            if(storedUser.isEmpty())
                throw new UsernameNotFoundException( "UserData "+email+" not found" );
            return getUserDetails( storedUser.get());
        };
    }

    private UserDetails getUserDetails(UserData userData) {
        return new org.springframework.security.core.userdetails.User(
                userData.getEmail(),
                userData.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority( userData.getRole().name()))
        );
    }

}
