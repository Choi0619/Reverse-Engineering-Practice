// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.res;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public final class ConfigurationHelper
{

    private ConfigurationHelper()
    {
    }

    public static int getDensityDpi(Resources resources)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
            return resources.getConfiguration().densityDpi;
        else
            return resources.getDisplayMetrics().densityDpi;
    }

    public static int getScreenHeightDp(Resources resources)
    {
        return resources.getConfiguration().screenHeightDp;
    }

    public static int getScreenWidthDp(Resources resources)
    {
        return resources.getConfiguration().screenWidthDp;
    }

    public static int getSmallestScreenWidthDp(Resources resources)
    {
        return resources.getConfiguration().smallestScreenWidthDp;
    }
}
