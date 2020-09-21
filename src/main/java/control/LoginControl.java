package control;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import modelobjects.UserManagerDAO;
import modelobjects.UserDTO;
import Exceptions.DatabaseException;
import Exceptions.NoSuchUserOrPassword;
import db.JDBCConnection;
import util.Roles;
import util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginControl {

    public static void checkAuthentication (String email, String password) throws Exception {

        ResultSet set = null;
        try {
            //DB-Zugriff
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT *  "
                    + "FROM Cars.user "
                    + "WHERE Cars.user.email = \'" + email + "\'"
                    + " AND Cars.user.passwort = \'" + password + "\'");



        } catch (SQLException ex) {


            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler im SQL-Befehl. Bitte den Programmierer benachrichtigen.");
        }


        UserDTO user = null;

        try {
            if( set.next() ){

                user = new UserDTO();
                user.setEmail( set.getString(1) );
                user.setVorname( set.getString(3) );
                user.setPassword( set.getString(2) );






            }

            else{
                //Fehlerfall
                throw new NoSuchUserOrPassword();
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

        VaadinSession session = UI.getCurrent().getSession();
        session.setAttribute( Roles.CURRENT_USER , user);

        UI.getCurrent().getNavigator().navigateTo(Views.SUCHE);

    }
    public static void logoutUser() {

        UI.getCurrent().getPage().setLocation("");
        UI.getCurrent().getSession().close();
    }

}
