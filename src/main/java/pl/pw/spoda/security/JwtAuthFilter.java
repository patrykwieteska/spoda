package pl.pw.spoda.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.pw.spoda.database.entities.UserData;
import pl.pw.spoda.repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader( AUTHORIZATION );
        final String userEmail;
        final String jwtToken;

        if(authHeader == null || !authHeader.startsWith( "Bearer " )) {
            filterChain.doFilter( request, response );
            return;
        }

        jwtToken = authHeader.substring( 7 );
        userEmail = jwtUtils.extractUsername( jwtToken );
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            Optional<UserData> user = userRepository.findByEmail( userEmail );
            UserData storedUserData;
            if(user.isEmpty())
                throw new UsernameNotFoundException( "UserData not found" );

            storedUserData =user.get();

            UserDetails userDetails = mapToUserDetails(storedUserData);


            if(Boolean.TRUE.equals(jwtUtils.isTokenValid(jwtToken,userDetails))) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( userDetails,
                        null, userDetails.getAuthorities());
                authToken.setDetails( new WebAuthenticationDetailsSource().buildDetails( request ) );
                SecurityContextHolder.getContext().setAuthentication( authToken );
            }

            filterChain.doFilter( request,response );

        }
    }

    private UserDetails mapToUserDetails(UserData storedUserData) {
        return new User( storedUserData.getEmail(),
                storedUserData.getPassword(), List.of( new SimpleGrantedAuthority(storedUserData.getRole().name()) ) );
    }
}
