// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import android.support.v4.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import java.lang.annotation.Annotation;
import java.text.NumberFormat;
import java.util.*;

// Referenced classes of package android.support.v4.app:
//            NotificationBuilderWithActions, NotificationCompatJellybean, RemoteInput, NotificationBuilderWithBuilderAccessor, 
//            NotificationCompatApi24, NotificationCompatKitKat, NotificationCompatApi20, NotificationCompatApi21

public class NotificationCompat
{
    public static class Action extends NotificationCompatBase.Action
    {

        public PendingIntent getActionIntent()
        {
            return actionIntent;
        }

        public boolean getAllowGeneratedReplies()
        {
            return mAllowGeneratedReplies;
        }

        public RemoteInput[] getDataOnlyRemoteInputs()
        {
            return mDataOnlyRemoteInputs;
        }

        public volatile RemoteInputCompatBase.RemoteInput[] getDataOnlyRemoteInputs()
        {
            return getDataOnlyRemoteInputs();
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public int getIcon()
        {
            return icon;
        }

        public RemoteInput[] getRemoteInputs()
        {
            return mRemoteInputs;
        }

        public volatile RemoteInputCompatBase.RemoteInput[] getRemoteInputs()
        {
            return getRemoteInputs();
        }

        public CharSequence getTitle()
        {
            return title;
        }

        public static final NotificationCompatBase.Action.Factory FACTORY = new NotificationCompatBase.Action.Factory() {

            public NotificationCompatBase.Action build(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInputCompatBase.RemoteInput aremoteinput[], RemoteInputCompatBase.RemoteInput aremoteinput1[], boolean flag)
            {
                return new Action(i, charsequence, pendingintent, bundle, (RemoteInput[])(RemoteInput[])aremoteinput, (RemoteInput[])(RemoteInput[])aremoteinput1, flag);
            }

            public Action[] newArray(int i)
            {
                return new Action[i];
            }

            public volatile NotificationCompatBase.Action[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public PendingIntent actionIntent;
        public int icon;
        private boolean mAllowGeneratedReplies;
        private final RemoteInput mDataOnlyRemoteInputs[];
        final Bundle mExtras;
        private final RemoteInput mRemoteInputs[];
        public CharSequence title;


        public Action(int i, CharSequence charsequence, PendingIntent pendingintent)
        {
            this(i, charsequence, pendingintent, new Bundle(), null, null, true);
        }

        Action(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInput aremoteinput[], RemoteInput aremoteinput1[], boolean flag)
        {
            icon = i;
            title = Builder.limitCharSequenceLength(charsequence);
            actionIntent = pendingintent;
            if(bundle == null)
                bundle = new Bundle();
            mExtras = bundle;
            mRemoteInputs = aremoteinput;
            mDataOnlyRemoteInputs = aremoteinput1;
            mAllowGeneratedReplies = flag;
        }
    }

    public static final class Action.Builder
    {

        public Action.Builder addExtras(Bundle bundle)
        {
            if(bundle != null)
                mExtras.putAll(bundle);
            return this;
        }

        public Action.Builder addRemoteInput(RemoteInput remoteinput)
        {
            if(mRemoteInputs == null)
                mRemoteInputs = new ArrayList();
            mRemoteInputs.add(remoteinput);
            return this;
        }

        public Action build()
        {
            RemoteInput aremoteinput[] = new ArrayList();
            RemoteInput aremoteinput1[] = new ArrayList();
            if(mRemoteInputs != null)
            {
                for(Iterator iterator = mRemoteInputs.iterator(); iterator.hasNext();)
                {
                    RemoteInput remoteinput = (RemoteInput)iterator.next();
                    if(remoteinput.isDataOnly())
                        aremoteinput.add(remoteinput);
                    else
                        aremoteinput1.add(remoteinput);
                }

            }
            if(aremoteinput.isEmpty())
                aremoteinput = null;
            else
                aremoteinput = (RemoteInput[])aremoteinput.toArray(new RemoteInput[aremoteinput.size()]);
            if(aremoteinput1.isEmpty())
                aremoteinput1 = null;
            else
                aremoteinput1 = (RemoteInput[])aremoteinput1.toArray(new RemoteInput[aremoteinput1.size()]);
            return new Action(mIcon, mTitle, mIntent, mExtras, aremoteinput1, aremoteinput, mAllowGeneratedReplies);
        }

        public Action.Builder extend(Action.Extender extender)
        {
            extender.extend(this);
            return this;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public Action.Builder setAllowGeneratedReplies(boolean flag)
        {
            mAllowGeneratedReplies = flag;
            return this;
        }

        private boolean mAllowGeneratedReplies;
        private final Bundle mExtras;
        private final int mIcon;
        private final PendingIntent mIntent;
        private ArrayList mRemoteInputs;
        private final CharSequence mTitle;

        public Action.Builder(int i, CharSequence charsequence, PendingIntent pendingintent)
        {
            this(i, charsequence, pendingintent, new Bundle(), null, true);
        }

        private Action.Builder(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInput aremoteinput[], boolean flag)
        {
            mAllowGeneratedReplies = true;
            mIcon = i;
            mTitle = Builder.limitCharSequenceLength(charsequence);
            mIntent = pendingintent;
            mExtras = bundle;
            if(aremoteinput == null)
                charsequence = null;
            else
                charsequence = new ArrayList(Arrays.asList(aremoteinput));
            mRemoteInputs = charsequence;
            mAllowGeneratedReplies = flag;
        }

        public Action.Builder(Action action)
        {
            this(action.icon, action.title, action.actionIntent, new Bundle(action.mExtras), action.getRemoteInputs(), action.getAllowGeneratedReplies());
        }
    }

    public static interface Action.Extender
    {

        public abstract Action.Builder extend(Action.Builder builder);
    }

    public static final class Action.WearableExtender
        implements Action.Extender
    {

        private void setFlag(int i, boolean flag)
        {
            if(flag)
            {
                mFlags = mFlags | i;
                return;
            } else
            {
                mFlags = mFlags & ~i;
                return;
            }
        }

        public Action.WearableExtender clone()
        {
            Action.WearableExtender wearableextender = new Action.WearableExtender();
            wearableextender.mFlags = mFlags;
            wearableextender.mInProgressLabel = mInProgressLabel;
            wearableextender.mConfirmLabel = mConfirmLabel;
            wearableextender.mCancelLabel = mCancelLabel;
            return wearableextender;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Action.Builder extend(Action.Builder builder)
        {
            Bundle bundle = new Bundle();
            if(mFlags != 1)
                bundle.putInt("flags", mFlags);
            if(mInProgressLabel != null)
                bundle.putCharSequence("inProgressLabel", mInProgressLabel);
            if(mConfirmLabel != null)
                bundle.putCharSequence("confirmLabel", mConfirmLabel);
            if(mCancelLabel != null)
                bundle.putCharSequence("cancelLabel", mCancelLabel);
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }

        public CharSequence getCancelLabel()
        {
            return mCancelLabel;
        }

        public CharSequence getConfirmLabel()
        {
            return mConfirmLabel;
        }

        public boolean getHintDisplayActionInline()
        {
            return (mFlags & 4) != 0;
        }

        public boolean getHintLaunchesActivity()
        {
            return (mFlags & 2) != 0;
        }

        public CharSequence getInProgressLabel()
        {
            return mInProgressLabel;
        }

        public boolean isAvailableOffline()
        {
            return (mFlags & 1) != 0;
        }

        public Action.WearableExtender setAvailableOffline(boolean flag)
        {
            setFlag(1, flag);
            return this;
        }

        public Action.WearableExtender setCancelLabel(CharSequence charsequence)
        {
            mCancelLabel = charsequence;
            return this;
        }

        public Action.WearableExtender setConfirmLabel(CharSequence charsequence)
        {
            mConfirmLabel = charsequence;
            return this;
        }

        public Action.WearableExtender setHintDisplayActionInline(boolean flag)
        {
            setFlag(4, flag);
            return this;
        }

        public Action.WearableExtender setHintLaunchesActivity(boolean flag)
        {
            setFlag(2, flag);
            return this;
        }

        public Action.WearableExtender setInProgressLabel(CharSequence charsequence)
        {
            mInProgressLabel = charsequence;
            return this;
        }

        private static final int DEFAULT_FLAGS = 1;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_DISPLAY_INLINE = 4;
        private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
        private static final String KEY_CANCEL_LABEL = "cancelLabel";
        private static final String KEY_CONFIRM_LABEL = "confirmLabel";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
        private CharSequence mCancelLabel;
        private CharSequence mConfirmLabel;
        private int mFlags;
        private CharSequence mInProgressLabel;

        public Action.WearableExtender()
        {
            mFlags = 1;
        }

        public Action.WearableExtender(Action action)
        {
            mFlags = 1;
            action = action.getExtras().getBundle("android.wearable.EXTENSIONS");
            if(action != null)
            {
                mFlags = action.getInt("flags", 1);
                mInProgressLabel = action.getCharSequence("inProgressLabel");
                mConfirmLabel = action.getCharSequence("confirmLabel");
                mCancelLabel = action.getCharSequence("cancelLabel");
            }
        }
    }

    public static interface BadgeIconType
        extends Annotation
    {
    }

    public static class BigPictureStyle extends Style
    {

        public void apply(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT >= 16)
                NotificationCompatJellybean.addBigPictureStyle(notificationbuilderwithbuilderaccessor, mBigContentTitle, mSummaryTextSet, mSummaryText, mPicture, mBigLargeIcon, mBigLargeIconSet);
        }

        public BigPictureStyle bigLargeIcon(Bitmap bitmap)
        {
            mBigLargeIcon = bitmap;
            mBigLargeIconSet = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap)
        {
            mPicture = bitmap;
            return this;
        }

        public BigPictureStyle setBigContentTitle(CharSequence charsequence)
        {
            mBigContentTitle = Builder.limitCharSequenceLength(charsequence);
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charsequence)
        {
            mSummaryText = Builder.limitCharSequenceLength(charsequence);
            mSummaryTextSet = true;
            return this;
        }

        private Bitmap mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private Bitmap mPicture;

        public BigPictureStyle()
        {
        }

        public BigPictureStyle(Builder builder)
        {
            setBuilder(builder);
        }
    }

    public static class BigTextStyle extends Style
    {

        public void apply(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT >= 16)
                NotificationCompatJellybean.addBigTextStyle(notificationbuilderwithbuilderaccessor, mBigContentTitle, mSummaryTextSet, mSummaryText, mBigText);
        }

        public BigTextStyle bigText(CharSequence charsequence)
        {
            mBigText = Builder.limitCharSequenceLength(charsequence);
            return this;
        }

        public BigTextStyle setBigContentTitle(CharSequence charsequence)
        {
            mBigContentTitle = Builder.limitCharSequenceLength(charsequence);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charsequence)
        {
            mSummaryText = Builder.limitCharSequenceLength(charsequence);
            mSummaryTextSet = true;
            return this;
        }

        private CharSequence mBigText;

        public BigTextStyle()
        {
        }

        public BigTextStyle(Builder builder)
        {
            setBuilder(builder);
        }
    }

    public static class Builder
    {

        protected static CharSequence limitCharSequenceLength(CharSequence charsequence)
        {
            while(charsequence == null || charsequence.length() <= 5120) 
                return charsequence;
            return charsequence.subSequence(0, 5120);
        }

        private void setFlag(int i, boolean flag)
        {
            if(flag)
            {
                Notification notification = mNotification;
                notification.flags = notification.flags | i;
                return;
            } else
            {
                Notification notification1 = mNotification;
                notification1.flags = notification1.flags & ~i;
                return;
            }
        }

        public Builder addAction(int i, CharSequence charsequence, PendingIntent pendingintent)
        {
            mActions.add(new Action(i, charsequence, pendingintent));
            return this;
        }

        public Builder addAction(Action action)
        {
            mActions.add(action);
            return this;
        }

        public Builder addExtras(Bundle bundle)
        {
label0:
            {
                if(bundle != null)
                {
                    if(mExtras != null)
                        break label0;
                    mExtras = new Bundle(bundle);
                }
                return this;
            }
            mExtras.putAll(bundle);
            return this;
        }

        public Builder addPerson(String s)
        {
            mPeople.add(s);
            return this;
        }

        public Notification build()
        {
            return NotificationCompat.IMPL.build(this, getExtender());
        }

        public Builder extend(Extender extender)
        {
            extender.extend(this);
            return this;
        }

        public RemoteViews getBigContentView()
        {
            return mBigContentView;
        }

        public int getColor()
        {
            return mColor;
        }

        public RemoteViews getContentView()
        {
            return mContentView;
        }

        protected BuilderExtender getExtender()
        {
            return new BuilderExtender();
        }

        public Bundle getExtras()
        {
            if(mExtras == null)
                mExtras = new Bundle();
            return mExtras;
        }

        public RemoteViews getHeadsUpContentView()
        {
            return mHeadsUpContentView;
        }

        public Notification getNotification()
        {
            return build();
        }

        public int getPriority()
        {
            return mPriority;
        }

        public long getWhenIfShowing()
        {
            if(mShowWhen)
                return mNotification.when;
            else
                return 0L;
        }

        public Builder setAutoCancel(boolean flag)
        {
            setFlag(16, flag);
            return this;
        }

        public Builder setBadgeIconType(int i)
        {
            mBadgeIcon = i;
            return this;
        }

        public Builder setCategory(String s)
        {
            mCategory = s;
            return this;
        }

        public Builder setChannelId(String s)
        {
            mChannelId = s;
            return this;
        }

        public Builder setColor(int i)
        {
            mColor = i;
            return this;
        }

        public Builder setColorized(boolean flag)
        {
            mColorized = flag;
            mColorizedSet = true;
            return this;
        }

        public Builder setContent(RemoteViews remoteviews)
        {
            mNotification.contentView = remoteviews;
            return this;
        }

        public Builder setContentInfo(CharSequence charsequence)
        {
            mContentInfo = limitCharSequenceLength(charsequence);
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingintent)
        {
            mContentIntent = pendingintent;
            return this;
        }

        public Builder setContentText(CharSequence charsequence)
        {
            mContentText = limitCharSequenceLength(charsequence);
            return this;
        }

        public Builder setContentTitle(CharSequence charsequence)
        {
            mContentTitle = limitCharSequenceLength(charsequence);
            return this;
        }

        public Builder setCustomBigContentView(RemoteViews remoteviews)
        {
            mBigContentView = remoteviews;
            return this;
        }

        public Builder setCustomContentView(RemoteViews remoteviews)
        {
            mContentView = remoteviews;
            return this;
        }

        public Builder setCustomHeadsUpContentView(RemoteViews remoteviews)
        {
            mHeadsUpContentView = remoteviews;
            return this;
        }

        public Builder setDefaults(int i)
        {
            mNotification.defaults = i;
            if((i & 4) != 0)
            {
                Notification notification = mNotification;
                notification.flags = notification.flags | 1;
            }
            return this;
        }

        public Builder setDeleteIntent(PendingIntent pendingintent)
        {
            mNotification.deleteIntent = pendingintent;
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mExtras = bundle;
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent pendingintent, boolean flag)
        {
            mFullScreenIntent = pendingintent;
            setFlag(128, flag);
            return this;
        }

        public Builder setGroup(String s)
        {
            mGroupKey = s;
            return this;
        }

        public Builder setGroupAlertBehavior(int i)
        {
            mGroupAlertBehavior = i;
            return this;
        }

        public Builder setGroupSummary(boolean flag)
        {
            mGroupSummary = flag;
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap)
        {
            mLargeIcon = bitmap;
            return this;
        }

        public Builder setLights(int i, int j, int k)
        {
            boolean flag = true;
            mNotification.ledARGB = i;
            mNotification.ledOnMS = j;
            mNotification.ledOffMS = k;
            Notification notification;
            if(mNotification.ledOnMS != 0 && mNotification.ledOffMS != 0)
                i = 1;
            else
                i = 0;
            notification = mNotification;
            j = mNotification.flags;
            if(i != 0)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            notification.flags = i | j & -2;
            return this;
        }

        public Builder setLocalOnly(boolean flag)
        {
            mLocalOnly = flag;
            return this;
        }

        public Builder setNumber(int i)
        {
            mNumber = i;
            return this;
        }

        public Builder setOngoing(boolean flag)
        {
            setFlag(2, flag);
            return this;
        }

        public Builder setOnlyAlertOnce(boolean flag)
        {
            setFlag(8, flag);
            return this;
        }

        public Builder setPriority(int i)
        {
            mPriority = i;
            return this;
        }

        public Builder setProgress(int i, int j, boolean flag)
        {
            mProgressMax = i;
            mProgress = j;
            mProgressIndeterminate = flag;
            return this;
        }

        public Builder setPublicVersion(Notification notification)
        {
            mPublicVersion = notification;
            return this;
        }

        public Builder setRemoteInputHistory(CharSequence acharsequence[])
        {
            mRemoteInputHistory = acharsequence;
            return this;
        }

        public Builder setShortcutId(String s)
        {
            mShortcutId = s;
            return this;
        }

        public Builder setShowWhen(boolean flag)
        {
            mShowWhen = flag;
            return this;
        }

        public Builder setSmallIcon(int i)
        {
            mNotification.icon = i;
            return this;
        }

        public Builder setSmallIcon(int i, int j)
        {
            mNotification.icon = i;
            mNotification.iconLevel = j;
            return this;
        }

        public Builder setSortKey(String s)
        {
            mSortKey = s;
            return this;
        }

        public Builder setSound(Uri uri)
        {
            mNotification.sound = uri;
            mNotification.audioStreamType = -1;
            return this;
        }

        public Builder setSound(Uri uri, int i)
        {
            mNotification.sound = uri;
            mNotification.audioStreamType = i;
            return this;
        }

        public Builder setStyle(Style style)
        {
            if(mStyle != style)
            {
                mStyle = style;
                if(mStyle != null)
                    mStyle.setBuilder(this);
            }
            return this;
        }

        public Builder setSubText(CharSequence charsequence)
        {
            mSubText = limitCharSequenceLength(charsequence);
            return this;
        }

        public Builder setTicker(CharSequence charsequence)
        {
            mNotification.tickerText = limitCharSequenceLength(charsequence);
            return this;
        }

        public Builder setTicker(CharSequence charsequence, RemoteViews remoteviews)
        {
            mNotification.tickerText = limitCharSequenceLength(charsequence);
            mTickerView = remoteviews;
            return this;
        }

        public Builder setTimeoutAfter(long l)
        {
            mTimeout = l;
            return this;
        }

        public Builder setUsesChronometer(boolean flag)
        {
            mUseChronometer = flag;
            return this;
        }

        public Builder setVibrate(long al[])
        {
            mNotification.vibrate = al;
            return this;
        }

        public Builder setVisibility(int i)
        {
            mVisibility = i;
            return this;
        }

        public Builder setWhen(long l)
        {
            mNotification.when = l;
            return this;
        }

        private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
        public ArrayList mActions;
        int mBadgeIcon;
        RemoteViews mBigContentView;
        String mCategory;
        String mChannelId;
        int mColor;
        boolean mColorized;
        boolean mColorizedSet;
        public CharSequence mContentInfo;
        PendingIntent mContentIntent;
        public CharSequence mContentText;
        public CharSequence mContentTitle;
        RemoteViews mContentView;
        public Context mContext;
        Bundle mExtras;
        PendingIntent mFullScreenIntent;
        private int mGroupAlertBehavior;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        public Bitmap mLargeIcon;
        boolean mLocalOnly;
        public Notification mNotification;
        public int mNumber;
        public ArrayList mPeople;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        public CharSequence mRemoteInputHistory[];
        String mShortcutId;
        boolean mShowWhen;
        String mSortKey;
        public Style mStyle;
        public CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        public boolean mUseChronometer;
        int mVisibility;


        public Builder(Context context)
        {
            this(context, null);
        }

        public Builder(Context context, String s)
        {
            mShowWhen = true;
            mActions = new ArrayList();
            mLocalOnly = false;
            mColor = 0;
            mVisibility = 0;
            mBadgeIcon = 0;
            mGroupAlertBehavior = 0;
            mNotification = new Notification();
            mContext = context;
            mChannelId = s;
            mNotification.when = System.currentTimeMillis();
            mNotification.audioStreamType = -1;
            mPriority = 0;
            mPeople = new ArrayList();
        }
    }

    protected static class BuilderExtender
    {

        public Notification build(Builder builder, NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            Notification notification;
            RemoteViews remoteviews;
            if(builder.mStyle != null)
                remoteviews = builder.mStyle.makeContentView(notificationbuilderwithbuilderaccessor);
            else
                remoteviews = null;
            notification = notificationbuilderwithbuilderaccessor.build();
            if(remoteviews == null) goto _L2; else goto _L1
_L1:
            notification.contentView = remoteviews;
_L4:
            if(android.os.Build.VERSION.SDK_INT >= 16 && builder.mStyle != null)
            {
                remoteviews = builder.mStyle.makeBigContentView(notificationbuilderwithbuilderaccessor);
                if(remoteviews != null)
                    notification.bigContentView = remoteviews;
            }
            if(android.os.Build.VERSION.SDK_INT >= 21 && builder.mStyle != null)
            {
                builder = builder.mStyle.makeHeadsUpContentView(notificationbuilderwithbuilderaccessor);
                if(builder != null)
                    notification.headsUpContentView = builder;
            }
            return notification;
_L2:
            if(builder.mContentView != null)
                notification.contentView = builder.mContentView;
            if(true) goto _L4; else goto _L3
_L3:
        }

        protected BuilderExtender()
        {
        }
    }

    public static final class CarExtender
        implements Extender
    {

        public Builder extend(Builder builder)
        {
            if(android.os.Build.VERSION.SDK_INT < 21)
                return builder;
            Bundle bundle = new Bundle();
            if(mLargeIcon != null)
                bundle.putParcelable("large_icon", mLargeIcon);
            if(mColor != 0)
                bundle.putInt("app_color", mColor);
            if(mUnreadConversation != null)
                bundle.putBundle("car_conversation", NotificationCompat.IMPL.getBundleForUnreadConversation(mUnreadConversation));
            builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
            return builder;
        }

        public int getColor()
        {
            return mColor;
        }

        public Bitmap getLargeIcon()
        {
            return mLargeIcon;
        }

        public UnreadConversation getUnreadConversation()
        {
            return mUnreadConversation;
        }

        public CarExtender setColor(int i)
        {
            mColor = i;
            return this;
        }

        public CarExtender setLargeIcon(Bitmap bitmap)
        {
            mLargeIcon = bitmap;
            return this;
        }

        public CarExtender setUnreadConversation(UnreadConversation unreadconversation)
        {
            mUnreadConversation = unreadconversation;
            return this;
        }

        private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String TAG = "CarExtender";
        private int mColor;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public CarExtender()
        {
            mColor = 0;
        }

        public CarExtender(Notification notification)
        {
            mColor = 0;
            if(android.os.Build.VERSION.SDK_INT >= 21)
            {
                if(NotificationCompat.getExtras(notification) == null)
                    notification = null;
                else
                    notification = NotificationCompat.getExtras(notification).getBundle("android.car.EXTENSIONS");
                if(notification != null)
                {
                    mLargeIcon = (Bitmap)notification.getParcelable("large_icon");
                    mColor = notification.getInt("app_color", 0);
                    notification = notification.getBundle("car_conversation");
                    mUnreadConversation = (UnreadConversation)NotificationCompat.IMPL.getUnreadConversationFromBundle(notification, UnreadConversation.FACTORY, RemoteInput.FACTORY);
                    return;
                }
            }
        }
    }

    public static class CarExtender.UnreadConversation extends NotificationCompatBase.UnreadConversation
    {

        public long getLatestTimestamp()
        {
            return mLatestTimestamp;
        }

        public String[] getMessages()
        {
            return mMessages;
        }

        public String getParticipant()
        {
            if(mParticipants.length > 0)
                return mParticipants[0];
            else
                return null;
        }

        public String[] getParticipants()
        {
            return mParticipants;
        }

        public PendingIntent getReadPendingIntent()
        {
            return mReadPendingIntent;
        }

        public RemoteInput getRemoteInput()
        {
            return mRemoteInput;
        }

        public volatile RemoteInputCompatBase.RemoteInput getRemoteInput()
        {
            return getRemoteInput();
        }

        public PendingIntent getReplyPendingIntent()
        {
            return mReplyPendingIntent;
        }

        static final NotificationCompatBase.UnreadConversation.Factory FACTORY = new NotificationCompatBase.UnreadConversation.Factory() {

            public CarExtender.UnreadConversation build(String as[], RemoteInputCompatBase.RemoteInput remoteinput, PendingIntent pendingintent, PendingIntent pendingintent1, String as1[], long l)
            {
                return new CarExtender.UnreadConversation(as, (RemoteInput)remoteinput, pendingintent, pendingintent1, as1, l);
            }

            public volatile NotificationCompatBase.UnreadConversation build(String as[], RemoteInputCompatBase.RemoteInput remoteinput, PendingIntent pendingintent, PendingIntent pendingintent1, String as1[], long l)
            {
                return build(as, remoteinput, pendingintent, pendingintent1, as1, l);
            }

        }
;
        private final long mLatestTimestamp;
        private final String mMessages[];
        private final String mParticipants[];
        private final PendingIntent mReadPendingIntent;
        private final RemoteInput mRemoteInput;
        private final PendingIntent mReplyPendingIntent;


        CarExtender.UnreadConversation(String as[], RemoteInput remoteinput, PendingIntent pendingintent, PendingIntent pendingintent1, String as1[], long l)
        {
            mMessages = as;
            mRemoteInput = remoteinput;
            mReadPendingIntent = pendingintent1;
            mReplyPendingIntent = pendingintent;
            mParticipants = as1;
            mLatestTimestamp = l;
        }
    }

    public static class CarExtender.UnreadConversation.Builder
    {

        public CarExtender.UnreadConversation.Builder addMessage(String s)
        {
            mMessages.add(s);
            return this;
        }

        public CarExtender.UnreadConversation build()
        {
            String as[] = (String[])mMessages.toArray(new String[mMessages.size()]);
            String s = mParticipant;
            RemoteInput remoteinput = mRemoteInput;
            PendingIntent pendingintent = mReplyPendingIntent;
            PendingIntent pendingintent1 = mReadPendingIntent;
            long l = mLatestTimestamp;
            return new CarExtender.UnreadConversation(as, remoteinput, pendingintent, pendingintent1, new String[] {
                s
            }, l);
        }

        public CarExtender.UnreadConversation.Builder setLatestTimestamp(long l)
        {
            mLatestTimestamp = l;
            return this;
        }

        public CarExtender.UnreadConversation.Builder setReadPendingIntent(PendingIntent pendingintent)
        {
            mReadPendingIntent = pendingintent;
            return this;
        }

        public CarExtender.UnreadConversation.Builder setReplyAction(PendingIntent pendingintent, RemoteInput remoteinput)
        {
            mRemoteInput = remoteinput;
            mReplyPendingIntent = pendingintent;
            return this;
        }

        private long mLatestTimestamp;
        private final List mMessages = new ArrayList();
        private final String mParticipant;
        private PendingIntent mReadPendingIntent;
        private RemoteInput mRemoteInput;
        private PendingIntent mReplyPendingIntent;

        public CarExtender.UnreadConversation.Builder(String s)
        {
            mParticipant = s;
        }
    }

    public static class DecoratedCustomViewStyle extends Style
    {

        private RemoteViews createRemoteViews(RemoteViews remoteviews, boolean flag)
        {
            boolean flag3 = false;
            RemoteViews remoteviews1 = applyStandardTemplate(true, android.support.compat.R.layout.notification_template_custom_big, false);
            remoteviews1.removeAllViews(android.support.compat.R.id.actions);
            boolean flag2 = false;
            boolean flag1 = flag2;
            if(flag)
            {
                flag1 = flag2;
                if(mBuilder.mActions != null)
                {
                    int k = Math.min(mBuilder.mActions.size(), 3);
                    flag1 = flag2;
                    if(k > 0)
                    {
                        boolean flag4 = true;
                        int j = 0;
                        do
                        {
                            flag1 = flag4;
                            if(j >= k)
                                break;
                            RemoteViews remoteviews2 = generateActionButton((Action)mBuilder.mActions.get(j));
                            remoteviews1.addView(android.support.compat.R.id.actions, remoteviews2);
                            j++;
                        } while(true);
                    }
                }
            }
            int i;
            if(flag1)
                i = ((flag3) ? 1 : 0);
            else
                i = 8;
            remoteviews1.setViewVisibility(android.support.compat.R.id.actions, i);
            remoteviews1.setViewVisibility(android.support.compat.R.id.action_divider, i);
            buildIntoRemoteViews(remoteviews1, remoteviews);
            return remoteviews1;
        }

        private RemoteViews generateActionButton(Action action)
        {
            boolean flag;
            int i;
            Object obj;
            if(action.actionIntent == null)
                flag = true;
            else
                flag = false;
            obj = mBuilder.mContext.getPackageName();
            if(flag)
                i = android.support.compat.R.layout.notification_action_tombstone;
            else
                i = android.support.compat.R.layout.notification_action;
            obj = new RemoteViews(((String) (obj)), i);
            ((RemoteViews) (obj)).setImageViewBitmap(android.support.compat.R.id.action_image, createColoredBitmap(action.getIcon(), mBuilder.mContext.getResources().getColor(android.support.compat.R.color.notification_action_color_filter)));
            ((RemoteViews) (obj)).setTextViewText(android.support.compat.R.id.action_text, action.title);
            if(!flag)
                ((RemoteViews) (obj)).setOnClickPendingIntent(android.support.compat.R.id.action_container, action.actionIntent);
            if(android.os.Build.VERSION.SDK_INT >= 15)
                ((RemoteViews) (obj)).setContentDescription(android.support.compat.R.id.action_container, action.title);
            return ((RemoteViews) (obj));
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT >= 24)
                notificationbuilderwithbuilderaccessor.getBuilder().setStyle(new android.app.Notification.DecoratedCustomViewStyle());
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT < 24)
            {
                notificationbuilderwithbuilderaccessor = mBuilder.getBigContentView();
                if(notificationbuilderwithbuilderaccessor == null)
                    notificationbuilderwithbuilderaccessor = mBuilder.getContentView();
                if(notificationbuilderwithbuilderaccessor != null)
                    return createRemoteViews(notificationbuilderwithbuilderaccessor, true);
            }
            return null;
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            while(android.os.Build.VERSION.SDK_INT >= 24 || mBuilder.getContentView() == null) 
                return null;
            return createRemoteViews(mBuilder.getContentView(), false);
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT < 24)
            {
                RemoteViews remoteviews = mBuilder.getHeadsUpContentView();
                if(remoteviews != null)
                    notificationbuilderwithbuilderaccessor = remoteviews;
                else
                    notificationbuilderwithbuilderaccessor = mBuilder.getContentView();
                if(remoteviews != null)
                    return createRemoteViews(notificationbuilderwithbuilderaccessor, true);
            }
            return null;
        }

