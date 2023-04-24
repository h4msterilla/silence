package game.vt.silence.vaadin.silencemenu;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import game.vt.silence.game_mech.model.VTCharacter;

@Route("silence")
public class SilenceMenu extends VerticalLayout {

    private SilenceHeader silenceHeader = new SilenceHeader();
    private VTCharacterSelectMenu selectMenu = new VTCharacterSelectMenu();
    private CharacterMenu characterMenu = new CharacterMenu();

    public SilenceMenu(){
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(silenceHeader);

        silenceHeader.addTabsListener(e ->{
            if(e.selectedTab == SelectedTab.MY_CHARACTERS){
                selectMenu.setVisible(true);
                characterMenu.setVisible(false);
            }
            if(e.selectedTab == SelectedTab.SELECTED_CHARACTER){
                selectMenu.setVisible(false);
                characterMenu.setVisible(true);
                characterMenu.setSelectedCharacter(selectMenu.getSelectedCharacter());
            }
        });

        add(selectMenu);
        characterMenu.setVisible(false);
        add(characterMenu);


    }



}
