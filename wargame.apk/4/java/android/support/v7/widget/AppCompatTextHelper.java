// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

// Referenced classes of package android.support.v7.widget:
//            AppCompatTextViewAutoSizeHelper, AppCompatTextHelperV17, AppCompatDrawableManager, TintInfo, 
//            TintTypedArray

class AppCompatTextHelper
{

    AppCompatTextHelper(TextView textview)
    {
        mStyle = 0;
        mView = textview;
        mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(mView);
    }

    static AppCompatTextHelper create(TextView textview)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
            return new AppCompatTextHelperV17(textview);
        else
            return new AppCompatTextHelper(textview);
    }

    protected static TintInfo createTintInfo(Context context, AppCompatDrawableManager appcompatdrawablemanager, int i)
    {
        context = appcompatdrawablemanager.getTintList(context, i);
        if(context != null)
        {
            appcompatdrawablemanager = new TintInfo();
            appcompatdrawablemanager.mHasTintList = true;
            appcompatdrawablemanager.mTintList = context;
            return appcompatdrawablemanager;
        } else
        {
            return null;
        }
    }

    private void setTextSizeInternal(int i, float f)
    {
        mAutoSizeTextHelper.setTextSizeInternal(i, f);
    }

    private void updateTypefaceAndStyle(Context context, TintTypedArray tinttypedarray)
    {
        mStyle = tinttypedarray.getInt(android.support.v7.appcompat.R.styleable.TextAppearance_android_textStyle, mStyle);
        if(tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_fontFamily) || tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_fontFamily))
        {
            mFontTypeface = null;
            int i;
            if(tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_fontFamily))
                i = android.support.v7.appcompat.R.styleable.TextAppearance_android_fontFamily;
            else
                i = android.support.v7.appcompat.R.styleable.TextAppearance_fontFamily;
            if(!context.isRestricted())
                try
                {
                    mFontTypeface = tinttypedarray.getFont(i, mStyle, mView);
                }
                // Misplaced declaration of an exception variable
                catch(Context context) { }
                // Misplaced declaration of an exception variable
                catch(Context context) { }
            if(mFontTypeface == null)
                mFontTypeface = Typeface.create(tinttypedarray.getString(i), mStyle);
        }
    }

    final void applyCompoundDrawableTint(Drawable drawable, TintInfo tintinfo)
    {
        if(drawable != null && tintinfo != null)
            AppCompatDrawableManager.tintDrawable(drawable, tintinfo, mView.getDrawableState());
    }

    void applyCompoundDrawablesTints()
    {
        if(mDrawableLeftTint != null || mDrawableTopTint != null || mDrawableRightTint != null || mDrawableBottomTint != null)
        {
            Drawable adrawable[] = mView.getCompoundDrawables();
            applyCompoundDrawableTint(adrawable[0], mDrawableLeftTint);
            applyCompoundDrawableTint(adrawable[1], mDrawableTopTint);
            applyCompoundDrawableTint(adrawable[2], mDrawableRightTint);
            applyCompoundDrawableTint(adrawable[3], mDrawableBottomTint);
        }
    }

    void autoSizeText()
    {
        mAutoSizeTextHelper.autoSizeText();
    }

    int getAutoSizeMaxTextSize()
    {
        return mAutoSizeTextHelper.getAutoSizeMaxTextSize();
    }

    int getAutoSizeMinTextSize()
    {
        return mAutoSizeTextHelper.getAutoSizeMinTextSize();
    }

    int getAutoSizeStepGranularity()
    {
        return mAutoSizeTextHelper.getAutoSizeStepGranularity();
    }

    int[] getAutoSizeTextAvailableSizes()
    {
        return mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
    }

    int getAutoSizeTextType()
    {
        return mAutoSizeTextHelper.getAutoSizeTextType();
    }

    boolean isAutoSizeEnabled()
    {
        return mAutoSizeTextHelper.isAutoSizeEnabled();
    }

    void loadFromAttributes(AttributeSet attributeset, int i)
    {
label0:
        {
            Context context = mView.getContext();
            Object obj = AppCompatDrawableManager.get();
            Object obj1 = TintTypedArray.obtainStyledAttributes(context, attributeset, android.support.v7.appcompat.R.styleable.AppCompatTextHelper, i, 0);
            int j = ((TintTypedArray) (obj1)).getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_textAppearance, -1);
            if(((TintTypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableLeft))
                mDrawableLeftTint = createTintInfo(context, ((AppCompatDrawableManager) (obj)), ((TintTypedArray) (obj1)).getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
            if(((TintTypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableTop))
                mDrawableTopTint = createTintInfo(context, ((AppCompatDrawableManager) (obj)), ((TintTypedArray) (obj1)).getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableTop, 0));
            if(((TintTypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableRight))
                mDrawableRightTint = createTintInfo(context, ((AppCompatDrawableManager) (obj)), ((TintTypedArray) (obj1)).getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableRight, 0));
            if(((TintTypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableBottom))
                mDrawableBottomTint = createTintInfo(context, ((AppCompatDrawableManager) (obj)), ((TintTypedArray) (obj1)).getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
            ((TintTypedArray) (obj1)).recycle();
            boolean flag4 = mView.getTransformationMethod() instanceof PasswordTransformationMethod;
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag = false;
            boolean flag1 = false;
            Object obj2 = null;
            obj1 = null;
            TintTypedArray tinttypedarray = null;
            android.content.res.ColorStateList colorstatelist1 = null;
            obj = null;
            android.content.res.ColorStateList colorstatelist2 = null;
            android.content.res.ColorStateList colorstatelist = null;
            android.content.res.ColorStateList colorstatelist3 = null;
            if(j != -1)
            {
                TintTypedArray tinttypedarray1 = TintTypedArray.obtainStyledAttributes(context, j, android.support.v7.appcompat.R.styleable.TextAppearance);
                flag2 = flag3;
                flag = flag1;
                if(!flag4)
                {
                    flag2 = flag3;
                    flag = flag1;
                    if(tinttypedarray1.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps))
                    {
                        flag = true;
                        flag2 = tinttypedarray1.getBoolean(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps, false);
                    }
                }
                updateTypefaceAndStyle(context, tinttypedarray1);
                obj1 = obj2;
                colorstatelist = colorstatelist3;
                if(android.os.Build.VERSION.SDK_INT < 23)
                {
                    obj = tinttypedarray;
                    if(tinttypedarray1.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor))
                        obj = tinttypedarray1.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
                    if(tinttypedarray1.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint))
                        colorstatelist2 = tinttypedarray1.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint);
                    obj1 = obj;
                    colorstatelist1 = colorstatelist2;
                    colorstatelist = colorstatelist3;
                    if(tinttypedarray1.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink))
                    {
                        colorstatelist = tinttypedarray1.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink);
                        colorstatelist1 = colorstatelist2;
                        obj1 = obj;
                    }
                }
                tinttypedarray1.recycle();
                obj = colorstatelist1;
            }
            tinttypedarray = TintTypedArray.obtainStyledAttributes(context, attributeset, android.support.v7.appcompat.R.styleable.TextAppearance, i, 0);
            flag3 = flag2;
            flag1 = flag;
            if(!flag4)
            {
                flag3 = flag2;
                flag1 = flag;
                if(tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps))
                {
                    flag1 = true;
                    flag3 = tinttypedarray.getBoolean(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps, false);
                }
            }
            colorstatelist1 = ((android.content.res.ColorStateList) (obj1));
            colorstatelist2 = ((android.content.res.ColorStateList) (obj));
            colorstatelist3 = colorstatelist;
            if(android.os.Build.VERSION.SDK_INT < 23)
            {
                if(tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor))
                    obj1 = tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
                if(tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint))
                    obj = tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint);
                colorstatelist1 = ((android.content.res.ColorStateList) (obj1));
                colorstatelist2 = ((android.content.res.ColorStateList) (obj));
                colorstatelist3 = colorstatelist;
                if(tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink))
                {
                    colorstatelist3 = tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink);
                    colorstatelist2 = ((android.content.res.ColorStateList) (obj));
                    colorstatelist1 = ((android.content.res.ColorStateList) (obj1));
                }
            }
            updateTypefaceAndStyle(context, tinttypedarray);
            tinttypedarray.recycle();
            if(colorstatelist1 != null)
                mView.setTextColor(colorstatelist1);
            if(colorstatelist2 != null)
                mView.setHintTextColor(colorstatelist2);
            if(colorstatelist3 != null)
                mView.setLinkTextColor(colorstatelist3);
            if(!flag4 && flag1)
                setAllCaps(flag3);
            if(mFontTypeface != null)
                mView.setTypeface(mFontTypeface, mStyle);
            mAutoSizeTextHelper.loadFromAttributes(attributeset, i);
            if(android.os.Build.VERSION.SDK_INT >= 26 && mAutoSizeTextHelper.getAutoSizeTextType() != 0)
            {
                attributeset = mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
                if(attributeset.length > 0)
                {
                    if((float)mView.getAutoSizeStepGranularity() == -1F)
                        break label0;
                    mView.setAutoSizeTextTypeUniformWithConfiguration(mAutoSizeTextHelper.getAutoSizeMinTextSize(), mAutoSizeTextHelper.getAutoSizeMaxTextSize(), mAutoSizeTextHelper.getAutoSizeStepGranularity(), 0);
                }
            }
            return;
        }
        mView.setAutoSizeTextTypeUniformWithPresetSizes(attributeset, 0);
    }

    void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(android.os.Build.VERSION.SDK_INT < 26)
            autoSizeText();
    }

    void onSetTextAppearance(Context context, int i)
    {
        TintTypedArray tinttypedarray = TintTypedArray.obtainStyledAttributes(context, i, android.support.v7.appcompat.R.styleable.TextAppearance);
        if(tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps))
            setAllCaps(tinttypedarray.getBoolean(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps, false));
        if(android.os.Build.VERSION.SDK_INT < 23 && tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor))
        {
            android.content.res.ColorStateList colorstatelist = tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
            if(colorstatelist != null)
                mView.setTextColor(colorstatelist);
        }
        updateTypefaceAndStyle(context, tinttypedarray);
        tinttypedarray.recycle();
        if(mFontTypeface != null)
            mView.setTypeface(mFontTypeface, mStyle);
    }

    void setAllCaps(boolean flag)
    {
        mView.setAllCaps(flag);
    }

    void setAutoSizeTextTypeUniformWithConfiguration(int i, int j, int k, int l)
        throws IllegalArgumentException
    {
        mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(i, j, k, l);
    }

    void setAutoSizeTextTypeUniformWithPresetSizes(int ai[], int i)
        throws IllegalArgumentException
    {
        mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(ai, i);
    }

    void setAutoSizeTextTypeWithDefaults(int i)
    {
        mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(i);
    }

    void setTextSize(int i, float f)
    {
        if(android.os.Build.VERSION.SDK_INT < 26 && !isAutoSizeEnabled())
            setTextSizeInternal(i, f);
    }

    private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableTopTint;
    private Typeface mFontTypeface;
    private int mStyle;
    final TextView mView;
}
