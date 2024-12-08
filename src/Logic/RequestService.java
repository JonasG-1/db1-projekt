package Logic;

import DAO.BrauereiDatabaseAccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        StringBuilder builder = new StringBuilder();

        for (String[] strings : output) {
            builder.append(Arrays.toString(strings)).append("\n");
        }

        return builder.toString();
    }
}
