// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.*;
import android.content.pm.*;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;
import android.view.KeyEvent;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            PlaybackStateCompat, MediaSessionCompat, MediaControllerCompat

public class MediaButtonReceiver extends BroadcastReceiver
{
    private static class MediaButtonConnectionCallback extends android.support.v4.media.MediaBrowserCompat.ConnectionCallback
    {

        private void finish()
        {
            mMediaBrowser.disconnect();
            mPendingResult.finish();
        }

        public void onConnected()
        {
            try
            {
                (new MediaControllerCompat(mContext, mMediaBrowser.getSessionToken())).dispatchMediaButtonEvent((KeyEvent)mIntent.getParcelableExtra("android.intent.extra.KEY_EVENT"));
            }
            catch(RemoteException remoteexception)
            {
                Log.e("MediaButtonReceiver", "Failed to create a media controller", remoteexception);
            }
            finish();
        }

        public void onConnectionFailed()
        {
            finish();
        }

        public void onConnectionSuspended()
        {
            finish();
        }

        void setMediaBrowser(MediaBrowserCompat mediabrowsercompat)
        {
            mMediaBrowser = mediabrowsercompat;
        }

        private final Context mContext;
        private final Intent mIntent;
        private MediaBrowserCompat mMediaBrowser;
        private final android.content.BroadcastReceiver.PendingResult mPendingResult;

        MediaButtonConnectionCallback(Context context, Intent intent, android.content.BroadcastReceiver.PendingResult pendingresult)
        {
            mContext = context;
            mIntent = intent;
            mPendingResult = pendingresult;
        }
    }


    public MediaButtonReceiver()
    {
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context context, long l)
    {
        ComponentName componentname = getMediaButtonReceiverComponent(context);
        if(componentname == null)
        {
            Log.w("MediaButtonReceiver", "A unique media button receiver could not be found in the given context, so couldn't build a pending intent.");
            return null;
        } else
        {
            return buildMediaButtonPendingIntent(context, componentname, l);
        }
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context context, ComponentName componentname, long l)
    {
        if(componentname == null)
        {
            Log.w("MediaButtonReceiver", "The component name of media button receiver should be provided.");
            return null;
        }
        int i = PlaybackStateCompat.toKeyCode(l);
        if(i == 0)
        {
            Log.w("MediaButtonReceiver", (new StringBuilder()).append("Cannot build a media button pending intent with the given action: ").append(l).toString());
            return null;
        } else
        {
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(componentname);
            intent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, i));
            return PendingIntent.getBroadcast(context, i, intent, 0);
        }
    }

    static ComponentName getMediaButtonReceiverComponent(Context context)
    {
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(context.getPackageName());
        context = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if(context.size() == 1)
        {
            context = (ResolveInfo)context.get(0);
            return new ComponentName(((ResolveInfo) (context)).activityInfo.packageName, ((ResolveInfo) (context)).activityInfo.name);
        }
        if(context.size() > 1)
            Log.w("MediaButtonReceiver", "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
        return null;
    }

    private static ComponentName getServiceComponentByAction(Context context, String s)
    {
        PackageManager packagemanager = context.getPackageManager();
        Intent intent = new Intent(s);
        intent.setPackage(context.getPackageName());
        context = packagemanager.queryIntentServices(intent, 0);
        if(context.size() == 1)
        {
            context = (ResolveInfo)context.get(0);
            return new ComponentName(((ResolveInfo) (context)).serviceInfo.packageName, ((ResolveInfo) (context)).serviceInfo.name);
        }
        if(context.isEmpty())
            return null;
        else
            throw new IllegalStateException((new StringBuilder()).append("Expected 1 service that handles ").append(s).append(", found ").append(context.size()).toString());
    }

    public static KeyEvent handleIntent(MediaSessionCompat mediasessioncompat, Intent intent)
    {
        if(mediasessioncompat == null || intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT"))
        {
            return null;
        } else
        {
            intent = (KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            mediasessioncompat.getController().dispatchMediaButtonEvent(intent);
            return intent;
        }
    }

    private static void startForegroundService(Context context, Intent intent)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
        {
            context.startForegroundService(intent);
            return;
        } else
        {
            context.startService(intent);
            return;
        }
    }

    public void onReceive(Context context, Intent intent)
    {
        if(intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT"))
        {
            Log.d("MediaButtonReceiver", (new StringBuilder()).append("Ignore unsupported intent: ").append(intent).toString());
            return;
        }
        ComponentName componentname = getServiceComponentByAction(context, "android.intent.action.MEDIA_BUTTON");
        if(componentname != null)
        {
            intent.setComponent(componentname);
            startForegroundService(context, intent);
            return;
        }
        componentname = getServiceComponentByAction(context, "android.media.browse.MediaBrowserService");
        if(componentname != null)
        {
            android.content.BroadcastReceiver.PendingResult pendingresult = goAsync();
            context = context.getApplicationContext();
            intent = new MediaButtonConnectionCallback(context, intent, pendingresult);
            context = new MediaBrowserCompat(context, componentname, intent, null);
            intent.setMediaBrowser(context);
            context.connect();
            return;
        } else
        {
            throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
        }
    }

    private static final String TAG = "MediaButtonReceiver";
}
