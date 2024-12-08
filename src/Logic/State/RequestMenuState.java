package Logic.State;

import DAO.BrauereiDatabaseAccess;
import Logic.RequestService;
import View.ConsoleApp;

/**
 * State Objekt, dass ... macht
 *
 *
 */
public class RequestMenuState extends ConsoleState {

    RequestService requestService;

    public RequestMenuState(ConsoleApp consoleApp, RequestService requestService) {
        super(consoleApp);
        this.requestService = requestService;
    }

    @Override
    public String processInput(String input) {
        return switch (input) {
            case "1" -> requestService.startRequest(1);
            case "2" -> requestService.startRequest(2);
            case "3" -> requestService.startRequest(3);
            case "4" -> requestService.startRequest(4);
            case "5" -> requestService.startRequest(5);
            case "exit" -> exitToMainMenu();
            default -> processWrongInput(input);
        };
    }

    @Override
    public String getMenuOptions() {
        return """
                ---------------------------------------------------------------------------------------------------------------------------------------------------------
                Abfrage auswählen
                
                1    - 1. Gib alle Zutaten der Biersorte "Pils" aus.
                2    - 2. Gib alle Standort IDs aus, von Standorten in denen Fässer Lagern
                3    - 3. [Rekursiv] Gib die Verpackungsnamen der Verpakungen aus, welche die Behälter der Biersorte "Pils" enthalten.
                4    - 4. Gib ir alle Temperaturen des Lagers mit der LAGER_ID = 1.
                5    - 5. Gib mir die Anuahl an Lagerabschnitten im Lager mit der LAGER_ID = 3, welche eine Luftfeuchtigkeit > 10% haben.
                exit - Zurück zum Hauptmenü
                ---------------------------------------------------------------------------------------------------------------------------------------------------------
                """;
    }
}
