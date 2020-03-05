package ua.kpi.calculator.model;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorServiceTestOld {

    public CalculatorService calculatorService = new  CalculatorService();//.getInstance();

    @Test
    public void add() {
        assertEquals(calculatorService.add(3, 5), 8);
        assertNotEquals(calculatorService.add(3, 2), 3);
    }

    @Test
    public void sub() {
        assertEquals(calculatorService.sub(5, 3), 2);
        assertNotEquals(calculatorService.sub(3, 2), 3);
    }
}