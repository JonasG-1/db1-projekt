package Logic.State;

import View.ConsoleApp;

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
