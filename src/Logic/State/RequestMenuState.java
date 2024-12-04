package Logic.State;

import View.ConsoleApp;

public class RequestMenuState extends ConsoleState {

    public RequestMenuState(ConsoleApp consoleApp) {
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
