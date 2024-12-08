package Logic.State;

import DAO.BrauereiDatabaseAccess;
import Logic.RequestService;
import View.ConsoleApp;

import java.util.ArrayList;
import java.util.List;

public class SingletonStateCollection {

    private static List<ConsoleState> consoleStates;

    public static void createSingeltons(ConsoleApp consoleApp) {
        BrauereiDatabaseAccess brauereiDatabaseAccess = new BrauereiDatabaseAccess();
        RequestService requestService = new RequestService(brauereiDatabaseAccess);


        consoleStates = new ArrayList<>();
        consoleStates.add(new MainMenuState(consoleApp));
        consoleStates.add(new RequestMenuState(consoleApp, requestService));
        consoleStates.add(new InsertMenuState(consoleApp, requestService));
        consoleStates.add(new SearchMenuState(consoleApp));
    }

    /**
     *
     * @param consoleStateClass Die Klasse, von der das bereits erstellte Objekt geliefert werden soll.
     * @return Gibt die erstellte Instanz der übergebenen Klasse zurück, sofern in der Liste enthalten.
     * Ansonsten wird MainMenuState zurückgegeben.
     */
    public static ConsoleState getInstance(Class<? extends ConsoleState> consoleStateClass) {
        for (ConsoleState consoleState : consoleStates) {
            if (consoleStateClass.isInstance(consoleState)) {
                return consoleState;
            }
        }
        return consoleStates.getFirst();
    }
}
