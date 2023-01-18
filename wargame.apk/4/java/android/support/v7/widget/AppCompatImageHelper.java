// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ImageView;

// Referenced classes of package android.support.v7.widget:
//            TintInfo, AppCompatDrawableManager, DrawableUtils, TintTypedArray

public class AppCompatImageHelper
{

    public AppCompatImageHelper(ImageView imageview)
    {
        mView = imageview;
    }

    private boolean applyFrameworkTintUsingColorFilter(Drawable drawable)
    {
        if(mTmpInfo == null)
            mTmpInfo = new TintInfo();
        TintInfo tintinfo = mTmpInfo;
        tintinfo.clear();
        Object obj = ImageViewCompat.getImageTintList(mView);
        if(obj != null)
        {
            tintinfo.mHasTintList = true;
            tintinfo.mTintList = ((ColorStateList) (obj));
        }
        obj = ImageViewCompat.getImageTintMode(mView);
        if(obj != null)
        {
            tintinfo.mHasTintMode = true;
            tintinfo.mTintMode = ((android.graphics.PorterDuff.Mode) (obj));
        }
        if(tintinfo.mHasTintList || tintinfo.mHasTintMode)
        {
            AppCompatDrawableManager.tintDrawable(drawable, tintinfo, mView.getDrawableState());
            return true;
        } else
        {
            return false;
        }
    }

    private boolean shouldApplyFrameworkTintUsingColorFilter()
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i <= 21) goto _L2; else goto _L1
_L1:
        if(mInternalImageTint == null) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if(i != 21)
            return false;
        if(true) goto _L3; else goto _L5
_L5:
    }

    void applySupportImageTint()
    {
        Drawable drawable = mView.getDrawable();
        if(drawable != null)
            DrawableUtils.fixDrawable(drawable);
        if(drawable != null && (!shouldApplyFrameworkTintUsingColorFilter() || !applyFrameworkTintUsingColorFilter(drawable)))
        {
            if(mImageTint != null)
            {
                AppCompatDrawableManager.tintDrawable(drawable, mImageTint, mView.getDrawableState());
                return;
            }
            if(mInternalImageTint != null)
            {
                AppCompatDrawableManager.tintDrawable(drawable, mInternalImageTint, mView.getDrawableState());
                return;
            }
        }
    }

    ColorStateList getSupportImageTintList()
    {
        if(mImageTint != null)
            return mImageTint.mTintList;
        else
            return null;
    }

    android.graphics.PorterDuff.Mode getSupportImageTintMode()
    {
        if(mImageTint != null)
            return mImageTint.mTintMode;
        else
            return null;
    }

    boolean hasOverlappingRendering()
    {
        Drawable drawable = mView.getBackground();
        return android.os.Build.VERSION.SDK_INT < 21 || !(drawable instanceof RippleDrawable);
    }

    public void loadFromAttributes(AttributeSet attributeset, int i)
    {
        TintTypedArray tinttypedarray = TintTypedArray.obtainStyledAttributes(mView.getContext(), attributeset, android.support.v7.appcompat.R.styleable.AppCompatImageView, i, 0);
        Drawable drawable = mView.getDrawable();
        attributeset = drawable;
        if(drawable != null)
            break MISSING_BLOCK_LABEL_77;
        i = tinttypedarray.getResourceId(android.support.v7.appcompat.R.styleable.AppCompatImageView_srcCompat, -1);
        attributeset = drawable;
        if(i == -1)
            break MISSING_BLOCK_LABEL_77;
        drawable = AppCompatResources.getDrawable(mView.getContext(), i);
        attributeset = drawable;
        if(drawable == null)
            break MISSING_BLOCK_LABEL_77;
        mView.setImageDrawable(drawable);
        attributeset = drawable;
        if(attributeset == null)
            break MISSING_BLOCK_LABEL_85;
        DrawableUtils.fixDrawable(attributeset);
        if(tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.AppCompatImageView_tint))
            ImageViewCompat.setImageTintList(mView, tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.AppCompatImageView_tint));
        if(tinttypedarray.hasValue(android.support.v7.appcompat.R.styleable.AppCompatImageView_tintMode))
            ImageViewCompat.setImageTintMode(mView, DrawableUtils.parseTintMode(tinttypedarray.getInt(android.support.v7.appcompat.R.styleable.AppCompatImageView_tintMode, -1), null));
        tinttypedarray.recycle();
        return;
        attributeset;
        tinttypedarray.recycle();
        throw attributeset;
    }

    public void setImageResource(int i)
    {
        if(i != 0)
        {
            Drawable drawable = AppCompatResources.getDrawable(mView.getContext(), i);
            if(drawable != null)
                DrawableUtils.fixDrawable(drawable);
            mView.setImageDrawable(drawable);
        } else
        {
            mView.setImageDrawable(null);
        }
        applySupportImageTint();
    }

    void setInternalImageTint(ColorStateList colorstatelist)
    {
        if(colorstatelist != null)
        {
            if(mInternalImageTint == null)
                mInternalImageTint = new TintInfo();
            mInternalImageTint.mTintList = colorstatelist;
            mInternalImageTint.mHasTintList = true;
        } else
        {
            mInternalImageTint = null;
        }
        applySupportImageTint();
    }

    void setSupportImageTintList(ColorStateList colorstatelist)
    {
        if(mImageTint == null)
            mImageTint = new TintInfo();
        mImageTint.mTintList = colorstatelist;
        mImageTint.mHasTintList = true;
        applySupportImageTint();
    }

    void setSupportImageTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mImageTint == null)
            mImageTint = new TintInfo();
        mImageTint.mTintMode = mode;
        mImageTint.mHasTintMode = true;
        applySupportImageTint();
    }

    private TintInfo mImageTint;
    private TintInfo mInternalImageTint;
    private TintInfo mTmpInfo;
    private final ImageView mView;
}
