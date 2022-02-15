
package com.lmax.disruptor.util;


public class MutableLong
{
    private long value = 0L;


    public MutableLong()
    {
    }


    public MutableLong(final long initialValue)
    {
        this.value = initialValue;
    }


    public long get()
    {
        return value;
    }


    public void set(final long value)
    {
        this.value = value;
    }


    public void increment()
    {
        value++;
    }
}
