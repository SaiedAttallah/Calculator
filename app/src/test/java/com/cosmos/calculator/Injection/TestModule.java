package com.cosmos.calculator.Injection;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.cosmos.calculator.CalculatorActivity;
import com.cosmos.calculator.CalculatorOperations;
import com.cosmos.calculator.GivenACalculatorActivity;
import com.cosmos.calculator.GivenACalculatorOperations;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sattallah on 5/26/2016.
 */

@Module(
        injects = {
                CalculatorActivity.class,
                GivenACalculatorActivity.class,
                GivenACalculatorOperations.class
        },
        library = true
)

public class TestModule {
    @Provides
    Context getAppContext() {
        return Mockito.mock(Context.class);
    }

    @Provides
    TextView getTextView() {
        return  Mockito.mock(TextView.class);
    }

    @Provides
    Button getButton() {
        return  Mockito.mock(Button.class);
    }

    @Provides
    @Singleton
    CalculatorOperations calculatorOperations() {return Mockito.mock(CalculatorOperations.class);}

}
