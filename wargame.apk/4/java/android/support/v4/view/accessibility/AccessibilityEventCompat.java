// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityRecordCompat

public final class AccessibilityEventCompat
{
    static class AccessibilityEventCompatApi16Impl extends AccessibilityEventCompatBaseImpl
    {

        public int getAction(AccessibilityEvent accessibilityevent)
        {
            return accessibilityevent.getAction();
        }

        public int getMovementGranularity(AccessibilityEvent accessibilityevent)
        {
            return accessibilityevent.getMovementGranularity();
        }

        public void setAction(AccessibilityEvent accessibilityevent, int i)
        {
            accessibilityevent.setAction(i);
        }

        public void setMovementGranularity(AccessibilityEvent accessibilityevent, int i)
        {
            accessibilityevent.setMovementGranularity(i);
        }

        AccessibilityEventCompatApi16Impl()
        {
        }
    }

    static class AccessibilityEventCompatApi19Impl extends AccessibilityEventCompatApi16Impl
    {

        public int getContentChangeTypes(AccessibilityEvent accessibilityevent)
        {
            return accessibilityevent.getContentChangeTypes();
        }

        public void setContentChangeTypes(AccessibilityEvent accessibilityevent, int i)
        {
            accessibilityevent.setContentChangeTypes(i);
        }

        AccessibilityEventCompatApi19Impl()
        {
        }
    }

    static class AccessibilityEventCompatBaseImpl
    {

        public int getAction(AccessibilityEvent accessibilityevent)
        {
            return 0;
        }

        public int getContentChangeTypes(AccessibilityEvent accessibilityevent)
        {
            return 0;
        }

        public int getMovementGranularity(AccessibilityEvent accessibilityevent)
        {
            return 0;
        }

        public void setAction(AccessibilityEvent accessibilityevent, int i)
        {
        }

        public void setContentChangeTypes(AccessibilityEvent accessibilityevent, int i)
        {
        }

        public void setMovementGranularity(AccessibilityEvent accessibilityevent, int i)
        {
        }

        AccessibilityEventCompatBaseImpl()
        {
        }
    }


    private AccessibilityEventCompat()
    {
    }

    public static void appendRecord(AccessibilityEvent accessibilityevent, AccessibilityRecordCompat accessibilityrecordcompat)
    {
        accessibilityevent.appendRecord((AccessibilityRecord)accessibilityrecordcompat.getImpl());
    }

    public static AccessibilityRecordCompat asRecord(AccessibilityEvent accessibilityevent)
    {
        return new AccessibilityRecordCompat(accessibilityevent);
    }

    public static int getContentChangeTypes(AccessibilityEvent accessibilityevent)
    {
        return IMPL.getContentChangeTypes(accessibilityevent);
    }

    public static AccessibilityRecordCompat getRecord(AccessibilityEvent accessibilityevent, int i)
    {
        return new AccessibilityRecordCompat(accessibilityevent.getRecord(i));
    }

    public static int getRecordCount(AccessibilityEvent accessibilityevent)
    {
        return accessibilityevent.getRecordCount();
    }

    public static void setContentChangeTypes(AccessibilityEvent accessibilityevent, int i)
    {
        IMPL.setContentChangeTypes(accessibilityevent, i);
    }

    public int getAction(AccessibilityEvent accessibilityevent)
    {
        return IMPL.getAction(accessibilityevent);
    }

    public int getMovementGranularity(AccessibilityEvent accessibilityevent)
    {
        return IMPL.getMovementGranularity(accessibilityevent);
    }

    public void setAction(AccessibilityEvent accessibilityevent, int i)
    {
        IMPL.setAction(accessibilityevent, i);
    }

    public void setMovementGranularity(AccessibilityEvent accessibilityevent, int i)
    {
        IMPL.setMovementGranularity(accessibilityevent, i);
    }

    public static final int CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION = 4;
    public static final int CONTENT_CHANGE_TYPE_SUBTREE = 1;
    public static final int CONTENT_CHANGE_TYPE_TEXT = 2;
    public static final int CONTENT_CHANGE_TYPE_UNDEFINED = 0;
    private static final AccessibilityEventCompatBaseImpl IMPL;
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 16384;
    public static final int TYPE_ASSIST_READING_CONTEXT = 0x1000000;
    public static final int TYPE_GESTURE_DETECTION_END = 0x80000;
    public static final int TYPE_GESTURE_DETECTION_START = 0x40000;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 512;
    public static final int TYPE_TOUCH_INTERACTION_END = 0x200000;
    public static final int TYPE_TOUCH_INTERACTION_START = 0x100000;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 0x10000;
    public static final int TYPE_VIEW_CONTEXT_CLICKED = 0x800000;
    public static final int TYPE_VIEW_HOVER_ENTER = 128;
    public static final int TYPE_VIEW_HOVER_EXIT = 256;
    public static final int TYPE_VIEW_SCROLLED = 4096;
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 0x20000;
    public static final int TYPE_WINDOWS_CHANGED = 0x400000;
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 2048;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            IMPL = new AccessibilityEventCompatApi19Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new AccessibilityEventCompatApi16Impl();
        else
            IMPL = new AccessibilityEventCompatBaseImpl();
    }
}
