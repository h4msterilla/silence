package game.vt.silence.vaadin.silencemenu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.service.VTCharacterValueService;
import game.vt.silence.game_mech.service.transactions.VTCharacterValueEditionService;
import game.vt.silence.vaadin.SpringContextProvider;

import java.util.List;

public class CharacterMenu extends VerticalLayout {

    private static final String UP = "up";
    private static final String DOWN = "down";

    private VTCharacter selectedCharacter = null;
    private VTCharacterValueService vtCharacterValueService;
    private List<VTCharacterValue> vtCharacterValueList = null;
    private Grid<VTCharacterValue> grid = new Grid<>(VTCharacterValue.class, false);

    private VTCharacterValueEditionService valueEditionService;

    public CharacterMenu() {

        vtCharacterValueService = SpringContextProvider.getVtCharacterValueService();
        valueEditionService = SpringContextProvider.getVtCharacterValueEditionService();
        ;

        grid.addColumn(VTCharacterValue::getValueName).setHeader("Skill");
        grid.addColumn(VTCharacterValue::getValue).setHeader("Value");
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);

        grid.addComponentColumn(v -> {
            HorizontalLayout buttonsLayout = new HorizontalLayout(
                    new Button("-", e -> {
                        try {
                            valueEditionService.editValue4Vaadin(selectedCharacter, vtCharacterValueList, v, DOWN);
                        } catch (VTCharacterValueBreakRuleException ex) {
                            Notification.show(ex.getMessage());
                        }
                        grid.getDataProvider().refreshAll();
                    }),
                    new Button("+", e -> {
                        try {
                            valueEditionService.editValue4Vaadin(selectedCharacter, vtCharacterValueList, v, UP);
                        } catch (VTCharacterValueBreakRuleException ex) {
                            Notification.show(ex.getMessage());
                        }
                        grid.getDataProvider().refreshAll();
                    })
            );
            buttonsLayout.setMargin(false);
            return buttonsLayout;
        }).setHeader("modify");

        add(grid);

    }

    public void setSelectedCharacter(VTCharacter vtCharacter) {
        if (vtCharacter == null) {
            Notification.show("No character selected!");
            grid.setVisible(false);
        } else {
            selectedCharacter = vtCharacter;
            Notification.show("selected character: " + vtCharacter.getCharname());

            vtCharacterValueList = vtCharacterValueService.getVTCharacterValuesByVTCharacter(selectedCharacter);
            vtCharacterValueList.sort((v1, v2) -> {
                return v1.getValueName().compareTo(v2.getValueName());
            });
            grid.setItems(vtCharacterValueList);
            grid.setVisible(true);
        }
    }
}
