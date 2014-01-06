package org.isen.guess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GuessNumberTest extends JettyHarness {

    @Test
    public void itShouldGiveAFormToEnterANumber() throws Exception {
        String html = get(getBaseUri());

        assertThat(html).contains("<form");
        assertThat(html).contains("<input name=\"number\" />");
    }

    @Test
    public void itShouldHowManyTurnsAreLeft() throws Exception {
        String html = get(getBaseUri());
        for (int i = 10; i > 1; i--) {
            assertThat(html).contains( i + " turns left");
            html = get(getBaseUri() + "?number=-1");
        }


    }

    @Test
    public void itShouldSayYouLooseAfterAllTurns() throws Exception {
        for (int i = 10; i > 1; i--) {
            get(getBaseUri() + "?number=-1");
        }
        String html = get(getBaseUri() + "?number=-1");
        assertThat(html).contains("You loose");
    }

    @Test
    public void itCanResetTheGame() throws Exception {
        String html  = get(getBaseUri() + "?number=-1");
        assertThat(html).contains( "9 turns left");

        get(getBaseUri()+"restart");
        html  = get(getBaseUri());
        assertThat(html).contains( "10 turns left");
    }

}
