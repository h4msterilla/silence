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
public class TalentLimitRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        //if (vtCharacterValue.getName().equalsIgnoreCase("talent_limit"))
        if(vtCharacterValue.equalsVTValue(VTValue.TALENT_LIMIT))
            throw new VTCharacterValueBreakRuleException("unmodifiable value");

        //if (!state.getState().toString().contains("_SKILL_")) return;
        if(!state.contains(VTCharacterValueRulesChainState.EDIT_BLOCK)) return;

        int talentLimit =
                vtValueMap.values().stream()
                        //.filter(x -> x.getType().equalsIgnoreCase("block"))
                        .filter(x -> x.containsTag(VTValueTag.BLOCK))
                        .reduce(0, (sum, value) -> sum + (value.getValue() - value.getValue() % 2) / 2, Integer::sum);

        //vtValueMap.get("talent_limit").setValue(talentLimit);
        vtValueMap.get(VTValue.TALENT_LIMIT).setValue(talentLimit);
    }

    @Override
    public int getOrder() {
        return 40;
    }
}
