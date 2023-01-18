// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics;

import android.graphics.Paint;

// Referenced classes of package android.support.v4.graphics:
//            PaintCompatApi14

public final class PaintCompat
{

    private PaintCompat()
    {
    }

    public static boolean hasGlyph(Paint paint, String s)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return paint.hasGlyph(s);
        else
            return PaintCompatApi14.hasGlyph(paint, s);
    }
}
