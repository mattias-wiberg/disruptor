
package com.lmax.disruptor.alternatives;

import com.lmax.disruptor.util.Util;
import sun.misc.Unsafe;

// https://github.com/LMAX-Exchange/disruptor/issues/231

class LhsPaddingDouble
{
    protected long
            p1, p2, p3, p4, p5, p6, p7, p8,
            p9, p10, p11, p12, p13, p14, p15;
}

class ValueDoublePadded extends LhsPaddingDouble
{
    protected volatile long value;
}

class RhsPaddingDouble extends ValueDoublePadded
{
    protected long
            p1, p2, p3, p4, p5, p6, p7, p8,
            p9, p10, p11, p12, p13, p14, p15;
}


public class SequenceDoublePadded extends RhsPaddingDouble
{
    static final long INITIAL_VALUE = -1L;
    private static final Unsafe UNSAFE;
    private static final long VALUE_OFFSET;

    static
    {
        UNSAFE = Util.getUnsafe();
        try
        {
            VALUE_OFFSET = UNSAFE.objectFieldOffset(ValueDoublePadded.class.getDeclaredField("value"));
        }
        catch (final Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    public SequenceDoublePadded()
    {
        this(INITIAL_VALUE);
    }


    public SequenceDoublePadded(final long initialValue)
    {
        UNSAFE.putOrderedLong(this, VALUE_OFFSET, initialValue);
    }


    public long get()
    {
        return value;
    }


    public void set(final long value)
    {
        UNSAFE.putOrderedLong(this, VALUE_OFFSET, value);
    }


    public void setVolatile(final long value)
    {
        UNSAFE.putLongVolatile(this, VALUE_OFFSET, value);
    }


    public boolean compareAndSet(final long expectedValue, final long newValue)
    {
        return UNSAFE.compareAndSwapLong(this, VALUE_OFFSET, expectedValue, newValue);
    }


    public long incrementAndGet()
    {
        return addAndGet(1L);
    }


    public long addAndGet(final long increment)
    {
        long currentValue;
        long newValue;

        do
        {
            currentValue = get();
            newValue = currentValue + increment;
        }
        while (!compareAndSet(currentValue, newValue));

        return newValue;
    }

    @Override
    public String toString()
    {
        return Long.toString(get());
    }
}
