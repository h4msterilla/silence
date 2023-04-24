package game.vt.silence.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import game.vt.silence.vaadin.logreg.LoginMenu;

@Route("vaadin")
public class MyMainView extends VerticalLayout {

    private TextField usernameField = new TextField("Username");
    private PasswordField passwordField = new PasswordField("Password");

    private LoginMenu loginMenu = new LoginMenu();
    private boolean isLoginMenuVisible = true;
    private Button visibleButton = new Button("visible", e -> {
        isLoginMenuVisible = !isLoginMenuVisible;
        loginMenu.setVisible(isLoginMenuVisible);
    });

    public MyMainView() {
        //VaadinService.getCurrent()



        add(new Label("Hello, world!"));
        add(usernameField);
        add(passwordField);

        add(loginMenu);

        add(new Button("Click me", e -> Notification.show("Hello!")));
        add(visibleButton);
        //add(new Button(("to vaadin2", e -> )));
        Button button = new Button("to vaadin 2");
        button.addClickListener(e -> button.getUI().ifPresent(ui -> ui.navigate("vaadin2")));
        add(button);
        add(new RouterLink("to vaadin2", AnotherMenu.class));

        //VaadinServletResponse response = VaadinServletResponse.getCurrent();
        //response.getHeader
    }

}
