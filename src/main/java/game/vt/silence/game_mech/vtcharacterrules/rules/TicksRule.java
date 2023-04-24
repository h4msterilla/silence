package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.VTValue;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TicksRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        //if(!vtCharacterValue.getName().equalsIgnoreCase("value_ticks"))return;
        if(!vtCharacterValue.equalsVTValue(VTValue.VALUE_TICKS)) return;

        if(vtCharacterValue.getValue() + upDownArg < 0)
            throw new VTCharacterValueBreakRuleException("this value can not be lower then 0");

        //vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);
        vtCharacterValue.modifyValueBy(upDownArg);
        state.add(VTCharacterValueRulesChainState.EDIT);
        //state.setState(VTCharacterValueRulesChainState.EDIT);
    }

    @Override
    public int getOrder() {
        return 4;
    }
}
