// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.*;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.widget:
//            ViewDragHelper

public class DrawerLayout extends ViewGroup
{
    class AccessibilityDelegate extends AccessibilityDelegateCompat
    {

        private void addChildrenForAccessibility(AccessibilityNodeInfoCompat accessibilitynodeinfocompat, ViewGroup viewgroup)
        {
            int j = viewgroup.getChildCount();
            for(int i = 0; i < j; i++)
            {
                View view = viewgroup.getChildAt(i);
                if(DrawerLayout.includeChildForAccessibility(view))
                    accessibilitynodeinfocompat.addChild(view);
            }

        }

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat accessibilitynodeinfocompat, AccessibilityNodeInfoCompat accessibilitynodeinfocompat1)
        {
            Rect rect = mTmpRect;
            accessibilitynodeinfocompat1.getBoundsInParent(rect);
            accessibilitynodeinfocompat.setBoundsInParent(rect);
            accessibilitynodeinfocompat1.getBoundsInScreen(rect);
            accessibilitynodeinfocompat.setBoundsInScreen(rect);
            accessibilitynodeinfocompat.setVisibleToUser(accessibilitynodeinfocompat1.isVisibleToUser());
            accessibilitynodeinfocompat.setPackageName(accessibilitynodeinfocompat1.getPackageName());
            accessibilitynodeinfocompat.setClassName(accessibilitynodeinfocompat1.getClassName());
            accessibilitynodeinfocompat.setContentDescription(accessibilitynodeinfocompat1.getContentDescription());
            accessibilitynodeinfocompat.setEnabled(accessibilitynodeinfocompat1.isEnabled());
            accessibilitynodeinfocompat.setClickable(accessibilitynodeinfocompat1.isClickable());
            accessibilitynodeinfocompat.setFocusable(accessibilitynodeinfocompat1.isFocusable());
            accessibilitynodeinfocompat.setFocused(accessibilitynodeinfocompat1.isFocused());
            accessibilitynodeinfocompat.setAccessibilityFocused(accessibilitynodeinfocompat1.isAccessibilityFocused());
            accessibilitynodeinfocompat.setSelected(accessibilitynodeinfocompat1.isSelected());
            accessibilitynodeinfocompat.setLongClickable(accessibilitynodeinfocompat1.isLongClickable());
            accessibilitynodeinfocompat.addAction(accessibilitynodeinfocompat1.getActions());
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            if(accessibilityevent.getEventType() == 32)
            {
                view = accessibilityevent.getText();
                accessibilityevent = findVisibleDrawer();
                if(accessibilityevent != null)
                {
                    int i = getDrawerViewAbsoluteGravity(accessibilityevent);
                    accessibilityevent = getDrawerTitle(i);
                    if(accessibilityevent != null)
                        view.add(accessibilityevent);
                }
                return true;
            } else
            {
                return super.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
            }
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(view, accessibilityevent);
            accessibilityevent.setClassName(android/support/v4/widget/DrawerLayout.getName());
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            if(DrawerLayout.CAN_HIDE_DESCENDANTS)
            {
                super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            } else
            {
                AccessibilityNodeInfoCompat accessibilitynodeinfocompat1 = AccessibilityNodeInfoCompat.obtain(accessibilitynodeinfocompat);
                super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat1);
                accessibilitynodeinfocompat.setSource(view);
                android.view.ViewParent viewparent = ViewCompat.getParentForAccessibility(view);
                if(viewparent instanceof View)
                    accessibilitynodeinfocompat.setParent((View)viewparent);
                copyNodeInfoNoChildren(accessibilitynodeinfocompat, accessibilitynodeinfocompat1);
                accessibilitynodeinfocompat1.recycle();
                addChildrenForAccessibility(accessibilitynodeinfocompat, (ViewGroup)view);
            }
            accessibilitynodeinfocompat.setClassName(android/support/v4/widget/DrawerLayout.getName());
            accessibilitynodeinfocompat.setFocusable(false);
            accessibilitynodeinfocompat.setFocused(false);
            accessibilitynodeinfocompat.removeAction(android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS);
            accessibilitynodeinfocompat.removeAction(android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
        {
            if(DrawerLayout.CAN_HIDE_DESCENDANTS || DrawerLayout.includeChildForAccessibility(view))
                return super.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
            else
                return false;
        }

        private final Rect mTmpRect = new Rect();
        final DrawerLayout this$0;

        AccessibilityDelegate()
        {
            this$0 = DrawerLayout.this;
            super();
        }
    }

    static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat
    {

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            if(!DrawerLayout.includeChildForAccessibility(view))
                accessibilitynodeinfocompat.setParent(null);
        }

