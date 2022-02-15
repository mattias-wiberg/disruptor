package com.lmax.disruptor.alternatives;


import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

class LhsPaddingVarHandle
{
    protected byte
        p10, p11, p12, p13, p14, p15, p16, p17,
        p20, p21, p22, p23, p24, p25, p26, p27,
        p30, p31, p32, p33, p34, p35, p36, p37,
        p40, p41, p42, p43, p44, p45, p46, p47,
        p50, p51, p52, p53, p54, p55, p56, p57,
        p60, p61, p62, p63, p64, p65, p66, p67,
        p70, p71, p72, p73, p74, p75, p76, p77;
}

class ValueVarHandle extends LhsPaddingVarHandle
{
    protected volatile long value;
}

class RhsPaddingVarHandle extends ValueVarHandle
{
    protected byte
        p90, p91, p92, p93, p94, p95, p96, p97,
        p100, p101, p102, p103, p104, p105, p106, p107,
        p110, p111, p112, p113, p114, p115, p116, p117,
        p120, p121, p122, p123, p124, p125, p126, p127,
        p130, p131, p132, p133, p134, p135, p136, p137,
        p140, p141, p142, p143, p144, p145, p146, p147,
        p150, p151, p152, p153, p154, p155, p156, p157;
}


public class SequenceVarHandle extends RhsPaddingVarHandle
{
    static final long INITIAL_VALUE = -1L;
    private static final VarHandle VALUE_FIELD;

    static
    {
        try
        {
            VALUE_FIELD = MethodHandles.lookup().in(SequenceVarHandle.class)
                    .findVarHandle(SequenceVarHandle.class, "value", long.class);
        }
        catch (final Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    public SequenceVarHandle()
    {
        this(INITIAL_VALUE);
    }


    public SequenceVarHandle(final long initialValue)
    {
        VALUE_FIELD.setRelease(this, initialValue);
    }


    public long get()
    {
        return (long) (Long) VALUE_FIELD.getAcquire(this);
    }


    public void set(final long value)
    {
        VALUE_FIELD.setRelease(this, value);
    }


    public void setVolatile(final long value)
    {
        VALUE_FIELD.setVolatile(this, value);
    }


    public boolean compareAndSet(final long expectedValue, final long newValue)
    {
        return (boolean) VALUE_FIELD.compareAndSet(this, expectedValue, newValue);
    }


    public long incrementAndGet()
    {
        return addAndGet(1L);
    }


    public long addAndGet(final long increment)
    {
        final long oldValue = (Long) VALUE_FIELD.getAndAdd(this, increment);
        return oldValue + increment;
    }

    @Override
    public String toString()
    {
        return Long.toString(get());
    }
}