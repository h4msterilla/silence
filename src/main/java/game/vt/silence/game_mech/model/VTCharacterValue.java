package game.vt.silence.game_mech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "VTCharacterValue")
public class VTCharacterValue {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    private String type;
    private String name;
    private VTValue valueName;
    private int value;

    @ManyToOne
    @JsonIgnore
    private VTCharacter vtCharacter;
}
