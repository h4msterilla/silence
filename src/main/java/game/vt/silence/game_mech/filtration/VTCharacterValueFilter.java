package game.vt.silence.game_mech.filtration;

import game.vt.silence.game_mech.model.VTCharacterValue;

import java.util.List;

public interface VTCharacterValueFilter {

    void doFilter(List<VTCharacterValue> vtCharacterValues,VTCharacterValue vtCharacterValue, String upDown);

    int getOrder();
}
