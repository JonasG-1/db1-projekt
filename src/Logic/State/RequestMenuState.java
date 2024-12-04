package Logic.State;

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
            case "1" -> processWrongInput(input);
            case "exit" -> exitToMainMenu();
            default -> processWrongInput(input);
        };
    }

    @Override
    public String getMenuOptions() {
        return """
                ---------------------------------------------------
                Abfrage auswählen
                
                
                1    - 1. Gib alle Zutaten der Biersorte "Pils" aus.
                2    - 2. Gib alle Standort IDs aus, von Standorten in denen Fässer Lagern
                3    - 3. [Rekursiv] Gib die Verpackungsnamen aus
                4    - 4. Abfrage starten
                5    - 5. Abfrage starten
                exit - Zurück zum Hauptmenü
                ---------------------------------------------------
                """;
    }
}
