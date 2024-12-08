package Logic.State;

import DAO.Model.Verpackung;
import Logic.RequestService;
import View.ConsoleApp;

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

    private String changeStateToChooseName() {
        changeState(UserInputState.ENTER_NAME);
        verpackung = new Verpackung();
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

        try {
            verpackung.setSubVerpackungId(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return "Die Eingabe enthielt keine Zahl: " + input;
        }

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
            case "1" -> changeStateToChooseName();
            case "exit" -> exitToMainMenu();
            default -> processWrongInput(input);
        };
    }

    @Override
    public String processInput(String input) {
        return switch (currentState) {
            case DEFAULT -> processDefaultInput(input);
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
            case ENTER_NAME -> "Tupel hinzufügen --- Bitte Namen eingeben.";
            case ENTER_SUB_ID -> "Tupel hinzufügen --- Bitte VerpackungsId der enthaltenen Verpackung eingeben. " +
                    "'0' eingeben, wenn Verpackung Behälter enthält. (SUB_VERPACKUNG_ID)";
            case ENTER_QUANTITY -> "Tupel hinzufügen --- Bitte Anzahl der Einheiten angeben, die enthalten sein können.";
            case DEFAULT -> DEFAULT_MENU;
        });
        if (currentState != UserInputState.DEFAULT) {
            builder.append(" \"abort\" schreiben, um abzubrechen");
        }
        currentMenuOutput = builder.toString();
    }
}
