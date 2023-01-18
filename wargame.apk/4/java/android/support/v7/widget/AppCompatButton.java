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
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatBackgroundHelper, AppCompatTextHelper

public class AppCompatButton extends Button
    implements TintableBackgroundView, AutoSizeableTextView
{

    public AppCompatButton(Context context)
    {
        AppCompatButton(context, null);
    }

    public AppCompatButton(Context context, AttributeSet attributeset)
    {
        AppCompatButton(context, attributeset, android.support.v7.appcompat.R.attr.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attributeset, int i)
    {
        Button(TintContextWrapper.wrap(context), attributeset, i);
        mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attributeset, i);
        mTextHelper = AppCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attributeset, i);
        mTextHelper.applyCompoundDrawablesTints();
    }

    protected void drawableStateChanged()
    {
        drawableStateChanged();
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.applySupportBackgroundTint();
        if(mTextHelper != null)
            mTextHelper.applyCompoundDrawablesTints();
    }

    public int getAutoSizeMaxTextSize()
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return getAutoSizeMaxTextSize();
        if(mTextHelper != null)
            return mTextHelper.getAutoSizeMaxTextSize();
        else
            return -1;
    }

    public int getAutoSizeMinTextSize()
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return getAutoSizeMinTextSize();
        if(mTextHelper != null)
            return mTextHelper.getAutoSizeMinTextSize();
        else
            return -1;
    }

    public int getAutoSizeStepGranularity()
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return getAutoSizeStepGranularity();
        if(mTextHelper != null)
            return mTextHelper.getAutoSizeStepGranularity();
        else
            return -1;
    }

    public int[] getAutoSizeTextAvailableSizes()
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return getAutoSizeTextAvailableSizes();
        if(mTextHelper != null)
            return mTextHelper.getAutoSizeTextAvailableSizes();
        else
            return new int[0];
    }

    public int getAutoSizeTextType()
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return getAutoSizeTextType() != 1 ? 0 : 1;
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

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setClassName(android/widget/Button.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(android/widget/Button.getName());
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        onLayout(flag, i, j, k, l);
        if(mTextHelper != null)
            mTextHelper.onLayout(flag, i, j, k, l);
    }

    protected void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        onTextChanged(charsequence, i, j, k);
        if(mTextHelper != null && android.os.Build.VERSION.SDK_INT < 26 && mTextHelper.isAutoSizeEnabled())
            mTextHelper.autoSizeText();
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int j, int k, int l)
        throws IllegalArgumentException
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            setAutoSizeTextTypeUniformWithConfiguration(i, j, k, l);
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
            setAutoSizeTextTypeUniformWithPresetSizes(ai, i);
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
            setAutoSizeTextTypeWithDefaults(i);
        else
        if(mTextHelper != null)
        {
            mTextHelper.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        setBackgroundDrawable(drawable);
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.onSetBackgroundDrawable(drawable);
    }

    public void setBackgroundResource(int i)
    {
        setBackgroundResource(i);
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.onSetBackgroundResource(i);
    }

    public void setSupportAllCaps(boolean flag)
    {
        if(mTextHelper != null)
            mTextHelper.setAllCaps(flag);
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
        setTextAppearance(context, i);
        if(mTextHelper != null)
            mTextHelper.onSetTextAppearance(context, i);
    }

    public void setTextSize(int i, float f)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            setTextSize(i, f);
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
