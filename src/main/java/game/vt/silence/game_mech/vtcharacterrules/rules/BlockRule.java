package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.values.VTValue;
import game.vt.silence.game_mech.model.values.VTValueTag;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;
import game.vt.silence.game_mech.model.values.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BlockRule implements VTCharacterValueRule {

    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        /*if (vtCharacterValue.getName().contains("block_"))
            throw new VTCharacterValueBreakRuleException("unmodifiable value");*/
        if (vtCharacterValue.getValueName().containsTag(VTValueTag.BLOCK))
            throw new VTCharacterValueBreakRuleException("unmodifiable value");

        //if (!state.getState().toString().contains("_SKILL_")) return;
        if(!state.contains(VTCharacterValueRulesChainState.EDIT_SKILL)) return;

        //String shortBlockName = state.getState().toString().replace("EDIT_SKILL_", "").toLowerCase();
        VTValueTag blockTag = state.adaptToVTValueTagBlock();

        //int blockSum =
        //        vtValueMap.values().stream()
        //                .filter(x -> x.getType().equalsIgnoreCase("skill"))
        //                .filter(x -> x.getName().contains(shortBlockName))
        //                .reduce(0, (sum, value) -> sum + value.getValue(), Integer::sum);
        int blockSum =
                vtValueMap.values().stream()
                        .filter(x -> x.containsTag(blockTag))
                        .filter(x -> x.containsTag(VTValueTag.SKILL))
                        .reduce(0, (sum, value) -> sum + value.getValue(), Integer::sum);

        blockSum = (blockSum - (blockSum % 3)) / 3;
        //String blockName = "block_" + shortBlockName;
        VTValue block = state.adaptToVTValueBlock();
        vtValueMap.get(block).setValue(blockSum);

        state
                .add(VTCharacterValueRulesChainState.EDIT_BLOCK)
                .add(block.adaptToVTCharacterValueRulesChainState());//e.g. EDIT_BLOCK_EGO

    }

    @Override
    public int getOrder() {
        return 10;
    }
}
