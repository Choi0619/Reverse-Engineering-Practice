// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.*;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.*;
import android.net.Uri;
import android.os.*;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompatApi21, MediaControllerCompat, MediaButtonReceiver, PlaybackStateCompat, 
//            MediaSessionCompatApi24, MediaSessionCompatApi23, IMediaSession, IMediaControllerCallback, 
//            ParcelableVolumeInfo, MediaSessionCompatApi22

public class MediaSessionCompat
{
    public static abstract class Callback
    {

        private void handleMediaPlayPauseKeySingleTapIfPending()
        {
            boolean flag2 = true;
            if(mMediaPlayPauseKeyPending)
            {
                mMediaPlayPauseKeyPending = false;
                mCallbackHandler.removeMessages(1);
                Object obj = (MediaSessionImpl)mSessionImpl.get();
                if(obj != null)
                {
                    obj = ((MediaSessionImpl) (obj)).getPlaybackState();
                    boolean flag;
                    boolean flag1;
                    long l;
                    if(obj == null)
                        l = 0L;
                    else
                        l = ((PlaybackStateCompat) (obj)).getActions();
                    if(obj != null && ((PlaybackStateCompat) (obj)).getState() == 3)
                        flag = true;
                    else
                        flag = false;
                    if((516L & l) != 0L)
                        flag1 = true;
                    else
                        flag1 = false;
                    if((514L & l) == 0L)
                        flag2 = false;
                    if(flag && flag2)
                    {
                        onPause();
                        return;
                    }
                    if(!flag && flag1)
                    {
                        onPlay();
                        return;
                    }
                }
            }
        }

        private void setSessionImpl(MediaSessionImpl mediasessionimpl, Handler handler)
        {
            mSessionImpl = new WeakReference(mediasessionimpl);
            if(mCallbackHandler != null)
                mCallbackHandler.removeCallbacksAndMessages(null);
            mCallbackHandler = new CallbackHandler(handler.getLooper());
        }

        public void onAddQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
        }

        public void onAddQueueItem(MediaDescriptionCompat mediadescriptioncompat, int i)
        {
        }

