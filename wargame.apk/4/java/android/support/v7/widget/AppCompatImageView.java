// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.widget.TintableImageSourceView;
import android.util.AttributeSet;
import android.widget.ImageView;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatBackgroundHelper, AppCompatImageHelper

public class AppCompatImageView extends ImageView
    implements TintableBackgroundView, TintableImageSourceView
{

    public AppCompatImageView(Context context)
    {
        AppCompatImageView(context, null);
    }

    public AppCompatImageView(Context context, AttributeSet attributeset)
    {
        AppCompatImageView(context, attributeset, 0);
    }

    public AppCompatImageView(Context context, AttributeSet attributeset, int i)
    {
        ImageView(TintContextWrapper.wrap(context), attributeset, i);
        mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attributeset, i);
        mImageHelper = new AppCompatImageHelper(this);
        mImageHelper.loadFromAttributes(attributeset, i);
    }

    protected void drawableStateChanged()
    {
        drawableStateChanged();
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.applySupportBackgroundTint();
        if(mImageHelper != null)
            mImageHelper.applySupportImageTint();
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

    public ColorStateList getSupportImageTintList()
    {
        if(mImageHelper != null)
            return mImageHelper.getSupportImageTintList();
        else
            return null;
    }

    public android.graphics.PorterDuff.Mode getSupportImageTintMode()
    {
        if(mImageHelper != null)
            return mImageHelper.getSupportImageTintMode();
        else
            return null;
    }

    public boolean hasOverlappingRendering()
    {
        return mImageHelper.hasOverlappingRendering() && hasOverlappingRendering();
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

    public void setImageBitmap(Bitmap bitmap)
    {
        setImageBitmap(bitmap);
        if(mImageHelper != null)
            mImageHelper.applySupportImageTint();
    }

    public void setImageDrawable(Drawable drawable)
    {
        setImageDrawable(drawable);
        if(mImageHelper != null)
            mImageHelper.applySupportImageTint();
    }

    public void setImageIcon(Icon icon)
    {
        setImageIcon(icon);
        if(mImageHelper != null)
            mImageHelper.applySupportImageTint();
    }

    public void setImageResource(int i)
    {
        if(mImageHelper != null)
            mImageHelper.setImageResource(i);
    }

    public void setImageURI(Uri uri)
    {
        setImageURI(uri);
        if(mImageHelper != null)
            mImageHelper.applySupportImageTint();
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

    public void setSupportImageTintList(ColorStateList colorstatelist)
    {
        if(mImageHelper != null)
            mImageHelper.setSupportImageTintList(colorstatelist);
    }

    public void setSupportImageTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mImageHelper != null)
            mImageHelper.setSupportImageTintMode(mode);
    }

    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatImageHelper mImageHelper;
}
