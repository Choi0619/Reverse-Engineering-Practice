// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;


class AccessibilityNodeInfoCompatKitKat
{
    static class RangeInfo
    {

        static float getCurrent(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo)obj).getCurrent();
        }

        static float getMax(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo)obj).getMax();
        }

        static float getMin(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo)obj).getMin();
        }

        static int getType(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo)obj).getType();
        }

        RangeInfo()
        {
        }
    }


    AccessibilityNodeInfoCompatKitKat()
    {
    }
}
