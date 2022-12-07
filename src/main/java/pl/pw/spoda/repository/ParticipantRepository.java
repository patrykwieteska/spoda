package pl.pw.spoda.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.pw.spoda.database.entities.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends CrudRepository<Participant, Integer> {
    Optional<Participant> findByDisplayName(String alias);
    @Query(value = "SELECT p FROM Participant p")
    List<Participant> findAllParticipants();
}
