package Logic.State;

import View.ConsoleApp;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class MainMenuState extends ConsoleState {


    public MainMenuState(ConsoleApp consoleApp) {
        super(consoleApp);
    }

    private String chooseRequestCommand() {
        consoleApp.setConsoleAppState(new RequestMenuState(consoleApp));
        return "";
    }

    private String processWrongInput(String args) {
        return "Kein Befehl erkannt: " + args;
    }

    @Override
    public String processInput(String input) {
        return switch (input) {
            case "1" -> chooseRequestCommand();
            default -> processWrongInput(input);
        };
    }

    @Override
    public String getMenuOptions() {
        String output = """
                ---------------------------------------------------
                Hauptmenu - Bitte Befehl auswählen
                
                
                1    - Abfragen Auswählen
                2    - Tupel zur rekursiven Beziehung hinzufügen
                3    - Tiefensuche mit Tupel ausführen
                exit - Beenden
                ---------------------------------------------------
                """;

        return output;
    }
}
