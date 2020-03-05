package ua.kpi.calculator.controller.parser;

public class ParserFactory {
    private static ParserFactory instance;
    public static ParserFactory getInstance() {
        if (instance == null) {
            instance = new ParserFactory();
        }
        return instance;
    }
    private ParserFactory() {
    }
    public TwoArgumentParser twoArgumentParser( String input){
        return new TwoArgumentParser(input);
    }
}
