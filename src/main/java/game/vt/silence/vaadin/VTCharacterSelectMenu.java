package game.vt.silence.vaadin;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import game.vt.silence.game_mech.service.transactions.VTCharacterListProviderService;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.service.VTUserService;

public class VTCharacterSelectMenu extends VerticalLayout {

    private VTCharacterListProviderService listProvider;

    public VTCharacterSelectMenu(){
        //vtUserService = SpringContextProvider.getVtUserService();
        listProvider = SpringContextProvider.getVtCharacterListProviderService();

        listProvider.getAllVTCharacterList().forEach(x ->{
            add(new Label(x.getCharname()));
        });

    }

}
