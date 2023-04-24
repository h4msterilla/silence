package game.vt.silence.vaadin.logreg;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import game.vt.silence.vaadin.components.ThemeButton;

@Route("")
public class LogRegMenu extends VerticalLayout {

    private LoginMenu loginMenu = new LoginMenu();
    private Tab loginTab = new Tab("Login");
    private RegMenu regMenu = new RegMenu();
    private Tab regTab = new Tab("Register");
    private Tabs tabs = new Tabs(loginTab, regTab);

    boolean darkTheme = true;
    private Button themeButton = new ThemeButton();

    public LogRegMenu() {
        setSizeFull();

        HorizontalLayout horizontalLayout = new HorizontalLayout(themeButton);
        add(horizontalLayout);
        setHorizontalComponentAlignment(Alignment.END, horizontalLayout);

        setAlignItems(Alignment.CENTER);
        tabs.setSelectedTab(loginTab);
        tabs.addSelectedChangeListener(e -> checkTabs());
        add(tabs);

        loginMenu.setAlignItems(Alignment.CENTER);
        loginMenu.setVisible(true);
        add(loginMenu);

        regMenu.setAlignItems(Alignment.CENTER);
        regMenu.setVisible(false);
        add(regMenu);
    }

    private void checkTabs() {
        if (tabs.getSelectedTab() == loginTab) {
            loginMenu.setVisible(true);
            regMenu.setVisible(false);
        }
        if (tabs.getSelectedTab() == regTab) {
            loginMenu.setVisible(false);
            regMenu.setVisible(true);
        }
    }

}
