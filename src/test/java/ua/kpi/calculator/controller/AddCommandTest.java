package ua.kpi.calculator.controller;

import ua.kpi.calculator.controller.parser.ParserFactory;
import ua.kpi.calculator.controller.parser.TwoArgumentParser;
import ua.kpi.calculator.model.CalculatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ParserFactory.class)
public class AddCommandTest {

    @Test
    public void execute2Plus3Returns5() {
        //mocking parser
        TwoArgumentParser parser = mock(TwoArgumentParser.class);
        when(parser.hasError()).thenReturn(false);
        when(parser.first() ).thenReturn(2);
        when(parser.second()).thenReturn(3);
        when(parser.parse()).thenReturn(parser);
        //mocking parserFactory
        ParserFactory parserFactory = mock(ParserFactory.class);
        when(parserFactory.twoArgumentParser(anyString())).thenReturn(parser);
        CalculatorService calculatorService = mock(CalculatorService.class);
        when(calculatorService.add(2, 3)).thenReturn(5L);
        //setting mocks as dependencies
        AddCommand addCommand = new AddCommand(calculatorService);
        ReflectionTestUtils.setField(addCommand , "parserFactory" , parserFactory);
        final String expectedResult = "result = 5";

        String actualResult = addCommand.execute("2 3");

        verify(parser).parse();
        verify(calculatorService).add(2,3);
        assertThat( actualResult, equalTo( expectedResult ) );
    }

    @Test
    public void testThreeArgumentsExecute() {
        String input = "2 3 4";
        ParserFactory parserFactory = mock(ParserFactory.class);
        TwoArgumentParser parser = mock(TwoArgumentParser.class);
        when(parserFactory.twoArgumentParser(input)).thenReturn(parser);
        when(parser.parse()).thenReturn(parser);
        when(parser.hasError()).thenReturn(true);
        when(parser.getErrorMessage()).thenReturn("error");

        CalculatorService calculatorService = mock(CalculatorService.class);

        AddCommand addCommand = new AddCommand(calculatorService);
        ReflectionTestUtils.setField(addCommand, "parserFactory", parserFactory);

        String expected = "error";
        String actual = addCommand.execute(input);

        verifyZeroInteractions(calculatorService);
        verify(parserFactory).twoArgumentParser(input);
        verify(parser).parse();

        assertEquals(expected, actual);
    }

    @Test
    //@SneakyThrows
    public void testExecute2And3WithStaticMethod() {
        TwoArgumentParser parser = mock(TwoArgumentParser.class);
        ParserFactory parserFactory = mock(ParserFactory.class);
        PowerMockito.mockStatic( ParserFactory.class );
        PowerMockito.when( ParserFactory.getInstance() ).thenReturn(parserFactory);

        when(parserFactory.twoArgumentParser(anyString())).thenReturn(parser);
        CalculatorService calculatorService = mock(CalculatorService.class);
        when(calculatorService.add(2, 3)).thenReturn(5L);


        when(parser.hasError()).thenReturn(false);
        when(parser.first() ).thenReturn(2);
        when(parser.second()).thenReturn(3);
        when(parser.parse()).thenReturn(parser);
        AddCommand addCommand = new AddCommand(calculatorService);



        String expectedResult = "result = 5";
        String actualResult = addCommand.execute("2 3");

        verify(parser).parse();
        verify(calculatorService).add(2,3);
        assertThat( actualResult, equalTo( expectedResult ) );
    }
}