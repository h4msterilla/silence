package game.vt.silence.vaadin.silencemenu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

public class SilenceHeaderEvent extends ComponentEvent<SilenceHeader> {
    SelectedTab selectedTab;

    public SilenceHeaderEvent(Component source, boolean fromClient, SelectedTab selectedTab) {
        super((SilenceHeader) source, fromClient);
        this.selectedTab = selectedTab;
        //System.out.println("in event");
    }

    public SelectedTab getSelectedTab() {
        return selectedTab;
    }
}
