package game.vt.silence.vaadin.logreg;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import game.vt.silence.exceptions.VTValidationException;
import game.vt.silence.security.service.SecurityService;
import game.vt.silence.security.validation.PasswordValidator;
import game.vt.silence.security.validation.UsernameValidator;
import game.vt.silence.vaadin.SpringContextProvider;

public class RegMenu extends VerticalLayout {

    Label infoLabel = new Label("registration");
    TextField loginField = new TextField("login");
    PasswordField passwordField = new PasswordField("password");
    PasswordField passwordConfirmField = new PasswordField("password confirm");
    Button loginButton = new Button("register", e -> register());

    SecurityService securityService;

    public RegMenu() {
        add(infoLabel, loginField, new HorizontalLayout(passwordField, passwordConfirmField), loginButton);
        securityService = SpringContextProvider.getSecurityService();
    }

    private void register() {

        if(!passwordField.getValue().equals(passwordConfirmField.getValue())){
            Notification.show("Passwords are not same");
            return;
        }

        try{
            UsernameValidator.validate(loginField.getValue());
            PasswordValidator.validate(passwordField.getValue());
        }catch (VTValidationException e){
            Notification.show(e.getMessage());
            return;
        }

        if(securityService.regUser4Vaadin(loginField.getValue(),passwordField.getValue())){
            Notification.show("reg success!");
        }else{
            Notification.show("username already occupied!");
        }
    }
}
