// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import java.util.*;

// Referenced classes of package android.support.v4.os:
//            LocaleHelper

final class LocaleListHelper
{

    LocaleListHelper(Locale locale, LocaleListHelper localelisthelper)
    {
        int k1;
        Locale alocale[];
label0:
        {
            super();
            if(locale == null)
                throw new NullPointerException("topLocale is null");
            int i;
            int i1;
            int j1;
            if(localelisthelper == null)
                i1 = 0;
            else
                i1 = localelisthelper.mList.length;
            k1 = -1;
            i = 0;
label1:
            do
            {
label2:
                {
                    j1 = k1;
                    if(i < i1)
                    {
                        if(!locale.equals(localelisthelper.mList[i]))
                            break label2;
                        j1 = i;
                    }
                    if(j1 == -1)
                        i = 1;
                    else
                        i = 0;
                    k1 = i1 + i;
                    alocale = new Locale[k1];
                    alocale[0] = (Locale)locale.clone();
                    if(j1 != -1)
                        break label1;
                    for(i = 0; i < i1; i++)
                        alocale[i + 1] = (Locale)localelisthelper.mList[i].clone();

                    break label0;
                }
                i++;
            } while(true);
            for(int j = 0; j < j1; j++)
                alocale[j + 1] = (Locale)localelisthelper.mList[j].clone();

            for(int k = j1 + 1; k < i1; k++)
                alocale[k] = (Locale)localelisthelper.mList[k].clone();

        }
        locale = new StringBuilder();
        for(int l = 0; l < k1; l++)
        {
            locale.append(LocaleHelper.toLanguageTag(alocale[l]));
            if(l < k1 - 1)
                locale.append(',');
        }

        mList = alocale;
        mStringRepresentation = locale.toString();
    }

    transient LocaleListHelper(Locale alocale[])
    {
        if(alocale.length == 0)
        {
            mList = sEmptyList;
            mStringRepresentation = "";
            return;
        }
        Locale alocale1[] = new Locale[alocale.length];
        HashSet hashset = new HashSet();
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = 0; i < alocale.length; i++)
        {
            Locale locale = alocale[i];
            if(locale == null)
                throw new NullPointerException((new StringBuilder()).append("list[").append(i).append("] is null").toString());
            if(hashset.contains(locale))
                throw new IllegalArgumentException((new StringBuilder()).append("list[").append(i).append("] is a repetition").toString());
            locale = (Locale)locale.clone();
            alocale1[i] = locale;
            stringbuilder.append(LocaleHelper.toLanguageTag(locale));
            if(i < alocale.length - 1)
                stringbuilder.append(',');
            hashset.add(locale);
        }

