package ua.kpi.calculator.controller;

import javax.annotation.PostConstruct;
import ua.kpi.calculator.controller.parser.TwoArgumentParser;
import ua.kpi.calculator.model.CalculatorService;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DivCommand implements Command{

    @Autowired
    private CalculatorService service ;//= CalculatorService.getInstance();

    @Autowired
    private CommandFactory commandFactory;

    @PostConstruct
    void init(){
        commandFactory.registerCommand( "div" , this );
    }

    @Override
    public String execute(String command) {
        TwoArgumentParser parser = new TwoArgumentParser(command).parse();
        if(parser.hasError()) {
            return parser.getErrorMessage();
        }
        int first = parser.first();
        int second = parser.second();
        Optional<Integer> result = service.div(first, second);

        result.ifPresent( val -> System.out.println(val) );

        return result.map( val -> "result = " + val  )
                     .orElse("result is not a number!");
                     //.orElseGet( () ->  "result is not a number!" );
                     //.orElseThrow( RuntimeException::new );
                             //() -> new RuntimeException(" /0 ") );
        /*
        if( result.isPresent() ) {
            return "result = " + result.get();
        }else{
            return "result is not a number!";
        }*/
    }
}
