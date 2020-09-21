package modelobjects;

import Exceptions.DatabaseException;
import db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.Statement;


public class AbstractDAO {

    protected Statement getStatement () {
        Statement statement = null;

        try {

            statement = JDBCConnection.getInstance().getStatement();

        } catch (Exception ex) {
            return null;
        }

        return statement;
    }


    protected PreparedStatement getPreparedStatement ( String sql ) throws Exception {

        PreparedStatement statement = null;

        try {

            statement = JDBCConnection.getInstance().getPreparedStatement(  sql );

        } catch (DatabaseException ex) {
            return null;

        }

        return statement;
    }
}


