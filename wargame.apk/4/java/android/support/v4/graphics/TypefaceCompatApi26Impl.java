// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.v4.provider.FontsContractCompat;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.ByteBuffer;
import java.util.Map;

// Referenced classes of package android.support.v4.graphics:
//            TypefaceCompatApi21Impl

public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl
{

    public TypefaceCompatApi26Impl()
    {
    }

    private static boolean abortCreation(Object obj)
    {
        boolean flag = ((Boolean)sAbortCreation.invoke(obj, new Object[0])).booleanValue();
        return flag;
        obj;
_L2:
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static boolean addFontFromAssetManager(Context context, Object obj, String s, int i, int j, int k)
    {
        boolean flag = ((Boolean)sAddFontFromAssetManager.invoke(obj, new Object[] {
            context.getAssets(), s, Integer.valueOf(0), Boolean.valueOf(false), Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), null
        })).booleanValue();
        return flag;
        context;
_L2:
        throw new RuntimeException(context);
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static boolean addFontFromBuffer(Object obj, ByteBuffer bytebuffer, int i, int j, int k)
    {
        boolean flag = ((Boolean)sAddFontFromBuffer.invoke(obj, new Object[] {
            bytebuffer, Integer.valueOf(i), null, Integer.valueOf(j), Integer.valueOf(k)
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
            obj1, Integer.valueOf(-1), Integer.valueOf(-1)
        });
        return ((Typeface) (obj));
        obj;
_L2:
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static boolean freeze(Object obj)
    {
        boolean flag = ((Boolean)sFreeze.invoke(obj, new Object[0])).booleanValue();
        return flag;
        obj;
_L2:
        throw new RuntimeException(((Throwable) (obj)));
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static boolean isFontFamilyPrivateAPIAvailable()
    {
        if(sAddFontFromAssetManager == null)
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        return sAddFontFromAssetManager != null;
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
        if(!isFontFamilyPrivateAPIAvailable())
            return super.createFromFontFamilyFilesResourceEntry(context, fontfamilyfilesresourceentry, resources, i);
        resources = ((Resources) (newFamily()));
        fontfamilyfilesresourceentry = fontfamilyfilesresourceentry.getEntries();
        int k = fontfamilyfilesresourceentry.length;
        int j;
        for(i = 0; i < k; i++)
        {
            android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry fontfileresourceentry = fontfamilyfilesresourceentry[i];
            String s = fontfileresourceentry.getFileName();
            int l = fontfileresourceentry.getWeight();
            if(fontfileresourceentry.isItalic())
                j = 1;
            else
                j = 0;
            if(!addFontFromAssetManager(context, resources, s, 0, l, j))
            {
                abortCreation(resources);
                return null;
            }
        }

        if(!freeze(resources))
            return null;
        else
            return createFromFamiliesWithDefault(resources);
    }

    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationsignal, android.support.v4.provider.FontsContractCompat.FontInfo afontinfo[], int i)
    {
        if(afontinfo.length >= 1) goto _L2; else goto _L1
_L1:
        cancellationsignal = null;
_L6:
        return cancellationsignal;
_L2:
        if(isFontFamilyPrivateAPIAvailable()) goto _L4; else goto _L3
_L3:
        Object obj = findBestInfo(afontinfo, i);
        context = context.getContentResolver();
        int j;
        int k;
        int l;
        int i1;
        android.support.v4.provider.FontsContractCompat.FontInfo fontinfo;
        try
        {
            afontinfo = context.openFileDescriptor(((android.support.v4.provider.FontsContractCompat.FontInfo) (obj)).getUri(), "r", cancellationsignal);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return null;
        }
        cancellationsignal = null;
        context = (new android.graphics.Typeface.Builder(afontinfo.getFileDescriptor())).setWeight(((android.support.v4.provider.FontsContractCompat.FontInfo) (obj)).getWeight()).setItalic(((android.support.v4.provider.FontsContractCompat.FontInfo) (obj)).isItalic()).build();
        cancellationsignal = context;
        if(afontinfo == null) goto _L6; else goto _L5
_L5:
        if(true)
            break MISSING_BLOCK_LABEL_102;
        afontinfo.close();
        return context;
        context;
        throw new NullPointerException();
        afontinfo.close();
        return context;
        cancellationsignal;
        throw cancellationsignal;
        context;
_L9:
        if(afontinfo == null)
            break MISSING_BLOCK_LABEL_124;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_135;
        afontinfo.close();
_L7:
        throw context;
        afontinfo;
        cancellationsignal.addSuppressed(afontinfo);
          goto _L7
        afontinfo.close();
          goto _L7
_L4:
        context = FontsContractCompat.prepareFontData(context, afontinfo, cancellationsignal);
        obj = newFamily();
        j = 0;
        k = afontinfo.length;
        i = 0;
        while(i < k) 
        {
            fontinfo = afontinfo[i];
            cancellationsignal = (ByteBuffer)context.get(fontinfo.getUri());
            if(cancellationsignal != null)
            {
                l = fontinfo.getTtcIndex();
                i1 = fontinfo.getWeight();
                if(fontinfo.isItalic())
                    j = 1;
                else
                    j = 0;
                if(!addFontFromBuffer(obj, cancellationsignal, l, i1, j))
                {
                    abortCreation(obj);
                    return null;
                }
                j = 1;
            }
            i++;
        }
        if(j == 0)
        {
            abortCreation(obj);
            return null;
        }
        if(!freeze(obj))
            return null;
        else
            return createFromFamiliesWithDefault(obj);
        context;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String s, int j)
    {
        if(!isFontFamilyPrivateAPIAvailable())
            return super.createFromResourcesFontFile(context, resources, i, s, j);
        resources = ((Resources) (newFamily()));
        if(!addFontFromAssetManager(context, resources, s, 0, -1, -1))
        {
            abortCreation(resources);
            return null;
        }
        if(!freeze(resources))
            return null;
        else
            return createFromFamiliesWithDefault(resources);
    }

    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    private static final Method sAbortCreation;
    private static final Method sAddFontFromAssetManager;
    private static final Method sAddFontFromBuffer;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    private static final Method sFreeze;

    static 
    {
        Object obj;
        Method method;
        Method method1;
        Method method2;
        Class class1;
        Constructor constructor;
        Method method3;
        class1 = Class.forName("android.graphics.FontFamily");
        constructor = class1.getConstructor(new Class[0]);
        method = class1.getMethod("addFontFromAssetManager", new Class[] {
            android/content/res/AssetManager, java/lang/String, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, [Landroid/graphics/fonts/FontVariationAxis;
        });
        method1 = class1.getMethod("addFontFromBuffer", new Class[] {
            java/nio/ByteBuffer, Integer.TYPE, [Landroid/graphics/fonts/FontVariationAxis;, Integer.TYPE, Integer.TYPE
        });
        method3 = class1.getMethod("freeze", new Class[0]);
        obj = class1.getMethod("abortCreation", new Class[0]);
        method2 = android/graphics/Typeface.getDeclaredMethod("createFromFamiliesWithDefault", new Class[] {
            Array.newInstance(class1, 1).getClass(), Integer.TYPE, Integer.TYPE
        });
        method2.setAccessible(true);
_L2:
        sFontFamilyCtor = constructor;
        sFontFamily = class1;
        sAddFontFromAssetManager = method;
        sAddFontFromBuffer = method1;
        sFreeze = method3;
        sAbortCreation = ((Method) (obj));
        sCreateFromFamiliesWithDefault = method2;
        return;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
_L3:
        Log.e("TypefaceCompatApi26Impl", (new StringBuilder()).append("Unable to collect necessary methods for class ").append(classnotfoundexception.getClass().getName()).toString(), classnotfoundexception);
        class1 = null;
        constructor = null;
        method = null;
        method1 = null;
        method3 = null;
        classnotfoundexception = null;
        method2 = null;
        if(true) goto _L2; else goto _L1
_L1:
        classnotfoundexception;
          goto _L3
    }
}
