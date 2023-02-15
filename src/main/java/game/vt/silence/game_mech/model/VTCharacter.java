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



    /*private void rematchCharacterBySkills() {

        value_experience_actual = 0;

        int block_endurance = sum_block_endurance();
        value_experience_actual += block_endurance;
        block_endurance = (block_endurance - (block_endurance % 3)) / 3;
        setBlock_endurance(block_endurance);

        int block_swiftness = sum_block_swiftness();
        value_experience_actual += block_swiftness;
        block_swiftness = (block_swiftness - (block_swiftness % 3)) / 3;
        setBlock_swiftness(block_swiftness);

        int block_mind = sum_block_mind();
        value_experience_actual += block_mind;
        block_mind = (block_mind - (block_mind % 3)) / 3;
        setBlock_mind(block_mind);

        int block_ego = sum_block_ego();
        value_experience_actual += block_ego;
        block_ego = (block_ego - (block_ego % 3)) / 3;
        setBlock_ego(block_ego);

        setVital_stats_health_max(block_endurance + block_swiftness);
        setVital_stats_sanity_max(block_mind + block_ego);

    }*/

    /*private int sum_block_endurance() {
        int sum = 0;
        sum += skill_physique;
        sum += skill_hand_to_hand_combat;
        sum += skill_heavy_weapons;
        sum += skill_adaptability;
        sum += skill_sense_of_town;
        sum += skill_mutation;
        return sum;
    }

    private int sum_block_swiftness() {
        int sum = 0;
        sum += skill_precision;
        sum += skill_mobility;
        sum += skill_stealth;
        sum += skill_fencing;
        sum += skill_accuracy;
        sum += skill_slithering;
        return sum;
    }

    private int sum_block_mind() {
        int sum = 0;
        sum += skill_search;
        sum += skill_planning;
        sum += skill_mechanics;
        sum += skill_medicine;
        sum += skill_chemistry;
        sum += skill_autoimmersion;
        return sum;
    }

    private int sum_block_ego() {
        int sum = 0;
        sum += skill_manipulation;
        sum += skill_psychoanalysis;
        sum += skill_creativity;
        sum += skill_self_control;
        sum += skill_hypnosis;
        sum += skill_willpower;
        return sum;
    }*/

}
