// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.Icon;
import android.net.Uri;

public class IconCompat
{

    private IconCompat(int i)
    {
        mType = i;
    }

    static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap bitmap)
    {
        int i = (int)(0.6666667F * (float)Math.min(bitmap.getWidth(), bitmap.getHeight()));
        Bitmap bitmap1 = Bitmap.createBitmap(i, i, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint(3);
        float f = (float)i * 0.5F;
        float f1 = f * 0.9166667F;
        float f2 = 0.01041667F * (float)i;
        paint.setColor(0);
        paint.setShadowLayer(f2, 0.0F, 0.02083333F * (float)i, 0x3d000000);
        canvas.drawCircle(f, f, f1, paint);
        paint.setShadowLayer(f2, 0.0F, 0.0F, 0x1e000000);
        canvas.drawCircle(f, f, f1, paint);
        paint.clearShadowLayer();
        paint.setColor(0xff000000);
        BitmapShader bitmapshader = new BitmapShader(bitmap, android.graphics.Shader.TileMode.CLAMP, android.graphics.Shader.TileMode.CLAMP);
        Matrix matrix = new Matrix();
        matrix.setTranslate(-(bitmap.getWidth() - i) / 2, -(bitmap.getHeight() - i) / 2);
        bitmapshader.setLocalMatrix(matrix);
        paint.setShader(bitmapshader);
        canvas.drawCircle(f, f, f1, paint);
        canvas.setBitmap(null);
        return bitmap1;
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap bitmap)
    {
        if(bitmap == null)
        {
            throw new IllegalArgumentException("Bitmap must not be null.");
        } else
        {
            IconCompat iconcompat = new IconCompat(5);
            iconcompat.mObj1 = bitmap;
            return iconcompat;
        }
    }

    public static IconCompat createWithBitmap(Bitmap bitmap)
    {
        if(bitmap == null)
        {
            throw new IllegalArgumentException("Bitmap must not be null.");
        } else
        {
            IconCompat iconcompat = new IconCompat(1);
            iconcompat.mObj1 = bitmap;
            return iconcompat;
        }
    }

    public static IconCompat createWithContentUri(Uri uri)
    {
        if(uri == null)
            throw new IllegalArgumentException("Uri must not be null.");
        else
            return createWithContentUri(uri.toString());
    }

    public static IconCompat createWithContentUri(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Uri must not be null.");
        } else
        {
            IconCompat iconcompat = new IconCompat(4);
            iconcompat.mObj1 = s;
            return iconcompat;
        }
    }

    public static IconCompat createWithData(byte abyte0[], int i, int j)
    {
        if(abyte0 == null)
        {
            throw new IllegalArgumentException("Data must not be null.");
        } else
        {
            IconCompat iconcompat = new IconCompat(3);
            iconcompat.mObj1 = abyte0;
            iconcompat.mInt1 = i;
            iconcompat.mInt2 = j;
            return iconcompat;
        }
    }

    public static IconCompat createWithResource(Context context, int i)
    {
        if(context == null)
        {
            throw new IllegalArgumentException("Context must not be null.");
        } else
        {
            IconCompat iconcompat = new IconCompat(2);
            iconcompat.mInt1 = i;
            iconcompat.mObj1 = context;
            return iconcompat;
        }
    }

    public void addToShortcutIntent(Intent intent)
    {
        switch(mType)
        {
        case 3: // '\003'
        case 4: // '\004'
        default:
            throw new IllegalArgumentException("Icon type not supported for intent shortcuts");

        case 1: // '\001'
            intent.putExtra("android.intent.extra.shortcut.ICON", (Bitmap)mObj1);
            return;

        case 5: // '\005'
            intent.putExtra("android.intent.extra.shortcut.ICON", createLegacyIconFromAdaptiveIcon((Bitmap)mObj1));
            return;

        case 2: // '\002'
            intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", android.content.Intent.ShortcutIconResource.fromContext((Context)mObj1, mInt1));
            return;
        }
    }

    public Icon toIcon()
    {
        switch(mType)
        {
        default:
            throw new IllegalArgumentException("Unknown type");

        case 1: // '\001'
            return Icon.createWithBitmap((Bitmap)mObj1);

        case 5: // '\005'
            if(android.os.Build.VERSION.SDK_INT >= 26)
                return Icon.createWithAdaptiveBitmap((Bitmap)mObj1);
            else
                return Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap)mObj1));

        case 2: // '\002'
            return Icon.createWithResource((Context)mObj1, mInt1);

        case 3: // '\003'
            return Icon.createWithData((byte[])(byte[])mObj1, mInt1, mInt2);

        case 4: // '\004'
            return Icon.createWithContentUri((String)mObj1);
        }
    }

    private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25F;
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.01041667F;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667F;
    private static final float ICON_DIAMETER_FACTOR = 0.9166667F;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_OFFSET_FACTOR = 0.02083333F;
    private static final int TYPE_ADAPTIVE_BITMAP = 5;
    private static final int TYPE_BITMAP = 1;
    private static final int TYPE_DATA = 3;
    private static final int TYPE_RESOURCE = 2;
    private static final int TYPE_URI = 4;
    private int mInt1;
    private int mInt2;
    private Object mObj1;
    private final int mType;
}
