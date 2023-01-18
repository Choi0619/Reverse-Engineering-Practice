// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.*;

// Referenced classes of package android.support.v4.app:
//            RemoteInputCompatApi20, NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions

class NotificationCompatApi24
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
            NotificationCompatApi24.addAction(b, action);
        }

        public Notification build()
        {
            Notification notification = b.build();
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
        private int mGroupAlertBehavior;

        public Builder(Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, 
                PendingIntent pendingintent, PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag, boolean flag1, 
                boolean flag2, int l, CharSequence charsequence3, boolean flag3, String s, ArrayList arraylist, Bundle bundle, 
                int i1, int j1, Notification notification1, String s1, boolean flag4, String s2, CharSequence acharsequence[], 
                RemoteViews remoteviews1, RemoteViews remoteviews2, RemoteViews remoteviews3, int k1)
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
            b = context.setFullScreenIntent(pendingintent1, flag1).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(flag2).setPriority(l).setProgress(j, k, flag).setLocalOnly(flag3).setExtras(bundle).setGroup(s1).setGroupSummary(flag4).setSortKey(s2).setCategory(s).setColor(i1).setVisibility(j1).setPublicVersion(notification1).setRemoteInputHistory(acharsequence);
            if(remoteviews1 != null)
                b.setCustomContentView(remoteviews1);
            if(remoteviews2 != null)
                b.setCustomBigContentView(remoteviews2);
            if(remoteviews3 != null)
                b.setCustomHeadsUpContentView(remoteviews3);
            for(context = arraylist.iterator(); context.hasNext(); b.addPerson(notification))
                notification = (String)context.next();

            mGroupAlertBehavior = k1;
        }
    }


    NotificationCompatApi24()
    {
    }

    public static void addAction(android.app.Notification.Builder builder, NotificationCompatBase.Action action)
    {
        android.app.Notification.Action.Builder builder1 = new android.app.Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
        if(action.getRemoteInputs() != null)
        {
            android.app.RemoteInput aremoteinput[] = RemoteInputCompatApi20.fromCompat(action.getRemoteInputs());
            int j = aremoteinput.length;
            for(int i = 0; i < j; i++)
                builder1.addRemoteInput(aremoteinput[i]);

        }
        Bundle bundle;
        if(action.getExtras() != null)
            bundle = new Bundle(action.getExtras());
        else
            bundle = new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        builder1.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
        builder1.addExtras(bundle);
        builder.addAction(builder1.build());
    }

    public static void addMessagingStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, CharSequence charsequence, CharSequence charsequence1, List list, List list1, List list2, List list3, List list4)
    {
        charsequence = (new android.app.Notification.MessagingStyle(charsequence)).setConversationTitle(charsequence1);
        for(int i = 0; i < list.size(); i++)
        {
            charsequence1 = new android.app.Notification.MessagingStyle.Message((CharSequence)list.get(i), ((Long)list1.get(i)).longValue(), (CharSequence)list2.get(i));
            if(list3.get(i) != null)
                charsequence1.setData((String)list3.get(i), (Uri)list4.get(i));
            charsequence.addMessage(charsequence1);
        }

        charsequence.setBuilder(notificationbuilderwithbuilderaccessor.getBuilder());
    }

    public static NotificationCompatBase.Action getAction(Notification notification, int i, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        return getActionCompatFromAction(notification.actions[i], factory, factory1);
    }

    private static NotificationCompatBase.Action getActionCompatFromAction(android.app.Notification.Action action, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        factory1 = RemoteInputCompatApi20.toCompat(action.getRemoteInputs(), factory1);
        boolean flag;
        if(action.getExtras().getBoolean("android.support.allowGeneratedReplies") || action.getAllowGeneratedReplies())
            flag = true;
        else
            flag = false;
        return factory.build(action.icon, action.title, action.actionIntent, action.getExtras(), factory1, null, flag);
    }

    private static android.app.Notification.Action getActionFromActionCompat(NotificationCompatBase.Action action)
    {
        android.app.Notification.Action.Builder builder = new android.app.Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
        Bundle bundle;
        if(action.getExtras() != null)
            bundle = new Bundle(action.getExtras());
        else
            bundle = new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
        builder.addExtras(bundle);
        action = action.getRemoteInputs();
        if(action != null)
        {
            action = RemoteInputCompatApi20.fromCompat(action);
            int j = action.length;
            for(int i = 0; i < j; i++)
                builder.addRemoteInput(action[i]);

        }
        return builder.build();
    }

    public static NotificationCompatBase.Action[] getActionsFromParcelableArrayList(ArrayList arraylist, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        if(arraylist != null) goto _L2; else goto _L1
_L1:
        NotificationCompatBase.Action aaction[] = null;
_L4:
        return aaction;
_L2:
        NotificationCompatBase.Action aaction1[] = factory.newArray(arraylist.size());
        int i = 0;
        do
        {
            aaction = aaction1;
            if(i >= aaction1.length)
                continue;
            aaction1[i] = getActionCompatFromAction((android.app.Notification.Action)arraylist.get(i), factory, factory1);
            i++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static ArrayList getParcelableArrayListForActions(NotificationCompatBase.Action aaction[])
    {
        if(aaction != null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist = null;
_L4:
        return arraylist;
_L2:
        ArrayList arraylist1 = new ArrayList(aaction.length);
        int j = aaction.length;
        int i = 0;
        do
        {
            arraylist = arraylist1;
            if(i >= j)
                continue;
            arraylist1.add(getActionFromActionCompat(aaction[i]));
            i++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }
}