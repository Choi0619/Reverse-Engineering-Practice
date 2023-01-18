// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.pm;

import android.content.*;
import android.content.pm.*;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.content.pm:
//            ShortcutInfoCompat

public class ShortcutManagerCompat
{

    private ShortcutManagerCompat()
    {
    }

    public static Intent createShortcutResultIntent(Context context, ShortcutInfoCompat shortcutinfocompat)
    {
        Intent intent = null;
        if(android.os.Build.VERSION.SDK_INT >= 26)
            intent = ((ShortcutManager)context.getSystemService(android/content/pm/ShortcutManager)).createShortcutResultIntent(shortcutinfocompat.toShortcutInfo());
        context = intent;
        if(intent == null)
            context = new Intent();
        return shortcutinfocompat.addToIntent(context);
    }

    public static boolean isRequestPinShortcutSupported(Context context)
    {
        boolean flag1 = false;
        if(android.os.Build.VERSION.SDK_INT < 26) goto _L2; else goto _L1
_L1:
        boolean flag = ((ShortcutManager)context.getSystemService(android/content/pm/ShortcutManager)).isRequestPinShortcutSupported();
_L4:
        return flag;
_L2:
        flag = flag1;
        if(ContextCompat.checkSelfPermission(context, "com.android.launcher.permission.INSTALL_SHORTCUT") != 0)
            continue; /* Loop/switch isn't completed */
        context = context.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 0).iterator();
        do
        {
            flag = flag1;
            if(!context.hasNext())
                continue; /* Loop/switch isn't completed */
            String s = ((ResolveInfo)context.next()).activityInfo.permission;
            if(TextUtils.isEmpty(s) || "com.android.launcher.permission.INSTALL_SHORTCUT".equals(s))
                return true;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean requestPinShortcut(Context context, ShortcutInfoCompat shortcutinfocompat, IntentSender intentsender)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return ((ShortcutManager)context.getSystemService(android/content/pm/ShortcutManager)).requestPinShortcut(shortcutinfocompat.toShortcutInfo(), intentsender);
        if(!isRequestPinShortcutSupported(context))
            return false;
        shortcutinfocompat = shortcutinfocompat.addToIntent(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"));
        if(intentsender == null)
        {
            context.sendBroadcast(shortcutinfocompat);
            return true;
        } else
        {
            context.sendOrderedBroadcast(shortcutinfocompat, null, new BroadcastReceiver(intentsender) {

                public void onReceive(Context context1, Intent intent)
                {
                    try
                    {
                        callback.sendIntent(context1, 0, null, null, null);
                        return;
                    }
                    // Misplaced declaration of an exception variable
                    catch(Context context1)
                    {
                        return;
                    }
                }

                final IntentSender val$callback;

            
            {
                callback = intentsender;
                super();
            }
            }
, null, -1, null, null);
            return true;
        }
    }

    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";
}
