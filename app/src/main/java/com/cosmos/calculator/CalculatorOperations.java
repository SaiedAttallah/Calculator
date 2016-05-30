package com.cosmos.calculator;

import android.util.Log;

import java.util.regex.Pattern;

/**
 * Created by sattallah on 5/30/2016.
 */
public class CalculatorOperations {

    private static String display = "";
    private static String currentOperator = "";
    private static String result = "";

    public String numberClicked(String s){
        if (!(result.isEmpty())){
            clear();
            return display;
        }

        display += s;
        return display;
    }

    public String signClicked(){
        if (!(result.isEmpty())){
            clear();
            return display;
        }

        display += "-";
        return display;
    }

    public String operatorClicked(String s){

        if (display.isEmpty()) return null;

        if (!(result.isEmpty())){
            String tempDisplay = result;
            clear();
            display = tempDisplay;
        }

        if (!(currentOperator.isEmpty())){
            Log.d("CalcX", "" + display.charAt(display.length()-1));
            if (isOperator(display.charAt(display.length()-1))){
                display = display.replace(display.charAt(display.length()-1), s.charAt(0));
                return display;
            }else {
                calculateResult();
                display = result;
                result = "";
            }
            currentOperator = s;
        }

        display += s;
        currentOperator = s;
        return display;
    }

    public boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case '×':
            case '÷': return true;
            default: return false;
        }
    }

    private double operate(String x, String y, String op){
        switch (op){
            case "+": return Double.valueOf(x) + Double.valueOf(y);
            case "-":
                try {
                    return Double.valueOf(x) - Double.valueOf(y);
                } catch (Exception e) {
                    Log.d("Calc", e.getMessage());
                }
            case "×": return Double.valueOf(x) * Double.valueOf(y);
            case "÷":
                try {
                    return Double.valueOf(x) / Double.valueOf(y);
                } catch (Exception e) {
                    Log.d("Calc", e.getMessage());
                }
            default: return -1;
        }
    }

    public boolean calculateResult(){
        if (currentOperator.isEmpty()) return false;

        String[] operation = display.split(Pattern.quote(currentOperator));

        if (operation.length < 2) return false;

        result = String.valueOf(operate(operation[0], operation[1], currentOperator));
        return true;
    }

    public String equalClicked(){
        if (display.isEmpty()) return null;

        if (!calculateResult()) return null;
        String s = display + "\n" + String.valueOf(result);
        return s;
    }

    public String deleteClicked(){
        if (!(result.isEmpty())) return null;

        if (display.length() >= 1){
            display = display.substring(0, display.length()-1);
        }
        return display;
    }

    public String clearClicked(){
        clear();
        return display;
    }

    public void clear(){
        display = "";
        currentOperator = "";
        result = "";
    }

}
