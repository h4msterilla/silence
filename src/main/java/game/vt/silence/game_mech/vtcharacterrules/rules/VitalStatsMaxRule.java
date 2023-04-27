package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.values.VTCharacterValue;
import game.vt.silence.game_mech.model.values.VTValue;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VitalStatsMaxRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        /*if (vtCharacterValue.getName().equals("vital_stats_health_max")
                || vtCharacterValue.getName().equals("vital_stats_sanity_max"))
            throw new VTCharacterValueBreakRuleException("unmodifiable value");*/
        if (vtCharacterValue.getValueName() == VTValue.VITAL_STATS_HEALTH_MAX
                || vtCharacterValue.getValueName() == VTValue.VITAL_STATS_SANITY_MAX)
            throw new VTCharacterValueBreakRuleException("unmodifiable value");

        //if (!state.getState().toString().contains("_SKILL_")) return;
        if (!state.contains(VTCharacterValueRulesChainState.EDIT_BLOCK)) return;

        /*if (state.getState() == VTCharacterValueRulesChainState.EDIT_SKILL_ENDURANCE ||
                state.getState() == VTCharacterValueRulesChainState.EDIT_SKILL_SWIFTNESS)
            vtValueMap.get("vital_stats_health_max").setValue(
                    vtValueMap.get("block_endurance").getValue() + vtValueMap.get("block_swiftness").getValue()
            );*/
        if (state.contains(VTCharacterValueRulesChainState.EDIT_BLOCK_ENDURANCE) ||
                state.contains(VTCharacterValueRulesChainState.EDIT_BLOCK_SWIFTNESS)) {
            vtValueMap.get(VTValue.VITAL_STATS_HEALTH_MAX).setValue(
                    vtValueMap.get(VTValue.BLOCK_ENDURANCE).getValue() + vtValueMap.get(VTValue.BLOCK_SWIFTNESS).getValue()
            );
            state.add(VTCharacterValueRulesChainState.EDIT_VITAL_STATS)
                    .add(VTCharacterValueRulesChainState.EDIT_VITAL_STATS_HEALTH);
            return;
        }

        /*if (state.getState() == VTCharacterValueRulesChainState.EDIT_SKILL_EGO ||
                state.getState() == VTCharacterValueRulesChainState.EDIT_SKILL_MIND)
            vtValueMap.get("vital_stats_sanity_max").setValue(
                    vtValueMap.get("block_endurance").getValue() + vtValueMap.get("block_swiftness").getValue()
            );*/
        if (state.contains(VTCharacterValueRulesChainState.EDIT_BLOCK_MIND) ||
                state.contains(VTCharacterValueRulesChainState.EDIT_BLOCK_EGO)) {
            vtValueMap.get(VTValue.VITAL_STATS_SANITY_MAX).setValue(
                    vtValueMap.get(VTValue.BLOCK_MIND).getValue() + vtValueMap.get(VTValue.BLOCK_EGO).getValue()
            );
            state.add(VTCharacterValueRulesChainState.EDIT_VITAL_STATS)
                    .add(VTCharacterValueRulesChainState.EDIT_VITAL_STATS_SANITY);
            return;
        }

    }

    @Override
    public int getOrder() {
        return 20;
    }
}
