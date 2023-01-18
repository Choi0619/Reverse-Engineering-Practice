// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import java.util.Locale;

final class LocaleHelper
{

    LocaleHelper()
    {
    }

    static Locale forLanguageTag(String s)
    {
        if(s.contains("-"))
        {
            String as[] = s.split("-");
            if(as.length > 2)
                return new Locale(as[0], as[1], as[2]);
            if(as.length > 1)
                return new Locale(as[0], as[1]);
            if(as.length == 1)
                return new Locale(as[0]);
        } else
        if(s.contains("_"))
        {
            String as1[] = s.split("_");
            if(as1.length > 2)
                return new Locale(as1[0], as1[1], as1[2]);
            if(as1.length > 1)
                return new Locale(as1[0], as1[1]);
            if(as1.length == 1)
                return new Locale(as1[0]);
        } else
        {
            return new Locale(s);
        }
        throw new IllegalArgumentException((new StringBuilder()).append("Can not parse language tag: [").append(s).append("]").toString());
    }

    static String toLanguageTag(Locale locale)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(locale.getLanguage());
        String s = locale.getCountry();
        if(s != null && !s.isEmpty())
        {
            stringbuilder.append("-");
            stringbuilder.append(locale.getCountry());
        }
        return stringbuilder.toString();
    }
}
