package Views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import control.RegisterControl;
import modelobjects.RegistrationResult;
import modelobjects.UserDTO;
import modelobjects.UserManagerDAO;

public class Registration extends VerticalLayout implements View {
    private UserDTO userDTO = new UserDTO();

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){
        this.setup();
    }


    public void setup () {



        FormLayout form = new FormLayout();
        form.setMargin(false);
        form.setWidth(String.valueOf(300));
        form.setHeight(String.valueOf(300));


        Label t = new Label("<font size =\"7\" style = \"font-family\": \"Courier\">Coll@Cars", ContentMode.HTML);


        final Label label = new Label("<font size = \"6\" color=\"blue\"> Jetzt schnell registrieren und Traumwagen finden",
                ContentMode.HTML);

        TextField firstname = new TextField("Vorname");
        TextField lastname = new TextField("Nachname");
        DateField date = new DateField("Geburtstag");
        TextField email = new TextField("Email");
        PasswordField psswrd = new PasswordField("Passwort");

        Button button = new Button("Registrieren");

        button.addClickListener(e -> {

            userDTO.setVorname(firstname.getValue());//
            userDTO.setLastName(lastname.getValue());
            userDTO.setBirthdate(date.getValue());
            userDTO.setEmail(email.getValue());
            userDTO.setPassword(psswrd.getValue());

            RegistrationResult regiresult = RegisterControl.regiFirst(userDTO);

            RegisterControl.regiStudent(userDTO);
            try {
                UserManagerDAO.getInstance().registerUser(userDTO);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            try {
               // UserManagerDAO.getInstance().registerKundeUser(userDTO);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            UI.getCurrent().getNavigator().navigateTo("Main");


        });


        form.addComponents(t, label, firstname, lastname, date, email,  psswrd, button);
        final VerticalLayout verticalLayout = new VerticalLayout();
        setHeight("1000");
        setHeight("1000");
        verticalLayout.setWidth("400");
        verticalLayout.setWidth("400");
        verticalLayout.addComponents(form);
        addComponent(verticalLayout);




        setComponentAlignment(verticalLayout, Alignment.TOP_CENTER);


    }
}