        mList = alocale1;
        mStringRepresentation = stringbuilder.toString();
    }

    private Locale computeFirstMatch(Collection collection, boolean flag)
    {
        int i = computeFirstMatchIndex(collection, flag);
        if(i == -1)
            return null;
        else
            return mList[i];
    }

    private int computeFirstMatchIndex(Collection collection, boolean flag)
    {
        int j;
        if(mList.length == 1)
        {
            j = 0;
        } else
        {
            if(mList.length == 0)
                return -1;
            j = 0x7fffffff;
            int i = j;
            if(flag)
            {
                int k = findFirstMatchIndex(EN_LATN);
                if(k == 0)
                    return 0;
                i = j;
                if(k < 0x7fffffff)
                    i = k;
            }
            collection = collection.iterator();
            do
            {
                if(!collection.hasNext())
                    break;
                j = findFirstMatchIndex(LocaleHelper.forLanguageTag((String)collection.next()));
                if(j == 0)
                    return 0;
                if(j < i)
                    i = j;
            } while(true);
            j = i;
            if(i == 0x7fffffff)
                return 0;
        }
        return j;
    }

    private int findFirstMatchIndex(Locale locale)
    {
        for(int i = 0; i < mList.length; i++)
            if(matchScore(locale, mList[i]) > 0)
                return i;

        return 0x7fffffff;
    }

    static LocaleListHelper forLanguageTags(String s)
    {
        if(s == null || s.isEmpty())
            return getEmptyLocaleList();
        s = s.split(",");
        Locale alocale[] = new Locale[s.length];
        for(int i = 0; i < alocale.length; i++)
            alocale[i] = LocaleHelper.forLanguageTag(s[i]);

        return new LocaleListHelper(alocale);
    }

    static LocaleListHelper getAdjustedDefault()
    {
        getDefault();
        LocaleListHelper localelisthelper;
        synchronized(sLock)
        {
            localelisthelper = sDefaultAdjustedLocaleList;
        }
        return localelisthelper;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static LocaleListHelper getDefault()
    {
        Object obj1;
label0:
        {
            obj1 = Locale.getDefault();
            synchronized(sLock)
            {
                if(((Locale) (obj1)).equals(sLastDefaultLocale))
                    break MISSING_BLOCK_LABEL_72;
                sLastDefaultLocale = ((Locale) (obj1));
                if(sDefaultLocaleList == null || !((Locale) (obj1)).equals(sDefaultLocaleList.get(0)))
                    break label0;
                obj1 = sDefaultLocaleList;
            }
            return ((LocaleListHelper) (obj1));
        }
        sDefaultLocaleList = new LocaleListHelper(((Locale) (obj1)), sLastExplicitlySetLocaleList);
        sDefaultAdjustedLocaleList = sDefaultLocaleList;
        obj1 = sDefaultLocaleList;
        obj;
        JVM INSTR monitorexit ;
        return ((LocaleListHelper) (obj1));
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static LocaleListHelper getEmptyLocaleList()
    {
        return sEmptyLocaleList;
    }

    private static String getLikelyScript(Locale locale)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            locale = locale.getScript();
            if(!locale.isEmpty())
                return locale;
            else
                return "";
        } else
        {
            return "";
        }
    }

    private static boolean isPseudoLocale(String s)
    {
        return "en-XA".equals(s) || "ar-XB".equals(s);
    }

    private static boolean isPseudoLocale(Locale locale)
    {
        return LOCALE_EN_XA.equals(locale) || LOCALE_AR_XB.equals(locale);
    }

    static boolean isPseudoLocalesOnly(String as[])
    {
        if(as != null)
        {
            if(as.length > 3)
                return false;
            int j = as.length;
            int i = 0;
            while(i < j) 
            {
                String s = as[i];
                if(!s.isEmpty() && !isPseudoLocale(s))
                    return false;
                i++;
            }
        }
        return true;
    }

    private static int matchScore(Locale locale, Locale locale1)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = false;
        if(!locale.equals(locale1)) goto _L2; else goto _L1
_L1:
        int i = 1;
_L4:
        return i;
_L2:
        i = ((flag1) ? 1 : 0);
        if(!locale.getLanguage().equals(locale1.getLanguage())) goto _L4; else goto _L3
_L3:
        i = ((flag1) ? 1 : 0);
        if(isPseudoLocale(locale)) goto _L4; else goto _L5
_L5:
        i = ((flag1) ? 1 : 0);
        if(isPseudoLocale(locale1)) goto _L4; else goto _L6
_L6:
        String s;
        s = getLikelyScript(locale);
        if(!s.isEmpty())
            break MISSING_BLOCK_LABEL_96;
        locale = locale.getCountry();
        if(locale.isEmpty())
            break; /* Loop/switch isn't completed */
        i = ((flag1) ? 1 : 0);
        if(!locale.equals(locale1.getCountry())) goto _L4; else goto _L7
