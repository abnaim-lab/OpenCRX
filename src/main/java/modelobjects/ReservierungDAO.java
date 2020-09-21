package modelobjects;

import Exceptions.DatabaseException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import db.JDBCConnection;
import util.Roles;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReservierungDAO extends AbstractDAO {

    private static ReservierungDAO search;

    private ReservierungDAO() {
    }

    public static ReservierungDAO getInstance() {
        if (search == null) {
            search = new ReservierungDAO();
        }
        return search;
    }

    public List<ReservierungDTO> getKundeByReservierung(Integer id) throws Exception {
        UserDTO user = (UserDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        Statement statement = null;
        try {
            statement = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException ex) {
            return null;
        }
        ResultSet rs = null;

        try {
            rs = statement.executeQuery("Select * "
                    + " FROM cars.auto ");
                   // + " INNER JOIN cars.kunde ON kunde.\"kundenID\" = auto.\"marke\" ");
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            Notification.show("SQL FALSCH");
        }


        if (rs == null) return null;


        List<ReservierungDTO> liste = new ArrayList<>();
        ReservierungDTO res = null;

        try {
            while (rs.next()) {
                res = new ReservierungDTO();
                res.setId(rs.getInt(1));
                res.setDate(rs.getDate(2));
                res.setAutoID(rs.getInt(3));
                res.setId(rs.getInt(4));

                liste.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }


    public List<AutoDTO> getAngebotByReservierung(String name ) throws Exception {
        UserDTO user = (UserDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        Statement statement = null;

        try {

            statement = JDBCConnection.getInstance().getStatement();

        } catch (DatabaseException ex) {
            return null;
        }

        ResultSet rs = null;

        try {

            rs = statement.executeQuery("Select * "
                    + " FROM cars.auto ");
                   // + " INNER JOIN cars.reservierung ON reservierung.\"reservierungID\" = reservierung.\"reservierungID\"  ");
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            Notification.show("SQL FALSCH");
        }


        if ( rs == null ) return null;


        List<AutoDTO> liste = new ArrayList<AutoDTO>();
        AutoDTO Auto = null;

        try {
            while ( rs.next() ){
                Auto = new AutoDTO();
                //Auto.setAutoid(rs.getInt(7));
                Auto.setMarke(rs.getString(1));
                Auto.setModell(rs.getString(2));
                Auto.setBaujahr(rs.getString(3));
               // Auto.setAusstattung(rs.getString(4));

                liste.add(Auto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public boolean createAuto (String marke, String modell, int baujahr, String beschreibung, int autoId, int kundeId ) throws Exception {
        String sql = "INSERT INTO cars.auto VALUES (nextval('cars.\"reservierungsID\"'::regclass),?,?,?,?,?);";
        PreparedStatement statement = this.getPreparedStatement( sql);

        try {
            statement.setString(2, ( marke));
            statement.setString(1, ( modell));
            statement.setInt(3, ( baujahr));
            statement.setString(4, ( beschreibung));
            statement.setInt(5, ( autoId));
            statement.setInt(5, ( kundeId));

            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            Notification.show("SQL Fehler BewerbungDAO.createBewerbung", Notification.TYPE_ERROR_MESSAGE);
            return false;
        }
    }

}

