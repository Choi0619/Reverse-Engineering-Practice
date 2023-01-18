// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.AppOpsManager;
import android.content.Context;

public final class AppOpsManagerCompat
{

    private AppOpsManagerCompat()
    {
    }

    public static int noteOp(Context context, String s, int i, String s1)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return ((AppOpsManager)context.getSystemService(android/app/AppOpsManager)).noteOp(s, i, s1);
        else
            return 1;
    }

    public static int noteProxyOp(Context context, String s, String s1)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return ((AppOpsManager)context.getSystemService(android/app/AppOpsManager)).noteProxyOp(s, s1);
        else
            return 1;
    }

    public static String permissionToOp(String s)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return AppOpsManager.permissionToOp(s);
        else
            return null;
    }

    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_IGNORED = 1;
}
