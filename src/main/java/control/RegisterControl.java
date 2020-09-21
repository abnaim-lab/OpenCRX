package control;

import modelobjects.RegistrationResult;
import modelobjects.UserDTO;
import modelobjects.UserManagerDAO;
import util.Roles;

import java.util.ArrayList;
import java.util.List;


public class RegisterControl {

    // testen der Eingaben der ersten RegiSeite
    public static RegistrationResult regiFirst(UserDTO dto){

        RegistrationResult result = new RegistrationResult();
        List<String> problemsNotification = new ArrayList<String>();


        if ( UserManagerDAO.getInstance().userExist(dto.getEmail())) {
            result.setRegiResult (false);
            problemsNotification.add("Benutzer E-Mail wurde bereits vergeben");
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


        result.setproblemsNotification(problemsNotification);
        return result;
    }


    public static RegistrationResult regiStudent(UserDTO dto){

        RegistrationResult result = new RegistrationResult();
        List<String> problemsNotification = new ArrayList<String>();




        result.setproblemsNotification(problemsNotification);
        return result;
    }











    // Aufruf des Erzeugens eines Users
    public static boolean registerUser ( UserDTO user ) throws Exception {

        UserManagerDAO.getInstance().registerUser(user);





        return false;



    }
}

