// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.lang.reflect.*;
import java.nio.ByteBuffer;
import java.util.List;

// Referenced classes of package android.support.v4.graphics:
//            TypefaceCompatBaseImpl, TypefaceCompatUtil

class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl
{

    TypefaceCompatApi24Impl()
    {
    }

    private static boolean addFontWeightStyle(Object obj, ByteBuffer bytebuffer, int i, int j, boolean flag)
    {
        flag = ((Boolean)sAddFontWeightStyle.invoke(obj, new Object[] {
            bytebuffer, Integer.valueOf(i), null, Integer.valueOf(j), Boolean.valueOf(flag)
        })).booleanValue();
        return flag;
        obj;
_L2:
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static Typeface createFromFamiliesWithDefault(Object obj)
    {
        Object obj1 = Array.newInstance(sFontFamily, 1);
        Array.set(obj1, 0, obj);
        obj = (Typeface)sCreateFromFamiliesWithDefault.invoke(null, new Object[] {
            obj1
        });
        return ((Typeface) (obj));
        obj;
_L2:
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean isUsable()
    {
        if(sAddFontWeightStyle == null)
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        return sAddFontWeightStyle != null;
    }

    private static Object newFamily()
    {
        Object obj = sFontFamilyCtor.newInstance(new Object[0]);
        return obj;
        Object obj1;
        obj1;
_L2:
        throw new RuntimeException(((Throwable) (obj1)));
        obj1;
        continue; /* Loop/switch isn't completed */
        obj1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry fontfamilyfilesresourceentry, Resources resources, int i)
    {
        Object obj = newFamily();
        fontfamilyfilesresourceentry = fontfamilyfilesresourceentry.getEntries();
        int j = fontfamilyfilesresourceentry.length;
        for(i = 0; i < j; i++)
        {
            android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry fontfileresourceentry = fontfamilyfilesresourceentry[i];
            if(!addFontWeightStyle(obj, TypefaceCompatUtil.copyToDirectBuffer(context, resources, fontfileresourceentry.getResourceId()), 0, fontfileresourceentry.getWeight(), fontfileresourceentry.isItalic()))
                return null;
        }

        return createFromFamiliesWithDefault(obj);
    }

    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationsignal, android.support.v4.provider.FontsContractCompat.FontInfo afontinfo[], int i)
    {
        Object obj = newFamily();
        SimpleArrayMap simplearraymap = new SimpleArrayMap();
        int j = afontinfo.length;
        for(i = 0; i < j; i++)
        {
            android.support.v4.provider.FontsContractCompat.FontInfo fontinfo = afontinfo[i];
            android.net.Uri uri = fontinfo.getUri();
            ByteBuffer bytebuffer1 = (ByteBuffer)simplearraymap.get(uri);
            ByteBuffer bytebuffer = bytebuffer1;
            if(bytebuffer1 == null)
            {
                bytebuffer = TypefaceCompatUtil.mmap(context, cancellationsignal, uri);
                simplearraymap.put(uri, bytebuffer);
            }
            if(!addFontWeightStyle(obj, bytebuffer, fontinfo.getTtcIndex(), fontinfo.getWeight(), fontinfo.isItalic()))
                return null;
        }

        return createFromFamiliesWithDefault(obj);
    }

    private static final String ADD_FONT_WEIGHT_STYLE_METHOD = "addFontWeightStyle";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String TAG = "TypefaceCompatApi24Impl";
    private static final Method sAddFontWeightStyle;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;

    static 
    {
        Object obj;
        Method method;
        Class class1;
        Constructor constructor;
        class1 = Class.forName("android.graphics.FontFamily");
        constructor = class1.getConstructor(new Class[0]);
        obj = class1.getMethod("addFontWeightStyle", new Class[] {
            java/nio/ByteBuffer, Integer.TYPE, java/util/List, Integer.TYPE, Boolean.TYPE
        });
        method = android/graphics/Typeface.getMethod("createFromFamiliesWithDefault", new Class[] {
            Array.newInstance(class1, 1).getClass()
        });
_L2:
        sFontFamilyCtor = constructor;
        sFontFamily = class1;
        sAddFontWeightStyle = ((Method) (obj));
        sCreateFromFamiliesWithDefault = method;
        return;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
_L3:
        Log.e("TypefaceCompatApi24Impl", classnotfoundexception.getClass().getName(), classnotfoundexception);
        class1 = null;
        constructor = null;
        classnotfoundexception = null;
        method = null;
        if(true) goto _L2; else goto _L1
_L1:
        classnotfoundexception;
          goto _L3
    }
}
