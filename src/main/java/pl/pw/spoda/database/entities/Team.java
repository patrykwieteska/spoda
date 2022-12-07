package pl.pw.spoda.database.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "TEAM")
public class Team extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    private Integer id;
    @Column(name="NAME", nullable = false)
    private String name;
    @Column(name="RATING", nullable = false)
    private Integer rating;
    @Column(name = "LOGO_URL", nullable = false)
    private String logoUrl;
    @Column(name = "TYPE", nullable = true)
    private Integer type;
}
