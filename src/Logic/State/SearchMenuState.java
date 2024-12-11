package Logic.State;

import View.ConsoleApp;

/**
 * Autoren:
 *
 * Jonas Goldbach, Matrikelnummer: 7217641
 * Jan Schulze, Matrikelnummer: 7217725
 */
public class SearchMenuState extends ConsoleState{

    public SearchMenuState(ConsoleApp consoleApp) {
        super(consoleApp);
    }



    @Override
    public String processInput(String input) {
        return switch (input) {
            case "1" -> "";
            case "exit" -> exitToMainMenu();
            default -> processWrongInput(input);
        };
    }

    @Override
    public String getMenuOptions() {
        return "";
    }
}
