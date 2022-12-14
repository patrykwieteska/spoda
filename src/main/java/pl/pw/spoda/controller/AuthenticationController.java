package pl.pw.spoda.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pw.spoda.dto.AuthRequest;
import pl.pw.spoda.dto.AuthResponse;
import pl.pw.spoda.dto.RegisterRequest;
import pl.pw.spoda.service.auth.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;


    @PostMapping("/token")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return authService.getToken( request );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(
            @RequestBody RegisterRequest request
    ) {
        return authService.signUpNewUser( request );
    }
}
