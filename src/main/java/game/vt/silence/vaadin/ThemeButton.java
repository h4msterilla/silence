package game.vt.silence.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;
import com.vaadin.flow.theme.lumo.Lumo;

public class ThemeButton extends Button {

    private ThemableLayout layout;
    private boolean darkTheme = true;

    public ThemeButton(ThemableLayout layout) {
        this.layout = layout;

        layout.getThemeList().set(Lumo.DARK, true);
        setIcon(new Icon(VaadinIcon.ADJUST));

        addClickListener(x -> {
            darkTheme = !darkTheme;
            if (darkTheme) {
                layout.getThemeList().set(Lumo.DARK, true);
            } else {
                layout.getThemeList().set(Lumo.DARK, false);
            }
        });

    }

}
