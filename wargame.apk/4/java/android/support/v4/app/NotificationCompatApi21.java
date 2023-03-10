// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.*;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.support.v4.app:
//            NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions, NotificationCompatApi20

class NotificationCompatApi21
{
    public static class Builder
        implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions
    {

        private void removeSoundAndVibration(Notification notification)
        {
            notification.sound = null;
            notification.vibrate = null;
            notification.defaults = notification.defaults & -2;
            notification.defaults = notification.defaults & -3;
        }

        public void addAction(NotificationCompatBase.Action action)
        {
            NotificationCompatApi20.addAction(b, action);
        }

        public Notification build()
        {
            b.setExtras(mExtras);
            Notification notification = b.build();
            if(mContentView != null)
                notification.contentView = mContentView;
            if(mBigContentView != null)
                notification.bigContentView = mBigContentView;
            if(mHeadsUpContentView != null)
                notification.headsUpContentView = mHeadsUpContentView;
            if(mGroupAlertBehavior != 0)
            {
                if(notification.getGroup() != null && (notification.flags & 0x200) != 0 && mGroupAlertBehavior == 2)
                    removeSoundAndVibration(notification);
                if(notification.getGroup() != null && (notification.flags & 0x200) == 0 && mGroupAlertBehavior == 1)
                    removeSoundAndVibration(notification);
            }
            return notification;
        }

        public android.app.Notification.Builder getBuilder()
        {
            return b;
        }

        private android.app.Notification.Builder b;
        private RemoteViews mBigContentView;
        private RemoteViews mContentView;
        private Bundle mExtras;
        private int mGroupAlertBehavior;
        private RemoteViews mHeadsUpContentView;

        public Builder(Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, 
                PendingIntent pendingintent, PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag, boolean flag1, 
                boolean flag2, int l, CharSequence charsequence3, boolean flag3, String s, ArrayList arraylist, Bundle bundle, 
                int i1, int j1, Notification notification1, String s1, boolean flag4, String s2, RemoteViews remoteviews1, 
                RemoteViews remoteviews2, RemoteViews remoteviews3, int k1)
        {
            context = (new android.app.Notification.Builder(context)).setWhen(notification.when).setShowWhen(flag1).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteviews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            if((notification.flags & 2) != 0)
                flag1 = true;
            else
                flag1 = false;
            context = context.setOngoing(flag1);
            if((notification.flags & 8) != 0)
                flag1 = true;
            else
                flag1 = false;
            context = context.setOnlyAlertOnce(flag1);
            if((notification.flags & 0x10) != 0)
                flag1 = true;
            else
                flag1 = false;
            context = context.setAutoCancel(flag1).setDefaults(notification.defaults).setContentTitle(charsequence).setContentText(charsequence1).setSubText(charsequence3).setContentInfo(charsequence2).setContentIntent(pendingintent).setDeleteIntent(notification.deleteIntent);
            if((notification.flags & 0x80) != 0)
                flag1 = true;
            else
                flag1 = false;
            b = context.setFullScreenIntent(pendingintent1, flag1).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(flag2).setPriority(l).setProgress(j, k, flag).setLocalOnly(flag3).setGroup(s1).setGroupSummary(flag4).setSortKey(s2).setCategory(s).setColor(i1).setVisibility(j1).setPublicVersion(notification1);
            mExtras = new Bundle();
            if(bundle != null)
                mExtras.putAll(bundle);
            for(context = arraylist.iterator(); context.hasNext(); b.addPerson(notification))
                notification = (String)context.next();

            mContentView = remoteviews1;
            mBigContentView = remoteviews2;
            mHeadsUpContentView = remoteviews3;
            mGroupAlertBehavior = k1;
        }
    }


    NotificationCompatApi21()
    {
    }

    private static RemoteInput fromCompatRemoteInput(RemoteInputCompatBase.RemoteInput remoteinput)
    {
        return (new android.app.RemoteInput.Builder(remoteinput.getResultKey())).setLabel(remoteinput.getLabel()).setChoices(remoteinput.getChoices()).setAllowFreeFormInput(remoteinput.getAllowFreeFormInput()).addExtras(remoteinput.getExtras()).build();
    }

