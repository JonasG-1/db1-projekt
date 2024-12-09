package DAO;

import DAO.Factory.ConnectionFactory;
import DAO.Factory.OracleConnectionFactory;
import DAO.Model.Verpackung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrauereiDatabaseAccess {
    private final ConnectionFactory connectionFactory;

    public BrauereiDatabaseAccess() {
        this.connectionFactory = new OracleConnectionFactory();
    }


    public List<String[]> abfrage1() {
        String sql = "SELECT BIERSORTE.BIERSORTE_ID, BIERSORTE_NAME, ZUTAT.ZUTAT_NAME " +
                "FROM BIERSORTE " +
                "INNER JOIN BIERSORTE_ZUTAT ON BIERSORTE.BIERSORTE_ID = BIERSORTE_ZUTAT.BIERSORTE_ID " +
                "INNER JOIN ZUTAT ON BIERSORTE_ZUTAT.ZUTAT_ID = ZUTAT.ZUTAT_ID " +
                "WHERE BIERSORTE.BIERSORTE_NAME = 'Pils'";

        return executeSqlAndGetResultList(sql);
    }

    public List<String[]> abfrage2() {
        String sql = "SELECT STANDORT.STANDORT_ID " +
                "FROM STANDORT " +
                "INNER JOIN LAGER ON LAGER.STANDORT_ID = STANDORT.STANDORT_ID " +
                "INNER JOIN LAGERABSCHNITT ON LAGERABSCHNITT.LAGER_ID = LAGER.LAGER_ID " +
                "INNER JOIN LAGERBESTAND_LAGERABSCHNITT ON LAGERBESTAND_LAGERABSCHNITT.LAGERABSCHNITT_ID = LAGERABSCHNITT.LAGERABSCHNITT_ID " +
                "INNER JOIN LAGERBESTAND on LAGERBESTAND_LAGERABSCHNITT.LAGERBESTAND_ID = LAGERBESTAND.LAGERBESTAND_ID " +
                "INNER JOIN BEHAELTER on LAGERBESTAND.BEHAELTER_ID = BEHAELTER.BEHAELTER_ID " +
                "WHERE BEHAELTER.BEHAELTERTYP = 'Fass'";

        return executeSqlAndGetResultList(sql);
    }



    public List<String[]> abfrage3() {

        String sql = "WITH Verpackung_Hierarchie (VERPACKUNG_ID, VERPACKUNG_NAME, SUB_VERPACKUNG_ID) AS (" +
                "SELECT V.VERPACKUNG_ID, V.VERPACKUNG_NAME, V.SUB_VERPACKUNG_ID " +
                "FROM BIERSORTE BS " +
                "INNER JOIN BEHAELTER B ON BS.BIERSORTE_ID = B.BIERSORTE_ID " +
                "INNER JOIN LAGERBESTAND L ON B.BEHAELTER_ID = L.BEHAELTER_ID " +
                "INNER JOIN VERPACKUNG V ON L.VERPACKUNG_ID = V.VERPACKUNG_ID " +
                "WHERE BS.BIERSORTE_NAME = 'Pils' " +
                "UNION ALL " +
                "SELECT V.VERPACKUNG_ID, V.VERPACKUNG_NAME, V.SUB_VERPACKUNG_ID " +
                "FROM VERPACKUNG V " +
                "INNER JOIN Verpackung_Hierarchie VH ON V.VERPACKUNG_ID = VH.SUB_VERPACKUNG_ID) " +
                "SELECT DISTINCT VERPACKUNG_ID, VERPACKUNG_NAME " +
                "FROM Verpackung_Hierarchie";

        return executeSqlAndGetResultList(sql);
    }

    public List<String[]> abfrage4() {

        String sql = "Select lagerabschnitt.lagerabschnitt_id, lagerbedingung.temperatur " +
                "From Lager " +
                "inner Join Lagerabschnitt " +
                "on lager.lager_id = lagerabschnitt.lager_id " +
                "inner JOIN Lagerbedingung " +
                "on lagerabschnitt.lagerbedingung_id = lagerbedingung.lagerbedingung_id " +
                "where lager.lager_id = '1' ";


        return executeSqlAndGetResultList(sql);
    }

    public List<String[]> abfrage5() {

        String sql = "Select Count(Lagerabschnitt.Lagerabschnitt_ID) " +
                "From Lager " +
                "inner Join lagerabschnitt " +
                "on lagerabschnitt.lager_id = lager.lager_id " +
                "inner Join lagerbedingung " +
                "on lagerbedingung.lagerbedingung_id = lagerabschnitt.lagerbedingung_id " +
                "where lagerbedingung.luftfeuchtigkeit <= 0.1 " +
                "and lager.lager_id = 3";


        return executeSqlAndGetResultList(sql);
    }



    private List<String[]> executeSqlAndGetResultList(String sql) {
        try (Connection connection = connectionFactory.createConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = statement.executeQuery(sql)) {
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

    /**
     *
     * @param verpackung
     * @return Id der eingefügten Verpackung oder -1, wenn nichts eingefügt wurde oder ein Fehler aufgetreten ist.
     */
    public long insertVerpackungTuple(Verpackung verpackung) {
        String sql = "INSERT INTO Verpackung (VERPACKUNG_ID, VERPACKUNG_NAME, SUB_VERPACKUNG_ID, ANZAHL_EINHEITEN) VALUES (?, ?, ?, ?)";
        long result = -1;

        try (Connection connection = connectionFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, verpackung.getVerpackungId());
            preparedStatement.setString(2, verpackung.getVerpackungName());
            if (verpackung.getSubVerpackungId() == 0) {
                preparedStatement.setNull(3, Types.INTEGER);
                preparedStatement.setNull(4, Types.INTEGER);
            } else {
                preparedStatement.setInt(3, verpackung.getSubVerpackungId());
                preparedStatement.setInt(4, verpackung.getAnzahlEinheiten());
            }


            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e);
        }
        return result;
    }

}
