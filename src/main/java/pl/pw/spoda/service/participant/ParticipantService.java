package pl.pw.spoda.service.participant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pw.spoda.exceptions.ParticipantAlreadyExistsException;
import pl.pw.spoda.database.entities.Participant;
import pl.pw.spoda.dto.ParticipantDto;
import pl.pw.spoda.exceptions.ParticipantNotFoundException;
import pl.pw.spoda.repository.ParticipantRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    private Optional<Participant> getByDisplayName(String alias) {
        return participantRepository.findByDisplayName( alias );
    }

    public void addParticipant(CreateParticipantRequest request) {
        if (getByDisplayName( request.getDisplayName() ).isPresent())
            throw new ParticipantAlreadyExistsException( request.getDisplayName() );

        Participant participant = participantMapper.mapToEntity( request );
        participantRepository.save( participant );
    }

    public List<ParticipantDto> getParticipants() {
        List<Participant> participants = participantRepository.findAllParticipants();
        return participantMapper.mapToResponseList( participants );
    }

    public ParticipantDto getParticipantById(Integer id) {
        Participant participant = participantRepository.findById( id )
                .orElseThrow( () -> new ParticipantNotFoundException( id ) );
        return participantMapper.mapToResponse( participant );
    }
}