    static Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation unreadconversation)
    {
        if(unreadconversation == null)
            return null;
        Bundle bundle = new Bundle();
        Parcelable aparcelable[] = null;
        Object obj = aparcelable;
        if(unreadconversation.getParticipants() != null)
        {
            obj = aparcelable;
            if(unreadconversation.getParticipants().length > 1)
                obj = unreadconversation.getParticipants()[0];
        }
        aparcelable = new Parcelable[unreadconversation.getMessages().length];
        for(int i = 0; i < aparcelable.length; i++)
        {
            Bundle bundle1 = new Bundle();
            bundle1.putString("text", unreadconversation.getMessages()[i]);
            bundle1.putString("author", ((String) (obj)));
            aparcelable[i] = bundle1;
        }

        bundle.putParcelableArray("messages", aparcelable);
        obj = unreadconversation.getRemoteInput();
        if(obj != null)
            bundle.putParcelable("remote_input", fromCompatRemoteInput(((RemoteInputCompatBase.RemoteInput) (obj))));
        bundle.putParcelable("on_reply", unreadconversation.getReplyPendingIntent());
        bundle.putParcelable("on_read", unreadconversation.getReadPendingIntent());
        bundle.putStringArray("participants", unreadconversation.getParticipants());
        bundle.putLong("timestamp", unreadconversation.getLatestTimestamp());
        return bundle;
    }

    static NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle bundle, NotificationCompatBase.UnreadConversation.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        RemoteInputCompatBase.RemoteInput remoteinput = null;
        if(bundle != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        int i;
        boolean flag1;
        String as[];
        Object obj;
        obj = bundle.getParcelableArray("messages");
        as = null;
        if(obj == null)
            break; /* Loop/switch isn't completed */
        as = new String[obj.length];
        flag1 = true;
        i = 0;
_L4:
        boolean flag = flag1;
        PendingIntent pendingintent;
        String as1[];
        RemoteInput remoteinput1;
        if(i < as.length)
            if(!(obj[i] instanceof Bundle))
            {
                flag = false;
            } else
            {
label0:
                {
                    as[i] = ((Bundle)obj[i]).getString("text");
                    if(as[i] != null)
                        break label0;
                    flag = false;
                }
            }
        if(!flag) goto _L1; else goto _L3
_L3:
        obj = (PendingIntent)bundle.getParcelable("on_read");
        pendingintent = (PendingIntent)bundle.getParcelable("on_reply");
        remoteinput1 = (RemoteInput)bundle.getParcelable("remote_input");
        as1 = bundle.getStringArray("participants");
        if(as1 != null && as1.length == 1)
        {
            if(remoteinput1 != null)
                remoteinput = toCompatRemoteInput(remoteinput1, factory1);
            return factory.build(as, remoteinput, pendingintent, ((PendingIntent) (obj)), as1, bundle.getLong("timestamp"));
        }
          goto _L1
        i++;
          goto _L4
    }

    private static RemoteInputCompatBase.RemoteInput toCompatRemoteInput(RemoteInput remoteinput, RemoteInputCompatBase.RemoteInput.Factory factory)
    {
        return factory.build(remoteinput.getResultKey(), remoteinput.getLabel(), remoteinput.getChoices(), remoteinput.getAllowFreeFormInput(), remoteinput.getExtras(), null);
    }

    private static final String KEY_AUTHOR = "author";
    private static final String KEY_MESSAGES = "messages";
    private static final String KEY_ON_READ = "on_read";
    private static final String KEY_ON_REPLY = "on_reply";
    private static final String KEY_PARTICIPANTS = "participants";
    private static final String KEY_REMOTE_INPUT = "remote_input";
    private static final String KEY_TEXT = "text";
    private static final String KEY_TIMESTAMP = "timestamp";
}
