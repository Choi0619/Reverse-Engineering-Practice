// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

public final class ViewConfigurationCompat
{

    private ViewConfigurationCompat()
    {
    }

    private static float getLegacyScrollFactor(ViewConfiguration viewconfiguration, Context context)
    {
        if(android.os.Build.VERSION.SDK_INT < 25 || sGetScaledScrollFactorMethod == null)
            break MISSING_BLOCK_LABEL_44;
        int i = ((Integer)sGetScaledScrollFactorMethod.invoke(viewconfiguration, new Object[0])).intValue();
        return (float)i;
        viewconfiguration;
        Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
        viewconfiguration = new TypedValue();
        if(context.getTheme().resolveAttribute(0x101004d, viewconfiguration, true))
            return viewconfiguration.getDimension(context.getResources().getDisplayMetrics());
        else
            return 0.0F;
    }

    public static float getScaledHorizontalScrollFactor(ViewConfiguration viewconfiguration, Context context)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return viewconfiguration.getScaledHorizontalScrollFactor();
        else
            return getLegacyScrollFactor(viewconfiguration, context);
    }

    public static int getScaledPagingTouchSlop(ViewConfiguration viewconfiguration)
    {
        return viewconfiguration.getScaledPagingTouchSlop();
    }

    public static float getScaledVerticalScrollFactor(ViewConfiguration viewconfiguration, Context context)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return viewconfiguration.getScaledVerticalScrollFactor();
        else
            return getLegacyScrollFactor(viewconfiguration, context);
    }

    public static boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration)
    {
        return viewconfiguration.hasPermanentMenuKey();
    }

    private static final String TAG = "ViewConfigCompat";
    private static Method sGetScaledScrollFactorMethod = android/view/ViewConfiguration.getDeclaredMethod("getScaledScrollFactor", new Class[0]);

    static 
    {
        if(android.os.Build.VERSION.SDK_INT != 25)
            break MISSING_BLOCK_LABEL_22;
        return;
        Exception exception;
        exception;
        Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
    }
}
