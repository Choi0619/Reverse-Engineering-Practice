// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public final class NavUtils
{

    private NavUtils()
    {
    }

    public static Intent getParentActivityIntent(Activity activity)
    {
        String s;
        ComponentName componentname;
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            Intent intent = activity.getParentActivityIntent();
            if(intent != null)
                return intent;
        }
        s = getParentActivityName(activity);
        if(s == null)
            return null;
        componentname = new ComponentName(activity, s);
label0:
        {
            if(getParentActivityName(activity, componentname) == null)
            {
                activity = Intent.makeMainActivity(componentname);
                break label0;
            }
            try
            {
                activity = (new Intent()).setComponent(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(Activity activity)
            {
                Log.e("NavUtils", (new StringBuilder()).append("getParentActivityIntent: bad parentActivityName '").append(s).append("' in manifest").toString());
                return null;
            }
        }
        return activity;
    }

    public static Intent getParentActivityIntent(Context context, ComponentName componentname)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        String s = getParentActivityName(context, componentname);
        if(s == null)
            return null;
        componentname = new ComponentName(componentname.getPackageName(), s);
        if(getParentActivityName(context, componentname) == null)
            return Intent.makeMainActivity(componentname);
        else
            return (new Intent()).setComponent(componentname);
    }

    public static Intent getParentActivityIntent(Context context, Class class1)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        class1 = getParentActivityName(context, new ComponentName(context, class1));
        if(class1 == null)
            return null;
        class1 = new ComponentName(context, class1);
        if(getParentActivityName(context, class1) == null)
            return Intent.makeMainActivity(class1);
        else
            return (new Intent()).setComponent(class1);
    }

    public static String getParentActivityName(Activity activity)
    {
        try
        {
            activity = getParentActivityName(((Context) (activity)), activity.getComponentName());
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            throw new IllegalArgumentException(activity);
        }
        return activity;
    }

    public static String getParentActivityName(Context context, ComponentName componentname)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        componentname = context.getPackageManager().getActivityInfo(componentname, 128);
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            String s = ((ActivityInfo) (componentname)).parentActivityName;
            if(s != null)
                return s;
        }
        if(((ActivityInfo) (componentname)).metaData == null)
            return null;
        String s1 = ((ActivityInfo) (componentname)).metaData.getString("android.support.PARENT_ACTIVITY");
        if(s1 == null)
            return null;
        componentname = s1;
        if(s1.charAt(0) == '.')
            componentname = (new StringBuilder()).append(context.getPackageName()).append(s1).toString();
        return componentname;
    }

    public static void navigateUpFromSameTask(Activity activity)
    {
        Intent intent = getParentActivityIntent(activity);
        if(intent == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Activity ").append(activity.getClass().getSimpleName()).append(" does not have a parent activity name specified.").append(" (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> ").append(" element in your manifest?)").toString());
        } else
        {
            navigateUpTo(activity, intent);
            return;
        }
    }

    public static void navigateUpTo(Activity activity, Intent intent)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            activity.navigateUpTo(intent);
            return;
        } else
        {
            intent.addFlags(0x4000000);
            activity.startActivity(intent);
            activity.finish();
            return;
        }
    }

    public static boolean shouldUpRecreateTask(Activity activity, Intent intent)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return activity.shouldUpRecreateTask(intent);
        activity = activity.getIntent().getAction();
        return activity != null && !activity.equals("android.intent.action.MAIN");
    }

    public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
    private static final String TAG = "NavUtils";
}
