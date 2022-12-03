package pl.pw.spoda.service.participant;

import org.springframework.stereotype.Service;
import pl.pw.spoda.database.entities.Participant;
import pl.pw.spoda.dto.ParticipantDto;

import java.time.LocalDateTime;

@Service
public class ParticipantMapper {

    public ParticipantDto mapToResponse(Participant participant) {
        return ParticipantDto.builder()
                .id(participant.getId())
                .alias(participant.getAlias())
                .name(participant.getName())
                .creationDate(participant.getCreationDate())
                .lastModificationDate(participant.getLastModificationDate())
                .createdBy(participant.getCreatedBy())
                .build();
    }

    public Participant mapToEntity(CreateParticipantRequest request) {
        Participant participant = Participant.builder()
                .name(request.getName())
                .alias(request.getAlias())
                .build();
        participant.setCreationDate(LocalDateTime.now());
        participant.setLastModificationDate(LocalDateTime.now());
        return participant;
    }
}
