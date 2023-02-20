package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRule;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStates;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VitalStatsCrisisRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainState state) {

        if(!(vtCharacterValue.getName().equals("vital_stats_health_crisis") ||
                vtCharacterValue.getName().equals("vital_stats_sanity_crisis"))) return;

        if(vtCharacterValue.getValue() + upDownArg < 0)
            throw new VTCharacterValueBreakRuleException("this value can not be lower then 0");

        vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);
        state.setState(VTCharacterValueRulesChainStates.EDIT_UNLINKED);
    }

    @Override
    public int getOrder() {
        return 23;
    }
}
