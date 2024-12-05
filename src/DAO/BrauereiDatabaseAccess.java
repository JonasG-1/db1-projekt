package DAO;

import DAO.Factory.OracleConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrauereiDatabaseAccess {
    static Connection connection;
    static OracleConnectionFactory connectionFactory;

    public static Connection initializeConnection() {
        connectionFactory = new OracleConnectionFactory();
        connection = connectionFactory.createConnection();
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if(connectionFactory.closeConnection(connection)){
            connection = null;
            connectionFactory = null;
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
