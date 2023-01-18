// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.widget.PopupMenu;

public final class PopupMenuCompat
{

    private PopupMenuCompat()
    {
    }

    public static android.view.View.OnTouchListener getDragToOpenListener(Object obj)
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            return ((PopupMenu)obj).getDragToOpenListener();
        else
            return null;
    }
}
