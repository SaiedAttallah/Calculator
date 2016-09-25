package com.cosmos.calculator;

import android.widget.Button;
import android.widget.TextView;

import com.cosmos.calculator.Injection.RoboRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sattallah on 5/29/2016.
 */
@RunWith(RoboRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)

public class GivenACalculatorActivity {

    private CalculatorActivity calculatorActivity;

    @Inject
    TextView screen;

    @Inject
    Button button;

    @Inject
    CalculatorOperations calculatorOperations;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.calculatorActivity = Robolectric.setupActivity(CalculatorActivity.class);
        this.calculatorActivity.screen = this.screen;
        this.calculatorActivity.button = this.button;

        when(this.screen.getText()).thenReturn("");
        when(this.button.getText()).thenReturn("");
    }

    @Test
    public void whenCallingOnClickNumberShouldSetTextViewWithNumber(){
        when(this.button.getText()).thenReturn("9");
        when(calculatorOperations.numberClicked("9")).thenReturn("9");

        this.calculatorActivity.onClickNumber(this.calculatorActivity.button);

        verify(this.screen).setText("9");
    }

    @Test
    public void whenCallingOnClickDecimalPointShouldSetTextViewWithDecimalPoint(){
        when(this.button.getText()).thenReturn(".");
        when(this.screen.getText()).thenReturn("");
        when(calculatorOperations.decimalPointClicked()).thenReturn(".");

        this.calculatorActivity.onClickDecimalPoint(this.calculatorActivity.button);

        verify(this.screen).setText(".");
    }

    @Test
    public void whenCallingOnClickSignShouldSetTextViewWithSign(){
        when(calculatorOperations.signClicked()).thenReturn("-");
        this.calculatorActivity.onClickSign(this.calculatorActivity.button);
        verify(this.screen).setText("-");
    }

    @Test
    public void whenCallingOnClickOperatorPlusShouldSetTextViewWithSign(){
        when(this.button.getText()).thenReturn("+");
        when(calculatorOperations.operatorClicked("+")).thenReturn("+");
        this.calculatorActivity.onClickOperator(this.calculatorActivity.button);
        verify(this.screen).setText("+");
    }

    @Test
    public void whenCallingOnClickOperatorMinusShouldSetTextViewWithSign(){
        when(this.button.getText()).thenReturn("-");
        when(calculatorOperations.operatorClicked("-")).thenReturn("-");
        this.calculatorActivity.onClickOperator(this.calculatorActivity.button);
        verify(this.screen).setText("-");
    }


    @Test
    public void whenCallingOnClickEqualShouldSetTextViewWithResult(){
        when(calculatorOperations.equalClicked()).thenReturn("1+2\n3");
        this.calculatorActivity.onClickEqual(this.calculatorActivity.button);
        verify(this.screen).setText("1+2\n3");
    }

    @Test
    public void whenCallingOnClickClearShouldSetTextViewWithResult(){
        when(calculatorOperations.clearClicked()).thenReturn("");
        this.calculatorActivity.onClickClear(this.calculatorActivity.button);
        verify(this.screen).setText("");
    }

    @Test
    public void whenCallingOnClickDeleteShouldSetTextViewWithResult(){
        when(calculatorOperations.deleteClicked()).thenReturn("1");
        this.calculatorActivity.onClickDelete(this.calculatorActivity.button);
        verify(this.screen).setText("1");
    }

}