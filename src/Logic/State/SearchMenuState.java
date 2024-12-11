package Logic.State;

import Logic.RequestService;
import View.ConsoleApp;

/**
 * Autoren:
 *
 * @author Jonas Goldbach, Matrikelnummer: 7217641
 * @author Jan Schulze, Matrikelnummer: 7217725
 */
public class SearchMenuState extends ConsoleState {

    private String currentMenuOutput;

    private UserInputState currentState;

    private final RequestService requestService;

    private enum UserInputState {
        DEFAULT,
        ENTER_ID,
    }

    public SearchMenuState(ConsoleApp consoleApp, RequestService requestService) {
        super(consoleApp);
        resetToInitialState();
        this.requestService = requestService;
    }

    private void resetToInitialState() {
        changeStates(UserInputState.DEFAULT);
    }



    private String changeStateToChooseId() {
        changeStates(UserInputState.ENTER_ID);
        return "";
    }

    private String processEnterIdStateInput(String input) {
        if (input.equals("abort")) {
            resetToInitialState();
            return "Eingabe abgebrochen. Änderungen wurden nicht gespeichert.";
        }

        int startingTupleId;

        try {
            startingTupleId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return "Die Eingabe enthielt keine Zahl: " + input;
        }

        String result = requestService.executeDFSQuery(startingTupleId);

        resetToInitialState();
        return result;
    }


    private String processDefaultInput(String input) {
        return switch (input) {
            case "1" -> changeStateToChooseId();
            case "exit" -> exitToMainMenu();
            default -> processWrongInput(input);
        };
    }



    @Override
    public String processInput(String input) {
        return switch (currentState) {
            case DEFAULT -> processDefaultInput(input);
            case ENTER_ID -> processEnterIdStateInput(input);
        };
    }

    @Override
    public String getMenuOptions() {
        return currentMenuOutput;
    }

    private void changeStates(UserInputState userInputState) {
        currentState = userInputState;
        setMenuTextForCurrentState();
    }

    private void setMenuTextForCurrentState() {
        String DEFAULT_MENU = """
                    ---------------------------------------------------------------------------------------------------------------------------------------------------------
                    Tiefensuche - Bitte Befehl auswählen

                    1    - Gesuchtes Tupel anhand ID auswählen und Suchen.
                    exit - Zurück zum Hauptmenü
                    ---------------------------------------------------------------------------------------------------------------------------------------------------------
                    """;

        StringBuilder builder = new StringBuilder(switch (currentState) {
            case ENTER_ID -> "Tupel hinzufügen --- Bitte die Id eingeben, von der die Suche ausgehen soll.";
            case DEFAULT -> DEFAULT_MENU;
        });
        if (currentState != UserInputState.DEFAULT) {
            builder.append(" \"abort\" schreiben, um abzubrechen");
        }
        currentMenuOutput = builder.toString();
    }
}
