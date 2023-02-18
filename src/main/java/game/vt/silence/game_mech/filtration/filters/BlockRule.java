package game.vt.silence.game_mech.filtration.filters;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.filtration.VTCharacterValueRule;
import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.model.VTCharacterValue;

import java.util.Map;

public class BlockRule implements VTCharacterValueRule {

    @Override
    public void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, String upDown, VTCharacterValueRulesChainState state) {

        if(vtCharacterValue.getName().contains("block_"))
            throw new VTCharacterValueBreakRuleException("unmodifiable value");

        

    }

    @Override
    public int getOrder() {
        return 10;
    }
}
