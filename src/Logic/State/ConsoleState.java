package Logic.State;

import View.ConsoleApp;

public abstract class ConsoleState {

    ConsoleApp consoleApp;

    public ConsoleState(ConsoleApp consoleApp) {
        this.consoleApp = consoleApp;
    }

    public abstract String processInput(String input);

    public abstract String getMenuOptions();
}
