
package com.lmax.disruptor;

import com.lmax.disruptor.support.TestEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class FatalExceptionHandlerTest
{
    @Test
    public void shouldHandleFatalException()
    {
        final Exception causeException = new Exception();
        final TestEvent event = new TestEvent();

        ExceptionHandler<Object> exceptionHandler = new FatalExceptionHandler();

        try
        {
            exceptionHandler.handleEventException(causeException, 0L, event);
        }
        catch (RuntimeException ex)
        {
            assertEquals(causeException, ex.getCause());
        }
    }
}
