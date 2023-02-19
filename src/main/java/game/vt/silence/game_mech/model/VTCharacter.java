package game.vt.silence.game_mech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.vt.silence.security.model.VTUser;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "VTCharacter")
public class VTCharacter {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private VTUser vtUser;

    private String charname = ""; //финальное
    private String self = ""; //меняемое
    private String phenotype = "";//финальное

    @OneToMany//(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<VTCharacterValue> values = new ArrayList<>();

    public void addVTCharacterValue(VTCharacterValue vtCharacterValue) {
        values.add(vtCharacterValue);
    }

}
