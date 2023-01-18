// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.text;

import java.util.Locale;

// Referenced classes of package android.support.v4.text:
//            ICUCompatApi21, ICUCompatIcs

public final class ICUCompat
{
    static class ICUCompatApi21Impl extends ICUCompatBaseImpl
    {

        public String maximizeAndGetScript(Locale locale)
        {
            return ICUCompatApi21.maximizeAndGetScript(locale);
        }

        ICUCompatApi21Impl()
        {
        }
    }

    static class ICUCompatBaseImpl
    {

        public String maximizeAndGetScript(Locale locale)
        {
            return ICUCompatIcs.maximizeAndGetScript(locale);
        }

        ICUCompatBaseImpl()
        {
        }
    }


    private ICUCompat()
    {
    }

    public static String maximizeAndGetScript(Locale locale)
    {
        return IMPL.maximizeAndGetScript(locale);
    }

    private static final ICUCompatBaseImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            IMPL = new ICUCompatApi21Impl();
        else
            IMPL = new ICUCompatBaseImpl();
    }
}
