package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.values.VTValue;
import game.vt.silence.game_mech.model.values.VTValueTag;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.model.values.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SkillRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        //if (!vtCharacterValue.getType().equalsIgnoreCase("skill")) return;
        if(!vtCharacterValue.getValueName().containsTag(VTValueTag.SKILL)) return;

        //int expMax = vtValueMap.get("value_experience_max").getValue();
        int expMax = vtValueMap.get(VTValue.VALUE_EXPERIENCE_MAX).getValue();
        //int expActual = vtValueMap.get("value_experience_actual").getValue();
        int expActual = vtValueMap.get(VTValue.VALUE_EXPERIENCE_ACTUAL).getValue();

        if (expActual + upDownArg > expMax)
            throw new VTCharacterValueBreakRuleException("not enough experience to up skill");

        if (vtCharacterValue.getValue() + upDownArg > 8)
            throw new VTCharacterValueBreakRuleException("skill can not be upper then 8");

        if (vtCharacterValue.getValue() + upDownArg < 0)
            throw new VTCharacterValueBreakRuleException("skill can not be lower then 0");

        vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);

        //state.setState(blockType(vtCharacterValue.getValueName()));
        state
                .add(VTCharacterValueRulesChainState.EDIT_SKILL)
                //.add(blockType(vtCharacterValue.getValueName()));
                .add(vtCharacterValue.getValueName().adaptToVTCharacterValueRulesChainState());//e.g. EDIT_SKILL_MIND
    }

    @Override
    public int getOrder() {
        return 1;
    }

    /*private VTCharacterValueRulesChainState blockType(VTValue vtValue) {
        //if (skillName.contains("endurance")) return VTCharacterValueRulesChainStates.EDIT_SKILL_ENDURANCE;
        //if (skillName.contains("swiftness")) return VTCharacterValueRulesChainStates.EDIT_SKILL_SWIFTNESS;
        //if (skillName.contains("mind")) return VTCharacterValueRulesChainStates.EDIT_SKILL_MIND;
        //if (skillName.contains("ego")) return VTCharacterValueRulesChainStates.EDIT_SKILL_EGO;

        if(vtValue.containsTag(VTValueTag.BLOCK_ENDURANCE)) return VTCharacterValueRulesChainState.EDIT_SKILL_ENDURANCE;
        if(vtValue.containsTag(VTValueTag.BLOCK_SWIFTNESS)) return VTCharacterValueRulesChainState.EDIT_SKILL_SWIFTNESS;
        if(vtValue.containsTag(VTValueTag.BLOCK_MIND)) return VTCharacterValueRulesChainState.EDIT_SKILL_MIND;
        if(vtValue.containsTag(VTValueTag.BLOCK_EGO)) return VTCharacterValueRulesChainState.EDIT_SKILL_EGO;

        throw new VTCharacterValueNotFoundException();
    }*/

}
