package DAO.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Autoren:
 *
 * @author Jonas Goldbach, Matrikelnummer: 7217641
 * @author Jan Schulze, Matrikelnummer: 7217725
 */
public class OracleConnectionFactory extends ConnectionFactory {

    /*private final String connectionString = "jdbc:oracle:thin:@172.22.160.22:1521:xe";
    private final String user = "C##FBPOOL87";
    private final String pass = "rWsJFsBJKN4dL662Y4UE";*/

    //Lokale Datenbank Jan

    private final String connectionString = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String user = "system";
    private final String pass = "oracle";


    //Lokale Datenbank Jonas
    /*
    private final String connectionString = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String user = "BRAUEREI";
    private final String pass = "oracle";
    */

    @Override
    protected Connection createGetConnectionObject() {
        Connection con;
        try {
            con = DriverManager.getConnection(connectionString, user, pass);
            System.out.println("\n[ConnectionFactory] Connection established\n");
        } catch (SQLException e) {
            System.out.println("\n[ConnectionFactory] Failed to establish connection: \n" + e.getMessage() + "\n");
            throw new RuntimeException();
        }
        return con;
    }
}
