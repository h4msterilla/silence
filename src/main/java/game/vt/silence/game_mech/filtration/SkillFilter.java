package game.vt.silence.game_mech.filtration;

import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

@Component
public class SkillFilter implements VTCharacterValueFilter {
    @Override
    public void doFilter(VTCharacterValue vtCharacterValue, String upDown) {
        System.out.println("in SkillFilter");

        if(!vtCharacterValue.getType().equalsIgnoreCase("skill"))return;



    }

    @Override
    public int getOrder() {
        return 1;
    }
}