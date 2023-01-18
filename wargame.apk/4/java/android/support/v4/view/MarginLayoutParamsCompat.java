// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;


public final class MarginLayoutParamsCompat
{

    private MarginLayoutParamsCompat()
    {
    }

    public static int getLayoutDirection(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
    {
        int i;
        int j;
        if(android.os.Build.VERSION.SDK_INT >= 17)
            i = marginlayoutparams.getLayoutDirection();
        else
            i = 0;
        j = i;
        if(i != 0)
        {
            j = i;
            if(i != 1)
                j = 0;
        }
        return j;
    }

    public static int getMarginEnd(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
            return marginlayoutparams.getMarginEnd();
        else
            return marginlayoutparams.rightMargin;
    }

    public static int getMarginStart(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
            return marginlayoutparams.getMarginStart();
        else
            return marginlayoutparams.leftMargin;
    }

    public static boolean isMarginRelative(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
            return marginlayoutparams.isMarginRelative();
        else
            return false;
    }

    public static void resolveLayoutDirection(android.view.ViewGroup.MarginLayoutParams marginlayoutparams, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
            marginlayoutparams.resolveLayoutDirection(i);
    }

    public static void setLayoutDirection(android.view.ViewGroup.MarginLayoutParams marginlayoutparams, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
            marginlayoutparams.setLayoutDirection(i);
    }

    public static void setMarginEnd(android.view.ViewGroup.MarginLayoutParams marginlayoutparams, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
        {
            marginlayoutparams.setMarginEnd(i);
            return;
        } else
        {
            marginlayoutparams.rightMargin = i;
            return;
        }
    }

    public static void setMarginStart(android.view.ViewGroup.MarginLayoutParams marginlayoutparams, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
        {
            marginlayoutparams.setMarginStart(i);
            return;
        } else
        {
            marginlayoutparams.leftMargin = i;
            return;
        }
    }
}
