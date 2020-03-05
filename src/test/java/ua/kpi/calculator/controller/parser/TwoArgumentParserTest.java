package ua.kpi.calculator.controller.parser;


import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class TwoArgumentParserTest {

    TwoArgumentParser twoArgumentParser = null;

    @Before
    public void removeTwoArgumentParser(){
        twoArgumentParser = null;
    }

    public void newTwoArgumentParser(String str){
        twoArgumentParser = new TwoArgumentParser(str);
    }

    @Test
    public void parseTrue() {
        newTwoArgumentParser("2 9");
        twoArgumentParser.parse();
        List<Integer> result = Arrays.asList( twoArgumentParser.first() , twoArgumentParser.second() );
        assertThat( result , Matchers.<Collection<Integer>>
                             allOf( hasSize(2), contains(2,9) ));

        //assertEquals(, 2);
        //assertEquals(twoArgumentParser.second(), 9);
    }

    @Test(expected=IllegalStateException.class)
    public void parseIfThreeArguments() {
        newTwoArgumentParser("2 8 9");
        twoArgumentParser.parse();
        assertTrue(twoArgumentParser.hasError());
        String expectedErrorMessage = "add command should get 2 arguments";
        String actualErrorMessage = twoArgumentParser.getErrorMessage();
        assertThat( actualErrorMessage , equalTo( expectedErrorMessage)  );
        //assertEquals(, );
        twoArgumentParser.first();
    }
    @Test
    public void parseIfNotTwoArguments(){
        newTwoArgumentParser("2");
        twoArgumentParser.parse();
        assertTrue(twoArgumentParser.hasError());
        newTwoArgumentParser("2 3");
        twoArgumentParser.parse();
        assertFalse(twoArgumentParser.hasError());
    }

    @Test
    public void parseIfNotNumbers(){
        newTwoArgumentParser("one two");
        twoArgumentParser.parse();
        assertTrue(twoArgumentParser.hasError());
        assertEquals("Can't parse argument", twoArgumentParser.getErrorMessage());
    }
}