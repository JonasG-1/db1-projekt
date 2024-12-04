package Logic.State;

import View.ConsoleApp;

public class InsertMenuState extends ConsoleState {

    public InsertMenuState(ConsoleApp consoleApp) {
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