        private static final int MAX_ACTION_BUTTONS = 3;

        public DecoratedCustomViewStyle()
        {
        }
    }

    public static interface Extender
    {

        public abstract Builder extend(Builder builder);
    }

    public static class InboxStyle extends Style
    {

        public InboxStyle addLine(CharSequence charsequence)
        {
            mTexts.add(Builder.limitCharSequenceLength(charsequence));
            return this;
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT >= 16)
                NotificationCompatJellybean.addInboxStyle(notificationbuilderwithbuilderaccessor, mBigContentTitle, mSummaryTextSet, mSummaryText, mTexts);
        }

        public InboxStyle setBigContentTitle(CharSequence charsequence)
        {
            mBigContentTitle = Builder.limitCharSequenceLength(charsequence);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charsequence)
        {
            mSummaryText = Builder.limitCharSequenceLength(charsequence);
            mSummaryTextSet = true;
            return this;
        }

        private ArrayList mTexts;

        public InboxStyle()
        {
            mTexts = new ArrayList();
        }

        public InboxStyle(Builder builder)
        {
            mTexts = new ArrayList();
            setBuilder(builder);
        }
    }

    public static class MessagingStyle extends Style
    {

        public static MessagingStyle extractMessagingStyleFromNotification(Notification notification)
        {
            notification = NotificationCompat.getExtras(notification);
            if(notification != null && !notification.containsKey("android.selfDisplayName"))
                return null;
            MessagingStyle messagingstyle;
            try
            {
                messagingstyle = new MessagingStyle();
                messagingstyle.restoreFromCompatExtras(notification);
            }
            // Misplaced declaration of an exception variable
            catch(Notification notification)
            {
                return null;
            }
            return messagingstyle;
        }

        private Message findLatestIncomingMessage()
        {
            for(int i = mMessages.size() - 1; i >= 0; i--)
            {
                Message message = (Message)mMessages.get(i);
                if(!TextUtils.isEmpty(message.getSender()))
                    return message;
            }

            if(!mMessages.isEmpty())
                return (Message)mMessages.get(mMessages.size() - 1);
            else
                return null;
        }

        private boolean hasMessagesWithoutSender()
        {
            for(int i = mMessages.size() - 1; i >= 0; i--)
                if(((Message)mMessages.get(i)).getSender() == null)
                    return true;

            return false;
        }

        private TextAppearanceSpan makeFontColorSpan(int i)
        {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i), null);
        }

        private CharSequence makeMessageLine(Message message)
        {
            BidiFormatter bidiformatter = BidiFormatter.getInstance();
            SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
            int i;
            boolean flag;
            int j;
            Object obj1;
            if(android.os.Build.VERSION.SDK_INT >= 21)
                flag = true;
            else
                flag = false;
            if(flag)
                i = 0xff000000;
            else
                i = -1;
            obj1 = message.getSender();
            j = i;
            if(TextUtils.isEmpty(message.getSender()))
            {
                Object obj;
                if(mUserDisplayName == null)
                    obj = "";
                else
                    obj = mUserDisplayName;
                j = i;
                obj1 = obj;
                if(flag)
                {
                    j = i;
                    obj1 = obj;
                    if(mBuilder.getColor() != 0)
                    {
                        j = mBuilder.getColor();
                        obj1 = obj;
                    }
                }
            }
            obj = bidiformatter.unicodeWrap(((CharSequence) (obj1)));
            spannablestringbuilder.append(((CharSequence) (obj)));
            spannablestringbuilder.setSpan(makeFontColorSpan(j), spannablestringbuilder.length() - ((CharSequence) (obj)).length(), spannablestringbuilder.length(), 33);
            if(message.getText() == null)
                message = "";
            else
                message = message.getText();
            spannablestringbuilder.append("  ").append(bidiformatter.unicodeWrap(message));
            return spannablestringbuilder;
        }

        public void addCompatExtras(Bundle bundle)
        {
            super.addCompatExtras(bundle);
            if(mUserDisplayName != null)
                bundle.putCharSequence("android.selfDisplayName", mUserDisplayName);
            if(mConversationTitle != null)
                bundle.putCharSequence("android.conversationTitle", mConversationTitle);
            if(!mMessages.isEmpty())
                bundle.putParcelableArray("android.messages", Message.getBundleArrayForMessages(mMessages));
        }

        public MessagingStyle addMessage(Message message)
        {
            mMessages.add(message);
            if(mMessages.size() > 25)
                mMessages.remove(0);
            return this;
        }

        public MessagingStyle addMessage(CharSequence charsequence, long l, CharSequence charsequence1)
        {
            mMessages.add(new Message(charsequence, l, charsequence1));
            if(mMessages.size() > 25)
                mMessages.remove(0);
            return this;
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            if(android.os.Build.VERSION.SDK_INT >= 24)
            {
                ArrayList arraylist = new ArrayList();
                ArrayList arraylist1 = new ArrayList();
                ArrayList arraylist2 = new ArrayList();
                ArrayList arraylist3 = new ArrayList();
                ArrayList arraylist4 = new ArrayList();
                Message message;
                for(Iterator iterator = mMessages.iterator(); iterator.hasNext(); arraylist4.add(message.getDataUri()))
                {
                    message = (Message)iterator.next();
                    arraylist.add(message.getText());
                    arraylist1.add(Long.valueOf(message.getTimestamp()));
                    arraylist2.add(message.getSender());
                    arraylist3.add(message.getDataMimeType());
                }

                NotificationCompatApi24.addMessagingStyle(notificationbuilderwithbuilderaccessor, mUserDisplayName, mConversationTitle, arraylist, arraylist1, arraylist2, arraylist3, arraylist4);
            } else
            {
                Object obj = findLatestIncomingMessage();
                if(mConversationTitle != null)
                    notificationbuilderwithbuilderaccessor.getBuilder().setContentTitle(mConversationTitle);
                else
                if(obj != null)
                    notificationbuilderwithbuilderaccessor.getBuilder().setContentTitle(((Message) (obj)).getSender());
                if(obj != null)
                {
                    android.app.Notification.Builder builder = notificationbuilderwithbuilderaccessor.getBuilder();
                    int i;
                    if(mConversationTitle != null)
                        obj = makeMessageLine(((Message) (obj)));
                    else
                        obj = ((Message) (obj)).getText();
                    builder.setContentText(((CharSequence) (obj)));
                }
                if(android.os.Build.VERSION.SDK_INT >= 16)
                {
                    SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
                    boolean flag;
                    if(mConversationTitle != null || hasMessagesWithoutSender())
                        flag = true;
                    else
                        flag = false;
                    i = mMessages.size() - 1;
                    while(i >= 0) 
                    {
                        obj = (Message)mMessages.get(i);
                        if(flag)
                            obj = makeMessageLine(((Message) (obj)));
                        else
                            obj = ((Message) (obj)).getText();
                        if(i != mMessages.size() - 1)
                            spannablestringbuilder.insert(0, "\n");
                        spannablestringbuilder.insert(0, ((CharSequence) (obj)));
                        i--;
                    }
                    NotificationCompatJellybean.addBigTextStyle(notificationbuilderwithbuilderaccessor, null, false, null, spannablestringbuilder);
                    return;
                }
            }
        }

        public CharSequence getConversationTitle()
        {
            return mConversationTitle;
        }

        public List getMessages()
        {
            return mMessages;
        }

        public CharSequence getUserDisplayName()
        {
            return mUserDisplayName;
        }

        protected void restoreFromCompatExtras(Bundle bundle)
        {
            mMessages.clear();
            mUserDisplayName = bundle.getString("android.selfDisplayName");
            mConversationTitle = bundle.getString("android.conversationTitle");
            bundle = bundle.getParcelableArray("android.messages");
            if(bundle != null)
                mMessages = Message.getMessagesFromBundleArray(bundle);
        }

        public MessagingStyle setConversationTitle(CharSequence charsequence)
        {
            mConversationTitle = charsequence;
            return this;
        }

        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        CharSequence mConversationTitle;
        List mMessages;
        CharSequence mUserDisplayName;

        MessagingStyle()
        {
            mMessages = new ArrayList();
        }

        public MessagingStyle(CharSequence charsequence)
        {
            mMessages = new ArrayList();
            mUserDisplayName = charsequence;
        }
    }

    public static final class MessagingStyle.Message
    {

        static Bundle[] getBundleArrayForMessages(List list)
        {
            Bundle abundle[] = new Bundle[list.size()];
            int j = list.size();
            for(int i = 0; i < j; i++)
                abundle[i] = ((MessagingStyle.Message)list.get(i)).toBundle();

            return abundle;
        }

        static MessagingStyle.Message getMessageFromBundle(Bundle bundle)
        {
            MessagingStyle.Message message;
            if(!bundle.containsKey("text") || !bundle.containsKey("time"))
                break MISSING_BLOCK_LABEL_114;
            MessagingStyle.Message message1;
            try
            {
                message1 = new MessagingStyle.Message(bundle.getCharSequence("text"), bundle.getLong("time"), bundle.getCharSequence("sender"));
                if(bundle.containsKey("type") && bundle.containsKey("uri"))
                    message1.setData(bundle.getString("type"), (Uri)bundle.getParcelable("uri"));
            }
            // Misplaced declaration of an exception variable
            catch(Bundle bundle)
            {
                return null;
            }
            message = message1;
            if(!bundle.containsKey("extras"))
                break MISSING_BLOCK_LABEL_116;
            message1.getExtras().putAll(bundle.getBundle("extras"));
            return message1;
            message = null;
            return message;
        }

        static List getMessagesFromBundleArray(Parcelable aparcelable[])
        {
            ArrayList arraylist = new ArrayList(aparcelable.length);
            for(int i = 0; i < aparcelable.length; i++)
            {
                if(!(aparcelable[i] instanceof Bundle))
                    continue;
                MessagingStyle.Message message = getMessageFromBundle((Bundle)aparcelable[i]);
                if(message != null)
                    arraylist.add(message);
            }

            return arraylist;
        }

        private Bundle toBundle()
        {
            Bundle bundle = new Bundle();
            if(mText != null)
                bundle.putCharSequence("text", mText);
            bundle.putLong("time", mTimestamp);
            if(mSender != null)
                bundle.putCharSequence("sender", mSender);
            if(mDataMimeType != null)
                bundle.putString("type", mDataMimeType);
            if(mDataUri != null)
                bundle.putParcelable("uri", mDataUri);
            if(mExtras != null)
                bundle.putBundle("extras", mExtras);
            return bundle;
        }

        public String getDataMimeType()
        {
            return mDataMimeType;
        }

        public Uri getDataUri()
        {
            return mDataUri;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public CharSequence getSender()
        {
            return mSender;
        }

        public CharSequence getText()
        {
            return mText;
        }

        public long getTimestamp()
        {
            return mTimestamp;
        }

        public MessagingStyle.Message setData(String s, Uri uri)
        {
            mDataMimeType = s;
            mDataUri = uri;
            return this;
        }

        static final String KEY_DATA_MIME_TYPE = "type";
        static final String KEY_DATA_URI = "uri";
        static final String KEY_EXTRAS_BUNDLE = "extras";
        static final String KEY_SENDER = "sender";
        static final String KEY_TEXT = "text";
        static final String KEY_TIMESTAMP = "time";
        private String mDataMimeType;
        private Uri mDataUri;
        private Bundle mExtras;
        private final CharSequence mSender;
        private final CharSequence mText;
        private final long mTimestamp;

        public MessagingStyle.Message(CharSequence charsequence, long l, CharSequence charsequence1)
        {
            mExtras = new Bundle();
            mText = charsequence;
            mTimestamp = l;
            mSender = charsequence1;
        }
    }

    static class NotificationCompatApi16Impl extends NotificationCompatBaseImpl
    {

        public Notification build(Builder builder, BuilderExtender builderextender)
        {
            NotificationCompatJellybean.Builder builder1 = new NotificationCompatJellybean.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey, builder.mContentView, builder.mBigContentView);
            NotificationCompat.addActionsToBuilder(builder1, builder.mActions);
            if(builder.mStyle != null)
                builder.mStyle.apply(builder1);
            builderextender = builderextender.build(builder, builder1);
            if(builder.mStyle != null)
            {
                Bundle bundle = NotificationCompat.getExtras(builderextender);
                if(bundle != null)
                    builder.mStyle.addCompatExtras(bundle);
            }
            return builderextender;
        }

        public Action getAction(Notification notification, int i)
        {
            return (Action)NotificationCompatJellybean.getAction(notification, i, Action.FACTORY, RemoteInput.FACTORY);
        }

        public Action[] getActionsFromParcelableArrayList(ArrayList arraylist)
        {
            return (Action[])(Action[])NotificationCompatJellybean.getActionsFromParcelableArrayList(arraylist, Action.FACTORY, RemoteInput.FACTORY);
        }

        public ArrayList getParcelableArrayListForActions(Action aaction[])
        {
            return NotificationCompatJellybean.getParcelableArrayListForActions(aaction);
        }

        NotificationCompatApi16Impl()
        {
        }
    }

    static class NotificationCompatApi19Impl extends NotificationCompatApi16Impl
    {

        public Notification build(Builder builder, BuilderExtender builderextender)
        {
            NotificationCompatKitKat.Builder builder1 = new NotificationCompatKitKat.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mPeople, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey, builder.mContentView, builder.mBigContentView);
            NotificationCompat.addActionsToBuilder(builder1, builder.mActions);
            if(builder.mStyle != null)
                builder.mStyle.apply(builder1);
            return builderextender.build(builder, builder1);
        }

        public Action getAction(Notification notification, int i)
        {
            return (Action)NotificationCompatKitKat.getAction(notification, i, Action.FACTORY, RemoteInput.FACTORY);
        }

        NotificationCompatApi19Impl()
        {
        }
    }

    static class NotificationCompatApi20Impl extends NotificationCompatApi19Impl
    {

        public Notification build(Builder builder, BuilderExtender builderextender)
        {
            NotificationCompatApi20.Builder builder1 = new NotificationCompatApi20.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mPeople, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey, builder.mContentView, builder.mBigContentView, builder.mGroupAlertBehavior);
            NotificationCompat.addActionsToBuilder(builder1, builder.mActions);
            if(builder.mStyle != null)
                builder.mStyle.apply(builder1);
            builderextender = builderextender.build(builder, builder1);
            if(builder.mStyle != null)
                builder.mStyle.addCompatExtras(NotificationCompat.getExtras(builderextender));
            return builderextender;
        }

        public Action getAction(Notification notification, int i)
        {
            return (Action)NotificationCompatApi20.getAction(notification, i, Action.FACTORY, RemoteInput.FACTORY);
        }

        public Action[] getActionsFromParcelableArrayList(ArrayList arraylist)
        {
            return (Action[])(Action[])NotificationCompatApi20.getActionsFromParcelableArrayList(arraylist, Action.FACTORY, RemoteInput.FACTORY);
        }

        public ArrayList getParcelableArrayListForActions(Action aaction[])
        {
            return NotificationCompatApi20.getParcelableArrayListForActions(aaction);
        }

        NotificationCompatApi20Impl()
        {
        }
    }

    static class NotificationCompatApi21Impl extends NotificationCompatApi20Impl
    {

        public Notification build(Builder builder, BuilderExtender builderextender)
        {
            NotificationCompatApi21.Builder builder1 = new NotificationCompatApi21.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mCategory, builder.mPeople, builder.mExtras, builder.mColor, builder.mVisibility, builder.mPublicVersion, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey, builder.mContentView, builder.mBigContentView, builder.mHeadsUpContentView, builder.mGroupAlertBehavior);
            NotificationCompat.addActionsToBuilder(builder1, builder.mActions);
            if(builder.mStyle != null)
                builder.mStyle.apply(builder1);
            builderextender = builderextender.build(builder, builder1);
            if(builder.mStyle != null)
                builder.mStyle.addCompatExtras(NotificationCompat.getExtras(builderextender));
            return builderextender;
        }

        public Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation unreadconversation)
        {
            return NotificationCompatApi21.getBundleForUnreadConversation(unreadconversation);
        }

        public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle bundle, NotificationCompatBase.UnreadConversation.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
        {
            return NotificationCompatApi21.getUnreadConversationFromBundle(bundle, factory, factory1);
        }

        NotificationCompatApi21Impl()
        {
        }
    }

    static class NotificationCompatApi24Impl extends NotificationCompatApi21Impl
    {

        public Notification build(Builder builder, BuilderExtender builderextender)
        {
            NotificationCompatApi24.Builder builder1 = new NotificationCompatApi24.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mCategory, builder.mPeople, builder.mExtras, builder.mColor, builder.mVisibility, builder.mPublicVersion, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey, builder.mRemoteInputHistory, builder.mContentView, builder.mBigContentView, builder.mHeadsUpContentView, builder.mGroupAlertBehavior);
            NotificationCompat.addActionsToBuilder(builder1, builder.mActions);
            if(builder.mStyle != null)
                builder.mStyle.apply(builder1);
            builderextender = builderextender.build(builder, builder1);
            if(builder.mStyle != null)
                builder.mStyle.addCompatExtras(NotificationCompat.getExtras(builderextender));
            return builderextender;
        }

        public Action getAction(Notification notification, int i)
        {
            return (Action)NotificationCompatApi24.getAction(notification, i, Action.FACTORY, RemoteInput.FACTORY);
        }

        public Action[] getActionsFromParcelableArrayList(ArrayList arraylist)
        {
            return (Action[])(Action[])NotificationCompatApi24.getActionsFromParcelableArrayList(arraylist, Action.FACTORY, RemoteInput.FACTORY);
        }

        public ArrayList getParcelableArrayListForActions(Action aaction[])
        {
            return NotificationCompatApi24.getParcelableArrayListForActions(aaction);
        }

        NotificationCompatApi24Impl()
        {
        }
    }

    static class NotificationCompatApi26Impl extends NotificationCompatApi24Impl
    {

        public Notification build(Builder builder, BuilderExtender builderextender)
        {
            NotificationCompatApi26.Builder builder1 = new NotificationCompatApi26.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mCategory, builder.mPeople, builder.mExtras, builder.mColor, builder.mVisibility, builder.mPublicVersion, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey, builder.mRemoteInputHistory, builder.mContentView, builder.mBigContentView, builder.mHeadsUpContentView, builder.mChannelId, builder.mBadgeIcon, builder.mShortcutId, builder.mTimeout, builder.mColorized, builder.mColorizedSet, builder.mGroupAlertBehavior);
            NotificationCompat.addActionsToBuilder(builder1, builder.mActions);
            if(builder.mStyle != null)
                builder.mStyle.apply(builder1);
            builderextender = builderextender.build(builder, builder1);
            if(builder.mStyle != null)
                builder.mStyle.addCompatExtras(NotificationCompat.getExtras(builderextender));
            return builderextender;
        }

        NotificationCompatApi26Impl()
        {
        }
    }

    static class NotificationCompatBaseImpl
        implements NotificationCompatImpl
    {

        public Notification build(Builder builder, BuilderExtender builderextender)
        {
            return builderextender.build(builder, new BuilderBase(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate));
        }

        public Action getAction(Notification notification, int i)
        {
            return null;
        }

        public Action[] getActionsFromParcelableArrayList(ArrayList arraylist)
        {
            return null;
        }

        public Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation unreadconversation)
        {
            return null;
        }

        public ArrayList getParcelableArrayListForActions(Action aaction[])
        {
            return null;
        }

        public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle bundle, NotificationCompatBase.UnreadConversation.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
        {
            return null;
        }

        NotificationCompatBaseImpl()
        {
        }
    }

    public static class NotificationCompatBaseImpl.BuilderBase
        implements NotificationBuilderWithBuilderAccessor
    {

        public Notification build()
        {
            return mBuilder.getNotification();
        }

        public android.app.Notification.Builder getBuilder()
        {
            return mBuilder;
        }

        private android.app.Notification.Builder mBuilder;

        NotificationCompatBaseImpl.BuilderBase(Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, 
                PendingIntent pendingintent, PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag)
        {
            context = (new android.app.Notification.Builder(context)).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteviews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            boolean flag1;
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
            context = context.setAutoCancel(flag1).setDefaults(notification.defaults).setContentTitle(charsequence).setContentText(charsequence1).setContentInfo(charsequence2).setContentIntent(pendingintent).setDeleteIntent(notification.deleteIntent);
            if((notification.flags & 0x80) != 0)
                flag1 = true;
            else
                flag1 = false;
            mBuilder = context.setFullScreenIntent(pendingintent1, flag1).setLargeIcon(bitmap).setNumber(i).setProgress(j, k, flag);
        }
    }

    static interface NotificationCompatImpl
    {

        public abstract Notification build(Builder builder, BuilderExtender builderextender);

        public abstract Action getAction(Notification notification, int i);

        public abstract Action[] getActionsFromParcelableArrayList(ArrayList arraylist);

        public abstract Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation unreadconversation);

        public abstract ArrayList getParcelableArrayListForActions(Action aaction[]);

        public abstract NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle bundle, NotificationCompatBase.UnreadConversation.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1);
    }

    public static interface NotificationVisibility
        extends Annotation
    {
    }

    public static abstract class Style
    {

        private int calculateTopPadding()
        {
            Resources resources = mBuilder.mContext.getResources();
            int i = resources.getDimensionPixelSize(android.support.compat.R.dimen.notification_top_pad);
            int j = resources.getDimensionPixelSize(android.support.compat.R.dimen.notification_top_pad_large_text);
            float f = (constrain(resources.getConfiguration().fontScale, 1.0F, 1.3F) - 1.0F) / 0.3F;
            return Math.round((1.0F - f) * (float)i + (float)j * f);
        }

        private static float constrain(float f, float f1, float f2)
        {
            if(f < f1)
                return f1;
            if(f > f2)
                return f2;
            else
                return f;
        }

        private Bitmap createColoredBitmap(int i, int j, int k)
        {
            Drawable drawable = mBuilder.mContext.getResources().getDrawable(i);
            Bitmap bitmap;
            if(k == 0)
                i = drawable.getIntrinsicWidth();
            else
                i = k;
            if(k == 0)
                k = drawable.getIntrinsicHeight();
            bitmap = Bitmap.createBitmap(i, k, android.graphics.Bitmap.Config.ARGB_8888);
            drawable.setBounds(0, 0, i, k);
            if(j != 0)
                drawable.mutate().setColorFilter(new PorterDuffColorFilter(j, android.graphics.PorterDuff.Mode.SRC_IN));
            drawable.draw(new Canvas(bitmap));
            return bitmap;
        }

        private Bitmap createIconWithBackground(int i, int j, int k, int l)
        {
            int j1 = android.support.compat.R.drawable.notification_icon_background;
            int i1 = l;
            if(l == 0)
                i1 = 0;
            Bitmap bitmap = createColoredBitmap(j1, i1, j);
            Canvas canvas = new Canvas(bitmap);
            Drawable drawable = mBuilder.mContext.getResources().getDrawable(i).mutate();
            drawable.setFilterBitmap(true);
            i = (j - k) / 2;
            drawable.setBounds(i, i, k + i, k + i);
            drawable.setColorFilter(new PorterDuffColorFilter(-1, android.graphics.PorterDuff.Mode.SRC_ATOP));
            drawable.draw(canvas);
            return bitmap;
        }

        private void hideNormalContent(RemoteViews remoteviews)
        {
            remoteviews.setViewVisibility(android.support.compat.R.id.title, 8);
            remoteviews.setViewVisibility(android.support.compat.R.id.text2, 8);
            remoteviews.setViewVisibility(android.support.compat.R.id.text, 8);
        }

        public void addCompatExtras(Bundle bundle)
        {
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
        }

        public RemoteViews applyStandardTemplate(boolean flag, int i, boolean flag1)
        {
            Resources resources = mBuilder.mContext.getResources();
            RemoteViews remoteviews = new RemoteViews(mBuilder.mContext.getPackageName(), i);
            boolean flag2 = false;
            boolean flag3 = false;
            int j;
            if(mBuilder.getPriority() < -1)
                i = 1;
            else
                i = 0;
            if(android.os.Build.VERSION.SDK_INT >= 16 && android.os.Build.VERSION.SDK_INT < 21)
                if(i != 0)
                {
                    remoteviews.setInt(android.support.compat.R.id.notification_background, "setBackgroundResource", android.support.compat.R.drawable.notification_bg_low);
                    remoteviews.setInt(android.support.compat.R.id.icon, "setBackgroundResource", android.support.compat.R.drawable.notification_template_icon_low_bg);
                } else
                {
                    remoteviews.setInt(android.support.compat.R.id.notification_background, "setBackgroundResource", android.support.compat.R.drawable.notification_bg);
                    remoteviews.setInt(android.support.compat.R.id.icon, "setBackgroundResource", android.support.compat.R.drawable.notification_template_icon_bg);
                }
            if(mBuilder.mLargeIcon != null)
            {
                if(android.os.Build.VERSION.SDK_INT >= 16)
                {
                    remoteviews.setViewVisibility(android.support.compat.R.id.icon, 0);
                    remoteviews.setImageViewBitmap(android.support.compat.R.id.icon, mBuilder.mLargeIcon);
                } else
                {
                    remoteviews.setViewVisibility(android.support.compat.R.id.icon, 8);
                }
                if(flag && mBuilder.mNotification.icon != 0)
                {
                    i = resources.getDimensionPixelSize(android.support.compat.R.dimen.notification_right_icon_size);
                    j = resources.getDimensionPixelSize(android.support.compat.R.dimen.notification_small_icon_background_padding);
                    float f;
                    if(android.os.Build.VERSION.SDK_INT >= 21)
                    {
                        Bitmap bitmap = createIconWithBackground(mBuilder.mNotification.icon, i, i - j * 2, mBuilder.getColor());
                        remoteviews.setImageViewBitmap(android.support.compat.R.id.right_icon, bitmap);
                    } else
                    {
                        remoteviews.setImageViewBitmap(android.support.compat.R.id.right_icon, createColoredBitmap(mBuilder.mNotification.icon, -1));
                    }
                    remoteviews.setViewVisibility(android.support.compat.R.id.right_icon, 0);
                }
            } else
            if(flag && mBuilder.mNotification.icon != 0)
            {
                remoteviews.setViewVisibility(android.support.compat.R.id.icon, 0);
                if(android.os.Build.VERSION.SDK_INT >= 21)
                {
                    i = resources.getDimensionPixelSize(android.support.compat.R.dimen.notification_large_icon_width);
                    j = resources.getDimensionPixelSize(android.support.compat.R.dimen.notification_big_circle_margin);
                    int k = resources.getDimensionPixelSize(android.support.compat.R.dimen.notification_small_icon_size_as_large);
                    Bitmap bitmap1 = createIconWithBackground(mBuilder.mNotification.icon, i - j, k, mBuilder.getColor());
                    remoteviews.setImageViewBitmap(android.support.compat.R.id.icon, bitmap1);
                } else
                {
                    remoteviews.setImageViewBitmap(android.support.compat.R.id.icon, createColoredBitmap(mBuilder.mNotification.icon, -1));
                }
            }
            if(mBuilder.mContentTitle != null)
                remoteviews.setTextViewText(android.support.compat.R.id.title, mBuilder.mContentTitle);
            if(mBuilder.mContentText != null)
            {
                remoteviews.setTextViewText(android.support.compat.R.id.text, mBuilder.mContentText);
                flag2 = true;
            }
            if(android.os.Build.VERSION.SDK_INT < 21 && mBuilder.mLargeIcon != null)
                i = 1;
            else
                i = 0;
            if(mBuilder.mContentInfo != null)
            {
                remoteviews.setTextViewText(android.support.compat.R.id.info, mBuilder.mContentInfo);
                remoteviews.setViewVisibility(android.support.compat.R.id.info, 0);
                flag2 = true;
                i = 1;
            } else
            if(mBuilder.mNumber > 0)
            {
                i = resources.getInteger(android.support.compat.R.integer.status_bar_notification_info_maxnum);
                if(mBuilder.mNumber > i)
                {
                    remoteviews.setTextViewText(android.support.compat.R.id.info, resources.getString(android.support.compat.R.string.status_bar_notification_info_overflow));
                } else
                {
                    NumberFormat numberformat = NumberFormat.getIntegerInstance();
                    remoteviews.setTextViewText(android.support.compat.R.id.info, numberformat.format(mBuilder.mNumber));
                }
                remoteviews.setViewVisibility(android.support.compat.R.id.info, 0);
                flag2 = true;
                i = 1;
            } else
            {
                remoteviews.setViewVisibility(android.support.compat.R.id.info, 8);
            }
            j = ((flag3) ? 1 : 0);
            if(mBuilder.mSubText != null)
            {
                j = ((flag3) ? 1 : 0);
                if(android.os.Build.VERSION.SDK_INT >= 16)
                {
                    remoteviews.setTextViewText(android.support.compat.R.id.text, mBuilder.mSubText);
                    if(mBuilder.mContentText != null)
                    {
                        remoteviews.setTextViewText(android.support.compat.R.id.text2, mBuilder.mContentText);
                        remoteviews.setViewVisibility(android.support.compat.R.id.text2, 0);
                        j = 1;
                    } else
                    {
                        remoteviews.setViewVisibility(android.support.compat.R.id.text2, 8);
                        j = ((flag3) ? 1 : 0);
                    }
                }
            }
            if(j != 0 && android.os.Build.VERSION.SDK_INT >= 16)
            {
                if(flag1)
                {
                    f = resources.getDimensionPixelSize(android.support.compat.R.dimen.notification_subtext_size);
                    remoteviews.setTextViewTextSize(android.support.compat.R.id.text, 0, f);
                }
                remoteviews.setViewPadding(android.support.compat.R.id.line1, 0, 0, 0, 0);
            }
            if(mBuilder.getWhenIfShowing() != 0L)
            {
                if(mBuilder.mUseChronometer && android.os.Build.VERSION.SDK_INT >= 16)
                {
                    remoteviews.setViewVisibility(android.support.compat.R.id.chronometer, 0);
                    remoteviews.setLong(android.support.compat.R.id.chronometer, "setBase", mBuilder.getWhenIfShowing() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                    remoteviews.setBoolean(android.support.compat.R.id.chronometer, "setStarted", true);
                } else
                {
                    remoteviews.setViewVisibility(android.support.compat.R.id.time, 0);
                    remoteviews.setLong(android.support.compat.R.id.time, "setTime", mBuilder.getWhenIfShowing());
                }
                i = 1;
            }
            j = android.support.compat.R.id.right_side;
            if(i != 0)
                i = 0;
            else
                i = 8;
            remoteviews.setViewVisibility(j, i);
            j = android.support.compat.R.id.line3;
            if(flag2)
                i = 0;
            else
                i = 8;
            remoteviews.setViewVisibility(j, i);
            return remoteviews;
        }

        public Notification build()
        {
            Notification notification = null;
            if(mBuilder != null)
                notification = mBuilder.build();
            return notification;
        }

        public void buildIntoRemoteViews(RemoteViews remoteviews, RemoteViews remoteviews1)
        {
            hideNormalContent(remoteviews);
            remoteviews.removeAllViews(android.support.compat.R.id.notification_main_column);
            remoteviews.addView(android.support.compat.R.id.notification_main_column, remoteviews1.clone());
            remoteviews.setViewVisibility(android.support.compat.R.id.notification_main_column, 0);
            if(android.os.Build.VERSION.SDK_INT >= 21)
                remoteviews.setViewPadding(android.support.compat.R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
        }

        public Bitmap createColoredBitmap(int i, int j)
        {
            return createColoredBitmap(i, j, 0);
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            return null;
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            return null;
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
        {
            return null;
        }

        protected void restoreFromCompatExtras(Bundle bundle)
        {
        }

        public void setBuilder(Builder builder)
        {
            if(mBuilder != builder)
            {
                mBuilder = builder;
                if(mBuilder != null)
                    mBuilder.setStyle(this);
            }
        }

        CharSequence mBigContentTitle;
        protected Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet;

        public Style()
        {
            mSummaryTextSet = false;
        }
    }

    public static final class WearableExtender
        implements Extender
    {

        private void setFlag(int i, boolean flag)
        {
            if(flag)
            {
                mFlags = mFlags | i;
                return;
            } else
            {
                mFlags = mFlags & ~i;
                return;
            }
        }

        public WearableExtender addAction(Action action)
        {
            mActions.add(action);
            return this;
        }

        public WearableExtender addActions(List list)
        {
            mActions.addAll(list);
            return this;
        }

        public WearableExtender addPage(Notification notification)
        {
            mPages.add(notification);
            return this;
        }

        public WearableExtender addPages(List list)
        {
            mPages.addAll(list);
            return this;
        }

        public WearableExtender clearActions()
        {
            mActions.clear();
            return this;
        }

        public WearableExtender clearPages()
        {
            mPages.clear();
            return this;
        }

        public WearableExtender clone()
        {
            WearableExtender wearableextender = new WearableExtender();
            wearableextender.mActions = new ArrayList(mActions);
            wearableextender.mFlags = mFlags;
            wearableextender.mDisplayIntent = mDisplayIntent;
            wearableextender.mPages = new ArrayList(mPages);
            wearableextender.mBackground = mBackground;
            wearableextender.mContentIcon = mContentIcon;
            wearableextender.mContentIconGravity = mContentIconGravity;
            wearableextender.mContentActionIndex = mContentActionIndex;
            wearableextender.mCustomSizePreset = mCustomSizePreset;
            wearableextender.mCustomContentHeight = mCustomContentHeight;
            wearableextender.mGravity = mGravity;
            wearableextender.mHintScreenTimeout = mHintScreenTimeout;
            wearableextender.mDismissalId = mDismissalId;
            wearableextender.mBridgeTag = mBridgeTag;
            return wearableextender;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Builder extend(Builder builder)
        {
            Bundle bundle = new Bundle();
            if(!mActions.isEmpty())
                bundle.putParcelableArrayList("actions", NotificationCompat.IMPL.getParcelableArrayListForActions((Action[])mActions.toArray(new Action[mActions.size()])));
            if(mFlags != 1)
                bundle.putInt("flags", mFlags);
            if(mDisplayIntent != null)
                bundle.putParcelable("displayIntent", mDisplayIntent);
            if(!mPages.isEmpty())
                bundle.putParcelableArray("pages", (Parcelable[])mPages.toArray(new Notification[mPages.size()]));
            if(mBackground != null)
                bundle.putParcelable("background", mBackground);
            if(mContentIcon != 0)
                bundle.putInt("contentIcon", mContentIcon);
            if(mContentIconGravity != 0x800005)
                bundle.putInt("contentIconGravity", mContentIconGravity);
            if(mContentActionIndex != -1)
                bundle.putInt("contentActionIndex", mContentActionIndex);
            if(mCustomSizePreset != 0)
                bundle.putInt("customSizePreset", mCustomSizePreset);
            if(mCustomContentHeight != 0)
                bundle.putInt("customContentHeight", mCustomContentHeight);
            if(mGravity != 80)
                bundle.putInt("gravity", mGravity);
            if(mHintScreenTimeout != 0)
                bundle.putInt("hintScreenTimeout", mHintScreenTimeout);
            if(mDismissalId != null)
                bundle.putString("dismissalId", mDismissalId);
            if(mBridgeTag != null)
                bundle.putString("bridgeTag", mBridgeTag);
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }

        public List getActions()
        {
            return mActions;
        }

        public Bitmap getBackground()
        {
            return mBackground;
        }

        public String getBridgeTag()
        {
            return mBridgeTag;
        }

        public int getContentAction()
        {
            return mContentActionIndex;
        }

        public int getContentIcon()
        {
            return mContentIcon;
        }

        public int getContentIconGravity()
        {
            return mContentIconGravity;
        }

        public boolean getContentIntentAvailableOffline()
        {
            return (mFlags & 1) != 0;
        }

        public int getCustomContentHeight()
        {
            return mCustomContentHeight;
        }

        public int getCustomSizePreset()
        {
            return mCustomSizePreset;
        }

        public String getDismissalId()
        {
            return mDismissalId;
        }

        public PendingIntent getDisplayIntent()
        {
            return mDisplayIntent;
        }

        public int getGravity()
        {
            return mGravity;
        }

        public boolean getHintAmbientBigPicture()
        {
            return (mFlags & 0x20) != 0;
        }

        public boolean getHintAvoidBackgroundClipping()
        {
            return (mFlags & 0x10) != 0;
        }

        public boolean getHintContentIntentLaunchesActivity()
        {
            return (mFlags & 0x40) != 0;
        }

        public boolean getHintHideIcon()
        {
            return (mFlags & 2) != 0;
        }

        public int getHintScreenTimeout()
        {
            return mHintScreenTimeout;
        }

        public boolean getHintShowBackgroundOnly()
        {
            return (mFlags & 4) != 0;
        }

        public List getPages()
        {
            return mPages;
        }

        public boolean getStartScrollBottom()
        {
            return (mFlags & 8) != 0;
        }

        public WearableExtender setBackground(Bitmap bitmap)
        {
            mBackground = bitmap;
            return this;
        }

        public WearableExtender setBridgeTag(String s)
        {
            mBridgeTag = s;
            return this;
        }

        public WearableExtender setContentAction(int i)
        {
            mContentActionIndex = i;
            return this;
        }

        public WearableExtender setContentIcon(int i)
        {
            mContentIcon = i;
            return this;
        }

        public WearableExtender setContentIconGravity(int i)
        {
            mContentIconGravity = i;
            return this;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean flag)
        {
            setFlag(1, flag);
            return this;
        }

        public WearableExtender setCustomContentHeight(int i)
        {
            mCustomContentHeight = i;
            return this;
        }

        public WearableExtender setCustomSizePreset(int i)
        {
            mCustomSizePreset = i;
            return this;
        }

        public WearableExtender setDismissalId(String s)
        {
            mDismissalId = s;
            return this;
        }

        public WearableExtender setDisplayIntent(PendingIntent pendingintent)
        {
            mDisplayIntent = pendingintent;
            return this;
        }

        public WearableExtender setGravity(int i)
        {
            mGravity = i;
            return this;
        }

        public WearableExtender setHintAmbientBigPicture(boolean flag)
        {
            setFlag(32, flag);
            return this;
        }

        public WearableExtender setHintAvoidBackgroundClipping(boolean flag)
        {
            setFlag(16, flag);
            return this;
        }

        public WearableExtender setHintContentIntentLaunchesActivity(boolean flag)
        {
            setFlag(64, flag);
            return this;
        }

        public WearableExtender setHintHideIcon(boolean flag)
        {
            setFlag(2, flag);
            return this;
        }

        public WearableExtender setHintScreenTimeout(int i)
        {
            mHintScreenTimeout = i;
            return this;
        }

        public WearableExtender setHintShowBackgroundOnly(boolean flag)
        {
            setFlag(4, flag);
            return this;
        }

        public WearableExtender setStartScrollBottom(boolean flag)
        {
            setFlag(8, flag);
            return this;
        }

        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 0x800005;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList mActions;
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList mPages;

        public WearableExtender()
        {
            mActions = new ArrayList();
            mFlags = 1;
            mPages = new ArrayList();
            mContentIconGravity = 0x800005;
            mContentActionIndex = -1;
            mCustomSizePreset = 0;
            mGravity = 80;
        }

        public WearableExtender(Notification notification)
        {
            mActions = new ArrayList();
            mFlags = 1;
            mPages = new ArrayList();
            mContentIconGravity = 0x800005;
            mContentActionIndex = -1;
            mCustomSizePreset = 0;
            mGravity = 80;
            notification = NotificationCompat.getExtras(notification);
            if(notification != null)
                notification = notification.getBundle("android.wearable.EXTENSIONS");
            else
                notification = null;
            if(notification != null)
            {
                Object aobj[] = NotificationCompat.IMPL.getActionsFromParcelableArrayList(notification.getParcelableArrayList("actions"));
                if(aobj != null)
                    Collections.addAll(mActions, aobj);
                mFlags = notification.getInt("flags", 1);
                mDisplayIntent = (PendingIntent)notification.getParcelable("displayIntent");
                aobj = NotificationCompat.getNotificationArrayFromBundle(notification, "pages");
                if(aobj != null)
                    Collections.addAll(mPages, aobj);
                mBackground = (Bitmap)notification.getParcelable("background");
                mContentIcon = notification.getInt("contentIcon");
                mContentIconGravity = notification.getInt("contentIconGravity", 0x800005);
                mContentActionIndex = notification.getInt("contentActionIndex", -1);
                mCustomSizePreset = notification.getInt("customSizePreset", 0);
                mCustomContentHeight = notification.getInt("customContentHeight");
                mGravity = notification.getInt("gravity", 80);
                mHintScreenTimeout = notification.getInt("hintScreenTimeout");
                mDismissalId = notification.getString("dismissalId");
                mBridgeTag = notification.getString("bridgeTag");
            }
        }
    }


    public NotificationCompat()
    {
    }

    static void addActionsToBuilder(NotificationBuilderWithActions notificationbuilderwithactions, ArrayList arraylist)
    {
        for(arraylist = arraylist.iterator(); arraylist.hasNext(); notificationbuilderwithactions.addAction((Action)arraylist.next()));
    }

    public static Action getAction(Notification notification, int i)
    {
        return IMPL.getAction(notification, i);
    }

    public static int getActionCount(Notification notification)
    {
        int i = 0;
        if(android.os.Build.VERSION.SDK_INT >= 19)
        {
            if(notification.actions != null)
                i = notification.actions.length;
        } else
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return NotificationCompatJellybean.getActionCount(notification);
        return i;
    }

    public static int getBadgeIconType(Notification notification)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return notification.getBadgeIconType();
        else
            return 0;
    }

    public static String getCategory(Notification notification)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return notification.category;
        else
            return null;
    }

    public static String getChannelId(Notification notification)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return notification.getChannelId();
        else
            return null;
    }

    public static Bundle getExtras(Notification notification)
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            return notification.extras;
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return NotificationCompatJellybean.getExtras(notification);
        else
            return null;
    }

    public static String getGroup(Notification notification)
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            return notification.getGroup();
        if(android.os.Build.VERSION.SDK_INT >= 19)
            return notification.extras.getString("android.support.groupKey");
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return NotificationCompatJellybean.getExtras(notification).getString("android.support.groupKey");
        else
            return null;
    }

    public static int getGroupAlertBehavior(Notification notification)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return notification.getGroupAlertBehavior();
        else
            return 0;
    }

    public static boolean getLocalOnly(Notification notification)
    {
        boolean flag = false;
        if(android.os.Build.VERSION.SDK_INT >= 20)
        {
            if((notification.flags & 0x100) != 0)
                flag = true;
        } else
        {
            if(android.os.Build.VERSION.SDK_INT >= 19)
                return notification.extras.getBoolean("android.support.localOnly");
            if(android.os.Build.VERSION.SDK_INT >= 16)
                return NotificationCompatJellybean.getExtras(notification).getBoolean("android.support.localOnly");
        }
        return flag;
    }

    static Notification[] getNotificationArrayFromBundle(Bundle bundle, String s)
    {
        Parcelable aparcelable[] = bundle.getParcelableArray(s);
        if((aparcelable instanceof Notification[]) || aparcelable == null)
            return (Notification[])(Notification[])aparcelable;
        Notification anotification[] = new Notification[aparcelable.length];
        for(int i = 0; i < aparcelable.length; i++)
            anotification[i] = (Notification)aparcelable[i];

        bundle.putParcelableArray(s, anotification);
        return anotification;
    }

    public static String getShortcutId(Notification notification)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return notification.getShortcutId();
        else
            return null;
    }

    public static String getSortKey(Notification notification)
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            return notification.getSortKey();
        if(android.os.Build.VERSION.SDK_INT >= 19)
            return notification.extras.getString("android.support.sortKey");
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return NotificationCompatJellybean.getExtras(notification).getString("android.support.sortKey");
        else
            return null;
    }

    public static long getTimeoutAfter(Notification notification)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            return notification.getTimeoutAfter();
        else
            return 0L;
    }

    public static boolean isGroupSummary(Notification notification)
    {
        boolean flag = false;
        if(android.os.Build.VERSION.SDK_INT >= 20)
        {
            if((notification.flags & 0x200) != 0)
                flag = true;
        } else
        {
            if(android.os.Build.VERSION.SDK_INT >= 19)
                return notification.extras.getBoolean("android.support.isGroupSummary");
            if(android.os.Build.VERSION.SDK_INT >= 16)
                return NotificationCompatJellybean.getExtras(notification).getBoolean("android.support.isGroupSummary");
        }
        return flag;
    }

    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    static final NotificationCompatImpl IMPL;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            IMPL = new NotificationCompatApi26Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 24)
            IMPL = new NotificationCompatApi24Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 21)
            IMPL = new NotificationCompatApi21Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 20)
            IMPL = new NotificationCompatApi20Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 19)
            IMPL = new NotificationCompatApi19Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new NotificationCompatApi16Impl();
        else
            IMPL = new NotificationCompatBaseImpl();
    }
}
