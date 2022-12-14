package pl.pw.spoda.validations;

import org.springframework.stereotype.Service;
import pl.pw.spoda.database.entities.UserData;
import pl.pw.spoda.dto.RegisterRequest;
import pl.pw.spoda.exceptions.EmailAlreadyExistsException;
import pl.pw.spoda.exceptions.PasswordAreNotTheSameException;

import java.util.Optional;

@Service
public class RegisterValidator {
    public void validateRequest(RegisterRequest request,Optional<UserData> storedUser) {
        if(storedUser.isPresent())
            throw new EmailAlreadyExistsException( request.getEmail());

        if(!request.getPassword().equals( request.getPasswordRepeated() ))
            throw new PasswordAreNotTheSameException();
    }
}
