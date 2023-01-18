// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat
{
    static class PopupWindowCompatApi19Impl extends PopupWindowCompatBaseImpl
    {

        public void showAsDropDown(PopupWindow popupwindow, View view, int i, int j, int k)
        {
            popupwindow.showAsDropDown(view, i, j, k);
        }

        PopupWindowCompatApi19Impl()
        {
        }
    }

    static class PopupWindowCompatApi21Impl extends PopupWindowCompatApi19Impl
    {

        public boolean getOverlapAnchor(PopupWindow popupwindow)
        {
            if(sOverlapAnchorField == null)
                break MISSING_BLOCK_LABEL_32;
            boolean flag = ((Boolean)sOverlapAnchorField.get(popupwindow)).booleanValue();
            return flag;
            popupwindow;
            Log.i("PopupWindowCompatApi21", "Could not get overlap anchor field in PopupWindow", popupwindow);
            return false;
        }

        public void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
        {
            if(sOverlapAnchorField == null)
                break MISSING_BLOCK_LABEL_17;
            sOverlapAnchorField.set(popupwindow, Boolean.valueOf(flag));
            return;
            popupwindow;
            Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", popupwindow);
            return;
        }

        private static final String TAG = "PopupWindowCompatApi21";
        private static Field sOverlapAnchorField;

        static 
        {
            try
            {
                sOverlapAnchorField = android/widget/PopupWindow.getDeclaredField("mOverlapAnchor");
                sOverlapAnchorField.setAccessible(true);
            }
            catch(NoSuchFieldException nosuchfieldexception)
            {
                Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", nosuchfieldexception);
            }
        }

        PopupWindowCompatApi21Impl()
        {
        }
    }

    static class PopupWindowCompatApi23Impl extends PopupWindowCompatApi21Impl
    {

        public boolean getOverlapAnchor(PopupWindow popupwindow)
        {
            return popupwindow.getOverlapAnchor();
        }

        public int getWindowLayoutType(PopupWindow popupwindow)
        {
            return popupwindow.getWindowLayoutType();
        }

        public void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
        {
            popupwindow.setOverlapAnchor(flag);
        }

        public void setWindowLayoutType(PopupWindow popupwindow, int i)
        {
            popupwindow.setWindowLayoutType(i);
        }

        PopupWindowCompatApi23Impl()
        {
        }
    }

    static class PopupWindowCompatBaseImpl
    {

        public boolean getOverlapAnchor(PopupWindow popupwindow)
        {
            return false;
        }

        public int getWindowLayoutType(PopupWindow popupwindow)
        {
            if(!sGetWindowLayoutTypeMethodAttempted)
            {
                int i;
                try
                {
                    sGetWindowLayoutTypeMethod = android/widget/PopupWindow.getDeclaredMethod("getWindowLayoutType", new Class[0]);
                    sGetWindowLayoutTypeMethod.setAccessible(true);
                }
                catch(Exception exception) { }
                sGetWindowLayoutTypeMethodAttempted = true;
            }
            if(sGetWindowLayoutTypeMethod == null)
                break MISSING_BLOCK_LABEL_58;
            i = ((Integer)sGetWindowLayoutTypeMethod.invoke(popupwindow, new Object[0])).intValue();
            return i;
            popupwindow;
            return 0;
        }

        public void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
        {
        }

        public void setWindowLayoutType(PopupWindow popupwindow, int i)
        {
            if(!sSetWindowLayoutTypeMethodAttempted)
            {
                try
                {
                    sSetWindowLayoutTypeMethod = android/widget/PopupWindow.getDeclaredMethod("setWindowLayoutType", new Class[] {
                        Integer.TYPE
                    });
                    sSetWindowLayoutTypeMethod.setAccessible(true);
                }
                catch(Exception exception) { }
                sSetWindowLayoutTypeMethodAttempted = true;
            }
            if(sSetWindowLayoutTypeMethod == null)
                break MISSING_BLOCK_LABEL_62;
            sSetWindowLayoutTypeMethod.invoke(popupwindow, new Object[] {
                Integer.valueOf(i)
            });
            return;
            popupwindow;
        }

        public void showAsDropDown(PopupWindow popupwindow, View view, int i, int j, int k)
        {
            int l = i;
            if((GravityCompat.getAbsoluteGravity(k, ViewCompat.getLayoutDirection(view)) & 7) == 5)
                l = i - (popupwindow.getWidth() - view.getWidth());
            popupwindow.showAsDropDown(view, l, j);
        }

        private static Method sGetWindowLayoutTypeMethod;
        private static boolean sGetWindowLayoutTypeMethodAttempted;
        private static Method sSetWindowLayoutTypeMethod;
        private static boolean sSetWindowLayoutTypeMethodAttempted;

        PopupWindowCompatBaseImpl()
        {
        }
    }


    private PopupWindowCompat()
    {
    }

    public static boolean getOverlapAnchor(PopupWindow popupwindow)
    {
        return IMPL.getOverlapAnchor(popupwindow);
    }

    public static int getWindowLayoutType(PopupWindow popupwindow)
    {
        return IMPL.getWindowLayoutType(popupwindow);
    }

    public static void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
    {
        IMPL.setOverlapAnchor(popupwindow, flag);
    }

    public static void setWindowLayoutType(PopupWindow popupwindow, int i)
    {
        IMPL.setWindowLayoutType(popupwindow, i);
    }

    public static void showAsDropDown(PopupWindow popupwindow, View view, int i, int j, int k)
    {
        IMPL.showAsDropDown(popupwindow, view, i, j, k);
    }

    static final PopupWindowCompatBaseImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            IMPL = new PopupWindowCompatApi23Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 21)
            IMPL = new PopupWindowCompatApi21Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 19)
            IMPL = new PopupWindowCompatApi19Impl();
        else
            IMPL = new PopupWindowCompatBaseImpl();
    }
}
