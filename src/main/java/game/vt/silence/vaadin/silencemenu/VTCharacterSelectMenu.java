package game.vt.silence.vaadin.silencemenu;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.service.transactions.VTCharacterListProviderService;
import game.vt.silence.vaadin.SpringContextProvider;

public class VTCharacterSelectMenu extends VerticalLayout {

    private VTCharacterListProviderService listProvider;

    public VTCharacterSelectMenu() {
        listProvider = SpringContextProvider.getVtCharacterListProviderService();

        Grid<VTCharacter> grid = new Grid<>(VTCharacter.class, false);
        grid.addColumn(VTCharacter::getCharname).setHeader("Name");
        grid.addColumn(VTCharacter::getPhenotype).setHeader("Phenotype");
        grid.addColumn(VTCharacter::getSelf).setHeader("Self");

        grid.setItems(listProvider.getVTCharacterListByLogInUser());
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        add(grid);

        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
    }

}
