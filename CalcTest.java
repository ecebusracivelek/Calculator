package com.example.calculatorapplication;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalcTest {
    private Calc calc;

    @BeforeEach
    public void setUp(){
        calc = new Calc();
    }

    @Test
    public void testAddition() {
        calc.calculate(3, "+");
        double result = calc.calculate(5, "+");
        assertEquals(8, result);
    }

    @Test
    public void testAdditionIfNegativeNum() {
        double result = calc.calculate(-5, "+");
        assertEquals(-5, result);
    }

    @Test
    public void testSubtraction() {
        calc.calculate(5, "+");
        double result = calc.calculate(3, "-");
        assertEquals(2, result);
    }

    @Test
    public void testMultiplication() {
        calc.calculate(2, "+");
        double result = calc.calculate(3, "x");
        assertEquals(6, result);
    }

    @Test
    public void testDivision() {
        calc.calculate(20, "+");
        double result = calc.calculate(10, "/");
        assertEquals(2, result);
    }

    @Test
    public void testLongArithmetic() {
        calc.calculate(1234567890, "+");
        calc.calculate(987654321, "+");
        double result = calc.getResult();
        assertEquals(2222222211L, result, 0.001);

        calc.calculate(1000000000, "x");
        result = calc.getResult();
        assertEquals(2.222222211E18, result, 1e12);
    }

    @Test
    public void testSquare() {
        double result = calc.calculate(4, "x²");
        assertEquals(16, result);
    }

    @Test
    public void testSquareRoot() {
        double result = calc.calculate(9, " √x");
        assertEquals(3, result, 0.0001);
    }

    @Test
    public void testReciprocal() {
        double result = calc.calculate(4, "1/x");
        assertEquals(0.25, result, 0.0001);
    }

    @Test
    public void testPercentage() {
        calc.calculate(200, "+");
        double result = calc.calculate(50, "%");
        assertEquals(100, result, 0.0001);
    }

    @Test
    public void testDividedByZero() {
        calc.calculate(10, "/");
        try {
            calc.calculate(0, "/");
        } catch (ArithmeticException e) {
            assertEquals("Can't divide by 0", e.getMessage());
        }
    }

    @Test
    public void testSquareRootOfNegative() {
        try {
            calc.calculate(-25, "√x");
        } catch (ArithmeticException e) {
            assertEquals("Can't calculate the square root of a negative number", e.getMessage());
        }
    }

    @Test
    public void testClearFunction() {
        calc.calculate(5, "+");
        calc.calculate(10, "+");
        calc.clear();
        double result = calc.getResult();
        assertEquals(0, result, 0.001);
        assertEquals("", calc.getCurrentOperation());
        assertTrue(calc.isStart());
    }

    @Test
    public void testSetCurrentOperation() {
        calc.setCurrentOperation("+");
        String operation = calc.getCurrentOperation();
        assertEquals("+", operation);
    }

    @Test
    public void testLargeMultiplication() {
        calc.calculate(Double.MAX_VALUE, "+");
        calc.calculate(2, "x");
        double result = calc.getResult();
        assertEquals(Double.POSITIVE_INFINITY, result);
    }

    @Test
    public void testSmallDecimal() {
        calc.calculate(Double.MIN_VALUE, "+");
        double result = calc.getResult();
        assertEquals(Double.MIN_VALUE, result);
    }

    @Test
    public void testPercentageBoundary() {
        calc.calculate(Double.MAX_VALUE, "+");
        double result = calc.calculate(Double.MAX_VALUE, "%");
        assertEquals(Double.POSITIVE_INFINITY, result);
    }

    @Test
    public void testChainingOperations() {
        calc.calculate(2, "+");
        calc.calculate(5, "-");
        calc.calculate(3, "x");
        calc.calculate(2, "/");
        assertEquals(-4.5, calc.getResult(), 0.001);
    }



}
