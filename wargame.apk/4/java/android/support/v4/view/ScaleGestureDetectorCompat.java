// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.ScaleGestureDetector;

public final class ScaleGestureDetectorCompat
{

    private ScaleGestureDetectorCompat()
    {
    }

    public static boolean isQuickScaleEnabled(ScaleGestureDetector scalegesturedetector)
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            return scalegesturedetector.isQuickScaleEnabled();
        else
            return false;
    }

    public static boolean isQuickScaleEnabled(Object obj)
    {
        return isQuickScaleEnabled((ScaleGestureDetector)obj);
    }

    public static void setQuickScaleEnabled(ScaleGestureDetector scalegesturedetector, boolean flag)
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            scalegesturedetector.setQuickScaleEnabled(flag);
    }

    public static void setQuickScaleEnabled(Object obj, boolean flag)
    {
        setQuickScaleEnabled((ScaleGestureDetector)obj, flag);
    }
}
