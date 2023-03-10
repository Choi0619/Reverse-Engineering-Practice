// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;


public class BuildCompat
{

    private BuildCompat()
    {
    }

    public static boolean isAtLeastN()
    {
        return android.os.Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isAtLeastNMR1()
    {
        return android.os.Build.VERSION.SDK_INT >= 25;
    }

    public static boolean isAtLeastO()
    {
        return android.os.Build.VERSION.SDK_INT >= 26;
    }

    public static boolean isAtLeastOMR1()
    {
        return android.os.Build.VERSION.CODENAME.startsWith("OMR") || isAtLeastP();
    }

    public static boolean isAtLeastP()
    {
        return android.os.Build.VERSION.CODENAME.equals("P");
    }
}
