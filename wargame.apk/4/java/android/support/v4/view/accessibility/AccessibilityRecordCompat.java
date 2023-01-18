// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeInfoCompat

public class AccessibilityRecordCompat
{
    static class AccessibilityRecordCompatApi15Impl extends AccessibilityRecordCompatBaseImpl
    {

        public int getMaxScrollX(AccessibilityRecord accessibilityrecord)
        {
            return accessibilityrecord.getMaxScrollX();
        }

        public int getMaxScrollY(AccessibilityRecord accessibilityrecord)
        {
            return accessibilityrecord.getMaxScrollY();
        }

        public void setMaxScrollX(AccessibilityRecord accessibilityrecord, int i)
        {
            accessibilityrecord.setMaxScrollX(i);
        }

        public void setMaxScrollY(AccessibilityRecord accessibilityrecord, int i)
        {
            accessibilityrecord.setMaxScrollY(i);
        }

        AccessibilityRecordCompatApi15Impl()
        {
        }
    }

    static class AccessibilityRecordCompatApi16Impl extends AccessibilityRecordCompatApi15Impl
    {

        public void setSource(AccessibilityRecord accessibilityrecord, View view, int i)
        {
            accessibilityrecord.setSource(view, i);
        }

        AccessibilityRecordCompatApi16Impl()
        {
        }
    }

    static class AccessibilityRecordCompatBaseImpl
    {

        public int getMaxScrollX(AccessibilityRecord accessibilityrecord)
        {
            return 0;
        }

        public int getMaxScrollY(AccessibilityRecord accessibilityrecord)
        {
            return 0;
        }

        public void setMaxScrollX(AccessibilityRecord accessibilityrecord, int i)
        {
        }

        public void setMaxScrollY(AccessibilityRecord accessibilityrecord, int i)
        {
        }

        public void setSource(AccessibilityRecord accessibilityrecord, View view, int i)
        {
        }

        AccessibilityRecordCompatBaseImpl()
        {
        }
    }


    public AccessibilityRecordCompat(Object obj)
    {
        mRecord = (AccessibilityRecord)obj;
    }

    public static int getMaxScrollX(AccessibilityRecord accessibilityrecord)
    {
        return IMPL.getMaxScrollX(accessibilityrecord);
    }

    public static int getMaxScrollY(AccessibilityRecord accessibilityrecord)
    {
        return IMPL.getMaxScrollY(accessibilityrecord);
    }

