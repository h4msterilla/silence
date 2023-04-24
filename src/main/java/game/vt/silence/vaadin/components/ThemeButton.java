package game.vt.silence.vaadin.components;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.service.SecurityService;
import game.vt.silence.vaadin.SpringContextProvider;

public class ThemeButton extends Button {

    private ThemableLayout mainLayout;
    private boolean darkTheme = true;
    private SecurityService securityService;
    private VTUser vtUser;

    public ThemeButton() {
        securityService = SpringContextProvider.getSecurityService();
        setIcon(new Icon(VaadinIcon.ADJUST));

        addClickListener(x -> {
            darkTheme = !darkTheme;
            mainLayout.getThemeList().set(Lumo.DARK, darkTheme);
        });

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        Component component = this;

        while (!component.getClass().isAnnotationPresent(Route.class)) {
            component = component.getParent().get();
        }
        if (!(component instanceof ThemableLayout)) {
            throw new RuntimeException("ThemeButton has not found main Layout!");
        }
        if (!component.getClass().isAnnotationPresent(Route.class)) {
            throw new RuntimeException("ThemeButton has not found main Layout!");
        }

        mainLayout = (ThemableLayout) component;
        mainLayout.getThemeList().set(Lumo.DARK, darkTheme);
    }

}
