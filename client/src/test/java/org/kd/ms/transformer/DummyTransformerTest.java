package org.kd.ms.transformer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DummyTransformerTest {

    @Mock
    private DummyTransformer transformer;

    class DummyTransformerCaptor {

        ArgumentCaptor<List> argumentCaptorList = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<String> argumentCaptorString = ArgumentCaptor.forClass(String.class);

        protected DummyTransformerCaptor(final DummyTransformer dummyTransformer) {
            verify(dummyTransformer).transform(argumentCaptorList.capture(), argumentCaptorString.capture());
        }

        public List getList() {
            return argumentCaptorList.getValue();
        }

        public String getString() {
            return argumentCaptorString.getValue();
        }
    }

    @Test
    public void test() {

//        transformer.transform(Arrays.asList("10", "11", "12"));
//
//        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
//
//        verify(transformer).transform(argumentCaptor.capture());

        transformer.transform(Arrays.asList("10", "11", "12"), "test");
        DummyTransformerCaptor captor = new DummyTransformerCaptor(transformer);

        // do assert here
    }
}
