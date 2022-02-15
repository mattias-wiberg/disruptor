
package com.lmax.disruptor.util;


public final class PaddedLong extends MutableLong
{
    public volatile long p1, p2, p3, p4, p5, p6 = 7L;


    public PaddedLong()
    {
    }


    public PaddedLong(final long initialValue)
    {
        super(initialValue);
    }

    public long sumPaddingToPreventOptimisation()
    {
        return p1 + p2 + p3 + p4 + p5 + p6;
    }
}
