
package com.lmax.disruptor;

import org.junit.jupiter.api.Test;

import static com.lmax.disruptor.support.WaitStrategyTestUtil.assertWaitForWithDelayOf;


public class SleepingWaitStrategyTest
{
    @Test
    public void shouldWaitForValue() throws Exception
    {
        assertWaitForWithDelayOf(50, new SleepingWaitStrategy());
    }
}
