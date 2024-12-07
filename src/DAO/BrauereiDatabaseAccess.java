package DAO;

import DAO.Factory.ConnectionFactory;
import DAO.Factory.OracleConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrauereiDatabaseAccess {
    private ConnectionFactory connectionFactory;

    public BrauereiDatabaseAccess() {
        this.connectionFactory = new OracleConnectionFactory();
    }


    public List<String[]> abfrage1()  {

        try (Connection connection = connectionFactory.createConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery
                    ("Select biersorte.biersorte_id, Biersorte_name, zutat.zutat_name From Biersorte Inner Join biersorte_zutat on Biersorte.Biersorte_ID = Biersorte_Zutat.Biersorte_ID INNER JOIN zutat on biersorte_zutat.zutat_id = zutat.zutat_id where biersorte.biersorte_name = 'Pils'");
            return createListFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Abfrage fehlgeschlagen:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    public List<String[]> abfrage2()  {

        try (Connection connection = connectionFactory.createConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery("SELECT standort.standort_id From Standort inner JOIN lager on lager.standort_id = standort.standort_id inner JOIN lagerabschnitt on lagerabschnitt.lager_id = lager.lager_id inner JOIN lagerbestand_lagerabschnitt on lagerbestand_lagerabschnitt.lagerabschnitt_id = lagerabschnitt.lagerabschnitt_id inner Join lagerbestand on lagerbestand_lagerabschnitt.lagerbestand_id = lagerbestand.lagerbestand_id inner JOIN behaelter on lagerbestand.behaelter_id = behaelter.behaelter_id where behaelter.behaeltertyp = 'Fass'");
            return createListFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Abfrage fehlgeschlagen:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    public List<String[]> abfrage3()  {

        try (Connection connection = connectionFactory.createConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery("SELECT * FROM Biersorte");
            return createListFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Abfrage fehlgeschlagen:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    public List<String[]> abfrage4()  {

        try (Connection connection = connectionFactory.createConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery("SELECT * FROM Biersorte");
            return createListFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Abfrage fehlgeschlagen:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    public List<String[]> abfrage5()  {

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

            while(resultSet.next()){
                String[] row = new String[columns];
                for(int i = 1; i <= columns; i++){
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
