package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.VTValue;
import game.vt.silence.game_mech.model.VTValueTag;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ExpActualRule implements VTCharacterValueRule {

    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        //if(vtCharacterValue.getName().equalsIgnoreCase("value_experience_actual"))
        //    throw new VTCharacterValueBreakRuleException("unmodifiable value");
        if(vtCharacterValue.equalsVTValue(VTValue.VALUE_EXPERIENCE_ACTUAL))
            throw new VTCharacterValueBreakRuleException("unmodifiable value");

        //if(!state.getState().toString().contains("_SKILL_")) return;
        if(!state.contains(VTCharacterValueRulesChainState.EDIT_SKILL)) return;

        int sum = vtValueMap.values().stream()
                .filter(x -> x.containsTag(VTValueTag.SKILL))
                .reduce(0, (partialSum, vtValue) -> partialSum + vtValue.getValue(), Integer::sum);

        //vtValueMap.get("value_experience_actual").setValue(sum);
        vtValueMap.get(VTValue.VALUE_EXPERIENCE_ACTUAL).setValue(sum);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
