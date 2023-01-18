// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.provider.FontRequest;
import android.util.Base64;
import android.util.Xml;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class FontResourcesParserCompat
{
    public static interface FamilyResourceEntry
    {
    }

    public static interface FetchStrategy
        extends Annotation
    {
    }

    public static final class FontFamilyFilesResourceEntry
        implements FamilyResourceEntry
    {

        public FontFileResourceEntry[] getEntries()
        {
            return mEntries;
        }

        private final FontFileResourceEntry mEntries[];

        public FontFamilyFilesResourceEntry(FontFileResourceEntry afontfileresourceentry[])
        {
            mEntries = afontfileresourceentry;
        }
    }

    public static final class FontFileResourceEntry
    {

        public String getFileName()
        {
            return mFileName;
        }

        public int getResourceId()
        {
            return mResourceId;
        }

        public int getWeight()
        {
            return mWeight;
        }

        public boolean isItalic()
        {
            return mItalic;
        }

        private final String mFileName;
        private boolean mItalic;
        private int mResourceId;
        private int mWeight;

        public FontFileResourceEntry(String s, int i, boolean flag, int j)
        {
            mFileName = s;
            mWeight = i;
            mItalic = flag;
            mResourceId = j;
        }
    }

    public static final class ProviderResourceEntry
        implements FamilyResourceEntry
    {

        public int getFetchStrategy()
        {
            return mStrategy;
        }

        public FontRequest getRequest()
        {
            return mRequest;
        }

        public int getTimeout()
        {
            return mTimeoutMs;
        }

        private final FontRequest mRequest;
        private final int mStrategy;
        private final int mTimeoutMs;

        public ProviderResourceEntry(FontRequest fontrequest, int i, int j)
        {
            mRequest = fontrequest;
            mStrategy = i;
            mTimeoutMs = j;
        }
    }


    public FontResourcesParserCompat()
    {
    }

    public static FamilyResourceEntry parse(XmlPullParser xmlpullparser, Resources resources)
        throws XmlPullParserException, IOException
    {
        int i;
        do
            i = xmlpullparser.next();
        while(i != 2 && i != 1);
        if(i != 2)
            throw new XmlPullParserException("No start tag found");
        else
            return readFamilies(xmlpullparser, resources);
    }

    public static List readCerts(Resources resources, int i)
    {
        ArrayList arraylist = null;
        Object obj = null;
        if(i != 0)
        {
            TypedArray typedarray = resources.obtainTypedArray(i);
            arraylist = obj;
            if(typedarray.length() > 0)
            {
                ArrayList arraylist1 = new ArrayList();
                boolean flag;
                if(typedarray.getResourceId(0, 0) != 0)
                    flag = true;
                else
                    flag = false;
                if(flag)
                {
                    i = 0;
                    do
                    {
                        arraylist = arraylist1;
                        if(i >= typedarray.length())
                            break;
                        arraylist1.add(toByteArrayList(resources.getStringArray(typedarray.getResourceId(i, 0))));
                        i++;
                    } while(true);
                } else
                {
                    arraylist1.add(toByteArrayList(resources.getStringArray(i)));
                    arraylist = arraylist1;
                }
            }
            typedarray.recycle();
        }
        if(arraylist != null)
            return arraylist;
        else
            return Collections.emptyList();
    }

    private static FamilyResourceEntry readFamilies(XmlPullParser xmlpullparser, Resources resources)
        throws XmlPullParserException, IOException
    {
        xmlpullparser.require(2, null, "font-family");
        if(xmlpullparser.getName().equals("font-family"))
        {
            return readFamily(xmlpullparser, resources);
        } else
        {
            skip(xmlpullparser);
            return null;
        }
    }

    private static FamilyResourceEntry readFamily(XmlPullParser xmlpullparser, Resources resources)
        throws XmlPullParserException, IOException
    {
        Object obj = resources.obtainAttributes(Xml.asAttributeSet(xmlpullparser), android.support.compat.R.styleable.FontFamily);
        String s = ((TypedArray) (obj)).getString(android.support.compat.R.styleable.FontFamily_fontProviderAuthority);
        String s1 = ((TypedArray) (obj)).getString(android.support.compat.R.styleable.FontFamily_fontProviderPackage);
        String s2 = ((TypedArray) (obj)).getString(android.support.compat.R.styleable.FontFamily_fontProviderQuery);
        int i = ((TypedArray) (obj)).getResourceId(android.support.compat.R.styleable.FontFamily_fontProviderCerts, 0);
        int j = ((TypedArray) (obj)).getInteger(android.support.compat.R.styleable.FontFamily_fontProviderFetchStrategy, 1);
        int k = ((TypedArray) (obj)).getInteger(android.support.compat.R.styleable.FontFamily_fontProviderFetchTimeout, 500);
        ((TypedArray) (obj)).recycle();
        if(s != null && s1 != null && s2 != null)
        {
            for(; xmlpullparser.next() != 3; skip(xmlpullparser));
            return new ProviderResourceEntry(new FontRequest(s, s1, s2, readCerts(resources, i)), j, k);
        }
        obj = new ArrayList();
        do
        {
            if(xmlpullparser.next() == 3)
                break;
            if(xmlpullparser.getEventType() == 2)
                if(xmlpullparser.getName().equals("font"))
                    ((List) (obj)).add(readFont(xmlpullparser, resources));
                else
                    skip(xmlpullparser);
        } while(true);
        if(((List) (obj)).isEmpty())
            return null;
        else
            return new FontFamilyFilesResourceEntry((FontFileResourceEntry[])((List) (obj)).toArray(new FontFileResourceEntry[((List) (obj)).size()]));
    }

    private static FontFileResourceEntry readFont(XmlPullParser xmlpullparser, Resources resources)
        throws XmlPullParserException, IOException
    {
        boolean flag = true;
        resources = resources.obtainAttributes(Xml.asAttributeSet(xmlpullparser), android.support.compat.R.styleable.FontFamilyFont);
        int i = resources.getInt(android.support.compat.R.styleable.FontFamilyFont_fontWeight, 400);
        int j;
        String s;
        if(1 != resources.getInt(android.support.compat.R.styleable.FontFamilyFont_fontStyle, 0))
            flag = false;
        j = resources.getResourceId(android.support.compat.R.styleable.FontFamilyFont_font, 0);
        s = resources.getString(android.support.compat.R.styleable.FontFamilyFont_font);
        resources.recycle();
        for(; xmlpullparser.next() != 3; skip(xmlpullparser));
        return new FontFileResourceEntry(s, i, flag, j);
    }

    private static void skip(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i = 1;
        do
        {
            if(i <= 0)
                break;
            switch(xmlpullparser.next())
            {
            case 2: // '\002'
                i++;
                break;

            case 3: // '\003'
                i--;
                break;
            }
        } while(true);
    }

    private static List toByteArrayList(String as[])
    {
        ArrayList arraylist = new ArrayList();
        int j = as.length;
        for(int i = 0; i < j; i++)
            arraylist.add(Base64.decode(as[i], 0));

        return arraylist;
    }

    private static final int DEFAULT_TIMEOUT_MILLIS = 500;
    public static final int FETCH_STRATEGY_ASYNC = 1;
    public static final int FETCH_STRATEGY_BLOCKING = 0;
    public static final int INFINITE_TIMEOUT_VALUE = -1;
    private static final int ITALIC = 1;
    private static final int NORMAL_WEIGHT = 400;
}
