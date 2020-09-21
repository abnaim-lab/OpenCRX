package Views;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;


import java.awt.event.ActionListener;

public class MainView extends VerticalLayout implements  View {

    @Override


    public void enter(ViewChangeListener.ViewChangeEvent event){
        this.setup();
    }

    public void setup(){
        final VerticalLayout v = new VerticalLayout();
        v.setWidth("300");
        v.setHeight("300");
        Label t = new Label("<font size =\"7\" style = \"font-family\": \"Courier\">Willkommen bei Coll@Cars", ContentMode.HTML);




        final Label label = new Label ("<font size = \"6\" color=\"blue\"> Finden sie jetzt ihren Traumwagen",
                ContentMode.HTML);
        addComponents(t,label);
        setComponentAlignment(t, Alignment.MIDDLE_CENTER);
        setComponentAlignment(label,Alignment.MIDDLE_CENTER);


        Button registrieren = new Button("Registrieren",(Button.ClickListener) event ->{

            UI.getCurrent().getNavigator().navigateTo("Registrierung");


        });


        Button login = new Button("Login", (Button.ClickListener) event ->{
            UI.getCurrent().getNavigator().navigateTo("Login");
        });
        login.setId("Click");

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponents(login,registrieren);


        Panel panel = new Panel();
        v.addComponents(t,label,horizontalLayout);
        panel.setContent(v);
        panel.setSizeUndefined();
        addComponent(v);
        setComponentAlignment(v,Alignment.MIDDLE_CENTER);




    }



}
