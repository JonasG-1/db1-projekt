package DAO.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionFactory extends ConnectionFactory {

    private final String connectionString = "jdbc:oracle:thin:@172.22.160.22:1521:xe";
    private final String user = "C##FBPOOL87";
    private final String pass = "rWsJFsBJKN4dL662Y4UE";


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
}
