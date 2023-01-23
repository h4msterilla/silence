package game.vt.silence.game_mech.model;

import lombok.*;
import org.springframework.util.ReflectionUtils;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VT_Character {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Transient
    private static final String PHENOTYPE_CAPITALIST = "Столичник";
    @Transient
    private static final String PHENOTYPE_STEPPEDWELLER = "Степняк";
    @Transient
    private static final String PHENOTYPE_ADAPTDWELLER = "Степняк-адаптант";
    @Transient
    private static final String PHENOTYPE_HALF_BLOOD = "Полукровка";
    @Transient
    private static final String PHENOTYPE_ELDER = "Древний";
    @Transient
    private static final String PHENOTYPE_HIGHLANDER = "Горняк";
    @Transient
    private static final String PHENOTYPE_SLITHER = "Серпарь";
    @Transient
    private static final String PHENOTYPE_COLLECTOR = "Коллектор";
    @Transient
    private static final String PHENOTYPE_MENTALIST = "Ментал";
    @Transient
    private static final String PHENOTYPE_PIPER = "Трубач";
    @Transient
    private static final String PHENOTYPE_SOLID = "Солид";

    private String value_name = ""; //финальное
    private String value_self = ""; //меняемое
    private String value_phenotype = ""; //финальное
    private int value_experience_max = 0; //меняемое
    private int value_experience_actual = 0;
    private int value_ticks = 0; //меняемое

    private int vital_stats_health_max = 0;//здоровье //вычисляемое
    private int vital_stats_health_actual = 0;//меняемое
    private int vital_stats_health_add = 0;//меняемое
    private int vital_stats_health_crisis = 0;//меняемое

    private int block_endurance = 0;//блок силы //вычисляемое
    private int skill_physique = 0;
    private int skill_hand_to_hand_combat = 0;
    private int skill_heavy_weapons = 0;
    private int skill_adaptability = 0;
    private int skill_sense_of_town = 0;
    private int skill_mutation = 0;

    private int block_swiftness = 0;//блок ловкость
    private int skill_precision = 0;
    private int skill_mobility = 0;
    private int skill_stealth = 0;
    private int skill_fencing = 0;
    private int skill_accuracy = 0;
    private int skill_slithering = 0;

    private int vital_stats_sanity_max = 0;//рассудок //вычисляемое
    private int vital_stats_sanity_actual = 0; //меняемое
    private int vital_stats_sanity_add = 0; //меняемое
    private int vital_stats_sanity_crisis = 0; //меняемое

    private int block_mind = 0;//блок разум
    private int skill_search = 0;
    private int skill_planning = 0;
    private int skill_mechanics = 0;
    private int skill_medicine = 0;
    private int skill_chemistry = 0;
    private int skill_autoimmersion = 0;

    private int block_ego = 0;//блок эго
    private int skill_manipulation = 0;
    private int skill_psychoanalysis = 0;
    private int skill_creativity = 0;
    private int skill_self_control = 0;
    private int skill_hypnosis = 0;
    private int skill_willpower = 0;

    private int connections_academies = 0;
    private int connections_authorities = 0;
    private int connections_aristocracy = 0;
    private int connections_business = 0;
    private int connections_art = 0;
    private int connections_army = 0;
    private int connections_criminals = 0;
    private int connections_occult = 0;
    private int connections_infrastructure = 0;
    private int connections_steppe = 0;

    private int knowledge_academies = 0;
    private int knowledge_authorities = 0;
    private int knowledge_aristocracy = 0;
    private int knowledge_business = 0;
    private int knowledge_art = 0;
    private int knowledge_army = 0;
    private int knowledge_criminals = 0;
    private int knowledge_occult = 0;
    private int knowledge_infrastructure = 0;
    private int knowledge_steppe = 0;

    private int cap_drive = 0;
    private int cap_stress = 0;
    private int cap_plan = 0;
    private int cap_Illness = 0;
    private int cap_swiftness_condition = 0;
    private int cap_inspiration = 0;
    private int cap_intoxication = 0;
    private int cap_regeneration = 0;
    private int cap_passion = 0;
    private int cap_light = 0;
    private int cap_greyness = 0;
    private int cap_darkness = 0;


    private void rematchCharacterBySkills() {

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

    }

    private int sum_block_endurance() {
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
    }

    public void changeValueByName(String valueName, String up_down) throws WrongCharacterValueNameException {
        if (valueName.contains("vital_stats_")) {
            changeVitalStatsByName(valueName, up_down);
            return;
        }

        if (valueName.contains("skill_")) {
            changeSkillByName(valueName, up_down);
            return;
        }
        if (valueName.contains("connections_") | valueName.contains("knowledge_")) {
            changeConnectionOrKnowledgeByName(valueName,up_down);
            return;
        }
        if (valueName.contains("cap_")) {
            changeConditionsAndProcessesByName(valueName,up_down);
            return;
        }
        if (valueName.contains("value_")) {
            changeValueByName(valueName,up_down);
            return;
        }

        throw new WrongCharacterValueNameException("can not parse");
    }

    private void changeValue_ByName(String valueName, String up_down) throws WrongCharacterValueNameException {
        if(valueName.equalsIgnoreCase("value_name")){
            value_name = up_down;
            return;
        }
        if(valueName.equalsIgnoreCase("value_self")){
            value_self = up_down;
            return;
        }
        if(valueName.equalsIgnoreCase("value_phenotype")){
            value_phenotype = up_down;
            return;
        }
        if(valueName.equalsIgnoreCase("value_experience")){
            if(up_down.equalsIgnoreCase("up")) value_experience_max++;
            if(up_down.equalsIgnoreCase("down")) value_experience_max--;
            return;
        }
        if(valueName.equalsIgnoreCase("value_ticks")){
            if(up_down.equalsIgnoreCase("up")) value_ticks++;
            if(up_down.equalsIgnoreCase("down")) value_ticks--;
            return;
        }

        throw new WrongCharacterValueNameException("can not parse value_");
    }

    private void changeSkillByName(String skillName, String up_down) throws WrongCharacterValueNameException {
        int value = getSkillByName(skillName);
        if (up_down.equalsIgnoreCase("up")) value++;
        if (up_down.equalsIgnoreCase("down")) value--;
        setSkillByName(skillName, value);
    }

    private void setSkillByName(String skillName, int value) throws WrongCharacterValueNameException {
        if (!skillName.contains("skill_")) throw new WrongCharacterValueNameException(skillName);
        setValueByName(skillName, value);
        rematchCharacterBySkills();
    }

    private int getSkillByName(String skillName) throws WrongCharacterValueNameException {
        if (!skillName.contains("skill_")) throw new WrongCharacterValueNameException(skillName);
        return getValueByName(skillName);
    }

    private void changeVitalStatsByName(String vitalStatsName, String up_down) throws WrongCharacterValueNameException {
        int value = getVitalStatsByName(vitalStatsName);
        if (up_down.equalsIgnoreCase("up")) value++;
        if (up_down.equalsIgnoreCase("down")) value--;
        setVitalStatsByName(vitalStatsName, value);
    }

    private void setVitalStatsByName(String vitalStatsName, int value) throws WrongCharacterValueNameException {
        if (!vitalStatsName.contains("vital_stats_")) throw new WrongCharacterValueNameException(vitalStatsName);

        if (vitalStatsName.contains("_max"))
            throw new WrongCharacterValueNameException(vitalStatsName + " cannot be changed because it depends on other values.");

        setValueByName(vitalStatsName, value);
    }

    private int getVitalStatsByName(String vitalStatsName) throws WrongCharacterValueNameException {
        if (!vitalStatsName.contains("vital_stats_")) throw new WrongCharacterValueNameException(vitalStatsName);
        return getValueByName(vitalStatsName);
    }

    private void changeConnectionOrKnowledgeByName(String connectionOrKnowledgeName, String up_down) throws WrongCharacterValueNameException {
        int value = getConnectionOrKnowledgeByName(connectionOrKnowledgeName);
        if (up_down.equalsIgnoreCase("up")) value++;
        if (up_down.equalsIgnoreCase("down")) value--;
        setConnectionOrKnowledgeByName(connectionOrKnowledgeName, value);
    }

    private void setConnectionOrKnowledgeByName(String connectionOrKnowledgeName, int value) throws WrongCharacterValueNameException {
        if (!connectionOrKnowledgeName.contains("connections_") | !connectionOrKnowledgeName.contains("knowledge_"))
            throw new WrongCharacterValueNameException(connectionOrKnowledgeName);

        setValueByName(connectionOrKnowledgeName, value);
    }

    private int getConnectionOrKnowledgeByName(String connectionOrKnowledgeName) throws WrongCharacterValueNameException {
        if (!connectionOrKnowledgeName.contains("connections_") | !connectionOrKnowledgeName.contains("knowledge_"))
            throw new WrongCharacterValueNameException(connectionOrKnowledgeName);

        return getValueByName(connectionOrKnowledgeName);
    }

    private void changeConditionsAndProcessesByName(String conditionsAndProcessesName, String up_down) throws WrongCharacterValueNameException {
        int value = getConditionsAndProcessesByName(conditionsAndProcessesName);
        if (up_down.equalsIgnoreCase("up")) value++;
        if (up_down.equalsIgnoreCase("down")) value--;
        setConditionsAndProcessesByName(conditionsAndProcessesName, value);
    }

    private void setConditionsAndProcessesByName(String conditionsAndProcessesName, int value) throws WrongCharacterValueNameException {
        if (!conditionsAndProcessesName.contains("cap_"))
            throw new WrongCharacterValueNameException(value_name);

        setValueByName(conditionsAndProcessesName, value);
    }

    private int getConditionsAndProcessesByName(String conditionsAndProcessesName) throws WrongCharacterValueNameException {
        if (!conditionsAndProcessesName.contains("cap_"))
            throw new WrongCharacterValueNameException(value_name);

        return getValueByName(conditionsAndProcessesName);
    }

    private void setValueByName(String valueName, int value) throws WrongCharacterValueNameException {
        if (valueName == null) throw new WrongCharacterValueNameException(null);

        Field field = ReflectionUtils.findField(VT_Character.class, valueName);
        if (field == null) throw new WrongCharacterValueNameException(valueName);
        field.setAccessible(true);
        ReflectionUtils.setField(field, this, value);
    }

    private int getValueByName(String valueName) throws WrongCharacterValueNameException {
        if (valueName == null) throw new WrongCharacterValueNameException(null);

        Field field = ReflectionUtils.findField(VT_Character.class, valueName);
        if (field == null) throw new WrongCharacterValueNameException(valueName);
        field.setAccessible(true);
        return (int) ReflectionUtils.getField(field, this);
    }

}
