package game.vt.silence.game_mech.filtration.filters;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.filtration.VTCharacterValueRule;
import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChainStates;
import game.vt.silence.game_mech.model.VTCharacterValue;

import java.util.Map;

public class VitalStatsAddRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, String upDown, VTCharacterValueRulesChainState state) {

        if(!(vtCharacterValue.getName().equals("vital_stats_health_add") ||
        vtCharacterValue.getName().equals("vital_stats_sanity_add"))) return;

        int upDownArg;

        if(upDown.equalsIgnoreCase("up"))
            upDownArg = 1;
        else
            upDownArg = -1;

        if(vtCharacterValue.getValue() + upDownArg < 0)
            throw new VTCharacterValueBreakRuleException("this value can not be lower then 0");

        vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);

        state.setState(VTCharacterValueRulesChainStates.EDIT_UNLINKED);

    }

    @Override
    public int getOrder() {
        return 21;
    }
}
