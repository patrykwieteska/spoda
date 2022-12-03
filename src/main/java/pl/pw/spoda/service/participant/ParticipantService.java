package pl.pw.spoda.service.participant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pw.spoda.database.entities.Participant;
import pl.pw.spoda.dto.ParticipantDto;
import pl.pw.spoda.repository.ParticipantRepository;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public ParticipantDto getParticipantByName(String username) {
        Optional<Participant> storedParticipant = participantRepository.findByName(username);
        if (storedParticipant.isEmpty())
            throw new RuntimeException("There is no participant with name " + username);

        Participant participant = storedParticipant.get();
        return participantMapper.mapToResponse(participant);
    }

    public void addParticipant(CreateParticipantRequest request) {
        Participant participant = participantMapper.mapToEntity(request);

        participantRepository.save(participant);
    }
}
