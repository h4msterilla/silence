package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.model.VTValue;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VitalStatsCrisisRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        //if(!(vtCharacterValue.getName().equals("vital_stats_health_crisis") ||
        //        vtCharacterValue.getName().equals("vital_stats_sanity_crisis"))) return;
        if (!(vtCharacterValue.equalsVTValue(VTValue.VITAL_STATS_HEALTH_CRISIS)
                || vtCharacterValue.equalsVTValue(VTValue.VITAL_STATS_SANITY_CRISIS))) return;

        if (vtCharacterValue.getValue() + upDownArg < 0)
            throw new VTCharacterValueBreakRuleException("this value can not be lower then 0");

        //vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);
        vtCharacterValue.modifyValueBy(upDownArg);
        state.setState(VTCharacterValueRulesChainState.EDIT);
    }

    @Override
    public int getOrder() {
        return 23;
    }
}
