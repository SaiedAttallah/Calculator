package com.cosmos.calculator;

import android.app.Application;

import com.cosmos.calculator.Injection.GraphProvider;
import com.cosmos.calculator.Injection.InjectionModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by sattallah on 5/29/2016.
 */
public class App extends Application {

    private ObjectGraph applicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        GraphProvider graphProvider = GraphProvider.getInstance();
        graphProvider.addModules(getModules().toArray());
        applicationGraph = graphProvider.getGraph();

    }

    /**
     * A list of modules to use for the application graph. Subclasses can override this method to
     * provide additional modules provided they call {@code super.getModules()}.
     */
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new InjectionModule(this));

    }

    ObjectGraph getApplicationGraph() {
        return applicationGraph;
    }
}
