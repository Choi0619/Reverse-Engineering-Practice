// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.*;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityWindowInfoCompat

public class AccessibilityNodeInfoCompat
{
    public static class AccessibilityActionCompat
    {

        public int getId()
        {
            return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionId(mAction);
        }

        public CharSequence getLabel()
        {
            return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionLabel(mAction);
        }

        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(64, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(128, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
        public static final AccessibilityActionCompat ACTION_CLICK = new AccessibilityActionCompat(16, null);
        public static final AccessibilityActionCompat ACTION_COLLAPSE = new AccessibilityActionCompat(0x80000, null);
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK;
        public static final AccessibilityActionCompat ACTION_COPY = new AccessibilityActionCompat(16384, null);
        public static final AccessibilityActionCompat ACTION_CUT = new AccessibilityActionCompat(0x10000, null);
        public static final AccessibilityActionCompat ACTION_DISMISS = new AccessibilityActionCompat(0x100000, null);
        public static final AccessibilityActionCompat ACTION_EXPAND = new AccessibilityActionCompat(0x40000, null);
        public static final AccessibilityActionCompat ACTION_FOCUS = new AccessibilityActionCompat(1, null);
        public static final AccessibilityActionCompat ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(256, null);
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(1024, null);
        public static final AccessibilityActionCompat ACTION_PASTE = new AccessibilityActionCompat(32768, null);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(512, null);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(2048, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN;
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT;
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT;
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION;
        public static final AccessibilityActionCompat ACTION_SCROLL_UP;
        public static final AccessibilityActionCompat ACTION_SELECT = new AccessibilityActionCompat(4, null);
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS;
        public static final AccessibilityActionCompat ACTION_SET_SELECTION = new AccessibilityActionCompat(0x20000, null);
        public static final AccessibilityActionCompat ACTION_SET_TEXT = new AccessibilityActionCompat(0x200000, null);
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN;
        final Object mAction;

        static 
        {
            ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionShowOnScreen());
            ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollToPosition());
            ACTION_SCROLL_UP = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollUp());
            ACTION_SCROLL_LEFT = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollLeft());
            ACTION_SCROLL_DOWN = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollDown());
            ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollRight());
            ACTION_CONTEXT_CLICK = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionContextClick());
            ACTION_SET_PROGRESS = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionSetProgress());
        }

        public AccessibilityActionCompat(int i, CharSequence charsequence)
        {
            this(AccessibilityNodeInfoCompat.IMPL.newAccessibilityAction(i, charsequence));
        }

        AccessibilityActionCompat(Object obj)
        {
            mAction = obj;
        }
    }

    static class AccessibilityNodeInfoApi16Impl extends AccessibilityNodeInfoBaseImpl
    {

        public void addChild(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
            accessibilitynodeinfo.addChild(view, i);
        }

        public Object findFocus(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
            return accessibilitynodeinfo.findFocus(i);
        }

        public Object focusSearch(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
            return accessibilitynodeinfo.focusSearch(i);
        }

        public int getMovementGranularities(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getMovementGranularities();
        }

        public boolean isAccessibilityFocused(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.isAccessibilityFocused();
        }

        public boolean isVisibleToUser(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.isVisibleToUser();
        }

        public AccessibilityNodeInfo obtain(View view, int i)
        {
            return AccessibilityNodeInfo.obtain(view, i);
        }

        public boolean performAction(AccessibilityNodeInfo accessibilitynodeinfo, int i, Bundle bundle)
        {
            return accessibilitynodeinfo.performAction(i, bundle);
        }

        public void setAccessibilityFocused(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
            accessibilitynodeinfo.setAccessibilityFocused(flag);
        }

        public void setMovementGranularities(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
            accessibilitynodeinfo.setMovementGranularities(i);
        }

        public void setParent(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
            accessibilitynodeinfo.setParent(view, i);
        }

        public void setSource(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
            accessibilitynodeinfo.setSource(view, i);
        }

        public void setVisibleToUser(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
            accessibilitynodeinfo.setVisibleToUser(flag);
        }

        AccessibilityNodeInfoApi16Impl()
        {
        }
    }

    static class AccessibilityNodeInfoApi17Impl extends AccessibilityNodeInfoApi16Impl
    {

        public Object getLabelFor(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getLabelFor();
        }

        public Object getLabeledBy(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getLabeledBy();
        }

        public void setLabelFor(AccessibilityNodeInfo accessibilitynodeinfo, View view)
        {
            accessibilitynodeinfo.setLabelFor(view);
        }

        public void setLabelFor(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
            accessibilitynodeinfo.setLabelFor(view, i);
        }

        public void setLabeledBy(AccessibilityNodeInfo accessibilitynodeinfo, View view)
        {
            accessibilitynodeinfo.setLabeledBy(view);
        }

        public void setLabeledBy(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
            accessibilitynodeinfo.setLabeledBy(view, i);
        }

        AccessibilityNodeInfoApi17Impl()
        {
        }
    }

    static class AccessibilityNodeInfoApi18Impl extends AccessibilityNodeInfoApi17Impl
    {

        public List findAccessibilityNodeInfosByViewId(AccessibilityNodeInfo accessibilitynodeinfo, String s)
        {
            return accessibilitynodeinfo.findAccessibilityNodeInfosByViewId(s);
        }

        public int getTextSelectionEnd(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getTextSelectionEnd();
        }

        public int getTextSelectionStart(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getTextSelectionStart();
        }

        public String getViewIdResourceName(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getViewIdResourceName();
        }

        public boolean isEditable(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.isEditable();
        }

        public boolean refresh(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.refresh();
        }

        public void setEditable(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
            accessibilitynodeinfo.setEditable(flag);
        }

        public void setTextSelection(AccessibilityNodeInfo accessibilitynodeinfo, int i, int j)
        {
            accessibilitynodeinfo.setTextSelection(i, j);
        }

        public void setViewIdResourceName(AccessibilityNodeInfo accessibilitynodeinfo, String s)
        {
            accessibilitynodeinfo.setViewIdResourceName(s);
        }

        AccessibilityNodeInfoApi18Impl()
        {
        }
    }

    static class AccessibilityNodeInfoApi19Impl extends AccessibilityNodeInfoApi18Impl
    {

        public boolean canOpenPopup(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.canOpenPopup();
        }

        public Object getCollectionInfo(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getCollectionInfo();
        }

        public int getCollectionInfoColumnCount(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)obj).getColumnCount();
        }

        public int getCollectionInfoRowCount(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)obj).getRowCount();
        }

        public int getCollectionItemColumnIndex(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).getColumnIndex();
        }

        public int getCollectionItemColumnSpan(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).getColumnSpan();
        }

        public Object getCollectionItemInfo(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getCollectionItemInfo();
        }

        public int getCollectionItemRowIndex(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).getRowIndex();
        }

        public int getCollectionItemRowSpan(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).getRowSpan();
        }

        public Bundle getExtras(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getExtras();
        }

        public int getInputType(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getInputType();
        }

        public int getLiveRegion(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getLiveRegion();
        }

        public Object getRangeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getRangeInfo();
        }

        public CharSequence getRoleDescription(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return getExtras(accessibilitynodeinfo).getCharSequence("AccessibilityNodeInfo.roleDescription");
        }

        public boolean isCollectionInfoHierarchical(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)obj).isHierarchical();
        }

        public boolean isCollectionItemHeading(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).isHeading();
        }

        public boolean isContentInvalid(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.isContentInvalid();
        }

        public boolean isDismissable(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.isDismissable();
        }

        public boolean isMultiLine(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.isMultiLine();
        }

        public Object obtainCollectionInfo(int i, int j, boolean flag)
        {
            return android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(i, j, flag);
        }

        public Object obtainCollectionInfo(int i, int j, boolean flag, int k)
        {
            return android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(i, j, flag);
        }

        public Object obtainCollectionItemInfo(int i, int j, int k, int l, boolean flag)
        {
            return android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.obtain(i, j, k, l, flag);
        }

        public Object obtainCollectionItemInfo(int i, int j, int k, int l, boolean flag, boolean flag1)
        {
            return android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.obtain(i, j, k, l, flag);
        }

        public Object obtainRangeInfo(int i, float f, float f1, float f2)
        {
            return android.view.accessibility.AccessibilityNodeInfo.RangeInfo.obtain(i, f, f1, f2);
        }

        public void setCanOpenPopup(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
            accessibilitynodeinfo.setCanOpenPopup(flag);
        }

        public void setCollectionInfo(AccessibilityNodeInfo accessibilitynodeinfo, Object obj)
        {
            accessibilitynodeinfo.setCollectionInfo((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)obj);
        }

        public void setCollectionItemInfo(AccessibilityNodeInfo accessibilitynodeinfo, Object obj)
        {
            accessibilitynodeinfo.setCollectionItemInfo((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj);
        }

        public void setContentInvalid(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
            accessibilitynodeinfo.setContentInvalid(flag);
        }

        public void setDismissable(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
            accessibilitynodeinfo.setDismissable(flag);
        }

        public void setInputType(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
            accessibilitynodeinfo.setInputType(i);
        }

        public void setLiveRegion(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
            accessibilitynodeinfo.setLiveRegion(i);
        }

        public void setMultiLine(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
            accessibilitynodeinfo.setMultiLine(flag);
        }

        public void setRangeInfo(AccessibilityNodeInfo accessibilitynodeinfo, Object obj)
        {
            accessibilitynodeinfo.setRangeInfo((android.view.accessibility.AccessibilityNodeInfo.RangeInfo)obj);
        }

        public void setRoleDescription(AccessibilityNodeInfo accessibilitynodeinfo, CharSequence charsequence)
        {
            getExtras(accessibilitynodeinfo).putCharSequence("AccessibilityNodeInfo.roleDescription", charsequence);
        }

        private static final String ROLE_DESCRIPTION_KEY = "AccessibilityNodeInfo.roleDescription";

        AccessibilityNodeInfoApi19Impl()
        {
        }
    }

    static class AccessibilityNodeInfoApi21Impl extends AccessibilityNodeInfoApi19Impl
    {

        public void addAction(AccessibilityNodeInfo accessibilitynodeinfo, Object obj)
        {
            accessibilitynodeinfo.addAction((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)obj);
        }

        public int getAccessibilityActionId(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)obj).getId();
        }

        public CharSequence getAccessibilityActionLabel(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)obj).getLabel();
        }

        public List getActionList(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return (List)accessibilitynodeinfo.getActionList();
        }

        public int getCollectionInfoSelectionMode(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)obj).getSelectionMode();
        }

        public CharSequence getError(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getError();
        }

        public int getMaxTextLength(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getMaxTextLength();
        }

        public Object getWindow(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getWindow();
        }

        public boolean isCollectionItemSelected(Object obj)
        {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)obj).isSelected();
        }

        public Object newAccessibilityAction(int i, CharSequence charsequence)
        {
            return new android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction(i, charsequence);
        }

        public Object obtainCollectionInfo(int i, int j, boolean flag, int k)
        {
            return android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(i, j, flag, k);
        }

        public Object obtainCollectionItemInfo(int i, int j, int k, int l, boolean flag, boolean flag1)
        {
            return android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.obtain(i, j, k, l, flag, flag1);
        }

        public boolean removeAction(AccessibilityNodeInfo accessibilitynodeinfo, Object obj)
        {
            return accessibilitynodeinfo.removeAction((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)obj);
        }

        public boolean removeChild(AccessibilityNodeInfo accessibilitynodeinfo, View view)
        {
            return accessibilitynodeinfo.removeChild(view);
        }

        public boolean removeChild(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
            return accessibilitynodeinfo.removeChild(view, i);
        }

        public void setError(AccessibilityNodeInfo accessibilitynodeinfo, CharSequence charsequence)
        {
            accessibilitynodeinfo.setError(charsequence);
        }

        public void setMaxTextLength(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
            accessibilitynodeinfo.setMaxTextLength(i);
        }

        AccessibilityNodeInfoApi21Impl()
        {
        }
    }

    static class AccessibilityNodeInfoApi22Impl extends AccessibilityNodeInfoApi21Impl
    {

        public Object getTraversalAfter(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getTraversalAfter();
        }

        public Object getTraversalBefore(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getTraversalBefore();
        }

        public void setTraversalAfter(AccessibilityNodeInfo accessibilitynodeinfo, View view)
        {
            accessibilitynodeinfo.setTraversalAfter(view);
        }

        public void setTraversalAfter(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
            accessibilitynodeinfo.setTraversalAfter(view, i);
        }

        public void setTraversalBefore(AccessibilityNodeInfo accessibilitynodeinfo, View view)
        {
            accessibilitynodeinfo.setTraversalBefore(view);
        }

        public void setTraversalBefore(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
            accessibilitynodeinfo.setTraversalBefore(view, i);
        }

        AccessibilityNodeInfoApi22Impl()
        {
        }
    }

    static class AccessibilityNodeInfoApi23Impl extends AccessibilityNodeInfoApi22Impl
    {

        public Object getActionContextClick()
        {
            return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK;
        }

        public Object getActionScrollDown()
        {
            return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN;
        }

        public Object getActionScrollLeft()
        {
            return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT;
        }

        public Object getActionScrollRight()
        {
            return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT;
        }

        public Object getActionScrollToPosition()
        {
            return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION;
        }

        public Object getActionScrollUp()
        {
            return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP;
        }

        public Object getActionShowOnScreen()
        {
            return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN;
        }

        public boolean isContextClickable(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.isContextClickable();
        }

        public void setContextClickable(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
            accessibilitynodeinfo.setContextClickable(flag);
        }

        AccessibilityNodeInfoApi23Impl()
        {
        }
    }

    static class AccessibilityNodeInfoApi24Impl extends AccessibilityNodeInfoApi23Impl
    {

        public Object getActionSetProgress()
        {
            return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
        }

        public int getDrawingOrder(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.getDrawingOrder();
        }

        public boolean isImportantForAccessibility(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return accessibilitynodeinfo.isImportantForAccessibility();
        }

        public void setDrawingOrder(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
            accessibilitynodeinfo.setDrawingOrder(i);
        }

        public void setImportantForAccessibility(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
            accessibilitynodeinfo.setImportantForAccessibility(flag);
        }

        AccessibilityNodeInfoApi24Impl()
        {
        }
    }

    static class AccessibilityNodeInfoBaseImpl
    {

        public void addAction(AccessibilityNodeInfo accessibilitynodeinfo, Object obj)
        {
        }

        public void addChild(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
        }

        public boolean canOpenPopup(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return false;
        }

        public List findAccessibilityNodeInfosByViewId(AccessibilityNodeInfo accessibilitynodeinfo, String s)
        {
            return Collections.emptyList();
        }

        public Object findFocus(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
            return null;
        }

        public Object focusSearch(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
            return null;
        }

        public int getAccessibilityActionId(Object obj)
        {
            return 0;
        }

        public CharSequence getAccessibilityActionLabel(Object obj)
        {
            return null;
        }

        public Object getActionContextClick()
        {
            return null;
        }

        public List getActionList(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public Object getActionScrollDown()
        {
            return null;
        }

        public Object getActionScrollLeft()
        {
            return null;
        }

        public Object getActionScrollRight()
        {
            return null;
        }

        public Object getActionScrollToPosition()
        {
            return null;
        }

        public Object getActionScrollUp()
        {
            return null;
        }

        public Object getActionSetProgress()
        {
            return null;
        }

        public Object getActionShowOnScreen()
        {
            return null;
        }

        public Object getCollectionInfo(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public int getCollectionInfoColumnCount(Object obj)
        {
            return 0;
        }

        public int getCollectionInfoRowCount(Object obj)
        {
            return 0;
        }

        public int getCollectionInfoSelectionMode(Object obj)
        {
            return 0;
        }

        public int getCollectionItemColumnIndex(Object obj)
        {
            return 0;
        }

        public int getCollectionItemColumnSpan(Object obj)
        {
            return 0;
        }

        public Object getCollectionItemInfo(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public int getCollectionItemRowIndex(Object obj)
        {
            return 0;
        }

        public int getCollectionItemRowSpan(Object obj)
        {
            return 0;
        }

        public int getDrawingOrder(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return 0;
        }

        public CharSequence getError(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public Bundle getExtras(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return new Bundle();
        }

        public int getInputType(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return 0;
        }

        public Object getLabelFor(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public Object getLabeledBy(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public int getLiveRegion(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return 0;
        }

        public int getMaxTextLength(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return -1;
        }

        public int getMovementGranularities(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return 0;
        }

        public Object getRangeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public CharSequence getRoleDescription(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public int getTextSelectionEnd(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return -1;
        }

        public int getTextSelectionStart(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return -1;
        }

        public Object getTraversalAfter(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public Object getTraversalBefore(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public String getViewIdResourceName(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public Object getWindow(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return null;
        }

        public boolean isAccessibilityFocused(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return false;
        }

        public boolean isCollectionInfoHierarchical(Object obj)
        {
            return false;
        }

        public boolean isCollectionItemHeading(Object obj)
        {
            return false;
        }

        public boolean isCollectionItemSelected(Object obj)
        {
            return false;
        }

        public boolean isContentInvalid(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return false;
        }

        public boolean isContextClickable(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return false;
        }

        public boolean isDismissable(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return false;
        }

        public boolean isEditable(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return false;
        }

        public boolean isImportantForAccessibility(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return true;
        }

        public boolean isMultiLine(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return false;
        }

        public boolean isVisibleToUser(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return false;
        }

        public Object newAccessibilityAction(int i, CharSequence charsequence)
        {
            return null;
        }

        public AccessibilityNodeInfo obtain(View view, int i)
        {
            return null;
        }

        public Object obtainCollectionInfo(int i, int j, boolean flag)
        {
            return null;
        }

        public Object obtainCollectionInfo(int i, int j, boolean flag, int k)
        {
            return null;
        }

        public Object obtainCollectionItemInfo(int i, int j, int k, int l, boolean flag)
        {
            return null;
        }

        public Object obtainCollectionItemInfo(int i, int j, int k, int l, boolean flag, boolean flag1)
        {
            return null;
        }

        public Object obtainRangeInfo(int i, float f, float f1, float f2)
        {
            return null;
        }

        public boolean performAction(AccessibilityNodeInfo accessibilitynodeinfo, int i, Bundle bundle)
        {
            return false;
        }

        public boolean refresh(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            return false;
        }

        public boolean removeAction(AccessibilityNodeInfo accessibilitynodeinfo, Object obj)
        {
            return false;
        }

        public boolean removeChild(AccessibilityNodeInfo accessibilitynodeinfo, View view)
        {
            return false;
        }

        public boolean removeChild(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
            return false;
        }

        public void setAccessibilityFocused(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
        }

        public void setCanOpenPopup(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
        }

        public void setCollectionInfo(AccessibilityNodeInfo accessibilitynodeinfo, Object obj)
        {
        }

        public void setCollectionItemInfo(AccessibilityNodeInfo accessibilitynodeinfo, Object obj)
        {
        }

        public void setContentInvalid(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
        }

        public void setContextClickable(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
        }

        public void setDismissable(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
        }

        public void setDrawingOrder(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
        }

        public void setEditable(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
        }

        public void setError(AccessibilityNodeInfo accessibilitynodeinfo, CharSequence charsequence)
        {
        }

        public void setImportantForAccessibility(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
        }

        public void setInputType(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
        }

        public void setLabelFor(AccessibilityNodeInfo accessibilitynodeinfo, View view)
        {
        }

        public void setLabelFor(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
        }

        public void setLabeledBy(AccessibilityNodeInfo accessibilitynodeinfo, View view)
        {
        }

        public void setLabeledBy(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
        }

        public void setLiveRegion(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
        }

        public void setMaxTextLength(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
        }

        public void setMovementGranularities(AccessibilityNodeInfo accessibilitynodeinfo, int i)
        {
        }

        public void setMultiLine(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
        }

        public void setParent(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
        }

        public void setRangeInfo(AccessibilityNodeInfo accessibilitynodeinfo, Object obj)
        {
        }

        public void setRoleDescription(AccessibilityNodeInfo accessibilitynodeinfo, CharSequence charsequence)
        {
        }

        public void setSource(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
        }

        public void setTextSelection(AccessibilityNodeInfo accessibilitynodeinfo, int i, int j)
        {
        }

        public void setTraversalAfter(AccessibilityNodeInfo accessibilitynodeinfo, View view)
        {
        }

        public void setTraversalAfter(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
        }

        public void setTraversalBefore(AccessibilityNodeInfo accessibilitynodeinfo, View view)
        {
        }

        public void setTraversalBefore(AccessibilityNodeInfo accessibilitynodeinfo, View view, int i)
        {
        }

        public void setViewIdResourceName(AccessibilityNodeInfo accessibilitynodeinfo, String s)
        {
        }

        public void setVisibleToUser(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
        }

        AccessibilityNodeInfoBaseImpl()
        {
        }
    }

    public static class CollectionInfoCompat
    {

        public static CollectionInfoCompat obtain(int i, int j, boolean flag)
        {
            return new CollectionInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(i, j, flag));
        }

        public static CollectionInfoCompat obtain(int i, int j, boolean flag, int k)
        {
            return new CollectionInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(i, j, flag, k));
        }

        public int getColumnCount()
        {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoColumnCount(mInfo);
        }

        public int getRowCount()
        {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoRowCount(mInfo);
        }

        public int getSelectionMode()
        {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoSelectionMode(mInfo);
        }

        public boolean isHierarchical()
        {
            return AccessibilityNodeInfoCompat.IMPL.isCollectionInfoHierarchical(mInfo);
        }

        public static final int SELECTION_MODE_MULTIPLE = 2;
        public static final int SELECTION_MODE_NONE = 0;
        public static final int SELECTION_MODE_SINGLE = 1;
        final Object mInfo;

        CollectionInfoCompat(Object obj)
        {
            mInfo = obj;
        }
    }

    public static class CollectionItemInfoCompat
    {

        public static CollectionItemInfoCompat obtain(int i, int j, int k, int l, boolean flag)
        {
            return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(i, j, k, l, flag));
        }

        public static CollectionItemInfoCompat obtain(int i, int j, int k, int l, boolean flag, boolean flag1)
        {
            return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(i, j, k, l, flag, flag1));
        }

        public int getColumnIndex()
        {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnIndex(mInfo);
        }

        public int getColumnSpan()
        {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnSpan(mInfo);
        }

        public int getRowIndex()
        {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowIndex(mInfo);
        }

        public int getRowSpan()
        {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowSpan(mInfo);
        }

        public boolean isHeading()
        {
            return AccessibilityNodeInfoCompat.IMPL.isCollectionItemHeading(mInfo);
        }

        public boolean isSelected()
        {
            return AccessibilityNodeInfoCompat.IMPL.isCollectionItemSelected(mInfo);
        }

        final Object mInfo;

        CollectionItemInfoCompat(Object obj)
        {
            mInfo = obj;
        }
    }

    public static class RangeInfoCompat
    {

        public static RangeInfoCompat obtain(int i, float f, float f1, float f2)
        {
            return new RangeInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainRangeInfo(i, f, f1, f2));
        }

        public float getCurrent()
        {
            return AccessibilityNodeInfoCompatKitKat.RangeInfo.getCurrent(mInfo);
        }

        public float getMax()
        {
            return AccessibilityNodeInfoCompatKitKat.RangeInfo.getMax(mInfo);
        }

        public float getMin()
        {
            return AccessibilityNodeInfoCompatKitKat.RangeInfo.getMin(mInfo);
        }

        public int getType()
        {
            return AccessibilityNodeInfoCompatKitKat.RangeInfo.getType(mInfo);
        }

        public static final int RANGE_TYPE_FLOAT = 1;
        public static final int RANGE_TYPE_INT = 0;
        public static final int RANGE_TYPE_PERCENT = 2;
        final Object mInfo;

        RangeInfoCompat(Object obj)
        {
            mInfo = obj;
        }
    }


    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        mParentVirtualDescendantId = -1;
        mInfo = accessibilitynodeinfo;
    }

    public AccessibilityNodeInfoCompat(Object obj)
    {
        mParentVirtualDescendantId = -1;
        mInfo = (AccessibilityNodeInfo)obj;
    }

    private static String getActionSymbolicName(int i)
    {
        switch(i)
        {
        default:
            return "ACTION_UNKNOWN";

        case 1: // '\001'
            return "ACTION_FOCUS";

        case 2: // '\002'
            return "ACTION_CLEAR_FOCUS";

        case 4: // '\004'
            return "ACTION_SELECT";

        case 8: // '\b'
            return "ACTION_CLEAR_SELECTION";

        case 16: // '\020'
            return "ACTION_CLICK";

        case 32: // ' '
            return "ACTION_LONG_CLICK";

        case 64: // '@'
            return "ACTION_ACCESSIBILITY_FOCUS";

        case 128: 
            return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";

        case 256: 
            return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";

        case 512: 
            return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";

        case 1024: 
            return "ACTION_NEXT_HTML_ELEMENT";

        case 2048: 
            return "ACTION_PREVIOUS_HTML_ELEMENT";

        case 4096: 
            return "ACTION_SCROLL_FORWARD";

        case 8192: 
            return "ACTION_SCROLL_BACKWARD";

        case 65536: 
            return "ACTION_CUT";

        case 16384: 
            return "ACTION_COPY";

        case 32768: 
            return "ACTION_PASTE";

        case 131072: 
            return "ACTION_SET_SELECTION";
        }
    }

    public static AccessibilityNodeInfoCompat obtain()
    {
        return wrap(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        return wrap(AccessibilityNodeInfo.obtain(accessibilitynodeinfocompat.mInfo));
    }

    public static AccessibilityNodeInfoCompat obtain(View view)
    {
        return wrap(AccessibilityNodeInfo.obtain(view));
    }

    public static AccessibilityNodeInfoCompat obtain(View view, int i)
    {
        return wrapNonNullInstance(IMPL.obtain(view, i));
    }

    public static AccessibilityNodeInfoCompat wrap(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        return new AccessibilityNodeInfoCompat(accessibilitynodeinfo);
    }

    static AccessibilityNodeInfoCompat wrapNonNullInstance(Object obj)
    {
        if(obj != null)
            return new AccessibilityNodeInfoCompat(obj);
        else
            return null;
    }

    public void addAction(int i)
    {
        mInfo.addAction(i);
    }

    public void addAction(AccessibilityActionCompat accessibilityactioncompat)
    {
        IMPL.addAction(mInfo, accessibilityactioncompat.mAction);
    }

    public void addChild(View view)
    {
        mInfo.addChild(view);
    }

    public void addChild(View view, int i)
    {
        IMPL.addChild(mInfo, view, i);
    }

    public boolean canOpenPopup()
    {
        return IMPL.canOpenPopup(mInfo);
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
        obj = (AccessibilityNodeInfoCompat)obj;
        if(mInfo != null)
            continue; /* Loop/switch isn't completed */
        if(((AccessibilityNodeInfoCompat) (obj)).mInfo == null) goto _L1; else goto _L3
_L3:
        return false;
        if(mInfo.equals(((AccessibilityNodeInfoCompat) (obj)).mInfo)) goto _L1; else goto _L4
_L4:
        return false;
    }

    public List findAccessibilityNodeInfosByText(String s)
    {
        ArrayList arraylist = new ArrayList();
        s = mInfo.findAccessibilityNodeInfosByText(s);
        int j = s.size();
        for(int i = 0; i < j; i++)
            arraylist.add(wrap((AccessibilityNodeInfo)s.get(i)));

        return arraylist;
    }

    public List findAccessibilityNodeInfosByViewId(String s)
    {
        s = IMPL.findAccessibilityNodeInfosByViewId(mInfo, s);
        if(s != null)
        {
            ArrayList arraylist = new ArrayList();
            Iterator iterator = s.iterator();
            do
            {
                s = arraylist;
                if(!iterator.hasNext())
                    break;
                arraylist.add(wrap((AccessibilityNodeInfo)iterator.next()));
            } while(true);
        } else
        {
            s = Collections.emptyList();
        }
        return s;
    }

    public AccessibilityNodeInfoCompat findFocus(int i)
    {
        return wrapNonNullInstance(IMPL.findFocus(mInfo, i));
    }

    public AccessibilityNodeInfoCompat focusSearch(int i)
    {
        return wrapNonNullInstance(IMPL.focusSearch(mInfo, i));
    }

    public List getActionList()
    {
        List list = IMPL.getActionList(mInfo);
        Object obj;
        if(list != null)
        {
            ArrayList arraylist = new ArrayList();
            int j = list.size();
            int i = 0;
            do
            {
                obj = arraylist;
                if(i >= j)
                    break;
                arraylist.add(new AccessibilityActionCompat(list.get(i)));
                i++;
            } while(true);
        } else
        {
            obj = Collections.emptyList();
        }
        return ((List) (obj));
    }

    public int getActions()
    {
        return mInfo.getActions();
    }

    public void getBoundsInParent(Rect rect)
    {
        mInfo.getBoundsInParent(rect);
    }

    public void getBoundsInScreen(Rect rect)
    {
        mInfo.getBoundsInScreen(rect);
    }

    public AccessibilityNodeInfoCompat getChild(int i)
    {
        return wrapNonNullInstance(mInfo.getChild(i));
    }

    public int getChildCount()
    {
        return mInfo.getChildCount();
    }

    public CharSequence getClassName()
    {
        return mInfo.getClassName();
    }

    public CollectionInfoCompat getCollectionInfo()
    {
        Object obj = IMPL.getCollectionInfo(mInfo);
        if(obj == null)
            return null;
        else
            return new CollectionInfoCompat(obj);
    }

    public CollectionItemInfoCompat getCollectionItemInfo()
    {
        Object obj = IMPL.getCollectionItemInfo(mInfo);
        if(obj == null)
            return null;
        else
            return new CollectionItemInfoCompat(obj);
    }

    public CharSequence getContentDescription()
    {
        return mInfo.getContentDescription();
    }

    public int getDrawingOrder()
    {
        return IMPL.getDrawingOrder(mInfo);
    }

    public CharSequence getError()
    {
        return IMPL.getError(mInfo);
    }

    public Bundle getExtras()
    {
        return IMPL.getExtras(mInfo);
    }

    public Object getInfo()
    {
        return mInfo;
    }

    public int getInputType()
    {
        return IMPL.getInputType(mInfo);
    }

    public AccessibilityNodeInfoCompat getLabelFor()
    {
        return wrapNonNullInstance(IMPL.getLabelFor(mInfo));
    }

    public AccessibilityNodeInfoCompat getLabeledBy()
    {
        return wrapNonNullInstance(IMPL.getLabeledBy(mInfo));
    }

    public int getLiveRegion()
    {
        return IMPL.getLiveRegion(mInfo);
    }

    public int getMaxTextLength()
    {
        return IMPL.getMaxTextLength(mInfo);
    }

    public int getMovementGranularities()
    {
        return IMPL.getMovementGranularities(mInfo);
    }

    public CharSequence getPackageName()
    {
        return mInfo.getPackageName();
    }

    public AccessibilityNodeInfoCompat getParent()
    {
        return wrapNonNullInstance(mInfo.getParent());
    }

    public RangeInfoCompat getRangeInfo()
    {
        Object obj = IMPL.getRangeInfo(mInfo);
        if(obj == null)
            return null;
        else
            return new RangeInfoCompat(obj);
    }

    public CharSequence getRoleDescription()
    {
        return IMPL.getRoleDescription(mInfo);
    }

    public CharSequence getText()
    {
        return mInfo.getText();
    }

    public int getTextSelectionEnd()
    {
        return IMPL.getTextSelectionEnd(mInfo);
    }

    public int getTextSelectionStart()
    {
        return IMPL.getTextSelectionStart(mInfo);
    }

    public AccessibilityNodeInfoCompat getTraversalAfter()
    {
        return wrapNonNullInstance(IMPL.getTraversalAfter(mInfo));
    }

    public AccessibilityNodeInfoCompat getTraversalBefore()
    {
        return wrapNonNullInstance(IMPL.getTraversalBefore(mInfo));
    }

    public String getViewIdResourceName()
    {
        return IMPL.getViewIdResourceName(mInfo);
    }

    public AccessibilityWindowInfoCompat getWindow()
    {
        return AccessibilityWindowInfoCompat.wrapNonNullInstance(IMPL.getWindow(mInfo));
    }

    public int getWindowId()
    {
        return mInfo.getWindowId();
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
        return IMPL.isAccessibilityFocused(mInfo);
    }

    public boolean isCheckable()
    {
        return mInfo.isCheckable();
    }

    public boolean isChecked()
    {
        return mInfo.isChecked();
    }

    public boolean isClickable()
    {
        return mInfo.isClickable();
    }

    public boolean isContentInvalid()
    {
        return IMPL.isContentInvalid(mInfo);
    }

    public boolean isContextClickable()
    {
        return IMPL.isContextClickable(mInfo);
    }

    public boolean isDismissable()
    {
        return IMPL.isDismissable(mInfo);
    }

    public boolean isEditable()
    {
        return IMPL.isEditable(mInfo);
    }

    public boolean isEnabled()
    {
        return mInfo.isEnabled();
    }

    public boolean isFocusable()
    {
        return mInfo.isFocusable();
    }

    public boolean isFocused()
    {
        return mInfo.isFocused();
    }

    public boolean isImportantForAccessibility()
    {
        return IMPL.isImportantForAccessibility(mInfo);
    }

    public boolean isLongClickable()
    {
        return mInfo.isLongClickable();
    }

    public boolean isMultiLine()
    {
        return IMPL.isMultiLine(mInfo);
    }

    public boolean isPassword()
    {
        return mInfo.isPassword();
    }

    public boolean isScrollable()
    {
        return mInfo.isScrollable();
    }

    public boolean isSelected()
    {
        return mInfo.isSelected();
    }

    public boolean isVisibleToUser()
    {
        return IMPL.isVisibleToUser(mInfo);
    }

    public boolean performAction(int i)
    {
        return mInfo.performAction(i);
    }

    public boolean performAction(int i, Bundle bundle)
    {
        return IMPL.performAction(mInfo, i, bundle);
    }

    public void recycle()
    {
        mInfo.recycle();
    }

    public boolean refresh()
    {
        return IMPL.refresh(mInfo);
    }

    public boolean removeAction(AccessibilityActionCompat accessibilityactioncompat)
    {
        return IMPL.removeAction(mInfo, accessibilityactioncompat.mAction);
    }

    public boolean removeChild(View view)
    {
        return IMPL.removeChild(mInfo, view);
    }

    public boolean removeChild(View view, int i)
    {
        return IMPL.removeChild(mInfo, view, i);
    }

    public void setAccessibilityFocused(boolean flag)
    {
        IMPL.setAccessibilityFocused(mInfo, flag);
    }

    public void setBoundsInParent(Rect rect)
    {
        mInfo.setBoundsInParent(rect);
    }

    public void setBoundsInScreen(Rect rect)
    {
        mInfo.setBoundsInScreen(rect);
    }

    public void setCanOpenPopup(boolean flag)
    {
        IMPL.setCanOpenPopup(mInfo, flag);
    }

    public void setCheckable(boolean flag)
    {
        mInfo.setCheckable(flag);
    }

    public void setChecked(boolean flag)
    {
        mInfo.setChecked(flag);
    }

    public void setClassName(CharSequence charsequence)
    {
        mInfo.setClassName(charsequence);
    }

    public void setClickable(boolean flag)
    {
        mInfo.setClickable(flag);
    }

    public void setCollectionInfo(Object obj)
    {
        IMPL.setCollectionInfo(mInfo, ((CollectionInfoCompat)obj).mInfo);
    }

    public void setCollectionItemInfo(Object obj)
    {
        IMPL.setCollectionItemInfo(mInfo, ((CollectionItemInfoCompat)obj).mInfo);
    }

    public void setContentDescription(CharSequence charsequence)
    {
        mInfo.setContentDescription(charsequence);
    }

    public void setContentInvalid(boolean flag)
    {
        IMPL.setContentInvalid(mInfo, flag);
    }

    public void setContextClickable(boolean flag)
    {
        IMPL.setContextClickable(mInfo, flag);
    }

    public void setDismissable(boolean flag)
    {
        IMPL.setDismissable(mInfo, flag);
    }

    public void setDrawingOrder(int i)
    {
        IMPL.setDrawingOrder(mInfo, i);
    }

    public void setEditable(boolean flag)
    {
        IMPL.setEditable(mInfo, flag);
    }

    public void setEnabled(boolean flag)
    {
        mInfo.setEnabled(flag);
    }

    public void setError(CharSequence charsequence)
    {
        IMPL.setError(mInfo, charsequence);
    }

    public void setFocusable(boolean flag)
    {
        mInfo.setFocusable(flag);
    }

    public void setFocused(boolean flag)
    {
        mInfo.setFocused(flag);
    }

    public void setImportantForAccessibility(boolean flag)
    {
        IMPL.setImportantForAccessibility(mInfo, flag);
    }

    public void setInputType(int i)
    {
        IMPL.setInputType(mInfo, i);
    }

    public void setLabelFor(View view)
    {
        IMPL.setLabelFor(mInfo, view);
    }

    public void setLabelFor(View view, int i)
    {
        IMPL.setLabelFor(mInfo, view, i);
    }

    public void setLabeledBy(View view)
    {
        IMPL.setLabeledBy(mInfo, view);
    }

    public void setLabeledBy(View view, int i)
    {
        IMPL.setLabeledBy(mInfo, view, i);
    }

    public void setLiveRegion(int i)
    {
        IMPL.setLiveRegion(mInfo, i);
    }

    public void setLongClickable(boolean flag)
    {
        mInfo.setLongClickable(flag);
    }

    public void setMaxTextLength(int i)
    {
        IMPL.setMaxTextLength(mInfo, i);
    }

    public void setMovementGranularities(int i)
    {
        IMPL.setMovementGranularities(mInfo, i);
    }

    public void setMultiLine(boolean flag)
    {
        IMPL.setMultiLine(mInfo, flag);
    }

    public void setPackageName(CharSequence charsequence)
    {
        mInfo.setPackageName(charsequence);
    }

    public void setParent(View view)
    {
        mInfo.setParent(view);
    }

    public void setParent(View view, int i)
    {
        mParentVirtualDescendantId = i;
        IMPL.setParent(mInfo, view, i);
    }

    public void setPassword(boolean flag)
    {
        mInfo.setPassword(flag);
    }

    public void setRangeInfo(RangeInfoCompat rangeinfocompat)
    {
        IMPL.setRangeInfo(mInfo, rangeinfocompat.mInfo);
    }

    public void setRoleDescription(CharSequence charsequence)
    {
        IMPL.setRoleDescription(mInfo, charsequence);
    }

    public void setScrollable(boolean flag)
    {
        mInfo.setScrollable(flag);
    }

    public void setSelected(boolean flag)
    {
        mInfo.setSelected(flag);
    }

    public void setSource(View view)
    {
        mInfo.setSource(view);
    }

    public void setSource(View view, int i)
    {
        IMPL.setSource(mInfo, view, i);
    }

    public void setText(CharSequence charsequence)
    {
        mInfo.setText(charsequence);
    }

    public void setTextSelection(int i, int j)
    {
        IMPL.setTextSelection(mInfo, i, j);
    }

    public void setTraversalAfter(View view)
    {
        IMPL.setTraversalAfter(mInfo, view);
    }

    public void setTraversalAfter(View view, int i)
    {
        IMPL.setTraversalAfter(mInfo, view, i);
    }

    public void setTraversalBefore(View view)
    {
        IMPL.setTraversalBefore(mInfo, view);
    }

    public void setTraversalBefore(View view, int i)
    {
        IMPL.setTraversalBefore(mInfo, view, i);
    }

    public void setViewIdResourceName(String s)
    {
        IMPL.setViewIdResourceName(mInfo, s);
    }

    public void setVisibleToUser(boolean flag)
    {
        IMPL.setVisibleToUser(mInfo, flag);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(super.toString());
        Rect rect = new Rect();
        getBoundsInParent(rect);
        stringbuilder.append((new StringBuilder()).append("; boundsInParent: ").append(rect).toString());
        getBoundsInScreen(rect);
        stringbuilder.append((new StringBuilder()).append("; boundsInScreen: ").append(rect).toString());
        stringbuilder.append("; packageName: ").append(getPackageName());
        stringbuilder.append("; className: ").append(getClassName());
        stringbuilder.append("; text: ").append(getText());
        stringbuilder.append("; contentDescription: ").append(getContentDescription());
        stringbuilder.append("; viewId: ").append(getViewIdResourceName());
        stringbuilder.append("; checkable: ").append(isCheckable());
        stringbuilder.append("; checked: ").append(isChecked());
        stringbuilder.append("; focusable: ").append(isFocusable());
        stringbuilder.append("; focused: ").append(isFocused());
        stringbuilder.append("; selected: ").append(isSelected());
        stringbuilder.append("; clickable: ").append(isClickable());
        stringbuilder.append("; longClickable: ").append(isLongClickable());
        stringbuilder.append("; enabled: ").append(isEnabled());
        stringbuilder.append("; password: ").append(isPassword());
        stringbuilder.append((new StringBuilder()).append("; scrollable: ").append(isScrollable()).toString());
        stringbuilder.append("; [");
        int i = getActions();
        do
        {
            if(i == 0)
                break;
            int k = 1 << Integer.numberOfTrailingZeros(i);
            int j = i & ~k;
            stringbuilder.append(getActionSymbolicName(k));
            i = j;
            if(j != 0)
            {
                stringbuilder.append(", ");
                i = j;
            }
        } while(true);
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public AccessibilityNodeInfo unwrap()
    {
        return mInfo;
    }

    public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COLLAPSE = 0x80000;
    public static final int ACTION_COPY = 16384;
    public static final int ACTION_CUT = 0x10000;
    public static final int ACTION_DISMISS = 0x100000;
    public static final int ACTION_EXPAND = 0x40000;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 32;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
    public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
    public static final int ACTION_PASTE = 32768;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
    public static final int ACTION_SCROLL_BACKWARD = 8192;
    public static final int ACTION_SCROLL_FORWARD = 4096;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 0x20000;
    public static final int ACTION_SET_TEXT = 0x200000;
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    static final AccessibilityNodeInfoBaseImpl IMPL;
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    private final AccessibilityNodeInfo mInfo;
    public int mParentVirtualDescendantId;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            IMPL = new AccessibilityNodeInfoApi24Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 23)
            IMPL = new AccessibilityNodeInfoApi23Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 22)
            IMPL = new AccessibilityNodeInfoApi22Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 21)
            IMPL = new AccessibilityNodeInfoApi21Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 19)
            IMPL = new AccessibilityNodeInfoApi19Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 18)
            IMPL = new AccessibilityNodeInfoApi18Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 17)
            IMPL = new AccessibilityNodeInfoApi17Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new AccessibilityNodeInfoApi16Impl();
        else
            IMPL = new AccessibilityNodeInfoBaseImpl();
    }
}
