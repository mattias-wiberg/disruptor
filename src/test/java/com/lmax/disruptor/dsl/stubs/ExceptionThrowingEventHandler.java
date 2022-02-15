
package com.lmax.disruptor.dsl.stubs;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.support.TestEvent;

public class ExceptionThrowingEventHandler implements EventHandler<TestEvent>
{
    private final RuntimeException testException;

    public ExceptionThrowingEventHandler(final RuntimeException testException)
    {
        this.testException = testException;
    }

    @Override
    public void onEvent(final TestEvent entry, final long sequence, final boolean endOfBatch) throws Exception
    {
        throw testException;
    }
}
