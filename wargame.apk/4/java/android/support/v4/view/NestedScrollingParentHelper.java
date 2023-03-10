// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;

public class NestedScrollingParentHelper
{

    public NestedScrollingParentHelper(ViewGroup viewgroup)
    {
        mViewGroup = viewgroup;
    }

    public int getNestedScrollAxes()
    {
        return mNestedScrollAxes;
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
        onNestedScrollAccepted(view, view1, i, 0);
    }

    public void onNestedScrollAccepted(View view, View view1, int i, int j)
    {
        mNestedScrollAxes = i;
    }

    public void onStopNestedScroll(View view)
    {
        onStopNestedScroll(view, 0);
    }

    public void onStopNestedScroll(View view, int i)
    {
        mNestedScrollAxes = 0;
    }

    private int mNestedScrollAxes;
    private final ViewGroup mViewGroup;
}
