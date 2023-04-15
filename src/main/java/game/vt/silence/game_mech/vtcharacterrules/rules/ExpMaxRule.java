package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.VTValue;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ExpMaxRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        //if(!vtCharacterValue.getName().equalsIgnoreCase("value_experience_max")) return;
        if(!vtCharacterValue.equalsVTValue(VTValue.VALUE_EXPERIENCE_MAX)) return;

        //if(vtCharacterValue.getValue() + upDownArg < vtValueMap.get("value_experience_actual").getValue())
        if(vtCharacterValue.getValue() + upDownArg < vtValueMap.get(VTValue.VALUE_EXPERIENCE_ACTUAL).getValue())
            throw new VTCharacterValueBreakRuleException("downgrade some skill before setting this value lower then exp actual");

        //vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);
        vtCharacterValue.modifyValueBy(upDownArg);
        state.setState(VTCharacterValueRulesChainState.EDIT);
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
