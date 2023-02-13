package game.vt.silence.game_mech.filtration;

import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

@Component
public class SomeShitFilter implements VTCharacterValueFilter{
    @Override
    public void doFilter(VTCharacterValue vtCharacterValue, String upDown) {
        System.out.println("in SomeShitFilter");
    }

    @Override
    public int getOrder() {
        return 2;
    }
}