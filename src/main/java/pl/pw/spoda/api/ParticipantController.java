package pl.pw.spoda.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pw.spoda.dto.ParticipantDto;
import pl.pw.spoda.service.participant.CreateParticipantRequest;
import pl.pw.spoda.service.participant.ParticipantService;

@RestController
@RequestMapping("/spoda/api/v1/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping(value = "/{username}")
    public ParticipantDto getParticipant(@PathVariable(name="username") String username) {
        return participantService.getParticipantByName(username);
    }

    @PostMapping(value="/addParticipant")
    public void addParticipant(@RequestBody CreateParticipantRequest request) {
        participantService.addParticipant(request);
    }
}
