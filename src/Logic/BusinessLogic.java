package Logic;

import Logic.State.MainMenuState;
import Logic.State.SingletonStateCollection;
import View.ConsoleApp;

/**
 * Autoren:
 *
 * @author Jonas Goldbach, Matrikelnummer: 7217641
 * @author Jan Schulze, Matrikelnummer: 7217725
 */
public class BusinessLogic {
    public static void main(String[] args) {

        ConsoleApp consoleApp = new ConsoleApp();
        SingletonStateCollection.createSingletons(consoleApp);
        consoleApp.setConsoleAppState(SingletonStateCollection.getInstance(MainMenuState.class));
        consoleApp.startLoop();

    }
}
