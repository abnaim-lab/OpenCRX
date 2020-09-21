package Views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import control.ReservierungControl;
import modelobjects.AutoDTO;
import modelobjects.ReservierungDAO;


import java.util.List;

public class CarSearch extends VerticalLayout implements View {
    private AutoDTO selectedcar;


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }
    public void setUp(){



        final VerticalLayout v = new VerticalLayout();
        v.setWidth("300");
        v.setHeight("300");
        Label t = new Label("<font size =\"7\" style = \"font-family\": \"Courier\">Coll@Cars", ContentMode.HTML);




        final Label label = new Label ("<font size = \"6\" color=\"blue\"> Ihr Traumwagen ist nur einen Click entfernt",
                ContentMode.HTML);
        addComponents(t,label);
        setComponentAlignment(t, Alignment.MIDDLE_CENTER);
        setComponentAlignment(label,Alignment.MIDDLE_CENTER);
        setMargin(true);
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        //Textfield
        final TextField textField = new TextField();
        Label labelText = new Label("Marke eingeben");

        //Buttons
        Button button = new Button("Suche", FontAwesome.SEARCH);
        Button bucheButton = new Button("Reservieren");

        //Layout items hinzufügen
        horizontalLayout.addComponent(labelText);
        horizontalLayout.setComponentAlignment(labelText, Alignment.MIDDLE_CENTER);
        horizontalLayout.addComponent(textField);
        horizontalLayout.addComponent(new Label("&nbsp" , ContentMode.HTML));
        horizontalLayout.addComponent(button);

        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);

        //spacer between items
        Label label1 = new Label("&nbsp;", ContentMode.HTML);
        addComponent(label1);

        //Creating a Table with a Grid

        Grid<AutoDTO> grid = new Grid<>();
        grid.setSizeFull();


        button.addClickListener(new Button.ClickListener(){
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String marke = textField.getValue();

                if(marke.equals("")){
                    Notification.show(null,"Bitte Marke eingeben",Notification.Type.ERROR_MESSAGE);
                } else {
                    List<AutoDTO> liste = null;
                    try {
                        liste = ReservierungDAO.getInstance().getAngebotByReservierung(marke);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //clear previous inputs
                    grid.setItems();
                    //add listitems to the grid
                    grid.setItems(liste);
                    addComponent(grid);

                    //Buche Button under the Grid
                    addComponent(bucheButton);
                    setComponentAlignment(bucheButton , Alignment.MIDDLE_CENTER);
                }
            }
        });

        bucheButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(grid.getSelectedItems()== null){
                    return;
                }else{
                    SingleSelect<AutoDTO> selection = grid.asSingleSelect();
                    AutoDTO selectedcar = null;
                    ReservierungControl.getInstance().setCar(selectedcar);
                   // UI.getCurrent().getNavigator().navigateTo();

                }

            }
        });

        grid.addItemClickListener(new ItemClickListener<AutoDTO>() {
            @Override
            public void itemClick(Grid.ItemClick<AutoDTO> itemClick) {
                CarSearch.this.selectedcar = itemClick.getItem();
            }
        });
    }


}

