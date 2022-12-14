package pl.pw.spoda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/tournaments")
public class TournamentController {


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String getTournament(Principal principal) {
        return "Witaj, "+principal.getName();
    }
}