        public void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
        }

        public void onCustomAction(String s, Bundle bundle)
        {
        }

        public void onFastForward()
        {
        }

        public boolean onMediaButtonEvent(Intent intent)
        {
            boolean flag = true;
            MediaSessionImpl mediasessionimpl = (MediaSessionImpl)mSessionImpl.get();
            if(mediasessionimpl == null || mCallbackHandler == null)
            {
                flag = false;
            } else
            {
                intent = (KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
                if(intent == null || intent.getAction() != 0)
                    return false;
                switch(intent.getKeyCode())
                {
                default:
                    handleMediaPlayPauseKeySingleTapIfPending();
                    return false;

                case 79: // 'O'
                case 85: // 'U'
                    break;
                }
                if(intent.getRepeatCount() > 0)
                {
                    handleMediaPlayPauseKeySingleTapIfPending();
                    return true;
                }
                if(mMediaPlayPauseKeyPending)
                {
                    mCallbackHandler.removeMessages(1);
                    mMediaPlayPauseKeyPending = false;
                    intent = mediasessionimpl.getPlaybackState();
                    long l;
                    if(intent == null)
                        l = 0L;
                    else
                        l = intent.getActions();
                    if((32L & l) != 0L)
                    {
                        onSkipToNext();
                        return true;
                    }
                } else
                {
                    mMediaPlayPauseKeyPending = true;
                    mCallbackHandler.sendEmptyMessageDelayed(1, ViewConfiguration.getDoubleTapTimeout());
                    return true;
                }
            }
            return flag;
        }

        public void onPause()
        {
        }

        public void onPlay()
        {
        }

        public void onPlayFromMediaId(String s, Bundle bundle)
        {
        }

        public void onPlayFromSearch(String s, Bundle bundle)
        {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle)
        {
        }

        public void onPrepare()
        {
        }

        public void onPrepareFromMediaId(String s, Bundle bundle)
        {
        }

        public void onPrepareFromSearch(String s, Bundle bundle)
        {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle)
        {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
        }

        public void onRemoveQueueItemAt(int i)
        {
        }

        public void onRewind()
        {
        }

        public void onSeekTo(long l)
        {
        }

        public void onSetCaptioningEnabled(boolean flag)
        {
        }

        public void onSetRating(RatingCompat ratingcompat)
        {
        }

        public void onSetRating(RatingCompat ratingcompat, Bundle bundle)
        {
        }

        public void onSetRepeatMode(int i)
        {
        }

        public void onSetShuffleMode(int i)
        {
        }

        public void onSetShuffleModeEnabled(boolean flag)
        {
        }

        public void onSkipToNext()
        {
        }

        public void onSkipToPrevious()
        {
        }

        public void onSkipToQueueItem(long l)
        {
        }

        public void onStop()
        {
        }

        private CallbackHandler mCallbackHandler;
        final Object mCallbackObj;
        private boolean mMediaPlayPauseKeyPending;
        private WeakReference mSessionImpl;




        public Callback()
        {
            mCallbackHandler = null;
            if(android.os.Build.VERSION.SDK_INT >= 24)
            {
                mCallbackObj = MediaSessionCompatApi24.createCallback(new StubApi24());
                return;
            }
            if(android.os.Build.VERSION.SDK_INT >= 23)
            {
                mCallbackObj = MediaSessionCompatApi23.createCallback(new StubApi23());
                return;
            }
            if(android.os.Build.VERSION.SDK_INT >= 21)
            {
                mCallbackObj = MediaSessionCompatApi21.createCallback(new StubApi21());
                return;
            } else
            {
                mCallbackObj = null;
                return;
            }
        }
    }

    private class Callback.CallbackHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(message.what == 1)
                handleMediaPlayPauseKeySingleTapIfPending();
        }

        private static final int MSG_MEDIA_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 1;
        final Callback this$0;

        Callback.CallbackHandler(Looper looper)
        {
            this$0 = Callback.this;
            super(looper);
        }
    }

    private class Callback.StubApi21
        implements MediaSessionCompatApi21.Callback
    {

        public void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            Object obj;
            Object obj1;
            obj1 = null;
            obj = null;
            if(!s.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER"))
                break MISSING_BLOCK_LABEL_80;
            s = (MediaSessionImplApi21)mSessionImpl.get();
            if(s == null)
                break MISSING_BLOCK_LABEL_310;
            bundle = new Bundle();
            s = s.getSessionToken().getExtraBinder();
            if(s != null)
                break MISSING_BLOCK_LABEL_70;
            s = obj;
_L1:
            try
            {
                BundleCompat.putBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER", s);
                resultreceiver.send(0, bundle);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
            }
            break MISSING_BLOCK_LABEL_124;
            s = s.asBinder();
              goto _L1
            if(s.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM"))
            {
                bundle.setClassLoader(android/support/v4/media/MediaDescriptionCompat.getClassLoader());
                onAddQueueItem((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                return;
            }
            break MISSING_BLOCK_LABEL_125;
            return;
            if(s.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT"))
            {
                bundle.setClassLoader(android/support/v4/media/MediaDescriptionCompat.getClassLoader());
                onAddQueueItem((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
                return;
            }
            if(s.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM"))
            {
                bundle.setClassLoader(android/support/v4/media/MediaDescriptionCompat.getClassLoader());
                onRemoveQueueItem((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                return;
            }
            if(!s.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT"))
                break MISSING_BLOCK_LABEL_300;
            resultreceiver = (MediaSessionImplApi21)mSessionImpl.get();
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_310;
            int i;
            if(((MediaSessionImplApi21) (resultreceiver)).mQueue == null)
                break MISSING_BLOCK_LABEL_310;
            i = bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
            s = obj1;
            if(i < 0)
                break MISSING_BLOCK_LABEL_284;
            s = obj1;
            if(i < ((MediaSessionImplApi21) (resultreceiver)).mQueue.size())
                s = (QueueItem)((MediaSessionImplApi21) (resultreceiver)).mQueue.get(i);
            if(s == null)
                break MISSING_BLOCK_LABEL_310;
            onRemoveQueueItem(s.getDescription());
            return;
            Callback.this.onCommand(s, bundle, resultreceiver);
        }

        public void onCustomAction(String s, Bundle bundle)
        {
            if(s.equals("android.support.v4.media.session.action.PLAY_FROM_URI"))
            {
                s = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                bundle = (Bundle)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPlayFromUri(s, bundle);
                return;
            }
            if(s.equals("android.support.v4.media.session.action.PREPARE"))
            {
                onPrepare();
                return;
            }
            if(s.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID"))
            {
                s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPrepareFromMediaId(s, bundle);
                return;
            }
            if(s.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH"))
            {
                s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPrepareFromSearch(s, bundle);
                return;
            }
            if(s.equals("android.support.v4.media.session.action.PREPARE_FROM_URI"))
            {
                s = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPrepareFromUri(s, bundle);
                return;
            }
            if(s.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED"))
            {
                boolean flag = bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED");
                onSetCaptioningEnabled(flag);
                return;
            }
            if(s.equals("android.support.v4.media.session.action.SET_REPEAT_MODE"))
            {
                int i = bundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE");
                onSetRepeatMode(i);
                return;
            }
            if(s.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED"))
            {
                boolean flag1 = bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED");
                onSetShuffleModeEnabled(flag1);
                return;
            }
            if(s.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE"))
            {
                int j = bundle.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE");
                onSetShuffleMode(j);
                return;
            }
            if(s.equals("android.support.v4.media.session.action.SET_RATING"))
            {
                bundle.setClassLoader(android/support/v4/media/RatingCompat.getClassLoader());
                s = (RatingCompat)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING");
                bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                Callback.this.onSetRating(s, bundle);
                return;
            } else
            {
                Callback.this.onCustomAction(s, bundle);
                return;
            }
        }

        public void onFastForward()
        {
            Callback.this.onFastForward();
        }

        public boolean onMediaButtonEvent(Intent intent)
        {
            return Callback.this.onMediaButtonEvent(intent);
        }

        public void onPause()
        {
            Callback.this.onPause();
        }

        public void onPlay()
        {
            Callback.this.onPlay();
        }

        public void onPlayFromMediaId(String s, Bundle bundle)
        {
            Callback.this.onPlayFromMediaId(s, bundle);
        }

        public void onPlayFromSearch(String s, Bundle bundle)
        {
            Callback.this.onPlayFromSearch(s, bundle);
        }

        public void onRewind()
        {
            Callback.this.onRewind();
        }

        public void onSeekTo(long l)
        {
            Callback.this.onSeekTo(l);
        }

        public void onSetRating(Object obj)
        {
            Callback.this.onSetRating(RatingCompat.fromRating(obj));
        }

        public void onSetRating(Object obj, Bundle bundle)
        {
            Callback.this.onSetRating(RatingCompat.fromRating(obj), bundle);
        }

        public void onSkipToNext()
        {
            Callback.this.onSkipToNext();
        }

        public void onSkipToPrevious()
        {
            Callback.this.onSkipToPrevious();
        }

        public void onSkipToQueueItem(long l)
        {
            Callback.this.onSkipToQueueItem(l);
        }

        public void onStop()
        {
            Callback.this.onStop();
        }

        final Callback this$0;

        Callback.StubApi21()
        {
            this$0 = Callback.this;
            super();
        }
    }

    private class Callback.StubApi23 extends Callback.StubApi21
        implements MediaSessionCompatApi23.Callback
    {

        public void onPlayFromUri(Uri uri, Bundle bundle)
        {
            Callback.this.onPlayFromUri(uri, bundle);
        }

        final Callback this$0;

        Callback.StubApi23()
        {
            this$0 = Callback.this;
            super();
        }
    }

    private class Callback.StubApi24 extends Callback.StubApi23
        implements MediaSessionCompatApi24.Callback
    {

        public void onPrepare()
        {
            Callback.this.onPrepare();
        }

        public void onPrepareFromMediaId(String s, Bundle bundle)
        {
            Callback.this.onPrepareFromMediaId(s, bundle);
        }

        public void onPrepareFromSearch(String s, Bundle bundle)
        {
            Callback.this.onPrepareFromSearch(s, bundle);
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle)
        {
            Callback.this.onPrepareFromUri(uri, bundle);
        }

        final Callback this$0;

        Callback.StubApi24()
        {
            this$0 = Callback.this;
            super();
        }
    }

    static interface MediaSessionImpl
    {

        public abstract String getCallingPackage();

        public abstract Object getMediaSession();

        public abstract PlaybackStateCompat getPlaybackState();

        public abstract Object getRemoteControlClient();

        public abstract Token getSessionToken();

        public abstract boolean isActive();

        public abstract void release();

        public abstract void sendSessionEvent(String s, Bundle bundle);

        public abstract void setActive(boolean flag);

        public abstract void setCallback(Callback callback, Handler handler);

        public abstract void setCaptioningEnabled(boolean flag);

        public abstract void setExtras(Bundle bundle);

        public abstract void setFlags(int i);

        public abstract void setMediaButtonReceiver(PendingIntent pendingintent);

        public abstract void setMetadata(MediaMetadataCompat mediametadatacompat);

        public abstract void setPlaybackState(PlaybackStateCompat playbackstatecompat);

        public abstract void setPlaybackToLocal(int i);

        public abstract void setPlaybackToRemote(VolumeProviderCompat volumeprovidercompat);

        public abstract void setQueue(List list);

        public abstract void setQueueTitle(CharSequence charsequence);

        public abstract void setRatingType(int i);

        public abstract void setRepeatMode(int i);

        public abstract void setSessionActivity(PendingIntent pendingintent);

        public abstract void setShuffleMode(int i);

        public abstract void setShuffleModeEnabled(boolean flag);
    }

    static class MediaSessionImplApi18 extends MediaSessionImplBase
    {

        int getRccTransportControlFlagsFromActions(long l)
        {
            int j = super.getRccTransportControlFlagsFromActions(l);
            int i = j;
            if((256L & l) != 0L)
                i = j | 0x100;
            return i;
        }

        void registerMediaButtonEventReceiver(PendingIntent pendingintent, ComponentName componentname)
        {
            if(sIsMbrPendingIntentSupported)
                try
                {
                    mAudioManager.registerMediaButtonEventReceiver(pendingintent);
                }
                catch(NullPointerException nullpointerexception)
                {
                    Log.w("MediaSessionCompat", "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
                    sIsMbrPendingIntentSupported = false;
                }
            if(!sIsMbrPendingIntentSupported)
                super.registerMediaButtonEventReceiver(pendingintent, componentname);
        }

        public void setCallback(Callback callback, Handler handler)
        {
            super.setCallback(callback, handler);
            if(callback == null)
            {
                mRcc.setPlaybackPositionUpdateListener(null);
                return;
            } else
            {
                callback = new android.media.RemoteControlClient.OnPlaybackPositionUpdateListener() {

                    public void onPlaybackPositionUpdate(long l)
                    {
                        postToHandler(18, Long.valueOf(l));
                    }

                    final MediaSessionImplApi18 this$0;

            
            {
                this$0 = MediaSessionImplApi18.this;
                super();
            }
                }
;
                mRcc.setPlaybackPositionUpdateListener(callback);
                return;
            }
        }

        void setRccState(PlaybackStateCompat playbackstatecompat)
        {
            long l1 = playbackstatecompat.getPosition();
            float f = playbackstatecompat.getPlaybackSpeed();
            long l2 = playbackstatecompat.getLastPositionUpdateTime();
            long l3 = SystemClock.elapsedRealtime();
            long l = l1;
            if(playbackstatecompat.getState() == 3)
            {
                l = l1;
                if(l1 > 0L)
                {
                    l = 0L;
                    if(l2 > 0L)
                    {
                        l2 = l3 - l2;
                        l = l2;
                        if(f > 0.0F)
                        {
                            l = l2;
                            if(f != 1.0F)
                                l = (long)((float)l2 * f);
                        }
                    }
                    l = l1 + l;
                }
            }
            mRcc.setPlaybackState(getRccStateFromState(playbackstatecompat.getState()), l, f);
        }

        void unregisterMediaButtonEventReceiver(PendingIntent pendingintent, ComponentName componentname)
        {
            if(sIsMbrPendingIntentSupported)
            {
                mAudioManager.unregisterMediaButtonEventReceiver(pendingintent);
                return;
            } else
            {
                super.unregisterMediaButtonEventReceiver(pendingintent, componentname);
                return;
            }
        }

        private static boolean sIsMbrPendingIntentSupported = true;


        MediaSessionImplApi18(Context context, String s, ComponentName componentname, PendingIntent pendingintent)
        {
            super(context, s, componentname, pendingintent);
        }
    }

    static class MediaSessionImplApi19 extends MediaSessionImplApi18
    {

        android.media.RemoteControlClient.MetadataEditor buildRccMetadata(Bundle bundle)
        {
            android.media.RemoteControlClient.MetadataEditor metadataeditor = super.buildRccMetadata(bundle);
            long l;
            if(mState == null)
                l = 0L;
            else
                l = mState.getActions();
            if((128L & l) != 0L)
                metadataeditor.addEditableKey(0x10000001);
            if(bundle != null)
            {
                if(bundle.containsKey("android.media.metadata.YEAR"))
                    metadataeditor.putLong(8, bundle.getLong("android.media.metadata.YEAR"));
                if(bundle.containsKey("android.media.metadata.RATING"))
                    metadataeditor.putObject(101, bundle.getParcelable("android.media.metadata.RATING"));
                if(bundle.containsKey("android.media.metadata.USER_RATING"))
                {
                    metadataeditor.putObject(0x10000001, bundle.getParcelable("android.media.metadata.USER_RATING"));
                    return metadataeditor;
                }
            }
            return metadataeditor;
        }

        int getRccTransportControlFlagsFromActions(long l)
        {
            int j = super.getRccTransportControlFlagsFromActions(l);
            int i = j;
            if((128L & l) != 0L)
                i = j | 0x200;
            return i;
        }

        public void setCallback(Callback callback, Handler handler)
        {
            super.setCallback(callback, handler);
            if(callback == null)
            {
                mRcc.setMetadataUpdateListener(null);
                return;
            } else
            {
                callback = new android.media.RemoteControlClient.OnMetadataUpdateListener() {

                    public void onMetadataUpdate(int i, Object obj)
                    {
                        if(i == 0x10000001 && (obj instanceof Rating))
                            postToHandler(19, RatingCompat.fromRating(obj));
                    }

                    final MediaSessionImplApi19 this$0;

            
            {
                this$0 = MediaSessionImplApi19.this;
                super();
            }
                }
;
                mRcc.setMetadataUpdateListener(callback);
                return;
            }
        }

        MediaSessionImplApi19(Context context, String s, ComponentName componentname, PendingIntent pendingintent)
        {
            super(context, s, componentname, pendingintent);
        }
    }

    static class MediaSessionImplApi21
        implements MediaSessionImpl
    {

        public String getCallingPackage()
        {
            if(android.os.Build.VERSION.SDK_INT < 24)
                return null;
            else
                return MediaSessionCompatApi24.getCallingPackage(mSessionObj);
        }

        public Object getMediaSession()
        {
            return mSessionObj;
        }

        public PlaybackStateCompat getPlaybackState()
        {
            return mPlaybackState;
        }

        public Object getRemoteControlClient()
        {
            return null;
        }

        public Token getSessionToken()
        {
            return mToken;
        }

        public boolean isActive()
        {
            return MediaSessionCompatApi21.isActive(mSessionObj);
        }

        public void release()
        {
            mDestroyed = true;
            MediaSessionCompatApi21.release(mSessionObj);
        }

        public void sendSessionEvent(String s, Bundle bundle)
        {
            if(android.os.Build.VERSION.SDK_INT < 23)
            {
                int i = mExtraControllerCallbacks.beginBroadcast() - 1;
                while(i >= 0) 
                {
                    IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
                    try
                    {
                        imediacontrollercallback.onEvent(s, bundle);
                    }
                    catch(RemoteException remoteexception) { }
                    i--;
                }
                mExtraControllerCallbacks.finishBroadcast();
            }
            MediaSessionCompatApi21.sendSessionEvent(mSessionObj, s, bundle);
        }

        public void setActive(boolean flag)
        {
            MediaSessionCompatApi21.setActive(mSessionObj, flag);
        }

        public void setCallback(Callback callback, Handler handler)
        {
            Object obj1 = mSessionObj;
            Object obj;
            if(callback == null)
                obj = null;
            else
                obj = callback.mCallbackObj;
            MediaSessionCompatApi21.setCallback(obj1, obj, handler);
            if(callback != null)
                callback.setSessionImpl(this, handler);
        }

        public void setCaptioningEnabled(boolean flag)
        {
            if(mCaptioningEnabled != flag)
            {
                mCaptioningEnabled = flag;
                int i = mExtraControllerCallbacks.beginBroadcast() - 1;
                while(i >= 0) 
                {
                    IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
                    try
                    {
                        imediacontrollercallback.onCaptioningEnabledChanged(flag);
                    }
                    catch(RemoteException remoteexception) { }
                    i--;
                }
                mExtraControllerCallbacks.finishBroadcast();
            }
        }

        public void setExtras(Bundle bundle)
        {
            MediaSessionCompatApi21.setExtras(mSessionObj, bundle);
        }

        public void setFlags(int i)
        {
            MediaSessionCompatApi21.setFlags(mSessionObj, i);
        }

        public void setMediaButtonReceiver(PendingIntent pendingintent)
        {
            MediaSessionCompatApi21.setMediaButtonReceiver(mSessionObj, pendingintent);
        }

        public void setMetadata(MediaMetadataCompat mediametadatacompat)
        {
            mMetadata = mediametadatacompat;
            Object obj = mSessionObj;
            if(mediametadatacompat == null)
                mediametadatacompat = null;
            else
                mediametadatacompat = ((MediaMetadataCompat) (mediametadatacompat.getMediaMetadata()));
            MediaSessionCompatApi21.setMetadata(obj, mediametadatacompat);
        }

        public void setPlaybackState(PlaybackStateCompat playbackstatecompat)
        {
            mPlaybackState = playbackstatecompat;
            int i = mExtraControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                Object obj = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
                try
                {
                    ((IMediaControllerCallback) (obj)).onPlaybackStateChanged(playbackstatecompat);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mExtraControllerCallbacks.finishBroadcast();
            obj = mSessionObj;
            if(playbackstatecompat == null)
                playbackstatecompat = null;
            else
                playbackstatecompat = ((PlaybackStateCompat) (playbackstatecompat.getPlaybackState()));
            MediaSessionCompatApi21.setPlaybackState(obj, playbackstatecompat);
        }

        public void setPlaybackToLocal(int i)
        {
            MediaSessionCompatApi21.setPlaybackToLocal(mSessionObj, i);
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeprovidercompat)
        {
            MediaSessionCompatApi21.setPlaybackToRemote(mSessionObj, volumeprovidercompat.getVolumeProvider());
        }

        public void setQueue(List list)
        {
            mQueue = list;
            ArrayList arraylist = null;
            if(list != null)
            {
                ArrayList arraylist1 = new ArrayList();
                list = list.iterator();
                do
                {
                    arraylist = arraylist1;
                    if(!list.hasNext())
                        break;
                    arraylist1.add(((QueueItem)list.next()).getQueueItem());
                } while(true);
            }
            MediaSessionCompatApi21.setQueue(mSessionObj, arraylist);
        }

        public void setQueueTitle(CharSequence charsequence)
        {
            MediaSessionCompatApi21.setQueueTitle(mSessionObj, charsequence);
        }

        public void setRatingType(int i)
        {
            if(android.os.Build.VERSION.SDK_INT < 22)
            {
                mRatingType = i;
                return;
            } else
            {
                MediaSessionCompatApi22.setRatingType(mSessionObj, i);
                return;
            }
        }

        public void setRepeatMode(int i)
        {
            if(mRepeatMode != i)
            {
                mRepeatMode = i;
                int j = mExtraControllerCallbacks.beginBroadcast() - 1;
                while(j >= 0) 
                {
                    IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(j);
                    try
                    {
                        imediacontrollercallback.onRepeatModeChanged(i);
                    }
                    catch(RemoteException remoteexception) { }
                    j--;
                }
                mExtraControllerCallbacks.finishBroadcast();
            }
        }

        public void setSessionActivity(PendingIntent pendingintent)
        {
            MediaSessionCompatApi21.setSessionActivity(mSessionObj, pendingintent);
        }

        public void setShuffleMode(int i)
        {
            if(mShuffleMode != i)
            {
                mShuffleMode = i;
                int j = mExtraControllerCallbacks.beginBroadcast() - 1;
                while(j >= 0) 
                {
                    IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(j);
                    try
                    {
                        imediacontrollercallback.onShuffleModeChanged(i);
                    }
                    catch(RemoteException remoteexception) { }
                    j--;
                }
                mExtraControllerCallbacks.finishBroadcast();
            }
        }

        public void setShuffleModeEnabled(boolean flag)
        {
            if(mShuffleModeEnabled != flag)
            {
                mShuffleModeEnabled = flag;
                int i = mExtraControllerCallbacks.beginBroadcast() - 1;
                while(i >= 0) 
                {
                    IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mExtraControllerCallbacks.getBroadcastItem(i);
                    try
                    {
                        imediacontrollercallback.onShuffleModeChangedDeprecated(flag);
                    }
                    catch(RemoteException remoteexception) { }
                    i--;
                }
                mExtraControllerCallbacks.finishBroadcast();
            }
        }

        boolean mCaptioningEnabled;
        private boolean mDestroyed;
        private final RemoteCallbackList mExtraControllerCallbacks;
        private MediaMetadataCompat mMetadata;
        private PlaybackStateCompat mPlaybackState;
        private List mQueue;
        int mRatingType;
        int mRepeatMode;
        private final Object mSessionObj;
        int mShuffleMode;
        boolean mShuffleModeEnabled;
        private final Token mToken;






        public MediaSessionImplApi21(Context context, String s)
        {
            mDestroyed = false;
            mExtraControllerCallbacks = new RemoteCallbackList();
            mSessionObj = MediaSessionCompatApi21.createSession(context, s);
            mToken = new Token(MediaSessionCompatApi21.getSessionToken(mSessionObj), new ExtraSession());
        }

        public MediaSessionImplApi21(Object obj)
        {
            mDestroyed = false;
            mExtraControllerCallbacks = new RemoteCallbackList();
            mSessionObj = MediaSessionCompatApi21.verifySession(obj);
            mToken = new Token(MediaSessionCompatApi21.getSessionToken(mSessionObj), new ExtraSession());
        }
    }

    class MediaSessionImplApi21.ExtraSession extends IMediaSession.Stub
    {

        public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            throw new AssertionError();
        }

        public void addQueueItemAt(MediaDescriptionCompat mediadescriptioncompat, int i)
        {
            throw new AssertionError();
        }

        public void adjustVolume(int i, int j, String s)
        {
            throw new AssertionError();
        }

        public void fastForward()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public Bundle getExtras()
        {
            throw new AssertionError();
        }

        public long getFlags()
        {
            throw new AssertionError();
        }

        public PendingIntent getLaunchPendingIntent()
        {
            throw new AssertionError();
        }

        public MediaMetadataCompat getMetadata()
        {
            throw new AssertionError();
        }

        public String getPackageName()
        {
            throw new AssertionError();
        }

        public PlaybackStateCompat getPlaybackState()
        {
            return MediaSessionCompat.getStateWithUpdatedPosition(mPlaybackState, mMetadata);
        }

        public List getQueue()
        {
            return null;
        }

        public CharSequence getQueueTitle()
        {
            throw new AssertionError();
        }

        public int getRatingType()
        {
            return mRatingType;
        }

        public int getRepeatMode()
        {
            return mRepeatMode;
        }

        public int getShuffleMode()
        {
            return mShuffleMode;
        }

        public String getTag()
        {
            throw new AssertionError();
        }

        public ParcelableVolumeInfo getVolumeAttributes()
        {
            throw new AssertionError();
        }

        public boolean isCaptioningEnabled()
        {
            return mCaptioningEnabled;
        }

        public boolean isShuffleModeEnabledDeprecated()
        {
            return mShuffleModeEnabled;
        }

        public boolean isTransportControlEnabled()
        {
            throw new AssertionError();
        }

        public void next()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void pause()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void play()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void playFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void playFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void playFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void prepare()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void prepareFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void prepareFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void prepareFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void previous()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void rate(RatingCompat ratingcompat)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void rateWithExtras(RatingCompat ratingcompat, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void registerCallbackListener(IMediaControllerCallback imediacontrollercallback)
        {
            if(!mDestroyed)
                mExtraControllerCallbacks.register(imediacontrollercallback);
        }

        public void removeQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            throw new AssertionError();
        }

        public void removeQueueItemAt(int i)
        {
            throw new AssertionError();
        }

        public void rewind()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void seekTo(long l)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void sendCommand(String s, Bundle bundle, ResultReceiverWrapper resultreceiverwrapper)
        {
            throw new AssertionError();
        }

        public void sendCustomAction(String s, Bundle bundle)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public boolean sendMediaButton(KeyEvent keyevent)
        {
            throw new AssertionError();
        }

        public void setCaptioningEnabled(boolean flag)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void setRepeatMode(int i)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void setShuffleMode(int i)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void setShuffleModeEnabledDeprecated(boolean flag)
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void setVolumeTo(int i, int j, String s)
        {
            throw new AssertionError();
        }

        public void skipToQueueItem(long l)
        {
            throw new AssertionError();
        }

        public void stop()
            throws RemoteException
        {
            throw new AssertionError();
        }

        public void unregisterCallbackListener(IMediaControllerCallback imediacontrollercallback)
        {
            mExtraControllerCallbacks.unregister(imediacontrollercallback);
        }

        final MediaSessionImplApi21 this$0;

        MediaSessionImplApi21.ExtraSession()
        {
            this$0 = MediaSessionImplApi21.this;
            super();
        }
    }

    static class MediaSessionImplBase
        implements MediaSessionImpl
    {

        private void sendCaptioningEnabled(boolean flag)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onCaptioningEnabledChanged(flag);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendEvent(String s, Bundle bundle)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onEvent(s, bundle);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendExtras(Bundle bundle)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onExtrasChanged(bundle);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendMetadata(MediaMetadataCompat mediametadatacompat)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onMetadataChanged(mediametadatacompat);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendQueue(List list)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onQueueChanged(list);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendQueueTitle(CharSequence charsequence)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onQueueTitleChanged(charsequence);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendRepeatMode(int i)
        {
            int j = mControllerCallbacks.beginBroadcast() - 1;
            while(j >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(j);
                try
                {
                    imediacontrollercallback.onRepeatModeChanged(i);
                }
                catch(RemoteException remoteexception) { }
                j--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendSessionDestroyed()
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onSessionDestroyed();
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
            mControllerCallbacks.kill();
        }

        private void sendShuffleMode(int i)
        {
            int j = mControllerCallbacks.beginBroadcast() - 1;
            while(j >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(j);
                try
                {
                    imediacontrollercallback.onShuffleModeChanged(i);
                }
                catch(RemoteException remoteexception) { }
                j--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendShuffleModeEnabled(boolean flag)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onShuffleModeChangedDeprecated(flag);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendState(PlaybackStateCompat playbackstatecompat)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onPlaybackStateChanged(playbackstatecompat);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        void adjustVolume(int i, int j)
        {
            if(mVolumeType == 2)
            {
                if(mVolumeProvider != null)
                    mVolumeProvider.onAdjustVolume(i);
                return;
            } else
            {
                mAudioManager.adjustStreamVolume(mLocalStream, i, j);
                return;
            }
        }

        android.media.RemoteControlClient.MetadataEditor buildRccMetadata(Bundle bundle)
        {
            android.media.RemoteControlClient.MetadataEditor metadataeditor = mRcc.editMetadata(true);
            if(bundle != null)
            {
                if(bundle.containsKey("android.media.metadata.ART"))
                {
                    Bitmap bitmap2 = (Bitmap)bundle.getParcelable("android.media.metadata.ART");
                    Bitmap bitmap = bitmap2;
                    if(bitmap2 != null)
                        bitmap = bitmap2.copy(bitmap2.getConfig(), false);
                    metadataeditor.putBitmap(100, bitmap);
                } else
                if(bundle.containsKey("android.media.metadata.ALBUM_ART"))
                {
                    Bitmap bitmap3 = (Bitmap)bundle.getParcelable("android.media.metadata.ALBUM_ART");
                    Bitmap bitmap1 = bitmap3;
                    if(bitmap3 != null)
                        bitmap1 = bitmap3.copy(bitmap3.getConfig(), false);
                    metadataeditor.putBitmap(100, bitmap1);
                }
                if(bundle.containsKey("android.media.metadata.ALBUM"))
                    metadataeditor.putString(1, bundle.getString("android.media.metadata.ALBUM"));
                if(bundle.containsKey("android.media.metadata.ALBUM_ARTIST"))
                    metadataeditor.putString(13, bundle.getString("android.media.metadata.ALBUM_ARTIST"));
                if(bundle.containsKey("android.media.metadata.ARTIST"))
                    metadataeditor.putString(2, bundle.getString("android.media.metadata.ARTIST"));
                if(bundle.containsKey("android.media.metadata.AUTHOR"))
                    metadataeditor.putString(3, bundle.getString("android.media.metadata.AUTHOR"));
                if(bundle.containsKey("android.media.metadata.COMPILATION"))
                    metadataeditor.putString(15, bundle.getString("android.media.metadata.COMPILATION"));
                if(bundle.containsKey("android.media.metadata.COMPOSER"))
                    metadataeditor.putString(4, bundle.getString("android.media.metadata.COMPOSER"));
                if(bundle.containsKey("android.media.metadata.DATE"))
                    metadataeditor.putString(5, bundle.getString("android.media.metadata.DATE"));
                if(bundle.containsKey("android.media.metadata.DISC_NUMBER"))
                    metadataeditor.putLong(14, bundle.getLong("android.media.metadata.DISC_NUMBER"));
                if(bundle.containsKey("android.media.metadata.DURATION"))
                    metadataeditor.putLong(9, bundle.getLong("android.media.metadata.DURATION"));
                if(bundle.containsKey("android.media.metadata.GENRE"))
                    metadataeditor.putString(6, bundle.getString("android.media.metadata.GENRE"));
                if(bundle.containsKey("android.media.metadata.TITLE"))
                    metadataeditor.putString(7, bundle.getString("android.media.metadata.TITLE"));
                if(bundle.containsKey("android.media.metadata.TRACK_NUMBER"))
                    metadataeditor.putLong(0, bundle.getLong("android.media.metadata.TRACK_NUMBER"));
                if(bundle.containsKey("android.media.metadata.WRITER"))
                {
                    metadataeditor.putString(11, bundle.getString("android.media.metadata.WRITER"));
                    return metadataeditor;
                }
            }
            return metadataeditor;
        }

        public String getCallingPackage()
        {
            return null;
        }

        public Object getMediaSession()
        {
            return null;
        }

        public PlaybackStateCompat getPlaybackState()
        {
            PlaybackStateCompat playbackstatecompat;
            synchronized(mLock)
            {
                playbackstatecompat = mState;
            }
            return playbackstatecompat;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        int getRccStateFromState(int i)
        {
            switch(i)
            {
            default:
                return -1;

            case 6: // '\006'
            case 8: // '\b'
                return 8;

            case 7: // '\007'
                return 9;

            case 4: // '\004'
                return 4;

            case 0: // '\0'
                return 0;

            case 2: // '\002'
                return 2;

            case 3: // '\003'
                return 3;

            case 5: // '\005'
                return 5;

            case 9: // '\t'
                return 7;

            case 10: // '\n'
            case 11: // '\013'
                return 6;

            case 1: // '\001'
                return 1;
            }
        }

        int getRccTransportControlFlagsFromActions(long l)
        {
            int j = 0;
            if((1L & l) != 0L)
                j = 0 | 0x20;
            int i = j;
            if((2L & l) != 0L)
                i = j | 0x10;
            j = i;
            if((4L & l) != 0L)
                j = i | 4;
            i = j;
            if((8L & l) != 0L)
                i = j | 2;
            j = i;
            if((16L & l) != 0L)
                j = i | 1;
            i = j;
            if((32L & l) != 0L)
                i = j | 0x80;
            j = i;
            if((64L & l) != 0L)
                j = i | 0x40;
            i = j;
            if((512L & l) != 0L)
                i = j | 8;
            return i;
        }

        public Object getRemoteControlClient()
        {
            return null;
        }

        public Token getSessionToken()
        {
            return mToken;
        }

        public boolean isActive()
        {
            return mIsActive;
        }

        void postToHandler(int i)
        {
            postToHandler(i, null);
        }

        void postToHandler(int i, int j)
        {
            postToHandler(i, null, j);
        }

        void postToHandler(int i, Object obj)
        {
            postToHandler(i, obj, ((Bundle) (null)));
        }

        void postToHandler(int i, Object obj, int j)
        {
            synchronized(mLock)
            {
                if(mHandler != null)
                    mHandler.post(i, obj, j);
            }
            return;
            obj;
            obj1;
            JVM INSTR monitorexit ;
            throw obj;
        }

        void postToHandler(int i, Object obj, Bundle bundle)
        {
            synchronized(mLock)
            {
                if(mHandler != null)
                    mHandler.post(i, obj, bundle);
            }
            return;
            obj;
            obj1;
            JVM INSTR monitorexit ;
            throw obj;
        }

        void registerMediaButtonEventReceiver(PendingIntent pendingintent, ComponentName componentname)
        {
            mAudioManager.registerMediaButtonEventReceiver(componentname);
        }

        public void release()
        {
            mIsActive = false;
            mDestroyed = true;
            update();
            sendSessionDestroyed();
        }

        public void sendSessionEvent(String s, Bundle bundle)
        {
            sendEvent(s, bundle);
        }

        void sendVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onVolumeInfoChanged(parcelablevolumeinfo);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        public void setActive(boolean flag)
        {
            if(flag != mIsActive)
            {
                mIsActive = flag;
                if(update())
                {
                    setMetadata(mMetadata);
                    setPlaybackState(mState);
                    return;
                }
            }
        }

        public void setCallback(Callback callback, Handler handler)
        {
            mCallback = callback;
            if(callback != null)
            {
                callback = handler;
                if(handler == null)
                    callback = new Handler();
                synchronized(mLock)
                {
                    if(mHandler != null)
                        mHandler.removeCallbacksAndMessages(null);
                    mHandler = new MessageHandler(callback.getLooper());
                    mCallback.setSessionImpl(this, callback);
                }
                return;
            } else
            {
                return;
            }
            callback;
            handler;
            JVM INSTR monitorexit ;
            throw callback;
        }

        public void setCaptioningEnabled(boolean flag)
        {
            if(mCaptioningEnabled != flag)
            {
                mCaptioningEnabled = flag;
                sendCaptioningEnabled(flag);
            }
        }

        public void setExtras(Bundle bundle)
        {
            mExtras = bundle;
            sendExtras(bundle);
        }

        public void setFlags(int i)
        {
            synchronized(mLock)
            {
                mFlags = i;
            }
            update();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void setMediaButtonReceiver(PendingIntent pendingintent)
        {
        }

        public void setMetadata(MediaMetadataCompat mediametadatacompat)
        {
            obj = mediametadatacompat;
            if(mediametadatacompat != null)
                obj = (new android.support.v4.media.MediaMetadataCompat.Builder(mediametadatacompat, MediaSessionCompat.sMaxBitmapSize)).build();
            synchronized(mLock)
            {
                mMetadata = ((MediaMetadataCompat) (obj));
            }
            sendMetadata(((MediaMetadataCompat) (obj)));
            if(!mIsActive)
                return;
            break MISSING_BLOCK_LABEL_53;
            obj;
            mediametadatacompat;
            JVM INSTR monitorexit ;
            throw obj;
            if(obj == null)
                mediametadatacompat = null;
            else
                mediametadatacompat = ((MediaMetadataCompat) (obj)).getBundle();
            buildRccMetadata(mediametadatacompat).apply();
            return;
        }

        public void setPlaybackState(PlaybackStateCompat playbackstatecompat)
        {
            synchronized(mLock)
            {
                mState = playbackstatecompat;
            }
            sendState(playbackstatecompat);
            if(!mIsActive)
                return;
            break MISSING_BLOCK_LABEL_32;
            playbackstatecompat;
            obj;
            JVM INSTR monitorexit ;
            throw playbackstatecompat;
            if(playbackstatecompat == null)
            {
                mRcc.setPlaybackState(0);
                mRcc.setTransportControlFlags(0);
                return;
            } else
            {
                setRccState(playbackstatecompat);
                mRcc.setTransportControlFlags(getRccTransportControlFlagsFromActions(playbackstatecompat.getActions()));
                return;
            }
        }

        public void setPlaybackToLocal(int i)
        {
            if(mVolumeProvider != null)
                mVolumeProvider.setCallback(null);
            mVolumeType = 1;
            sendVolumeInfoChanged(new ParcelableVolumeInfo(mVolumeType, mLocalStream, 2, mAudioManager.getStreamMaxVolume(mLocalStream), mAudioManager.getStreamVolume(mLocalStream)));
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeprovidercompat)
        {
            if(volumeprovidercompat == null)
                throw new IllegalArgumentException("volumeProvider may not be null");
            if(mVolumeProvider != null)
                mVolumeProvider.setCallback(null);
            mVolumeType = 2;
            mVolumeProvider = volumeprovidercompat;
            sendVolumeInfoChanged(new ParcelableVolumeInfo(mVolumeType, mLocalStream, mVolumeProvider.getVolumeControl(), mVolumeProvider.getMaxVolume(), mVolumeProvider.getCurrentVolume()));
            volumeprovidercompat.setCallback(mVolumeCallback);
        }

        public void setQueue(List list)
        {
            mQueue = list;
            sendQueue(list);
        }

        public void setQueueTitle(CharSequence charsequence)
        {
            mQueueTitle = charsequence;
            sendQueueTitle(charsequence);
        }

        public void setRatingType(int i)
        {
            mRatingType = i;
        }

        void setRccState(PlaybackStateCompat playbackstatecompat)
        {
            mRcc.setPlaybackState(getRccStateFromState(playbackstatecompat.getState()));
        }

        public void setRepeatMode(int i)
        {
            if(mRepeatMode != i)
            {
                mRepeatMode = i;
                sendRepeatMode(i);
            }
        }

        public void setSessionActivity(PendingIntent pendingintent)
        {
            synchronized(mLock)
            {
                mSessionActivity = pendingintent;
            }
            return;
            pendingintent;
            obj;
            JVM INSTR monitorexit ;
            throw pendingintent;
        }

        public void setShuffleMode(int i)
        {
            if(mShuffleMode != i)
            {
                mShuffleMode = i;
                sendShuffleMode(i);
            }
        }

        public void setShuffleModeEnabled(boolean flag)
        {
            if(mShuffleModeEnabled != flag)
            {
                mShuffleModeEnabled = flag;
                sendShuffleModeEnabled(flag);
            }
        }

        void setVolumeTo(int i, int j)
        {
            if(mVolumeType == 2)
            {
                if(mVolumeProvider != null)
                    mVolumeProvider.onSetVolumeTo(i);
                return;
            } else
            {
                mAudioManager.setStreamVolume(mLocalStream, i, j);
                return;
            }
        }

        void unregisterMediaButtonEventReceiver(PendingIntent pendingintent, ComponentName componentname)
        {
            mAudioManager.unregisterMediaButtonEventReceiver(componentname);
        }

        boolean update()
        {
            boolean flag1 = false;
            if(!mIsActive) goto _L2; else goto _L1
_L1:
            if(mIsMbrRegistered || (mFlags & 1) == 0) goto _L4; else goto _L3
_L3:
            registerMediaButtonEventReceiver(mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
            mIsMbrRegistered = true;
_L12:
            if(mIsRccRegistered || (mFlags & 2) == 0) goto _L6; else goto _L5
_L5:
            boolean flag;
            mAudioManager.registerRemoteControlClient(mRcc);
            mIsRccRegistered = true;
            flag = true;
_L8:
            return flag;
_L4:
            if(mIsMbrRegistered && (mFlags & 1) == 0)
            {
                unregisterMediaButtonEventReceiver(mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
                mIsMbrRegistered = false;
            }
            continue; /* Loop/switch isn't completed */
_L6:
            flag = flag1;
            if(!mIsRccRegistered) goto _L8; else goto _L7
_L7:
            flag = flag1;
            if((mFlags & 2) != 0) goto _L8; else goto _L9
_L9:
            mRcc.setPlaybackState(0);
            mAudioManager.unregisterRemoteControlClient(mRcc);
            mIsRccRegistered = false;
            return false;
_L2:
            if(mIsMbrRegistered)
            {
                unregisterMediaButtonEventReceiver(mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
                mIsMbrRegistered = false;
            }
            flag = flag1;
            if(!mIsRccRegistered) goto _L8; else goto _L10
_L10:
            mRcc.setPlaybackState(0);
            mAudioManager.unregisterRemoteControlClient(mRcc);
            mIsRccRegistered = false;
            return false;
            if(true) goto _L12; else goto _L11
_L11:
        }

        static final int RCC_PLAYSTATE_NONE = 0;
        final AudioManager mAudioManager;
        volatile Callback mCallback;
        boolean mCaptioningEnabled;
        private final Context mContext;
        final RemoteCallbackList mControllerCallbacks = new RemoteCallbackList();
        boolean mDestroyed;
        Bundle mExtras;
        int mFlags;
        private MessageHandler mHandler;
        boolean mIsActive;
        private boolean mIsMbrRegistered;
        private boolean mIsRccRegistered;
        int mLocalStream;
        final Object mLock = new Object();
        private final ComponentName mMediaButtonReceiverComponentName;
        private final PendingIntent mMediaButtonReceiverIntent;
        MediaMetadataCompat mMetadata;
        final String mPackageName;
        List mQueue;
        CharSequence mQueueTitle;
        int mRatingType;
        final RemoteControlClient mRcc;
        int mRepeatMode;
        PendingIntent mSessionActivity;
        int mShuffleMode;
        boolean mShuffleModeEnabled;
        PlaybackStateCompat mState;
        private final MediaSessionStub mStub;
        final String mTag;
        private final Token mToken;
        private android.support.v4.media.VolumeProviderCompat.Callback mVolumeCallback;
        VolumeProviderCompat mVolumeProvider;
        int mVolumeType;

        public MediaSessionImplBase(Context context, String s, ComponentName componentname, PendingIntent pendingintent)
        {
            mDestroyed = false;
            mIsActive = false;
            mIsMbrRegistered = false;
            mIsRccRegistered = false;
            mVolumeCallback = new _cls1();
            if(componentname == null)
            {
                throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
            } else
            {
                mContext = context;
                mPackageName = context.getPackageName();
                mAudioManager = (AudioManager)context.getSystemService("audio");
                mTag = s;
                mMediaButtonReceiverComponentName = componentname;
                mMediaButtonReceiverIntent = pendingintent;
                mStub = new MediaSessionStub();
                mToken = new Token(mStub);
                mRatingType = 0;
                mVolumeType = 1;
                mLocalStream = 3;
                mRcc = new RemoteControlClient(pendingintent);
                return;
            }
        }
    }

    private static final class MediaSessionImplBase.Command
    {

        public final String command;
        public final Bundle extras;
        public final ResultReceiver stub;

        public MediaSessionImplBase.Command(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            command = s;
            extras = bundle;
            stub = resultreceiver;
        }
    }

    class MediaSessionImplBase.MediaSessionStub extends IMediaSession.Stub
    {

        public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            postToHandler(25, mediadescriptioncompat);
        }

        public void addQueueItemAt(MediaDescriptionCompat mediadescriptioncompat, int i)
        {
            postToHandler(26, mediadescriptioncompat, i);
        }

        public void adjustVolume(int i, int j, String s)
        {
            MediaSessionImplBase.this.adjustVolume(i, j);
        }

        public void fastForward()
            throws RemoteException
        {
            postToHandler(16);
        }

        public Bundle getExtras()
        {
            Bundle bundle;
            synchronized(mLock)
            {
                bundle = mExtras;
            }
            return bundle;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public long getFlags()
        {
            long l;
            synchronized(mLock)
            {
                l = mFlags;
            }
            return l;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public PendingIntent getLaunchPendingIntent()
        {
            PendingIntent pendingintent;
            synchronized(mLock)
            {
                pendingintent = mSessionActivity;
            }
            return pendingintent;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public MediaMetadataCompat getMetadata()
        {
            return mMetadata;
        }

        public String getPackageName()
        {
            return mPackageName;
        }

        public PlaybackStateCompat getPlaybackState()
        {
            PlaybackStateCompat playbackstatecompat;
            MediaMetadataCompat mediametadatacompat;
            synchronized(mLock)
            {
                playbackstatecompat = mState;
                mediametadatacompat = mMetadata;
            }
            return MediaSessionCompat.getStateWithUpdatedPosition(playbackstatecompat, mediametadatacompat);
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public List getQueue()
        {
            List list;
            synchronized(mLock)
            {
                list = mQueue;
            }
            return list;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public CharSequence getQueueTitle()
        {
            return mQueueTitle;
        }

        public int getRatingType()
        {
            return mRatingType;
        }

        public int getRepeatMode()
        {
            return mRepeatMode;
        }

        public int getShuffleMode()
        {
            return mShuffleMode;
        }

        public String getTag()
        {
            return mTag;
        }

        public ParcelableVolumeInfo getVolumeAttributes()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            int l;
            int i1;
            VolumeProviderCompat volumeprovidercompat;
            l = mVolumeType;
            i1 = mLocalStream;
            volumeprovidercompat = mVolumeProvider;
            if(l != 2) goto _L2; else goto _L1
_L1:
            int i;
            int j;
            int k;
            i = volumeprovidercompat.getVolumeControl();
            j = volumeprovidercompat.getMaxVolume();
            k = volumeprovidercompat.getCurrentVolume();
_L3:
            obj;
            JVM INSTR monitorexit ;
            return new ParcelableVolumeInfo(l, i1, i, j, k);
_L2:
            i = 2;
            j = mAudioManager.getStreamMaxVolume(i1);
            k = mAudioManager.getStreamVolume(i1);
              goto _L3
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public boolean isCaptioningEnabled()
        {
            return mCaptioningEnabled;
        }

        public boolean isShuffleModeEnabledDeprecated()
        {
            return mShuffleModeEnabled;
        }

        public boolean isTransportControlEnabled()
        {
            return (mFlags & 2) != 0;
        }

        public void next()
            throws RemoteException
        {
            postToHandler(14);
        }

        public void pause()
            throws RemoteException
        {
            postToHandler(12);
        }

        public void play()
            throws RemoteException
        {
            postToHandler(7);
        }

        public void playFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(8, s, bundle);
        }

        public void playFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(9, s, bundle);
        }

        public void playFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            postToHandler(10, uri, bundle);
        }

        public void prepare()
            throws RemoteException
        {
            postToHandler(3);
        }

        public void prepareFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(4, s, bundle);
        }

        public void prepareFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(5, s, bundle);
        }

        public void prepareFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            postToHandler(6, uri, bundle);
        }

        public void previous()
            throws RemoteException
        {
            postToHandler(15);
        }

        public void rate(RatingCompat ratingcompat)
            throws RemoteException
        {
            postToHandler(19, ratingcompat);
        }

        public void rateWithExtras(RatingCompat ratingcompat, Bundle bundle)
            throws RemoteException
        {
            postToHandler(31, ratingcompat, bundle);
        }

        public void registerCallbackListener(IMediaControllerCallback imediacontrollercallback)
        {
            if(mDestroyed)
            {
                try
                {
                    imediacontrollercallback.onSessionDestroyed();
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(IMediaControllerCallback imediacontrollercallback)
                {
                    return;
                }
            } else
            {
                mControllerCallbacks.register(imediacontrollercallback);
                return;
            }
        }

        public void removeQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        {
            postToHandler(27, mediadescriptioncompat);
        }

        public void removeQueueItemAt(int i)
        {
            postToHandler(28, i);
        }

        public void rewind()
            throws RemoteException
        {
            postToHandler(17);
        }

        public void seekTo(long l)
            throws RemoteException
        {
            postToHandler(18, Long.valueOf(l));
        }

        public void sendCommand(String s, Bundle bundle, ResultReceiverWrapper resultreceiverwrapper)
        {
            postToHandler(1, new MediaSessionImplBase.Command(s, bundle, resultreceiverwrapper.mResultReceiver));
        }

        public void sendCustomAction(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(20, s, bundle);
        }

        public boolean sendMediaButton(KeyEvent keyevent)
        {
            boolean flag;
            if((mFlags & 1) != 0)
                flag = true;
            else
                flag = false;
            if(flag)
                postToHandler(21, keyevent);
            return flag;
        }

        public void setCaptioningEnabled(boolean flag)
            throws RemoteException
        {
            postToHandler(29, Boolean.valueOf(flag));
        }

        public void setRepeatMode(int i)
            throws RemoteException
        {
            postToHandler(23, i);
        }

        public void setShuffleMode(int i)
            throws RemoteException
        {
            postToHandler(30, i);
        }

        public void setShuffleModeEnabledDeprecated(boolean flag)
            throws RemoteException
        {
            postToHandler(24, Boolean.valueOf(flag));
        }

        public void setVolumeTo(int i, int j, String s)
        {
            MediaSessionImplBase.this.setVolumeTo(i, j);
        }

        public void skipToQueueItem(long l)
        {
            postToHandler(11, Long.valueOf(l));
        }

        public void stop()
            throws RemoteException
        {
            postToHandler(13);
        }

        public void unregisterCallbackListener(IMediaControllerCallback imediacontrollercallback)
        {
            mControllerCallbacks.unregister(imediacontrollercallback);
        }

        final MediaSessionImplBase this$0;

        MediaSessionImplBase.MediaSessionStub()
        {
            this$0 = MediaSessionImplBase.this;
            super();
        }
    }

    class MediaSessionImplBase.MessageHandler extends Handler
    {

        private void onMediaButtonEvent(KeyEvent keyevent, Callback callback)
        {
            if(keyevent != null && keyevent.getAction() == 0) goto _L2; else goto _L1
_L1:
            return;
_L2:
            long l;
            if(mState == null)
                l = 0L;
            else
                l = mState.getActions();
            keyevent.getKeyCode();
            JVM INSTR lookupswitch 9: default 112
        //                       79: 113
        //                       85: 113
        //                       86: 196
        //                       87: 166
        //                       88: 181
        //                       89: 224
        //                       90: 209
        //                       126: 136
        //                       127: 151;
               goto _L3 _L4 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L8:
            continue; /* Loop/switch isn't completed */
_L3:
            return;
_L4:
            Log.w("MediaSessionCompat", "KEYCODE_MEDIA_PLAY_PAUSE and KEYCODE_HEADSETHOOK are handled already");
            return;
_L10:
            if((4L & l) == 0L) goto _L1; else goto _L12
_L12:
            callback.onPlay();
            return;
_L11:
            if((2L & l) == 0L) goto _L1; else goto _L13
_L13:
            callback.onPause();
            return;
_L6:
            if((32L & l) == 0L) goto _L1; else goto _L14
_L14:
            callback.onSkipToNext();
            return;
_L7:
            if((16L & l) == 0L) goto _L1; else goto _L15
_L15:
            callback.onSkipToPrevious();
            return;
_L5:
            if((1L & l) == 0L) goto _L1; else goto _L16
_L16:
            callback.onStop();
            return;
_L9:
            if((64L & l) == 0L) goto _L1; else goto _L17
_L17:
            callback.onFastForward();
            return;
            if((8L & l) == 0L) goto _L1; else goto _L18
_L18:
            callback.onRewind();
            return;
        }

        public void handleMessage(Message message)
        {
            Callback callback = mCallback;
            if(callback != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            switch(message.what)
            {
            default:
                return;

            case 2: // '\002'
                break; /* Loop/switch isn't completed */

            case 22: // '\026'
                break MISSING_BLOCK_LABEL_560;

            case 23: // '\027'
                break MISSING_BLOCK_LABEL_588;

            case 24: // '\030'
                break MISSING_BLOCK_LABEL_597;

            case 29: // '\035'
                break MISSING_BLOCK_LABEL_573;

            case 30: // '\036'
                break MISSING_BLOCK_LABEL_612;

            case 1: // '\001'
                message = (MediaSessionImplBase.Command)message.obj;
                callback.onCommand(((MediaSessionImplBase.Command) (message)).command, ((MediaSessionImplBase.Command) (message)).extras, ((MediaSessionImplBase.Command) (message)).stub);
                return;

            case 21: // '\025'
                message = (KeyEvent)message.obj;
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                intent.putExtra("android.intent.extra.KEY_EVENT", message);
                if(!callback.onMediaButtonEvent(intent))
                {
                    onMediaButtonEvent(message, callback);
                    return;
                }
                break;

            case 3: // '\003'
                callback.onPrepare();
                return;

            case 4: // '\004'
                callback.onPrepareFromMediaId((String)message.obj, message.getData());
                return;

            case 5: // '\005'
                callback.onPrepareFromSearch((String)message.obj, message.getData());
                return;

            case 6: // '\006'
                callback.onPrepareFromUri((Uri)message.obj, message.getData());
                return;

            case 7: // '\007'
                callback.onPlay();
                return;

            case 8: // '\b'
                callback.onPlayFromMediaId((String)message.obj, message.getData());
                return;

            case 9: // '\t'
                callback.onPlayFromSearch((String)message.obj, message.getData());
                return;

            case 10: // '\n'
                callback.onPlayFromUri((Uri)message.obj, message.getData());
                return;

            case 11: // '\013'
                callback.onSkipToQueueItem(((Long)message.obj).longValue());
                return;

            case 12: // '\f'
                callback.onPause();
                return;

            case 13: // '\r'
                callback.onStop();
                return;

            case 14: // '\016'
                callback.onSkipToNext();
                return;

            case 15: // '\017'
                callback.onSkipToPrevious();
                return;

            case 16: // '\020'
                callback.onFastForward();
                return;

            case 17: // '\021'
                callback.onRewind();
                return;

            case 18: // '\022'
                callback.onSeekTo(((Long)message.obj).longValue());
                return;

            case 19: // '\023'
                callback.onSetRating((RatingCompat)message.obj);
                return;

            case 31: // '\037'
                callback.onSetRating((RatingCompat)message.obj, message.getData());
                return;

            case 20: // '\024'
                callback.onCustomAction((String)message.obj, message.getData());
                return;

            case 25: // '\031'
                callback.onAddQueueItem((MediaDescriptionCompat)message.obj);
                return;

            case 26: // '\032'
                callback.onAddQueueItem((MediaDescriptionCompat)message.obj, message.arg1);
                return;

            case 27: // '\033'
                callback.onRemoveQueueItem((MediaDescriptionCompat)message.obj);
                return;

            case 28: // '\034'
                if(mQueue != null)
                    break MISSING_BLOCK_LABEL_483;
                break;
            }
            if(true) goto _L1; else goto _L3
_L3:
            break; /* Loop/switch isn't completed */
            if(message.arg1 >= 0 && message.arg1 < mQueue.size())
                message = (QueueItem)mQueue.get(message.arg1);
            else
                message = null;
            if(message != null)
            {
                callback.onRemoveQueueItem(message.getDescription());
                return;
            }
            if(true) goto _L1; else goto _L4
_L4:
            adjustVolume(message.arg1, 0);
            return;
            setVolumeTo(message.arg1, 0);
            return;
            callback.onSetCaptioningEnabled(((Boolean)message.obj).booleanValue());
            return;
            callback.onSetRepeatMode(message.arg1);
            return;
            callback.onSetShuffleModeEnabled(((Boolean)message.obj).booleanValue());
            return;
            callback.onSetShuffleMode(message.arg1);
            return;
        }

        public void post(int i)
        {
            post(i, null);
        }

        public void post(int i, Object obj)
        {
            obtainMessage(i, obj).sendToTarget();
        }

        public void post(int i, Object obj, int j)
        {
            obtainMessage(i, j, 0, obj).sendToTarget();
        }

        public void post(int i, Object obj, Bundle bundle)
        {
            obj = obtainMessage(i, obj);
            ((Message) (obj)).setData(bundle);
            ((Message) (obj)).sendToTarget();
        }

        private static final int KEYCODE_MEDIA_PAUSE = 127;
        private static final int KEYCODE_MEDIA_PLAY = 126;
        private static final int MSG_ADD_QUEUE_ITEM = 25;
        private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
        private static final int MSG_ADJUST_VOLUME = 2;
        private static final int MSG_COMMAND = 1;
        private static final int MSG_CUSTOM_ACTION = 20;
        private static final int MSG_FAST_FORWARD = 16;
        private static final int MSG_MEDIA_BUTTON = 21;
        private static final int MSG_NEXT = 14;
        private static final int MSG_PAUSE = 12;
        private static final int MSG_PLAY = 7;
        private static final int MSG_PLAY_MEDIA_ID = 8;
        private static final int MSG_PLAY_SEARCH = 9;
        private static final int MSG_PLAY_URI = 10;
        private static final int MSG_PREPARE = 3;
        private static final int MSG_PREPARE_MEDIA_ID = 4;
        private static final int MSG_PREPARE_SEARCH = 5;
        private static final int MSG_PREPARE_URI = 6;
        private static final int MSG_PREVIOUS = 15;
        private static final int MSG_RATE = 19;
        private static final int MSG_RATE_EXTRA = 31;
        private static final int MSG_REMOVE_QUEUE_ITEM = 27;
        private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
        private static final int MSG_REWIND = 17;
        private static final int MSG_SEEK_TO = 18;
        private static final int MSG_SET_CAPTIONING_ENABLED = 29;
        private static final int MSG_SET_REPEAT_MODE = 23;
        private static final int MSG_SET_SHUFFLE_MODE = 30;
        private static final int MSG_SET_SHUFFLE_MODE_ENABLED = 24;
        private static final int MSG_SET_VOLUME = 22;
        private static final int MSG_SKIP_TO_ITEM = 11;
        private static final int MSG_STOP = 13;
        final MediaSessionImplBase this$0;

        public MediaSessionImplBase.MessageHandler(Looper looper)
        {
            this$0 = MediaSessionImplBase.this;
            super(looper);
        }
    }

    public static interface OnActiveChangeListener
    {

        public abstract void onActiveChanged();
    }

    public static final class QueueItem
        implements Parcelable
    {

        public static QueueItem fromQueueItem(Object obj)
        {
            if(obj == null || android.os.Build.VERSION.SDK_INT < 21)
                return null;
            else
                return new QueueItem(obj, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21.QueueItem.getDescription(obj)), MediaSessionCompatApi21.QueueItem.getQueueId(obj));
        }

        public static List fromQueueItemList(List list)
        {
            if(list != null && android.os.Build.VERSION.SDK_INT >= 21) goto _L2; else goto _L1
_L1:
            list = null;
_L4:
            return list;
_L2:
            ArrayList arraylist = new ArrayList();
            Iterator iterator = list.iterator();
            do
            {
                list = arraylist;
                if(!iterator.hasNext())
                    continue;
                arraylist.add(fromQueueItem(iterator.next()));
            } while(true);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public int describeContents()
        {
            return 0;
        }

        public MediaDescriptionCompat getDescription()
        {
            return mDescription;
        }

        public long getQueueId()
        {
            return mId;
        }

        public Object getQueueItem()
        {
            if(mItem != null || android.os.Build.VERSION.SDK_INT < 21)
            {
                return mItem;
            } else
            {
                mItem = MediaSessionCompatApi21.QueueItem.createItem(mDescription.getMediaDescription(), mId);
                return mItem;
            }
        }

        public String toString()
        {
            return (new StringBuilder()).append("MediaSession.QueueItem {Description=").append(mDescription).append(", Id=").append(mId).append(" }").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            mDescription.writeToParcel(parcel, i);
            parcel.writeLong(mId);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public QueueItem createFromParcel(Parcel parcel)
            {
                return new QueueItem(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public QueueItem[] newArray(int i)
            {
                return new QueueItem[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int UNKNOWN_ID = -1;
        private final MediaDescriptionCompat mDescription;
        private final long mId;
        private Object mItem;


        QueueItem(Parcel parcel)
        {
            mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            mId = parcel.readLong();
        }

        public QueueItem(MediaDescriptionCompat mediadescriptioncompat, long l)
        {
            this(null, mediadescriptioncompat, l);
        }

        private QueueItem(Object obj, MediaDescriptionCompat mediadescriptioncompat, long l)
        {
            if(mediadescriptioncompat == null)
                throw new IllegalArgumentException("Description cannot be null.");
            if(l == -1L)
            {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            } else
            {
                mDescription = mediadescriptioncompat;
                mId = l;
                mItem = obj;
                return;
            }
        }
    }

    static final class ResultReceiverWrapper
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            mResultReceiver.writeToParcel(parcel, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ResultReceiverWrapper createFromParcel(Parcel parcel)
            {
                return new ResultReceiverWrapper(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ResultReceiverWrapper[] newArray(int i)
            {
                return new ResultReceiverWrapper[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private ResultReceiver mResultReceiver;



        ResultReceiverWrapper(Parcel parcel)
        {
            mResultReceiver = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        public ResultReceiverWrapper(ResultReceiver resultreceiver)
        {
            mResultReceiver = resultreceiver;
        }
    }

    public static interface SessionFlags
        extends Annotation
    {
    }

    public static final class Token
        implements Parcelable
    {

        public static Token fromToken(Object obj)
        {
            return fromToken(obj, null);
        }

        public static Token fromToken(Object obj, IMediaSession imediasession)
        {
            if(obj != null && android.os.Build.VERSION.SDK_INT >= 21)
                return new Token(MediaSessionCompatApi21.verifyToken(obj), imediasession);
            else
                return null;
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this != obj)
            {
                if(!(obj instanceof Token))
                    return false;
                obj = (Token)obj;
                if(mInner == null)
                {
                    if(((Token) (obj)).mInner != null)
                        return false;
                } else
                if(((Token) (obj)).mInner == null)
                    return false;
                else
                    return mInner.equals(((Token) (obj)).mInner);
            }
            return true;
        }

        public IMediaSession getExtraBinder()
        {
            return mExtraBinder;
        }

        public Object getToken()
        {
            return mInner;
        }

        public int hashCode()
        {
            if(mInner == null)
                return 0;
            else
                return mInner.hashCode();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            if(android.os.Build.VERSION.SDK_INT >= 21)
            {
                parcel.writeParcelable((Parcelable)mInner, i);
                return;
            } else
            {
                parcel.writeStrongBinder((IBinder)mInner);
                return;
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Token createFromParcel(Parcel parcel)
            {
                if(android.os.Build.VERSION.SDK_INT >= 21)
                    parcel = parcel.readParcelable(null);
                else
                    parcel = parcel.readStrongBinder();
                return new Token(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Token[] newArray(int i)
            {
                return new Token[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final IMediaSession mExtraBinder;
        private final Object mInner;


        Token(Object obj)
        {
            this(obj, null);
        }

        Token(Object obj, IMediaSession imediasession)
        {
            mInner = obj;
            mExtraBinder = imediasession;
        }
    }


    private MediaSessionCompat(Context context, MediaSessionImpl mediasessionimpl)
    {
        mActiveListeners = new ArrayList();
        mImpl = mediasessionimpl;
        if(android.os.Build.VERSION.SDK_INT >= 21 && !MediaSessionCompatApi21.hasCallback(mediasessionimpl.getMediaSession()))
            setCallback(new Callback() {

                final MediaSessionCompat this$0;

            
            {
                this$0 = MediaSessionCompat.this;
                super();
            }
            }
);
        mController = new MediaControllerCompat(context, this);
    }

    public MediaSessionCompat(Context context, String s)
    {
        this(context, s, null, null);
    }

    public MediaSessionCompat(Context context, String s, ComponentName componentname, PendingIntent pendingintent)
    {
        mActiveListeners = new ArrayList();
        if(context == null)
            throw new IllegalArgumentException("context must not be null");
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("tag must not be null or empty");
        ComponentName componentname1 = componentname;
        if(componentname == null)
        {
            componentname = MediaButtonReceiver.getMediaButtonReceiverComponent(context);
            componentname1 = componentname;
            if(componentname == null)
            {
                Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
                componentname1 = componentname;
            }
        }
        componentname = pendingintent;
        if(componentname1 != null)
        {
            componentname = pendingintent;
            if(pendingintent == null)
            {
                componentname = new Intent("android.intent.action.MEDIA_BUTTON");
                componentname.setComponent(componentname1);
                componentname = PendingIntent.getBroadcast(context, 0, componentname, 0);
            }
        }
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            mImpl = new MediaSessionImplApi21(context, s);
            setCallback(new Callback() {

                final MediaSessionCompat this$0;

            
            {
                this$0 = MediaSessionCompat.this;
                super();
            }
            }
);
            mImpl.setMediaButtonReceiver(componentname);
        } else
        if(android.os.Build.VERSION.SDK_INT >= 19)
            mImpl = new MediaSessionImplApi19(context, s, componentname1, componentname);
        else
        if(android.os.Build.VERSION.SDK_INT >= 18)
            mImpl = new MediaSessionImplApi18(context, s, componentname1, componentname);
        else
            mImpl = new MediaSessionImplBase(context, s, componentname1, componentname);
        mController = new MediaControllerCompat(context, this);
        if(sMaxBitmapSize == 0)
            sMaxBitmapSize = (int)TypedValue.applyDimension(1, 320F, context.getResources().getDisplayMetrics());
    }

    public static MediaSessionCompat fromMediaSession(Context context, Object obj)
    {
        if(context != null && obj != null && android.os.Build.VERSION.SDK_INT >= 21)
            return new MediaSessionCompat(context, new MediaSessionImplApi21(obj));
        else
            return null;
    }

    private static PlaybackStateCompat getStateWithUpdatedPosition(PlaybackStateCompat playbackstatecompat, MediaMetadataCompat mediametadatacompat)
    {
_L2:
        return playbackstatecompat;
        if(playbackstatecompat == null || playbackstatecompat.getPosition() == -1L || playbackstatecompat.getState() != 3 && playbackstatecompat.getState() != 4 && playbackstatecompat.getState() != 5) goto _L2; else goto _L1
_L1:
        long l = playbackstatecompat.getLastPositionUpdateTime();
        if(l <= 0L) goto _L2; else goto _L3
_L3:
        long l1;
        long l3;
        l3 = SystemClock.elapsedRealtime();
        l1 = (long)(playbackstatecompat.getPlaybackSpeed() * (float)(l3 - l)) + playbackstatecompat.getPosition();
        long l2 = -1L;
        l = l2;
        if(mediametadatacompat != null)
        {
            l = l2;
            if(mediametadatacompat.containsKey("android.media.metadata.DURATION"))
                l = mediametadatacompat.getLong("android.media.metadata.DURATION");
        }
        if(l < 0L || l1 <= l) goto _L5; else goto _L4
_L4:
        return (new PlaybackStateCompat.Builder(playbackstatecompat)).setState(playbackstatecompat.getState(), l, playbackstatecompat.getPlaybackSpeed(), l3).build();
_L5:
        l = l1;
        if(l1 < 0L)
            l = 0L;
        if(true) goto _L4; else goto _L6
_L6:
    }

    public void addOnActiveChangeListener(OnActiveChangeListener onactivechangelistener)
    {
        if(onactivechangelistener == null)
        {
            throw new IllegalArgumentException("Listener may not be null");
        } else
        {
            mActiveListeners.add(onactivechangelistener);
            return;
        }
    }

    public String getCallingPackage()
    {
        return mImpl.getCallingPackage();
    }

    public MediaControllerCompat getController()
    {
        return mController;
    }

    public Object getMediaSession()
    {
        return mImpl.getMediaSession();
    }

    public Object getRemoteControlClient()
    {
        return mImpl.getRemoteControlClient();
    }

    public Token getSessionToken()
    {
        return mImpl.getSessionToken();
    }

    public boolean isActive()
    {
        return mImpl.isActive();
    }

    public void release()
    {
        mImpl.release();
    }

    public void removeOnActiveChangeListener(OnActiveChangeListener onactivechangelistener)
    {
        if(onactivechangelistener == null)
        {
            throw new IllegalArgumentException("Listener may not be null");
        } else
        {
            mActiveListeners.remove(onactivechangelistener);
            return;
        }
    }

    public void sendSessionEvent(String s, Bundle bundle)
    {
        if(TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("event cannot be null or empty");
        } else
        {
            mImpl.sendSessionEvent(s, bundle);
            return;
        }
    }

    public void setActive(boolean flag)
    {
        mImpl.setActive(flag);
        for(Iterator iterator = mActiveListeners.iterator(); iterator.hasNext(); ((OnActiveChangeListener)iterator.next()).onActiveChanged());
    }

    public void setCallback(Callback callback)
    {
        setCallback(callback, null);
    }

    public void setCallback(Callback callback, Handler handler)
    {
        MediaSessionImpl mediasessionimpl = mImpl;
        if(handler == null)
            handler = new Handler();
        mediasessionimpl.setCallback(callback, handler);
    }

    public void setCaptioningEnabled(boolean flag)
    {
        mImpl.setCaptioningEnabled(flag);
    }

    public void setExtras(Bundle bundle)
    {
        mImpl.setExtras(bundle);
    }

    public void setFlags(int i)
    {
        mImpl.setFlags(i);
    }

    public void setMediaButtonReceiver(PendingIntent pendingintent)
    {
        mImpl.setMediaButtonReceiver(pendingintent);
    }

    public void setMetadata(MediaMetadataCompat mediametadatacompat)
    {
        mImpl.setMetadata(mediametadatacompat);
    }

    public void setPlaybackState(PlaybackStateCompat playbackstatecompat)
    {
        mImpl.setPlaybackState(playbackstatecompat);
    }

    public void setPlaybackToLocal(int i)
    {
        mImpl.setPlaybackToLocal(i);
    }

    public void setPlaybackToRemote(VolumeProviderCompat volumeprovidercompat)
    {
        if(volumeprovidercompat == null)
        {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        } else
        {
            mImpl.setPlaybackToRemote(volumeprovidercompat);
            return;
        }
    }

    public void setQueue(List list)
    {
        mImpl.setQueue(list);
    }

    public void setQueueTitle(CharSequence charsequence)
    {
        mImpl.setQueueTitle(charsequence);
    }

    public void setRatingType(int i)
    {
        mImpl.setRatingType(i);
    }

    public void setRepeatMode(int i)
    {
        mImpl.setRepeatMode(i);
    }

    public void setSessionActivity(PendingIntent pendingintent)
    {
        mImpl.setSessionActivity(pendingintent);
    }

    public void setShuffleMode(int i)
    {
        mImpl.setShuffleMode(i);
    }

    public void setShuffleModeEnabled(boolean flag)
    {
        mImpl.setShuffleModeEnabled(flag);
    }

    static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";
    static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
    static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
    static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
    static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";
    static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";
    static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";
    static final String ACTION_ARGUMENT_SHUFFLE_MODE_ENABLED = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED";
    static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
    public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";
    static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
    static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
    static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
    static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
    static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
    static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";
    static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";
    static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";
    static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
    static final String ACTION_SET_SHUFFLE_MODE_ENABLED = "android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED";
    public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
    public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
    static final String EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    private static final int MAX_BITMAP_SIZE_IN_DP = 320;
    public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
    public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
    public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
    static final String TAG = "MediaSessionCompat";
    static int sMaxBitmapSize;
    private final ArrayList mActiveListeners;
    private final MediaControllerCompat mController;
    private final MediaSessionImpl mImpl;


    // Unreferenced inner class android/support/v4/media/session/MediaSessionCompat$MediaSessionImplBase$1

/* anonymous class */
    class MediaSessionImplBase._cls1 extends android.support.v4.media.VolumeProviderCompat.Callback
    {

        public void onVolumeChanged(VolumeProviderCompat volumeprovidercompat)
        {
            if(mVolumeProvider != volumeprovidercompat)
            {
                return;
            } else
            {
                volumeprovidercompat = new ParcelableVolumeInfo(mVolumeType, mLocalStream, volumeprovidercompat.getVolumeControl(), volumeprovidercompat.getMaxVolume(), volumeprovidercompat.getCurrentVolume());
                sendVolumeInfoChanged(volumeprovidercompat);
                return;
            }
        }

        final MediaSessionImplBase this$0;

            
            {
                this$0 = MediaSessionImplBase.this;
                super();
            }
    }

}
