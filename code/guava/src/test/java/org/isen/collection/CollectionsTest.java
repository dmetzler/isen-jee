package org.isen.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CollectionsTest {

    public static final List<String> START_LIST = Arrays.asList(new String[] {
            "UN", "DEUX", "TROIS" });

    @Test
    public void iShouldBeAbleToConcatenateStrings() throws Exception {
        // Given a list
        List<String> list = START_LIST;

        // When i apply the concatenat methode
        String result = CollectionLib.concatenate(list);

        // Then it returns the list concatenated
        assertThat(result).isEqualTo("UN, DEUX, TROIS");
    }

    @Test
    public void shouldRevertAList() {
        List<String> result = CollectionLib.revert(START_LIST);
        assertThat(result).isEqualTo(
                Arrays.asList(new String[] { "TROIS", "DEUX", "UN" }));
    }

    @Test
    public void iShouldBeAbleToMinimizeListMembers() throws Exception {
        List<String> minimized = CollectionLib.minimize(START_LIST);
        assertThat(minimized).isEqualTo(
                Arrays.asList(new String[] { "un", "deux", "trois" }));
    }

    @Test
    public void shouldTestAll() {

        assertThat(CollectionLib.concatenate(START_LIST)).isEqualTo(
                "UN, DEUX, TROIS");

        assertThat(CollectionLib.concatenate(CollectionLib.revert(START_LIST)))
                .isEqualTo("TROIS, DEUX, UN");

        assertThat(
                CollectionLib.concatenate(CollectionLib.minimize(CollectionLib
                        .revert(START_LIST)))).isEqualTo("trois, deux, un");
    }

}