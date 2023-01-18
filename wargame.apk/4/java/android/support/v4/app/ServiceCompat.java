// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Service;
import java.lang.annotation.Annotation;

public final class ServiceCompat
{
    public static interface StopForegroundFlags
        extends Annotation
    {
    }


    private ServiceCompat()
    {
    }

    public static void stopForeground(Service service, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
        {
            service.stopForeground(i);
            return;
        }
        boolean flag;
        if((i & 1) != 0)
            flag = true;
        else
            flag = false;
        service.stopForeground(flag);
    }

    public static final int START_STICKY = 1;
    public static final int STOP_FOREGROUND_DETACH = 2;
    public static final int STOP_FOREGROUND_REMOVE = 1;
}
