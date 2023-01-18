// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.graphics.drawable;

import android.animation.TypeEvaluator;

public class ArgbEvaluator
    implements TypeEvaluator
{

    public ArgbEvaluator()
    {
    }

    public static ArgbEvaluator getInstance()
    {
        return sInstance;
    }

    public Object evaluate(float f, Object obj, Object obj1)
    {
        int i = ((Integer)obj).intValue();
        float f1 = (float)(i >> 24 & 0xff) / 255F;
        float f4 = (float)(i >> 16 & 0xff) / 255F;
        float f5 = (float)(i >> 8 & 0xff) / 255F;
        float f6 = (float)(i & 0xff) / 255F;
        i = ((Integer)obj1).intValue();
        float f2 = (float)(i >> 24 & 0xff) / 255F;
        float f8 = (float)(i >> 16 & 0xff) / 255F;
        float f7 = (float)(i >> 8 & 0xff) / 255F;
        float f3 = (float)(i & 0xff) / 255F;
        f4 = (float)Math.pow(f4, 2.2000000000000002D);
        f5 = (float)Math.pow(f5, 2.2000000000000002D);
        f6 = (float)Math.pow(f6, 2.2000000000000002D);
        f8 = (float)Math.pow(f8, 2.2000000000000002D);
        f7 = (float)Math.pow(f7, 2.2000000000000002D);
        f3 = (float)Math.pow(f3, 2.2000000000000002D);
        f4 = (float)Math.pow(f4 + (f8 - f4) * f, 0.45454545454545453D);
        f5 = (float)Math.pow(f5 + (f7 - f5) * f, 0.45454545454545453D);
        f3 = (float)Math.pow(f6 + (f3 - f6) * f, 0.45454545454545453D);
        return Integer.valueOf(Math.round((f1 + (f2 - f1) * f) * 255F) << 24 | Math.round(f4 * 255F) << 16 | Math.round(f5 * 255F) << 8 | Math.round(f3 * 255F));
    }

    private static final ArgbEvaluator sInstance = new ArgbEvaluator();

}
