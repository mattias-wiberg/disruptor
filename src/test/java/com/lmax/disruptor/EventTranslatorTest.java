
package com.lmax.disruptor;

import com.lmax.disruptor.support.StubEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class EventTranslatorTest
{
    private static final String TEST_VALUE = "Wibble";

    @Test
    public void shouldTranslateOtherDataIntoAnEvent()
    {
        StubEvent event = StubEvent.EVENT_FACTORY.newInstance();
        EventTranslator<StubEvent> eventTranslator = new ExampleEventTranslator(TEST_VALUE);

        eventTranslator.translateTo(event, 0);

        assertEquals(TEST_VALUE, event.getTestString());
    }

    public static final class ExampleEventTranslator
        implements EventTranslator<StubEvent>
    {
        private final String testValue;

        public ExampleEventTranslator(final String testValue)
        {
            this.testValue = testValue;
        }

        @Override
        public void translateTo(final StubEvent event, long sequence)
        {
            event.setTestString(testValue);
        }
    }
}
