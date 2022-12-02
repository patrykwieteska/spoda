package pl.pw.spoda.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pw.spoda.database.entities.Participant;

import java.util.Optional;

public interface ParticipantRepository extends CrudRepository<Participant,Integer> {
    Optional<Participant> findByUserName(String userName);
}
