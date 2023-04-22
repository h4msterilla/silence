package game.vt.silence.vaadin;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("silence")
public class SilenceMenu extends VerticalLayout {

    public SilenceMenu(){
        add(new Label("silence"));

        add(new VTCharacterSelectMenu());

    }

}
