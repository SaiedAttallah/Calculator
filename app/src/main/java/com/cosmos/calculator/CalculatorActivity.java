package com.cosmos.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cosmos.calculator.Injection.GraphProvider;

import java.util.regex.Pattern;

public class CalculatorActivity extends AppCompatActivity {

    private TextView screen;
    private CalculatorOperations calculatorOperations = new CalculatorOperations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        screen = (TextView)findViewById(R.id.textView);
        //screen.setText(calculatorOperations.getDisplay());
    }

//    public void updateScreen(String s){
//        screen.setText(s);
//    }

    public void onClickNumber(View v){
        Button b = (Button) v;
        screen.setText(calculatorOperations.numberClicked(b.getText().toString()));
    }

    public void onClickSign(View v){
        screen.setText(calculatorOperations.signClicked());
    }

    public void onClickOperator(View v){
        Button b = (Button)v;
        screen.setText(calculatorOperations.operatorClicked(b.getText().toString()));
    }

    public void onClickEqual(View v){
        if (calculatorOperations.getDisplay().isEmpty()) return;

        if (!calculatorOperations.opResult()) return;
        screen.setText(calculatorOperations.getDisplay() + "\n" + calculatorOperations.getResult());
    }

    public void onClickClear(View v){
        calculatorOperations.clear();
        screen.setText(calculatorOperations.getDisplay());
    }

    public void onClickDelete(View v){
        if (!(calculatorOperations.getResult().isEmpty())) return;

        if (calculatorOperations.getDisplay().length() >= 1){
            calculatorOperations.setDisplay(calculatorOperations.getDisplay().substring(0, calculatorOperations.getDisplay().length()-1));
            screen.setText(calculatorOperations.getDisplay());
        }

    }


}