package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.model.VTValue;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;

import java.util.Map;

public class VitalStatsAddRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        //if(!(vtCharacterValue.getName().equals("vital_stats_health_add") ||
        //vtCharacterValue.getName().equals("vital_stats_sanity_add"))) return;
        if (!(vtCharacterValue.equalsVTValue(VTValue.VITAL_STATS_HEALTH_ADD)
                || vtCharacterValue.equalsVTValue(VTValue.VITAL_STATS_SANITY_ADD))) return;

        if (vtCharacterValue.getValue() + upDownArg < 0)
            throw new VTCharacterValueBreakRuleException("this value can not be lower then 0");

        //vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);
        vtCharacterValue.modifyValueBy(upDownArg);
        //state.setState(VTCharacterValueRulesChainState.EDIT);
        state.add(VTCharacterValueRulesChainState.EDIT);
    }

    @Override
    public int getOrder() {
        return 21;
    }
}
