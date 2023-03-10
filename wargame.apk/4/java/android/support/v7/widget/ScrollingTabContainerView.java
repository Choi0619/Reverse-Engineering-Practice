// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.view.ActionBarPolicy;
import android.text.TextUtils;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.*;

// Referenced classes of package android.support.v7.widget:
//            AppCompatSpinner, LinearLayoutCompat, TintTypedArray, AppCompatImageView, 
//            AppCompatTextView, TooltipCompat

public class ScrollingTabContainerView extends HorizontalScrollView
    implements android.widget.AdapterView.OnItemSelectedListener
{
    private class TabAdapter extends BaseAdapter
    {

        public int getCount()
        {
            return mTabLayout.getChildCount();
        }

        public Object getItem(int i)
        {
            return ((TabView)mTabLayout.getChildAt(i)).getTab();
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if(view == null)
            {
                return createTabView((android.support.v7.app.ActionBar.Tab)getItem(i), true);
            } else
            {
                ((TabView)view).bindTab((android.support.v7.app.ActionBar.Tab)getItem(i));
                return view;
            }
        }

        final ScrollingTabContainerView this$0;

        TabAdapter()
        {
            this$0 = ScrollingTabContainerView.this;
            super();
        }
    }

    private class TabClickListener
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            ((TabView)view).getTab().select();
            int j = mTabLayout.getChildCount();
            int i = 0;
            while(i < j) 
            {
                View view1 = mTabLayout.getChildAt(i);
                boolean flag;
                if(view1 == view)
                    flag = true;
                else
                    flag = false;
                view1.setSelected(flag);
                i++;
            }
        }

        final ScrollingTabContainerView this$0;

        TabClickListener()
        {
            this$0 = ScrollingTabContainerView.this;
            super();
        }
    }

    private class TabView extends LinearLayoutCompat
    {

        public void bindTab(android.support.v7.app.ActionBar.Tab tab)
        {
            mTab = tab;
            update();
        }

        public android.support.v7.app.ActionBar.Tab getTab()
        {
            return mTab;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(accessibilityevent);
            accessibilityevent.setClassName(android/support/v7/app/ActionBar$Tab.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
        {
            super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
            accessibilitynodeinfo.setClassName(android/support/v7/app/ActionBar$Tab.getName());
        }

        public void onMeasure(int i, int j)
        {
            super.onMeasure(i, j);
            if(mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth)
                super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(mMaxTabWidth, 0x40000000), j);
        }

        public void setSelected(boolean flag)
        {
            boolean flag1;
            if(isSelected() != flag)
                flag1 = true;
            else
                flag1 = false;
            super.setSelected(flag);
            if(flag1 && flag)
                sendAccessibilityEvent(4);
        }

        public void update()
        {
            Object obj = null;
            android.support.v7.app.ActionBar.Tab tab = mTab;
            Object obj1 = tab.getCustomView();
            if(obj1 != null)
            {
                obj = ((View) (obj1)).getParent();
                if(obj != this)
                {
                    if(obj != null)
                        ((ViewGroup)obj).removeView(((View) (obj1)));
                    addView(((View) (obj1)));
                }
                mCustomView = ((View) (obj1));
                if(mTextView != null)
                    mTextView.setVisibility(8);
                if(mIconView != null)
                {
                    mIconView.setVisibility(8);
                    mIconView.setImageDrawable(null);
                }
                return;
            }
            if(mCustomView != null)
            {
                removeView(mCustomView);
                mCustomView = null;
            }
            android.graphics.drawable.Drawable drawable = tab.getIcon();
            obj1 = tab.getText();
            boolean flag;
            if(drawable != null)
            {
                if(mIconView == null)
                {
                    AppCompatImageView appcompatimageview = new AppCompatImageView(getContext());
                    LinearLayoutCompat.LayoutParams layoutparams1 = new LinearLayoutCompat.LayoutParams(-2, -2);
                    layoutparams1.gravity = 16;
                    appcompatimageview.setLayoutParams(layoutparams1);
                    addView(appcompatimageview, 0);
                    mIconView = appcompatimageview;
                }
                mIconView.setImageDrawable(drawable);
                mIconView.setVisibility(0);
            } else
            if(mIconView != null)
            {
                mIconView.setVisibility(8);
                mIconView.setImageDrawable(null);
            }
            if(!TextUtils.isEmpty(((CharSequence) (obj1))))
                flag = true;
            else
                flag = false;
            if(flag)
            {
                if(mTextView == null)
                {
                    AppCompatTextView appcompattextview = new AppCompatTextView(getContext(), null, android.support.v7.appcompat.R.attr.actionBarTabTextStyle);
                    appcompattextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
                    LinearLayoutCompat.LayoutParams layoutparams = new LinearLayoutCompat.LayoutParams(-2, -2);
                    layoutparams.gravity = 16;
                    appcompattextview.setLayoutParams(layoutparams);
                    addView(appcompattextview);
                    mTextView = appcompattextview;
                }
                mTextView.setText(((CharSequence) (obj1)));
                mTextView.setVisibility(0);
            } else
            if(mTextView != null)
            {
                mTextView.setVisibility(8);
                mTextView.setText(null);
            }
            if(mIconView != null)
                mIconView.setContentDescription(tab.getContentDescription());
            if(!flag)
                obj = tab.getContentDescription();
            TooltipCompat.setTooltipText(this, ((CharSequence) (obj)));
        }

        private final int BG_ATTRS[] = {
            0x10100d4
        };
        private View mCustomView;
        private ImageView mIconView;
        private android.support.v7.app.ActionBar.Tab mTab;
        private TextView mTextView;
        final ScrollingTabContainerView this$0;

        public TabView(Context context, android.support.v7.app.ActionBar.Tab tab, boolean flag)
        {
            this$0 = ScrollingTabContainerView.this;
            super(context, null, android.support.v7.appcompat.R.attr.actionBarTabStyle);
            mTab = tab;
            scrollingtabcontainerview = TintTypedArray.obtainStyledAttributes(context, null, BG_ATTRS, android.support.v7.appcompat.R.attr.actionBarTabStyle, 0);
            if(hasValue(0))
                setBackgroundDrawable(getDrawable(0));
            recycle();
            if(flag)
                setGravity(0x800013);
            update();
        }
    }

    protected class VisibilityAnimListener extends AnimatorListenerAdapter
    {

        public void onAnimationCancel(Animator animator)
        {
            mCanceled = true;
        }

        public void onAnimationEnd(Animator animator)
        {
            if(mCanceled)
            {
                return;
            } else
            {
                mVisibilityAnim = null;
                setVisibility(mFinalVisibility);
                return;
            }
        }

        public void onAnimationStart(Animator animator)
        {
            setVisibility(0);
            mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimator viewpropertyanimator, int i)
        {
            mFinalVisibility = i;
            mVisibilityAnim = viewpropertyanimator;
            return this;
        }

        private boolean mCanceled;
        private int mFinalVisibility;
        final ScrollingTabContainerView this$0;

        protected VisibilityAnimListener()
        {
            this$0 = ScrollingTabContainerView.this;
            super();
            mCanceled = false;
        }
    }


    public ScrollingTabContainerView(Context context)
    {
        super(context);
        setHorizontalScrollBarEnabled(false);
        context = ActionBarPolicy.get(context);
        setContentHeight(context.getTabContainerHeight());
        mStackedTabMaxWidth = context.getStackedTabMaxWidth();
        mTabLayout = createTabLayout();
        addView(mTabLayout, new android.view.ViewGroup.LayoutParams(-2, -1));
    }

    private Spinner createSpinner()
    {
        AppCompatSpinner appcompatspinner = new AppCompatSpinner(getContext(), null, android.support.v7.appcompat.R.attr.actionDropDownStyle);
        appcompatspinner.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        appcompatspinner.setOnItemSelectedListener(this);
        return appcompatspinner;
    }

    private LinearLayoutCompat createTabLayout()
    {
        LinearLayoutCompat linearlayoutcompat = new LinearLayoutCompat(getContext(), null, android.support.v7.appcompat.R.attr.actionBarTabBarStyle);
        linearlayoutcompat.setMeasureWithLargestChildEnabled(true);
        linearlayoutcompat.setGravity(17);
        linearlayoutcompat.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        return linearlayoutcompat;
    }

    private boolean isCollapsed()
    {
        return mTabSpinner != null && mTabSpinner.getParent() == this;
    }

    private void performCollapse()
    {
        if(isCollapsed())
            return;
        if(mTabSpinner == null)
            mTabSpinner = createSpinner();
        removeView(mTabLayout);
        addView(mTabSpinner, new android.view.ViewGroup.LayoutParams(-2, -1));
        if(mTabSpinner.getAdapter() == null)
            mTabSpinner.setAdapter(new TabAdapter());
        if(mTabSelector != null)
        {
            removeCallbacks(mTabSelector);
            mTabSelector = null;
        }
        mTabSpinner.setSelection(mSelectedTabIndex);
    }

    private boolean performExpand()
    {
        if(!isCollapsed())
        {
            return false;
        } else
        {
            removeView(mTabSpinner);
            addView(mTabLayout, new android.view.ViewGroup.LayoutParams(-2, -1));
            setTabSelected(mTabSpinner.getSelectedItemPosition());
            return false;
        }
    }

    public void addTab(android.support.v7.app.ActionBar.Tab tab, int i, boolean flag)
    {
        tab = createTabView(tab, false);
        mTabLayout.addView(tab, i, new LinearLayoutCompat.LayoutParams(0, -1, 1.0F));
        if(mTabSpinner != null)
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        if(flag)
            tab.setSelected(true);
        if(mAllowCollapse)
            requestLayout();
    }

    public void addTab(android.support.v7.app.ActionBar.Tab tab, boolean flag)
    {
        tab = createTabView(tab, false);
        mTabLayout.addView(tab, new LinearLayoutCompat.LayoutParams(0, -1, 1.0F));
        if(mTabSpinner != null)
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        if(flag)
            tab.setSelected(true);
        if(mAllowCollapse)
            requestLayout();
    }

    public void animateToTab(int i)
    {
        final View tabView = mTabLayout.getChildAt(i);
        if(mTabSelector != null)
            removeCallbacks(mTabSelector);
        mTabSelector = new Runnable() {

            public void run()
            {
                int j = tabView.getLeft();
                int k = (getWidth() - tabView.getWidth()) / 2;
                smoothScrollTo(j - k, 0);
                mTabSelector = null;
            }

            final ScrollingTabContainerView this$0;
            final View val$tabView;

            
            {
                this$0 = ScrollingTabContainerView.this;
                tabView = view;
                super();
            }
        }
;
        post(mTabSelector);
    }

    public void animateToVisibility(int i)
    {
        if(mVisibilityAnim != null)
            mVisibilityAnim.cancel();
        if(i == 0)
        {
            if(getVisibility() != 0)
                setAlpha(0.0F);
            ViewPropertyAnimator viewpropertyanimator = animate().alpha(1.0F);
            viewpropertyanimator.setDuration(200L);
            viewpropertyanimator.setInterpolator(sAlphaInterpolator);
            viewpropertyanimator.setListener(mVisAnimListener.withFinalVisibility(viewpropertyanimator, i));
            viewpropertyanimator.start();
            return;
        } else
        {
            ViewPropertyAnimator viewpropertyanimator1 = animate().alpha(0.0F);
            viewpropertyanimator1.setDuration(200L);
            viewpropertyanimator1.setInterpolator(sAlphaInterpolator);
            viewpropertyanimator1.setListener(mVisAnimListener.withFinalVisibility(viewpropertyanimator1, i));
            viewpropertyanimator1.start();
            return;
        }
    }

    TabView createTabView(android.support.v7.app.ActionBar.Tab tab, boolean flag)
    {
        tab = new TabView(getContext(), tab, flag);
        if(flag)
        {
            tab.setBackgroundDrawable(null);
            tab.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, mContentHeight));
            return tab;
        }
        tab.setFocusable(true);
        if(mTabClickListener == null)
            mTabClickListener = new TabClickListener();
        tab.setOnClickListener(mTabClickListener);
        return tab;
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mTabSelector != null)
            post(mTabSelector);
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        configuration = ActionBarPolicy.get(getContext());
        setContentHeight(configuration.getTabContainerHeight());
        mStackedTabMaxWidth = configuration.getStackedTabMaxWidth();
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mTabSelector != null)
            removeCallbacks(mTabSelector);
    }

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        ((TabView)view).getTab().select();
    }

    public void onMeasure(int i, int j)
    {
        j = android.view.View.MeasureSpec.getMode(i);
        int k;
        boolean flag;
        if(j == 0x40000000)
            flag = true;
        else
            flag = false;
        setFillViewport(flag);
        k = mTabLayout.getChildCount();
        if(k > 1 && (j == 0x40000000 || j == 0x80000000))
        {
            if(k > 2)
                mMaxTabWidth = (int)((float)android.view.View.MeasureSpec.getSize(i) * 0.4F);
            else
                mMaxTabWidth = android.view.View.MeasureSpec.getSize(i) / 2;
            mMaxTabWidth = Math.min(mMaxTabWidth, mStackedTabMaxWidth);
        } else
        {
            mMaxTabWidth = -1;
        }
        k = android.view.View.MeasureSpec.makeMeasureSpec(mContentHeight, 0x40000000);
        if(!flag && mAllowCollapse)
            j = 1;
        else
            j = 0;
        if(j != 0)
        {
            mTabLayout.measure(0, k);
            if(mTabLayout.getMeasuredWidth() > android.view.View.MeasureSpec.getSize(i))
                performCollapse();
            else
                performExpand();
        } else
        {
            performExpand();
        }
        j = getMeasuredWidth();
        super.onMeasure(i, k);
        i = getMeasuredWidth();
        if(flag && j != i)
            setTabSelected(mSelectedTabIndex);
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    public void removeAllTabs()
    {
        mTabLayout.removeAllViews();
        if(mTabSpinner != null)
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        if(mAllowCollapse)
            requestLayout();
    }

    public void removeTabAt(int i)
    {
        mTabLayout.removeViewAt(i);
        if(mTabSpinner != null)
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        if(mAllowCollapse)
            requestLayout();
    }

    public void setAllowCollapse(boolean flag)
    {
        mAllowCollapse = flag;
    }

    public void setContentHeight(int i)
    {
        mContentHeight = i;
        requestLayout();
    }

    public void setTabSelected(int i)
    {
        mSelectedTabIndex = i;
        int k = mTabLayout.getChildCount();
        int j = 0;
        while(j < k) 
        {
            View view = mTabLayout.getChildAt(j);
            boolean flag;
            if(j == i)
                flag = true;
            else
                flag = false;
            view.setSelected(flag);
            if(flag)
                animateToTab(i);
            j++;
        }
        if(mTabSpinner != null && i >= 0)
            mTabSpinner.setSelection(i);
    }

    public void updateTab(int i)
    {
        ((TabView)mTabLayout.getChildAt(i)).update();
        if(mTabSpinner != null)
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        if(mAllowCollapse)
            requestLayout();
    }

    private static final int FADE_DURATION = 200;
    private static final String TAG = "ScrollingTabContainerView";
    private static final Interpolator sAlphaInterpolator = new DecelerateInterpolator();
    private boolean mAllowCollapse;
    private int mContentHeight;
    int mMaxTabWidth;
    private int mSelectedTabIndex;
    int mStackedTabMaxWidth;
    private TabClickListener mTabClickListener;
    LinearLayoutCompat mTabLayout;
    Runnable mTabSelector;
    private Spinner mTabSpinner;
    protected final VisibilityAnimListener mVisAnimListener = new VisibilityAnimListener();
    protected ViewPropertyAnimator mVisibilityAnim;

}
