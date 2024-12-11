package View;

import Logic.State.ConsoleState;

import java.util.Scanner;

/**
 * Autoren:
 *
 * Jonas Goldbach, Matrikelnummer: 7217641
 * Jan Schulze, Matrikelnummer: 7217725
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
        String output = state.processInput(input);
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
