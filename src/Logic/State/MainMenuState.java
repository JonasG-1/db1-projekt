package Logic.State;

import View.ConsoleApp;


/**
 * Autoren:
 *
 * @author Jonas Goldbach, Matrikelnummer: 7217641
 * @author Jan Schulze, Matrikelnummer: 7217725
 */
public class MainMenuState extends ConsoleState {


    public MainMenuState(ConsoleApp consoleApp) {
        super(consoleApp);
    }

    private String chooseRequestCommand() {
        consoleApp.setConsoleAppState(SingletonStateCollection.getInstance(RequestMenuState.class));
        return "";
    }

    private String chooseInsertTupel() {
        consoleApp.setConsoleAppState(SingletonStateCollection.getInstance(InsertMenuState.class));
        return "";
    }

    private String chooseDepthSearch() {
        consoleApp.setConsoleAppState(SingletonStateCollection.getInstance(SearchMenuState.class));
        return "";
    }

    private String exitConsoleApp() {
        consoleApp.setRunState(false);
        return "";
    }

    @Override
    public String processInput(String input) {
        return switch (input) {
            case "1" -> chooseRequestCommand();
            case "2" -> chooseInsertTupel();
            case "3" -> chooseDepthSearch();
            case "exit" -> exitConsoleApp();
            default -> processWrongInput(input);
        };
    }

    @Override
    public String getMenuOptions() {

        return """
                ---------------------------------------------------------------------------------------------------------------------------------------------------------
                Hauptmenü - Bitte Befehl auswählen
                
                1    - Abfragen auswählen
                2    - Tupel zur rekursiven Beziehung hinzufügen
                3    - Tiefensuche mit Tupel ausführen
                exit - Beenden
                ---------------------------------------------------------------------------------------------------------------------------------------------------------
                """;
    }
}
