// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.TypefaceCompat;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v4.content.res:
//            FontResourcesParserCompat

public final class ResourcesCompat
{

    private ResourcesCompat()
    {
    }

    public static int getColor(Resources resources, int i, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return resources.getColor(i, theme);
        else
            return resources.getColor(i);
    }

    public static ColorStateList getColorStateList(Resources resources, int i, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return resources.getColorStateList(i, theme);
        else
            return resources.getColorStateList(i);
    }

    public static Drawable getDrawable(Resources resources, int i, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return resources.getDrawable(i, theme);
        else
            return resources.getDrawable(i);
    }

    public static Drawable getDrawableForDensity(Resources resources, int i, int j, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return resources.getDrawableForDensity(i, j, theme);
        if(android.os.Build.VERSION.SDK_INT >= 15)
            return resources.getDrawableForDensity(i, j);
        else
            return resources.getDrawable(i);
    }

    public static Typeface getFont(Context context, int i)
        throws android.content.res.Resources.NotFoundException
    {
        if(context.isRestricted())
            return null;
        else
            return loadFont(context, i, new TypedValue(), 0, null);
    }

    public static Typeface getFont(Context context, int i, TypedValue typedvalue, int j, TextView textview)
        throws android.content.res.Resources.NotFoundException
    {
        if(context.isRestricted())
            return null;
        else
            return loadFont(context, i, typedvalue, j, textview);
    }

    private static Typeface loadFont(Context context, int i, TypedValue typedvalue, int j, TextView textview)
    {
        Resources resources = context.getResources();
        resources.getValue(i, typedvalue, true);
        context = loadFont(context, resources, typedvalue, i, j, textview);
        if(context != null)
            return context;
        else
            throw new android.content.res.Resources.NotFoundException((new StringBuilder()).append("Font resource ID #0x").append(Integer.toHexString(i)).toString());
    }

    private static Typeface loadFont(Context context, Resources resources, TypedValue typedvalue, int i, int j, TextView textview)
    {
        String s;
        if(typedvalue.string == null)
            throw new android.content.res.Resources.NotFoundException((new StringBuilder()).append("Resource \"").append(resources.getResourceName(i)).append("\" (").append(Integer.toHexString(i)).append(") is not a Font: ").append(typedvalue).toString());
        s = typedvalue.string.toString();
        if(s.startsWith("res/")) goto _L2; else goto _L1
_L1:
        typedvalue = null;
_L4:
        return typedvalue;
_L2:
        Typeface typeface;
        typeface = TypefaceCompat.findFromCache(resources, i, j);
        typedvalue = typeface;
        if(typeface != null) goto _L4; else goto _L3
_L3:
        if(!s.toLowerCase().endsWith(".xml"))
            break MISSING_BLOCK_LABEL_150;
        typedvalue = FontResourcesParserCompat.parse(resources.getXml(i), resources);
        if(typedvalue != null)
            break MISSING_BLOCK_LABEL_138;
        Log.e("ResourcesCompat", "Failed to find font-family tag");
        return null;
        return TypefaceCompat.createFromResourcesFamilyXml(context, typedvalue, resources, i, j, textview);
        context = TypefaceCompat.createFromResourcesFontFile(context, resources, i, s, j);
        return context;
        context;
        Log.e("ResourcesCompat", (new StringBuilder()).append("Failed to parse xml resource ").append(s).toString(), context);
_L6:
        return null;
        context;
        Log.e("ResourcesCompat", (new StringBuilder()).append("Failed to read xml resource ").append(s).toString(), context);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static final String TAG = "ResourcesCompat";
}
