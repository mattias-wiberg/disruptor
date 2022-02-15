
package com.lmax.disruptor.support;

import com.lmax.disruptor.EventFactory;

public final class TestEvent
{
    @Override
    public String toString()
    {
        return "Test Event";
    }

    public static final EventFactory<TestEvent> EVENT_FACTORY = TestEvent::new;
}
