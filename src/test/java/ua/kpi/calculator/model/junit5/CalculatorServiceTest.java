package ua.kpi.calculator.model.junit5;


import org.junit.jupiter.api.Disabled;
import ua.kpi.calculator.model.CalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class CalculatorServiceTest {

    public CalculatorService calculatorService = new CalculatorService();

    @Test
    @DisplayName("new test for add")
    public void addThreeAndFiveGetEight() {
        var first = 3;
        var second = 5;
        final int expectedSum = 8;

        var actualSum = calculatorService.add(first,second);

        assertEquals(expectedSum , actualSum);
        assertThat( expectedSum, is( actualSum) );
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

    @Disabled
    @Test
    @DisplayName ("direct test for dividing")
    void divideUsualExceptionally(){
        int first = 6;
        int second = 0;

        assertThrows( IllegalArgumentException.class, () ->calculatorService.divUsual(first,second));

    }

    @Test
    @DisplayName("test for 2 x 2 multipling")
    void multiply2x2get4(){
        int first  = 2;
        int second = 2;
        final long expectedResult = 4;

        long actual = calculatorService.mul(first, second);
        assertThat(expectedResult, is(actual) );

    }

    @Test
    @DisplayName ("direct test for multiplying")
    void multiplyTwoWithThree(){
        var first = 2;
        var second = 3;
        var expected = 6L;

        var actualResult = calculatorService.mul(first, second);

        assertThat(actualResult, is(expected));
    }


}