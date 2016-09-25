package com.cosmos.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cosmos.calculator.Injection.GraphProvider;

import javax.inject.Inject;

public class CalculatorActivity extends AppCompatActivity {

    protected TextView screen;
    protected Button button;

    @Inject
    protected CalculatorOperations calculatorOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GraphProvider graphProvider = GraphProvider.getInstance();
        graphProvider.getGraph().inject(this);
        setContentView(R.layout.activity_calculator);
        this.screen = (TextView)findViewById(R.id.textView);
    }

    public void onClickNumber(View v){
        this.button = (Button) v;
        this.screen.setText(calculatorOperations.numberClicked(this.button.getText().toString()));
    }

    public void onClickDecimalPoint(View v){
        this.screen.setText(calculatorOperations.decimalPointClicked());
    }

    public void onClickSign(View v){
        this.screen.setText(calculatorOperations.signClicked());
    }

    public void onClickOperator(View v){
        this.button = (Button)v;
        this.screen.setText(calculatorOperations.operatorClicked(this.button.getText().toString()));
    }

    public void onClickEqual(View v){
        this.screen.setText(calculatorOperations.equalClicked());
    }

    public void onClickClear(View v){
        this.screen.setText(calculatorOperations.clearClicked());
    }

    public void onClickDelete(View v){
        this.screen.setText(calculatorOperations.deleteClicked());
    }


}