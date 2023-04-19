package game.vt.silence.vaadin;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("vaadin2")
public class AnotherMenu extends VerticalLayout {

    public AnotherMenu(){

        add(new Label("another menu"));
    }

}
