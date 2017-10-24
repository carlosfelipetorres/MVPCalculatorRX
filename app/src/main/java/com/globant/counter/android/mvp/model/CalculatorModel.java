package com.globant.counter.android.mvp.model;

public class CalculatorModel {

    private double result = 0;

    public void calculateOperation(double first, double second, String operator) {
        switch (operator) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "/":
                result = first / second;
                break;
            case "*":
                result = first * second;
                break;
            case "!":
                result = second * -1;
                break;
            default:
                result = 0;
        }
    }

    public double getResult() {
        return result;
    }

}
