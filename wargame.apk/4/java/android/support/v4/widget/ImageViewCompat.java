// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

// Referenced classes of package android.support.v4.widget:
//            TintableImageSourceView

public class ImageViewCompat
{
    static class BaseViewCompatImpl
        implements ImageViewCompatImpl
    {

        public ColorStateList getImageTintList(ImageView imageview)
        {
            if(imageview instanceof TintableImageSourceView)
                return ((TintableImageSourceView)imageview).getSupportImageTintList();
            else
                return null;
        }

        public android.graphics.PorterDuff.Mode getImageTintMode(ImageView imageview)
        {
            if(imageview instanceof TintableImageSourceView)
                return ((TintableImageSourceView)imageview).getSupportImageTintMode();
            else
                return null;
        }

        public void setImageTintList(ImageView imageview, ColorStateList colorstatelist)
        {
            if(imageview instanceof TintableImageSourceView)
                ((TintableImageSourceView)imageview).setSupportImageTintList(colorstatelist);
        }

        public void setImageTintMode(ImageView imageview, android.graphics.PorterDuff.Mode mode)
        {
            if(imageview instanceof TintableImageSourceView)
                ((TintableImageSourceView)imageview).setSupportImageTintMode(mode);
        }

        BaseViewCompatImpl()
        {
        }
    }

    static interface ImageViewCompatImpl
    {

        public abstract ColorStateList getImageTintList(ImageView imageview);

        public abstract android.graphics.PorterDuff.Mode getImageTintMode(ImageView imageview);

        public abstract void setImageTintList(ImageView imageview, ColorStateList colorstatelist);

        public abstract void setImageTintMode(ImageView imageview, android.graphics.PorterDuff.Mode mode);
    }

    static class LollipopViewCompatImpl extends BaseViewCompatImpl
    {

        public ColorStateList getImageTintList(ImageView imageview)
        {
            return imageview.getImageTintList();
        }

        public android.graphics.PorterDuff.Mode getImageTintMode(ImageView imageview)
        {
            return imageview.getImageTintMode();
        }

        public void setImageTintList(ImageView imageview, ColorStateList colorstatelist)
        {
            imageview.setImageTintList(colorstatelist);
            if(android.os.Build.VERSION.SDK_INT == 21)
            {
                colorstatelist = imageview.getDrawable();
                boolean flag;
                if(imageview.getImageTintList() != null && imageview.getImageTintMode() != null)
                    flag = true;
                else
                    flag = false;
                if(colorstatelist != null && flag)
                {
                    if(colorstatelist.isStateful())
                        colorstatelist.setState(imageview.getDrawableState());
                    imageview.setImageDrawable(colorstatelist);
                }
            }
        }

        public void setImageTintMode(ImageView imageview, android.graphics.PorterDuff.Mode mode)
        {
            imageview.setImageTintMode(mode);
            if(android.os.Build.VERSION.SDK_INT == 21)
            {
                mode = imageview.getDrawable();
                boolean flag;
                if(imageview.getImageTintList() != null && imageview.getImageTintMode() != null)
                    flag = true;
                else
                    flag = false;
                if(mode != null && flag)
                {
                    if(mode.isStateful())
                        mode.setState(imageview.getDrawableState());
                    imageview.setImageDrawable(mode);
                }
            }
        }

        LollipopViewCompatImpl()
        {
        }
    }


    private ImageViewCompat()
    {
    }

    public static ColorStateList getImageTintList(ImageView imageview)
    {
        return IMPL.getImageTintList(imageview);
    }

    public static android.graphics.PorterDuff.Mode getImageTintMode(ImageView imageview)
    {
        return IMPL.getImageTintMode(imageview);
    }

    public static void setImageTintList(ImageView imageview, ColorStateList colorstatelist)
    {
        IMPL.setImageTintList(imageview, colorstatelist);
    }

    public static void setImageTintMode(ImageView imageview, android.graphics.PorterDuff.Mode mode)
    {
        IMPL.setImageTintMode(imageview, mode);
    }

    static final ImageViewCompatImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            IMPL = new LollipopViewCompatImpl();
        else
            IMPL = new BaseViewCompatImpl();
    }
}
