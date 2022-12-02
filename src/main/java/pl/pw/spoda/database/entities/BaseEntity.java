package pl.pw.spoda.database.entities;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;
    @Column(name = "LAST_MODIFICATION_DATE")
    private LocalDateTime lastModificationDate;
    @Column(name = "CREATED_BY")
    private String createdBy;

}