        ChildAccessibilityDelegate()
        {
        }
    }

    public static interface DrawerListener
    {

        public abstract void onDrawerClosed(View view);

        public abstract void onDrawerOpened(View view);

        public abstract void onDrawerSlide(View view, float f);

        public abstract void onDrawerStateChanged(int i);
    }

    private static interface EdgeGravity
        extends Annotation
    {
    }

    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        private static final int FLAG_IS_CLOSING = 4;
        private static final int FLAG_IS_OPENED = 1;
        private static final int FLAG_IS_OPENING = 2;
        public int gravity;
        boolean isPeeking;
        float onScreen;
        int openState;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            gravity = 0;
        }

        public LayoutParams(int i, int j, int k)
        {
            this(i, j);
            gravity = k;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            gravity = 0;
            context = context.obtainStyledAttributes(attributeset, DrawerLayout.LAYOUT_ATTRS);
            gravity = context.getInt(0, 0);
            context.recycle();
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            gravity = 0;
            gravity = layoutparams.gravity;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            gravity = 0;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            gravity = 0;
        }
    }

    private static interface LockMode
        extends Annotation
    {
    }

    protected static class SavedState extends AbsSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(openDrawerGravity);
            parcel.writeInt(lockModeLeft);
            parcel.writeInt(lockModeRight);
            parcel.writeInt(lockModeStart);
            parcel.writeInt(lockModeEnd);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.ClassLoaderCreator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        int lockModeEnd;
        int lockModeLeft;
        int lockModeRight;
        int lockModeStart;
        int openDrawerGravity;


        public SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            openDrawerGravity = 0;
            openDrawerGravity = parcel.readInt();
            lockModeLeft = parcel.readInt();
            lockModeRight = parcel.readInt();
            lockModeStart = parcel.readInt();
            lockModeEnd = parcel.readInt();
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
            openDrawerGravity = 0;
        }
    }

    public static abstract class SimpleDrawerListener
        implements DrawerListener
    {

        public void onDrawerClosed(View view)
        {
        }

        public void onDrawerOpened(View view)
        {
        }

        public void onDrawerSlide(View view, float f)
        {
        }

        public void onDrawerStateChanged(int i)
        {
        }

        public SimpleDrawerListener()
        {
        }
    }

    private static interface State
        extends Annotation
    {
    }

    private class ViewDragCallback extends ViewDragHelper.Callback
    {

        private void closeOtherDrawer()
        {
            byte byte0 = 3;
            if(mAbsGravity == 3)
                byte0 = 5;
            View view = findDrawerWithGravity(byte0);
            if(view != null)
                closeDrawer(view);
        }

        public int clampViewPositionHorizontal(View view, int i, int j)
        {
            if(checkDrawerViewAbsoluteGravity(view, 3))
            {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            } else
            {
                j = getWidth();
                return Math.max(j - view.getWidth(), Math.min(i, j));
            }
        }

        public int clampViewPositionVertical(View view, int i, int j)
        {
            return view.getTop();
        }

        public int getViewHorizontalDragRange(View view)
        {
            if(isDrawerView(view))
                return view.getWidth();
            else
                return 0;
        }

        public void onEdgeDragStarted(int i, int j)
        {
            View view;
            if((i & 1) == 1)
                view = findDrawerWithGravity(3);
            else
                view = findDrawerWithGravity(5);
            if(view != null && getDrawerLockMode(view) == 0)
                mDragger.captureChildView(view, j);
        }

        public boolean onEdgeLock(int i)
        {
            return false;
        }

        public void onEdgeTouched(int i, int j)
        {
            postDelayed(mPeekRunnable, 160L);
        }

        public void onViewCaptured(View view, int i)
        {
            ((LayoutParams)view.getLayoutParams()).isPeeking = false;
            closeOtherDrawer();
        }

        public void onViewDragStateChanged(int i)
        {
            updateDrawerState(mAbsGravity, i, mDragger.getCapturedView());
        }

        public void onViewPositionChanged(View view, int i, int j, int k, int l)
        {
            j = view.getWidth();
            float f;
            if(checkDrawerViewAbsoluteGravity(view, 3))
                f = (float)(j + i) / (float)j;
            else
                f = (float)(getWidth() - i) / (float)j;
            setDrawerViewOffset(view, f);
            if(f == 0.0F)
                i = 4;
            else
                i = 0;
            view.setVisibility(i);
            invalidate();
        }

        public void onViewReleased(View view, float f, float f1)
        {
            int j;
            f1 = getDrawerViewOffset(view);
            j = view.getWidth();
            if(!checkDrawerViewAbsoluteGravity(view, 3)) goto _L2; else goto _L1
_L1:
            int i;
            if(f > 0.0F || f == 0.0F && f1 > 0.5F)
                i = 0;
            else
                i = -j;
_L4:
            mDragger.settleCapturedViewAt(i, view.getTop());
            invalidate();
            return;
_L2:
            i = getWidth();
            if(f < 0.0F || f == 0.0F && f1 > 0.5F)
                i -= j;
            if(true) goto _L4; else goto _L3
_L3:
        }

        void peekDrawer()
        {
            int i = 0;
            int j = mDragger.getEdgeSize();
            boolean flag;
            View view;
            if(mAbsGravity == 3)
                flag = true;
            else
                flag = false;
            if(flag)
            {
                view = findDrawerWithGravity(3);
                if(view != null)
                    i = -view.getWidth();
                i += j;
            } else
            {
                view = findDrawerWithGravity(5);
                i = getWidth() - j;
            }
            if(view != null && (flag && view.getLeft() < i || !flag && view.getLeft() > i) && getDrawerLockMode(view) == 0)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                mDragger.smoothSlideViewTo(view, i, view.getTop());
                layoutparams.isPeeking = true;
                invalidate();
                closeOtherDrawer();
                cancelChildViewTouch();
            }
        }

        public void removeCallbacks()
        {
            DrawerLayout.this.removeCallbacks(mPeekRunnable);
        }

        public void setDragger(ViewDragHelper viewdraghelper)
        {
            mDragger = viewdraghelper;
        }

        public boolean tryCaptureView(View view, int i)
        {
            return isDrawerView(view) && checkDrawerViewAbsoluteGravity(view, mAbsGravity) && getDrawerLockMode(view) == 0;
        }

        private final int mAbsGravity;
        private ViewDragHelper mDragger;
        private final Runnable mPeekRunnable = new _cls1();
        final DrawerLayout this$0;

        ViewDragCallback(int i)
        {
            this$0 = DrawerLayout.this;
            super();
            mAbsGravity = i;
        }
    }


    public DrawerLayout(Context context)
    {
        this(context, null);
    }

    public DrawerLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public DrawerLayout(Context context, AttributeSet attributeset, int i)
    {
        float f;
        super(context, attributeset, i);
        mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
        mScrimColor = 0x99000000;
        mScrimPaint = new Paint();
        mFirstLayout = true;
        mLockModeLeft = 3;
        mLockModeRight = 3;
        mLockModeStart = 3;
        mLockModeEnd = 3;
        mShadowStart = null;
        mShadowEnd = null;
        mShadowLeft = null;
        mShadowRight = null;
        setDescendantFocusability(0x40000);
        f = getResources().getDisplayMetrics().density;
        mMinDrawerMargin = (int)(64F * f + 0.5F);
        float f1 = 400F * f;
        mLeftCallback = new ViewDragCallback(3);
        mRightCallback = new ViewDragCallback(5);
        mLeftDragger = ViewDragHelper.create(this, 1.0F, mLeftCallback);
        mLeftDragger.setEdgeTrackingEnabled(1);
        mLeftDragger.setMinVelocity(f1);
        mLeftCallback.setDragger(mLeftDragger);
        mRightDragger = ViewDragHelper.create(this, 1.0F, mRightCallback);
        mRightDragger.setEdgeTrackingEnabled(2);
        mRightDragger.setMinVelocity(f1);
        mRightCallback.setDragger(mRightDragger);
        setFocusableInTouchMode(true);
        ViewCompat.setImportantForAccessibility(this, 1);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
        if(!ViewCompat.getFitsSystemWindows(this)) goto _L2; else goto _L1
_L1:
        if(android.os.Build.VERSION.SDK_INT < 21) goto _L4; else goto _L3
_L3:
        setOnApplyWindowInsetsListener(new android.view.View.OnApplyWindowInsetsListener() {

            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowinsets)
            {
                view = (DrawerLayout)view;
                boolean flag;
                if(windowinsets.getSystemWindowInsetTop() > 0)
                    flag = true;
                else
                    flag = false;
                view.setChildInsets(windowinsets, flag);
                return windowinsets.consumeSystemWindowInsets();
            }

            final DrawerLayout this$0;

            
            {
                this$0 = DrawerLayout.this;
                super();
            }
        }
);
        setSystemUiVisibility(1280);
        context = context.obtainStyledAttributes(THEME_ATTRS);
        mStatusBarBackground = context.getDrawable(0);
        context.recycle();
