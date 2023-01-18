// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

public class ActivityOptionsCompat
{
    private static class ActivityOptionsCompatApi16Impl extends ActivityOptionsCompat
    {

        public Bundle toBundle()
        {
            return mActivityOptions.toBundle();
        }

        public void update(ActivityOptionsCompat activityoptionscompat)
        {
            if(activityoptionscompat instanceof ActivityOptionsCompatApi16Impl)
            {
                activityoptionscompat = (ActivityOptionsCompatApi16Impl)activityoptionscompat;
                mActivityOptions.update(((ActivityOptionsCompatApi16Impl) (activityoptionscompat)).mActivityOptions);
            }
        }

        protected final ActivityOptions mActivityOptions;

        ActivityOptionsCompatApi16Impl(ActivityOptions activityoptions)
        {
            mActivityOptions = activityoptions;
        }
    }

    private static class ActivityOptionsCompatApi23Impl extends ActivityOptionsCompatApi16Impl
    {

        public void requestUsageTimeReport(PendingIntent pendingintent)
        {
            mActivityOptions.requestUsageTimeReport(pendingintent);
        }

        ActivityOptionsCompatApi23Impl(ActivityOptions activityoptions)
        {
            super(activityoptions);
        }
    }

    private static class ActivityOptionsCompatApi24Impl extends ActivityOptionsCompatApi23Impl
    {

        public Rect getLaunchBounds()
        {
            return mActivityOptions.getLaunchBounds();
        }

        public ActivityOptionsCompat setLaunchBounds(Rect rect)
        {
            return new ActivityOptionsCompatApi24Impl(mActivityOptions.setLaunchBounds(rect));
        }

        ActivityOptionsCompatApi24Impl(ActivityOptions activityoptions)
        {
            super(activityoptions);
        }
    }


    protected ActivityOptionsCompat()
    {
    }

    private static ActivityOptionsCompat createImpl(ActivityOptions activityoptions)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return new ActivityOptionsCompatApi24Impl(activityoptions);
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return new ActivityOptionsCompatApi23Impl(activityoptions);
        else
            return new ActivityOptionsCompatApi16Impl(activityoptions);
    }

    public static ActivityOptionsCompat makeBasic()
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return createImpl(ActivityOptions.makeBasic());
        else
            return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeClipRevealAnimation(View view, int i, int j, int k, int l)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return createImpl(ActivityOptions.makeClipRevealAnimation(view, i, j, k, l));
        else
            return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeCustomAnimation(Context context, int i, int j)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return createImpl(ActivityOptions.makeCustomAnimation(context, i, j));
        else
            return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeScaleUpAnimation(View view, int i, int j, int k, int l)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return createImpl(ActivityOptions.makeScaleUpAnimation(view, i, j, k, l));
        else
            return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, View view, String s)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return createImpl(ActivityOptions.makeSceneTransitionAnimation(activity, view, s));
        else
            return new ActivityOptionsCompat();
    }

    public static transient ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, android.support.v4.util.Pair apair[])
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            Pair apair1[] = null;
            if(apair != null)
            {
                Pair apair2[] = new Pair[apair.length];
                int i = 0;
                do
                {
                    apair1 = apair2;
                    if(i >= apair.length)
                        break;
                    apair2[i] = Pair.create(apair[i].first, apair[i].second);
                    i++;
                } while(true);
            }
            return createImpl(ActivityOptions.makeSceneTransitionAnimation(activity, apair1));
        } else
        {
            return new ActivityOptionsCompat();
        }
    }

    public static ActivityOptionsCompat makeTaskLaunchBehind()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return createImpl(ActivityOptions.makeTaskLaunchBehind());
        else
            return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int i, int j)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return createImpl(ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, i, j));
        else
            return new ActivityOptionsCompat();
    }

    public Rect getLaunchBounds()
    {
        return null;
    }

    public void requestUsageTimeReport(PendingIntent pendingintent)
    {
    }

    public ActivityOptionsCompat setLaunchBounds(Rect rect)
    {
        return null;
    }

    public Bundle toBundle()
    {
        return null;
    }

    public void update(ActivityOptionsCompat activityoptionscompat)
    {
    }

    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";
}
