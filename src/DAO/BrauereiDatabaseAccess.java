package DAO;

import DAO.Factory.OracleConnectionFactory;

import java.sql.*;

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

    public static String abfrage1()  {

        Connection connection = initializeConnection();



        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery("SELECT * FROM Biersorte");
            printResultSet(rs);
        }catch (SQLException e) {
            System.out.println("Abfrage vehgeschlagen:" + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return "";
    }


    // VOn Chatgpt
    public static void printResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            System.out.println("Das ResultSet ist leer oder null.");
            return;
        }

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Spaltenüberschriften ausgeben
            StringBuilder header = new StringBuilder();
            StringBuilder separator = new StringBuilder();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                header.append(String.format("%-20s", columnName));
                separator.append("--------------------");
            }
            System.out.println(header);
            System.out.println(separator);

            // Datenzeilen ausgeben
            boolean hasRows = false;
            while (resultSet.next()) {
                hasRows = true;
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }

            if (!hasRows) {
                System.out.println("Keine Daten im ResultSet.");
            }

        } catch (SQLException e) {
            System.out.println("Fehler beim Verarbeiten des ResultSets: " + e.getMessage());
        }
    }

}
