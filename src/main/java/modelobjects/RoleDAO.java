package modelobjects;

import Exceptions.DatabaseException;
import db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class RoleDAO {

    private static RoleDAO dao = null;

    private RoleDAO (){

    }

    public static RoleDAO getInstance (){
        if ( dao == null){
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<RoleDTO> getRolesForUserEmail (String email ){

        Statement statement = null;

        try {

            statement = JDBCConnection.getInstance().getStatement();

        } catch (Exception ex) {
            return null;
        }

        ResultSet rs = null;

        try {
            rs = statement.executeQuery("Select *"
                    + " FROM test.benutzer_hat_rolle"
                    + " WHERE test.benutzer_hat_rolle.email = \'" + email + "\'");
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ( rs == null ) return null;

        List<RoleDTO> liste = new ArrayList<>();
        RoleDTO role = null;

        try {
            while ( rs.next() ){
                //role = new Role();
                role.setBezeichnung(rs.getString(2));
                liste.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }


}

