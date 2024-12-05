package DAO.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionFactory extends ConnectionFactory {

    /*private final String connectionString = "jdbc:oracle:thin:@172.22.160.22:1521:xe";
    private final String user = "C##FBPOOL87";
    private final String pass = "rWsJFsBJKN4dL662Y4UE";*/

    //Lokale Datenbank
    private final String connectionString = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String user = "system";
    private final String pass = "oracle";


    @Override
    protected Connection createGetConnectionObject() {
        Connection con;
        try {
            con = DriverManager.getConnection(connectionString, user, pass);
            System.out.println("[ConnectionFactory] Connection established");
        } catch (SQLException e) {
            System.out.println("[ConnectionFactory] Failed to establish connection: \n" + e.getMessage());
            throw new RuntimeException();
        }
        return con;
    }

    protected boolean closeConnectionObject(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("[ConnectionFactory] Connection closed successfully.");
                return true;
            } catch (SQLException e) {
                System.err.println("[ConnectionFactory] Failed to close the connection: " + e.getMessage());
                return false;
            }
        }
        return false;
    }
}
