package game.vt.silence.game_mech.vtcharacterrules;

public enum VTCharacterValueRulesChainState {
    NOT_EDIT,
    EDIT_SKILL,
    EDIT_SKILL_ENDURANCE,
    EDIT_SKILL_SWIFTNESS,
    EDIT_SKILL_MIND,
    EDIT_SKILL_EGO,
    EDIT_BLOCK,
    EDIT_BLOCK_ENDURANCE,
    EDIT_BLOCK_SWIFTNESS,
    EDIT_BLOCK_MIND,
    EDIT_BLOCK_EGO,
    EDIT,
    EDIT_VITAL_STATS,
    EDIT_VITAL_STATS_HEALTH,
    EDIT_VITAL_STATS_SANITY/*;

    public static VTValueTag adatperToVTValueTag(VTCharacterValueRulesChainState state){
        return switch (state){
            case EDIT_SKILL_ENDURANCE -> VTValueTag.BLOCK_ENDURANCE;
            default -> throw new IllegalArgumentException(state.toString());
        };
    }*/
}
