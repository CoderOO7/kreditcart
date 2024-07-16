package com.kreditcart.productCatalogue.TestExamples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    // happy case
    @Test
    public void Test_AddTwoIntegers_ReturnsInt() {
        // arrange
        Calculator c = new Calculator();

        // act
        int res = c.add(2,3);

        // assert
        assertEquals(5, res);
    }

    // sad case
    @Test
    public void Test_DivideByZero_ThrowsException() {
        Calculator c= new Calculator();
        assertThrows(ArithmeticException.class, () ->  c.divide(16,0));
    }

    @Test
    public void Test_DivideTwoIntegers_ReturnsInt() {
        Calculator c= new Calculator();
        int res = c.divide(16,8);
        assertEquals(2, res);
    }

}