package game.vt.silence.security.model;

import game.vt.silence.game_mech.model.VT_Character;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VT_User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VTUser {
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

    @OneToMany
    private List<VT_Character> vt_characterList = new ArrayList<>();

    public void addVT_Character(VT_Character character){
       vt_characterList.add(character);
    }

}
