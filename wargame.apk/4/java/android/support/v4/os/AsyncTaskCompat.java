// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.AsyncTask;

public final class AsyncTaskCompat
{

    private AsyncTaskCompat()
    {
    }

    public static transient AsyncTask executeParallel(AsyncTask asynctask, Object aobj[])
    {
        if(asynctask == null)
        {
            throw new IllegalArgumentException("task can not be null");
        } else
        {
            asynctask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, aobj);
            return asynctask;
        }
    }
}
