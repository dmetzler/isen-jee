package org.isen.guess;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class DeploymentTest extends JettyHarness {

    @Test
    public void itCanBrowseTheWelcomePage() throws Exception {
        String result = get(getBaseUri());
        assertThat(result).isNotEmpty();
        assertThat(result).contains("Guess a number");
    }


}
