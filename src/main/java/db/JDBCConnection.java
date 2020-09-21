package db;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {

    // with vpn to access db
    private static String url = "jdbc:postgresql://dumbo.inf.h-brs.de/amoham2s";
    private static String username = "amoham2s";
    private static String password = "amoham2s";
    private static JDBCConnection connection = null;
    private static Connection conn;


    public static JDBCConnection getInstance() throws Exception {
        if (connection == null) {
            connection = new JDBCConnection();
        }
        return connection;
    }

    private JDBCConnection() throws  Exception {
        this.initconnection();
    }

    public void initconnection() throws Exception {

        try {
            DriverManager.registerDriver((new org.postgresql.Driver()));
        } catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        this.openConnection();
    }

    public void openConnection() throws Exception {

        try {
            Properties properties = new Properties();
            properties.setProperty("user", this.username);
            properties.setProperty("password", this.password);

            this.conn = DriverManager.getConnection(this.url, properties);

        } catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("VPN Verbindung vorhanden");
        }
    }

    public Statement getStatement() throws Exception {
        try {
            if (this.conn.isClosed()) {
                this.openConnection();
            }
            return this.conn.createStatement();
        }catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }


    public void closeConnection(){
        try {
            this.conn.close();
        } catch (SQLException e){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public PreparedStatement getPreparedStatement(String sql) throws Exception {
        try {
            if (this.conn.isClosed()){
                this.openConnection();
            }
            return this.conn.prepareStatement(sql);
        } catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }
}

