package org.kd.ms.transformer;

import java.util.ArrayList;
import java.util.List;

public class DummyTransformer {

    public List<String> transform(final List<String> list) {

        for (String str : list) {
            List<Integer> integers = new ArrayList<>();
            integers.add(Integer.parseInt(str));
        }

        return list;

    }

    public List<String> transform(final List<String> list, final String name) {

        if (name == null) {
            return null;
        }

        for (String str : list) {
            List<Integer> integers = new ArrayList<>();
            integers.add(Integer.parseInt(str));
        }

        return list;

    }

}
