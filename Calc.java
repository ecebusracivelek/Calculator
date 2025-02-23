package com.example.calculatorapplication;
public class Calc {

    private double result;
    private String currentOperation;
    private boolean start;

    public Calc(){
        result = 0;
        currentOperation = "";
        start = true;
    }

    public double calculate(double input, String operation) throws ArithmeticException{
        switch (operation){
            case "+":
                result += input;
                break;
            case "-":
                result -= input;
                break;
            case "x":
                result *= input;
                break;
            case "/":
                if(input != 0)
                    result /= input;
                else
                    throw new ArithmeticException("Can't divide by 0");
                break;
            case "x²":
                result = input * input;
                break;
            case " √x":
                if(input >=0)
                    result = Math.sqrt(input);
                else
                    throw new ArithmeticException("Can't take square root of a negative number.");
                break;
            case "1/x":
                if(input != 0)
                    result = 1 / input;
                else
                    throw new ArithmeticException("Can't divide by 0");
                break;
            case "%":
                result = result * (input / 100);
                break;
            //case "=":
            //    break;
            default:
                result = input;
                break;
        }
        return result;
    }

    public double getResult(){
        return result;
    }

    public void setCurrentOperation(String operation){
        this.currentOperation = operation;
    }

    public String getCurrentOperation(){
        return currentOperation;
    }

    public boolean isStart(){
        return start;
    }

    public void setStart(boolean start){
        this.start = start;
    }

    public void clear(){
        result = 0;
        currentOperation = "";
        start = true;
    }
}
