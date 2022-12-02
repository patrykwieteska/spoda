package pl.pw.spoda.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pw.spoda.database.entities.Participant;
import pl.pw.spoda.dto.ParticipantDto;
import pl.pw.spoda.repository.ParticipantRepository;

import java.util.Optional;

@RestController
@RequestMapping("/spoda/api/v1/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantRepository participantRepository;


    @GetMapping(value = "/{username}")
    public ParticipantDto getParticipant(@PathVariable(name="username") String username) {
        Optional<Participant> storedParticipant =  participantRepository.findByUserName(username);

        if(storedParticipant.isEmpty())
            throw new RuntimeException("There is no participant with name username");

        Participant participant = storedParticipant.get();

        return ParticipantDto.builder()
                .id(participant.getId())
                .alias(participant.getAlias())
                .name(participant.getUserName())
                .creationDate(participant.getCreationDate())
                .lastModificationDate(participant.getLastModificationDate())
                .createdBy(participant.getCreatedBy())
                .build();
    }

}
