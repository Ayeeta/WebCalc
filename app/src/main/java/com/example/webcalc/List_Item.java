package com.example.webcalc;

public class List_Item {
    private String numberOne;
    private String numberTwo;
    private String response;
    private String expected;
    private String passed;


    public List_Item(String numberOne, String numberTwo, String response, String expected, String passed) {
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
        this.response = response;
        this.expected = expected;
        this.passed = passed;
    }

    public String getNumberOne() {
        return numberOne;
    }

    public String getNumberTwo() {
        return numberTwo;
    }

    public String getResponse() {
        return response;
    }

    public String getExpected() {
        return expected;
    }

    public String getPassed() {
        return passed;
    }
}
