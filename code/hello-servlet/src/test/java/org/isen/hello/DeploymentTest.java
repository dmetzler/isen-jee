package org.isen.hello;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeploymentTest extends JettyHarness {


    @Test
    public void itCanBrowseIndex() throws Exception {
        String index = get(getBaseUri());
        assertTrue(index.contains("Hello"));
    }
}
