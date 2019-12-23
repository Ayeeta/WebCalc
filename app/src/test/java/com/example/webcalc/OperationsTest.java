package com.example.webcalc;

import org.junit.Test;

import static org.junit.Assert.*;

public class OperationsTest {

    private Operations operations = new Operations(4.0, 2.0);

    @Test
    public void add() {

        double output = operations.add();

        assertEquals(6.0, output, .1);

    }

    @Test
    public void subtract() {
        double output = operations.subtract();
        assertEquals(2.0, output, .1);
    }

    @Test
    public void multiply() {
        double output = operations.multiply();
        assertEquals(8.0, output, .1);
    }

    @Test
    public void divide() {
        double output = operations.divide();
        assertEquals(2.0, output, .1);
    }
}