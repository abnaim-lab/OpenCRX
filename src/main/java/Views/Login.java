package Views;

import Exceptions.DatabaseException;
import Exceptions.NoSuchUserOrPassword;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import control.LoginControl;
import modelobjects.UserDTO;
import util.Roles;

public class Login extends VerticalLayout implements View {

    public void setUp() {

        this.setSizeFull();

        final TextField email = new TextField();
        email.setCaption("E-Mail:");
        email.setId("email");

        final PasswordField passwortFeld = new PasswordField();
        passwortFeld.setCaption("Passwort:");
        passwortFeld.setId("psw");

        Label tab = new Label("&nbsp;", ContentMode.HTML);

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(email);
        layout.addComponent(passwortFeld);
        layout.addComponent(tab);



        Panel panel = new Panel("Bitte Logindaten eingeben ");

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(passwortFeld, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(email, Alignment.MIDDLE_CENTER);

        panel.setContent(layout);
        panel.setSizeUndefined();

        Button loginButton = new Button("Login", FontAwesome.SIGN_IN);
        loginButton.addStyleName("loginButton");


        layout.addComponent(loginButton);
        layout.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);


        loginButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                String e_mail = email.getValue();
                String passwort = passwortFeld.getValue();

                try {
                    LoginControl.checkAuthentication(e_mail, passwort);
                    UserDTO user = (UserDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
                    if (user != null) {
                        UI.getCurrent().getNavigator().navigateTo("Suche");
                    }


                    } catch(NoSuchUserOrPassword ex){
                        Notification.show("Fehler", "E-Mail oder Passwort falsch", Notification.Type.ERROR_MESSAGE);
                        email.setValue("");
                        passwortFeld.setValue("");

                    } catch(DatabaseException ex){
                        Notification.show("DB-Fehler", ex.getReason(), Notification.Type.ERROR_MESSAGE);
                        email.setValue("");
                        passwortFeld.setValue("");

                    } catch(Exception e){
                        e.printStackTrace();
                    }


            }
        });



    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {


        UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);

        if (user != null) {
            UI.getCurrent().getNavigator().navigateTo("Main");
        } else {
            this.setUp();
        }

    }

}
