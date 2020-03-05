package ua.kpi.calculator.view;

import org.springframework.stereotype.Component;

//@Component
public class View {
    public void printHelp() {
        System.out.println("Usage <command> <argument> ... <argument> ");
        System.out.println("Example add 3 5");
        System.out.println("Enabled commands: add sub exit");
    }

    public void printInputCorrectCommand() {
        System.out.println("Input is invalid. Input correct command.");
    }

    public void printResult(String result) {
        System.out.println(result);
    }
}
