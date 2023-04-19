package game.vt.silence.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class RegMenu extends VerticalLayout {

    Label infoLabel = new Label("registration");
    TextField loginField = new TextField("login");
    PasswordField passwordField = new PasswordField("password");
    PasswordField passwordConfirmField = new PasswordField("password confirm");
    Button loginButton = new Button("register", e -> register());

    public RegMenu() {
        add(infoLabel, loginField, new HorizontalLayout(passwordField, passwordConfirmField), loginButton);

    }

    private void register() {
        System.out.println("click reg");
    }
}
