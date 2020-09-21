package control;

import modelobjects.RegistrationResult;
import modelobjects.UserDTO;
import modelobjects.UserManagerDAO;
import util.Roles;

import java.util.ArrayList;
import java.util.List;


public class RegistrierungControl {

    // testen der Eingaben der ersten RegiSeite
    public static RegistrationResult regiFirst(UserDTO dto){

        RegistrationResult result = new RegistrationResult();
        List<String> problemsNotification = new ArrayList<String>();


        if ( UserManagerDAO.getInstance().userExist(dto.getEmail())) {
            result.setRegiResult (false);
            problemsNotification.add("Benutzer E-Mail wurde bereits vergeben");
        }

        if(!inCorrectPassword(dto.getPassword())){
            result.setRegiResult (false);
            problemsNotification.add("Password nicht korrekt gewählt. Mindestens 6 stellig, ein Klein/-Großbuchstabe und eine Zahl  ");
        }

        if (dto.getEmail().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte eine Email angeben");
        }

        if(!dto.getEmail().contains("@")  &&   (!dto.getEmail().contains(".com")  || !dto.getEmail().contains(".de") )){
            result.setRegiResult (false);
            problemsNotification.add("Bitte eine korrekte Email eingeben");
        }
        if (dto.getPassword().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte ein Password angeben");
        }



        if( !dto.getPassword().equals(dto.getPasswordRepeat())){
            result.setRegiResult (false);
            problemsNotification.add("Wiederholung des Passworts stimmt nicht überein");
        }


        if ( !dto.getUser () ){
            result.setRegiResult (false);
            problemsNotification.add("Bitte wähle deinen Benutzer");
        }


        result.setproblemsNotification(problemsNotification);
        return result;

    }

    // testen der Eingaben der zweiten RegiSeite
    public static RegistrationResult regiSecond(UserDTO dto){

        RegistrationResult result = new RegistrationResult();
        List<String> problemsNotification = new ArrayList<String>();

        if (dto.getVorname().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte einen Vornamen angeben");
        }

        if (dto.getLastName().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte einen Nachnamen angeben");
        }
        if (!dto.getFeMale()){
            result.setRegiResult (false);
            problemsNotification.add("Bitte eine Anrede angeben");
        }

        result.setproblemsNotification(problemsNotification);
        return result;
    }


    public static RegistrationResult regiStudent(UserDTO dto){

        RegistrationResult result = new RegistrationResult();
        List<String> problemsNotification = new ArrayList<String>();


        if (dto.getHochschule().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte eine Hochschule wählen");
        }

        if (dto.getStudiengang().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte einen Studiengang wählen");
        }

        if (dto.getSuche().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Erzähl uns wonach du auf der Suche bist!");
        }

        if (dto.getSkills().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte trag deine Fähigkeiten ein!");
        }

        result.setproblemsNotification(problemsNotification);
        return result;
    }


    public static RegistrationResult regiUnternehmen(UserDTO dto){

        RegistrationResult result = new RegistrationResult();
        List<String> problemsNotification = new ArrayList<String>();

        if (dto.getFirmenname().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte trag deinen Firmenname ein!");
        }
        if (dto.getFirmensitz().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte trag deinen Firmensitz ein!");
        }
        if (dto.getBeschreibung().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte trag eine Beschreibung des Unternehmens ein!");
        }
        if (dto.getWebsite().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte trag eine Website ein!");
        }

        if (dto.getBranche().equals("")){
            result.setRegiResult (false);
            problemsNotification.add("Bitte gebe deine Branche ein!");
        }

        result.setproblemsNotification(problemsNotification);
        return result;
    }




    //  Methode zur überprüfung eines korrekten Passworts
    public static boolean inCorrectPassword(String passwort) {
        if (passwort.length() < 6) {
            return false;
        }
        if (anzGrossbuchstaben(passwort) < 1) {
            return false;
        }
        if (anzKleinbuchstaben(passwort) < 1) {
            return false;
        }
        if (anzZiffern(passwort) < 1) {
            return false;
        }

        return true;
    }

    public static int anzGrossbuchstaben(String pass) {
        return anzBuchstabenInIntervall(pass, 'A', 'Z');
    }

    public static int anzKleinbuchstaben(String pass) {
        return anzBuchstabenInIntervall(pass, 'a', 'z');
    }

    public static int anzZiffern(String pass) {
        return anzBuchstabenInIntervall(pass, '0', '9');
    }


    public static int anzBuchstabenInIntervall(String pass, char min, char max) {
        int anz = 0;
        for (char c : pass.toCharArray()) {
            if (c >= min && c <= max) {
                anz = anz + 1;
            }
        }
        return anz;
    }

    // Aufruf des Erzeugens eines Users
    public static boolean registerUser ( UserDTO user ) throws Exception {

        UserManagerDAO.getInstance().registerUser(user);



        if ( user.getBezeichnung().equals(Roles.Kunde_USER)){

           // return UserManagerDAO.getInstance().registerKundeUser(user);
        }

        return false;



    }
}

