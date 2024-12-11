package Logic;

import DAO.BrauereiDatabaseAccess;
import DAO.Model.Verpackung;

import java.util.ArrayList;
import java.util.List;

/**
 * Autoren:
 *
 * @author Jonas Goldbach, Matrikelnummer: 7217641
 * @author Jan Schulze, Matrikelnummer: 7217725
 */
public class RequestService {

    BrauereiDatabaseAccess brauereiDatabaseAccess;

    public RequestService(BrauereiDatabaseAccess brauereiDatabaseAccess) {
        this.brauereiDatabaseAccess = brauereiDatabaseAccess;
    }


    /**
     * Startet den Request ...
     *
     * @param abfrage Gibt die Nummer der Anfrage an, die ausgeführt werden soll
     */
    public String startRequest(int abfrage) {
        List<String[]> output = switch (abfrage) {
            case 1 -> brauereiDatabaseAccess.abfrage1();
            case 2 -> brauereiDatabaseAccess.abfrage2();
            case 3 -> brauereiDatabaseAccess.abfrage3();
            case 4 -> brauereiDatabaseAccess.abfrage4();
            case 5 -> brauereiDatabaseAccess.abfrage5();
            default -> new ArrayList<>();
        };

        return getFormattedOutputFromList(output);
    }

    private static String getFormattedOutputFromList(List<String[]> output) {
        if (output.isEmpty()) {
            return "";
        }
        if (output.size() == 1 && output.getFirst().length == 1) {
            return output.getFirst()[0];
        }

        int[] columnWidths = new int[output.getFirst().length];
        for (String[] row : output) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] != null) {
                    columnWidths[i] = Math.max(columnWidths[i], row[i].length());
                } else {
                    columnWidths[i] = Math.max(columnWidths[i], 4);
                }
            }
        }

        StringBuilder builder = new StringBuilder();

        StringBuilder formatBuilder = new StringBuilder();
        for (int width : columnWidths) {
            formatBuilder.append("%-").append(width).append("s | ");
        }
        String format = formatBuilder.toString();

        // Append the header row
        builder.append(String.format(format, (Object[]) output.getFirst())).append("\n");

        // Append a separator line
        for (int width : columnWidths) {
            builder.append("-".repeat(width)).append("-+-");
        }
        builder.append("\n");

        // Append the data rows
        for (int i = 1; i < output.size(); i++) {
            builder.append(String.format(format, (Object[]) output.get(i))).append("\n");
        }

        return builder.toString();
    }


    public String insertVerpackungTuple(Verpackung verpackung) {
        return brauereiDatabaseAccess.insertVerpackungTuple(verpackung);
    }

    public String executeDFSQuery(int startingTupleId) {
        List<String[]> output = brauereiDatabaseAccess.executeDFSQuery(startingTupleId);
        return getFormattedOutputFromList(output);
    }
}
