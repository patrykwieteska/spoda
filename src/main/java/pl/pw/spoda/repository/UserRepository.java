package pl.pw.spoda.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pw.spoda.database.entities.UserData;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserData, Integer> {

    Optional<UserData> findByEmail(String email);
}
