package game.vt.silence.game_mech.filtration.filters;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.filtration.VTCharacterValueRule;
import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.model.VTCharacterValue;

import java.util.Map;

public class ActualExpRule implements VTCharacterValueRule {

    @Override
    public void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, String upDown, VTCharacterValueRulesChainState state) {

        if(vtCharacterValue.getName().equalsIgnoreCase("value_experience_actual"))
            throw new VTCharacterValueBreakRuleException("unmodifiable value");

        if(!state.getState().toString().contains("_SKILL_")) return;

        int sum = vtValueMap.values().stream()
                .filter(x -> x.getType().equals("skill"))
                .reduce(0, (partialSum, vtValue) -> partialSum + vtValue.getValue(), Integer::sum);

        vtValueMap.get("value_experience_actual").setValue(sum);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
