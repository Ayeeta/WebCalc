package com.example.webcalc;

import com.orm.SugarRecord;

public class WebCalculator extends SugarRecord {
    private String numberOne;
    private String numberTwo;
    private String response;
    private String expected;
    private String passed;

    public WebCalculator() {
    }

    public WebCalculator(String numberOne, String numberTwo, String response, String expected, String passed) {
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
        this.response = response;
        this.expected = expected;
        this.passed = passed;
    }

    public String getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(String numberOne) {
        this.numberOne = numberOne;
    }

    public String getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(String numberTwo) {
        this.numberTwo = numberTwo;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getPassed() {
        return passed;
    }

    public void setPassed(String passed) {
        this.passed = passed;
    }
}
