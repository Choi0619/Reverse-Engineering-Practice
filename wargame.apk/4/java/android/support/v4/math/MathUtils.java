// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.math;


public class MathUtils
{

    private MathUtils()
    {
    }

    public static double clamp(double d, double d1, double d2)
    {
        if(d < d1)
            return d1;
        if(d > d2)
            return d2;
        else
            return d;
    }

    public static float clamp(float f, float f1, float f2)
    {
        if(f < f1)
            return f1;
        if(f > f2)
            return f2;
        else
            return f;
    }

    public static int clamp(int i, int j, int k)
    {
        if(i < j)
            return j;
        if(i > k)
            return k;
        else
            return i;
    }
}
