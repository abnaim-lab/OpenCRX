package modelobjects;

import com.vaadin.data.Result;
import com.vaadin.ui.Notification;
import modelobjects.RoleDTO;
import modelobjects.UserDTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class UserManagerDAO extends AbstractDAO {

    private static UserManagerDAO dao = null;


    private UserManagerDAO() {

    }

    public static UserManagerDAO getInstance() {
        if (dao == null) {
            dao = new UserManagerDAO();
        }
        return dao;
    }



    public boolean updateKunde(KundeDTO dTO, UserDTO user) {
        Statement statement = this.getStatement();
        try {
            statement.executeUpdate(
                    "UPDATE cars.kunde SET \"vorname\" = \'" + dTO.getFirstName() + "\' WHERE \"email\" = \'" + user.getEmail() + "\'"
            );
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }


    //register User
    public boolean registerUser(UserDTO user) throws Exception {


        String sql = "INSERT INTO cars.user VALUES (?,?,?,?, ?);";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setString(1, (user.getEmail()));
            statement.setString(2, (user.getPassword()));
            statement.setString(3, (user.getVorname()));
            statement.setString(4, (user.getLastName()));
            statement.setDate(5, Date.valueOf((user.getBirthdate())));

            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            Notification.show("SQL Fehler UserManagerDAO.registerUser", Notification.TYPE_ERROR_MESSAGE);
            return false;
        }

    }

















    public List getUser(String email) {
        RoleDAO dao;
        List<RoleDTO> liste = new ArrayList<>();
        liste = RoleDAO.getInstance().getRolesForUserEmail(email);
        RoleDTO role = liste.get(0);
        String s = role.getBezeichnung();


        Statement statement = this.getStatement();

        ResultSet rs = null;


        if (s.equals("kunde")) {

            try {
                rs = statement.executeQuery("Select *"
                        + " FROM cars.user"
                        + " INNER JOIN cars.kunde ON user.email = kunde.email AND user.email = \'" + email + "\'");
            } catch (SQLException ex) {
                Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
                Notification.show("SQL Fehler UserManagerDAO.getUser", Notification.TYPE_ERROR_MESSAGE);
            }

            if (rs == null) return null;
            List<KundeDTO> listeKunde = new ArrayList<>();
            try {
                while (rs.next()) {
                    KundeDTO user = new KundeDTO();
                    user.setEmail(rs.getString(1));
                    user.setLastName(rs.getString(4));
                    user.setKundenID(rs.getInt(7));


                    listeKunde.add(user);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listeKunde;


        }
return null;
    }


    //TODO


    // Abfrage ob gesetzte Email schon existiert
    public boolean userExist ( String email ){

        Statement statement = this.getStatement();

        ResultSet rs = null;

        try {
            rs = statement.executeQuery("Select *"
                    + " FROM cars.user"
                    + " WHERE cars.user.email = \'" + email + "\'");
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            Notification.show("falscher String", Notification.Type.ERROR_MESSAGE);
        }

        List<UserDTO> liste = new ArrayList<>();

        try {
            while ( rs.next() ){

                liste.add(new UserDTO());

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (liste.isEmpty()){ return false; }
        else {return true;}

    }



    public int getIDKunde ( String email ){
        Statement statement = this.getStatement();

        ResultSet rs = null;

        try {
            rs = statement.executeQuery("Select \"kundenID\""
                    + " FROM cars.kunde"
                    + " WHERE \"email\" = \'" + email + "\'");
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            Notification.show("SQL Fehler UserManagerDAO.getRolesForUser", Notification.TYPE_ERROR_MESSAGE);
        }

        int id = 0;

        try {
            while ( rs.next() ){

                id = (rs.getInt(1));
                return id;

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

}

