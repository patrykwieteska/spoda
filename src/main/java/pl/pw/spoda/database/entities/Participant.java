package pl.pw.spoda.database.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "PARTICIPANT")
public class Participant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "ALIAS")
    private String alias;
}
