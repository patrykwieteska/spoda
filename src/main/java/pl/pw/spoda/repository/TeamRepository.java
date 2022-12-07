package pl.pw.spoda.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pw.spoda.database.entities.Team;

import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Integer> {
    Optional<Team> findById(Integer id);
}
