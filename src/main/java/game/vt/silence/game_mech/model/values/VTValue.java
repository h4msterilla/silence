package game.vt.silence.game_mech.model.values;

import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;

import java.util.Arrays;

public enum VTValue {
    VALUE_EXPERIENCE_MAX(VTValueTag.BASE, VTValueTag.EXPERIENCE),
    VALUE_EXPERIENCE_ACTUAL(VTValueTag.BASE, VTValueTag.EXPERIENCE),
    VALUE_TICKS(VTValueTag.BASE),
    TALENT_LIMIT(VTValueTag.BASE),

    VITAL_STATS_HEALTH_MAX(VTValueTag.VITAL_STATE, VTValueTag.HEALTH),
    VITAL_STATS_HEALTH_ACTUAL(VTValueTag.VITAL_STATE, VTValueTag.HEALTH),
    VITAL_STATS_HEALTH_ADD(VTValueTag.VITAL_STATE, VTValueTag.HEALTH),
    VITAL_STATS_HEALTH_CRISIS(VTValueTag.VITAL_STATE, VTValueTag.HEALTH),

    BLOCK_ENDURANCE(VTValueTag.BLOCK, VTValueTag.BLOCK_ENDURANCE),
    SKILL_ENDURANCE_PHYSIQUE(VTValueTag.SKILL, VTValueTag.BLOCK_ENDURANCE),
    SKILL_ENDURANCE_HAND_TO_HAND_COMBAT(VTValueTag.SKILL, VTValueTag.BLOCK_ENDURANCE),
    SKILL_ENDURANCE_HEAVY_WEAPONS(VTValueTag.SKILL, VTValueTag.BLOCK_ENDURANCE),
    SKILL_ENDURANCE_ADAPTABILITY(VTValueTag.SKILL, VTValueTag.BLOCK_ENDURANCE),
    SKILL_ENDURANCE_SENSE_OF_TOWN(VTValueTag.SKILL, VTValueTag.BLOCK_ENDURANCE),
    SKILL_ENDURANCE_MUTATION(VTValueTag.SKILL, VTValueTag.BLOCK_ENDURANCE, VTValueTag.SPECIAL_SKILL),

    BLOCK_SWIFTNESS(VTValueTag.BLOCK, VTValueTag.BLOCK_SWIFTNESS),
    SKILL_SWIFTNESS_PRECISION(VTValueTag.SKILL, VTValueTag.BLOCK_SWIFTNESS),
    SKILL_SWIFTNESS_MOBILITY(VTValueTag.SKILL, VTValueTag.BLOCK_SWIFTNESS),
    SKILL_SWIFTNESS_STEALTH(VTValueTag.SKILL, VTValueTag.BLOCK_SWIFTNESS),
    SKILL_SWIFTNESS_FENCING(VTValueTag.SKILL, VTValueTag.BLOCK_SWIFTNESS),
    SKILL_SWIFTNESS_ACCURACY(VTValueTag.SKILL, VTValueTag.BLOCK_SWIFTNESS),
    SKILL_SWIFTNESS_SLITHERING(VTValueTag.SKILL, VTValueTag.BLOCK_SWIFTNESS, VTValueTag.SPECIAL_SKILL),

    VITAL_STATS_SANITY_MAX(VTValueTag.VITAL_STATE, VTValueTag.SANITY),
    VITAL_STATS_SANITY_ACTUAL(VTValueTag.VITAL_STATE, VTValueTag.SANITY),
    VITAL_STATS_SANITY_ADD(VTValueTag.VITAL_STATE, VTValueTag.SANITY),
    VITAL_STATS_SANITY_CRISIS(VTValueTag.VITAL_STATE, VTValueTag.SANITY),

    BLOCK_MIND(VTValueTag.BLOCK, VTValueTag.BLOCK_MIND),
    SKILL_MIND_SEARCH(VTValueTag.SKILL, VTValueTag.BLOCK_MIND),
    SKILL_MIND_PLANNING(VTValueTag.SKILL, VTValueTag.BLOCK_MIND),
    SKILL_MIND_MECHANICS(VTValueTag.SKILL, VTValueTag.BLOCK_MIND),
    SKILL_MIND_MEDICINE(VTValueTag.SKILL, VTValueTag.BLOCK_MIND),
    SKILL_MIND_CHEMISTRY(VTValueTag.SKILL, VTValueTag.BLOCK_MIND),
    SKILL_MIND_AUTOIMMERSION(VTValueTag.SKILL, VTValueTag.BLOCK_MIND, VTValueTag.SPECIAL_SKILL),

    BLOCK_EGO(VTValueTag.BLOCK, VTValueTag.BLOCK_EGO),
    SKILL_EGO_MANIPULATION(VTValueTag.SKILL, VTValueTag.BLOCK_EGO),
    SKILL_EGO_PSYCHOANALYSIS(VTValueTag.SKILL, VTValueTag.BLOCK_EGO),
    SKILL_EGO_CREATIVITY(VTValueTag.SKILL, VTValueTag.BLOCK_EGO),
    SKILL_EGO_SELF_CONTROL(VTValueTag.SKILL, VTValueTag.BLOCK_EGO),
    SKILL_EGO_HYPNOSIS(VTValueTag.SKILL, VTValueTag.BLOCK_EGO),
    SKILL_EGO_WILLPOWER(VTValueTag.SKILL, VTValueTag.BLOCK_EGO, VTValueTag.SPECIAL_SKILL),

