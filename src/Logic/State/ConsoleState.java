package Logic.State;

import View.ConsoleApp;

/**
 * Autoren:
 *
 * @author Jonas Goldbach, Matrikelnummer: 7217641
 * @author Jan Schulze, Matrikelnummer: 7217725
 */
public abstract class ConsoleState {

    ConsoleApp consoleApp;

    public ConsoleState(ConsoleApp consoleApp) {
        this.consoleApp = consoleApp;
    }

    protected String exitToMainMenu() {
        consoleApp.setConsoleAppState(new MainMenuState(consoleApp));
        return "";
    }

    public abstract String processInput(String input);

    public abstract String getMenuOptions();

    protected String processWrongInput(String args) {
        return "Kein Befehl erkannt: " + args;
    }
}
