package game.vt.silence.vaadin.silencemenu;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("silence")
public class SilenceMenu extends VerticalLayout {

    SilenceHeader silenceHeader = new SilenceHeader();
    VTCharacterSelectMenu selectMenu = new VTCharacterSelectMenu();

    public SilenceMenu(){
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(silenceHeader);

        silenceHeader.addTabsListener(e ->{
            if(e.selectedTab == SelectedTab.MY_CHARACTERS){
                selectMenu.setVisible(true);
            }
            if(e.selectedTab == SelectedTab.SELECTED_CHARACTER){
                selectMenu.setVisible(false);
            }
        });

        add(selectMenu);


    }



}
