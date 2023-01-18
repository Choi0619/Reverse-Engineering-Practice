// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.AlarmManager;
import android.app.PendingIntent;

public final class AlarmManagerCompat
{

    private AlarmManagerCompat()
    {
    }

    public static void setAlarmClock(AlarmManager alarmmanager, long l, PendingIntent pendingintent, PendingIntent pendingintent1)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            alarmmanager.setAlarmClock(new android.app.AlarmManager.AlarmClockInfo(l, pendingintent), pendingintent1);
            return;
        } else
        {
            setExact(alarmmanager, 0, l, pendingintent1);
            return;
        }
    }

    public static void setAndAllowWhileIdle(AlarmManager alarmmanager, int i, long l, PendingIntent pendingintent)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            alarmmanager.setAndAllowWhileIdle(i, l, pendingintent);
            return;
        } else
        {
            alarmmanager.set(i, l, pendingintent);
            return;
        }
    }

    public static void setExact(AlarmManager alarmmanager, int i, long l, PendingIntent pendingintent)
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
        {
            alarmmanager.setExact(i, l, pendingintent);
            return;
        } else
        {
            alarmmanager.set(i, l, pendingintent);
            return;
        }
    }

    public static void setExactAndAllowWhileIdle(AlarmManager alarmmanager, int i, long l, PendingIntent pendingintent)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            alarmmanager.setExactAndAllowWhileIdle(i, l, pendingintent);
            return;
        } else
        {
            setExact(alarmmanager, i, l, pendingintent);
            return;
        }
    }
}
