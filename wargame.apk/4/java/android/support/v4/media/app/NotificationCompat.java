// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.widget.RemoteViews;
import java.util.ArrayList;

public class NotificationCompat
{
    public static class DecoratedMediaCustomViewStyle extends MediaStyle
    {

        private void setBackgroundColor(RemoteViews remoteviews)
        {
            int i;
            if(mBuilder.getColor() != 0)
                i = mBuilder.getColor();
            else
                i = mBuilder.mContext.getResources().getColor(android.support.mediacompat.R.color.notification_material_background_media_default_color);
            remoteviews.setInt(android.support.mediacompat.R.id.status_bar_latest_event_content, "setBackgroundColor", i);
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT >= 24)
            {
                notificationbuilderwithbuilderaccessor.getBuilder().setStyle(fillInMediaStyle(new android.app.Notification.DecoratedMediaCustomViewStyle()));
                return;
            } else
            {
                super.apply(notificationbuilderwithbuilderaccessor);
                return;
            }
        }

        int getBigContentViewLayoutResource(int i)
        {
            if(i <= 3)
                return android.support.mediacompat.R.layout.notification_template_big_media_narrow_custom;
            else
                return android.support.mediacompat.R.layout.notification_template_big_media_custom;
        }

        int getContentViewLayoutResource()
        {
            if(mBuilder.getContentView() != null)
                return android.support.mediacompat.R.layout.notification_template_media_custom;
            else
                return super.getContentViewLayoutResource();
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            RemoteViews remoteviews = null;
            if(android.os.Build.VERSION.SDK_INT < 24)
            {
                if(mBuilder.getBigContentView() != null)
                    notificationbuilderwithbuilderaccessor = mBuilder.getBigContentView();
                else
                    notificationbuilderwithbuilderaccessor = mBuilder.getContentView();
                if(notificationbuilderwithbuilderaccessor != null)
                {
                    RemoteViews remoteviews1 = generateBigContentView();
                    buildIntoRemoteViews(remoteviews1, notificationbuilderwithbuilderaccessor);
                    remoteviews = remoteviews1;
                    if(android.os.Build.VERSION.SDK_INT >= 21)
                    {
                        setBackgroundColor(remoteviews1);
                        return remoteviews1;
                    }
                }
            }
            return remoteviews;
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT >= 24)
                return null;
            boolean flag;
            if(mBuilder.getContentView() != null)
                flag = true;
            else
                flag = false;
            if(android.os.Build.VERSION.SDK_INT >= 21)
            {
                boolean flag1;
                if(flag || mBuilder.getBigContentView() != null)
                    flag1 = true;
                else
                    flag1 = false;
                if(flag1)
                {
                    notificationbuilderwithbuilderaccessor = generateContentView();
                    if(flag)
                        buildIntoRemoteViews(notificationbuilderwithbuilderaccessor, mBuilder.getContentView());
                    setBackgroundColor(notificationbuilderwithbuilderaccessor);
                    return notificationbuilderwithbuilderaccessor;
                }
            } else
            {
                notificationbuilderwithbuilderaccessor = generateContentView();
                if(flag)
                {
                    buildIntoRemoteViews(notificationbuilderwithbuilderaccessor, mBuilder.getContentView());
                    return notificationbuilderwithbuilderaccessor;
                }
            }
            return null;
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            RemoteViews remoteviews = null;
            if(android.os.Build.VERSION.SDK_INT < 24)
            {
                if(mBuilder.getHeadsUpContentView() != null)
                    notificationbuilderwithbuilderaccessor = mBuilder.getHeadsUpContentView();
                else
                    notificationbuilderwithbuilderaccessor = mBuilder.getContentView();
                if(notificationbuilderwithbuilderaccessor != null)
                {
                    RemoteViews remoteviews1 = generateBigContentView();
                    buildIntoRemoteViews(remoteviews1, notificationbuilderwithbuilderaccessor);
                    remoteviews = remoteviews1;
                    if(android.os.Build.VERSION.SDK_INT >= 21)
                    {
                        setBackgroundColor(remoteviews1);
                        return remoteviews1;
                    }
                }
            }
            return remoteviews;
        }

        public DecoratedMediaCustomViewStyle()
        {
        }
    }

    public static class MediaStyle extends android.support.v4.app.Style
    {

        private RemoteViews generateMediaActionButton(android.support.v4.app.Action action)
        {
            boolean flag;
            RemoteViews remoteviews;
            if(action.getActionIntent() == null)
                flag = true;
            else
                flag = false;
            remoteviews = new RemoteViews(mBuilder.mContext.getPackageName(), android.support.mediacompat.R.layout.notification_media_action);
            remoteviews.setImageViewResource(android.support.mediacompat.R.id.action0, action.getIcon());
            if(!flag)
                remoteviews.setOnClickPendingIntent(android.support.mediacompat.R.id.action0, action.getActionIntent());
            if(android.os.Build.VERSION.SDK_INT >= 15)
                remoteviews.setContentDescription(android.support.mediacompat.R.id.action0, action.getTitle());
            return remoteviews;
        }

        public static android.support.v4.media.session.MediaSessionCompat.Token getMediaSession(Notification notification)
        {
            notification = android.support.v4.app.NotificationCompat.getExtras(notification);
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

        public void apply(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT >= 21)
                notificationbuilderwithbuilderaccessor.getBuilder().setStyle(fillInMediaStyle(new android.app.Notification.MediaStyle()));
            else
            if(mShowCancelButton)
            {
                notificationbuilderwithbuilderaccessor.getBuilder().setOngoing(true);
                return;
            }
        }

        android.app.Notification.MediaStyle fillInMediaStyle(android.app.Notification.MediaStyle mediastyle)
        {
            if(mActionsToShowInCompact != null)
                mediastyle.setShowActionsInCompactView(mActionsToShowInCompact);
            if(mToken != null)
                mediastyle.setMediaSession((android.media.session.MediaSession.Token)mToken.getToken());
            return mediastyle;
        }

        RemoteViews generateBigContentView()
        {
            int j = Math.min(mBuilder.mActions.size(), 5);
            RemoteViews remoteviews = applyStandardTemplate(false, getBigContentViewLayoutResource(j), false);
            remoteviews.removeAllViews(android.support.mediacompat.R.id.media_actions);
            if(j > 0)
            {
                for(int i = 0; i < j; i++)
                {
                    RemoteViews remoteviews1 = generateMediaActionButton((android.support.v4.app.Action)mBuilder.mActions.get(i));
                    remoteviews.addView(android.support.mediacompat.R.id.media_actions, remoteviews1);
                }

            }
            if(mShowCancelButton)
            {
                remoteviews.setViewVisibility(android.support.mediacompat.R.id.cancel_action, 0);
                remoteviews.setInt(android.support.mediacompat.R.id.cancel_action, "setAlpha", mBuilder.mContext.getResources().getInteger(android.support.mediacompat.R.integer.cancel_button_image_alpha));
                remoteviews.setOnClickPendingIntent(android.support.mediacompat.R.id.cancel_action, mCancelButtonIntent);
                return remoteviews;
            } else
            {
                remoteviews.setViewVisibility(android.support.mediacompat.R.id.cancel_action, 8);
                return remoteviews;
            }
        }

        RemoteViews generateContentView()
        {
            RemoteViews remoteviews = applyStandardTemplate(false, getContentViewLayoutResource(), true);
            int k = mBuilder.mActions.size();
            int i;
            if(mActionsToShowInCompact == null)
                i = 0;
            else
                i = Math.min(mActionsToShowInCompact.length, 3);
            remoteviews.removeAllViews(android.support.mediacompat.R.id.media_actions);
            if(i > 0)
            {
                for(int j = 0; j < i; j++)
                {
                    if(j >= k)
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[] {
                            Integer.valueOf(j), Integer.valueOf(k - 1)
                        }));
                    RemoteViews remoteviews1 = generateMediaActionButton((android.support.v4.app.Action)mBuilder.mActions.get(mActionsToShowInCompact[j]));
                    remoteviews.addView(android.support.mediacompat.R.id.media_actions, remoteviews1);
                }

            }
            if(mShowCancelButton)
            {
                remoteviews.setViewVisibility(android.support.mediacompat.R.id.end_padder, 8);
                remoteviews.setViewVisibility(android.support.mediacompat.R.id.cancel_action, 0);
                remoteviews.setOnClickPendingIntent(android.support.mediacompat.R.id.cancel_action, mCancelButtonIntent);
                remoteviews.setInt(android.support.mediacompat.R.id.cancel_action, "setAlpha", mBuilder.mContext.getResources().getInteger(android.support.mediacompat.R.integer.cancel_button_image_alpha));
                return remoteviews;
            } else
            {
                remoteviews.setViewVisibility(android.support.mediacompat.R.id.end_padder, 0);
                remoteviews.setViewVisibility(android.support.mediacompat.R.id.cancel_action, 8);
                return remoteviews;
            }
        }

        int getBigContentViewLayoutResource(int i)
        {
            if(i <= 3)
                return android.support.mediacompat.R.layout.notification_template_big_media_narrow;
            else
                return android.support.mediacompat.R.layout.notification_template_big_media;
        }

        int getContentViewLayoutResource()
        {
            return android.support.mediacompat.R.layout.notification_template_media;
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT >= 21)
                return null;
            else
                return generateBigContentView();
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT >= 21)
                return null;
            else
                return generateContentView();
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingintent)
        {
            mCancelButtonIntent = pendingintent;
            return this;
        }

        public MediaStyle setMediaSession(android.support.v4.media.session.MediaSessionCompat.Token token)
        {
            mToken = token;
            return this;
        }

        public transient MediaStyle setShowActionsInCompactView(int ai[])
        {
            mActionsToShowInCompact = ai;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean flag)
        {
            if(android.os.Build.VERSION.SDK_INT < 21)
                mShowCancelButton = flag;
            return this;
        }

        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int mActionsToShowInCompact[];
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        android.support.v4.media.session.MediaSessionCompat.Token mToken;

        public MediaStyle()
        {
            mActionsToShowInCompact = null;
        }

        public MediaStyle(android.support.v4.app.Builder builder)
        {
            mActionsToShowInCompact = null;
            setBuilder(builder);
        }
    }


    private NotificationCompat()
    {
    }
}
