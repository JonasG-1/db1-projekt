package Logic.State;

import View.ConsoleApp;

import java.util.ArrayList;
import java.util.List;

public class SingletonStateCollection {

    private static List<ConsoleState> consoleStates;

    public static void createSingeltons(ConsoleApp consoleApp) {
        consoleStates = new ArrayList<>();
        consoleStates.add(new MainMenuState(consoleApp));
        consoleStates.add(new RequestMenuState(consoleApp));
        consoleStates.add(new InsertMenuState(consoleApp));
    }

    public static ConsoleState getInstance(Class<? extends ConsoleState> consoleStateClass) {
        for (ConsoleState consoleState : consoleStates) {
            if (consoleStateClass.isInstance(consoleState)) {
                return consoleState;
            }
        }
        return null;
    }
}
