// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.VelocityTracker;

public final class VelocityTrackerCompat
{

    private VelocityTrackerCompat()
    {
    }

    public static float getXVelocity(VelocityTracker velocitytracker, int i)
    {
        return velocitytracker.getXVelocity(i);
    }

    public static float getYVelocity(VelocityTracker velocitytracker, int i)
    {
        return velocitytracker.getYVelocity(i);
    }
}
