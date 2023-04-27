package game.vt.silence.game_mech.model.values;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.vt.silence.game_mech.model.VTCharacter;
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

    //@JsonIgnore
    //private String type;
    //private String name;
    private VTValue valueName;
    private int value;

    @ManyToOne
    @JsonIgnore
    private VTCharacter vtCharacter;

    public boolean containsTag(VTValueTag vtValueTag){
        return valueName.containsTag(vtValueTag);
    }

    public boolean equalsVTValue(VTValue vtValue){
        return this.valueName == vtValue;
    }

    public void modifyValueBy(int arg){
        value = value + arg;
    }
}
