package Logic.State;

import View.ConsoleApp;

public class InsertMenuState extends ConsoleState {

    private String currentMenuOutput;

    private UserInputState currentState;

    private enum UserInputState {
        DEFAULT,
        ENTER_NAME,
        ENTER_SUB_ID,
        ENTER_QUANTITY
    }

    public InsertMenuState(ConsoleApp consoleApp) {
        super(consoleApp);
        currentState = UserInputState.DEFAULT;
        currentMenuOutput = """
            ---------------------------------------------------------------------------------------------------------------------------------------------------------
                Einfügen - Bitte Befehl auswählen
            
                1    - Neues Tupel zur Tabelle Verpackung hinzufügen
                exit - Zurück zum Hauptmenü
            ---------------------------------------------------------------------------------------------------------------------------------------------------------
            """;
    }

    private String changeStateToChooseName() {
        currentMenuOutput = """
            Tupel hinzufügen: Bitte einen Namen eingeben. "abort" eingeben, um abzubrechen.
            """;
        currentState = UserInputState.ENTER_NAME;
        return "";
    }

    private String processDefaultInput(String input) {
        return switch (input) {
            case "1" -> changeStateToChooseName();
            case "exit" -> exitToMainMenu();
            default -> processWrongInput(input);
        };
    }

    @Override
    public String processInput(String input) {
        return switch (currentState) {
            case DEFAULT -> processDefaultInput(input);
            case ENTER_NAME -> null;
            case ENTER_SUB_ID -> null;
            case ENTER_QUANTITY -> null;
        };
    }

    @Override
    public String getMenuOptions() {
        return currentMenuOutput;
    }
}
