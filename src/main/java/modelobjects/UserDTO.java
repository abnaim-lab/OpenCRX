package modelobjects;

//import UserManagaerDAO

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class UserDTO {


    private int id;
    private String password;
    private String passwordRepeat;
    private String vorname;
    private String lastName;
    private LocalDate birthdate;
    private String email;
    private char telephone = 0;
    private boolean user;
    private boolean feMale;
    private String bezeichnung;

    private String hochschule = null;
    private String studiengang = null;
    private String suche = null;
    private String skills = null;

    private String firmenname = null;
    private String firmensitz = null;
    private String beschreibung = null;
    private String website = null;
    private String branche = null;

    private List<RoleDTO> roles = new ArrayList();



    public UserDTO(){}






    public boolean getFeMale() {
        return feMale;
    }

    // Getter und Setter
    public void setFeMale(boolean feMale) {
        this.feMale = feMale;
    }



    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public boolean getUser (){
        return user;
    }

    public String getBranche() {
        return branche;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }



    public String getFirmenname() {
        return firmenname;
    }

    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }
    public String getHochschule() {
        return hochschule;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public void setHochschule(String hochschule) {
        this.hochschule = hochschule;
    }

    public String getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }

    public String getSuche() {
        return suche;
    }

    public void setSuche(String suche) {
        this.suche = suche;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getFirmensitz() {
        return firmensitz;
    }

    public void setFirmensitz(String Firmensitz) {
        this.firmensitz = Firmensitz;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getTelephone() {
        return telephone;
    }

    public void setTelephone(char telephone) {
        this.telephone = telephone;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}

