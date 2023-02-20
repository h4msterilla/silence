package game.vt.silence.game_mech.filtration.filters;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.filtration.VTCharacterValueRule;
import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TalentLimitRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainState state) {

        if (vtCharacterValue.getName().equalsIgnoreCase("talent_limit"))
            throw new VTCharacterValueBreakRuleException("unmodifiable value");

        if (!state.getState().toString().contains("_SKILL_")) return;

        int talentLimit =
                vtValueMap.values().stream()
                        .filter(x -> x.getType().equalsIgnoreCase("block"))
                        .reduce(0, (sum, value) -> sum + (value.getValue() - value.getValue() % 2) / 2, Integer::sum);

        vtValueMap.get("talent_limit").setValue(talentLimit);
    }

    @Override
    public int getOrder() {
        return 40;
    }
}
