// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityWindowInfo;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeInfoCompat

public class AccessibilityWindowInfoCompat
{

    private AccessibilityWindowInfoCompat(Object obj)
    {
        mInfo = obj;
    }

    public static AccessibilityWindowInfoCompat obtain()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return wrapNonNullInstance(AccessibilityWindowInfo.obtain());
        else
            return null;
    }

    public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat accessibilitywindowinfocompat)
    {
        if(android.os.Build.VERSION.SDK_INT < 21 || accessibilitywindowinfocompat == null)
            return null;
        else
            return wrapNonNullInstance(AccessibilityWindowInfo.obtain((AccessibilityWindowInfo)accessibilitywindowinfocompat.mInfo));
    }

    private static String typeToString(int i)
    {
        switch(i)
        {
        default:
            return "<UNKNOWN>";

        case 1: // '\001'
            return "TYPE_APPLICATION";

        case 2: // '\002'
            return "TYPE_INPUT_METHOD";

        case 3: // '\003'
            return "TYPE_SYSTEM";

        case 4: // '\004'
            return "TYPE_ACCESSIBILITY_OVERLAY";
        }
    }

    static AccessibilityWindowInfoCompat wrapNonNullInstance(Object obj)
    {
        if(obj != null)
            return new AccessibilityWindowInfoCompat(obj);
        else
            return null;
    }

    public boolean equals(Object obj)
    {
        if(this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (AccessibilityWindowInfoCompat)obj;
        if(mInfo != null)
            continue; /* Loop/switch isn't completed */
        if(((AccessibilityWindowInfoCompat) (obj)).mInfo == null) goto _L1; else goto _L3
_L3:
        return false;
        if(mInfo.equals(((AccessibilityWindowInfoCompat) (obj)).mInfo)) goto _L1; else goto _L4
_L4:
        return false;
    }

    public AccessibilityNodeInfoCompat getAnchor()
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)mInfo).getAnchor());
        else
            return null;
    }

    public void getBoundsInScreen(Rect rect)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            ((AccessibilityWindowInfo)mInfo).getBoundsInScreen(rect);
    }

    public AccessibilityWindowInfoCompat getChild(int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return wrapNonNullInstance(((AccessibilityWindowInfo)mInfo).getChild(i));
        else
            return null;
    }

    public int getChildCount()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((AccessibilityWindowInfo)mInfo).getChildCount();
        else
            return 0;
    }

    public int getId()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((AccessibilityWindowInfo)mInfo).getId();
        else
            return -1;
    }

    public int getLayer()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((AccessibilityWindowInfo)mInfo).getLayer();
        else
            return -1;
    }

    public AccessibilityWindowInfoCompat getParent()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return wrapNonNullInstance(((AccessibilityWindowInfo)mInfo).getParent());
        else
            return null;
    }

    public AccessibilityNodeInfoCompat getRoot()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo)mInfo).getRoot());
        else
            return null;
    }

    public CharSequence getTitle()
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return ((AccessibilityWindowInfo)mInfo).getTitle();
        else
            return null;
    }

    public int getType()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((AccessibilityWindowInfo)mInfo).getType();
        else
            return -1;
    }

    public int hashCode()
    {
        if(mInfo == null)
            return 0;
        else
            return mInfo.hashCode();
    }

    public boolean isAccessibilityFocused()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((AccessibilityWindowInfo)mInfo).isAccessibilityFocused();
        else
            return true;
    }

    public boolean isActive()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((AccessibilityWindowInfo)mInfo).isActive();
        else
            return true;
    }

    public boolean isFocused()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ((AccessibilityWindowInfo)mInfo).isFocused();
        else
            return true;
    }

    public void recycle()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            ((AccessibilityWindowInfo)mInfo).recycle();
    }

    public String toString()
    {
        boolean flag1 = true;
        StringBuilder stringbuilder = new StringBuilder();
        Object obj = new Rect();
        getBoundsInScreen(((Rect) (obj)));
        stringbuilder.append("AccessibilityWindowInfo[");
        stringbuilder.append("id=").append(getId());
        stringbuilder.append(", type=").append(typeToString(getType()));
        stringbuilder.append(", layer=").append(getLayer());
        stringbuilder.append(", bounds=").append(obj);
        stringbuilder.append(", focused=").append(isFocused());
        stringbuilder.append(", active=").append(isActive());
        obj = stringbuilder.append(", hasParent=");
        boolean flag;
        if(getParent() != null)
            flag = true;
        else
            flag = false;
        ((StringBuilder) (obj)).append(flag);
        obj = stringbuilder.append(", hasChildren=");
        if(getChildCount() > 0)
            flag = flag1;
        else
            flag = false;
        ((StringBuilder) (obj)).append(flag);
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    private static final int UNDEFINED = -1;
    private Object mInfo;
}
