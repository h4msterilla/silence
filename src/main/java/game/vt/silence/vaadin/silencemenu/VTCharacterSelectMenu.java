package game.vt.silence.vaadin.silencemenu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.service.transactions.VTCharacterCreationService;
import game.vt.silence.game_mech.service.transactions.VTCharacterListProviderService;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.service.SecurityService;
import game.vt.silence.vaadin.SpringContextProvider;

import java.util.List;
import java.util.NoSuchElementException;

public class VTCharacterSelectMenu extends VerticalLayout {

    private VTCharacterListProviderService listProvider;
    private VTCharacterCreationService vtCharacterCreationService;
    private SecurityService securityService;
    private VTUser vtUser;
    private List<VTCharacter> characterList;

    private Grid<VTCharacter> grid = new Grid<>(VTCharacter.class, false);

    private Button characterAddButton = new Button("add Character");

    public VTCharacterSelectMenu() {
        listProvider = SpringContextProvider.getVtCharacterListProviderService();
        vtCharacterCreationService = SpringContextProvider.getVtCharacterCreationService();
        securityService = SpringContextProvider.getSecurityService();

        vtUser = securityService.findLoggedInVT_User();
        characterList = listProvider.getVTCharacterListByLogInUser();

        grid.addColumn(VTCharacter::getCharname).setHeader("Name");
        grid.addColumn(VTCharacter::getPhenotype).setHeader("Phenotype");
        grid.addColumn(VTCharacter::getSelf).setHeader("Self");

        grid.setItems(characterList);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        add(grid);

        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);

        characterAddButton.addClickListener(e -> {
            vtCharacterCreationService.create4vaadin(vtUser, characterList);
            grid.getDataProvider().refreshAll();
        });

        setAlignSelf(Alignment.END, characterAddButton);
        add(characterAddButton);
    }

    public VTCharacter getSelectedCharacter() {
        try {
            return grid.getSelectedItems().stream().findAny().get();
        } catch (NoSuchElementException e){
            return null;
        }
    }

}
