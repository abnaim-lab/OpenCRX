package modelobjects;

import modelobjects.UserManagerDAO;

public class AutoDTO {

    public int getAutoid() {
        return autoid;
    }

    public void setAutoid(int autoid) {
        this.autoid = autoid;
    }

    private int autoid;
    private String marke;
    private String modell;
    private String baujahr;
    private String kmStand;

    public int getReservierungid() {
        return reservierungid;
    }

    public void setReservierungid(int reservierungid) {
        this.reservierungid = reservierungid;
    }

    private int reservierungid;

    public void setKmStand(String kmStand) {
        this.kmStand = kmStand;
    }

    public void setAusstattung(String ausstattung) {
        this.ausstattung = ausstattung;
    }

    private String ausstattung ;

    public String getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(String baujahr){

        this.baujahr = baujahr;

    }

    public String getKmStand() {
        return kmStand;
    }

    public String getAusstattung() {
        return ausstattung;
    }


    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }









}
