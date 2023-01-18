// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            TooltipCompatHandler

public class TooltipCompat
{
    private static class Api26ViewCompatImpl
        implements ViewCompatImpl
    {

        public void setTooltipText(View view, CharSequence charsequence)
        {
            view.setTooltipText(charsequence);
        }

        private Api26ViewCompatImpl()
        {
        }

    }

    private static class BaseViewCompatImpl
        implements ViewCompatImpl
    {

        public void setTooltipText(View view, CharSequence charsequence)
        {
            TooltipCompatHandler.setTooltipText(view, charsequence);
        }

        private BaseViewCompatImpl()
        {
        }

    }

    private static interface ViewCompatImpl
    {

        public abstract void setTooltipText(View view, CharSequence charsequence);
    }


    private TooltipCompat()
    {
    }

    public static void setTooltipText(View view, CharSequence charsequence)
    {
        IMPL.setTooltipText(view, charsequence);
    }

    private static final ViewCompatImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            IMPL = new Api26ViewCompatImpl();
        else
            IMPL = new BaseViewCompatImpl();
    }
}
