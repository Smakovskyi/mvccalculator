package ua.kpi.calculator.controller.parser;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("parser")
@Scope("prototype")
public class TwoArgumentParser {
    private String input;
    private String errorMessage;
    private boolean isParsed = false;
    private boolean wasErrors = false;
    private int first;
    private int second;

    public TwoArgumentParser(String input) {
        this.input = input;
    }

    public TwoArgumentParser() {
    }

    public TwoArgumentParser parse(String input){
        this.input = input;
        isParsed = false;
        wasErrors = false;
        parse();
        return this;
    }

    public TwoArgumentParser parse(){
        isParsed = true;
        String args[] = input.split("\\s+");
        if( args.length != 2){
            errorMessage = "add command should get 2 arguments";
            wasErrors = true;
            return this;
        }

        try{
            first = Integer.parseInt(args[0]);
            second = Integer.parseInt(args[1]);
        }catch (Exception ex){
            errorMessage =  "Can't parse argument";
            wasErrors = true;

        }
        return this;
    }

    public boolean hasError(){
        return wasErrors;
    }

    public int first(){
        if( isParsed && !wasErrors )
            return this.first;
        throw new IllegalStateException();
    }

    public int second(){
        if( isParsed && !wasErrors )
            return this.second;
        throw new IllegalStateException();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
