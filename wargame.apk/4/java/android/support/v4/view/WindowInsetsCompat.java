// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.graphics.Rect;
import android.view.WindowInsets;

public class WindowInsetsCompat
{

    public WindowInsetsCompat(WindowInsetsCompat windowinsetscompat)
    {
        Object obj = null;
        super();
        if(android.os.Build.VERSION.SDK_INT >= 20)
        {
            if(windowinsetscompat == null)
                windowinsetscompat = obj;
            else
                windowinsetscompat = new WindowInsets((WindowInsets)windowinsetscompat.mInsets);
            mInsets = windowinsetscompat;
            return;
        } else
        {
            mInsets = null;
            return;
        }
    }

    private WindowInsetsCompat(Object obj)
    {
        mInsets = obj;
    }

    static Object unwrap(WindowInsetsCompat windowinsetscompat)
    {
        if(windowinsetscompat == null)
            return null;
        else
            return windowinsetscompat.mInsets;
    }

    static WindowInsetsCompat wrap(Object obj)
    {
        if(obj == null)
            return null;
        else
            return new WindowInsetsCompat(obj);
    }

    public WindowInsetsCompat consumeStableInsets()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return new WindowInsetsCompat(((WindowInsets)mInsets).consumeStableInsets());
        else
            return null;
    }

    public WindowInsetsCompat consumeSystemWindowInsets()
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            return new WindowInsetsCompat(((WindowInsets)mInsets).consumeSystemWindowInsets());
        else
            return null;
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (WindowInsetsCompat)obj;
            if(mInsets == null)
            {
                if(((WindowInsetsCompat) (obj)).mInsets != null)
                    return false;
            } else
            {
                return mInsets.equals(((WindowInsetsCompat) (obj)).mInsets);
            }
        }
        return true;
    }

    public int getStableInsetBottom()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((WindowInsets)mInsets).getStableInsetBottom();
        else
            return 0;
    }

    public int getStableInsetLeft()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((WindowInsets)mInsets).getStableInsetLeft();
        else
            return 0;
    }

    public int getStableInsetRight()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((WindowInsets)mInsets).getStableInsetRight();
        else
            return 0;
    }

    public int getStableInsetTop()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((WindowInsets)mInsets).getStableInsetTop();
        else
            return 0;
    }

    public int getSystemWindowInsetBottom()
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            return ((WindowInsets)mInsets).getSystemWindowInsetBottom();
        else
            return 0;
    }

    public int getSystemWindowInsetLeft()
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            return ((WindowInsets)mInsets).getSystemWindowInsetLeft();
        else
            return 0;
    }

    public int getSystemWindowInsetRight()
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            return ((WindowInsets)mInsets).getSystemWindowInsetRight();
        else
            return 0;
    }

    public int getSystemWindowInsetTop()
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            return ((WindowInsets)mInsets).getSystemWindowInsetTop();
        else
            return 0;
    }

    public boolean hasInsets()
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            return ((WindowInsets)mInsets).hasInsets();
        else
            return false;
    }

    public boolean hasStableInsets()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((WindowInsets)mInsets).hasStableInsets();
        else
            return false;
    }

    public boolean hasSystemWindowInsets()
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            return ((WindowInsets)mInsets).hasSystemWindowInsets();
        else
            return false;
    }

    public int hashCode()
    {
        if(mInsets == null)
            return 0;
        else
            return mInsets.hashCode();
    }

    public boolean isConsumed()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((WindowInsets)mInsets).isConsumed();
        else
            return false;
    }

    public boolean isRound()
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            return ((WindowInsets)mInsets).isRound();
        else
            return false;
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int i, int j, int k, int l)
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            return new WindowInsetsCompat(((WindowInsets)mInsets).replaceSystemWindowInsets(i, j, k, l));
        else
            return null;
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return new WindowInsetsCompat(((WindowInsets)mInsets).replaceSystemWindowInsets(rect));
        else
            return null;
    }

    private final Object mInsets;
}
