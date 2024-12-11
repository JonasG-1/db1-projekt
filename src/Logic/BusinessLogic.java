package Logic;

import DAO.Factory.OracleConnectionFactory;
import Logic.State.MainMenuState;
import Logic.State.SingletonStateCollection;
import View.ConsoleApp;
import View.ProjectView;

import javax.swing.*;
import java.sql.Connection;

/**
 * Autoren:
 *
 * Jonas Goldbach, Matrikelnummer: 7217641
 * Jan Schulze, Matrikelnummer: 7217725
 */
public class BusinessLogic {
    public static void main(String[] args) {

        ConsoleApp consoleApp = new ConsoleApp();
        SingletonStateCollection.createSingeltons(consoleApp);
        consoleApp.setConsoleAppState(SingletonStateCollection.getInstance(MainMenuState.class));
        consoleApp.startLoop();

    }
}
