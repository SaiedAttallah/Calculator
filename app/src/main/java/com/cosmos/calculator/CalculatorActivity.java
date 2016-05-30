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
    }

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
        screen.setText(calculatorOperations.equalClicked());
    }

    public void onClickClear(View v){
        screen.setText(calculatorOperations.clearClicked());
    }

    public void onClickDelete(View v){
        screen.setText(calculatorOperations.deleteClicked());
//        if (calculatorOperations.deleteClicked() != null) {
//            screen.setText(calculatorOperations.deleteClicked());
//        }
    }


}