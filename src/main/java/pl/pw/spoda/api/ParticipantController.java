package pl.pw.spoda.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pw.spoda.dto.ParticipantDto;
import pl.pw.spoda.service.participant.CreateParticipantRequest;
import pl.pw.spoda.service.participant.ParticipantService;

import java.util.List;

@RestController
@RequestMapping("/spoda/api/v1/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ParticipantDto getParticipantsById(@PathVariable(name="id") Integer id) {
        return participantService.getParticipantById(id);
    }

    @GetMapping(value = "/getParticipants")
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipantDto> getParticipants() {
        return participantService.getParticipants();
    }

    @PostMapping(value="/addParticipant")
    @ResponseStatus(HttpStatus.CREATED)
    public void addParticipant(@RequestBody CreateParticipantRequest request) {
        participantService.addParticipant(request);
    }
}
