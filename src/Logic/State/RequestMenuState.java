package Logic.State;

import DAO.BrauereiDatabaseAccess;
import View.ConsoleApp;

public class RequestMenuState extends ConsoleState {

    public RequestMenuState(ConsoleApp consoleApp) {
        super(consoleApp);
    }

    private String startRequest(String input) {
        return "";
    }

    private String exitToMainMenu() {
        consoleApp.setConsoleAppState(new MainMenuState(consoleApp));
        return "";
    }

    @Override
    public String processInput(String input) {
        return switch (input) {
            case "1" -> BrauereiDatabaseAccess.abfrage1();
            case "2" -> processWrongInput(input);
            case "3" -> processWrongInput(input);
            case "4" -> processWrongInput(input);
            case "5" -> processWrongInput(input);
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
                5    - 5. Gib mir die Anuahl an Lagerabschnitten im Lager mit der LAGER_ID = 10, welche eine Luftfeuchtigkeit > 10% haben.
                exit - Zurück zum Hauptmenü
                ---------------------------------------------------------------------------------------------------------------------------------------------------------
                """;
    }
}
