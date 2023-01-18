// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.text;

import android.text.TextUtils;
import java.util.Locale;

// Referenced classes of package android.support.v4.text:
//            ICUCompat

public final class TextUtilsCompat
{

    private TextUtilsCompat()
    {
    }

    private static int getLayoutDirectionFromFirstChar(Locale locale)
    {
        switch(Character.getDirectionality(locale.getDisplayName(locale).charAt(0)))
        {
        default:
            return 0;

        case 1: // '\001'
        case 2: // '\002'
            return 1;
        }
    }

    public static int getLayoutDirectionFromLocale(Locale locale)
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
            return TextUtils.getLayoutDirectionFromLocale(locale);
        if(locale != null && !locale.equals(ROOT))
        {
            String s = ICUCompat.maximizeAndGetScript(locale);
            if(s == null)
                return getLayoutDirectionFromFirstChar(locale);
            if(s.equalsIgnoreCase("Arab") || s.equalsIgnoreCase("Hebr"))
                return 1;
        }
        return 0;
    }

    public static String htmlEncode(String s)
    {
        int i;
        StringBuilder stringbuilder;
        if(android.os.Build.VERSION.SDK_INT >= 17)
            return TextUtils.htmlEncode(s);
        stringbuilder = new StringBuilder();
        i = 0;
_L8:
        char c;
        if(i >= s.length())
            break MISSING_BLOCK_LABEL_151;
        c = s.charAt(i);
        c;
        JVM INSTR lookupswitch 5: default 88
    //                   34: 141
    //                   38: 121
    //                   39: 131
    //                   60: 101
    //                   62: 111;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L2:
        break MISSING_BLOCK_LABEL_141;
_L5:
        break; /* Loop/switch isn't completed */
_L1:
        stringbuilder.append(c);
_L9:
        i++;
        if(true) goto _L8; else goto _L7
_L7:
        stringbuilder.append("&lt;");
          goto _L9
_L6:
        stringbuilder.append("&gt;");
          goto _L9
_L3:
        stringbuilder.append("&amp;");
          goto _L9
_L4:
        stringbuilder.append("&#39;");
          goto _L9
        stringbuilder.append("&quot;");
          goto _L9
        return stringbuilder.toString();
    }

    private static final String ARAB_SCRIPT_SUBTAG = "Arab";
    private static final String HEBR_SCRIPT_SUBTAG = "Hebr";
    public static final Locale ROOT = new Locale("", "");

}
