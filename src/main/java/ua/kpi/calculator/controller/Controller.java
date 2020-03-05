package ua.kpi.calculator.controller;

import ua.kpi.calculator.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;

public class Controller {

    private InputStream in;
    private View view;

    @Autowired
    CommandFactory commandFactory; // = CommandFactory.getInstance();

    public Controller(InputStream in, View view) {
        this.in = in;
        this.view = view;
    }

    public void processUserInput(){
        view.printHelp();
        try ( BufferedReader reader = new BufferedReader(new InputStreamReader( in , "UTF-8")) ){
            while(true){
                String userLine = reader.readLine();
                int spaceIndex = userLine.indexOf(' ');
                if( spaceIndex == -1 ){
                    view.printInputCorrectCommand();
                }
                String userCommand = userLine.substring(0, spaceIndex);
                String arguments = userLine.substring(spaceIndex).trim();
                Command command = commandFactory.getCommand(userCommand);
                String result = command.execute(arguments);
                view.printResult(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
