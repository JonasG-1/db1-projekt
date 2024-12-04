package Logic;

import DAO.Factory.OracleConnectionFactory;
import Logic.State.MainMenuState;
import View.ConsoleApp;
import View.ProjectView;

import javax.swing.*;
import java.sql.Connection;

public class BusinessLogic {
    public static void main(String[] args) {

        ConsoleApp consoleApp = new ConsoleApp();
        consoleApp.setConsoleAppState(new MainMenuState(consoleApp));
        consoleApp.startLoop();

    }
}
