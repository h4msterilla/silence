package game.vt.silence.game_mech.filtration;

import game.vt.silence.game_mech.model.VTCharacterValue;

public interface VTCharacterValueFilter {

    void doFilter(VTCharacterValue vtCharacterValue, String upDown);

    int getOrder();
}
