// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

public final class ListViewCompat
{

    private ListViewCompat()
    {
    }

    public static boolean canScrollList(ListView listview, int i)
    {
        boolean flag1 = false;
        if(android.os.Build.VERSION.SDK_INT < 19) goto _L2; else goto _L1
_L1:
        boolean flag = listview.canScrollList(i);
_L4:
        return flag;
_L2:
        int k;
        k = listview.getChildCount();
        flag = flag1;
        if(k == 0) goto _L4; else goto _L3
_L3:
        int j;
        j = listview.getFirstVisiblePosition();
        if(i <= 0)
            break MISSING_BLOCK_LABEL_83;
        i = listview.getChildAt(k - 1).getBottom();
        if(j + k < listview.getCount())
            break; /* Loop/switch isn't completed */
        flag = flag1;
        if(i <= listview.getHeight() - listview.getListPaddingBottom()) goto _L4; else goto _L5
_L5:
        return true;
        i = listview.getChildAt(0).getTop();
        if(j > 0)
            break; /* Loop/switch isn't completed */
        flag = flag1;
        if(i >= listview.getListPaddingTop()) goto _L4; else goto _L6
_L6:
        return true;
    }

    public static void scrollListBy(ListView listview, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
        {
            listview.scrollListBy(i);
        } else
        {
            int j = listview.getFirstVisiblePosition();
            if(j != -1)
            {
                View view = listview.getChildAt(0);
                if(view != null)
                {
                    listview.setSelectionFromTop(j, view.getTop() - i);
                    return;
                }
            }
        }
    }
}
