// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.widget.AutoSizeableTextView;
import android.util.AttributeSet;
import android.widget.TextView;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatBackgroundHelper, AppCompatTextHelper

public class AppCompatTextView extends TextView
    implements TintableBackgroundView, AutoSizeableTextView
{

    public AppCompatTextView(Context context)
    {
        this(context, null);
    }

    public AppCompatTextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010084);
    }

    public AppCompatTextView(Context context, AttributeSet attributeset, int i)
    {
        super(TintContextWrapper.wrap(context), attributeset, i);
        mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attributeset, i);
        mTextHelper = AppCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attributeset, i);
        mTextHelper.applyCompoundDrawablesTints();
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.applySupportBackgroundTint();
        if(mTextHelper != null)
            mTextHelper.applyCompoundDrawablesTints();
    }

    public int getAutoSizeMaxTextSize()
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return super.getAutoSizeMaxTextSize();
        if(mTextHelper != null)
            return mTextHelper.getAutoSizeMaxTextSize();
        else
            return -1;
    }

    public int getAutoSizeMinTextSize()
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return super.getAutoSizeMinTextSize();
        if(mTextHelper != null)
            return mTextHelper.getAutoSizeMinTextSize();
        else
            return -1;
    }

    public int getAutoSizeStepGranularity()
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return super.getAutoSizeStepGranularity();
        if(mTextHelper != null)
            return mTextHelper.getAutoSizeStepGranularity();
        else
            return -1;
    }

    public int[] getAutoSizeTextAvailableSizes()
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return super.getAutoSizeTextAvailableSizes();
        if(mTextHelper != null)
            return mTextHelper.getAutoSizeTextAvailableSizes();
        else
            return new int[0];
    }

    public int getAutoSizeTextType()
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return super.getAutoSizeTextType() != 1 ? 0 : 1;
        if(mTextHelper != null)
            return mTextHelper.getAutoSizeTextType();
        else
            return 0;
    }

    public ColorStateList getSupportBackgroundTintList()
    {
        if(mBackgroundTintHelper != null)
            return mBackgroundTintHelper.getSupportBackgroundTintList();
        else
            return null;
    }

    public android.graphics.PorterDuff.Mode getSupportBackgroundTintMode()
    {
        if(mBackgroundTintHelper != null)
            return mBackgroundTintHelper.getSupportBackgroundTintMode();
        else
            return null;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(mTextHelper != null)
            mTextHelper.onLayout(flag, i, j, k, l);
    }

    protected void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        super.onTextChanged(charsequence, i, j, k);
        if(mTextHelper != null && android.os.Build.VERSION.SDK_INT < 26 && mTextHelper.isAutoSizeEnabled())
            mTextHelper.autoSizeText();
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int j, int k, int l)
        throws IllegalArgumentException
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            super.setAutoSizeTextTypeUniformWithConfiguration(i, j, k, l);
        else
        if(mTextHelper != null)
        {
            mTextHelper.setAutoSizeTextTypeUniformWithConfiguration(i, j, k, l);
            return;
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int ai[], int i)
        throws IllegalArgumentException
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            super.setAutoSizeTextTypeUniformWithPresetSizes(ai, i);
        else
        if(mTextHelper != null)
        {
            mTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(ai, i);
            return;
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            super.setAutoSizeTextTypeWithDefaults(i);
        else
        if(mTextHelper != null)
        {
            mTextHelper.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        super.setBackgroundDrawable(drawable);
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.onSetBackgroundDrawable(drawable);
    }

    public void setBackgroundResource(int i)
    {
        super.setBackgroundResource(i);
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.onSetBackgroundResource(i);
    }

    public void setSupportBackgroundTintList(ColorStateList colorstatelist)
    {
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.setSupportBackgroundTintList(colorstatelist);
    }

    public void setSupportBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.setSupportBackgroundTintMode(mode);
    }

    public void setTextAppearance(Context context, int i)
    {
        super.setTextAppearance(context, i);
        if(mTextHelper != null)
            mTextHelper.onSetTextAppearance(context, i);
    }

    public void setTextSize(int i, float f)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            super.setTextSize(i, f);
        else
        if(mTextHelper != null)
        {
            mTextHelper.setTextSize(i, f);
            return;
        }
    }

    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;
}
