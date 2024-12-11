package View;

import Logic.State.ConsoleState;
import Logic.State.MainMenuState;
import Logic.State.SingletonStateCollection;

import java.util.Scanner;

/**
 * Autoren:
 *
 * @author Jonas Goldbach, Matrikelnummer: 7217641
 * @author Jan Schulze, Matrikelnummer: 7217725
 */
public class ConsoleApp {

    private ConsoleState state;
    private boolean run = true;

    public void startLoop() {
        Scanner scanner = new Scanner(System.in);

        while (run) {
            outputMenuOptions();
            String input = scanner.next();
            processInput(input);
        }

        System.out.println("Auf Wiedersehen!");
    }

    private void processInput(String input) {
        String output;
        try {
            output = state.processInput(input);
        } catch (Exception e) {
            output = "Es ist ein Fehler aufgetreten. Fehlerdetails: \n" + e.getMessage() +
                    "\n\nDie App wurde auf das Hauptmenü zurückgesetzt, um inkonsistente Zustände zu vermeiden.\n";
            state.resetToInitialState();
            state = SingletonStateCollection.getInstance(MainMenuState.class);
        }

        System.out.println(output);
    }

    private void outputMenuOptions() {
        System.out.println(state.getMenuOptions());
    }

    public void setConsoleAppState(ConsoleState state) {
        this.state = state;
    }

    public void setRunState(boolean run) {
        this.run = run;
    }
}
