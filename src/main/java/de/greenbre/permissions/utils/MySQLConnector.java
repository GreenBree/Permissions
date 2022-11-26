package de.greenbre.permissions.utils;

import de.greenbre.permissions.Permissions;
import de.greenbre.permissions.manager.Config_Manager;

import java.sql.*;

public class MySQLConnector {

    private static Connection con;

    public MySQLConnector() {
        if(Config_Manager.MYSQL_HOSTNAME != "") connect();
    }

    public static boolean isConnected() {
        return (con == null ? false : true);
    }

    public static void connect() {
        if(!isConnected()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://"+Config_Manager.MYSQL_HOSTNAME+":"+String.valueOf(Config_Manager.MYSQL_PORT)+"/"+Config_Manager.MYSQL_DATABASE, Config_Manager.MYSQL_USERNAME, Config_Manager.MYSQL_PASSWORD);
                Permissions.getInstance().getLogger().info("MySQL connection established!");
            } catch(SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void disconnect() {
        if(isConnected()) {
            try {
                con.close();
                Permissions.getInstance().getLogger().info("MySQL disconnected!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void update(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            ps.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getResult(String sql) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

}
