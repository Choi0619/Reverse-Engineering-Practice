// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.LocaleList;
import java.util.Locale;

// Referenced classes of package android.support.v4.os:
//            LocaleHelper, LocaleListInterface, LocaleListHelper

public final class LocaleListCompat
{
    static class LocaleListCompatApi24Impl
        implements LocaleListInterface
    {

        public boolean equals(Object obj)
        {
            return mLocaleList.equals(((LocaleListCompat)obj).unwrap());
        }

        public Locale get(int i)
        {
            return mLocaleList.get(i);
        }

        public Locale getFirstMatch(String as[])
        {
            if(mLocaleList != null)
                return mLocaleList.getFirstMatch(as);
            else
                return null;
        }

        public Object getLocaleList()
        {
            return mLocaleList;
        }

        public int hashCode()
        {
            return mLocaleList.hashCode();
        }

        public int indexOf(Locale locale)
        {
            return mLocaleList.indexOf(locale);
        }

        public boolean isEmpty()
        {
            return mLocaleList.isEmpty();
        }

        public transient void setLocaleList(Locale alocale[])
        {
            mLocaleList = new LocaleList(alocale);
        }

        public int size()
        {
            return mLocaleList.size();
        }

        public String toLanguageTags()
        {
            return mLocaleList.toLanguageTags();
        }

        public String toString()
        {
            return mLocaleList.toString();
        }

        private LocaleList mLocaleList;

        LocaleListCompatApi24Impl()
        {
            mLocaleList = new LocaleList(new Locale[0]);
        }
    }

    static class LocaleListCompatBaseImpl
        implements LocaleListInterface
    {

        public boolean equals(Object obj)
        {
            return mLocaleList.equals(((LocaleListCompat)obj).unwrap());
        }

        public Locale get(int i)
        {
            return mLocaleList.get(i);
        }

        public Locale getFirstMatch(String as[])
        {
            if(mLocaleList != null)
                return mLocaleList.getFirstMatch(as);
            else
                return null;
        }

        public Object getLocaleList()
        {
            return mLocaleList;
        }

        public int hashCode()
        {
            return mLocaleList.hashCode();
        }

        public int indexOf(Locale locale)
        {
            return mLocaleList.indexOf(locale);
        }

        public boolean isEmpty()
        {
            return mLocaleList.isEmpty();
        }

        public transient void setLocaleList(Locale alocale[])
        {
            mLocaleList = new LocaleListHelper(alocale);
        }

        public int size()
        {
            return mLocaleList.size();
        }

        public String toLanguageTags()
        {
            return mLocaleList.toLanguageTags();
        }

        public String toString()
        {
            return mLocaleList.toString();
        }

        private LocaleListHelper mLocaleList;

        LocaleListCompatBaseImpl()
        {
            mLocaleList = new LocaleListHelper(new Locale[0]);
        }
    }


    private LocaleListCompat()
    {
    }

    public static transient LocaleListCompat create(Locale alocale[])
    {
        LocaleListCompat localelistcompat = new LocaleListCompat();
        localelistcompat.setLocaleListArray(alocale);
        return localelistcompat;
    }

    public static LocaleListCompat forLanguageTags(String s)
    {
        if(s == null || s.isEmpty())
            return getEmptyLocaleList();
        String as[] = s.split(",");
        Locale alocale[] = new Locale[as.length];
        int i = 0;
        while(i < alocale.length) 
        {
            if(android.os.Build.VERSION.SDK_INT >= 21)
                s = Locale.forLanguageTag(as[i]);
            else
                s = LocaleHelper.forLanguageTag(as[i]);
            alocale[i] = s;
            i++;
        }
        s = new LocaleListCompat();
        s.setLocaleListArray(alocale);
        return s;
    }

    public static LocaleListCompat getAdjustedDefault()
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return wrap(LocaleList.getAdjustedDefault());
        else
            return create(new Locale[] {
                Locale.getDefault()
            });
    }

    public static LocaleListCompat getDefault()
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return wrap(LocaleList.getDefault());
        else
            return create(new Locale[] {
                Locale.getDefault()
            });
    }

    public static LocaleListCompat getEmptyLocaleList()
    {
        return sEmptyLocaleList;
    }

    private void setLocaleList(LocaleList localelist)
    {
        int j = localelist.size();
        if(j > 0)
        {
            Locale alocale[] = new Locale[j];
            for(int i = 0; i < j; i++)
                alocale[i] = localelist.get(i);

            IMPL.setLocaleList(alocale);
        }
    }

    private transient void setLocaleListArray(Locale alocale[])
    {
        IMPL.setLocaleList(alocale);
    }

    public static LocaleListCompat wrap(Object obj)
    {
        LocaleListCompat localelistcompat = new LocaleListCompat();
        if(obj instanceof LocaleList)
            localelistcompat.setLocaleList((LocaleList)obj);
        return localelistcompat;
    }

    public boolean equals(Object obj)
    {
        return IMPL.equals(obj);
    }

    public Locale get(int i)
    {
        return IMPL.get(i);
    }

    public Locale getFirstMatch(String as[])
    {
        return IMPL.getFirstMatch(as);
    }

    public int hashCode()
    {
        return IMPL.hashCode();
    }

    public int indexOf(Locale locale)
    {
        return IMPL.indexOf(locale);
    }

    public boolean isEmpty()
    {
        return IMPL.isEmpty();
    }

    public int size()
    {
        return IMPL.size();
    }

    public String toLanguageTags()
    {
        return IMPL.toLanguageTags();
    }

    public String toString()
    {
        return IMPL.toString();
    }

    public Object unwrap()
    {
        return IMPL.getLocaleList();
    }

    static final LocaleListInterface IMPL;
    private static final LocaleListCompat sEmptyLocaleList = new LocaleListCompat();

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            IMPL = new LocaleListCompatApi24Impl();
        else
            IMPL = new LocaleListCompatBaseImpl();
    }
}
