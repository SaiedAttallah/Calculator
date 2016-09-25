package com.cosmos.calculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

/**
 * Created by sattallah on 6/7/2016.
 */
public class GivenAnApp {
    App app;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.app = new App();
    }

    @Test
    public void testOnCreate() throws Exception {

        this.app.onCreate();

        assertNotNull(this.app.getApplicationGraph());
    }

    @Test
    public void testGetModules() throws Exception {
        assertNotNull(this.app.getModules());
    }
}
