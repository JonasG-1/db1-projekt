package DAO;

import DAO.Factory.ConnectionFactory;
import DAO.Factory.OracleConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrauereiDatabaseAccess {
    private final ConnectionFactory connectionFactory;

    public BrauereiDatabaseAccess() {
        this.connectionFactory = new OracleConnectionFactory();
    }


    public List<String[]> abfrage1() {
        try (Connection connection = connectionFactory.createConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery
                    ("SELECT BIERSORTE.BIERSORTE_ID, BIERSORTE_NAME, ZUTAT.ZUTAT_NAME " +
                            "FROM BIERSORTE " +
                            "INNER JOIN BIERSORTE_ZUTAT ON BIERSORTE.BIERSORTE_ID = BIERSORTE_ZUTAT.BIERSORTE_ID " +
                            "INNER JOIN ZUTAT ON BIERSORTE_ZUTAT.ZUTAT_ID = ZUTAT.ZUTAT_ID " +
                            "WHERE BIERSORTE.BIERSORTE_NAME = 'Pils'");
            return createListFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Abfrage fehlgeschlagen:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    public List<String[]> abfrage2() {

        try (Connection connection = connectionFactory.createConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery
                    ("SELECT STANDORT.STANDORT_ID " +
                            "FROM STANDORT " +
                            "INNER JOIN LAGER ON LAGER.STANDORT_ID = STANDORT.STANDORT_ID " +
                            "INNER JOIN LAGERABSCHNITT ON LAGERABSCHNITT.LAGER_ID = LAGER.LAGER_ID " +
                            "INNER JOIN LAGERBESTAND_LAGERABSCHNITT ON LAGERBESTAND_LAGERABSCHNITT.LAGERABSCHNITT_ID = LAGERABSCHNITT.LAGERABSCHNITT_ID " +
                            "INNER JOIN LAGERBESTAND on LAGERBESTAND_LAGERABSCHNITT.LAGERBESTAND_ID = LAGERBESTAND.LAGERBESTAND_ID " +
                            "INNER JOIN BEHAELTER on LAGERBESTAND.BEHAELTER_ID = BEHAELTER.BEHAELTER_ID " +
                            "WHERE BEHAELTER.BEHAELTERTYP = 'Fass'");
            return createListFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Abfrage fehlgeschlagen:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    public List<String[]> abfrage3() {

        try (Connection connection = connectionFactory.createConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery("SELECT * FROM Biersorte");
            return createListFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Abfrage fehlgeschlagen:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    public List<String[]> abfrage4() {

        try (Connection connection = connectionFactory.createConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery("SELECT * FROM Biersorte");
            return createListFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Abfrage fehlgeschlagen:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    public List<String[]> abfrage5() {

        try (Connection connection = connectionFactory.createConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery("SELECT * FROM Biersorte");
            return createListFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Abfrage fehlgeschlagen:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    private List<String[]> createListFromResultSet(ResultSet resultSet) {
        List<String[]> result = new ArrayList<>();

        if (resultSet == null) {
            System.out.println("Das ResultSet ist leer oder null.");
            return result;
        }

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();

            String[] columnNames = getColumnNames(metaData);
            result.add(columnNames);

            while (resultSet.next()) {
                String[] row = new String[columns];
                for (int i = 1; i <= columns; i++) {
                    row[i - 1] = resultSet.getString(i);
                }
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println("[BrauereiDatabaseAccess] Das ResultSet konnte nicht (vollständig) gelesen werden.\n" + e.getMessage());
        }

        return result;
    }

    private String[] getColumnNames(ResultSetMetaData metaData) throws SQLException {
        int columns = metaData.getColumnCount();

        String[] columnNames = new String[columns];
        for (int i = 1; i <= columns; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }
        return columnNames;
    }

}
