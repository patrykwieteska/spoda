package pl.pw.spoda.service.participant;

import org.springframework.stereotype.Service;
import pl.pw.spoda.database.entities.Participant;
import pl.pw.spoda.dto.ParticipantDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantMapper {

    public ParticipantDto mapToResponse(Participant participant) {
        return ParticipantDto.builder()
                .id(participant.getId())
                .alias(participant.getDisplayName())
                .name(participant.getName())
                .creationDate(participant.getCreationDate())
                .lastModificationDate(participant.getLastModificationDate())
                .createdBy(participant.getCreatedBy())
                .build();
    }

    public Participant mapToEntity(CreateParticipantRequest request) {
        Participant participant = Participant.builder()
                .name(request.getName())
                .displayName(request.getDisplayName())
                .build();
        participant.setCreationDate(LocalDateTime.now());
        participant.setLastModificationDate(LocalDateTime.now());
        return participant;
    }

    public List<ParticipantDto> mapToResponseList(List<Participant> participants) {
        return participants.stream()
                .map( this::mapToResponse )
                .collect( Collectors.toList());
    }
}
