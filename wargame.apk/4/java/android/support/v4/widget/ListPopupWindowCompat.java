// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.view.View;
import android.widget.ListPopupWindow;

public final class ListPopupWindowCompat
{

    private ListPopupWindowCompat()
    {
    }

    public static android.view.View.OnTouchListener createDragToOpenListener(ListPopupWindow listpopupwindow, View view)
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            return listpopupwindow.createDragToOpenListener(view);
        else
            return null;
    }

    public static android.view.View.OnTouchListener createDragToOpenListener(Object obj, View view)
    {
        return createDragToOpenListener((ListPopupWindow)obj, view);
    }
}
