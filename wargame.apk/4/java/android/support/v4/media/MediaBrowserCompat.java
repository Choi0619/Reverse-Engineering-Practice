// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.content.*;
import android.os.*;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompatApi21, MediaBrowserCompatApi23, MediaBrowserCompatApi24, MediaDescriptionCompat, 
//            MediaBrowserCompatUtils

public final class MediaBrowserCompat
{
    private static class CallbackHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(mCallbacksMessengerRef != null && mCallbacksMessengerRef.get() != null && mCallbackImplRef.get() != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            MediaBrowserServiceCallbackImpl mediabrowserservicecallbackimpl;
            Messenger messenger;
            Object obj;
            obj = message.getData();
            ((Bundle) (obj)).setClassLoader(android/support/v4/media/session/MediaSessionCompat.getClassLoader());
            mediabrowserservicecallbackimpl = (MediaBrowserServiceCallbackImpl)mCallbackImplRef.get();
            messenger = (Messenger)mCallbacksMessengerRef.get();
            message.what;
            JVM INSTR tableswitch 1 3: default 238
        //                       1 169
        //                       2 201
        //                       3 209;
               goto _L3 _L4 _L5 _L6
_L3:
            Log.w("MediaBrowserCompat", (new StringBuilder()).append("Unhandled message: ").append(message).append("\n  Client version: ").append(1).append("\n  Service version: ").append(message.arg1).toString());
            return;
_L4:
            try
            {
                mediabrowserservicecallbackimpl.onServiceConnected(messenger, ((Bundle) (obj)).getString("data_media_item_id"), (android.support.v4.media.session.MediaSessionCompat.Token)((Bundle) (obj)).getParcelable("data_media_session_token"), ((Bundle) (obj)).getBundle("data_root_hints"));
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
            }
            if(message.what == 1)
            {
                mediabrowserservicecallbackimpl.onConnectionFailed(messenger);
                return;
            }
              goto _L1
_L5:
            mediabrowserservicecallbackimpl.onConnectionFailed(messenger);
            return;
_L6:
            mediabrowserservicecallbackimpl.onLoadChildren(messenger, ((Bundle) (obj)).getString("data_media_item_id"), ((Bundle) (obj)).getParcelableArrayList("data_media_item_list"), ((Bundle) (obj)).getBundle("data_options"));
            return;
        }

        void setCallbacksMessenger(Messenger messenger)
        {
            mCallbacksMessengerRef = new WeakReference(messenger);
        }

        private final WeakReference mCallbackImplRef;
        private WeakReference mCallbacksMessengerRef;

