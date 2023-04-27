package game.vt.silence.vaadin.silencemenu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.service.transactions.VTCharacterCreationService;
import game.vt.silence.game_mech.service.transactions.VTCharacterListProviderService;
import game.vt.silence.game_mech.service.transactions.VTCharacterRetireService;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.service.SecurityService;
import game.vt.silence.vaadin.SpringContextProvider;

import java.util.List;
import java.util.NoSuchElementException;

public class VTCharacterSelectMenu extends VerticalLayout {

    private VTCharacterListProviderService listProvider;
    private VTCharacterCreationService vtCharacterCreationService;
    private VTCharacterRetireService vtCharacterRetireService;
    private SecurityService securityService;
    private VTUser vtUser;
    private List<VTCharacter> characterList;

    private Grid<VTCharacter> grid = new Grid<>(VTCharacter.class, false);

    private Button characterAddButton = new Button("add Character");
    private Button characterRetireButton = new Button("retire Character");
    private HorizontalLayout characterButtonLayout = new HorizontalLayout(characterAddButton, characterRetireButton);

    public VTCharacterSelectMenu() {
        listProvider = SpringContextProvider.getVtCharacterListProviderService();
        vtCharacterCreationService = SpringContextProvider.getVtCharacterCreationService();
        securityService = SpringContextProvider.getSecurityService();
        vtCharacterRetireService = SpringContextProvider.getVtCharacterRetireService();

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
            VTCharacter createCharacter = vtCharacterCreationService.create4vaadin(vtUser, characterList);
            grid.getDataProvider().refreshAll();
            Notification.show("create new " + createCharacter.getCharname());
        });

        characterRetireButton.addClickListener(e -> {
            VTCharacter selectedCharacter = getSelectedCharacter();
            if(selectedCharacter == null){
                Notification.show("select any Character to retire");
                return;
            }

            ConfirmDialog dialog = new ConfirmDialog();
            dialog.setHeader("Retire Character");
            dialog.setText("Are you sure you want to retire "+ selectedCharacter.getCharname() + "?");
            dialog.setCancelable(true);
            dialog.setConfirmText("Retire");
            dialog.setConfirmButtonTheme("error primary");
            dialog.addConfirmListener(confirmEvent ->{
                vtCharacterRetireService.retire4vaadin(vtUser, characterList, selectedCharacter);
                grid.getDataProvider().refreshAll();
                Notification.show(selectedCharacter.getCharname() + " retired");
                grid.select(null);
            });
            dialog.open();
        });

        setAlignSelf(Alignment.END, characterButtonLayout);
        add(characterButtonLayout);
    }

    public VTCharacter getSelectedCharacter() {
        try {
            return grid.getSelectedItems().stream().findAny().get();
        } catch (NoSuchElementException e){
            return null;
        }
    }

}