    CONNECTIONS_ACADEMIES(VTValueTag.CONNECTIONS),
    CONNECTIONS_AUTHORITIES(VTValueTag.CONNECTIONS),
    CONNECTIONS_ARISTOCRACY(VTValueTag.CONNECTIONS),
    CONNECTIONS_BUSINESS(VTValueTag.CONNECTIONS),
    CONNECTIONS_ART(VTValueTag.CONNECTIONS),
    CONNECTIONS_ARMY(VTValueTag.CONNECTIONS),
    CONNECTIONS_CRIMINALS(VTValueTag.CONNECTIONS),
    CONNECTIONS_OCCULT(VTValueTag.CONNECTIONS),
    CONNECTIONS_INFRASTRUCTURE(VTValueTag.CONNECTIONS),
    CONNECTIONS_STEPPE(VTValueTag.CONNECTIONS),

    KNOWLEDGE_ACADEMIES(VTValueTag.KNOWLEDGE),
    KNOWLEDGE_AUTHORITIES(VTValueTag.KNOWLEDGE),
    KNOWLEDGE_ARISTOCRACY(VTValueTag.KNOWLEDGE),
    KNOWLEDGE_BUSINESS(VTValueTag.KNOWLEDGE),
    KNOWLEDGE_ART(VTValueTag.KNOWLEDGE),
    KNOWLEDGE_ARMY(VTValueTag.KNOWLEDGE),
    KNOWLEDGE_CRIMINALS(VTValueTag.KNOWLEDGE),
    KNOWLEDGE_OCCULT(VTValueTag.KNOWLEDGE),
    KNOWLEDGE_INFRASTRUCTURE(VTValueTag.KNOWLEDGE),
    KNOWLEDGE_STEPPE(VTValueTag.KNOWLEDGE),

    CAP_DRIVE(VTValueTag.CONDITIONS_AND_PROCESSES),
    CAP_STRESS(VTValueTag.CONDITIONS_AND_PROCESSES),
    CAP_PLAN(VTValueTag.CONDITIONS_AND_PROCESSES),
    CAP_ILLNESS(VTValueTag.CONDITIONS_AND_PROCESSES),
    CAP_SWIFTNESS_CONDITION(VTValueTag.CONDITIONS_AND_PROCESSES),
    CAP_INSPIRATION(VTValueTag.CONDITIONS_AND_PROCESSES),
    CAP_INTOXICATION(VTValueTag.CONDITIONS_AND_PROCESSES),
    CAP_REGENERATION(VTValueTag.CONDITIONS_AND_PROCESSES),
    CAP_PASSION(VTValueTag.CONDITIONS_AND_PROCESSES),
    CAP_LIGHT(VTValueTag.CONDITIONS_AND_PROCESSES),
    CAP_GREYNESS(VTValueTag.CONDITIONS_AND_PROCESSES),
    CAP_DARKNESS(VTValueTag.CONDITIONS_AND_PROCESSES);

    private VTValueTag[] tags;

    VTValue(VTValueTag... tags) {
        this.tags = tags;

    }

    /*public List<VTValueTag> getTags() {
        return tags;
    }*/

    public boolean containsTag(VTValueTag tag) {
        return Arrays.stream(tags).anyMatch(x -> x == tag);
    }

    public VTCharacterValueRulesChainState adaptToVTCharacterValueRulesChainState(){

        if(containsTag(VTValueTag.BLOCK_ENDURANCE) && containsTag(VTValueTag.SKILL)) return VTCharacterValueRulesChainState.EDIT_SKILL_ENDURANCE;
        if(containsTag(VTValueTag.BLOCK_SWIFTNESS) && containsTag(VTValueTag.SKILL)) return VTCharacterValueRulesChainState.EDIT_SKILL_SWIFTNESS;
        if(containsTag(VTValueTag.BLOCK_MIND) && containsTag(VTValueTag.SKILL)) return VTCharacterValueRulesChainState.EDIT_SKILL_MIND;
        if(containsTag(VTValueTag.BLOCK_EGO) && containsTag(VTValueTag.SKILL)) return VTCharacterValueRulesChainState.EDIT_SKILL_EGO;

        if(containsTag(VTValueTag.BLOCK_ENDURANCE) && containsTag(VTValueTag.BLOCK)) return VTCharacterValueRulesChainState.EDIT_BLOCK_ENDURANCE;
        if(containsTag(VTValueTag.BLOCK_SWIFTNESS) && containsTag(VTValueTag.BLOCK)) return VTCharacterValueRulesChainState.EDIT_BLOCK_ENDURANCE;
        if(containsTag(VTValueTag.BLOCK_MIND) && containsTag(VTValueTag.BLOCK)) return VTCharacterValueRulesChainState.EDIT_BLOCK_ENDURANCE;
        if(containsTag(VTValueTag.BLOCK_EGO) && containsTag(VTValueTag.BLOCK)) return VTCharacterValueRulesChainState.EDIT_BLOCK_ENDURANCE;

        throw new IllegalArgumentException(this.toString());
    }
}
