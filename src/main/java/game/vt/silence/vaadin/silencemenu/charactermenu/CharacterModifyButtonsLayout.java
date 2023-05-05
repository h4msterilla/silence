package game.vt.silence.vaadin.silencemenu.charactermenu;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.values.VTCharacterValue;

import java.util.List;

public class CharacterModifyButtonsLayout extends HorizontalLayout {

    private static String DOWN = "down";
    private static String UP = "up";

    public CharacterModifyButtonsLayout(VTCharacter selectedCharacter,
                                        List<VTCharacterValue> vtCharacterValueList,
                                        VTCharacterValue vtCharacterValue,
                                        List<Grid<VTCharacterValue>> gridList) {
        add(
                new CharacterModifyButton(selectedCharacter, vtCharacterValueList, vtCharacterValue, gridList, DOWN),
                new CharacterModifyButton(selectedCharacter, vtCharacterValueList, vtCharacterValue, gridList, UP)
        );
    }

}
