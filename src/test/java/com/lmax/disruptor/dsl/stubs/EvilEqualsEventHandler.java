
package com.lmax.disruptor.dsl.stubs;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.support.TestEvent;

public class EvilEqualsEventHandler implements EventHandler<TestEvent>
{
    @Override
    public void onEvent(final TestEvent entry, final long sequence, boolean endOfBatch) throws Exception
    {
    }

    @SuppressWarnings({"EqualsWhichDoesntCheckParameterClass"})
    public boolean equals(Object o)
    {
        return true;
    }

    public int hashCode()
    {
        return 1;
    }
}
