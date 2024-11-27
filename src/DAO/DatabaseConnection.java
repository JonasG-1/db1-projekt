package DAO;

import java.sql.*;

public class DatabaseConnection {

    private final String connectionString = "jdbc:oracle:thin:@172.22.160.22:1521:xe";
    private static Connection con;
    protected static Statement befehl;


    public DatabaseConnection() {
        try {
            con = DriverManager.getConnection(connectionString, "C##FBPOOL87", "rWsJFsBJKN4dL662Y4UE");
            System.out.println("Die Datenbankverbindung war erfolgreich!");
            befehl = con.createStatement();
            befehl.executeQuery("SELECT * FROM Biersorte");
        } catch (SQLException e) {
            System.out.println("DIe Datenbankverbindung ist fehlgeschlagen!");
            e.printStackTrace();
        }
    }
}
