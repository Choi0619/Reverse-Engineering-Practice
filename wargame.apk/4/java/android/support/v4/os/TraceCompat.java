// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.Trace;

public final class TraceCompat
{

    private TraceCompat()
    {
    }

    public static void beginSection(String s)
    {
        if(android.os.Build.VERSION.SDK_INT >= 18)
            Trace.beginSection(s);
    }

    public static void endSection()
    {
        if(android.os.Build.VERSION.SDK_INT >= 18)
            Trace.endSection();
    }
}
