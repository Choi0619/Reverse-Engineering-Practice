// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.BundleCompat;

public class NotificationCompat extends android.support.v4.app.NotificationCompat
{
    public static class Builder extends android.support.v4.app.Builder
    {

        public Builder(Context context)
        {
            super(context);
        }
    }

    public static class DecoratedCustomViewStyle extends android.support.v4.app.DecoratedCustomViewStyle
    {

        public DecoratedCustomViewStyle()
        {
        }
    }

    public static class DecoratedMediaCustomViewStyle extends android.support.v4.media.app.DecoratedMediaCustomViewStyle
    {

        public DecoratedMediaCustomViewStyle()
        {
        }
    }

    public static class MediaStyle extends android.support.v4.media.app.MediaStyle
    {

        public volatile android.support.v4.media.app.MediaStyle setCancelButtonIntent(PendingIntent pendingintent)
        {
            return setCancelButtonIntent(pendingintent);
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingintent)
        {
            return (MediaStyle)super.setCancelButtonIntent(pendingintent);
        }

        public volatile android.support.v4.media.app.MediaStyle setMediaSession(android.support.v4.media.session.MediaSessionCompat.Token token)
        {
            return setMediaSession(token);
        }

        public MediaStyle setMediaSession(android.support.v4.media.session.MediaSessionCompat.Token token)
        {
            return (MediaStyle)super.setMediaSession(token);
        }

        public volatile android.support.v4.media.app.MediaStyle setShowActionsInCompactView(int ai[])
        {
            return setShowActionsInCompactView(ai);
        }

        public transient MediaStyle setShowActionsInCompactView(int ai[])
        {
            return (MediaStyle)super.setShowActionsInCompactView(ai);
        }

        public volatile android.support.v4.media.app.MediaStyle setShowCancelButton(boolean flag)
        {
            return setShowCancelButton(flag);
        }

        public MediaStyle setShowCancelButton(boolean flag)
        {
            return (MediaStyle)super.setShowCancelButton(flag);
        }

        public MediaStyle()
        {
        }

        public MediaStyle(android.support.v4.app.Builder builder)
        {
            super(builder);
        }
    }


    public NotificationCompat()
    {
    }

    public static android.support.v4.media.session.MediaSessionCompat.Token getMediaSession(Notification notification)
    {
        notification = getExtras(notification);
        if(notification != null)
            if(android.os.Build.VERSION.SDK_INT >= 21)
            {
                notification = notification.getParcelable("android.mediaSession");
                if(notification != null)
                    return android.support.v4.media.session.MediaSessionCompat.Token.fromToken(notification);
            } else
            {
                Object obj = BundleCompat.getBinder(notification, "android.mediaSession");
                if(obj != null)
                {
                    notification = Parcel.obtain();
                    notification.writeStrongBinder(((android.os.IBinder) (obj)));
                    notification.setDataPosition(0);
                    obj = (android.support.v4.media.session.MediaSessionCompat.Token)android.support.v4.media.session.MediaSessionCompat.Token.CREATOR.createFromParcel(notification);
                    notification.recycle();
                    return ((android.support.v4.media.session.MediaSessionCompat.Token) (obj));
                }
            }
        return null;
    }
}
