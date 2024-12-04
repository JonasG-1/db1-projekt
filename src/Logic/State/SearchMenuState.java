package Logic.State;

import View.ConsoleApp;

public class SearchMenuState extends ConsoleState{

    public SearchMenuState(ConsoleApp consoleApp) {
        super(consoleApp);
    }

    @Override
    public String processInput(String input) {
        return "";
    }

    @Override
    public String getMenuOptions() {
        return "";
    }
}
