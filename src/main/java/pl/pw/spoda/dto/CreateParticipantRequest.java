package pl.pw.spoda.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateParticipantRequest {

    private String name;
    private String displayName;
}
