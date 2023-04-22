package game.vt.silence.vaadin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

public class ThemeButton extends Button {

    private ThemableLayout layout;
    private Component component = this;
    private boolean lightTheme = true;

    public ThemeButton() {
        setIcon(new Icon(VaadinIcon.ADJUST));

        addClickListener(x -> {

            while (!component.getClass().isAnnotationPresent(Route.class)){
                component = component.getParent().get();
                if(component.getClass().isAnnotationPresent(Route.class)){
                    layout = (ThemableLayout) component;
                    if(!(component instanceof ThemableLayout)){
                        throw new RuntimeException("ThemeButton has not found main Layout!");
                    }
                }
            }

            lightTheme = !lightTheme;
            if (lightTheme) {
                layout.getThemeList().set(Lumo.DARK, false);
            } else {
                layout.getThemeList().set(Lumo.DARK, true);
            }
        });

    }

}
