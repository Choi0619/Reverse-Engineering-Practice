// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat
{
    private static class DisplayManagerCompatApi14Impl extends DisplayManagerCompat
    {

        public Display getDisplay(int i)
        {
            Display display = mWindowManager.getDefaultDisplay();
            if(display.getDisplayId() == i)
                return display;
            else
                return null;
        }

        public Display[] getDisplays()
        {
            return (new Display[] {
                mWindowManager.getDefaultDisplay()
            });
        }

        public Display[] getDisplays(String s)
        {
            if(s == null)
                return getDisplays();
            else
                return new Display[0];
        }

        private final WindowManager mWindowManager;

        DisplayManagerCompatApi14Impl(Context context)
        {
            mWindowManager = (WindowManager)context.getSystemService("window");
        }
    }

    private static class DisplayManagerCompatApi17Impl extends DisplayManagerCompat
    {

        public Display getDisplay(int i)
        {
            return mDisplayManager.getDisplay(i);
        }

        public Display[] getDisplays()
        {
            return mDisplayManager.getDisplays();
        }

        public Display[] getDisplays(String s)
        {
            return mDisplayManager.getDisplays(s);
        }

        private final DisplayManager mDisplayManager;

        DisplayManagerCompatApi17Impl(Context context)
        {
            mDisplayManager = (DisplayManager)context.getSystemService("display");
        }
    }


    DisplayManagerCompat()
    {
    }

    public static DisplayManagerCompat getInstance(Context context)
    {
        WeakHashMap weakhashmap = sInstances;
        weakhashmap;
        JVM INSTR monitorenter ;
        DisplayManagerCompat displaymanagercompat = (DisplayManagerCompat)sInstances.get(context);
        Object obj = displaymanagercompat;
        if(displaymanagercompat != null) goto _L2; else goto _L1
_L1:
        if(android.os.Build.VERSION.SDK_INT < 17)
            break MISSING_BLOCK_LABEL_53;
        obj = new DisplayManagerCompatApi17Impl(context);
_L3:
        sInstances.put(context, obj);
_L2:
        weakhashmap;
        JVM INSTR monitorexit ;
        return ((DisplayManagerCompat) (obj));
        obj = new DisplayManagerCompatApi14Impl(context);
          goto _L3
        context;
        weakhashmap;
        JVM INSTR monitorexit ;
        throw context;
    }

    public abstract Display getDisplay(int i);

    public abstract Display[] getDisplays();

    public abstract Display[] getDisplays(String s);

    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    private static final WeakHashMap sInstances = new WeakHashMap();

}
