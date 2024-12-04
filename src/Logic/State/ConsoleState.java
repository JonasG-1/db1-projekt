package Logic.State;

import View.ConsoleApp;

public abstract class ConsoleState {

    ConsoleApp consoleApp;
    SingletonStateCollection singletonStateCollection;

    public ConsoleState(ConsoleApp consoleApp) {
        this.consoleApp = consoleApp;
    }

    public abstract String processInput(String input);

    public abstract String getMenuOptions();

    protected String processWrongInput(String args) {
        return "Kein Befehl erkannt: " + args;
    }
}
