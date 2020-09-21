package control;

import com.vaadin.ui.UI;
import modelobjects.AutoDTO;
import modelobjects.ReservierungDAO;
import util.Roles;

public class ReservierungControl {

    private static ReservierungControl search;
    private AutoDTO auto;
    AutoDTO user = (AutoDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);


    private ReservierungControl(){}

    public static ReservierungControl getInstance(){
        if ( search == null){
            search = new ReservierungControl();
        }
        return search;
    }


    public AutoDTO getJob() {
        return auto;
    }

    public void setCar(AutoDTO car) {
        this.auto = car;
    }


    public boolean testInput (String marke, String modell, String baujahr){

        if (marke == null || modell == null || baujahr == null){
            return false;
        }
        else {
            return true;
        }
    }



    public boolean createAuto ( String marke, String modell,int baujahr, String beschreibung) throws Exception {

        return ReservierungDAO.getInstance().createAuto(marke, modell, baujahr,beschreibung, user.getAutoid(), auto.getReservierungid());

    }



}

