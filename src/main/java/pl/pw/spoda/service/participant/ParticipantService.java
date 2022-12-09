package pl.pw.spoda.service.participant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pw.spoda.exceptions.ParticipantAlreadyExistsException;
import pl.pw.spoda.database.entities.Participant;
import pl.pw.spoda.dto.ParticipantDto;
import pl.pw.spoda.exceptions.ParticipantNotFoundException;
import pl.pw.spoda.repository.ParticipantRepository;
import pl.pw.spoda.service.DateService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;
    private final DateService dateService;

    private Optional<Participant> getByDisplayName(String alias) {
        return participantRepository.findByDisplayName( alias );
    }

    public void addParticipant(CreateParticipantRequest request) {
        if (getByDisplayName( request.getDisplayName() ).isPresent())
            throw new ParticipantAlreadyExistsException( request.getDisplayName() );
        Participant participant = participantMapper.mapToEntity( request );
        participant.setCreationDate( dateService.getCurrentDate() );
        participantRepository.save( participant );
    }

    public List<ParticipantDto> getParticipants() {
        List<Participant> participants = participantRepository.findAllParticipants();
        return participantMapper.mapToParticipantList( participants );
    }

    public ParticipantDto getParticipantById(Integer id) {
        Participant participant = participantRepository.findById( id )
                .orElseThrow( () -> new ParticipantNotFoundException( id ) );
        return participantMapper.mapToResponse( participant );
    }
}
