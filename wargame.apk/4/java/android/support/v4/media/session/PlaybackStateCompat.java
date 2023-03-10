// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.os.*;
import android.text.TextUtils;
import java.lang.annotation.Annotation;
import java.util.*;

// Referenced classes of package android.support.v4.media.session:
//            PlaybackStateCompatApi21, PlaybackStateCompatApi22

public final class PlaybackStateCompat
    implements Parcelable
{
    public static interface Actions
        extends Annotation
    {
    }

    public static final class Builder
    {

        public Builder addCustomAction(CustomAction customaction)
        {
            if(customaction == null)
            {
                throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat.");
            } else
            {
                mCustomActions.add(customaction);
                return this;
            }
        }

        public Builder addCustomAction(String s, String s1, int i)
        {
            return addCustomAction(new CustomAction(s, s1, i, null));
        }

        public PlaybackStateCompat build()
        {
            return new PlaybackStateCompat(mState, mPosition, mBufferedPosition, mRate, mActions, mErrorCode, mErrorMessage, mUpdateTime, mCustomActions, mActiveItemId, mExtras);
        }

        public Builder setActions(long l)
        {
            mActions = l;
            return this;
        }

        public Builder setActiveQueueItemId(long l)
        {
            mActiveItemId = l;
            return this;
        }

        public Builder setBufferedPosition(long l)
        {
            mBufferedPosition = l;
            return this;
        }

        public Builder setErrorMessage(int i, CharSequence charsequence)
        {
            mErrorCode = i;
            mErrorMessage = charsequence;
            return this;
        }

        public Builder setErrorMessage(CharSequence charsequence)
        {
            mErrorMessage = charsequence;
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mExtras = bundle;
            return this;
        }

        public Builder setState(int i, long l, float f)
        {
            return setState(i, l, f, SystemClock.elapsedRealtime());
        }

        public Builder setState(int i, long l, float f, long l1)
        {
            mState = i;
            mPosition = l;
            mUpdateTime = l1;
            mRate = f;
            return this;
        }

        private long mActions;
        private long mActiveItemId;
        private long mBufferedPosition;
        private final List mCustomActions;
        private int mErrorCode;
        private CharSequence mErrorMessage;
        private Bundle mExtras;
        private long mPosition;
        private float mRate;
        private int mState;
        private long mUpdateTime;

        public Builder()
        {
            mCustomActions = new ArrayList();
            mActiveItemId = -1L;
        }

        public Builder(PlaybackStateCompat playbackstatecompat)
        {
            mCustomActions = new ArrayList();
            mActiveItemId = -1L;
            mState = playbackstatecompat.mState;
            mPosition = playbackstatecompat.mPosition;
            mRate = playbackstatecompat.mSpeed;
            mUpdateTime = playbackstatecompat.mUpdateTime;
            mBufferedPosition = playbackstatecompat.mBufferedPosition;
            mActions = playbackstatecompat.mActions;
            mErrorCode = playbackstatecompat.mErrorCode;
            mErrorMessage = playbackstatecompat.mErrorMessage;
            if(playbackstatecompat.mCustomActions != null)
                mCustomActions.addAll(playbackstatecompat.mCustomActions);
            mActiveItemId = playbackstatecompat.mActiveItemId;
            mExtras = playbackstatecompat.mExtras;
        }
    }

    public static final class CustomAction
        implements Parcelable
    {

        public static CustomAction fromCustomAction(Object obj)
        {
            if(obj == null || android.os.Build.VERSION.SDK_INT < 21)
            {
                return null;
            } else
            {
                CustomAction customaction = new CustomAction(PlaybackStateCompatApi21.CustomAction.getAction(obj), PlaybackStateCompatApi21.CustomAction.getName(obj), PlaybackStateCompatApi21.CustomAction.getIcon(obj), PlaybackStateCompatApi21.CustomAction.getExtras(obj));
                customaction.mCustomActionObj = obj;
                return customaction;
            }
        }

        public int describeContents()
        {
            return 0;
        }

        public String getAction()
        {
            return mAction;
        }

        public Object getCustomAction()
        {
            if(mCustomActionObj != null || android.os.Build.VERSION.SDK_INT < 21)
            {
                return mCustomActionObj;
            } else
            {
                mCustomActionObj = PlaybackStateCompatApi21.CustomAction.newInstance(mAction, mName, mIcon, mExtras);
                return mCustomActionObj;
            }
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public int getIcon()
        {
            return mIcon;
        }

        public CharSequence getName()
        {
            return mName;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Action:mName='").append(mName).append(", mIcon=").append(mIcon).append(", mExtras=").append(mExtras).toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mAction);
            TextUtils.writeToParcel(mName, parcel, i);
            parcel.writeInt(mIcon);
            parcel.writeBundle(mExtras);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public CustomAction createFromParcel(Parcel parcel)
            {
                return new CustomAction(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public CustomAction[] newArray(int i)
            {
                return new CustomAction[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final String mAction;
        private Object mCustomActionObj;
        private final Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;


        CustomAction(Parcel parcel)
        {
            mAction = parcel.readString();
            mName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            mIcon = parcel.readInt();
            mExtras = parcel.readBundle();
        }

        CustomAction(String s, CharSequence charsequence, int i, Bundle bundle)
        {
            mAction = s;
            mName = charsequence;
            mIcon = i;
            mExtras = bundle;
        }
    }

    public static final class CustomAction.Builder
    {

        public CustomAction build()
        {
            return new CustomAction(mAction, mName, mIcon, mExtras);
        }

        public CustomAction.Builder setExtras(Bundle bundle)
        {
            mExtras = bundle;
            return this;
        }

        private final String mAction;
        private Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;

        public CustomAction.Builder(String s, CharSequence charsequence, int i)
        {
            if(TextUtils.isEmpty(s))
                throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
            if(TextUtils.isEmpty(charsequence))
                throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
            if(i == 0)
            {
                throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
            } else
            {
                mAction = s;
                mName = charsequence;
                mIcon = i;
                return;
            }
        }
    }

    public static interface ErrorCode
        extends Annotation
    {
    }

    public static interface MediaKeyAction
        extends Annotation
    {
    }

    public static interface RepeatMode
        extends Annotation
    {
    }

    public static interface ShuffleMode
        extends Annotation
    {
    }

    public static interface State
        extends Annotation
    {
    }


    PlaybackStateCompat(int i, long l, long l1, float f, long l2, int j, CharSequence charsequence, long l3, List list, long l4, Bundle bundle)
    {
        mState = i;
        mPosition = l;
        mBufferedPosition = l1;
        mSpeed = f;
        mActions = l2;
        mErrorCode = j;
        mErrorMessage = charsequence;
        mUpdateTime = l3;
        mCustomActions = new ArrayList(list);
        mActiveItemId = l4;
        mExtras = bundle;
    }

    PlaybackStateCompat(Parcel parcel)
    {
        mState = parcel.readInt();
        mPosition = parcel.readLong();
        mSpeed = parcel.readFloat();
        mUpdateTime = parcel.readLong();
        mBufferedPosition = parcel.readLong();
        mActions = parcel.readLong();
        mErrorMessage = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mCustomActions = parcel.createTypedArrayList(CustomAction.CREATOR);
        mActiveItemId = parcel.readLong();
        mExtras = parcel.readBundle();
        mErrorCode = parcel.readInt();
    }

    public static PlaybackStateCompat fromPlaybackState(Object obj)
    {
        if(obj != null && android.os.Build.VERSION.SDK_INT >= 21)
        {
            Object obj2 = PlaybackStateCompatApi21.getCustomActions(obj);
            Object obj1 = null;
            if(obj2 != null)
            {
                ArrayList arraylist = new ArrayList(((List) (obj2)).size());
                obj2 = ((List) (obj2)).iterator();
                do
                {
                    obj1 = arraylist;
                    if(!((Iterator) (obj2)).hasNext())
                        break;
                    arraylist.add(CustomAction.fromCustomAction(((Iterator) (obj2)).next()));
                } while(true);
            }
            Bundle bundle;
            if(android.os.Build.VERSION.SDK_INT >= 22)
                bundle = PlaybackStateCompatApi22.getExtras(obj);
            else
                bundle = null;
            obj1 = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(obj), PlaybackStateCompatApi21.getPosition(obj), PlaybackStateCompatApi21.getBufferedPosition(obj), PlaybackStateCompatApi21.getPlaybackSpeed(obj), PlaybackStateCompatApi21.getActions(obj), 0, PlaybackStateCompatApi21.getErrorMessage(obj), PlaybackStateCompatApi21.getLastPositionUpdateTime(obj), ((List) (obj1)), PlaybackStateCompatApi21.getActiveQueueItemId(obj), bundle);
            obj1.mStateObj = obj;
            return ((PlaybackStateCompat) (obj1));
        } else
        {
            return null;
        }
    }

    public static int toKeyCode(long l)
    {
        if(l == 4L)
            return 126;
        if(l == 2L)
            return 127;
        if(l == 32L)
            return 87;
        if(l == 16L)
            return 88;
        if(l == 1L)
            return 86;
        if(l == 64L)
            return 90;
        if(l == 8L)
            return 89;
        return l != 512L ? 0 : 85;
    }

    public int describeContents()
    {
        return 0;
    }

    public long getActions()
    {
        return mActions;
    }

    public long getActiveQueueItemId()
    {
        return mActiveItemId;
    }

    public long getBufferedPosition()
    {
        return mBufferedPosition;
    }

    public List getCustomActions()
    {
        return mCustomActions;
    }

    public int getErrorCode()
    {
        return mErrorCode;
    }

    public CharSequence getErrorMessage()
    {
        return mErrorMessage;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public long getLastPositionUpdateTime()
    {
        return mUpdateTime;
    }

    public float getPlaybackSpeed()
    {
        return mSpeed;
    }

    public Object getPlaybackState()
    {
        if(mStateObj == null && android.os.Build.VERSION.SDK_INT >= 21)
        {
            ArrayList arraylist = null;
            if(mCustomActions != null)
            {
                ArrayList arraylist1 = new ArrayList(mCustomActions.size());
                Iterator iterator = mCustomActions.iterator();
                do
                {
                    arraylist = arraylist1;
                    if(!iterator.hasNext())
                        break;
                    arraylist1.add(((CustomAction)iterator.next()).getCustomAction());
                } while(true);
            }
            if(android.os.Build.VERSION.SDK_INT >= 22)
                mStateObj = PlaybackStateCompatApi22.newInstance(mState, mPosition, mBufferedPosition, mSpeed, mActions, mErrorMessage, mUpdateTime, arraylist, mActiveItemId, mExtras);
            else
                mStateObj = PlaybackStateCompatApi21.newInstance(mState, mPosition, mBufferedPosition, mSpeed, mActions, mErrorMessage, mUpdateTime, arraylist, mActiveItemId);
        }
        return mStateObj;
    }

    public long getPosition()
    {
        return mPosition;
    }

    public int getState()
    {
        return mState;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("PlaybackState {");
        stringbuilder.append("state=").append(mState);
        stringbuilder.append(", position=").append(mPosition);
        stringbuilder.append(", buffered position=").append(mBufferedPosition);
        stringbuilder.append(", speed=").append(mSpeed);
        stringbuilder.append(", updated=").append(mUpdateTime);
        stringbuilder.append(", actions=").append(mActions);
        stringbuilder.append(", error code=").append(mErrorCode);
        stringbuilder.append(", error message=").append(mErrorMessage);
        stringbuilder.append(", custom actions=").append(mCustomActions);
        stringbuilder.append(", active item id=").append(mActiveItemId);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mState);
        parcel.writeLong(mPosition);
        parcel.writeFloat(mSpeed);
        parcel.writeLong(mUpdateTime);
        parcel.writeLong(mBufferedPosition);
        parcel.writeLong(mActions);
        TextUtils.writeToParcel(mErrorMessage, parcel, i);
        parcel.writeTypedList(mCustomActions);
        parcel.writeLong(mActiveItemId);
        parcel.writeBundle(mExtras);
        parcel.writeInt(mErrorCode);
    }

    public static final long ACTION_FAST_FORWARD = 64L;
    public static final long ACTION_PAUSE = 2L;
    public static final long ACTION_PLAY = 4L;
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024L;
    public static final long ACTION_PLAY_FROM_SEARCH = 2048L;
    public static final long ACTION_PLAY_FROM_URI = 8192L;
    public static final long ACTION_PLAY_PAUSE = 512L;
    public static final long ACTION_PREPARE = 16384L;
    public static final long ACTION_PREPARE_FROM_MEDIA_ID = 32768L;
    public static final long ACTION_PREPARE_FROM_SEARCH = 0x10000L;
    public static final long ACTION_PREPARE_FROM_URI = 0x20000L;
    public static final long ACTION_REWIND = 8L;
    public static final long ACTION_SEEK_TO = 256L;
    public static final long ACTION_SET_CAPTIONING_ENABLED = 0x100000L;
    public static final long ACTION_SET_RATING = 128L;
    public static final long ACTION_SET_REPEAT_MODE = 0x40000L;
    public static final long ACTION_SET_SHUFFLE_MODE_ENABLED = 0x80000L;
    public static final long ACTION_SKIP_TO_NEXT = 32L;
    public static final long ACTION_SKIP_TO_PREVIOUS = 16L;
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096L;
    public static final long ACTION_STOP = 1L;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PlaybackStateCompat createFromParcel(Parcel parcel)
        {
            return new PlaybackStateCompat(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PlaybackStateCompat[] newArray(int i)
        {
            return new PlaybackStateCompat[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int ERROR_CODE_ACTION_ABORTED = 10;
    public static final int ERROR_CODE_APP_ERROR = 1;
    public static final int ERROR_CODE_AUTHENTICATION_EXPIRED = 3;
    public static final int ERROR_CODE_CONCURRENT_STREAM_LIMIT = 5;
    public static final int ERROR_CODE_CONTENT_ALREADY_PLAYING = 8;
    public static final int ERROR_CODE_END_OF_QUEUE = 11;
    public static final int ERROR_CODE_NOT_AVAILABLE_IN_REGION = 7;
    public static final int ERROR_CODE_NOT_SUPPORTED = 2;
    public static final int ERROR_CODE_PARENTAL_CONTROL_RESTRICTED = 6;
    public static final int ERROR_CODE_PREMIUM_ACCOUNT_REQUIRED = 4;
    public static final int ERROR_CODE_SKIP_LIMIT_REACHED = 9;
    public static final int ERROR_CODE_UNKNOWN_ERROR = 0;
    private static final int KEYCODE_MEDIA_PAUSE = 127;
    private static final int KEYCODE_MEDIA_PLAY = 126;
    public static final long PLAYBACK_POSITION_UNKNOWN = -1L;
    public static final int REPEAT_MODE_ALL = 2;
    public static final int REPEAT_MODE_GROUP = 3;
    public static final int REPEAT_MODE_NONE = 0;
    public static final int REPEAT_MODE_ONE = 1;
    public static final int SHUFFLE_MODE_ALL = 1;
    public static final int SHUFFLE_MODE_GROUP = 2;
    public static final int SHUFFLE_MODE_NONE = 0;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_CONNECTING = 8;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_REWINDING = 5;
    public static final int STATE_SKIPPING_TO_NEXT = 10;
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
    public static final int STATE_STOPPED = 1;
    final long mActions;
    final long mActiveItemId;
    final long mBufferedPosition;
    List mCustomActions;
    final int mErrorCode;
    final CharSequence mErrorMessage;
    final Bundle mExtras;
    final long mPosition;
    final float mSpeed;
    final int mState;
    private Object mStateObj;
    final long mUpdateTime;

}
