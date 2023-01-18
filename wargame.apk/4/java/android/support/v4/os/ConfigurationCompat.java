// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.content.res.Configuration;
import java.util.Locale;

// Referenced classes of package android.support.v4.os:
//            LocaleListCompat

public final class ConfigurationCompat
{

    private ConfigurationCompat()
    {
    }

    public static LocaleListCompat getLocales(Configuration configuration)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return LocaleListCompat.wrap(configuration.getLocales());
        else
            return LocaleListCompat.create(new Locale[] {
                configuration.locale
            });
    }
}
