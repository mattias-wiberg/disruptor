
package com.lmax.disruptor;

import com.lmax.disruptor.support.TestEvent;
import org.junit.jupiter.api.Test;

public final class IgnoreExceptionHandlerTest
{
    @Test
    public void shouldHandleAndIgnoreException()
    {
        final Exception ex = new Exception();
        final TestEvent event = new TestEvent();

        ExceptionHandler<Object> exceptionHandler = new IgnoreExceptionHandler();
        exceptionHandler.handleEventException(ex, 0L, event);
    }
}
