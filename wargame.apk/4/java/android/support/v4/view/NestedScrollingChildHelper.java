// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;

// Referenced classes of package android.support.v4.view:
//            ViewParentCompat, ViewCompat

public class NestedScrollingChildHelper
{

    public NestedScrollingChildHelper(View view)
    {
        mView = view;
    }

    private ViewParent getNestedScrollingParentForType(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 0: // '\0'
            return mNestedScrollingParentTouch;

        case 1: // '\001'
            return mNestedScrollingParentNonTouch;
        }
    }

    private void setNestedScrollingParentForType(int i, ViewParent viewparent)
    {
        switch(i)
        {
        default:
            return;

        case 0: // '\0'
            mNestedScrollingParentTouch = viewparent;
            return;

        case 1: // '\001'
            mNestedScrollingParentNonTouch = viewparent;
            break;
        }
    }

    public boolean dispatchNestedFling(float f, float f1, boolean flag)
    {
        boolean flag2 = false;
        boolean flag1 = flag2;
        if(isNestedScrollingEnabled())
        {
            ViewParent viewparent = getNestedScrollingParentForType(0);
            flag1 = flag2;
            if(viewparent != null)
                flag1 = ViewParentCompat.onNestedFling(viewparent, mView, f, f1, flag);
        }
        return flag1;
    }

    public boolean dispatchNestedPreFling(float f, float f1)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if(isNestedScrollingEnabled())
        {
            ViewParent viewparent = getNestedScrollingParentForType(0);
            flag = flag1;
            if(viewparent != null)
                flag = ViewParentCompat.onNestedPreFling(viewparent, mView, f, f1);
        }
        return flag;
    }

    public boolean dispatchNestedPreScroll(int i, int j, int ai[], int ai1[])
    {
        return dispatchNestedPreScroll(i, j, ai, ai1, 0);
    }

    public boolean dispatchNestedPreScroll(int i, int j, int ai[], int ai1[], int k)
    {
        if(isNestedScrollingEnabled())
        {
            ViewParent viewparent = getNestedScrollingParentForType(k);
            if(viewparent != null)
            {
                if(i != 0 || j != 0)
                {
                    int l = 0;
                    int i1 = 0;
                    if(ai1 != null)
                    {
                        mView.getLocationInWindow(ai1);
                        l = ai1[0];
                        i1 = ai1[1];
                    }
                    int ai2[] = ai;
                    if(ai == null)
                    {
                        if(mTempNestedScrollConsumed == null)
                            mTempNestedScrollConsumed = new int[2];
                        ai2 = mTempNestedScrollConsumed;
                    }
                    ai2[0] = 0;
                    ai2[1] = 0;
                    ViewParentCompat.onNestedPreScroll(viewparent, mView, i, j, ai2, k);
                    if(ai1 != null)
                    {
                        mView.getLocationInWindow(ai1);
                        ai1[0] = ai1[0] - l;
                        ai1[1] = ai1[1] - i1;
                    }
                    boolean flag;
                    if(ai2[0] != 0 || ai2[1] != 0)
                        flag = true;
                    else
                        flag = false;
                    return flag;
                }
                if(ai1 != null)
                {
                    ai1[0] = 0;
                    ai1[1] = 0;
                    return false;
                }
            }
        }
        return false;
    }

    public boolean dispatchNestedScroll(int i, int j, int k, int l, int ai[])
    {
        return dispatchNestedScroll(i, j, k, l, ai, 0);
    }

    public boolean dispatchNestedScroll(int i, int j, int k, int l, int ai[], int i1)
    {
        if(isNestedScrollingEnabled())
        {
            ViewParent viewparent = getNestedScrollingParentForType(i1);
            if(viewparent == null)
                return false;
            if(i != 0 || j != 0 || k != 0 || l != 0)
            {
                int j1 = 0;
                int k1 = 0;
                if(ai != null)
                {
                    mView.getLocationInWindow(ai);
                    j1 = ai[0];
                    k1 = ai[1];
                }
                ViewParentCompat.onNestedScroll(viewparent, mView, i, j, k, l, i1);
                if(ai != null)
                {
                    mView.getLocationInWindow(ai);
                    ai[0] = ai[0] - j1;
                    ai[1] = ai[1] - k1;
                }
                return true;
            }
            if(ai != null)
            {
                ai[0] = 0;
                ai[1] = 0;
            }
        }
        return false;
    }

    public boolean hasNestedScrollingParent()
    {
        return hasNestedScrollingParent(0);
    }

    public boolean hasNestedScrollingParent(int i)
    {
        return getNestedScrollingParentForType(i) != null;
    }

    public boolean isNestedScrollingEnabled()
    {
        return mIsNestedScrollingEnabled;
    }

    public void onDetachedFromWindow()
    {
        ViewCompat.stopNestedScroll(mView);
    }

    public void onStopNestedScroll(View view)
    {
        ViewCompat.stopNestedScroll(mView);
    }

    public void setNestedScrollingEnabled(boolean flag)
    {
        if(mIsNestedScrollingEnabled)
            ViewCompat.stopNestedScroll(mView);
        mIsNestedScrollingEnabled = flag;
    }

    public boolean startNestedScroll(int i)
    {
        return startNestedScroll(i, 0);
    }

    public boolean startNestedScroll(int i, int j)
    {
        if(hasNestedScrollingParent(j))
            return true;
        if(isNestedScrollingEnabled())
        {
            ViewParent viewparent = mView.getParent();
            View view = mView;
            for(; viewparent != null; viewparent = viewparent.getParent())
            {
                if(ViewParentCompat.onStartNestedScroll(viewparent, view, mView, i, j))
                {
                    setNestedScrollingParentForType(j, viewparent);
                    ViewParentCompat.onNestedScrollAccepted(viewparent, view, mView, i, j);
                    return true;
                }
                if(viewparent instanceof View)
                    view = (View)viewparent;
            }

        }
        return false;
    }

    public void stopNestedScroll()
    {
        stopNestedScroll(0);
    }

    public void stopNestedScroll(int i)
    {
        ViewParent viewparent = getNestedScrollingParentForType(i);
        if(viewparent != null)
        {
            ViewParentCompat.onStopNestedScroll(viewparent, mView, i);
            setNestedScrollingParentForType(i, null);
        }
    }

    private boolean mIsNestedScrollingEnabled;
    private ViewParent mNestedScrollingParentNonTouch;
    private ViewParent mNestedScrollingParentTouch;
    private int mTempNestedScrollConsumed[];
    private final View mView;
}
