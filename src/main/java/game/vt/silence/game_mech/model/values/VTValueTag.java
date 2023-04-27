package game.vt.silence.game_mech.model.values;

import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;

public enum VTValueTag {
    EXPERIENCE,
    VITAL_STATE,
    HEALTH,
    SANITY,
    BLOCK,
    SKILL,
    SPECIAL_SKILL,
    BLOCK_ENDURANCE,
    BLOCK_SWIFTNESS,
    BLOCK_MIND,
    BLOCK_EGO,
    CONNECTIONS,
    KNOWLEDGE,
    CONDITIONS_AND_PROCESSES/*;

    public static VTCharacterValueRulesChainState adaptToVTCharacterValueRulesChainState(VTValueTag vtValueTag){
        return switch (vtValueTag) {
            case BLOCK_ENDURANCE -> VTCharacterValueRulesChainState.EDIT_SKILL_ENDURANCE;
            case BLOCK_SWIFTNESS -> VTCharacterValueRulesChainState.EDIT_SKILL_SWIFTNESS;
            case BLOCK_MIND -> VTCharacterValueRulesChainState.EDIT_SKILL_MIND;
            case BLOCK_EGO -> VTCharacterValueRulesChainState.EDIT_SKILL_EGO;
            default -> throw new IllegalArgumentException(vtValueTag.toString());
        };
    }*/
}