_L7:
        return 1;
        int j;
        if(s.equals(getLikelyScript(locale1)))
            j = ((flag) ? 1 : 0);
        else
            j = 0;
        return j;
    }

    static void setDefault(LocaleListHelper localelisthelper)
    {
        setDefault(localelisthelper, 0);
    }

    static void setDefault(LocaleListHelper localelisthelper, int i)
    {
        if(localelisthelper == null)
            throw new NullPointerException("locales is null");
        if(localelisthelper.isEmpty())
            throw new IllegalArgumentException("locales is empty");
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        sLastDefaultLocale = localelisthelper.get(i);
        Locale.setDefault(sLastDefaultLocale);
        sLastExplicitlySetLocaleList = localelisthelper;
        sDefaultLocaleList = localelisthelper;
        if(i != 0)
            break MISSING_BLOCK_LABEL_74;
        sDefaultAdjustedLocaleList = sDefaultLocaleList;
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        sDefaultAdjustedLocaleList = new LocaleListHelper(sLastDefaultLocale, sDefaultLocaleList);
        if(true) goto _L2; else goto _L1
_L1:
        localelisthelper;
        obj;
        JVM INSTR monitorexit ;
        throw localelisthelper;
    }

    public boolean equals(Object obj)
    {
        if(obj != this)
        {
            if(!(obj instanceof LocaleListHelper))
                return false;
            obj = ((LocaleListHelper)obj).mList;
            if(mList.length != obj.length)
                return false;
            int i = 0;
            while(i < mList.length) 
            {
                if(!mList[i].equals(obj[i]))
                    return false;
                i++;
            }
        }
        return true;
    }

    Locale get(int i)
    {
        if(i >= 0 && i < mList.length)
            return mList[i];
        else
            return null;
    }

    Locale getFirstMatch(String as[])
    {
        return computeFirstMatch(Arrays.asList(as), false);
    }

    int getFirstMatchIndex(String as[])
    {
        return computeFirstMatchIndex(Arrays.asList(as), false);
    }

    int getFirstMatchIndexWithEnglishSupported(Collection collection)
    {
        return computeFirstMatchIndex(collection, true);
    }

    int getFirstMatchIndexWithEnglishSupported(String as[])
    {
        return getFirstMatchIndexWithEnglishSupported(((Collection) (Arrays.asList(as))));
    }

    Locale getFirstMatchWithEnglishSupported(String as[])
    {
        return computeFirstMatch(Arrays.asList(as), true);
    }

    public int hashCode()
    {
        int j = 1;
        for(int i = 0; i < mList.length; i++)
            j = j * 31 + mList[i].hashCode();

        return j;
    }

    int indexOf(Locale locale)
    {
        for(int i = 0; i < mList.length; i++)
            if(mList[i].equals(locale))
                return i;

        return -1;
    }

    boolean isEmpty()
    {
        return mList.length == 0;
    }

    int size()
    {
        return mList.length;
    }

    String toLanguageTags()
    {
        return mStringRepresentation;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        for(int i = 0; i < mList.length; i++)
        {
            stringbuilder.append(mList[i]);
            if(i < mList.length - 1)
                stringbuilder.append(',');
        }

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    private static final Locale EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
    private static final Locale LOCALE_AR_XB = new Locale("ar", "XB");
    private static final Locale LOCALE_EN_XA = new Locale("en", "XA");
    private static final int NUM_PSEUDO_LOCALES = 2;
    private static final String STRING_AR_XB = "ar-XB";
    private static final String STRING_EN_XA = "en-XA";
    private static LocaleListHelper sDefaultAdjustedLocaleList = null;
    private static LocaleListHelper sDefaultLocaleList = null;
    private static final Locale sEmptyList[] = new Locale[0];
    private static final LocaleListHelper sEmptyLocaleList = new LocaleListHelper(new Locale[0]);
    private static Locale sLastDefaultLocale = null;
    private static LocaleListHelper sLastExplicitlySetLocaleList = null;
    private static final Object sLock = new Object();
    private final Locale mList[];
    private final String mStringRepresentation;

}
