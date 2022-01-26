package ua.kpi.calculator.controller;

import java.awt.geom.RectangularShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


import org.springframework.test.util.ReflectionTestUtils;
import ua.kpi.calculator.controller.parser.ParserFactory;
import ua.kpi.calculator.controller.parser.TwoArgumentParser;
import ua.kpi.calculator.model.CalculatorService;

public class SubCommandTest {
  @Test
  @DisplayName("test parse five and three")
  void testParse()
  {
    final String inputString = "5 3";
    final String expectedResult = "result = 2";

    CalculatorService calculatorService = mock(CalculatorService.class);
    ParserFactory parserFactory = mock(ParserFactory.class);
    TwoArgumentParser parser = mock(TwoArgumentParser.class);

    when(parserFactory.twoArgumentParser(inputString)).thenReturn(parser);
    when(parser.parse()).thenReturn(parser);
    when(parser.hasError()).thenReturn(false);
    when(parser.first()).thenReturn(5);
    when(parser.second()).thenReturn(3);
    when(calculatorService.sub(5, 3)).thenReturn(2L);


    SubCommand command = new SubCommand();
    ReflectionTestUtils.setField(command,"service", calculatorService);
    ReflectionTestUtils.setField(command, "parserFactory", parserFactory);

    String actualResult = command.execute(inputString);
    assertThat( actualResult, equalTo( expectedResult ) );

    verify(parserFactory).twoArgumentParser(inputString);
    verify(parser).parse();
    verify(parser).hasError();
    verify(parser).first();
    verify(parser).second();
    verify(calculatorService).sub(5,3);
  }


}
