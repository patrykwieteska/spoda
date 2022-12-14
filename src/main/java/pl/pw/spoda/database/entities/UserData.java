package pl.pw.spoda.database.entities;


import jakarta.persistence.*;
import lombok.*;
import pl.pw.spoda.database.enums.UserRole;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "USER_DATA")
public class UserData extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    private Integer id;
    @Column(name="EMAIL", nullable = false)
    private String email;
    @Column(name="PASSWORD", nullable = false)
    private String password;
    @Column(name="ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

}
