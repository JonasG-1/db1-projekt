package DAO;

import DAO.Factory.OracleConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrauereiDatabaseAccess {

    public static Connection initializeConnection() {
        OracleConnectionFactory connectionFactory = new OracleConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                System.err.println("Failed to close the connection: " + e.getMessage());
            }
        }
    }

    public static String abfrage1() {

        Connection connection = initializeConnection() ;

        closeConnection(connection);

        //ResultSet resultSet;
        //return new ArrayList<>();
        return "";
    }
}
