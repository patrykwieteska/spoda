package pl.pw.spoda.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pw.spoda.dto.ParticipantResponse;
import pl.pw.spoda.dto.CreateParticipantRequest;
import pl.pw.spoda.service.participant.ParticipantService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ParticipantResponse getParticipantsById(@PathVariable(name="id") Integer id) {
        return participantService.getParticipantById(id);
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipantResponse> getParticipants() {
        return participantService.getParticipants();
    }

    @PostMapping(value="/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addParticipant(@RequestBody CreateParticipantRequest request) {
        participantService.addParticipant(request);
    }
}
