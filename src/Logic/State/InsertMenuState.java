package Logic.State;

import DAO.Model.Verpackung;
import Logic.RequestService;
import View.ConsoleApp;

/**
 * Autoren:
 *
 * @author Jonas Goldbach, Matrikelnummer: 7217641
 * @author Jan Schulze, Matrikelnummer: 7217725
 */
public class InsertMenuState extends ConsoleState {

    private final String DEFAULT_MENU = """
            ---------------------------------------------------------------------------------------------------------------------------------------------------------
            Einfügen - Bitte Befehl auswählen
            
            1    - Neues Tupel zur Tabelle Verpackung hinzufügen
            exit - Zurück zum Hauptmenü
            ---------------------------------------------------------------------------------------------------------------------------------------------------------
            """;

    private String currentMenuOutput;

    private UserInputState currentState;

    private Verpackung verpackung;

    private RequestService requestService;

    private enum UserInputState {
        DEFAULT,
        ENTER_ID,
        ENTER_NAME,
        ENTER_SUB_ID,
        ENTER_QUANTITY,
    }

    public InsertMenuState(ConsoleApp consoleApp, RequestService requestService) {
        super(consoleApp);
        resetToInitialState();
        this.requestService = requestService;
    }

    private void resetToInitialState() {
        changeState(UserInputState.DEFAULT);
        verpackung = new Verpackung();
    }

    private String changeStateToChooseId() {
        changeState(UserInputState.ENTER_ID);
        verpackung = new Verpackung();
        return "";
    }

    private String processEnterIdStateInput(String input) {
        if (input.equals("abort")) {
            resetToInitialState();
            return "Eingabe abgebrochen. Änderungen wurden nicht gespeichert.";
        }

        try {
            verpackung.setVerpackungId(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return "Die Eingabe enthielt keine Zahl: " + input;
        }

        changeState(UserInputState.ENTER_NAME);
        return "";
    }

    private String processEnterNameStateInput(String input) {
        if (input.equals("abort")) {
            resetToInitialState();
            return "Eingabe abgebrochen. Änderungen wurden nicht gespeichert.";
        }
        verpackung.setVerpackungName(input);
        changeState(UserInputState.ENTER_SUB_ID);
        return "";
    }

    private String processEnterSubIdStateInput(String input) {
        if (input.equals("abort")) {
            resetToInitialState();
            return "Eingabe abgebrochen. Änderungen wurden nicht gespeichert.";
        }

        int number = 0;

        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return "Die Eingabe enthielt keine Zahl: " + input;
        }

        if (number == 0) {
            String result = requestService.insertVerpackungTuple(verpackung);
            resetToInitialState();
            return result;
        }

        verpackung.setSubVerpackungId(number);

        changeState(UserInputState.ENTER_QUANTITY);
        return "";
    }

    private String processEnterQuantityStateInput(String input) {
        if (input.equals("abort")) {
            resetToInitialState();
            return "Eingabe abgebrochen. Änderungen wurden nicht gespeichert.";
        }

        try {
            verpackung.setAnzahlEinheiten(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return "Die Eingabe enthielt keine Zahl: " + input;
        }

        String result = requestService.insertVerpackungTuple(verpackung);
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
            case ENTER_NAME -> processEnterNameStateInput(input);
            case ENTER_SUB_ID -> processEnterSubIdStateInput(input);
            case ENTER_QUANTITY -> processEnterQuantityStateInput(input);
        };
    }

    @Override
    public String getMenuOptions() {
        return currentMenuOutput;
    }

    private void changeState(UserInputState userInputState) {
        currentState = userInputState;
        setMenuTextForCurrentState();
    }

    private void setMenuTextForCurrentState() {
        StringBuilder builder = new StringBuilder(switch (currentState) {
            case ENTER_ID -> "Tupel hinzufügen --- Bitte eine Id vergeben.";
            case ENTER_NAME -> "Tupel hinzufügen --- Bitte einen Namen eingeben.";
            case ENTER_SUB_ID -> "Tupel hinzufügen --- Bitte VerpackungsId der enthaltenden (größeren) Verpackung eingeben. " +
                    "'0' eingeben, wenn die Verpackung nicht weiter verpackt wird und um die Eingabe abzuschließen.";
            case ENTER_QUANTITY -> "Tupel hinzufügen --- Bitte eingeben, wie oft diese Verpackung in die Subverpackung passt und um die Eingabe abzuschließen.";
            case DEFAULT -> DEFAULT_MENU;
        });
        if (currentState != UserInputState.DEFAULT) {
            builder.append(" \"abort\" schreiben, um abzubrechen");
        }
        currentMenuOutput = builder.toString();
    }
}
