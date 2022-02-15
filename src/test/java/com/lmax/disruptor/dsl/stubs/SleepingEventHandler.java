
package com.lmax.disruptor.dsl.stubs;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.support.TestEvent;

public class SleepingEventHandler implements EventHandler<TestEvent>
{
    @Override
    public void onEvent(final TestEvent entry, final long sequence, final boolean endOfBatch) throws Exception
    {
        Thread.sleep(1000);
    }
}
