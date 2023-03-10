// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.content.Context;
import android.content.res.*;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Locale;

// Referenced classes of package android.support.v4.view:
//            ViewPager, PagerAdapter

public class PagerTitleStrip extends ViewGroup
{
    private class PageListener extends DataSetObserver
        implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener
    {

        public void onAdapterChanged(ViewPager viewpager, PagerAdapter pageradapter, PagerAdapter pageradapter1)
        {
            updateAdapter(pageradapter, pageradapter1);
        }

        public void onChanged()
        {
            float f = 0.0F;
            updateText(mPager.getCurrentItem(), mPager.getAdapter());
            if(mLastKnownPositionOffset >= 0.0F)
                f = mLastKnownPositionOffset;
            updateTextPositions(mPager.getCurrentItem(), f, true);
        }

        public void onPageScrollStateChanged(int i)
        {
            mScrollState = i;
        }

        public void onPageScrolled(int i, float f, int j)
        {
            j = i;
            if(f > 0.5F)
                j = i + 1;
            updateTextPositions(j, f, false);
        }

        public void onPageSelected(int i)
        {
            float f = 0.0F;
            if(mScrollState == 0)
            {
                updateText(mPager.getCurrentItem(), mPager.getAdapter());
                if(mLastKnownPositionOffset >= 0.0F)
                    f = mLastKnownPositionOffset;
                updateTextPositions(mPager.getCurrentItem(), f, true);
            }
        }

        private int mScrollState;
        final PagerTitleStrip this$0;

        PageListener()
        {
            this$0 = PagerTitleStrip.this;
            super();
        }
    }

    private static class SingleLineAllCapsTransform extends SingleLineTransformationMethod
    {

        public CharSequence getTransformation(CharSequence charsequence, View view)
        {
            charsequence = super.getTransformation(charsequence, view);
            if(charsequence != null)
                return charsequence.toString().toUpperCase(mLocale);
            else
                return null;
        }

        private Locale mLocale;

        SingleLineAllCapsTransform(Context context)
        {
            mLocale = context.getResources().getConfiguration().locale;
        }
    }


    public PagerTitleStrip(Context context)
    {
        this(context, null);
    }

