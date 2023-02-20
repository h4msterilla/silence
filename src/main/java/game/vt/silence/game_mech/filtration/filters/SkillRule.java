package game.vt.silence.game_mech.filtration.filters;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
import game.vt.silence.game_mech.filtration.VTCharacterValueRule;
import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChainStates;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SkillRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainState state) {

        if (!vtCharacterValue.getType().equalsIgnoreCase("skill")) return;

        int expMax = vtValueMap.get("value_experience_max").getValue();
        int expActual = vtValueMap.get("value_experience_actual").getValue();

        if (expActual + upDownArg > expMax)
            throw new VTCharacterValueBreakRuleException("not enough experience to up skill");

        if (vtCharacterValue.getValue() + upDownArg > 8)
            throw new VTCharacterValueBreakRuleException("skill can not be upper then 8");

        if (vtCharacterValue.getValue() + upDownArg < 0)
            throw new VTCharacterValueBreakRuleException("skill can not be lower then 0");

        vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);
        state.setState(blockType(vtCharacterValue.getName()));

    }

    @Override
    public int getOrder() {
        return 1;
    }

    private VTCharacterValueRulesChainStates blockType(String skillName) {
        if (skillName.contains("endurance")) return VTCharacterValueRulesChainStates.EDIT_SKILL_ENDURANCE;
        if (skillName.contains("swiftness")) return VTCharacterValueRulesChainStates.EDIT_SKILL_SWIFTNESS;
        if (skillName.contains("mind")) return VTCharacterValueRulesChainStates.EDIT_SKILL_MIND;
        if (skillName.contains("ego")) return VTCharacterValueRulesChainStates.EDIT_SKILL_EGO;
        throw new VTCharacterValueNotFoundException();
    }

}
