package com.example.webcalc;

public enum CalculatorOperations {

    ADD("+"),
    SUBTRACT("-"),
    DIVIDE("/"),
    MULTIPLY("*");

    private String op;
    CalculatorOperations(String op) {
        this.op = op;
    }

    private String value(){
        return op;
    }

    public static String getOperationSymbol(String value){
        String symbol = "";
        for(CalculatorOperations operation: CalculatorOperations.values()){
           if(operation.name().equalsIgnoreCase(value)){
               return operation.value();
           }
        }

        return symbol;
    }
}
