package com.lmax.disruptor.alternatives;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;



public class SequenceVarHandleArray
{
    private static final int VALUE_INDEX = 8;
    private static final VarHandle VALUE_FIELD = MethodHandles.arrayElementVarHandle(long[].class);

    private final long[] paddedValue = new long[16];

    static final long INITIAL_VALUE = -1L;


    public SequenceVarHandleArray()
    {
        this(INITIAL_VALUE);
    }


    public SequenceVarHandleArray(final long initialValue)
    {
        this.set(initialValue);
    }


    public long get()
    {
        return (long) (Long) VALUE_FIELD.getAcquire(this.paddedValue, VALUE_INDEX);
    }


    public void set(final long value)
    {
        VALUE_FIELD.setRelease(this.paddedValue, VALUE_INDEX, value);
    }


    public void setVolatile(final long value)
    {
        VALUE_FIELD.setVolatile(this.paddedValue, VALUE_INDEX, value);
    }


    public boolean compareAndSet(final long expectedValue, final long newValue)
    {
        return (boolean) VALUE_FIELD.compareAndSet(this.paddedValue, VALUE_INDEX, expectedValue, newValue);
    }


    public long incrementAndGet()
    {
        return addAndGet(1L);
    }


    public long addAndGet(final long increment)
    {
        final long oldValue = (Long) VALUE_FIELD.getAndAdd(this.paddedValue, VALUE_INDEX, increment);
        return oldValue + increment;
    }

    @Override
    public String toString()
    {
        return Long.toString(get());
    }
}

