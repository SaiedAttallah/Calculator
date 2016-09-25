package com.cosmos.calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * Created by sattallah on 5/30/2016.
 */
public class CalculatorOperations {

    protected String display = "";
    protected String currentOperator = "";
    protected String result = "";
    protected Boolean decPoint = false;
    protected Boolean fakeNegative = false;

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

    public void setFakeNegative(Boolean fakeNegative) {
        this.fakeNegative = fakeNegative;
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

    public Boolean getFakeNegative() {
        return fakeNegative;
    }

    public String numberClicked(String s){
        if (!(result.isEmpty())){
            clear();
            display += s;
        }else display += s;

        return display;
    }

    public String decimalPointClicked(){
        if (!(result.isEmpty())){
            clear();
            display += ".";
            decPoint = !decPoint;
        }

        if(!decPoint){
            display += ".";
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

        if (!(result.isEmpty())){
            String tempDisplay = result;
            clear();
            display = tempDisplay;
        }

        if (!(currentOperator.isEmpty())){
            //Log.d("CalcX", "" + display.charAt(display.length()-1));
            if (isOperator(display.charAt(display.length()-1))){
                display = display.substring(0,display.length()-1) + s;
                currentOperator = s;
                return display;
            }else {
                calculateResult();
                display = result;
                result = "";
            }
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
        double res = -1;
        switch (op){
            case "+": res = Double.valueOf(x) + Double.valueOf(y);
                break;
            case "-": res = Double.valueOf(x) - Double.valueOf(y);
                break;
            case "×": res = Double.valueOf(x) * Double.valueOf(y);
                break;
            case "÷": res = Double.valueOf(x) / Double.valueOf(y);
                break;
            //default: return res;
        }
        return res;
    }

    private boolean calculateResult(){
        if (currentOperator.isEmpty()) return false;
        String[] operation;
        if (display.charAt(0) == '-' && currentOperator.equals("-")){
            display = display.substring(1, display.length());
            operation = display.split(Pattern.quote(currentOperator));

            if (operation.length < 2) return false;

            Double res = (-1 * Double.valueOf(operation[0]) - Double.valueOf(operation[1]));
            DecimalFormat df = new DecimalFormat();
            df.setRoundingMode(RoundingMode.CEILING);
            result = String.valueOf(df.format(res));
            fakeNegative = true;
        }else if(display.charAt(0) == '∞') {
            result = "∞";
        }else{
            operation = display.split(Pattern.quote(currentOperator));

            if (operation.length < 2) return false;

            Double res = operate(operation[0], operation[1], currentOperator);
            DecimalFormat df = new DecimalFormat();
            df.setRoundingMode(RoundingMode.CEILING);
            result = String.valueOf(df.format(res));
        }
        return true;
    }

    public String equalClicked(){
        if (display.isEmpty()) return display;

        String s;

        if (!calculateResult()){
            return display;
        }else {
            if (fakeNegative){
                s = "-" + display + "\n" + result;
                fakeNegative = false;
            }else {
                s = display + "\n" + result;
            }

            if (decPoint){
                decPoint = !decPoint;
            }
        }


        return s;
    }

    public String deleteClicked(){
        if (!(result.isEmpty())) {
            result = "";
            return display;
        }

        if (display.length() >= 1){
            if (display.charAt(display.length()-1) == '.'){
                decPoint = !decPoint;
            }
            if (isOperator(display.charAt(display.length()-1))){
                currentOperator = "";
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