    public PagerTitleStrip(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mLastKnownCurrentPage = -1;
        mLastKnownPositionOffset = -1F;
        mPageListener = new PageListener();
        TextView textview = new TextView(context);
        mPrevText = textview;
        addView(textview);
        textview = new TextView(context);
        mCurrText = textview;
        addView(textview);
        textview = new TextView(context);
        mNextText = textview;
        addView(textview);
        attributeset = context.obtainStyledAttributes(attributeset, ATTRS);
        int i = attributeset.getResourceId(0, 0);
        if(i != 0)
        {
            TextViewCompat.setTextAppearance(mPrevText, i);
            TextViewCompat.setTextAppearance(mCurrText, i);
            TextViewCompat.setTextAppearance(mNextText, i);
        }
        int j = attributeset.getDimensionPixelSize(1, 0);
        if(j != 0)
            setTextSize(0, j);
        if(attributeset.hasValue(2))
        {
            int k = attributeset.getColor(2, 0);
            mPrevText.setTextColor(k);
            mCurrText.setTextColor(k);
            mNextText.setTextColor(k);
        }
        mGravity = attributeset.getInteger(3, 80);
        attributeset.recycle();
        mTextColor = mCurrText.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6F);
        mPrevText.setEllipsize(android.text.TextUtils.TruncateAt.END);
        mCurrText.setEllipsize(android.text.TextUtils.TruncateAt.END);
        mNextText.setEllipsize(android.text.TextUtils.TruncateAt.END);
        boolean flag = false;
        if(i != 0)
        {
            attributeset = context.obtainStyledAttributes(i, TEXT_ATTRS);
            flag = attributeset.getBoolean(0, false);
            attributeset.recycle();
        }
        if(flag)
        {
            setSingleLineAllCaps(mPrevText);
            setSingleLineAllCaps(mCurrText);
            setSingleLineAllCaps(mNextText);
        } else
        {
            mPrevText.setSingleLine();
            mCurrText.setSingleLine();
            mNextText.setSingleLine();
        }
        mScaledTextSpacing = (int)(16F * context.getResources().getDisplayMetrics().density);
    }

    private static void setSingleLineAllCaps(TextView textview)
    {
        textview.setTransformationMethod(new SingleLineAllCapsTransform(textview.getContext()));
    }

    int getMinHeight()
    {
        int i = 0;
        Drawable drawable = getBackground();
        if(drawable != null)
            i = drawable.getIntrinsicHeight();
        return i;
    }

    public int getTextSpacing()
    {
        return mScaledTextSpacing;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        Object obj = getParent();
        if(!(obj instanceof ViewPager))
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        obj = (ViewPager)obj;
        PagerAdapter pageradapter = ((ViewPager) (obj)).getAdapter();
        ((ViewPager) (obj)).setInternalPageChangeListener(mPageListener);
        ((ViewPager) (obj)).addOnAdapterChangeListener(mPageListener);
        mPager = ((ViewPager) (obj));
        if(mWatchingAdapter != null)
            obj = (PagerAdapter)mWatchingAdapter.get();
        else
            obj = null;
        updateAdapter(((PagerAdapter) (obj)), pageradapter);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mPager != null)
        {
            updateAdapter(mPager.getAdapter(), null);
            mPager.setInternalPageChangeListener(null);
            mPager.removeOnAdapterChangeListener(mPageListener);
            mPager = null;
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        float f = 0.0F;
        if(mPager != null)
        {
            if(mLastKnownPositionOffset >= 0.0F)
                f = mLastKnownPositionOffset;
            updateTextPositions(mLastKnownCurrentPage, f, true);
        }
    }

    protected void onMeasure(int i, int j)
    {
        if(android.view.View.MeasureSpec.getMode(i) != 0x40000000)
            throw new IllegalStateException("Must measure with an exact width");
        int l = getPaddingTop() + getPaddingBottom();
        int i1 = getChildMeasureSpec(j, l, -2);
        int k = android.view.View.MeasureSpec.getSize(i);
        i = getChildMeasureSpec(i, (int)((float)k * 0.2F), -2);
        mPrevText.measure(i, i1);
        mCurrText.measure(i, i1);
        mNextText.measure(i, i1);
        if(android.view.View.MeasureSpec.getMode(j) == 0x40000000)
        {
            i = android.view.View.MeasureSpec.getSize(j);
        } else
        {
            i = mCurrText.getMeasuredHeight();
            i = Math.max(getMinHeight(), i + l);
        }
        setMeasuredDimension(k, View.resolveSizeAndState(i, j, mCurrText.getMeasuredState() << 16));
    }

    public void requestLayout()
    {
        if(!mUpdatingText)
            super.requestLayout();
    }

    public void setGravity(int i)
    {
        mGravity = i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f)
    {
        mNonPrimaryAlpha = (int)(255F * f) & 0xff;
        int i = mNonPrimaryAlpha << 24 | mTextColor & 0xffffff;
        mPrevText.setTextColor(i);
        mNextText.setTextColor(i);
    }

    public void setTextColor(int i)
    {
        mTextColor = i;
        mCurrText.setTextColor(i);
        i = mNonPrimaryAlpha << 24 | mTextColor & 0xffffff;
        mPrevText.setTextColor(i);
        mNextText.setTextColor(i);
    }

    public void setTextSize(int i, float f)
    {
        mPrevText.setTextSize(i, f);
        mCurrText.setTextSize(i, f);
        mNextText.setTextSize(i, f);
    }

    public void setTextSpacing(int i)
    {
        mScaledTextSpacing = i;
        requestLayout();
    }

    void updateAdapter(PagerAdapter pageradapter, PagerAdapter pageradapter1)
    {
        if(pageradapter != null)
        {
            pageradapter.unregisterDataSetObserver(mPageListener);
            mWatchingAdapter = null;
        }
        if(pageradapter1 != null)
        {
            pageradapter1.registerDataSetObserver(mPageListener);
            mWatchingAdapter = new WeakReference(pageradapter1);
        }
        if(mPager != null)
        {
            mLastKnownCurrentPage = -1;
            mLastKnownPositionOffset = -1F;
            updateText(mPager.getCurrentItem(), pageradapter1);
            requestLayout();
        }
    }

    void updateText(int i, PagerAdapter pageradapter)
    {
        int j;
        int k;
        CharSequence charsequence;
        TextView textview;
        if(pageradapter != null)
            j = pageradapter.getCount();
        else
            j = 0;
        mUpdatingText = true;
        textview = null;
        charsequence = textview;
        if(i >= 1)
        {
            charsequence = textview;
            if(pageradapter != null)
                charsequence = pageradapter.getPageTitle(i - 1);
        }
        mPrevText.setText(charsequence);
        textview = mCurrText;
        if(pageradapter != null && i < j)
            charsequence = pageradapter.getPageTitle(i);
        else
            charsequence = null;
        textview.setText(charsequence);
        textview = null;
        charsequence = textview;
        if(i + 1 < j)
        {
            charsequence = textview;
            if(pageradapter != null)
                charsequence = pageradapter.getPageTitle(i + 1);
        }
        mNextText.setText(charsequence);
        j = android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, (int)((float)(getWidth() - getPaddingLeft() - getPaddingRight()) * 0.8F)), 0x80000000);
        k = android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, getHeight() - getPaddingTop() - getPaddingBottom()), 0x80000000);
        mPrevText.measure(j, k);
        mCurrText.measure(j, k);
        mNextText.measure(j, k);
        mLastKnownCurrentPage = i;
        if(!mUpdatingPositions)
            updateTextPositions(i, mLastKnownPositionOffset, false);
        mUpdatingText = false;
    }

    void updateTextPositions(int i, float f, boolean flag)
    {
        int j;
        int k;
        int k2;
        int l2;
        int i3;
        int j3;
        float f1;
        float f2;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k3;
        int l3;
        if(i != mLastKnownCurrentPage)
            updateText(i, mPager.getAdapter());
        else
        if(!flag && f == mLastKnownPositionOffset)
            return;
        mUpdatingPositions = true;
        i1 = mPrevText.getMeasuredWidth();
        j2 = mCurrText.getMeasuredWidth();
        l = mNextText.getMeasuredWidth();
        i2 = j2 / 2;
        j1 = getWidth();
        j = getHeight();
        l1 = getPaddingLeft();
        k1 = getPaddingRight();
        i = getPaddingTop();
        k = getPaddingBottom();
        k2 = k1 + i2;
        f2 = f + 0.5F;
        f1 = f2;
        if(f2 > 1.0F)
            f1 = f2 - 1.0F;
        i2 = j1 - k2 - (int)((float)(j1 - (l1 + i2) - k2) * f1) - j2 / 2;
        j2 = i2 + j2;
        i3 = mPrevText.getBaseline();
        l2 = mCurrText.getBaseline();
        k2 = mNextText.getBaseline();
        j3 = Math.max(Math.max(i3, l2), k2);
        i3 = j3 - i3;
        l2 = j3 - l2;
        k2 = j3 - k2;
        j3 = mPrevText.getMeasuredHeight();
        k3 = mCurrText.getMeasuredHeight();
        l3 = mNextText.getMeasuredHeight();
        j3 = Math.max(Math.max(i3 + j3, l2 + k3), k2 + l3);
        mGravity & 0x70;
        JVM INSTR lookupswitch 2: default 304
    //                   16: 456
    //                   80: 489;
           goto _L1 _L2 _L3
_L1:
        k = i + i3;
        j = i + l2;
        i += k2;
_L5:
        mCurrText.layout(i2, j, j2, mCurrText.getMeasuredHeight() + j);
        j = Math.min(l1, i2 - mScaledTextSpacing - i1);
        mPrevText.layout(j, k, j + i1, mPrevText.getMeasuredHeight() + k);
        j = Math.max(j1 - k1 - l, mScaledTextSpacing + j2);
        mNextText.layout(j, i, j + l, mNextText.getMeasuredHeight() + i);
        mLastKnownPositionOffset = f;
        mUpdatingPositions = false;
        return;
_L2:
        i = (j - i - k - j3) / 2;
        k = i + i3;
        j = i + l2;
        i += k2;
        continue; /* Loop/switch isn't completed */
_L3:
        i = j - k - j3;
        k = i + i3;
        j = i + l2;
        i += k2;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static final int ATTRS[] = {
        0x1010034, 0x1010095, 0x1010098, 0x10100af
    };
    private static final float SIDE_ALPHA = 0.6F;
    private static final int TEXT_ATTRS[] = {
        0x101038c
    };
    private static final int TEXT_SPACING = 16;
    TextView mCurrText;
    private int mGravity;
    private int mLastKnownCurrentPage;
    float mLastKnownPositionOffset;
    TextView mNextText;
    private int mNonPrimaryAlpha;
    private final PageListener mPageListener;
    ViewPager mPager;
    TextView mPrevText;
    private int mScaledTextSpacing;
    int mTextColor;
    private boolean mUpdatingPositions;
    private boolean mUpdatingText;
    private WeakReference mWatchingAdapter;

}
