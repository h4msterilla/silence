package game.vt.silence.vaadin.silencemenu.charactermenu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.values.VTCharacterValue;
import game.vt.silence.game_mech.service.transactions.VTCharacterValueEditionService;
import game.vt.silence.vaadin.SpringContextProvider;

import java.util.List;

public class CharacterModifyButton extends Button {

    private VTCharacterValueEditionService vtCharacterValueEditionService;

    public CharacterModifyButton(VTCharacter selectedCharacter,
                                 List<VTCharacterValue> vtCharacterValueList,
                                 VTCharacterValue targetValue,
                                 List<Grid<VTCharacterValue>> gridList,
                                 String upDown) {
        super();
        vtCharacterValueEditionService = SpringContextProvider.getVtCharacterValueEditionService();

        getStyle().set("--lumo-button-size","1.0rem");
        addThemeVariants(ButtonVariant.LUMO_ICON);
        //addThemeVariants(ButtonVariant.LUMO_SMALL);

        if (upDown.equalsIgnoreCase("up")) {
            setIcon(new Icon(VaadinIcon.PLUS));
        }
        if (upDown.equalsIgnoreCase("down")) {
            setIcon(new Icon(VaadinIcon.MINUS));
        }

        addClickListener(e -> {
            try {
                vtCharacterValueEditionService.editValue4Vaadin(selectedCharacter, vtCharacterValueList, targetValue, upDown);
            } catch (VTCharacterValueBreakRuleException ex) {
                Notification.show(ex.getMessage());
            }
            gridList.forEach(grid -> grid.getDataProvider().refreshAll());
        });
    }

}
