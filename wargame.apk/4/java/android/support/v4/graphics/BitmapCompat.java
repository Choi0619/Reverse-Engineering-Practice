// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics;

import android.graphics.Bitmap;

public final class BitmapCompat
{
    static class BitmapCompatApi18Impl extends BitmapCompatBaseImpl
    {

        public boolean hasMipMap(Bitmap bitmap)
        {
            return bitmap.hasMipMap();
        }

        public void setHasMipMap(Bitmap bitmap, boolean flag)
        {
            bitmap.setHasMipMap(flag);
        }

        BitmapCompatApi18Impl()
        {
        }
    }

    static class BitmapCompatApi19Impl extends BitmapCompatApi18Impl
    {

        public int getAllocationByteCount(Bitmap bitmap)
        {
            return bitmap.getAllocationByteCount();
        }

        BitmapCompatApi19Impl()
        {
        }
    }

    static class BitmapCompatBaseImpl
    {

        public int getAllocationByteCount(Bitmap bitmap)
        {
            return bitmap.getByteCount();
        }

        public boolean hasMipMap(Bitmap bitmap)
        {
            return false;
        }

        public void setHasMipMap(Bitmap bitmap, boolean flag)
        {
        }

        BitmapCompatBaseImpl()
        {
        }
    }


    private BitmapCompat()
    {
    }

    public static int getAllocationByteCount(Bitmap bitmap)
    {
        return IMPL.getAllocationByteCount(bitmap);
    }

    public static boolean hasMipMap(Bitmap bitmap)
    {
        return IMPL.hasMipMap(bitmap);
    }

    public static void setHasMipMap(Bitmap bitmap, boolean flag)
    {
        IMPL.setHasMipMap(bitmap, flag);
    }

    static final BitmapCompatBaseImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            IMPL = new BitmapCompatApi19Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 18)
            IMPL = new BitmapCompatApi18Impl();
        else
            IMPL = new BitmapCompatBaseImpl();
    }
}
