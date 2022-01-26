package ua.kpi.calculator.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import ua.kpi.calculator.model.CalculatorService;

@Component
public class CommandFactory {
    private static CommandFactory instance = new CommandFactory();
    public static CommandFactory getInstance(){
        return instance;
    }

    private Map<String,Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put("add", new AddCommand( CalculatorService.getInstance() ) );
        commands.put("sub", new SubCommand());
        commands.put("div", new DivCommand());

    }

    void registerCommand(String name, Command command){
      commands.put(name, command);
    }

    public Command getCommand(String command){
        return commands.getOrDefault(command,
                new Command() {
                    @Override
                    public String execute(String command) {
                        return "Invalid command";
                    }
                });
                //(args) -> "Invalid command" );
    }
}
