package View;

import Logic.State.ConsoleState;

import java.util.Scanner;

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