_L2:
        mDrawerElevation = 10F * f;
        mNonDrawerViews = new ArrayList();
        return;
        attributeset;
        context.recycle();
        throw attributeset;
_L4:
        mStatusBarBackground = null;
        if(true) goto _L2; else goto _L5
_L5:
    }

    static String gravityToString(int i)
    {
        if((i & 3) == 3)
            return "LEFT";
        if((i & 5) == 5)
            return "RIGHT";
        else
            return Integer.toHexString(i);
    }

    private static boolean hasOpaqueBackground(View view)
    {
        boolean flag1 = false;
        view = view.getBackground();
        boolean flag = flag1;
        if(view != null)
        {
            flag = flag1;
            if(view.getOpacity() == -1)
                flag = true;
        }
        return flag;
    }

    private boolean hasPeekingDrawer()
    {
        int j = getChildCount();
        for(int i = 0; i < j; i++)
            if(((LayoutParams)getChildAt(i).getLayoutParams()).isPeeking)
                return true;

        return false;
    }

    private boolean hasVisibleDrawer()
    {
        return findVisibleDrawer() != null;
    }

    static boolean includeChildForAccessibility(View view)
    {
        return ViewCompat.getImportantForAccessibility(view) != 4 && ViewCompat.getImportantForAccessibility(view) != 2;
    }

    private boolean mirror(Drawable drawable, int i)
    {
        if(drawable == null || !DrawableCompat.isAutoMirrored(drawable))
        {
            return false;
        } else
        {
            DrawableCompat.setLayoutDirection(drawable, i);
            return true;
        }
    }

    private Drawable resolveLeftShadow()
    {
        int i = ViewCompat.getLayoutDirection(this);
        if(i == 0)
        {
            if(mShadowStart != null)
            {
                mirror(mShadowStart, i);
                return mShadowStart;
            }
        } else
        if(mShadowEnd != null)
        {
            mirror(mShadowEnd, i);
            return mShadowEnd;
        }
        return mShadowLeft;
    }

    private Drawable resolveRightShadow()
    {
        int i = ViewCompat.getLayoutDirection(this);
        if(i == 0)
        {
            if(mShadowEnd != null)
            {
                mirror(mShadowEnd, i);
                return mShadowEnd;
            }
        } else
        if(mShadowStart != null)
        {
            mirror(mShadowStart, i);
            return mShadowStart;
        }
        return mShadowRight;
    }

    private void resolveShadowDrawables()
    {
        if(SET_DRAWER_SHADOW_FROM_ELEVATION)
        {
            return;
        } else
        {
            mShadowLeftResolved = resolveLeftShadow();
            mShadowRightResolved = resolveRightShadow();
            return;
        }
    }

    private void updateChildrenImportantForAccessibility(View view, boolean flag)
    {
        int j = getChildCount();
        int i = 0;
        while(i < j) 
        {
            View view1 = getChildAt(i);
            if(!flag && !isDrawerView(view1) || flag && view1 == view)
                ViewCompat.setImportantForAccessibility(view1, 1);
            else
                ViewCompat.setImportantForAccessibility(view1, 4);
            i++;
        }
    }

    public void addDrawerListener(DrawerListener drawerlistener)
    {
        if(drawerlistener == null)
            return;
        if(mListeners == null)
            mListeners = new ArrayList();
        mListeners.add(drawerlistener);
    }

    public void addFocusables(ArrayList arraylist, int i, int j)
    {
        if(getDescendantFocusability() == 0x60000)
            return;
        int j1 = getChildCount();
        boolean flag = false;
        int k = 0;
        while(k < j1) 
        {
            View view = getChildAt(k);
            if(isDrawerView(view))
            {
                if(isDrawerOpen(view))
                {
                    flag = true;
                    view.addFocusables(arraylist, i, j);
                }
            } else
            {
                mNonDrawerViews.add(view);
            }
            k++;
        }
        if(!flag)
        {
            int i1 = mNonDrawerViews.size();
            for(int l = 0; l < i1; l++)
            {
                View view1 = (View)mNonDrawerViews.get(l);
                if(view1.getVisibility() == 0)
                    view1.addFocusables(arraylist, i, j);
            }

        }
        mNonDrawerViews.clear();
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addView(view, i, layoutparams);
        if(findOpenDrawer() != null || isDrawerView(view))
            ViewCompat.setImportantForAccessibility(view, 4);
        else
            ViewCompat.setImportantForAccessibility(view, 1);
        if(!CAN_HIDE_DESCENDANTS)
            ViewCompat.setAccessibilityDelegate(view, mChildAccessibilityDelegate);
    }

    void cancelChildViewTouch()
    {
        if(!mChildrenCanceledTouch)
        {
            long l = SystemClock.uptimeMillis();
            MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
            int j = getChildCount();
            for(int i = 0; i < j; i++)
                getChildAt(i).dispatchTouchEvent(motionevent);

            motionevent.recycle();
            mChildrenCanceledTouch = true;
        }
    }

    boolean checkDrawerViewAbsoluteGravity(View view, int i)
    {
        return (getDrawerViewAbsoluteGravity(view) & i) == i;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return (layoutparams instanceof LayoutParams) && super.checkLayoutParams(layoutparams);
    }

    public void closeDrawer(int i)
    {
        closeDrawer(i, true);
    }

    public void closeDrawer(int i, boolean flag)
    {
        View view = findDrawerWithGravity(i);
        if(view == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("No drawer view found with gravity ").append(gravityToString(i)).toString());
        } else
        {
            closeDrawer(view, flag);
            return;
        }
    }

    public void closeDrawer(View view)
    {
        closeDrawer(view, true);
    }

    public void closeDrawer(View view, boolean flag)
    {
        if(!isDrawerView(view))
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a sliding drawer").toString());
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if(mFirstLayout)
        {
            layoutparams.onScreen = 0.0F;
            layoutparams.openState = 0;
        } else
        if(flag)
        {
            layoutparams.openState = layoutparams.openState | 4;
            if(checkDrawerViewAbsoluteGravity(view, 3))
                mLeftDragger.smoothSlideViewTo(view, -view.getWidth(), view.getTop());
            else
                mRightDragger.smoothSlideViewTo(view, getWidth(), view.getTop());
        } else
        {
            moveDrawerToOffset(view, 0.0F);
            updateDrawerState(layoutparams.gravity, 0, view);
            view.setVisibility(4);
        }
        invalidate();
    }

    public void closeDrawers()
    {
        closeDrawers(false);
    }

    void closeDrawers(boolean flag)
    {
        boolean flag1 = false;
        int k = getChildCount();
        int i = 0;
        while(i < k) 
        {
            View view = getChildAt(i);
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            int j = ((flag1) ? 1 : 0);
            if(isDrawerView(view))
                if(flag && !layoutparams.isPeeking)
                {
                    j = ((flag1) ? 1 : 0);
                } else
                {
                    j = view.getWidth();
                    if(checkDrawerViewAbsoluteGravity(view, 3))
                        flag1 |= mLeftDragger.smoothSlideViewTo(view, -j, view.getTop());
                    else
                        flag1 |= mRightDragger.smoothSlideViewTo(view, getWidth(), view.getTop());
                    layoutparams.isPeeking = false;
                    j = ((flag1) ? 1 : 0);
                }
            i++;
            flag1 = j;
        }
        mLeftCallback.removeCallbacks();
        mRightCallback.removeCallbacks();
        if(flag1)
            invalidate();
    }

    public void computeScroll()
    {
        int j = getChildCount();
        float f = 0.0F;
        for(int i = 0; i < j; i++)
            f = Math.max(f, ((LayoutParams)getChildAt(i).getLayoutParams()).onScreen);

        mScrimOpacity = f;
        boolean flag = mLeftDragger.continueSettling(true);
        boolean flag1 = mRightDragger.continueSettling(true);
        if(flag || flag1)
            ViewCompat.postInvalidateOnAnimation(this);
    }

    void dispatchOnDrawerClosed(View view)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if((layoutparams.openState & 1) == 1)
        {
            layoutparams.openState = 0;
            if(mListeners != null)
            {
                for(int i = mListeners.size() - 1; i >= 0; i--)
                    ((DrawerListener)mListeners.get(i)).onDrawerClosed(view);

            }
            updateChildrenImportantForAccessibility(view, false);
            if(hasWindowFocus())
            {
                view = getRootView();
                if(view != null)
                    view.sendAccessibilityEvent(32);
            }
        }
    }

    void dispatchOnDrawerOpened(View view)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if((layoutparams.openState & 1) == 0)
        {
            layoutparams.openState = 1;
            if(mListeners != null)
            {
                for(int i = mListeners.size() - 1; i >= 0; i--)
                    ((DrawerListener)mListeners.get(i)).onDrawerOpened(view);

            }
            updateChildrenImportantForAccessibility(view, true);
            if(hasWindowFocus())
                sendAccessibilityEvent(32);
        }
    }

    void dispatchOnDrawerSlide(View view, float f)
    {
        if(mListeners != null)
        {
            for(int i = mListeners.size() - 1; i >= 0; i--)
                ((DrawerListener)mListeners.get(i)).onDrawerSlide(view, f);

        }
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        int j4 = getHeight();
        boolean flag1 = isContentView(view);
        int i = 0;
        boolean flag = false;
        int i1 = getWidth();
        int k4 = canvas.save();
        int i2 = i1;
        if(flag1)
        {
            int l4 = getChildCount();
            i2 = 0;
            i = ((flag) ? 1 : 0);
            while(i2 < l4) 
            {
                View view1 = getChildAt(i2);
                int l2 = i;
                int k3 = i1;
                if(view1 != view)
                {
                    l2 = i;
                    k3 = i1;
                    if(view1.getVisibility() == 0)
                    {
                        l2 = i;
                        k3 = i1;
                        if(hasOpaqueBackground(view1))
                        {
                            l2 = i;
                            k3 = i1;
                            if(isDrawerView(view1))
                                if(view1.getHeight() < j4)
                                {
                                    k3 = i1;
                                    l2 = i;
                                } else
                                if(checkDrawerViewAbsoluteGravity(view1, 3))
                                {
                                    int l3 = view1.getRight();
                                    l2 = i;
                                    k3 = i1;
                                    if(l3 > i)
                                    {
                                        l2 = l3;
                                        k3 = i1;
                                    }
                                } else
                                {
                                    int i4 = view1.getLeft();
                                    l2 = i;
                                    k3 = i1;
                                    if(i4 < i1)
                                    {
                                        k3 = i4;
                                        l2 = i;
                                    }
                                }
                        }
                    }
                }
                i2++;
                i = l2;
                i1 = k3;
            }
            canvas.clipRect(i, 0, i1, getHeight());
            i2 = i1;
        }
        boolean flag2 = super.drawChild(canvas, view, l);
        canvas.restoreToCount(k4);
        if(mScrimOpacity > 0.0F && flag1)
        {
            int j1 = (int)((float)((mScrimColor & 0xff000000) >>> 24) * mScrimOpacity);
            int i3 = mScrimColor;
            mScrimPaint.setColor(j1 << 24 | i3 & 0xffffff);
            canvas.drawRect(i, 0.0F, i2, getHeight(), mScrimPaint);
        } else
        {
            if(mShadowLeftResolved != null && checkDrawerViewAbsoluteGravity(view, 3))
            {
                int j = mShadowLeftResolved.getIntrinsicWidth();
                int k1 = view.getRight();
                int j2 = mLeftDragger.getEdgeSize();
                float f = Math.max(0.0F, Math.min((float)k1 / (float)j2, 1.0F));
                mShadowLeftResolved.setBounds(k1, view.getTop(), k1 + j, view.getBottom());
                mShadowLeftResolved.setAlpha((int)(255F * f));
                mShadowLeftResolved.draw(canvas);
                return flag2;
            }
            if(mShadowRightResolved != null && checkDrawerViewAbsoluteGravity(view, 5))
            {
                int k = mShadowRightResolved.getIntrinsicWidth();
                int l1 = view.getLeft();
                int k2 = getWidth();
                int j3 = mRightDragger.getEdgeSize();
                float f1 = Math.max(0.0F, Math.min((float)(k2 - l1) / (float)j3, 1.0F));
                mShadowRightResolved.setBounds(l1 - k, view.getTop(), l1, view.getBottom());
                mShadowRightResolved.setAlpha((int)(255F * f1));
                mShadowRightResolved.draw(canvas);
                return flag2;
            }
        }
        return flag2;
    }

    View findDrawerWithGravity(int i)
    {
        int j = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        int k = getChildCount();
        for(i = 0; i < k; i++)
        {
            View view = getChildAt(i);
            if((getDrawerViewAbsoluteGravity(view) & 7) == (j & 7))
                return view;
        }

        return null;
    }

    View findOpenDrawer()
    {
        int j = getChildCount();
        for(int i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            if((((LayoutParams)view.getLayoutParams()).openState & 1) == 1)
                return view;
        }

        return null;
    }

    View findVisibleDrawer()
    {
        int j = getChildCount();
        for(int i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            if(isDrawerView(view) && isDrawerVisible(view))
                return view;
        }

        return null;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-1, -1);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(layoutparams instanceof LayoutParams)
            return new LayoutParams((LayoutParams)layoutparams);
        if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
            return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        else
            return new LayoutParams(layoutparams);
    }

    public float getDrawerElevation()
    {
        if(SET_DRAWER_SHADOW_FROM_ELEVATION)
            return mDrawerElevation;
        else
            return 0.0F;
    }

    public int getDrawerLockMode(int i)
    {
        int j = ViewCompat.getLayoutDirection(this);
        i;
        JVM INSTR lookupswitch 4: default 48
    //                   3: 50
    //                   5: 87
    //                   8388611: 124
    //                   8388613: 161;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return 0;
_L2:
        if(mLockModeLeft != 3)
            return mLockModeLeft;
        if(j == 0)
            i = mLockModeStart;
        else
            i = mLockModeEnd;
        if(i != 3)
            return i;
        continue; /* Loop/switch isn't completed */
_L3:
        if(mLockModeRight != 3)
            return mLockModeRight;
        if(j == 0)
            i = mLockModeEnd;
        else
            i = mLockModeStart;
        if(i != 3)
            return i;
        if(true) goto _L1; else goto _L4
_L4:
        if(mLockModeStart != 3)
            return mLockModeStart;
        if(j == 0)
            i = mLockModeLeft;
        else
            i = mLockModeRight;
        if(i != 3)
            return i;
        if(true)
            continue; /* Loop/switch isn't completed */
_L5:
        if(mLockModeEnd != 3)
            return mLockModeEnd;
        if(j == 0)
            i = mLockModeRight;
        else
            i = mLockModeLeft;
        if(i != 3)
            return i;
        if(true) goto _L1; else goto _L6
_L6:
    }

    public int getDrawerLockMode(View view)
    {
        if(!isDrawerView(view))
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a drawer").toString());
        else
            return getDrawerLockMode(((LayoutParams)view.getLayoutParams()).gravity);
    }

    public CharSequence getDrawerTitle(int i)
    {
        i = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        if(i == 3)
            return mTitleLeft;
        if(i == 5)
            return mTitleRight;
        else
            return null;
    }

    int getDrawerViewAbsoluteGravity(View view)
    {
        return GravityCompat.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this));
    }

    float getDrawerViewOffset(View view)
    {
        return ((LayoutParams)view.getLayoutParams()).onScreen;
    }

    public Drawable getStatusBarBackgroundDrawable()
    {
        return mStatusBarBackground;
    }

    boolean isContentView(View view)
    {
        return ((LayoutParams)view.getLayoutParams()).gravity == 0;
    }

    public boolean isDrawerOpen(int i)
    {
        View view = findDrawerWithGravity(i);
        if(view != null)
            return isDrawerOpen(view);
        else
            return false;
    }

    public boolean isDrawerOpen(View view)
    {
        if(!isDrawerView(view))
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a drawer").toString());
        return (((LayoutParams)view.getLayoutParams()).openState & 1) == 1;
    }

    boolean isDrawerView(View view)
    {
        int i = GravityCompat.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(view));
        if((i & 3) != 0)
            return true;
        return (i & 5) != 0;
    }

    public boolean isDrawerVisible(int i)
    {
        View view = findDrawerWithGravity(i);
        if(view != null)
            return isDrawerVisible(view);
        else
            return false;
    }

    public boolean isDrawerVisible(View view)
    {
        if(!isDrawerView(view))
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a drawer").toString());
        return ((LayoutParams)view.getLayoutParams()).onScreen > 0.0F;
    }

    void moveDrawerToOffset(View view, float f)
    {
        float f1 = getDrawerViewOffset(view);
        int i = view.getWidth();
        int j = (int)((float)i * f1);
        i = (int)((float)i * f) - j;
        if(!checkDrawerViewAbsoluteGravity(view, 3))
            i = -i;
        view.offsetLeftAndRight(i);
        setDrawerViewOffset(view, f);
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mFirstLayout = true;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mFirstLayout = true;
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(mDrawStatusBarBackground && mStatusBarBackground != null)
        {
            int i;
            if(android.os.Build.VERSION.SDK_INT >= 21)
            {
                if(mLastInsets != null)
                    i = ((WindowInsets)mLastInsets).getSystemWindowInsetTop();
                else
                    i = 0;
            } else
            {
                i = 0;
            }
            if(i > 0)
            {
                mStatusBarBackground.setBounds(0, 0, getWidth(), i);
                mStatusBarBackground.draw(canvas);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        flag3 = false;
        i = motionevent.getActionMasked();
        flag4 = mLeftDragger.shouldInterceptTouchEvent(motionevent);
        flag5 = mRightDragger.shouldInterceptTouchEvent(motionevent);
        flag2 = false;
        flag1 = false;
        i;
        JVM INSTR tableswitch 0 3: default 68
    //                   0 105
    //                   1 222
    //                   2 186
    //                   3 222;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        boolean flag = flag1;
_L6:
        if(flag4 | flag5 || flag || hasPeekingDrawer() || mChildrenCanceledTouch)
            flag3 = true;
        return flag3;
_L2:
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        mInitialMotionX = f;
        mInitialMotionY = f1;
        flag = flag2;
        if(mScrimOpacity > 0.0F)
        {
            motionevent = mLeftDragger.findTopChildUnder((int)f, (int)f1);
            flag = flag2;
            if(motionevent != null)
            {
                flag = flag2;
                if(isContentView(motionevent))
                    flag = true;
            }
        }
        mDisallowInterceptRequested = false;
        mChildrenCanceledTouch = false;
        continue; /* Loop/switch isn't completed */
_L4:
        flag = flag1;
        if(mLeftDragger.checkTouchSlop(3))
        {
            mLeftCallback.removeCallbacks();
            mRightCallback.removeCallbacks();
            flag = flag1;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        closeDrawers(true);
        mDisallowInterceptRequested = false;
        mChildrenCanceledTouch = false;
        flag = flag1;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 4 && hasVisibleDrawer())
        {
            keyevent.startTracking();
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(i == 4)
        {
            keyevent = findVisibleDrawer();
            if(keyevent != null && getDrawerLockMode(keyevent) == 0)
                closeDrawers();
            return keyevent != null;
        } else
        {
            return super.onKeyUp(i, keyevent);
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int k1;
        int l1;
        mInLayout = true;
        k1 = k - i;
        l1 = getChildCount();
        k = 0;
_L2:
        View view;
        if(k >= l1)
            break MISSING_BLOCK_LABEL_446;
        view = getChildAt(k);
        if(view.getVisibility() != 8)
            break; /* Loop/switch isn't completed */
_L3:
        k++;
        if(true) goto _L2; else goto _L1
_L1:
        LayoutParams layoutparams;
label0:
        {
            layoutparams = (LayoutParams)view.getLayoutParams();
            if(!isContentView(view))
                break label0;
            view.layout(layoutparams.leftMargin, layoutparams.topMargin, layoutparams.leftMargin + view.getMeasuredWidth(), layoutparams.topMargin + view.getMeasuredHeight());
        }
          goto _L3
        int i1;
        int i2;
        int j2;
        i2 = view.getMeasuredWidth();
        j2 = view.getMeasuredHeight();
        float f;
        boolean flag1;
        if(checkDrawerViewAbsoluteGravity(view, 3))
        {
            i1 = -i2 + (int)((float)i2 * layoutparams.onScreen);
            f = (float)(i2 + i1) / (float)i2;
        } else
        {
            i1 = k1 - (int)((float)i2 * layoutparams.onScreen);
            f = (float)(k1 - i1) / (float)i2;
        }
        if(f != layoutparams.onScreen)
            flag1 = true;
        else
            flag1 = false;
        layoutparams.gravity & 0x70;
        JVM INSTR lookupswitch 2: default 212
    //                   16: 356
    //                   80: 316;
           goto _L4 _L5 _L6
_L5:
        break MISSING_BLOCK_LABEL_356;
_L4:
        view.layout(i1, layoutparams.topMargin, i1 + i2, layoutparams.topMargin + j2);
_L7:
        if(flag1)
            setDrawerViewOffset(view, f);
        int j1;
        int k2;
        if(layoutparams.onScreen > 0.0F)
            i = 0;
        else
            i = 4;
        if(view.getVisibility() != i)
            view.setVisibility(i);
          goto _L3
_L6:
        i = l - j;
        view.layout(i1, i - layoutparams.bottomMargin - view.getMeasuredHeight(), i1 + i2, i - layoutparams.bottomMargin);
          goto _L7
        k2 = l - j;
        j1 = (k2 - j2) / 2;
        if(j1 < layoutparams.topMargin)
        {
            i = layoutparams.topMargin;
        } else
        {
            i = j1;
            if(j1 + j2 > k2 - layoutparams.bottomMargin)
                i = k2 - layoutparams.bottomMargin - j2;
        }
        view.layout(i1, i, i1 + i2, i + j2);
          goto _L7
        mInLayout = false;
        mFirstLayout = false;
        return;
          goto _L3
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int j2;
label0:
        {
            l1 = android.view.View.MeasureSpec.getMode(i);
            k1 = android.view.View.MeasureSpec.getMode(j);
            k = android.view.View.MeasureSpec.getSize(i);
            l = android.view.View.MeasureSpec.getSize(j);
            if(l1 == 0x40000000)
            {
                i1 = l;
                j1 = k;
                if(k1 == 0x40000000)
                    break label0;
            }
            if(!isInEditMode())
                break MISSING_BLOCK_LABEL_190;
            int k2;
            if(l1 != 0x80000000 && l1 == 0)
                k = 300;
            if(k1 == 0x80000000)
            {
                j1 = k;
                i1 = l;
            } else
            {
                i1 = l;
                j1 = k;
                if(k1 == 0)
                {
                    i1 = 300;
                    j1 = k;
                }
            }
        }
        setMeasuredDimension(j1, i1);
        if(mLastInsets != null && ViewCompat.getFitsSystemWindows(this))
            k1 = 1;
        else
            k1 = 0;
        j2 = ViewCompat.getLayoutDirection(this);
        l = 0;
        k = 0;
        k2 = getChildCount();
        l1 = 0;
        do
        {
            if(l1 < k2)
            {
                View view = getChildAt(l1);
                if(view.getVisibility() != 8)
                {
                    LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                    if(k1 != 0)
                    {
                        int i2 = GravityCompat.getAbsoluteGravity(layoutparams.gravity, j2);
                        if(ViewCompat.getFitsSystemWindows(view))
                        {
                            if(android.os.Build.VERSION.SDK_INT >= 21)
                            {
                                WindowInsets windowinsets2 = (WindowInsets)mLastInsets;
                                WindowInsets windowinsets;
                                if(i2 == 3)
                                {
                                    windowinsets = windowinsets2.replaceSystemWindowInsets(windowinsets2.getSystemWindowInsetLeft(), windowinsets2.getSystemWindowInsetTop(), 0, windowinsets2.getSystemWindowInsetBottom());
                                } else
                                {
                                    windowinsets = windowinsets2;
                                    if(i2 == 5)
                                        windowinsets = windowinsets2.replaceSystemWindowInsets(0, windowinsets2.getSystemWindowInsetTop(), windowinsets2.getSystemWindowInsetRight(), windowinsets2.getSystemWindowInsetBottom());
                                }
                                view.dispatchApplyWindowInsets(windowinsets);
                            }
                        } else
                        if(android.os.Build.VERSION.SDK_INT >= 21)
                        {
                            WindowInsets windowinsets3 = (WindowInsets)mLastInsets;
                            WindowInsets windowinsets1;
                            if(i2 == 3)
                            {
                                windowinsets1 = windowinsets3.replaceSystemWindowInsets(windowinsets3.getSystemWindowInsetLeft(), windowinsets3.getSystemWindowInsetTop(), 0, windowinsets3.getSystemWindowInsetBottom());
                            } else
                            {
                                windowinsets1 = windowinsets3;
                                if(i2 == 5)
                                    windowinsets1 = windowinsets3.replaceSystemWindowInsets(0, windowinsets3.getSystemWindowInsetTop(), windowinsets3.getSystemWindowInsetRight(), windowinsets3.getSystemWindowInsetBottom());
                            }
                            layoutparams.leftMargin = windowinsets1.getSystemWindowInsetLeft();
                            layoutparams.topMargin = windowinsets1.getSystemWindowInsetTop();
                            layoutparams.rightMargin = windowinsets1.getSystemWindowInsetRight();
                            layoutparams.bottomMargin = windowinsets1.getSystemWindowInsetBottom();
                        }
                    }
                    if(isContentView(view))
                        view.measure(android.view.View.MeasureSpec.makeMeasureSpec(j1 - layoutparams.leftMargin - layoutparams.rightMargin, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(i1 - layoutparams.topMargin - layoutparams.bottomMargin, 0x40000000));
                    else
                    if(isDrawerView(view))
                    {
                        if(SET_DRAWER_SHADOW_FROM_ELEVATION && ViewCompat.getElevation(view) != mDrawerElevation)
                            ViewCompat.setElevation(view, mDrawerElevation);
                        int l2 = getDrawerViewAbsoluteGravity(view) & 7;
                        boolean flag;
                        if(l2 == 3)
                            flag = true;
                        else
                            flag = false;
                        if(flag && l != 0 || !flag && k != 0)
                            throw new IllegalStateException((new StringBuilder()).append("Child drawer has absolute gravity ").append(gravityToString(l2)).append(" but this ").append("DrawerLayout").append(" already has a ").append("drawer view along that edge").toString());
                        if(flag)
                            l = 1;
                        else
                            k = 1;
                        view.measure(getChildMeasureSpec(i, mMinDrawerMargin + layoutparams.leftMargin + layoutparams.rightMargin, layoutparams.width), getChildMeasureSpec(j, layoutparams.topMargin + layoutparams.bottomMargin, layoutparams.height));
                    } else
                    {
                        throw new IllegalStateException((new StringBuilder()).append("Child ").append(view).append(" at index ").append(l1).append(" does not have a valid layout_gravity - must be Gravity.LEFT, ").append("Gravity.RIGHT or Gravity.NO_GRAVITY").toString());
                    }
                }
            } else
            {
                return;
            }
            l1++;
        } while(true);
        throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            if(((SavedState) (parcelable)).openDrawerGravity != 0)
            {
                View view = findDrawerWithGravity(((SavedState) (parcelable)).openDrawerGravity);
                if(view != null)
                    openDrawer(view);
            }
            if(((SavedState) (parcelable)).lockModeLeft != 3)
                setDrawerLockMode(((SavedState) (parcelable)).lockModeLeft, 3);
            if(((SavedState) (parcelable)).lockModeRight != 3)
                setDrawerLockMode(((SavedState) (parcelable)).lockModeRight, 5);
            if(((SavedState) (parcelable)).lockModeStart != 3)
                setDrawerLockMode(((SavedState) (parcelable)).lockModeStart, 0x800003);
            if(((SavedState) (parcelable)).lockModeEnd != 3)
            {
                setDrawerLockMode(((SavedState) (parcelable)).lockModeEnd, 0x800005);
                return;
            }
        }
    }

    public void onRtlPropertiesChanged(int i)
    {
        resolveShadowDrawables();
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        int j = getChildCount();
        int i = 0;
        do
        {
label0:
            {
                if(i < j)
                {
                    LayoutParams layoutparams = (LayoutParams)getChildAt(i).getLayoutParams();
                    boolean flag;
                    boolean flag1;
                    if(layoutparams.openState == 1)
                        flag = true;
                    else
                        flag = false;
                    if(layoutparams.openState == 2)
                        flag1 = true;
                    else
                        flag1 = false;
                    if(!flag && !flag1)
                        break label0;
                    savedstate.openDrawerGravity = layoutparams.gravity;
                }
                savedstate.lockModeLeft = mLockModeLeft;
                savedstate.lockModeRight = mLockModeRight;
                savedstate.lockModeStart = mLockModeStart;
                savedstate.lockModeEnd = mLockModeEnd;
                return savedstate;
            }
            i++;
        } while(true);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        mLeftDragger.processTouchEvent(motionevent);
        mRightDragger.processTouchEvent(motionevent);
        switch(motionevent.getAction() & 0xff)
        {
        case 2: // '\002'
        default:
            return true;

        case 0: // '\0'
            float f = motionevent.getX();
            float f2 = motionevent.getY();
            mInitialMotionX = f;
            mInitialMotionY = f2;
            mDisallowInterceptRequested = false;
            mChildrenCanceledTouch = false;
            return true;

        case 1: // '\001'
            float f3 = motionevent.getX();
            float f1 = motionevent.getY();
            boolean flag1 = true;
            motionevent = mLeftDragger.findTopChildUnder((int)f3, (int)f1);
            boolean flag = flag1;
            if(motionevent != null)
            {
                flag = flag1;
                if(isContentView(motionevent))
                {
                    f3 -= mInitialMotionX;
                    f1 -= mInitialMotionY;
                    int i = mLeftDragger.getTouchSlop();
                    flag = flag1;
                    if(f3 * f3 + f1 * f1 < (float)(i * i))
                    {
                        motionevent = findOpenDrawer();
                        flag = flag1;
                        if(motionevent != null)
                            if(getDrawerLockMode(motionevent) == 2)
                                flag = true;
                            else
                                flag = false;
                    }
                }
            }
            closeDrawers(flag);
            mDisallowInterceptRequested = false;
            return true;

        case 3: // '\003'
            closeDrawers(true);
            mDisallowInterceptRequested = false;
            mChildrenCanceledTouch = false;
            return true;
        }
    }

    public void openDrawer(int i)
    {
        openDrawer(i, true);
    }

    public void openDrawer(int i, boolean flag)
    {
        View view = findDrawerWithGravity(i);
        if(view == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("No drawer view found with gravity ").append(gravityToString(i)).toString());
        } else
        {
            openDrawer(view, flag);
            return;
        }
    }

    public void openDrawer(View view)
    {
        openDrawer(view, true);
    }

    public void openDrawer(View view, boolean flag)
    {
        if(!isDrawerView(view))
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a sliding drawer").toString());
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if(mFirstLayout)
        {
            layoutparams.onScreen = 1.0F;
            layoutparams.openState = 1;
            updateChildrenImportantForAccessibility(view, true);
        } else
        if(flag)
        {
            layoutparams.openState = layoutparams.openState | 2;
            if(checkDrawerViewAbsoluteGravity(view, 3))
                mLeftDragger.smoothSlideViewTo(view, 0, view.getTop());
            else
                mRightDragger.smoothSlideViewTo(view, getWidth() - view.getWidth(), view.getTop());
        } else
        {
            moveDrawerToOffset(view, 1.0F);
            updateDrawerState(layoutparams.gravity, 0, view);
            view.setVisibility(0);
        }
        invalidate();
    }

    public void removeDrawerListener(DrawerListener drawerlistener)
    {
        while(drawerlistener == null || mListeners == null) 
            return;
        mListeners.remove(drawerlistener);
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        super.requestDisallowInterceptTouchEvent(flag);
        mDisallowInterceptRequested = flag;
        if(flag)
            closeDrawers(true);
    }

    public void requestLayout()
    {
        if(!mInLayout)
            super.requestLayout();
    }

    public void setChildInsets(Object obj, boolean flag)
    {
        mLastInsets = obj;
        mDrawStatusBarBackground = flag;
        if(!flag && getBackground() == null)
            flag = true;
        else
            flag = false;
        setWillNotDraw(flag);
        requestLayout();
    }

    public void setDrawerElevation(float f)
    {
        mDrawerElevation = f;
        for(int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            if(isDrawerView(view))
                ViewCompat.setElevation(view, mDrawerElevation);
        }

    }

    public void setDrawerListener(DrawerListener drawerlistener)
    {
        if(mListener != null)
            removeDrawerListener(mListener);
        if(drawerlistener != null)
            addDrawerListener(drawerlistener);
        mListener = drawerlistener;
    }

    public void setDrawerLockMode(int i)
    {
        setDrawerLockMode(i, 3);
        setDrawerLockMode(i, 5);
    }

    public void setDrawerLockMode(int i, int j)
    {
        int k = GravityCompat.getAbsoluteGravity(j, ViewCompat.getLayoutDirection(this));
        j;
        JVM INSTR lookupswitch 4: default 52
    //                   3: 97
    //                   5: 105
    //                   8388611: 113
    //                   8388613: 121;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if(i != 0)
        {
            ViewDragHelper viewdraghelper;
            if(k == 3)
                viewdraghelper = mLeftDragger;
            else
                viewdraghelper = mRightDragger;
            viewdraghelper.cancel();
        }
        i;
        JVM INSTR tableswitch 1 2: default 96
    //                   1 157
    //                   2 138;
           goto _L6 _L7 _L8
_L6:
        break; /* Loop/switch isn't completed */
_L7:
        break MISSING_BLOCK_LABEL_164;
_L9:
        return;
_L2:
        mLockModeLeft = i;
          goto _L1
_L3:
        mLockModeRight = i;
          goto _L1
_L4:
        mLockModeStart = i;
          goto _L1
_L5:
        View view;
        mLockModeEnd = i;
          goto _L1
_L8:
        if((view = findDrawerWithGravity(k)) != null)
        {
            openDrawer(view);
            return;
        }
          goto _L9
        if((view = findDrawerWithGravity(k)) != null)
        {
            closeDrawer(view);
            return;
        }
          goto _L9
    }

    public void setDrawerLockMode(int i, View view)
    {
        if(!isDrawerView(view))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a ").append("drawer with appropriate layout_gravity").toString());
        } else
        {
            setDrawerLockMode(i, ((LayoutParams)view.getLayoutParams()).gravity);
            return;
        }
    }

    public void setDrawerShadow(int i, int j)
    {
        setDrawerShadow(ContextCompat.getDrawable(getContext(), i), j);
    }

    public void setDrawerShadow(Drawable drawable, int i)
    {
        if(!SET_DRAWER_SHADOW_FROM_ELEVATION) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if((i & 0x800003) == 0x800003)
            mShadowStart = drawable;
        else
        if((i & 0x800005) == 0x800005)
        {
            mShadowEnd = drawable;
        } else
        {
            if((i & 3) != 3)
                continue; /* Loop/switch isn't completed */
            mShadowLeft = drawable;
        }
_L4:
        resolveShadowDrawables();
        invalidate();
        return;
        if((i & 5) != 5) goto _L1; else goto _L3
_L3:
        mShadowRight = drawable;
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void setDrawerTitle(int i, CharSequence charsequence)
    {
        i = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        if(i == 3)
            mTitleLeft = charsequence;
        else
        if(i == 5)
        {
            mTitleRight = charsequence;
            return;
        }
    }

    void setDrawerViewOffset(View view, float f)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if(f == layoutparams.onScreen)
        {
            return;
        } else
        {
            layoutparams.onScreen = f;
            dispatchOnDrawerSlide(view, f);
            return;
        }
    }

    public void setScrimColor(int i)
    {
        mScrimColor = i;
        invalidate();
    }

    public void setStatusBarBackground(int i)
    {
        Drawable drawable;
        if(i != 0)
            drawable = ContextCompat.getDrawable(getContext(), i);
        else
            drawable = null;
        mStatusBarBackground = drawable;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable)
    {
        mStatusBarBackground = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i)
    {
        mStatusBarBackground = new ColorDrawable(i);
        invalidate();
    }

    void updateDrawerState(int i, int j, View view)
    {
        LayoutParams layoutparams;
        i = mLeftDragger.getViewDragState();
        int k = mRightDragger.getViewDragState();
        if(i == 1 || k == 1)
            i = 1;
        else
        if(i == 2 || k == 2)
            i = 2;
        else
            i = 0;
        if(view == null || j != 0) goto _L2; else goto _L1
_L1:
        layoutparams = (LayoutParams)view.getLayoutParams();
        if(layoutparams.onScreen != 0.0F) goto _L4; else goto _L3
_L3:
        dispatchOnDrawerClosed(view);
_L2:
        if(i != mDrawerState)
        {
            mDrawerState = i;
            if(mListeners != null)
                for(j = mListeners.size() - 1; j >= 0; j--)
                    ((DrawerListener)mListeners.get(j)).onDrawerStateChanged(i);

        }
        break; /* Loop/switch isn't completed */
_L4:
        if(layoutparams.onScreen == 1.0F)
            dispatchOnDrawerOpened(view);
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static final boolean ALLOW_EDGE_LOCK = false;
    static final boolean CAN_HIDE_DESCENDANTS;
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    private static final int DEFAULT_SCRIM_COLOR = 0x99000000;
    private static final int DRAWER_ELEVATION = 10;
    static final int LAYOUT_ATTRS[] = {
        0x10100b3
    };
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNDEFINED = 3;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 64;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int PEEK_DELAY = 160;
    private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "DrawerLayout";
    private static final int THEME_ATTRS[] = {
        0x1010434
    };
    private static final float TOUCH_SLOP_SENSITIVITY = 1F;
    private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private boolean mChildrenCanceledTouch;
    private boolean mDisallowInterceptRequested;
    private boolean mDrawStatusBarBackground;
    private float mDrawerElevation;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private Object mLastInsets;
    private final ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;
    private DrawerListener mListener;
    private List mListeners;
    private int mLockModeEnd;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mLockModeStart;
    private int mMinDrawerMargin;
    private final ArrayList mNonDrawerViews;
    private final ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowEnd;
    private Drawable mShadowLeft;
    private Drawable mShadowLeftResolved;
    private Drawable mShadowRight;
    private Drawable mShadowRightResolved;
    private Drawable mShadowStart;
    private Drawable mStatusBarBackground;
    private CharSequence mTitleLeft;
    private CharSequence mTitleRight;

    static 
    {
        boolean flag1 = true;
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT >= 19)
            flag = true;
        else
            flag = false;
        CAN_HIDE_DESCENDANTS = flag;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            flag = flag1;
        else
            flag = false;
        SET_DRAWER_SHADOW_FROM_ELEVATION = flag;
    }

    // Unreferenced inner class android/support/v4/widget/DrawerLayout$ViewDragCallback$1

/* anonymous class */
    class ViewDragCallback._cls1
        implements Runnable
    {

        public void run()
        {
            peekDrawer();
        }

        final ViewDragCallback this$1;

            
            {
                this$1 = ViewDragCallback.this;
                super();
            }
    }

}
