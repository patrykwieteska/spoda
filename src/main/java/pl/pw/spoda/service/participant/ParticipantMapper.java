package pl.pw.spoda.service.participant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pw.spoda.database.entities.Participant;
import pl.pw.spoda.dto.ParticipantDto;
import pl.pw.spoda.service.DateService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantMapper {

    private final DateService dateService;


    public ParticipantDto mapToResponse(Participant participant) {
        return ParticipantDto.builder()
                .id(participant.getId())
                .displayName(participant.getDisplayName())
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
        participant.setLastModificationDate(dateService.getCurrentDate());
        return participant;
    }

    public List<ParticipantDto> mapToParticipantList(List<Participant> participants) {
        return participants.stream()
                .map( this::mapToResponse )
                .toList();
    }
}
