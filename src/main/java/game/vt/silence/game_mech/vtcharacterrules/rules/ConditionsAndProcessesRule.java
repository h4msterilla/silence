package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.values.VTValue;
import game.vt.silence.game_mech.model.values.VTValueTag;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.model.values.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConditionsAndProcessesRule implements VTCharacterValueRule {

    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        //if (!vtCharacterValue.getType().equalsIgnoreCase("cap")) return;
        if(!vtCharacterValue.containsTag(VTValueTag.CONDITIONS_AND_PROCESSES)) return;

        if (vtCharacterValue.getValue() + upDownArg < 0)
            throw new VTCharacterValueBreakRuleException("this value can not be lower then 0");

        /*if (vtCharacterValue.getName().equalsIgnoreCase("cap_drive")
                || vtCharacterValue.getName().equalsIgnoreCase("cap_regeneration")
                || vtCharacterValue.getName().equalsIgnoreCase("cap_light")
                || vtCharacterValue.getName().equalsIgnoreCase("cap_greyness")
                || vtCharacterValue.getName().equalsIgnoreCase("cap_darkness")) {
            vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);
            state.setState(VTCharacterValueRulesChainState.EDIT);
            return;
        }*/
        if(vtCharacterValue.equalsVTValue(VTValue.CAP_DRIVE)
            || vtCharacterValue.equalsVTValue(VTValue.CAP_REGENERATION)
            || vtCharacterValue.equalsVTValue(VTValue.CAP_LIGHT)
            || vtCharacterValue.equalsVTValue(VTValue.CAP_GREYNESS)
            || vtCharacterValue.equalsVTValue(VTValue.CAP_DARKNESS)){
            vtCharacterValue.modifyValueBy(upDownArg);
            state.add(VTCharacterValueRulesChainState.EDIT);
            return;
        }

        state.add(VTCharacterValueRulesChainState.EDIT);

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