        CallbackHandler(MediaBrowserServiceCallbackImpl mediabrowserservicecallbackimpl)
        {
            mCallbackImplRef = new WeakReference(mediabrowserservicecallbackimpl);
        }
    }

    public static class ConnectionCallback
    {

        public void onConnected()
        {
        }

        public void onConnectionFailed()
        {
        }

        public void onConnectionSuspended()
        {
        }

        void setInternalConnectionCallback(ConnectionCallbackInternal connectioncallbackinternal)
        {
            mConnectionCallbackInternal = connectioncallbackinternal;
        }

        ConnectionCallbackInternal mConnectionCallbackInternal;
        final Object mConnectionCallbackObj;

        public ConnectionCallback()
        {
            if(android.os.Build.VERSION.SDK_INT >= 21)
            {
                mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21());
                return;
            } else
            {
                mConnectionCallbackObj = null;
                return;
            }
        }
    }

    static interface ConnectionCallback.ConnectionCallbackInternal
    {

        public abstract void onConnected();

        public abstract void onConnectionFailed();

        public abstract void onConnectionSuspended();
    }

    private class ConnectionCallback.StubApi21
        implements MediaBrowserCompatApi21.ConnectionCallback
    {

        public void onConnected()
        {
            if(mConnectionCallbackInternal != null)
                mConnectionCallbackInternal.onConnected();
            ConnectionCallback.this.onConnected();
        }

        public void onConnectionFailed()
        {
            if(mConnectionCallbackInternal != null)
                mConnectionCallbackInternal.onConnectionFailed();
            ConnectionCallback.this.onConnectionFailed();
        }

        public void onConnectionSuspended()
        {
            if(mConnectionCallbackInternal != null)
                mConnectionCallbackInternal.onConnectionSuspended();
            ConnectionCallback.this.onConnectionSuspended();
        }

        final ConnectionCallback this$0;

        ConnectionCallback.StubApi21()
        {
            this$0 = ConnectionCallback.this;
            super();
        }
    }

    public static abstract class CustomActionCallback
    {

        public void onError(String s, Bundle bundle, Bundle bundle1)
        {
        }

        public void onProgressUpdate(String s, Bundle bundle, Bundle bundle1)
        {
        }

        public void onResult(String s, Bundle bundle, Bundle bundle1)
        {
        }

        public CustomActionCallback()
        {
        }
    }

    private static class CustomActionResultReceiver extends ResultReceiver
    {

        protected void onReceiveResult(int i, Bundle bundle)
        {
            if(mCallback == null)
                return;
            switch(i)
            {
            default:
                Log.w("MediaBrowserCompat", (new StringBuilder()).append("Unknown result code: ").append(i).append(" (extras=").append(mExtras).append(", resultData=").append(bundle).append(")").toString());
                return;

            case 1: // '\001'
                mCallback.onProgressUpdate(mAction, mExtras, bundle);
                return;

            case 0: // '\0'
                mCallback.onResult(mAction, mExtras, bundle);
                return;

            case -1: 
                mCallback.onError(mAction, mExtras, bundle);
                return;
            }
        }

        private final String mAction;
        private final CustomActionCallback mCallback;
        private final Bundle mExtras;

        CustomActionResultReceiver(String s, Bundle bundle, CustomActionCallback customactioncallback, Handler handler)
        {
            super(handler);
            mAction = s;
            mExtras = bundle;
            mCallback = customactioncallback;
        }
    }

    public static abstract class ItemCallback
    {

        public void onError(String s)
        {
        }

        public void onItemLoaded(MediaItem mediaitem)
        {
        }

        final Object mItemCallbackObj;

        public ItemCallback()
        {
            if(android.os.Build.VERSION.SDK_INT >= 23)
            {
                mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23());
                return;
            } else
            {
                mItemCallbackObj = null;
                return;
            }
        }
    }

    private class ItemCallback.StubApi23
        implements MediaBrowserCompatApi23.ItemCallback
    {

        public void onError(String s)
        {
            ItemCallback.this.onError(s);
        }

        public void onItemLoaded(Parcel parcel)
        {
            if(parcel == null)
            {
                ItemCallback.this.onItemLoaded(null);
                return;
            } else
            {
                parcel.setDataPosition(0);
                MediaItem mediaitem = (MediaItem)MediaItem.CREATOR.createFromParcel(parcel);
                parcel.recycle();
                ItemCallback.this.onItemLoaded(mediaitem);
                return;
            }
        }

        final ItemCallback this$0;

        ItemCallback.StubApi23()
        {
            this$0 = ItemCallback.this;
            super();
        }
    }

    private static class ItemReceiver extends ResultReceiver
    {

        protected void onReceiveResult(int i, Bundle bundle)
        {
            if(bundle != null)
                bundle.setClassLoader(android/support/v4/media/MediaBrowserCompat.getClassLoader());
            if(i != 0 || bundle == null || !bundle.containsKey("media_item"))
            {
                mCallback.onError(mMediaId);
                return;
            }
            bundle = bundle.getParcelable("media_item");
            if(bundle == null || (bundle instanceof MediaItem))
            {
                mCallback.onItemLoaded((MediaItem)bundle);
                return;
            } else
            {
                mCallback.onError(mMediaId);
                return;
            }
        }

        private final ItemCallback mCallback;
        private final String mMediaId;

        ItemReceiver(String s, ItemCallback itemcallback, Handler handler)
        {
            super(handler);
            mMediaId = s;
            mCallback = itemcallback;
        }
    }

    static interface MediaBrowserImpl
    {

        public abstract void connect();

        public abstract void disconnect();

        public abstract Bundle getExtras();

        public abstract void getItem(String s, ItemCallback itemcallback);

        public abstract String getRoot();

        public abstract ComponentName getServiceComponent();

        public abstract android.support.v4.media.session.MediaSessionCompat.Token getSessionToken();

        public abstract boolean isConnected();

        public abstract void search(String s, Bundle bundle, SearchCallback searchcallback);

        public abstract void sendCustomAction(String s, Bundle bundle, CustomActionCallback customactioncallback);

        public abstract void subscribe(String s, Bundle bundle, SubscriptionCallback subscriptioncallback);

        public abstract void unsubscribe(String s, SubscriptionCallback subscriptioncallback);
    }

    static class MediaBrowserImplApi21
        implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl, ConnectionCallback.ConnectionCallbackInternal
    {

        public void connect()
        {
            MediaBrowserCompatApi21.connect(mBrowserObj);
        }

        public void disconnect()
        {
            if(mServiceBinderWrapper != null && mCallbacksMessenger != null)
                try
                {
                    mServiceBinderWrapper.unregisterCallbackMessenger(mCallbacksMessenger);
                }
                catch(RemoteException remoteexception)
                {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            MediaBrowserCompatApi21.disconnect(mBrowserObj);
        }

        public Bundle getExtras()
        {
            return MediaBrowserCompatApi21.getExtras(mBrowserObj);
        }

        public void getItem(String s, final ItemCallback cb)
        {
            if(TextUtils.isEmpty(s))
                throw new IllegalArgumentException("mediaId is empty");
            if(cb == null)
                throw new IllegalArgumentException("cb is null");
            if(!MediaBrowserCompatApi21.isConnected(mBrowserObj))
            {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                mHandler.post(s. new Runnable() {

                    public void run()
                    {
                        cb.onError(mediaId);
                    }

                    final MediaBrowserImplApi21 this$0;
                    final ItemCallback val$cb;
                    final String val$mediaId;

            
            {
                this$0 = final_mediabrowserimplapi21;
                cb = itemcallback;
                mediaId = String.this;
                super();
            }
                }
);
                return;
            }
            if(mServiceBinderWrapper == null)
            {
                mHandler.post(s. new Runnable() {

                    public void run()
                    {
                        cb.onError(mediaId);
                    }

                    final MediaBrowserImplApi21 this$0;
                    final ItemCallback val$cb;
                    final String val$mediaId;

            
            {
                this$0 = final_mediabrowserimplapi21;
                cb = itemcallback;
                mediaId = String.this;
                super();
            }
                }
);
                return;
            }
            ItemReceiver itemreceiver = new ItemReceiver(s, cb, mHandler);
            try
            {
                mServiceBinderWrapper.getMediaItem(s, itemreceiver, mCallbacksMessenger);
                return;
            }
            catch(RemoteException remoteexception)
            {
                Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error getting media item: ").append(s).toString());
            }
            mHandler.post(s. new Runnable() {

                public void run()
                {
                    cb.onError(mediaId);
                }

                final MediaBrowserImplApi21 this$0;
                final ItemCallback val$cb;
                final String val$mediaId;

            
            {
                this$0 = final_mediabrowserimplapi21;
                cb = itemcallback;
                mediaId = String.this;
                super();
            }
            }
);
        }

        public String getRoot()
        {
            return MediaBrowserCompatApi21.getRoot(mBrowserObj);
        }

        public ComponentName getServiceComponent()
        {
            return MediaBrowserCompatApi21.getServiceComponent(mBrowserObj);
        }

        public android.support.v4.media.session.MediaSessionCompat.Token getSessionToken()
        {
            if(mMediaSessionToken == null)
                mMediaSessionToken = android.support.v4.media.session.MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(mBrowserObj));
            return mMediaSessionToken;
        }

        public boolean isConnected()
        {
            return MediaBrowserCompatApi21.isConnected(mBrowserObj);
        }

        public void onConnected()
        {
            Object obj = MediaBrowserCompatApi21.getExtras(mBrowserObj);
            if(obj != null)
            {
                IBinder ibinder = BundleCompat.getBinder(((Bundle) (obj)), "extra_messenger");
                if(ibinder != null)
                {
                    mServiceBinderWrapper = new ServiceBinderWrapper(ibinder, mRootHints);
                    mCallbacksMessenger = new Messenger(mHandler);
                    mHandler.setCallbacksMessenger(mCallbacksMessenger);
                    try
                    {
                        mServiceBinderWrapper.registerCallbackMessenger(mCallbacksMessenger);
                    }
                    catch(RemoteException remoteexception)
                    {
                        Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                    }
                }
                obj = android.support.v4.media.session.IMediaSession.Stub.asInterface(BundleCompat.getBinder(((Bundle) (obj)), "extra_session_binder"));
                if(obj != null)
                {
                    mMediaSessionToken = android.support.v4.media.session.MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(mBrowserObj), ((android.support.v4.media.session.IMediaSession) (obj)));
                    return;
                }
            }
        }

        public void onConnectionFailed()
        {
        }

        public void onConnectionFailed(Messenger messenger)
        {
        }

        public void onConnectionSuspended()
        {
            mServiceBinderWrapper = null;
            mCallbacksMessenger = null;
            mMediaSessionToken = null;
            mHandler.setCallbacksMessenger(null);
        }

        public void onLoadChildren(Messenger messenger, String s, List list, Bundle bundle)
        {
            if(mCallbacksMessenger == messenger) goto _L2; else goto _L1
_L1:
            return;
_L2:
            messenger = (Subscription)mSubscriptions.get(s);
            if(messenger != null)
                break; /* Loop/switch isn't completed */
            if(MediaBrowserCompat.DEBUG)
            {
                Log.d("MediaBrowserCompat", (new StringBuilder()).append("onLoadChildren for id that isn't subscribed id=").append(s).toString());
                return;
            }
            if(true) goto _L1; else goto _L3
_L3:
            messenger = messenger.getCallback(mContext, bundle);
            if(messenger != null)
            {
                if(bundle == null)
                    if(list == null)
                    {
                        messenger.onError(s);
                        return;
                    } else
                    {
                        messenger.onChildrenLoaded(s, list);
                        return;
                    }
                if(list == null)
                {
                    messenger.onError(s, bundle);
                    return;
                } else
                {
                    messenger.onChildrenLoaded(s, list, bundle);
                    return;
                }
            }
            if(true) goto _L1; else goto _L4
_L4:
        }

        public void onServiceConnected(Messenger messenger, String s, android.support.v4.media.session.MediaSessionCompat.Token token, Bundle bundle)
        {
        }

        public void search(final String query, Bundle bundle, final SearchCallback callback)
        {
            if(!isConnected())
                throw new IllegalStateException("search() called while not connected");
            if(mServiceBinderWrapper == null)
            {
                Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
                mHandler.post(bundle. new Runnable() {

                    public void run()
                    {
                        callback.onError(query, extras);
                    }

                    final MediaBrowserImplApi21 this$0;
                    final SearchCallback val$callback;
                    final Bundle val$extras;
                    final String val$query;

            
            {
                this$0 = final_mediabrowserimplapi21;
                callback = searchcallback;
                query = s;
                extras = Bundle.this;
                super();
            }
                }
);
                return;
            }
            SearchResultReceiver searchresultreceiver = new SearchResultReceiver(query, bundle, callback, mHandler);
            try
            {
                mServiceBinderWrapper.search(query, bundle, searchresultreceiver, mCallbacksMessenger);
                return;
            }
            catch(RemoteException remoteexception)
            {
                Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error searching items with query: ").append(query).toString(), remoteexception);
            }
            mHandler.post(bundle. new Runnable() {

                public void run()
                {
                    callback.onError(query, extras);
                }

                final MediaBrowserImplApi21 this$0;
                final SearchCallback val$callback;
                final Bundle val$extras;
                final String val$query;

            
            {
                this$0 = final_mediabrowserimplapi21;
                callback = searchcallback;
                query = s;
                extras = Bundle.this;
                super();
            }
            }
);
        }

        public void sendCustomAction(final String action, Bundle bundle, final CustomActionCallback callback)
        {
            CustomActionResultReceiver customactionresultreceiver;
            if(!isConnected())
                throw new IllegalStateException((new StringBuilder()).append("Cannot send a custom action (").append(action).append(") with ").append("extras ").append(bundle).append(" because the browser is not connected to the ").append("service.").toString());
            if(mServiceBinderWrapper == null)
            {
                Log.i("MediaBrowserCompat", "The connected service doesn't support sendCustomAction.");
                if(callback != null)
                    mHandler.post(bundle. new Runnable() {

                        public void run()
                        {
                            callback.onError(action, extras, null);
                        }

                        final MediaBrowserImplApi21 this$0;
                        final String val$action;
                        final CustomActionCallback val$callback;
                        final Bundle val$extras;

            
            {
                this$0 = final_mediabrowserimplapi21;
                callback = customactioncallback;
                action = s;
                extras = Bundle.this;
                super();
            }
                    }
);
            }
            customactionresultreceiver = new CustomActionResultReceiver(action, bundle, callback, mHandler);
            mServiceBinderWrapper.sendCustomAction(action, bundle, customactionresultreceiver, mCallbacksMessenger);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error sending a custom action: action=").append(action).append(", extras=").append(bundle).toString(), remoteexception);
            if(callback != null)
            {
                mHandler.post(bundle. new Runnable() {

                    public void run()
                    {
                        callback.onError(action, extras, null);
                    }

                    final MediaBrowserImplApi21 this$0;
                    final String val$action;
                    final CustomActionCallback val$callback;
                    final Bundle val$extras;

            
            {
                this$0 = final_mediabrowserimplapi21;
                callback = customactioncallback;
                action = s;
                extras = Bundle.this;
                super();
            }
                }
);
                return;
            }
              goto _L1
        }

        public void subscribe(String s, Bundle bundle, SubscriptionCallback subscriptioncallback)
        {
            Subscription subscription1 = (Subscription)mSubscriptions.get(s);
            Subscription subscription = subscription1;
            if(subscription1 == null)
            {
                subscription = new Subscription();
                mSubscriptions.put(s, subscription);
            }
            subscriptioncallback.setSubscription(subscription);
            if(bundle == null)
                bundle = null;
            else
                bundle = new Bundle(bundle);
            subscription.putCallback(mContext, bundle, subscriptioncallback);
            if(mServiceBinderWrapper == null)
            {
                MediaBrowserCompatApi21.subscribe(mBrowserObj, s, subscriptioncallback.mSubscriptionCallbackObj);
                return;
            }
            try
            {
                mServiceBinderWrapper.addSubscription(s, subscriptioncallback.mToken, bundle, mCallbacksMessenger);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Bundle bundle)
            {
                Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error subscribing media item: ").append(s).toString());
            }
        }

        public void unsubscribe(String s, SubscriptionCallback subscriptioncallback)
        {
            Subscription subscription = (Subscription)mSubscriptions.get(s);
            if(subscription != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if(mServiceBinderWrapper != null)
                break; /* Loop/switch isn't completed */
            if(subscriptioncallback == null)
            {
                MediaBrowserCompatApi21.unsubscribe(mBrowserObj, s);
            } else
            {
                List list = subscription.getCallbacks();
                List list2 = subscription.getOptionsList();
                for(int i = list.size() - 1; i >= 0; i--)
                    if(list.get(i) == subscriptioncallback)
                    {
                        list.remove(i);
                        list2.remove(i);
                    }

                if(list.size() == 0)
                    MediaBrowserCompatApi21.unsubscribe(mBrowserObj, s);
            }
_L4:
            if(subscription.isEmpty() || subscriptioncallback == null)
            {
                mSubscriptions.remove(s);
                return;
            }
            if(true) goto _L1; else goto _L3
_L3:
label0:
            {
                if(subscriptioncallback != null)
                    break label0;
                try
                {
                    mServiceBinderWrapper.removeSubscription(s, null, mCallbacksMessenger);
                }
                catch(RemoteException remoteexception)
                {
                    Log.d("MediaBrowserCompat", (new StringBuilder()).append("removeSubscription failed with RemoteException parentId=").append(s).toString());
                }
            }
              goto _L4
            int j;
            List list1;
            List list3;
            list1 = subscription.getCallbacks();
            list3 = subscription.getOptionsList();
            j = list1.size() - 1;
_L6:
            if(j < 0) goto _L4; else goto _L5
_L5:
            if(list1.get(j) == subscriptioncallback)
            {
                mServiceBinderWrapper.removeSubscription(s, subscriptioncallback.mToken, mCallbacksMessenger);
                list1.remove(j);
                list3.remove(j);
            }
            j--;
              goto _L6
        }

        protected final Object mBrowserObj;
        protected Messenger mCallbacksMessenger;
        final Context mContext;
        protected final CallbackHandler mHandler = new CallbackHandler(this);
        private android.support.v4.media.session.MediaSessionCompat.Token mMediaSessionToken;
        protected final Bundle mRootHints;
        protected ServiceBinderWrapper mServiceBinderWrapper;
        private final ArrayMap mSubscriptions = new ArrayMap();

        public MediaBrowserImplApi21(Context context, ComponentName componentname, ConnectionCallback connectioncallback, Bundle bundle)
        {
            mContext = context;
            Bundle bundle1 = bundle;
            if(bundle == null)
                bundle1 = new Bundle();
            bundle1.putInt("extra_client_version", 1);
            mRootHints = new Bundle(bundle1);
            connectioncallback.setInternalConnectionCallback(this);
            mBrowserObj = MediaBrowserCompatApi21.createBrowser(context, componentname, connectioncallback.mConnectionCallbackObj, mRootHints);
        }
    }

    static class MediaBrowserImplApi23 extends MediaBrowserImplApi21
    {

        public void getItem(String s, ItemCallback itemcallback)
        {
            if(mServiceBinderWrapper == null)
            {
                MediaBrowserCompatApi23.getItem(mBrowserObj, s, itemcallback.mItemCallbackObj);
                return;
            } else
            {
                super.getItem(s, itemcallback);
                return;
            }
        }

        public MediaBrowserImplApi23(Context context, ComponentName componentname, ConnectionCallback connectioncallback, Bundle bundle)
        {
            super(context, componentname, connectioncallback, bundle);
        }
    }

    static class MediaBrowserImplApi24 extends MediaBrowserImplApi23
    {

        public void subscribe(String s, Bundle bundle, SubscriptionCallback subscriptioncallback)
        {
            if(bundle == null)
            {
                MediaBrowserCompatApi21.subscribe(mBrowserObj, s, subscriptioncallback.mSubscriptionCallbackObj);
                return;
            } else
            {
                MediaBrowserCompatApi24.subscribe(mBrowserObj, s, bundle, subscriptioncallback.mSubscriptionCallbackObj);
                return;
            }
        }

        public void unsubscribe(String s, SubscriptionCallback subscriptioncallback)
        {
            if(subscriptioncallback == null)
            {
                MediaBrowserCompatApi21.unsubscribe(mBrowserObj, s);
                return;
            } else
            {
                MediaBrowserCompatApi24.unsubscribe(mBrowserObj, s, subscriptioncallback.mSubscriptionCallbackObj);
                return;
            }
        }

        public MediaBrowserImplApi24(Context context, ComponentName componentname, ConnectionCallback connectioncallback, Bundle bundle)
        {
            super(context, componentname, connectioncallback, bundle);
        }
    }

    static class MediaBrowserImplBase
        implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl
    {

        private static String getStateLabel(int i)
        {
            switch(i)
            {
            default:
                return (new StringBuilder()).append("UNKNOWN/").append(i).toString();

            case 0: // '\0'
                return "CONNECT_STATE_DISCONNECTING";

            case 1: // '\001'
                return "CONNECT_STATE_DISCONNECTED";

            case 2: // '\002'
                return "CONNECT_STATE_CONNECTING";

            case 3: // '\003'
                return "CONNECT_STATE_CONNECTED";

            case 4: // '\004'
                return "CONNECT_STATE_SUSPENDED";
            }
        }

        private boolean isCurrent(Messenger messenger, String s)
        {
            boolean flag = true;
            if(mCallbacksMessenger != messenger || mState == 0 || mState == 1)
            {
                if(mState != 0 && mState != 1)
                    Log.i("MediaBrowserCompat", (new StringBuilder()).append(s).append(" for ").append(mServiceComponent).append(" with mCallbacksMessenger=").append(mCallbacksMessenger).append(" this=").append(this).toString());
                flag = false;
            }
            return flag;
        }

        public void connect()
        {
            if(mState != 0 && mState != 1)
            {
                throw new IllegalStateException((new StringBuilder()).append("connect() called while neigther disconnecting nor disconnected (state=").append(getStateLabel(mState)).append(")").toString());
            } else
            {
                mState = 2;
                mHandler.post(new Runnable() {

                    public void run()
                    {
                        if(mState != 0) goto _L2; else goto _L1
_L1:
                        return;
_L2:
                        boolean flag;
                        Intent intent;
                        mState = 2;
                        if(MediaBrowserCompat.DEBUG && mServiceConnection != null)
                            throw new RuntimeException((new StringBuilder()).append("mServiceConnection should be null. Instead it is ").append(mServiceConnection).toString());
                        if(mServiceBinderWrapper != null)
                            throw new RuntimeException((new StringBuilder()).append("mServiceBinderWrapper should be null. Instead it is ").append(mServiceBinderWrapper).toString());
                        if(mCallbacksMessenger != null)
                            throw new RuntimeException((new StringBuilder()).append("mCallbacksMessenger should be null. Instead it is ").append(mCallbacksMessenger).toString());
                        intent = new Intent("android.media.browse.MediaBrowserService");
                        intent.setComponent(mServiceComponent);
                        mServiceConnection = new MediaBrowserImplBase.MediaServiceConnection();
                        flag = false;
                        boolean flag1 = mContext.bindService(intent, mServiceConnection, 1);
                        flag = flag1;
_L4:
                        if(!flag)
                        {
                            forceCloseConnection();
                            mCallback.onConnectionFailed();
                        }
                        if(MediaBrowserCompat.DEBUG)
                        {
                            Log.d("MediaBrowserCompat", "connect...");
                            dump();
                            return;
                        }
                        if(true) goto _L1; else goto _L3
_L3:
                        Exception exception;
                        exception;
                        Log.e("MediaBrowserCompat", (new StringBuilder()).append("Failed binding to service ").append(mServiceComponent).toString());
                          goto _L4
                    }

                    final MediaBrowserImplBase this$0;

            
            {
                this$0 = MediaBrowserImplBase.this;
                super();
            }
                }
);
                return;
            }
        }

        public void disconnect()
        {
            mState = 0;
            mHandler.post(new Runnable() {

                public void run()
                {
                    int i;
                    if(mCallbacksMessenger != null)
                        try
                        {
                            mServiceBinderWrapper.disconnect(mCallbacksMessenger);
                        }
                        catch(RemoteException remoteexception)
                        {
                            Log.w("MediaBrowserCompat", (new StringBuilder()).append("RemoteException during connect for ").append(mServiceComponent).toString());
                        }
                    i = mState;
                    forceCloseConnection();
                    if(i != 0)
                        mState = i;
                    if(MediaBrowserCompat.DEBUG)
                    {
                        Log.d("MediaBrowserCompat", "disconnect...");
                        dump();
                    }
                }

                final MediaBrowserImplBase this$0;

            
            {
                this$0 = MediaBrowserImplBase.this;
                super();
            }
            }
);
        }

        void dump()
        {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mServiceComponent=").append(mServiceComponent).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mCallback=").append(mCallback).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mRootHints=").append(mRootHints).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mState=").append(getStateLabel(mState)).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mServiceConnection=").append(mServiceConnection).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mServiceBinderWrapper=").append(mServiceBinderWrapper).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mCallbacksMessenger=").append(mCallbacksMessenger).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mRootId=").append(mRootId).toString());
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("  mMediaSessionToken=").append(mMediaSessionToken).toString());
        }

        void forceCloseConnection()
        {
            if(mServiceConnection != null)
                mContext.unbindService(mServiceConnection);
            mState = 1;
            mServiceConnection = null;
            mServiceBinderWrapper = null;
            mCallbacksMessenger = null;
            mHandler.setCallbacksMessenger(null);
            mRootId = null;
            mMediaSessionToken = null;
        }

        public Bundle getExtras()
        {
            if(!isConnected())
                throw new IllegalStateException((new StringBuilder()).append("getExtras() called while not connected (state=").append(getStateLabel(mState)).append(")").toString());
            else
                return mExtras;
        }

        public void getItem(String s, final ItemCallback cb)
        {
            if(TextUtils.isEmpty(s))
                throw new IllegalArgumentException("mediaId is empty");
            if(cb == null)
                throw new IllegalArgumentException("cb is null");
            if(!isConnected())
            {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                mHandler.post(s. new Runnable() {

                    public void run()
                    {
                        cb.onError(mediaId);
                    }

                    final MediaBrowserImplBase this$0;
                    final ItemCallback val$cb;
                    final String val$mediaId;

            
            {
                this$0 = final_mediabrowserimplbase;
                cb = itemcallback;
                mediaId = String.this;
                super();
            }
                }
);
                return;
            }
            ItemReceiver itemreceiver = new ItemReceiver(s, cb, mHandler);
            try
            {
                mServiceBinderWrapper.getMediaItem(s, itemreceiver, mCallbacksMessenger);
                return;
            }
            catch(RemoteException remoteexception)
            {
                Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error getting media item: ").append(s).toString());
            }
            mHandler.post(s. new Runnable() {

                public void run()
                {
                    cb.onError(mediaId);
                }

                final MediaBrowserImplBase this$0;
                final ItemCallback val$cb;
                final String val$mediaId;

            
            {
                this$0 = final_mediabrowserimplbase;
                cb = itemcallback;
                mediaId = String.this;
                super();
            }
            }
);
        }

        public String getRoot()
        {
            if(!isConnected())
                throw new IllegalStateException((new StringBuilder()).append("getRoot() called while not connected(state=").append(getStateLabel(mState)).append(")").toString());
            else
                return mRootId;
        }

        public ComponentName getServiceComponent()
        {
            if(!isConnected())
                throw new IllegalStateException((new StringBuilder()).append("getServiceComponent() called while not connected (state=").append(mState).append(")").toString());
            else
                return mServiceComponent;
        }

        public android.support.v4.media.session.MediaSessionCompat.Token getSessionToken()
        {
            if(!isConnected())
                throw new IllegalStateException((new StringBuilder()).append("getSessionToken() called while not connected(state=").append(mState).append(")").toString());
            else
                return mMediaSessionToken;
        }

        public boolean isConnected()
        {
            return mState == 3;
        }

        public void onConnectionFailed(Messenger messenger)
        {
            Log.e("MediaBrowserCompat", (new StringBuilder()).append("onConnectFailed for ").append(mServiceComponent).toString());
            if(!isCurrent(messenger, "onConnectFailed"))
                return;
            if(mState != 2)
            {
                Log.w("MediaBrowserCompat", (new StringBuilder()).append("onConnect from service while mState=").append(getStateLabel(mState)).append("... ignoring").toString());
                return;
            } else
            {
                forceCloseConnection();
                mCallback.onConnectionFailed();
                return;
            }
        }

        public void onLoadChildren(Messenger messenger, String s, List list, Bundle bundle)
        {
            if(isCurrent(messenger, "onLoadChildren")) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if(MediaBrowserCompat.DEBUG)
                Log.d("MediaBrowserCompat", (new StringBuilder()).append("onLoadChildren for ").append(mServiceComponent).append(" id=").append(s).toString());
            messenger = (Subscription)mSubscriptions.get(s);
            if(messenger != null)
                break; /* Loop/switch isn't completed */
            if(MediaBrowserCompat.DEBUG)
            {
                Log.d("MediaBrowserCompat", (new StringBuilder()).append("onLoadChildren for id that isn't subscribed id=").append(s).toString());
                return;
            }
            if(true) goto _L1; else goto _L3
_L3:
            messenger = messenger.getCallback(mContext, bundle);
            if(messenger != null)
            {
                if(bundle == null)
                    if(list == null)
                    {
                        messenger.onError(s);
                        return;
                    } else
                    {
                        messenger.onChildrenLoaded(s, list);
                        return;
                    }
                if(list == null)
                {
                    messenger.onError(s, bundle);
                    return;
                } else
                {
                    messenger.onChildrenLoaded(s, list, bundle);
                    return;
                }
            }
            if(true) goto _L1; else goto _L4
_L4:
        }

        public void onServiceConnected(Messenger messenger, String s, android.support.v4.media.session.MediaSessionCompat.Token token, Bundle bundle)
        {
            if(isCurrent(messenger, "onConnect")) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if(mState != 2)
            {
                Log.w("MediaBrowserCompat", (new StringBuilder()).append("onConnect from service while mState=").append(getStateLabel(mState)).append("... ignoring").toString());
                return;
            }
            mRootId = s;
            mMediaSessionToken = token;
            mExtras = bundle;
            mState = 3;
            if(MediaBrowserCompat.DEBUG)
            {
                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                dump();
            }
            mCallback.onConnected();
            messenger = mSubscriptions.entrySet().iterator();
_L4:
            if(!messenger.hasNext())
                continue; /* Loop/switch isn't completed */
            token = (java.util.Map.Entry)messenger.next();
            s = (String)token.getKey();
            bundle = (Subscription)token.getValue();
            token = bundle.getCallbacks();
            bundle = bundle.getOptionsList();
            int i = 0;
            do
            {
                try
                {
                    if(i >= token.size())
                        break;
                    mServiceBinderWrapper.addSubscription(s, ((SubscriptionCallback)token.get(i)).mToken, (Bundle)bundle.get(i), mCallbacksMessenger);
                }
                // Misplaced declaration of an exception variable
                catch(Messenger messenger)
                {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
                    return;
                }
                i++;
            } while(true);
            if(true) goto _L4; else goto _L3
_L3:
            if(true) goto _L1; else goto _L5
_L5:
        }

        public void search(final String query, Bundle bundle, final SearchCallback callback)
        {
            if(!isConnected())
                throw new IllegalStateException((new StringBuilder()).append("search() called while not connected (state=").append(getStateLabel(mState)).append(")").toString());
            SearchResultReceiver searchresultreceiver = new SearchResultReceiver(query, bundle, callback, mHandler);
            try
            {
                mServiceBinderWrapper.search(query, bundle, searchresultreceiver, mCallbacksMessenger);
                return;
            }
            catch(RemoteException remoteexception)
            {
                Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error searching items with query: ").append(query).toString(), remoteexception);
            }
            mHandler.post(bundle. new Runnable() {

                public void run()
                {
                    callback.onError(query, extras);
                }

                final MediaBrowserImplBase this$0;
                final SearchCallback val$callback;
                final Bundle val$extras;
                final String val$query;

            
            {
                this$0 = final_mediabrowserimplbase;
                callback = searchcallback;
                query = s;
                extras = Bundle.this;
                super();
            }
            }
);
        }

        public void sendCustomAction(final String action, Bundle bundle, final CustomActionCallback callback)
        {
            CustomActionResultReceiver customactionresultreceiver;
            if(!isConnected())
                throw new IllegalStateException((new StringBuilder()).append("Cannot send a custom action (").append(action).append(") with ").append("extras ").append(bundle).append(" because the browser is not connected to the ").append("service.").toString());
            customactionresultreceiver = new CustomActionResultReceiver(action, bundle, callback, mHandler);
            mServiceBinderWrapper.sendCustomAction(action, bundle, customactionresultreceiver, mCallbacksMessenger);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.i("MediaBrowserCompat", (new StringBuilder()).append("Remote error sending a custom action: action=").append(action).append(", extras=").append(bundle).toString(), remoteexception);
            if(callback != null)
            {
                mHandler.post(bundle. new Runnable() {

                    public void run()
                    {
                        callback.onError(action, extras, null);
                    }

                    final MediaBrowserImplBase this$0;
                    final String val$action;
                    final CustomActionCallback val$callback;
                    final Bundle val$extras;

            
            {
                this$0 = final_mediabrowserimplbase;
                callback = customactioncallback;
                action = s;
                extras = Bundle.this;
                super();
            }
                }
);
                return;
            }
              goto _L1
        }

        public void subscribe(String s, Bundle bundle, SubscriptionCallback subscriptioncallback)
        {
            Subscription subscription1 = (Subscription)mSubscriptions.get(s);
            Subscription subscription = subscription1;
            if(subscription1 == null)
            {
                subscription = new Subscription();
                mSubscriptions.put(s, subscription);
            }
            if(bundle == null)
                bundle = null;
            else
                bundle = new Bundle(bundle);
            subscription.putCallback(mContext, bundle, subscriptioncallback);
            if(!isConnected())
                break MISSING_BLOCK_LABEL_83;
            mServiceBinderWrapper.addSubscription(s, subscriptioncallback.mToken, bundle, mCallbacksMessenger);
            return;
            bundle;
            Log.d("MediaBrowserCompat", (new StringBuilder()).append("addSubscription failed with RemoteException parentId=").append(s).toString());
            return;
        }

        public void unsubscribe(String s, SubscriptionCallback subscriptioncallback)
        {
            Subscription subscription = (Subscription)mSubscriptions.get(s);
            if(subscription != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if(subscriptioncallback != null) goto _L4; else goto _L3
_L3:
            int i;
            List list;
            List list1;
            try
            {
                if(isConnected())
                    mServiceBinderWrapper.removeSubscription(s, null, mCallbacksMessenger);
            }
            catch(RemoteException remoteexception)
            {
                Log.d("MediaBrowserCompat", (new StringBuilder()).append("removeSubscription failed with RemoteException parentId=").append(s).toString());
            }
            if(subscription.isEmpty() || subscriptioncallback == null)
            {
                mSubscriptions.remove(s);
                return;
            }
              goto _L5
_L4:
            list = subscription.getCallbacks();
            list1 = subscription.getOptionsList();
            i = list.size() - 1;
_L8:
            if(i < 0) goto _L7; else goto _L6
_L6:
            if(list.get(i) == subscriptioncallback)
            {
                if(isConnected())
                    mServiceBinderWrapper.removeSubscription(s, subscriptioncallback.mToken, mCallbacksMessenger);
                list.remove(i);
                list1.remove(i);
            }
            i--;
            if(true) goto _L8; else goto _L7
_L7:
            if(false)
                break MISSING_BLOCK_LABEL_149;
            else
                break MISSING_BLOCK_LABEL_43;
_L5:
            if(true) goto _L1; else goto _L9
_L9:
        }

        static final int CONNECT_STATE_CONNECTED = 3;
        static final int CONNECT_STATE_CONNECTING = 2;
        static final int CONNECT_STATE_DISCONNECTED = 1;
        static final int CONNECT_STATE_DISCONNECTING = 0;
        static final int CONNECT_STATE_SUSPENDED = 4;
        final ConnectionCallback mCallback;
        Messenger mCallbacksMessenger;
        final Context mContext;
        private Bundle mExtras;
        final CallbackHandler mHandler = new CallbackHandler(this);
        private android.support.v4.media.session.MediaSessionCompat.Token mMediaSessionToken;
        final Bundle mRootHints;
        private String mRootId;
        ServiceBinderWrapper mServiceBinderWrapper;
        final ComponentName mServiceComponent;
        MediaServiceConnection mServiceConnection;
        int mState;
        private final ArrayMap mSubscriptions = new ArrayMap();

        public MediaBrowserImplBase(Context context, ComponentName componentname, ConnectionCallback connectioncallback, Bundle bundle)
        {
            mState = 1;
            if(context == null)
                throw new IllegalArgumentException("context must not be null");
            if(componentname == null)
                throw new IllegalArgumentException("service component must not be null");
            if(connectioncallback == null)
                throw new IllegalArgumentException("connection callback must not be null");
            mContext = context;
            mServiceComponent = componentname;
            mCallback = connectioncallback;
            if(bundle == null)
                context = null;
            else
                context = new Bundle(bundle);
            mRootHints = context;
        }
    }

    private class MediaBrowserImplBase.MediaServiceConnection
        implements ServiceConnection
    {

        private void postOrRun(Runnable runnable)
        {
            if(Thread.currentThread() == mHandler.getLooper().getThread())
            {
                runnable.run();
                return;
            } else
            {
                mHandler.post(runnable);
                return;
            }
        }

        boolean isCurrent(String s)
        {
            boolean flag = true;
            if(mServiceConnection != this || mState == 0 || mState == 1)
            {
                if(mState != 0 && mState != 1)
                    Log.i("MediaBrowserCompat", (new StringBuilder()).append(s).append(" for ").append(mServiceComponent).append(" with mServiceConnection=").append(mServiceConnection).append(" this=").append(this).toString());
                flag = false;
            }
            return flag;
        }

        public void onServiceConnected(final ComponentName name, IBinder ibinder)
        {
            postOrRun(ibinder. new Runnable() {

                public void run()
                {
                    if(MediaBrowserCompat.DEBUG)
                    {
                        Log.d("MediaBrowserCompat", (new StringBuilder()).append("MediaServiceConnection.onServiceConnected name=").append(name).append(" binder=").append(binder).toString());
                        dump();
                    }
                    if(isCurrent("onServiceConnected"))
                    {
                        mServiceBinderWrapper = new ServiceBinderWrapper(binder, mRootHints);
                        mCallbacksMessenger = new Messenger(mHandler);
                        mHandler.setCallbacksMessenger(mCallbacksMessenger);
                        mState = 2;
                        try
                        {
                            if(MediaBrowserCompat.DEBUG)
                            {
                                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                dump();
                            }
                            mServiceBinderWrapper.connect(mContext, mCallbacksMessenger);
                            return;
                        }
                        catch(RemoteException remoteexception)
                        {
                            Log.w("MediaBrowserCompat", (new StringBuilder()).append("RemoteException during connect for ").append(mServiceComponent).toString());
                        }
                        if(MediaBrowserCompat.DEBUG)
                        {
                            Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                            dump();
                            return;
                        }
                    }
                }

                final MediaBrowserImplBase.MediaServiceConnection this$1;
                final IBinder val$binder;
                final ComponentName val$name;

            
            {
                this$1 = final_mediaserviceconnection;
                name = componentname;
                binder = IBinder.this;
                super();
            }
            }
);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            postOrRun(componentname. new Runnable() {

                public void run()
                {
                    if(MediaBrowserCompat.DEBUG)
                    {
                        Log.d("MediaBrowserCompat", (new StringBuilder()).append("MediaServiceConnection.onServiceDisconnected name=").append(name).append(" this=").append(this).append(" mServiceConnection=").append(mServiceConnection).toString());
                        dump();
                    }
                    if(!isCurrent("onServiceDisconnected"))
                    {
                        return;
                    } else
                    {
                        mServiceBinderWrapper = null;
                        mCallbacksMessenger = null;
                        mHandler.setCallbacksMessenger(null);
                        mState = 4;
                        mCallback.onConnectionSuspended();
                        return;
                    }
                }

                final MediaBrowserImplBase.MediaServiceConnection this$1;
                final ComponentName val$name;

            
            {
                this$1 = final_mediaserviceconnection;
                name = ComponentName.this;
                super();
            }
            }
);
        }

        final MediaBrowserImplBase this$0;

        MediaBrowserImplBase.MediaServiceConnection()
        {
            this$0 = MediaBrowserImplBase.this;
            super();
        }
    }

    static interface MediaBrowserServiceCallbackImpl
    {

        public abstract void onConnectionFailed(Messenger messenger);

        public abstract void onLoadChildren(Messenger messenger, String s, List list, Bundle bundle);

        public abstract void onServiceConnected(Messenger messenger, String s, android.support.v4.media.session.MediaSessionCompat.Token token, Bundle bundle);
    }

    public static class MediaItem
        implements Parcelable
    {

        public static MediaItem fromMediaItem(Object obj)
        {
            if(obj == null || android.os.Build.VERSION.SDK_INT < 21)
            {
                return null;
            } else
            {
                int i = MediaBrowserCompatApi21.MediaItem.getFlags(obj);
                return new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21.MediaItem.getDescription(obj)), i);
            }
        }

        public static List fromMediaItemList(List list)
        {
            if(list != null && android.os.Build.VERSION.SDK_INT >= 21) goto _L2; else goto _L1
_L1:
            list = null;
_L4:
            return list;
_L2:
            ArrayList arraylist = new ArrayList(list.size());
            Iterator iterator = list.iterator();
            do
            {
                list = arraylist;
                if(!iterator.hasNext())
                    continue;
                arraylist.add(fromMediaItem(iterator.next()));
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

        public int getFlags()
        {
            return mFlags;
        }

        public String getMediaId()
        {
            return mDescription.getMediaId();
        }

        public boolean isBrowsable()
        {
            return (mFlags & 1) != 0;
        }

        public boolean isPlayable()
        {
            return (mFlags & 2) != 0;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder("MediaItem{");
            stringbuilder.append("mFlags=").append(mFlags);
            stringbuilder.append(", mDescription=").append(mDescription);
            stringbuilder.append('}');
            return stringbuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mFlags);
            mDescription.writeToParcel(parcel, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public MediaItem createFromParcel(Parcel parcel)
            {
                return new MediaItem(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public MediaItem[] newArray(int i)
            {
                return new MediaItem[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;


        MediaItem(Parcel parcel)
        {
            mFlags = parcel.readInt();
            mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }

        public MediaItem(MediaDescriptionCompat mediadescriptioncompat, int i)
        {
            if(mediadescriptioncompat == null)
                throw new IllegalArgumentException("description cannot be null");
            if(TextUtils.isEmpty(mediadescriptioncompat.getMediaId()))
            {
                throw new IllegalArgumentException("description must have a non-empty media id");
            } else
            {
                mFlags = i;
                mDescription = mediadescriptioncompat;
                return;
            }
        }
    }

    public static interface MediaItem.Flags
        extends Annotation
    {
    }

    public static abstract class SearchCallback
    {

        public void onError(String s, Bundle bundle)
        {
        }

        public void onSearchResult(String s, Bundle bundle, List list)
        {
        }

        public SearchCallback()
        {
        }
    }

    private static class SearchResultReceiver extends ResultReceiver
    {

        protected void onReceiveResult(int i, Bundle bundle)
        {
            if(bundle != null)
                bundle.setClassLoader(android/support/v4/media/MediaBrowserCompat.getClassLoader());
            if(i != 0 || bundle == null || !bundle.containsKey("search_results"))
            {
                mCallback.onError(mQuery, mExtras);
                return;
            }
            Parcelable aparcelable[] = bundle.getParcelableArray("search_results");
            bundle = null;
            if(aparcelable != null)
            {
                ArrayList arraylist = new ArrayList();
                int j = aparcelable.length;
                i = 0;
                do
                {
                    bundle = arraylist;
                    if(i >= j)
                        break;
                    arraylist.add((MediaItem)aparcelable[i]);
                    i++;
                } while(true);
            }
            mCallback.onSearchResult(mQuery, mExtras, bundle);
        }

        private final SearchCallback mCallback;
        private final Bundle mExtras;
        private final String mQuery;

        SearchResultReceiver(String s, Bundle bundle, SearchCallback searchcallback, Handler handler)
        {
            super(handler);
            mQuery = s;
            mExtras = bundle;
            mCallback = searchcallback;
        }
    }

    private static class ServiceBinderWrapper
    {

        private void sendRequest(int i, Bundle bundle, Messenger messenger)
            throws RemoteException
        {
            Message message = Message.obtain();
            message.what = i;
            message.arg1 = 1;
            message.setData(bundle);
            message.replyTo = messenger;
            mMessenger.send(message);
        }

        void addSubscription(String s, IBinder ibinder, Bundle bundle, Messenger messenger)
            throws RemoteException
        {
            Bundle bundle1 = new Bundle();
            bundle1.putString("data_media_item_id", s);
            BundleCompat.putBinder(bundle1, "data_callback_token", ibinder);
            bundle1.putBundle("data_options", bundle);
            sendRequest(3, bundle1, messenger);
        }

        void connect(Context context, Messenger messenger)
            throws RemoteException
        {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putBundle("data_root_hints", mRootHints);
            sendRequest(1, bundle, messenger);
        }

        void disconnect(Messenger messenger)
            throws RemoteException
        {
            sendRequest(2, null, messenger);
        }

        void getMediaItem(String s, ResultReceiver resultreceiver, Messenger messenger)
            throws RemoteException
        {
            Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", s);
            bundle.putParcelable("data_result_receiver", resultreceiver);
            sendRequest(5, bundle, messenger);
        }

        void registerCallbackMessenger(Messenger messenger)
            throws RemoteException
        {
            Bundle bundle = new Bundle();
            bundle.putBundle("data_root_hints", mRootHints);
            sendRequest(6, bundle, messenger);
        }

        void removeSubscription(String s, IBinder ibinder, Messenger messenger)
            throws RemoteException
        {
            Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", s);
            BundleCompat.putBinder(bundle, "data_callback_token", ibinder);
            sendRequest(4, bundle, messenger);
        }

        void search(String s, Bundle bundle, ResultReceiver resultreceiver, Messenger messenger)
            throws RemoteException
        {
            Bundle bundle1 = new Bundle();
            bundle1.putString("data_search_query", s);
            bundle1.putBundle("data_search_extras", bundle);
            bundle1.putParcelable("data_result_receiver", resultreceiver);
            sendRequest(8, bundle1, messenger);
        }

        void sendCustomAction(String s, Bundle bundle, ResultReceiver resultreceiver, Messenger messenger)
            throws RemoteException
        {
            Bundle bundle1 = new Bundle();
            bundle1.putString("data_custom_action", s);
            bundle1.putBundle("data_custom_action_extras", bundle);
            bundle1.putParcelable("data_result_receiver", resultreceiver);
            sendRequest(9, bundle1, messenger);
        }

        void unregisterCallbackMessenger(Messenger messenger)
            throws RemoteException
        {
            sendRequest(7, null, messenger);
        }

        private Messenger mMessenger;
        private Bundle mRootHints;

        public ServiceBinderWrapper(IBinder ibinder, Bundle bundle)
        {
            mMessenger = new Messenger(ibinder);
            mRootHints = bundle;
        }
    }

    private static class Subscription
    {

        public SubscriptionCallback getCallback(Context context, Bundle bundle)
        {
            if(bundle != null)
                bundle.setClassLoader(context.getClassLoader());
            for(int i = 0; i < mOptionsList.size(); i++)
                if(MediaBrowserCompatUtils.areSameOptions((Bundle)mOptionsList.get(i), bundle))
                    return (SubscriptionCallback)mCallbacks.get(i);

            return null;
        }

        public List getCallbacks()
        {
            return mCallbacks;
        }

        public List getOptionsList()
        {
            return mOptionsList;
        }

        public boolean isEmpty()
        {
            return mCallbacks.isEmpty();
        }

        public void putCallback(Context context, Bundle bundle, SubscriptionCallback subscriptioncallback)
        {
            if(bundle != null)
                bundle.setClassLoader(context.getClassLoader());
            for(int i = 0; i < mOptionsList.size(); i++)
                if(MediaBrowserCompatUtils.areSameOptions((Bundle)mOptionsList.get(i), bundle))
                {
                    mCallbacks.set(i, subscriptioncallback);
                    return;
                }

            mCallbacks.add(subscriptioncallback);
            mOptionsList.add(bundle);
        }

        private final List mCallbacks = new ArrayList();
        private final List mOptionsList = new ArrayList();

        public Subscription()
        {
        }
    }

    public static abstract class SubscriptionCallback
    {

        private void setSubscription(Subscription subscription)
        {
            mSubscriptionRef = new WeakReference(subscription);
        }

        public void onChildrenLoaded(String s, List list)
        {
        }

        public void onChildrenLoaded(String s, List list, Bundle bundle)
        {
        }

        public void onError(String s)
        {
        }

        public void onError(String s, Bundle bundle)
        {
        }

        private final Object mSubscriptionCallbackObj;
        WeakReference mSubscriptionRef;
        private final IBinder mToken;




        public SubscriptionCallback()
        {
            if(android.os.Build.VERSION.SDK_INT >= 26)
            {
                mSubscriptionCallbackObj = MediaBrowserCompatApi24.createSubscriptionCallback(new StubApi24());
                mToken = null;
                return;
            }
            if(android.os.Build.VERSION.SDK_INT >= 21)
            {
                mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21());
                mToken = new Binder();
                return;
            } else
            {
                mSubscriptionCallbackObj = null;
                mToken = new Binder();
                return;
            }
        }
    }

    private class SubscriptionCallback.StubApi21
        implements MediaBrowserCompatApi21.SubscriptionCallback
    {

        List applyOptions(List list, Bundle bundle)
        {
            if(list != null) goto _L2; else goto _L1
_L1:
            bundle = null;
_L4:
            return bundle;
_L2:
            int i;
            int l;
            i = bundle.getInt("android.media.browse.extra.PAGE", -1);
            l = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
            if(i != -1)
                break; /* Loop/switch isn't completed */
            bundle = list;
            if(l == -1) goto _L4; else goto _L3
_L3:
            int k = l * i;
            int j = k + l;
            if(i < 0 || l < 1 || k >= list.size())
                return Collections.EMPTY_LIST;
            i = j;
            if(j > list.size())
                i = list.size();
            return list.subList(k, i);
        }

        public void onChildrenLoaded(String s, List list)
        {
            Object obj;
            if(mSubscriptionRef == null)
                obj = null;
            else
                obj = (Subscription)mSubscriptionRef.get();
            if(obj == null)
            {
                SubscriptionCallback.this.onChildrenLoaded(s, MediaItem.fromMediaItemList(list));
            } else
            {
                list = MediaItem.fromMediaItemList(list);
                List list1 = ((Subscription) (obj)).getCallbacks();
                obj = ((Subscription) (obj)).getOptionsList();
                int i = 0;
                while(i < list1.size()) 
                {
                    Bundle bundle = (Bundle)((List) (obj)).get(i);
                    if(bundle == null)
                        SubscriptionCallback.this.onChildrenLoaded(s, list);
                    else
                        SubscriptionCallback.this.onChildrenLoaded(s, applyOptions(list, bundle), bundle);
                    i++;
                }
            }
        }

        public void onError(String s)
        {
            SubscriptionCallback.this.onError(s);
        }

        final SubscriptionCallback this$0;

        SubscriptionCallback.StubApi21()
        {
            this$0 = SubscriptionCallback.this;
            super();
        }
    }

    private class SubscriptionCallback.StubApi24 extends SubscriptionCallback.StubApi21
        implements MediaBrowserCompatApi24.SubscriptionCallback
    {

        public void onChildrenLoaded(String s, List list, Bundle bundle)
        {
            SubscriptionCallback.this.onChildrenLoaded(s, MediaItem.fromMediaItemList(list), bundle);
        }

        public void onError(String s, Bundle bundle)
        {
            SubscriptionCallback.this.onError(s, bundle);
        }

        final SubscriptionCallback this$0;

        SubscriptionCallback.StubApi24()
        {
            this$0 = SubscriptionCallback.this;
            super();
        }
    }


    public MediaBrowserCompat(Context context, ComponentName componentname, ConnectionCallback connectioncallback, Bundle bundle)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
        {
            mImpl = new MediaBrowserImplApi24(context, componentname, connectioncallback, bundle);
            return;
        }
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            mImpl = new MediaBrowserImplApi23(context, componentname, connectioncallback, bundle);
            return;
        }
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            mImpl = new MediaBrowserImplApi21(context, componentname, connectioncallback, bundle);
            return;
        } else
        {
            mImpl = new MediaBrowserImplBase(context, componentname, connectioncallback, bundle);
            return;
        }
    }

    public void connect()
    {
        mImpl.connect();
    }

    public void disconnect()
    {
        mImpl.disconnect();
    }

    public Bundle getExtras()
    {
        return mImpl.getExtras();
    }

    public void getItem(String s, ItemCallback itemcallback)
    {
        mImpl.getItem(s, itemcallback);
    }

    public String getRoot()
    {
        return mImpl.getRoot();
    }

    public ComponentName getServiceComponent()
    {
        return mImpl.getServiceComponent();
    }

    public android.support.v4.media.session.MediaSessionCompat.Token getSessionToken()
    {
        return mImpl.getSessionToken();
    }

    public boolean isConnected()
    {
        return mImpl.isConnected();
    }

    public void search(String s, Bundle bundle, SearchCallback searchcallback)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("query cannot be empty");
        if(searchcallback == null)
        {
            throw new IllegalArgumentException("callback cannot be null");
        } else
        {
            mImpl.search(s, bundle, searchcallback);
            return;
        }
    }

    public void sendCustomAction(String s, Bundle bundle, CustomActionCallback customactioncallback)
    {
        if(TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("action cannot be empty");
        } else
        {
            mImpl.sendCustomAction(s, bundle, customactioncallback);
            return;
        }
    }

    public void subscribe(String s, Bundle bundle, SubscriptionCallback subscriptioncallback)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("parentId is empty");
        if(subscriptioncallback == null)
            throw new IllegalArgumentException("callback is null");
        if(bundle == null)
        {
            throw new IllegalArgumentException("options are null");
        } else
        {
            mImpl.subscribe(s, bundle, subscriptioncallback);
            return;
        }
    }

    public void subscribe(String s, SubscriptionCallback subscriptioncallback)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("parentId is empty");
        if(subscriptioncallback == null)
        {
            throw new IllegalArgumentException("callback is null");
        } else
        {
            mImpl.subscribe(s, null, subscriptioncallback);
            return;
        }
    }

    public void unsubscribe(String s)
    {
        if(TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("parentId is empty");
        } else
        {
            mImpl.unsubscribe(s, null);
            return;
        }
    }

    public void unsubscribe(String s, SubscriptionCallback subscriptioncallback)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("parentId is empty");
        if(subscriptioncallback == null)
        {
            throw new IllegalArgumentException("callback is null");
        } else
        {
            mImpl.unsubscribe(s, subscriptioncallback);
            return;
        }
    }

    public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
    public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
    static final boolean DEBUG = Log.isLoggable("MediaBrowserCompat", 3);
    public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
    public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    static final String TAG = "MediaBrowserCompat";
    private final MediaBrowserImpl mImpl;

}
