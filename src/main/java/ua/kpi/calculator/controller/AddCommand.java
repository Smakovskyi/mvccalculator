package ua.kpi.calculator.controller;

import javax.annotation.PostConstruct;
import ua.kpi.calculator.controller.parser.ParserFactory;
import ua.kpi.calculator.controller.parser.TwoArgumentParser;
import ua.kpi.calculator.model.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AddCommand implements Command {

    @Autowired
    private CommandFactory commandFactory;



    private CalculatorService calculatorService;

    @Autowired
    private ApplicationContext context;

    private ParserFactory parserFactory = ParserFactory.getInstance();


    @Autowired
    public AddCommand(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostConstruct
    void init(){
        commandFactory.registerCommand( "add" , this );
    }

    @Override
    public String execute(String arguments) {
        TwoArgumentParser parser = //context.getBean("parser" , TwoArgumentParser.class)
            parserFactory
                .twoArgumentParser(arguments);
        parser.parse();
        if(parser.hasError() ){
            return parser.getErrorMessage();
        }
        long result = calculatorService.add( parser.first(), parser.second());
        return "result = " + result;
    }

}
