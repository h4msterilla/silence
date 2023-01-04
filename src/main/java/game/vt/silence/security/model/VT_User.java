package game.vt.silence.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "VT_User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VT_User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, name = "VT_username")
    private String username;

    @Column(name = "encoded_password")
    private String encodedPassword;

    @Transient
    private String password;

}
