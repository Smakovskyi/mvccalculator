package ua.kpi.calculator.model.junit5;


import ua.kpi.calculator.model.CalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class CalculatorServiceTest {

    public CalculatorService calculatorService = new CalculatorService();

    @Test
    @DisplayName("new test for add")
    public void addTwoThreeAndFiveGetEight() {
        final int first = 3;
        final int second = 5;
        final long expectedSum = 8L;
        long actualSum;

        actualSum = calculatorService.add(first,second);

        assertEquals(expectedSum , actualSum);
    }

    @Test
    @DisplayName("new test for sub")
    public void sub() {
        assertEquals(calculatorService.sub(5, 3), 2);
        assertNotEquals(calculatorService.sub(3, 2), 3);
    }

    @Test
    @DisplayName ("direct test for dividing")
    void divideSixOnTwoGetThree(){
        int first = 6;
        int second = 2;
        Optional<Integer> expectedResult = Optional.of(3);

        Optional<Integer> actualResult = calculatorService.div(first,second);

        assertEquals(expectedResult,actualResult);
    }

    @Test
    @DisplayName ("direct test for dividing")
    void divideUsualSixOnTwoGetThree(){
        int first = 6;
        int second = 2;
        int expectedResult = 3;

        int actualResult = calculatorService.divUsual(first,second);

        assertEquals(expectedResult,actualResult);
    }

    @Test
    @DisplayName ("direct test for dividing")

    void divideUsualExceptionally(){
        int first = 6;
        int second = 0;

        assertThrows( IllegalArgumentException.class, () ->calculatorService.divUsual(first,second));

    }


}