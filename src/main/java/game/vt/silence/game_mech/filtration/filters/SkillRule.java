package game.vt.silence.game_mech.filtration.filters;

import game.vt.silence.game_mech.filtration.VTCharacterValueRule;
import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillRule implements VTCharacterValueRule {
    @Override
    public void doRule(List<VTCharacterValue> vtCharacterValues, VTCharacterValue vtCharacterValue, String upDown, VTCharacterValueRulesChainState state) {
        System.out.println("in SkillFilter");

        if (!vtCharacterValue.getType().equalsIgnoreCase("skill")) return;


    }

    @Override
    public int getOrder() {
        return 1;
    }
}
