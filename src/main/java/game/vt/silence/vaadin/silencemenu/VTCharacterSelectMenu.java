package game.vt.silence.vaadin.silencemenu;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.service.transactions.VTCharacterListProviderService;
import game.vt.silence.vaadin.SpringContextProvider;

import java.util.NoSuchElementException;

public class VTCharacterSelectMenu extends VerticalLayout {

    private VTCharacterListProviderService listProvider;
    Grid<VTCharacter> grid = new Grid<>(VTCharacter.class, false);

    public VTCharacterSelectMenu() {
        listProvider = SpringContextProvider.getVtCharacterListProviderService();


        grid.addColumn(VTCharacter::getCharname).setHeader("Name");
        grid.addColumn(VTCharacter::getPhenotype).setHeader("Phenotype");
        grid.addColumn(VTCharacter::getSelf).setHeader("Self");

        grid.setItems(listProvider.getVTCharacterListByLogInUser());
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        add(grid);

        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
    }

    public VTCharacter getSelectedCharacter() {
        try {
            return grid.getSelectedItems().stream().findAny().get();
        } catch (NoSuchElementException e){
            return null;
        }
    }

}
