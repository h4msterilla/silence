package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRule;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BlockRule implements VTCharacterValueRule {

    @Override
    public void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainState state) {

        if (vtCharacterValue.getName().contains("block_"))
            throw new VTCharacterValueBreakRuleException("unmodifiable value");

        if (!state.getState().toString().contains("_SKILL_")) return;

        String shortBlockName = state.getState().toString().replace("EDIT_SKILL_", "").toLowerCase();

        int blockSum =
                vtValueMap.values().stream()
                        .filter(x -> x.getType().equalsIgnoreCase("skill"))
                        .filter(x -> x.getName().contains(shortBlockName))
                        .reduce(0, (sum, value) -> sum + value.getValue(), Integer::sum);

        blockSum = (blockSum - (blockSum % 3)) / 3;
        String blockName = "block_" + shortBlockName;
        vtValueMap.get(blockName).setValue(blockSum);

    }

    @Override
    public int getOrder() {
        return 10;
    }
}
