package game.vt.silence.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import game.vt.silence.security.service.SecurityService;

public class LoginMenu extends VerticalLayout {

    Label infoLabel = new Label("type login and password");
    TextField loginField = new TextField("login");
    PasswordField passwordField = new PasswordField("password");
    Button loginButton = new Button("login in", e -> login());

    public LoginMenu() {
        add(infoLabel, loginField, passwordField, loginButton);
    }

    SecurityService securityService = SpringContextProvider.getSecurityService();

    private void login() {
        if (securityService.autoLogin(loginField.getValue(), passwordField.getValue())) {
            Notification.show("login success");
            getUI().ifPresent(ui -> ui.navigate("silence"));
        } else {
            Notification.show("wrong login or password!");
        }
    }

}
