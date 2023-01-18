// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.graphics.Rect;
import android.view.Gravity;

public final class GravityCompat
{

    private GravityCompat()
    {
    }

    public static void apply(int i, int j, int k, Rect rect, int l, int i1, Rect rect1, int j1)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
        {
            Gravity.apply(i, j, k, rect, l, i1, rect1, j1);
            return;
        } else
        {
            Gravity.apply(i, j, k, rect, l, i1, rect1);
            return;
        }
    }

    public static void apply(int i, int j, int k, Rect rect, Rect rect1, int l)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
        {
            Gravity.apply(i, j, k, rect, rect1, l);
            return;
        } else
        {
            Gravity.apply(i, j, k, rect, rect1);
            return;
        }
    }

    public static void applyDisplay(int i, Rect rect, Rect rect1, int j)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
        {
            Gravity.applyDisplay(i, rect, rect1, j);
            return;
        } else
        {
            Gravity.applyDisplay(i, rect, rect1);
            return;
        }
    }

    public static int getAbsoluteGravity(int i, int j)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
            return Gravity.getAbsoluteGravity(i, j);
        else
            return 0xff7fffff & i;
    }

    public static final int END = 0x800005;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 0x800007;
    public static final int RELATIVE_LAYOUT_DIRECTION = 0x800000;
    public static final int START = 0x800003;
}
