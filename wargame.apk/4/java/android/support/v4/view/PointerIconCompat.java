// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.PointerIcon;

public final class PointerIconCompat
{

    private PointerIconCompat(Object obj)
    {
        mPointerIcon = obj;
    }

    public static PointerIconCompat create(Bitmap bitmap, float f, float f1)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return new PointerIconCompat(PointerIcon.create(bitmap, f, f1));
        else
            return new PointerIconCompat(null);
    }

    public static PointerIconCompat getSystemIcon(Context context, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return new PointerIconCompat(PointerIcon.getSystemIcon(context, i));
        else
            return new PointerIconCompat(null);
    }

    public static PointerIconCompat load(Resources resources, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return new PointerIconCompat(PointerIcon.load(resources, i));
        else
            return new PointerIconCompat(null);
    }

    public Object getPointerIcon()
    {
        return mPointerIcon;
    }

    public static final int TYPE_ALIAS = 1010;
    public static final int TYPE_ALL_SCROLL = 1013;
    public static final int TYPE_ARROW = 1000;
    public static final int TYPE_CELL = 1006;
    public static final int TYPE_CONTEXT_MENU = 1001;
    public static final int TYPE_COPY = 1011;
    public static final int TYPE_CROSSHAIR = 1007;
    public static final int TYPE_DEFAULT = 1000;
    public static final int TYPE_GRAB = 1020;
    public static final int TYPE_GRABBING = 1021;
    public static final int TYPE_HAND = 1002;
    public static final int TYPE_HELP = 1003;
    public static final int TYPE_HORIZONTAL_DOUBLE_ARROW = 1014;
    public static final int TYPE_NO_DROP = 1012;
    public static final int TYPE_NULL = 0;
    public static final int TYPE_TEXT = 1008;
    public static final int TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW = 1017;
    public static final int TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW = 1016;
    public static final int TYPE_VERTICAL_DOUBLE_ARROW = 1015;
    public static final int TYPE_VERTICAL_TEXT = 1009;
    public static final int TYPE_WAIT = 1004;
    public static final int TYPE_ZOOM_IN = 1018;
    public static final int TYPE_ZOOM_OUT = 1019;
    private Object mPointerIcon;
}
