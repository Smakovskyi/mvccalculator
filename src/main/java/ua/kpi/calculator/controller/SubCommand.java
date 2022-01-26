package ua.kpi.calculator.controller;

//import javax.annotation.PostConstruct;
import ua.kpi.calculator.controller.parser.ParserFactory;
import ua.kpi.calculator.controller.parser.TwoArgumentParser;
import ua.kpi.calculator.model.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubCommand implements Command {

    @Autowired
    private CalculatorService service ;//= CalculatorService.getInstance();

    @Autowired
    private CommandFactory commandFactory;

    private ParserFactory parserFactory = ParserFactory.getInstance();

   // @PostConstruct
    void init(){
        commandFactory.registerCommand( "sub" , this );
    }

    @Override
    public String execute(String command) {
        TwoArgumentParser parser = parserFactory.twoArgumentParser(command).parse();
        if(parser.hasError()) {
            return parser.getErrorMessage();
        }
        int first = parser.first();
        int second = parser.second();
        long result = service.sub(first, second);
        return "result = " + result;
    }
}
