// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package android.support.v4.app:
//            NotificationBuilderWithBuilderAccessor, BundleUtil, RemoteInputCompatJellybean, NotificationBuilderWithActions

class NotificationCompatJellybean
{
    public static class Builder
        implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions
    {

        public void addAction(NotificationCompatBase.Action action)
        {
            mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(b, action));
        }

        public Notification build()
        {
            Notification notification = b.build();
            Object obj = NotificationCompatJellybean.getExtras(notification);
            Bundle bundle = new Bundle(mExtras);
            Iterator iterator = mExtras.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                String s = (String)iterator.next();
                if(((Bundle) (obj)).containsKey(s))
                    bundle.remove(s);
            } while(true);
            ((Bundle) (obj)).putAll(bundle);
            obj = NotificationCompatJellybean.buildActionExtrasMap(mActionExtrasList);
            if(obj != null)
                NotificationCompatJellybean.getExtras(notification).putSparseParcelableArray("android.support.actionExtras", ((SparseArray) (obj)));
            if(mContentView != null)
                notification.contentView = mContentView;
            if(mBigContentView != null)
                notification.bigContentView = mBigContentView;
            return notification;
        }

        public android.app.Notification.Builder getBuilder()
        {
            return b;
        }

        private android.app.Notification.Builder b;
        private List mActionExtrasList;
        private RemoteViews mBigContentView;
        private RemoteViews mContentView;
        private final Bundle mExtras = new Bundle();

        public Builder(Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, 
                PendingIntent pendingintent, PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag, boolean flag1, 
                int l, CharSequence charsequence3, boolean flag2, Bundle bundle, String s, boolean flag3, String s1, 
                RemoteViews remoteviews1, RemoteViews remoteviews2)
        {
            mActionExtrasList = new ArrayList();
            context = (new android.app.Notification.Builder(context)).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteviews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            boolean flag4;
            if((notification.flags & 2) != 0)
                flag4 = true;
            else
                flag4 = false;
            context = context.setOngoing(flag4);
            if((notification.flags & 8) != 0)
                flag4 = true;
            else
                flag4 = false;
            context = context.setOnlyAlertOnce(flag4);
            if((notification.flags & 0x10) != 0)
                flag4 = true;
            else
                flag4 = false;
            context = context.setAutoCancel(flag4).setDefaults(notification.defaults).setContentTitle(charsequence).setContentText(charsequence1).setSubText(charsequence3).setContentInfo(charsequence2).setContentIntent(pendingintent).setDeleteIntent(notification.deleteIntent);
            if((notification.flags & 0x80) != 0)
                flag4 = true;
            else
                flag4 = false;
            b = context.setFullScreenIntent(pendingintent1, flag4).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(flag1).setPriority(l).setProgress(j, k, flag);
            if(bundle != null)
                mExtras.putAll(bundle);
            if(flag2)
                mExtras.putBoolean("android.support.localOnly", true);
            if(s != null)
            {
                mExtras.putString("android.support.groupKey", s);
                if(flag3)
                    mExtras.putBoolean("android.support.isGroupSummary", true);
                else
                    mExtras.putBoolean("android.support.useSideChannel", true);
            }
            if(s1 != null)
                mExtras.putString("android.support.sortKey", s1);
            mContentView = remoteviews1;
            mBigContentView = remoteviews2;
        }
    }


    NotificationCompatJellybean()
    {
    }

    public static void addBigPictureStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, CharSequence charsequence, boolean flag, CharSequence charsequence1, Bitmap bitmap, Bitmap bitmap1, boolean flag1)
    {
        notificationbuilderwithbuilderaccessor = (new android.app.Notification.BigPictureStyle(notificationbuilderwithbuilderaccessor.getBuilder())).setBigContentTitle(charsequence).bigPicture(bitmap);
        if(flag1)
            notificationbuilderwithbuilderaccessor.bigLargeIcon(bitmap1);
        if(flag)
            notificationbuilderwithbuilderaccessor.setSummaryText(charsequence1);
    }

    public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, CharSequence charsequence, boolean flag, CharSequence charsequence1, CharSequence charsequence2)
    {
        notificationbuilderwithbuilderaccessor = (new android.app.Notification.BigTextStyle(notificationbuilderwithbuilderaccessor.getBuilder())).setBigContentTitle(charsequence).bigText(charsequence2);
        if(flag)
            notificationbuilderwithbuilderaccessor.setSummaryText(charsequence1);
    }

    public static void addInboxStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, CharSequence charsequence, boolean flag, CharSequence charsequence1, ArrayList arraylist)
    {
        notificationbuilderwithbuilderaccessor = (new android.app.Notification.InboxStyle(notificationbuilderwithbuilderaccessor.getBuilder())).setBigContentTitle(charsequence);
        if(flag)
            notificationbuilderwithbuilderaccessor.setSummaryText(charsequence1);
        for(charsequence = arraylist.iterator(); charsequence.hasNext(); notificationbuilderwithbuilderaccessor.addLine((CharSequence)charsequence.next()));
    }

    public static SparseArray buildActionExtrasMap(List list)
    {
        SparseArray sparsearray = null;
        int i = 0;
        for(int j = list.size(); i < j;)
        {
            Bundle bundle = (Bundle)list.get(i);
            SparseArray sparsearray1 = sparsearray;
            if(bundle != null)
            {
                sparsearray1 = sparsearray;
                if(sparsearray == null)
                    sparsearray1 = new SparseArray();
                sparsearray1.put(i, bundle);
            }
            i++;
            sparsearray = sparsearray1;
        }

        return sparsearray;
    }

    private static boolean ensureActionReflectionReadyLocked()
    {
        boolean flag = true;
        if(sActionsAccessFailed)
            return false;
        try
        {
            if(sActionsField == null)
            {
                sActionClass = Class.forName("android.app.Notification$Action");
                sActionIconField = sActionClass.getDeclaredField("icon");
                sActionTitleField = sActionClass.getDeclaredField("title");
                sActionIntentField = sActionClass.getDeclaredField("actionIntent");
                sActionsField = android/app/Notification.getDeclaredField("actions");
                sActionsField.setAccessible(true);
            }
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            Log.e("NotificationCompat", "Unable to access notification actions", classnotfoundexception);
            sActionsAccessFailed = true;
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            Log.e("NotificationCompat", "Unable to access notification actions", nosuchfieldexception);
            sActionsAccessFailed = true;
        }
        if(sActionsAccessFailed)
            flag = false;
        return flag;
    }

    public static NotificationCompatBase.Action getAction(Notification notification, int i, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        Object obj = sActionsLock;
        obj;
        JVM INSTR monitorenter ;
        Object aobj[] = getActionObjectsLocked(notification);
        Object obj1;
        if(aobj == null)
            break MISSING_BLOCK_LABEL_125;
        obj1 = aobj[i];
        aobj = null;
        Object obj2 = getExtras(notification);
        notification = ((Notification) (aobj));
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_69;
        obj2 = ((Bundle) (obj2)).getSparseParcelableArray("android.support.actionExtras");
        notification = ((Notification) (aobj));
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_69;
        notification = (Bundle)((SparseArray) (obj2)).get(i);
        notification = readAction(factory, factory1, sActionIconField.getInt(obj1), (CharSequence)sActionTitleField.get(obj1), (PendingIntent)sActionIntentField.get(obj1), notification);
        obj;
        JVM INSTR monitorexit ;
        return notification;
        notification;
        Log.e("NotificationCompat", "Unable to access notification actions", notification);
        sActionsAccessFailed = true;
        obj;
        JVM INSTR monitorexit ;
        return null;
        notification;
        obj;
        JVM INSTR monitorexit ;
        throw notification;
    }

    public static int getActionCount(Notification notification)
    {
        Object obj = sActionsLock;
        obj;
        JVM INSTR monitorenter ;
        notification = ((Notification) (getActionObjectsLocked(notification)));
        if(notification == null)
            break MISSING_BLOCK_LABEL_27;
        int i = notification.length;
_L1:
        obj;
        JVM INSTR monitorexit ;
        return i;
        notification;
        obj;
        JVM INSTR monitorexit ;
        throw notification;
        i = 0;
          goto _L1
    }

    private static NotificationCompatBase.Action getActionFromBundle(Bundle bundle, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        Bundle bundle1 = bundle.getBundle("extras");
        boolean flag = false;
        if(bundle1 != null)
            flag = bundle1.getBoolean("android.support.allowGeneratedReplies", false);
        return factory.build(bundle.getInt("icon"), bundle.getCharSequence("title"), (PendingIntent)bundle.getParcelable("actionIntent"), bundle.getBundle("extras"), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "remoteInputs"), factory1), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "dataOnlyRemoteInputs"), factory1), flag);
    }

    private static Object[] getActionObjectsLocked(Notification notification)
    {
label0:
        {
            synchronized(sActionsLock)
            {
                if(ensureActionReflectionReadyLocked())
                    break label0;
            }
            return null;
        }
        notification = ((Notification) ((Object[])(Object[])sActionsField.get(notification)));
        obj;
        JVM INSTR monitorexit ;
        return notification;
        notification;
        obj;
        JVM INSTR monitorexit ;
        throw notification;
        notification;
        Log.e("NotificationCompat", "Unable to access notification actions", notification);
        sActionsAccessFailed = true;
        obj;
        JVM INSTR monitorexit ;
        return null;
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
            aaction1[i] = getActionFromBundle((Bundle)arraylist.get(i), factory, factory1);
            i++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static Bundle getBundleForAction(NotificationCompatBase.Action action)
    {
        Bundle bundle1 = new Bundle();
        bundle1.putInt("icon", action.getIcon());
        bundle1.putCharSequence("title", action.getTitle());
        bundle1.putParcelable("actionIntent", action.getActionIntent());
        Bundle bundle;
        if(action.getExtras() != null)
            bundle = new Bundle(action.getExtras());
        else
            bundle = new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        bundle1.putBundle("extras", bundle);
        bundle1.putParcelableArray("remoteInputs", RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
        return bundle1;
    }

    public static Bundle getExtras(Notification notification)
    {
label0:
        {
            synchronized(sExtrasLock)
            {
                if(!sExtrasFieldAccessFailed)
                    break label0;
            }
            return null;
        }
        Object obj;
        if(sExtrasField != null)
            break MISSING_BLOCK_LABEL_68;
        obj = android/app/Notification.getDeclaredField("extras");
        if(android/os/Bundle.isAssignableFrom(((Field) (obj)).getType()))
            break MISSING_BLOCK_LABEL_59;
        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
        sExtrasFieldAccessFailed = true;
        obj1;
        JVM INSTR monitorexit ;
        return null;
        ((Field) (obj)).setAccessible(true);
        sExtrasField = ((Field) (obj));
        Bundle bundle = (Bundle)sExtrasField.get(notification);
        obj = bundle;
        if(bundle != null)
            break MISSING_BLOCK_LABEL_101;
        obj = new Bundle();
        sExtrasField.set(notification, obj);
        obj1;
        JVM INSTR monitorexit ;
        return ((Bundle) (obj));
        notification;
        obj1;
        JVM INSTR monitorexit ;
        throw notification;
        notification;
        Log.e("NotificationCompat", "Unable to access notification extras", notification);
_L1:
        sExtrasFieldAccessFailed = true;
        obj1;
        JVM INSTR monitorexit ;
        return null;
        notification;
        Log.e("NotificationCompat", "Unable to access notification extras", notification);
          goto _L1
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
            arraylist1.add(getBundleForAction(aaction[i]));
            i++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static NotificationCompatBase.Action readAction(NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1, int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle)
    {
        RemoteInputCompatBase.RemoteInput aremoteinput[] = null;
        RemoteInputCompatBase.RemoteInput aremoteinput1[] = null;
        boolean flag = false;
        if(bundle != null)
        {
            aremoteinput = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "android.support.remoteInputs"), factory1);
            aremoteinput1 = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "android.support.dataRemoteInputs"), factory1);
            flag = bundle.getBoolean("android.support.allowGeneratedReplies");
        }
        return factory.build(i, charsequence, pendingintent, bundle, aremoteinput, aremoteinput1, flag);
    }

    public static Bundle writeActionAndGetExtras(android.app.Notification.Builder builder, NotificationCompatBase.Action action)
    {
        builder.addAction(action.getIcon(), action.getTitle(), action.getActionIntent());
        builder = new Bundle(action.getExtras());
        if(action.getRemoteInputs() != null)
            builder.putParcelableArray("android.support.remoteInputs", RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
        if(action.getDataOnlyRemoteInputs() != null)
            builder.putParcelableArray("android.support.dataRemoteInputs", RemoteInputCompatJellybean.toBundleArray(action.getDataOnlyRemoteInputs()));
        builder.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        return builder;
    }

    static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
    static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
    private static final String KEY_ACTION_INTENT = "actionIntent";
    private static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ICON = "icon";
    private static final String KEY_REMOTE_INPUTS = "remoteInputs";
    private static final String KEY_TITLE = "title";
    public static final String TAG = "NotificationCompat";
    private static Class sActionClass;
    private static Field sActionIconField;
    private static Field sActionIntentField;
    private static Field sActionTitleField;
    private static boolean sActionsAccessFailed;
    private static Field sActionsField;
    private static final Object sActionsLock = new Object();
    private static Field sExtrasField;
    private static boolean sExtrasFieldAccessFailed;
    private static final Object sExtrasLock = new Object();

}
