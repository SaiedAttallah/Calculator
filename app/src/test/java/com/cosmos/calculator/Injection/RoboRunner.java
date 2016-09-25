package com.cosmos.calculator.Injection;

import android.app.Application;

import org.junit.runners.model.InitializationError;
import org.robolectric.DefaultTestLifecycle;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.TestLifecycle;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;

import java.lang.reflect.Method;

/**
 * Created by sattallah on 5/29/2016.
 */
public class RoboRunner extends RobolectricGradleTestRunner {
    public RoboRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    protected Class<? extends TestLifecycle> getTestLifecycleClass() {
        return CalculatorTestLifecycle.class;
    }

    public static class CalculatorTestLifecycle extends DefaultTestLifecycle {
        @Override
        public Application createApplication(Method method, AndroidManifest appManifest, Config config) {
            return new Application();
        }

        @Override
        public void prepareTest(final Object test){
            GraphProvider graphProvider = GraphProvider.getInstance();
            graphProvider.addModules(new TestModule());
            graphProvider.getGraph().inject(test);
        }

        @Override
        public void afterTest(final Method method) {
        }


    }
}
