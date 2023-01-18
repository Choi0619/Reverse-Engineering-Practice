// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.support.v4.app:
//            SharedElementCallback

public class ActivityCompat extends ContextCompat
{
    public static interface OnRequestPermissionsResultCallback
    {

        public abstract void onRequestPermissionsResult(int i, String as[], int ai[]);
    }

    public static interface RequestPermissionsRequestCodeValidator
    {

        public abstract void validateRequestPermissionsRequestCode(int i);
    }

    private static class SharedElementCallback21Impl extends SharedElementCallback
    {

        public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectf)
        {
            return mCallback.onCaptureSharedElementSnapshot(view, matrix, rectf);
        }

        public View onCreateSnapshotView(Context context, Parcelable parcelable)
        {
            return mCallback.onCreateSnapshotView(context, parcelable);
        }

        public void onMapSharedElements(List list, Map map)
        {
            mCallback.onMapSharedElements(list, map);
        }

        public void onRejectSharedElements(List list)
        {
            mCallback.onRejectSharedElements(list);
        }

        public void onSharedElementEnd(List list, List list1, List list2)
        {
            mCallback.onSharedElementEnd(list, list1, list2);
        }

        public void onSharedElementStart(List list, List list1, List list2)
        {
            mCallback.onSharedElementStart(list, list1, list2);
        }

        protected android.support.v4.app.SharedElementCallback mCallback;

        public SharedElementCallback21Impl(android.support.v4.app.SharedElementCallback sharedelementcallback)
        {
            mCallback = sharedelementcallback;
        }
    }

    private static class SharedElementCallback23Impl extends SharedElementCallback21Impl
    {

        public void onSharedElementsArrived(List list, List list1, android.app.SharedElementCallback.OnSharedElementsReadyListener onsharedelementsreadylistener)
        {
            mCallback.onSharedElementsArrived(list, list1, onsharedelementsreadylistener. new SharedElementCallback.OnSharedElementsReadyListener() {

                public void onSharedElementsReady()
                {
                    listener.onSharedElementsReady();
                }

                final SharedElementCallback23Impl this$0;
                final android.app.SharedElementCallback.OnSharedElementsReadyListener val$listener;

            
            {
                this$0 = final_sharedelementcallback23impl;
                listener = android.app.SharedElementCallback.OnSharedElementsReadyListener.this;
                super();
            }
            }
);
        }

        public SharedElementCallback23Impl(android.support.v4.app.SharedElementCallback sharedelementcallback)
        {
            super(sharedelementcallback);
        }
    }


    protected ActivityCompat()
    {
    }

    public static void finishAffinity(Activity activity)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            activity.finishAffinity();
            return;
        } else
        {
            activity.finish();
            return;
        }
    }

    public static void finishAfterTransition(Activity activity)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            activity.finishAfterTransition();
            return;
        } else
        {
            activity.finish();
            return;
        }
    }

    public static Uri getReferrer(Activity activity)
    {
        if(android.os.Build.VERSION.SDK_INT >= 22)
        {
            activity = activity.getReferrer();
        } else
        {
            Intent intent = activity.getIntent();
            Uri uri = (Uri)intent.getParcelableExtra("android.intent.extra.REFERRER");
            activity = uri;
            if(uri == null)
            {
                activity = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
                if(activity != null)
                    return Uri.parse(activity);
                else
                    return null;
            }
        }
        return activity;
    }

    public static boolean invalidateOptionsMenu(Activity activity)
    {
        activity.invalidateOptionsMenu();
        return true;
    }

    public static void postponeEnterTransition(Activity activity)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            activity.postponeEnterTransition();
    }

    public static void requestPermissions(Activity activity, String as[], int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            if(activity instanceof RequestPermissionsRequestCodeValidator)
                ((RequestPermissionsRequestCodeValidator)activity).validateRequestPermissionsRequestCode(i);
            activity.requestPermissions(as, i);
        } else
        if(activity instanceof OnRequestPermissionsResultCallback)
        {
            (new Handler(Looper.getMainLooper())).post(new Runnable(as, activity, i) {

                public void run()
                {
                    int ai[] = new int[permissions.length];
                    PackageManager packagemanager = activity.getPackageManager();
                    String s = activity.getPackageName();
                    int k = permissions.length;
                    for(int j = 0; j < k; j++)
                        ai[j] = packagemanager.checkPermission(permissions[j], s);

                    ((OnRequestPermissionsResultCallback)activity).onRequestPermissionsResult(requestCode, permissions, ai);
                }

                final Activity val$activity;
                final String val$permissions[];
                final int val$requestCode;

            
            {
                permissions = as;
                activity = activity1;
                requestCode = i;
                super();
            }
            }
);
            return;
        }
    }

    public static void setEnterSharedElementCallback(Activity activity, android.support.v4.app.SharedElementCallback sharedelementcallback)
    {
        Object obj = null;
        SharedElementCallback23Impl sharedelementcallback23impl = null;
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            if(sharedelementcallback != null)
                sharedelementcallback23impl = new SharedElementCallback23Impl(sharedelementcallback);
            activity.setEnterSharedElementCallback(sharedelementcallback23impl);
        } else
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            SharedElementCallback21Impl sharedelementcallback21impl = obj;
            if(sharedelementcallback != null)
                sharedelementcallback21impl = new SharedElementCallback21Impl(sharedelementcallback);
            activity.setEnterSharedElementCallback(sharedelementcallback21impl);
            return;
        }
    }

    public static void setExitSharedElementCallback(Activity activity, android.support.v4.app.SharedElementCallback sharedelementcallback)
    {
        Object obj = null;
        SharedElementCallback23Impl sharedelementcallback23impl = null;
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            if(sharedelementcallback != null)
                sharedelementcallback23impl = new SharedElementCallback23Impl(sharedelementcallback);
            activity.setExitSharedElementCallback(sharedelementcallback23impl);
        } else
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            SharedElementCallback21Impl sharedelementcallback21impl = obj;
            if(sharedelementcallback != null)
                sharedelementcallback21impl = new SharedElementCallback21Impl(sharedelementcallback);
            activity.setExitSharedElementCallback(sharedelementcallback21impl);
            return;
        }
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String s)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return activity.shouldShowRequestPermissionRationale(s);
        else
            return false;
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, Bundle bundle)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            activity.startActivityForResult(intent, i, bundle);
            return;
        } else
        {
            activity.startActivityForResult(intent, i);
            return;
        }
    }

    public static void startIntentSenderForResult(Activity activity, IntentSender intentsender, int i, Intent intent, int j, int k, int l, Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            activity.startIntentSenderForResult(intentsender, i, intent, j, k, l, bundle);
            return;
        } else
        {
            activity.startIntentSenderForResult(intentsender, i, intent, j, k, l);
            return;
        }
    }

    public static void startPostponedEnterTransition(Activity activity)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            activity.startPostponedEnterTransition();
    }
}
