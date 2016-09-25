package com.cosmos.calculator.Injection;

import android.content.Context;

import com.cosmos.calculator.CalculatorActivity;
import com.cosmos.calculator.CalculatorOperations;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sattallah on 5/26/2016.
 */

@Module(
        injects = {
                CalculatorActivity.class,
                CalculatorOperations.class
        },
        library = true
)

public class InjectionModule {
    private final Context application;

    public InjectionModule(Context application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    CalculatorOperations providecalculatorOperations() {return new CalculatorOperations();}
}
