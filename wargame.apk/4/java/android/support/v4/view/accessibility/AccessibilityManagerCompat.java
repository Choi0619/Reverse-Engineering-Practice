// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager;
import java.util.List;

public final class AccessibilityManagerCompat
{
    public static interface AccessibilityStateChangeListener
    {

        public abstract void onAccessibilityStateChanged(boolean flag);
    }

    public static abstract class AccessibilityStateChangeListenerCompat
        implements AccessibilityStateChangeListener
    {

        public AccessibilityStateChangeListenerCompat()
        {
        }
    }

    private static class AccessibilityStateChangeListenerWrapper
        implements android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    {

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
            {
                return false;
            } else
            {
                obj = (AccessibilityStateChangeListenerWrapper)obj;
                return mListener.equals(((AccessibilityStateChangeListenerWrapper) (obj)).mListener);
            }
        }

        public int hashCode()
        {
            return mListener.hashCode();
        }

        public void onAccessibilityStateChanged(boolean flag)
        {
            mListener.onAccessibilityStateChanged(flag);
        }

        AccessibilityStateChangeListener mListener;

        AccessibilityStateChangeListenerWrapper(AccessibilityStateChangeListener accessibilitystatechangelistener)
        {
            mListener = accessibilitystatechangelistener;
        }
    }

    public static interface TouchExplorationStateChangeListener
    {

        public abstract void onTouchExplorationStateChanged(boolean flag);
    }

    private static class TouchExplorationStateChangeListenerWrapper
        implements android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    {

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
            {
                return false;
            } else
            {
                obj = (TouchExplorationStateChangeListenerWrapper)obj;
                return mListener.equals(((TouchExplorationStateChangeListenerWrapper) (obj)).mListener);
            }
        }

        public int hashCode()
        {
            return mListener.hashCode();
        }

        public void onTouchExplorationStateChanged(boolean flag)
        {
            mListener.onTouchExplorationStateChanged(flag);
        }

        final TouchExplorationStateChangeListener mListener;

        TouchExplorationStateChangeListenerWrapper(TouchExplorationStateChangeListener touchexplorationstatechangelistener)
        {
            mListener = touchexplorationstatechangelistener;
        }
    }


    private AccessibilityManagerCompat()
    {
    }

    public static boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListener accessibilitystatechangelistener)
    {
        if(accessibilitystatechangelistener == null)
            return false;
        else
            return accessibilitymanager.addAccessibilityStateChangeListener(new AccessibilityStateChangeListenerWrapper(accessibilitystatechangelistener));
    }

    public static boolean addTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, TouchExplorationStateChangeListener touchexplorationstatechangelistener)
    {
        if(android.os.Build.VERSION.SDK_INT < 19 || touchexplorationstatechangelistener == null)
            return false;
        else
            return accessibilitymanager.addTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(touchexplorationstatechangelistener));
    }

    public static List getEnabledAccessibilityServiceList(AccessibilityManager accessibilitymanager, int i)
    {
        return accessibilitymanager.getEnabledAccessibilityServiceList(i);
    }

    public static List getInstalledAccessibilityServiceList(AccessibilityManager accessibilitymanager)
    {
        return accessibilitymanager.getInstalledAccessibilityServiceList();
    }

    public static boolean isTouchExplorationEnabled(AccessibilityManager accessibilitymanager)
    {
        return accessibilitymanager.isTouchExplorationEnabled();
    }

    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListener accessibilitystatechangelistener)
    {
        if(accessibilitystatechangelistener == null)
            return false;
        else
            return accessibilitymanager.removeAccessibilityStateChangeListener(new AccessibilityStateChangeListenerWrapper(accessibilitystatechangelistener));
    }

    public static boolean removeTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, TouchExplorationStateChangeListener touchexplorationstatechangelistener)
    {
        if(android.os.Build.VERSION.SDK_INT < 19 || touchexplorationstatechangelistener == null)
            return false;
        else
            return accessibilitymanager.removeTouchExplorationStateChangeListener(new TouchExplorationStateChangeListenerWrapper(touchexplorationstatechangelistener));
    }
}
