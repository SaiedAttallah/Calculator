package com.cosmos.calculator;

import com.cosmos.calculator.Injection.RoboRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by sattallah on 6/1/2016.
 */
@RunWith(RoboRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class GivenACalculatorOperations {

    private CalculatorOperations calculatorOperations;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        calculatorOperations = new CalculatorOperations();
        calculatorOperations.setDisplay("");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("");
        calculatorOperations.setDecPoint(false);
    }

    @Test
    public void whenCallingNumberClickedAndDisplayIsEmptyAndResultIsEmptySetTextViewWithNumber(){
        calculatorOperations.setDisplay("");
        calculatorOperations.setResult("");
        assertEquals(calculatorOperations.numberClicked("1"), "1");
        assertEquals(calculatorOperations.getDisplay(), "1");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingNumberClickedAndDisplayIsNotEmptyAndResultIsEmptySetTextViewWithNewDisplay(){
        calculatorOperations.setDisplay("1+");
        calculatorOperations.setResult("");
        assertEquals(calculatorOperations.numberClicked("2"), "1+2");
        assertEquals(calculatorOperations.getDisplay(), "1+2");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingNumberClickedAndDisplayIsNotEmptyAndResultIsNotEmptyClearAndAddNumberToDisplay(){
        calculatorOperations.setDisplay("1+1");
        calculatorOperations.setResult("2");
        assertEquals(calculatorOperations.numberClicked("9"), "9");
        assertEquals(calculatorOperations.getDisplay(), "9");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingDecimalPointClickedAndDisplayIsEmptyAndResultIsEmptySetTextViewWithDecimalPoint(){
        calculatorOperations.setDisplay("");
        calculatorOperations.setResult("");
        calculatorOperations.setDecPoint(false);
        assertEquals(calculatorOperations.decimalPointClicked(), ".");
        assertEquals(calculatorOperations.getDisplay(), ".");
        assertEquals(calculatorOperations.getResult(), "");
        assertTrue(calculatorOperations.getDecPoint());
    }

    @Test
    public void whenCallingDecimalPointClickedAndDisplayIsNotEmptyAndResultIsEmptyAndDecimalPointIsFalseAddDecimalPointToDisplay(){
        calculatorOperations.setDisplay("1.2+3");
        calculatorOperations.setResult("");
        calculatorOperations.setDecPoint(false);
        assertEquals(calculatorOperations.decimalPointClicked(), "1.2+3.");
        assertEquals(calculatorOperations.getDisplay(), "1.2+3.");
        assertEquals(calculatorOperations.getResult(), "");
        assertTrue(calculatorOperations.getDecPoint());
    }

    @Test
    public void whenCallingDecimalPointClickedAndDisplayIsNotEmptyAndResultIsEmptyAndDecimalPointIsTrueDoNotAddDecimalPointToDisplay(){
        calculatorOperations.setDisplay("1.2+3.");
        calculatorOperations.setResult("");
        calculatorOperations.setDecPoint(true);
        assertEquals(calculatorOperations.decimalPointClicked(), "1.2+3.");
        assertEquals(calculatorOperations.getDisplay(), "1.2+3.");
        assertEquals(calculatorOperations.getResult(), "");
        assertTrue(calculatorOperations.getDecPoint());
    }

    @Test
    public void whenCallingDecimalPointClickedAndDisplayIsNotEmptyAndResultIsNotEmptyAndDecimalPointIsFalseAddDecimalPointToDisplay(){
        calculatorOperations.setDisplay("1.2+3.4");
        calculatorOperations.setResult("6.6");
        calculatorOperations.setDecPoint(false);
        assertEquals(calculatorOperations.decimalPointClicked(), ".");
        assertEquals(calculatorOperations.getDisplay(), ".");
        assertEquals(calculatorOperations.getResult(), "");
        assertTrue(calculatorOperations.getDecPoint());
    }

    @Test
    public void whenCallingSignClickedAndDisplayIsEmptyAndResultIsEmptyAddNegSignToDisplay(){
        calculatorOperations.setDisplay("");
        calculatorOperations.setResult("");
        assertEquals(calculatorOperations.signClicked(), "-");
        assertEquals(calculatorOperations.getDisplay(), "-");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingSignClickedAndDisplayIsNotEmptyAndLastCharIsPlusAndResultIsEmptyAddNegSignToDisplay(){
        calculatorOperations.setDisplay("1+");
        calculatorOperations.setResult("");
        assertEquals(calculatorOperations.signClicked(), "1+-");
        assertEquals(calculatorOperations.getDisplay(), "1+-");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingSignClickedAndDisplayIsNotEmptyAndLastCharIsMultiplyAndResultIsEmptyAddNegSignToDisplay(){
        calculatorOperations.setDisplay("1×");
        calculatorOperations.setResult("");
        assertEquals(calculatorOperations.signClicked(), "1×-");
        assertEquals(calculatorOperations.getDisplay(), "1×-");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingSignClickedAndDisplayIsNotEmptyAndLastCharIsDivideAndResultIsEmptyAddNegSignToDisplay(){
        calculatorOperations.setDisplay("1÷");
        calculatorOperations.setResult("");
        assertEquals(calculatorOperations.signClicked(), "1÷-");
        assertEquals(calculatorOperations.getDisplay(), "1÷-");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingSignClickedAndDisplayIsNotEmptyAndLastCharIsMinusAndResultIsEmptyDoNotAddNegSignToDisplay(){
        calculatorOperations.setDisplay("-");
        calculatorOperations.setResult("");
        assertEquals(calculatorOperations.signClicked(), "-");
        assertEquals(calculatorOperations.getDisplay(), "-");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingSignClickedAndDisplayIsNotEmptyAndLastCharIsNumberAndResultIsEmptyDoNotAddNegSignToDisplay(){
        calculatorOperations.setDisplay("-1");
        calculatorOperations.setResult("");
        assertEquals(calculatorOperations.signClicked(), "-1");
        assertEquals(calculatorOperations.getDisplay(), "-1");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingSignClickedAndDisplayIsNotEmptyAndResultIsNotEmptyDoNotAddNegSignToDisplay(){
        calculatorOperations.setDisplay("-1+-2");
        calculatorOperations.setResult("-3");
        assertEquals(calculatorOperations.signClicked(), "-");
        assertEquals(calculatorOperations.getDisplay(), "-");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingOperatorClickedAndDisplayIsEmptyAndResultIsEmptyDoNotAddOperatorToDisplay(){
        calculatorOperations.setDisplay("");
        calculatorOperations.setResult("");
        assertEquals(calculatorOperations.operatorClicked("+"), "");
        assertEquals(calculatorOperations.getDisplay(), "");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingOperatorClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsEmptyAndDecPointIsFalseAddOperatorToDisplay(){
        calculatorOperations.setDisplay("1");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("");
        assertEquals(calculatorOperations.operatorClicked("+"), "1+");
        assertEquals(calculatorOperations.getDisplay(), "1+");
        assertEquals(calculatorOperations.getResult(), "");
        assertEquals(calculatorOperations.getCurrentOperator(), "+");
    }

    @Test
    public void whenCallingOperatorClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsEmptyAndDecPointIsTrueAddOperatorToDisplayAndDecPointFalse(){
        calculatorOperations.setDisplay("-1");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("");
        calculatorOperations.setDecPoint(true);
        assertEquals(calculatorOperations.operatorClicked("+"), "-1+");
        assertEquals(calculatorOperations.getDisplay(), "-1+");
        assertEquals(calculatorOperations.getResult(), "");
        assertEquals(calculatorOperations.getCurrentOperator(), "+");
        assertFalse(calculatorOperations.getDecPoint());

    }

    @Test
    public void whenCallingOperatorClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsNotEmptyAndLastCharIsOperatorReplaceLastCharToNewOperator(){
        calculatorOperations.setDisplay("-1-");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("-");
        assertEquals(calculatorOperations.operatorClicked("+"), "-1+");
        assertEquals(calculatorOperations.getDisplay(), "-1+");
        assertEquals(calculatorOperations.getResult(), "");
        assertEquals(calculatorOperations.getCurrentOperator(), "+");
    }

    @Test
    public void whenCallingOperatorClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsNotEmptyAndLastCharIsNotOperatorCalculateResultAndAddResultAndOperatorToDisplayAndSetResultEmpty(){
        calculatorOperations.setDisplay("-1+2");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("+");
        assertEquals(calculatorOperations.operatorClicked("×"), "1×");
        assertEquals(calculatorOperations.getDisplay(), "1×");
        assertEquals(calculatorOperations.getResult(), "");
        assertEquals(calculatorOperations.getCurrentOperator(), "×");
    }

    @Test
    public void whenCallingOperatorClickedAndDisplayIsNotEmptyAndResultIsNotEmptyAndCurrentOperatorIsNotEmptyAddResultAndOperatorToDisplayAndClear(){
        calculatorOperations.setDisplay("1+1");
        calculatorOperations.setResult("2");
        calculatorOperations.setCurrentOperator("+");
        assertEquals(calculatorOperations.operatorClicked("×"), "2×");
        assertEquals(calculatorOperations.getDisplay(), "2×");
        assertEquals(calculatorOperations.getResult(), "");
        assertEquals(calculatorOperations.getCurrentOperator(), "×");
    }

    @Test
    public void whenCallingEqualClickedAndDisplayIsEmptyAndResultIsEmptyReturnDisplay(){
        calculatorOperations.setDisplay("");
        calculatorOperations.setResult("");
        assertEquals(calculatorOperations.equalClicked(), "");
        assertEquals(calculatorOperations.getDisplay(), "");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingEqualClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsEmptyReturnDisplay(){
        calculatorOperations.setDisplay("123");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("");
        assertEquals(calculatorOperations.equalClicked(), "123");
        assertEquals(calculatorOperations.getDisplay(), "123");
        assertEquals(calculatorOperations.getResult(), "");
        assertEquals(calculatorOperations.getCurrentOperator(), "");
    }

    @Test
    public void whenCallingEqualClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsNotEmptyAndThereIsNoNumbersAfterOperatorReturnDisplay(){
        calculatorOperations.setDisplay("123+");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("+");
        assertEquals(calculatorOperations.equalClicked(), "123+");
        assertEquals(calculatorOperations.getDisplay(), "123+");
        assertEquals(calculatorOperations.getResult(), "");
        assertEquals(calculatorOperations.getCurrentOperator(), "+");
    }

    @Test
    public void whenCallingEqualClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsNotEmptyAndThereIsNumberAfterOperatorAndFakeNegativeCalculateAndReturnNegativeDisplayAndResult(){
        calculatorOperations.setDisplay("-1-2");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("-");
        calculatorOperations.setFakeNegative(true);
        assertEquals(calculatorOperations.equalClicked(), "-1-2\n-3");
        assertEquals(calculatorOperations.getDisplay(), "1-2");
        assertEquals(calculatorOperations.getResult(), "-3");
        assertEquals(calculatorOperations.getCurrentOperator(), "-");
        assertFalse(calculatorOperations.getFakeNegative());
    }

    @Test
    public void whenCallingEqualClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsNotEmptyAndThereIsNumberAfterOperatorAndNotFakeNegativeCalculateAndReturnDisplayAndResult(){
        calculatorOperations.setDisplay("1-2");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("-");
        calculatorOperations.setFakeNegative(false);
        assertEquals(calculatorOperations.equalClicked(), "1-2\n-1");
        assertEquals(calculatorOperations.getDisplay(), "1-2");
        assertEquals(calculatorOperations.getResult(), "-1");
        assertEquals(calculatorOperations.getCurrentOperator(), "-");
        assertFalse(calculatorOperations.getFakeNegative());
    }

    @Test
    public void whenCallingEqualClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsMultiplicationAndThereIsNumberAfterOperatorAndNotFakeNegativeCalculateAndReturnDisplayAndResult(){
        calculatorOperations.setDisplay("2×1");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("×");
        calculatorOperations.setFakeNegative(false);
        assertEquals(calculatorOperations.equalClicked(), "2×1\n2");
        assertEquals(calculatorOperations.getDisplay(), "2×1");
        assertEquals(calculatorOperations.getResult(), "2");
        assertEquals(calculatorOperations.getCurrentOperator(), "×");
        assertFalse(calculatorOperations.getFakeNegative());
    }

    @Test
    public void whenCallingEqualClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsNotEmptyAndThereIsNumberAfterOperatorAndThereIsInfinityBeforeOperatorSetResultToInfinity(){
        calculatorOperations.setDisplay("∞*1");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("*");
        calculatorOperations.setFakeNegative(false);
        assertEquals(calculatorOperations.equalClicked(), "∞*1\n∞");
        assertEquals(calculatorOperations.getDisplay(), "∞*1");
        assertEquals(calculatorOperations.getResult(), "∞");
        assertEquals(calculatorOperations.getCurrentOperator(), "*");
        assertFalse(calculatorOperations.getFakeNegative());
    }

    @Test
    public void whenCallingEqualClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsDivideAndNumberAfterOperatorIsZeroReturnInfinity(){
        calculatorOperations.setDisplay("1÷0");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("÷");
        assertEquals(calculatorOperations.equalClicked(), "1÷0\n∞");
        assertEquals(calculatorOperations.getDisplay(), "1÷0");
        assertEquals(calculatorOperations.getResult(), "∞");
        assertEquals(calculatorOperations.getCurrentOperator(), "÷");
    }

    @Test
    public void whenCallingEqualClickedAndDisplayIsNotEmptyAndResultIsEmptyAndCurrentOperatorIsNotEmptyAndThereIsNumberAfterOperatorAndDecPointTrueCalculateAndReturnDisplayAndResultAndDecPointFalse(){
        calculatorOperations.setDisplay("1+0.2");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("+");
        calculatorOperations.setDecPoint(true);
        assertEquals(calculatorOperations.equalClicked(), "1+0.2\n1.2");
        assertEquals(calculatorOperations.getDisplay(), "1+0.2");
        assertEquals(calculatorOperations.getResult(), "1.2");
        assertEquals(calculatorOperations.getCurrentOperator(), "+");
        assertFalse(calculatorOperations.getDecPoint());
    }

    @Test
    public void whenCallingDeleteClickedAndDisplayIsNotEmptyAndResultIsNotEmptyClearResultAndReturnDisplay(){
        calculatorOperations.setDisplay("1+2");
        calculatorOperations.setResult("3");
        assertEquals(calculatorOperations.deleteClicked(), "1+2");
        assertEquals(calculatorOperations.getDisplay(), "1+2");
        assertEquals(calculatorOperations.getResult(), "");
    }

    @Test
    public void whenCallingDeleteClickedAndDisplayIsNotEmptyAndResultIsEmptyDeleteLastCharAndReturnDisplay(){
        calculatorOperations.setDisplay("1+2");
        calculatorOperations.setResult("");
        calculatorOperations.setDecPoint(false);
        assertEquals(calculatorOperations.deleteClicked(), "1+");
        assertEquals(calculatorOperations.getDisplay(), "1+");
        assertEquals(calculatorOperations.getResult(), "");
        assertFalse(calculatorOperations.getDecPoint());
    }

    @Test
    public void whenCallingDeleteClickedAndDisplayIsNotEmptyAndResultIsEmptyAndLastCharIsDecPointClearDecPointAndSetDecPointFalseAndReturnDisplay(){
        calculatorOperations.setDisplay("1+2.");
        calculatorOperations.setResult("");
        calculatorOperations.setDecPoint(true);
        assertEquals(calculatorOperations.deleteClicked(), "1+2");
        assertEquals(calculatorOperations.getDisplay(), "1+2");
        assertEquals(calculatorOperations.getResult(), "");
        assertFalse(calculatorOperations.getDecPoint());
    }

    @Test
    public void whenCallingDeleteClickedAndDisplayIsNotEmptyAndResultIsEmptyAndLastCharIsOperatorClearOperatorAndSetOperatorEmptyAndReturnDisplay(){
        calculatorOperations.setDisplay("1+");
        calculatorOperations.setResult("");
        calculatorOperations.setCurrentOperator("+");
        assertEquals(calculatorOperations.deleteClicked(), "1");
        assertEquals(calculatorOperations.getDisplay(), "1");
        assertEquals(calculatorOperations.getResult(), "");
        assertEquals(calculatorOperations.getCurrentOperator(), "");
    }

    @Test
    public void whenCallingClearClickedAndDisplayIsNotEmptyAndResultIsNotEmptyClearAllStrings(){
        calculatorOperations.setDisplay("-1-0.2");
        calculatorOperations.setResult("-3");
        calculatorOperations.setDecPoint(true);
        calculatorOperations.setCurrentOperator("-");
        assertEquals(calculatorOperations.clearClicked(), "");
        assertEquals(calculatorOperations.getDisplay(), "");
        assertEquals(calculatorOperations.getResult(), "");
        assertFalse(calculatorOperations.getDecPoint());
        assertEquals(calculatorOperations.getCurrentOperator(), "");
    }
}