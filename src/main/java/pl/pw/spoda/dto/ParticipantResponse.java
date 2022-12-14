package pl.pw.spoda.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantResponse {

    private Integer id;
    private String name;
    private String displayName;
    private LocalDateTime creationDate;
    private LocalDateTime lastModificationDate;
    private String createdBy;


}
