package org.isen.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class CollectionLib {

    public static String concatenate(List<String> startList) {
        return Joiner.on(", ").join(startList);
    }

    public static List<String> revert(final List<String> startList) {
        return Lists.reverse(startList);
    }

    public static List<String> minimize(List<String> startList) {
        return Lists.transform(startList, new Function<String, String>() {

            @Override
            public String apply(String input) {
                return input.toLowerCase();
            }
        });
    }
}
