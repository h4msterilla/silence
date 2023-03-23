package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStates;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VitalStatsMaxRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainState state) {

        if (vtCharacterValue.getName().equals("vital_stats_health_max")
                || vtCharacterValue.getName().equals("vital_stats_sanity_max"))
            throw new VTCharacterValueBreakRuleException("unmodifiable value");

        if (!state.getState().toString().contains("_SKILL_")) return;

        if (state.getState() == VTCharacterValueRulesChainStates.EDIT_SKILL_ENDURANCE ||
                state.getState() == VTCharacterValueRulesChainStates.EDIT_SKILL_SWIFTNESS)
            vtValueMap.get("vital_stats_health_max").setValue(
                    vtValueMap.get("block_endurance").getValue() + vtValueMap.get("block_swiftness").getValue()
            );

        if (state.getState() == VTCharacterValueRulesChainStates.EDIT_SKILL_EGO ||
                state.getState() == VTCharacterValueRulesChainStates.EDIT_SKILL_MIND)
            vtValueMap.get("vital_stats_sanity_max").setValue(
                    vtValueMap.get("block_endurance").getValue() + vtValueMap.get("block_swiftness").getValue()
            );

    }

    @Override
    public int getOrder() {
        return 20;
    }
}
