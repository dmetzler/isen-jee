package org.isen.hello;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeploymentTest extends JettyHarness {

    @Test
    public void itCanBrowseIndex() throws Exception {
        String index = get(getBaseUri()); // <> Récupère le contenu d'une page à un adresse
        assertTrue(index.contains("Hello")); // <> Vérifie le contenu de la page
    }
}
