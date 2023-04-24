package game.vt.silence.vaadin.silencemenu;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.shared.Registration;
import game.vt.silence.vaadin.components.ThemeButton;

public class SilenceHeader extends HorizontalLayout {

    private Tab myCharactersTab = new Tab("My Characters");
    private Tab selectedCharacterTab = new Tab("Selected Character");
    private SelectedTab selectedTab = SelectedTab.MY_CHARACTERS;

    Tabs tabs = new Tabs(myCharactersTab, selectedCharacterTab);

    public SilenceHeader() {

        tabs.addSelectedChangeListener(e -> {
            Tab selected = e.getSelectedTab();
            selectedTab = SelectedTab.MY_CHARACTERS;

            if (selected == myCharactersTab)
                selectedTab = SelectedTab.MY_CHARACTERS;
            if (selected == selectedCharacterTab)
                selectedTab = SelectedTab.SELECTED_CHARACTER;

            fireEvent(new SilenceHeaderEvent(this, false, selectedTab));
        });

        add(tabs);

        ThemeButton themeButton = new ThemeButton();
        add(themeButton);
    }


    public Registration addTabsListener(ComponentEventListener<SilenceHeaderEvent> listener) {
        return addListener(SilenceHeaderEvent.class, listener);
    }

}
