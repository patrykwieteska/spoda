package pl.pw.spoda.service.participant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pw.spoda.aspects.exceptions.ParticipantNotFoundException;
import pl.pw.spoda.database.entities.Participant;
import pl.pw.spoda.dto.ParticipantDto;
import pl.pw.spoda.repository.ParticipantRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    public ParticipantDto getParticipantByName(String username) {
        Optional<Participant> storedParticipant = participantRepository.findByName(username);
        Participant participant = storedParticipant.orElseThrow(() -> new ParticipantNotFoundException( username));
        return participantMapper.mapToResponse(participant);
    }

    public void addParticipant(CreateParticipantRequest request) {
        Participant participant = participantMapper.mapToEntity(request);

        participantRepository.save(participant);
    }
}
