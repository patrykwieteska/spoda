package pl.pw.spoda.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pw.spoda.database.entities.UserData;
import pl.pw.spoda.database.enums.UserRole;
import pl.pw.spoda.dto.AuthResponse;
import pl.pw.spoda.dto.AuthRequest;
import pl.pw.spoda.dto.RegisterRequest;
import pl.pw.spoda.repository.UserRepository;
import pl.pw.spoda.security.JwtUtils;
import pl.pw.spoda.service.DateService;
import pl.pw.spoda.validations.RegisterValidator;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final RegisterValidator registerValidator;
    private PasswordEncoder passwordEncoder;
    private DateService dateService;

    public ResponseEntity<AuthResponse> getToken(AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword() )
            );

            User user = (User) authentication.getPrincipal();
            AuthResponse authResponse = AuthResponse.builder()
                    .accessToken( jwtUtils.generateToken( user ) )
                    .build();

            return ResponseEntity.ok( authResponse );

        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException( "User not found" );
        }
    }

    private final UserRepository userRepository;

    public ResponseEntity<String> signUpNewUser(RegisterRequest request) {
        Optional<UserData> storedUser = userRepository.findByEmail( request.getEmail() );
        registerValidator.validateRequest( request, storedUser );

        UserData newUserData = mapToUserData( request );

        userRepository.save( newUserData );
        return ResponseEntity.status( HttpStatus.CREATED.value() ).body( "UserData created" );

    }

    private UserData mapToUserData(RegisterRequest request) {
        UserData newUser = UserData.builder()
                .email( request.getEmail() )
                .password( passwordEncoder.encode( request.getPassword() ) )
                .role( UserRole.ROLE_USER )
                .build();

        newUser.setCreationDate( dateService.getCurrentDate() );
        newUser.setLastModificationDate( dateService.getCurrentDate() );
        return newUser;
    }
}
