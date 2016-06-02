package com.cosmos.calculator;

import android.util.Log;

import java.util.regex.Pattern;

/**
 * Created by sattallah on 5/30/2016.
 */
public class CalculatorOperations {

    protected String display = "";
    protected String currentOperator = "";
    protected String result = "";
    protected Boolean decPoint = false;

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setCurrentOperator(String currentOperator) {
        this.currentOperator = currentOperator;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDecPoint(Boolean decPoint) {
        this.decPoint = decPoint;
    }

    public String getDisplay() {
        return display;
    }

    public String getCurrentOperator() {
        return currentOperator;
    }

    public String getResult() {
        return result;
    }

    public Boolean getDecPoint() {
        return decPoint;
    }

    public String numberClicked(String s){
        if (!(result.isEmpty())){
            clear();
            display += s;
        }else display += s;

        return display;
    }

    public String decimalPointClicked(String s){
        if (!(result.isEmpty())){
            clear();
            display += s;
            decPoint = !decPoint;
        }

        if(!decPoint){
            display += s;
            decPoint = !decPoint;
        }
        return display;
    }

    public String signClicked(){
        if (!(result.isEmpty())){
            clear();
            display += "-";
        }
        if (display.isEmpty() || display.charAt(display.length()-1) == '+' || display.charAt(display.length()-1) == '×' || display.charAt(display.length()-1) == '÷'){
            display += "-";
        }
        return display;
    }

    public String operatorClicked(String s){

        if (display.isEmpty()) return display;

        if (!(currentOperator.isEmpty())){
            //Log.d("CalcX", "" + display.charAt(display.length()-1));
            if (isOperator(display.charAt(display.length()-1))){
                display = display.replace(display.charAt(display.length()-1), s.charAt(0));
                currentOperator = s;
                return display;
            }else {
                calculateResult();
                display = result;
                result = "";
            }
            currentOperator = s;
        }

        if (!(result.isEmpty())){
            String tempDisplay = result;
            clear();
            display = tempDisplay;
        }

        display += s;
        currentOperator = s;
        if (decPoint){
            decPoint = !decPoint;
        }
        return display;
    }

    private boolean isOperator(char op){
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

    private boolean calculateResult(){
        if (currentOperator.isEmpty()) return false;

        String[] operation = display.split(Pattern.quote(currentOperator));

        if (operation.length < 2) return false;

        result = String.valueOf(operate(operation[0], operation[1], currentOperator));
        return true;
    }

    public String equalClicked(){
        if (display.isEmpty()) return null;

        if (!calculateResult()) return display;
        String s = display + "\n" + String.valueOf(result);
        decPoint = !decPoint;
        return s;
    }

    public String deleteClicked(){
        if (!(result.isEmpty())) {
            result = "";
            return display;
        }

        if (display.length() >= 1){
            if (display.length()-1 == '.'){
                decPoint = !decPoint;
            }
            display = display.substring(0, display.length()-1);
        }
        return display;
    }

    public String clearClicked(){
        clear();
        return display;
    }

    private void clear(){
        if (decPoint){
            decPoint = !decPoint;
        }
        display = "";
        currentOperator = "";
        result = "";
    }
}
