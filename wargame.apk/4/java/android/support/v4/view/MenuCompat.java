// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.MenuItem;

public final class MenuCompat
{

    private MenuCompat()
    {
    }

    public static void setShowAsAction(MenuItem menuitem, int i)
    {
        menuitem.setShowAsAction(i);
    }
}