    public static AccessibilityRecordCompat obtain()
    {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain());
    }

    public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat accessibilityrecordcompat)
    {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain(accessibilityrecordcompat.mRecord));
    }

    public static void setMaxScrollX(AccessibilityRecord accessibilityrecord, int i)
    {
        IMPL.setMaxScrollX(accessibilityrecord, i);
    }

    public static void setMaxScrollY(AccessibilityRecord accessibilityrecord, int i)
    {
        IMPL.setMaxScrollY(accessibilityrecord, i);
    }

    public static void setSource(AccessibilityRecord accessibilityrecord, View view, int i)
    {
        IMPL.setSource(accessibilityrecord, view, i);
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
        obj = (AccessibilityRecordCompat)obj;
        if(mRecord != null)
            continue; /* Loop/switch isn't completed */
        if(((AccessibilityRecordCompat) (obj)).mRecord == null) goto _L1; else goto _L3
_L3:
        return false;
        if(mRecord.equals(((AccessibilityRecordCompat) (obj)).mRecord)) goto _L1; else goto _L4
_L4:
        return false;
    }

    public int getAddedCount()
    {
        return mRecord.getAddedCount();
    }

    public CharSequence getBeforeText()
    {
        return mRecord.getBeforeText();
    }

    public CharSequence getClassName()
    {
        return mRecord.getClassName();
    }

    public CharSequence getContentDescription()
    {
        return mRecord.getContentDescription();
    }

    public int getCurrentItemIndex()
    {
        return mRecord.getCurrentItemIndex();
    }

    public int getFromIndex()
    {
        return mRecord.getFromIndex();
    }

    public Object getImpl()
    {
        return mRecord;
    }

    public int getItemCount()
    {
        return mRecord.getItemCount();
    }

    public int getMaxScrollX()
    {
        return getMaxScrollX(mRecord);
    }

    public int getMaxScrollY()
    {
        return getMaxScrollY(mRecord);
    }

    public Parcelable getParcelableData()
    {
        return mRecord.getParcelableData();
    }

    public int getRemovedCount()
    {
        return mRecord.getRemovedCount();
    }

    public int getScrollX()
    {
        return mRecord.getScrollX();
    }

    public int getScrollY()
    {
        return mRecord.getScrollY();
    }

    public AccessibilityNodeInfoCompat getSource()
    {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(mRecord.getSource());
    }

    public List getText()
    {
        return mRecord.getText();
    }

    public int getToIndex()
    {
        return mRecord.getToIndex();
    }

    public int getWindowId()
    {
        return mRecord.getWindowId();
    }

    public int hashCode()
    {
        if(mRecord == null)
            return 0;
        else
            return mRecord.hashCode();
    }

    public boolean isChecked()
    {
        return mRecord.isChecked();
    }

    public boolean isEnabled()
    {
        return mRecord.isEnabled();
    }

    public boolean isFullScreen()
    {
        return mRecord.isFullScreen();
    }

    public boolean isPassword()
    {
        return mRecord.isPassword();
    }

    public boolean isScrollable()
    {
        return mRecord.isScrollable();
    }

    public void recycle()
    {
        mRecord.recycle();
    }

    public void setAddedCount(int i)
    {
        mRecord.setAddedCount(i);
    }

    public void setBeforeText(CharSequence charsequence)
    {
        mRecord.setBeforeText(charsequence);
    }

    public void setChecked(boolean flag)
    {
        mRecord.setChecked(flag);
    }

    public void setClassName(CharSequence charsequence)
    {
        mRecord.setClassName(charsequence);
    }

    public void setContentDescription(CharSequence charsequence)
    {
        mRecord.setContentDescription(charsequence);
    }

    public void setCurrentItemIndex(int i)
    {
        mRecord.setCurrentItemIndex(i);
    }

    public void setEnabled(boolean flag)
    {
        mRecord.setEnabled(flag);
    }

    public void setFromIndex(int i)
    {
        mRecord.setFromIndex(i);
    }

    public void setFullScreen(boolean flag)
    {
        mRecord.setFullScreen(flag);
    }

    public void setItemCount(int i)
    {
        mRecord.setItemCount(i);
    }

    public void setMaxScrollX(int i)
    {
        setMaxScrollX(mRecord, i);
    }

    public void setMaxScrollY(int i)
    {
        setMaxScrollY(mRecord, i);
    }

    public void setParcelableData(Parcelable parcelable)
    {
        mRecord.setParcelableData(parcelable);
    }

    public void setPassword(boolean flag)
    {
        mRecord.setPassword(flag);
    }

    public void setRemovedCount(int i)
    {
        mRecord.setRemovedCount(i);
    }

    public void setScrollX(int i)
    {
        mRecord.setScrollX(i);
    }

    public void setScrollY(int i)
    {
        mRecord.setScrollY(i);
    }

    public void setScrollable(boolean flag)
    {
        mRecord.setScrollable(flag);
    }

    public void setSource(View view)
    {
        mRecord.setSource(view);
    }

    public void setSource(View view, int i)
    {
        setSource(mRecord, view, i);
    }

    public void setToIndex(int i)
    {
        mRecord.setToIndex(i);
    }

    private static final AccessibilityRecordCompatBaseImpl IMPL;
    private final AccessibilityRecord mRecord;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new AccessibilityRecordCompatApi16Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 15)
            IMPL = new AccessibilityRecordCompatApi15Impl();
        else
            IMPL = new AccessibilityRecordCompatBaseImpl();
    }
}
