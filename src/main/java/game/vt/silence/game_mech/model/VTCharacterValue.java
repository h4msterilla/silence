package game.vt.silence.game_mech.model;

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

    private String type;
    private String name;
    private int value;

    @ManyToOne
    private VTCharacter vtCharacter;
}
