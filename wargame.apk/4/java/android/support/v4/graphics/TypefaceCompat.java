// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.util.LruCache;
import android.widget.TextView;

// Referenced classes of package android.support.v4.graphics:
//            TypefaceCompatApi26Impl, TypefaceCompatApi24Impl, TypefaceCompatApi21Impl, TypefaceCompatBaseImpl

public class TypefaceCompat
{
    static interface TypefaceCompatImpl
    {

        public abstract Typeface createFromFontFamilyFilesResourceEntry(Context context, android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry fontfamilyfilesresourceentry, Resources resources, int i);

        public abstract Typeface createFromFontInfo(Context context, CancellationSignal cancellationsignal, android.support.v4.provider.FontsContractCompat.FontInfo afontinfo[], int i);

        public abstract Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String s, int j);
    }


    private TypefaceCompat()
    {
    }

    public static Typeface createFromFontInfo(Context context, CancellationSignal cancellationsignal, android.support.v4.provider.FontsContractCompat.FontInfo afontinfo[], int i)
    {
        return sTypefaceCompatImpl.createFromFontInfo(context, cancellationsignal, afontinfo, i);
    }

    public static Typeface createFromResourcesFamilyXml(Context context, android.support.v4.content.res.FontResourcesParserCompat.FamilyResourceEntry familyresourceentry, Resources resources, int i, int j, TextView textview)
    {
        if(familyresourceentry instanceof android.support.v4.content.res.FontResourcesParserCompat.ProviderResourceEntry)
        {
            familyresourceentry = (android.support.v4.content.res.FontResourcesParserCompat.ProviderResourceEntry)familyresourceentry;
            context = FontsContractCompat.getFontSync(context, familyresourceentry.getRequest(), textview, familyresourceentry.getFetchStrategy(), familyresourceentry.getTimeout(), j);
        } else
        {
            context = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context, (android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry)familyresourceentry, resources, j);
        }
        if(context != null)
            sTypefaceCache.put(createResourceUid(resources, i, j), context);
        return context;
    }

    public static Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String s, int j)
    {
        context = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources, i, s, j);
        if(context != null)
            sTypefaceCache.put(createResourceUid(resources, i, j), context);
        return context;
    }

    private static String createResourceUid(Resources resources, int i, int j)
    {
        return (new StringBuilder()).append(resources.getResourcePackageName(i)).append("-").append(i).append("-").append(j).toString();
    }

    public static Typeface findFromCache(Resources resources, int i, int j)
    {
        return (Typeface)sTypefaceCache.get(createResourceUid(resources, i, j));
    }

    private static final String TAG = "TypefaceCompat";
    private static final LruCache sTypefaceCache = new LruCache(16);
    private static final TypefaceCompatImpl sTypefaceCompatImpl;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 24 && TypefaceCompatApi24Impl.isUsable())
            sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 21)
            sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
        else
            sTypefaceCompatImpl = new TypefaceCompatBaseImpl();
    }
}
