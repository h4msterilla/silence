package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStates;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CaPRule implements VTCharacterValueRule {

    @Override
    public void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainState state) {

        if (!vtCharacterValue.getType().equalsIgnoreCase("cap")) return;

        if (vtCharacterValue.getValue() + upDownArg < 0)
            throw new VTCharacterValueBreakRuleException("this value can not be lower then 0");

        if (vtCharacterValue.getName().equalsIgnoreCase("cap_drive")
                || vtCharacterValue.getName().equalsIgnoreCase("cap_regeneration")
                || vtCharacterValue.getName().equalsIgnoreCase("cap_light")
                || vtCharacterValue.getName().equalsIgnoreCase("cap_greyness")
                || vtCharacterValue.getName().equalsIgnoreCase("cap_darkness")) {
            vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);
            state.setState(VTCharacterValueRulesChainStates.EDIT_UNLINKED);
            return;
        }

        //if(true){}

    }
/*
cap,cap_drive+ no rules
cap,cap_stress
cap,cap_plan
cap,cap_illness
cap,cap_swiftness_condition
cap,cap_inspiration
cap,cap_intoxication
cap,cap_regeneration+ no rules
cap,cap_passion
cap,cap_light+ no rules
cap,cap_greyness+ no rules
cap,cap_darkness+ no rules

* */
    @Override
    public int getOrder() {
        return 30;
    }
}
