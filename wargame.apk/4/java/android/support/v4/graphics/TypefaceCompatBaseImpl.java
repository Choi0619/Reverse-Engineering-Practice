// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import java.io.*;

// Referenced classes of package android.support.v4.graphics:
//            TypefaceCompat, TypefaceCompatUtil

class TypefaceCompatBaseImpl
    implements TypefaceCompat.TypefaceCompatImpl
{
    private static interface StyleExtractor
    {

        public abstract int getWeight(Object obj);

        public abstract boolean isItalic(Object obj);
    }


    TypefaceCompatBaseImpl()
    {
    }

    private android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry findBestEntry(android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry fontfamilyfilesresourceentry, int i)
    {
        return (android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry)findBestFont(fontfamilyfilesresourceentry.getEntries(), i, new StyleExtractor() {

            public int getWeight(android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry fontfileresourceentry)
            {
                return fontfileresourceentry.getWeight();
            }

            public volatile int getWeight(Object obj)
            {
                return getWeight((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry)obj);
            }

            public boolean isItalic(android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry fontfileresourceentry)
            {
                return fontfileresourceentry.isItalic();
            }

            public volatile boolean isItalic(Object obj)
            {
                return isItalic((android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry)obj);
            }

            final TypefaceCompatBaseImpl this$0;

            
            {
                this$0 = TypefaceCompatBaseImpl.this;
                super();
            }
        }
);
    }

    private static Object findBestFont(Object aobj[], int i, StyleExtractor styleextractor)
    {
        char c;
        int j;
        int i1;
        boolean flag;
        Object obj;
        if((i & 1) == 0)
            c = '\u0190';
        else
            c = '\u02BC';
        if((i & 2) != 0)
            flag = true;
        else
            flag = false;
        obj = null;
        j = 0x7fffffff;
        i1 = aobj.length;
        i = 0;
        while(i < i1) 
        {
label0:
            {
                Object obj1 = aobj[i];
                int l = Math.abs(styleextractor.getWeight(obj1) - c);
                int k;
                if(styleextractor.isItalic(obj1) == flag)
                    k = 0;
                else
                    k = 1;
                l = l * 2 + k;
                if(obj != null)
                {
                    k = j;
                    if(j <= l)
                        break label0;
                }
                obj = obj1;
                k = l;
            }
            i++;
            j = k;
        }
        return obj;
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry fontfamilyfilesresourceentry, Resources resources, int i)
    {
        fontfamilyfilesresourceentry = findBestEntry(fontfamilyfilesresourceentry, i);
        if(fontfamilyfilesresourceentry == null)
            return null;
        else
            return TypefaceCompat.createFromResourcesFontFile(context, resources, fontfamilyfilesresourceentry.getResourceId(), fontfamilyfilesresourceentry.getFileName(), i);
    }

    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationsignal, android.support.v4.provider.FontsContractCompat.FontInfo afontinfo[], int i)
    {
        Object obj;
        if(afontinfo.length < 1)
            return null;
        obj = findBestInfo(afontinfo, i);
        afontinfo = null;
        cancellationsignal = null;
        try
        {
            obj = context.getContentResolver().openInputStream(((android.support.v4.provider.FontsContractCompat.FontInfo) (obj)).getUri());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            TypefaceCompatUtil.closeQuietly(cancellationsignal);
            return null;
        }
        cancellationsignal = ((CancellationSignal) (obj));
        afontinfo = ((android.support.v4.provider.FontsContractCompat.FontInfo []) (obj));
        context = createFromInputStream(context, ((InputStream) (obj)));
        TypefaceCompatUtil.closeQuietly(((java.io.Closeable) (obj)));
        return context;
        context;
        TypefaceCompatUtil.closeQuietly(afontinfo);
        throw context;
    }

    protected Typeface createFromInputStream(Context context, InputStream inputstream)
    {
        context = TypefaceCompatUtil.getTempFile(context);
        if(context == null)
            return null;
        boolean flag;
        try
        {
            flag = TypefaceCompatUtil.copyToFile(context, inputstream);
        }
        // Misplaced declaration of an exception variable
        catch(InputStream inputstream)
        {
            context.delete();
            return null;
        }
        if(!flag)
        {
            context.delete();
            return null;
        }
        inputstream = Typeface.createFromFile(context.getPath());
        context.delete();
        return inputstream;
        inputstream;
        context.delete();
        throw inputstream;
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String s, int j)
    {
        context = TypefaceCompatUtil.getTempFile(context);
        if(context == null)
            return null;
        boolean flag;
        try
        {
            flag = TypefaceCompatUtil.copyToFile(context, resources, i);
        }
        // Misplaced declaration of an exception variable
        catch(Resources resources)
        {
            context.delete();
            return null;
        }
        if(!flag)
        {
            context.delete();
            return null;
        }
        resources = Typeface.createFromFile(context.getPath());
        context.delete();
        return resources;
        resources;
        context.delete();
        throw resources;
    }

    protected android.support.v4.provider.FontsContractCompat.FontInfo findBestInfo(android.support.v4.provider.FontsContractCompat.FontInfo afontinfo[], int i)
    {
        return (android.support.v4.provider.FontsContractCompat.FontInfo)findBestFont(afontinfo, i, new StyleExtractor() {

            public int getWeight(android.support.v4.provider.FontsContractCompat.FontInfo fontinfo)
            {
                return fontinfo.getWeight();
            }

            public volatile int getWeight(Object obj)
            {
                return getWeight((android.support.v4.provider.FontsContractCompat.FontInfo)obj);
            }

            public boolean isItalic(android.support.v4.provider.FontsContractCompat.FontInfo fontinfo)
            {
                return fontinfo.isItalic();
            }

            public volatile boolean isItalic(Object obj)
            {
                return isItalic((android.support.v4.provider.FontsContractCompat.FontInfo)obj);
            }

            final TypefaceCompatBaseImpl this$0;

            
            {
                this$0 = TypefaceCompatBaseImpl.this;
                super();
            }
        }
);
    }

    private static final String CACHE_FILE_PREFIX = "cached_font_";
    private static final String TAG = "TypefaceCompatBaseImpl";
}
