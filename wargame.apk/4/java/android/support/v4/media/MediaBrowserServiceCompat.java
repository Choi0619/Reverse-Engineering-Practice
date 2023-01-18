// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.*;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.*;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompatUtils, MediaBrowserServiceCompatApi21, MediaBrowserServiceCompatApi23, MediaBrowserServiceCompatApi24, 
//            MediaBrowserCompat

public abstract class MediaBrowserServiceCompat extends Service
{
    public static final class BrowserRoot
    {

        public Bundle getExtras()
        {
            return mExtras;
        }

        public String getRootId()
        {
            return mRootId;
        }

        public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
        public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
        public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
        public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";
        private final Bundle mExtras;
        private final String mRootId;

        public BrowserRoot(String s, Bundle bundle)
        {
            if(s == null)
            {
                throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
            } else
            {
                mRootId = s;
                mExtras = bundle;
                return;
            }
        }
    }

    private static class ConnectionRecord
    {

        ServiceCallbacks callbacks;
        String pkg;
        BrowserRoot root;
        Bundle rootHints;
        HashMap subscriptions;

        ConnectionRecord()
        {
            subscriptions = new HashMap();
        }
    }

    static interface MediaBrowserServiceImpl
    {

        public abstract Bundle getBrowserRootHints();

        public abstract void notifyChildrenChanged(String s, Bundle bundle);

        public abstract IBinder onBind(Intent intent);

        public abstract void onCreate();

        public abstract void setSessionToken(android.support.v4.media.session.MediaSessionCompat.Token token);
    }

    class MediaBrowserServiceImplApi21
        implements MediaBrowserServiceImpl, MediaBrowserServiceCompatApi21.ServiceCompatProxy
    {

        public Bundle getBrowserRootHints()
        {
            if(mMessenger != null)
            {
                if(mCurConnection == null)
                    throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem or onSearch methods");
                if(mCurConnection.rootHints != null)
                    return new Bundle(mCurConnection.rootHints);
            }
            return null;
        }

        public void notifyChildrenChanged(final String parentId, Bundle bundle)
        {
            if(mMessenger == null)
            {
                MediaBrowserServiceCompatApi21.notifyChildrenChanged(mServiceObj, parentId);
                return;
            } else
            {
                mHandler.post(bundle. new Runnable() {

                    public void run()
                    {
                        for(Iterator iterator = mConnections.keySet().iterator(); iterator.hasNext();)
                        {
                            Object obj = (IBinder)iterator.next();
                            obj = (ConnectionRecord)mConnections.get(obj);
                            Object obj1 = (List)((ConnectionRecord) (obj)).subscriptions.get(parentId);
                            if(obj1 != null)
                            {
                                obj1 = ((List) (obj1)).iterator();
                                while(((Iterator) (obj1)).hasNext()) 
                                {
                                    Pair pair = (Pair)((Iterator) (obj1)).next();
                                    if(MediaBrowserCompatUtils.hasDuplicatedItems(options, (Bundle)pair.second))
                                        performLoadChildren(parentId, ((ConnectionRecord) (obj)), (Bundle)pair.second);
                                }
                            }
                        }

                    }

                    final MediaBrowserServiceImplApi21 this$1;
                    final Bundle val$options;
                    final String val$parentId;

            
            {
                this$1 = final_mediabrowserserviceimplapi21;
                parentId = s;
                options = Bundle.this;
                super();
            }
                }
);
                return;
            }
        }

        public IBinder onBind(Intent intent)
        {
            return MediaBrowserServiceCompatApi21.onBind(mServiceObj, intent);
        }

        public void onCreate()
        {
            mServiceObj = MediaBrowserServiceCompatApi21.createService(MediaBrowserServiceCompat.this, this);
            MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
        }

        public MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String s, int i, Bundle bundle)
        {
            Object obj;
            Object obj1 = null;
            obj = obj1;
            if(bundle != null)
            {
                obj = obj1;
                if(bundle.getInt("extra_client_version", 0) != 0)
                {
                    bundle.remove("extra_client_version");
                    mMessenger = new Messenger(mHandler);
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("extra_service_version", 1);
                    BundleCompat.putBinder(bundle1, "extra_messenger", mMessenger.getBinder());
                    if(mSession != null)
                    {
                        obj = mSession.getExtraBinder();
                        if(obj == null)
                            obj = null;
                        else
                            obj = ((IMediaSession) (obj)).asBinder();
                        BundleCompat.putBinder(bundle1, "extra_session_binder", ((IBinder) (obj)));
                        obj = bundle1;
                    } else
                    {
                        mRootExtrasList.add(bundle1);
                        obj = bundle1;
                    }
                }
            }
            bundle = MediaBrowserServiceCompat.this.onGetRoot(s, i, bundle);
            if(bundle == null)
                return null;
            if(obj != null) goto _L2; else goto _L1
_L1:
            s = bundle.getExtras();
_L4:
            return new MediaBrowserServiceCompatApi21.BrowserRoot(bundle.getRootId(), s);
_L2:
            s = ((String) (obj));
            if(bundle.getExtras() != null)
            {
                ((Bundle) (obj)).putAll(bundle.getExtras());
                s = ((String) (obj));
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void onLoadChildren(String s, MediaBrowserServiceCompatApi21.ResultWrapper resultwrapper)
        {
            resultwrapper = s. new Result(resultwrapper) {

                public void detach()
                {
                    resultWrapper.detach();
                }

                volatile void onResultSent(Object obj)
                {
                    onResultSent((List)obj);
                }

                void onResultSent(List list)
                {
                    Object obj = null;
                    if(list != null)
                    {
                        ArrayList arraylist = new ArrayList();
                        list = list.iterator();
                        do
                        {
                            obj = arraylist;
                            if(!list.hasNext())
                                break;
                            obj = (MediaBrowserCompat.MediaItem)list.next();
                            Parcel parcel = Parcel.obtain();
                            ((MediaBrowserCompat.MediaItem) (obj)).writeToParcel(parcel, 0);
                            arraylist.add(parcel);
                        } while(true);
                    }
                    resultWrapper.sendResult(obj);
                }

                final MediaBrowserServiceImplApi21 this$1;
                final MediaBrowserServiceCompatApi21.ResultWrapper val$resultWrapper;

            
            {
                this$1 = final_mediabrowserserviceimplapi21;
                resultWrapper = resultwrapper;
                super(Object.this);
            }
            }
;
            MediaBrowserServiceCompat.this.onLoadChildren(s, resultwrapper);
        }

        public void setSessionToken(android.support.v4.media.session.MediaSessionCompat.Token token)
        {
            mHandler.postOrRun(token. new Runnable() {

                public void run()
                {
                    if(!mRootExtrasList.isEmpty())
                    {
                        IMediaSession imediasession = token.getExtraBinder();
                        if(imediasession != null)
                        {
                            for(Iterator iterator = mRootExtrasList.iterator(); iterator.hasNext(); BundleCompat.putBinder((Bundle)iterator.next(), "extra_session_binder", imediasession.asBinder()));
                        }
                        mRootExtrasList.clear();
                    }
                    MediaBrowserServiceCompatApi21.setSessionToken(mServiceObj, token.getToken());
                }

                final MediaBrowserServiceImplApi21 this$1;
                final android.support.v4.media.session.MediaSessionCompat.Token val$token;

            
            {
                this$1 = final_mediabrowserserviceimplapi21;
                token = android.support.v4.media.session.MediaSessionCompat.Token.this;
                super();
            }
            }
);
        }

        Messenger mMessenger;
        final List mRootExtrasList = new ArrayList();
        Object mServiceObj;
        final MediaBrowserServiceCompat this$0;

        MediaBrowserServiceImplApi21()
        {
            this$0 = MediaBrowserServiceCompat.this;
            super();
        }
    }

    class MediaBrowserServiceImplApi23 extends MediaBrowserServiceImplApi21
        implements MediaBrowserServiceCompatApi23.ServiceCompatProxy
    {

        public void onCreate()
        {
            mServiceObj = MediaBrowserServiceCompatApi23.createService(MediaBrowserServiceCompat.this, this);
            MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
        }

        public void onLoadItem(String s, MediaBrowserServiceCompatApi21.ResultWrapper resultwrapper)
        {
            resultwrapper = s. new Result(resultwrapper) {

                public void detach()
                {
                    resultWrapper.detach();
                }

                void onResultSent(MediaBrowserCompat.MediaItem mediaitem)
                {
                    if(mediaitem == null)
                    {
                        resultWrapper.sendResult(null);
                        return;
                    } else
                    {
                        Parcel parcel = Parcel.obtain();
                        mediaitem.writeToParcel(parcel, 0);
                        resultWrapper.sendResult(parcel);
                        return;
                    }
                }

                volatile void onResultSent(Object obj)
                {
                    onResultSent((MediaBrowserCompat.MediaItem)obj);
                }

                final MediaBrowserServiceImplApi23 this$1;
                final MediaBrowserServiceCompatApi21.ResultWrapper val$resultWrapper;

            
            {
                this$1 = final_mediabrowserserviceimplapi23;
                resultWrapper = resultwrapper;
                super(Object.this);
            }
            }
;
            MediaBrowserServiceCompat.this.onLoadItem(s, resultwrapper);
        }

        final MediaBrowserServiceCompat this$0;

        MediaBrowserServiceImplApi23()
        {
            this$0 = MediaBrowserServiceCompat.this;
            super();
        }
    }

    class MediaBrowserServiceImplApi24 extends MediaBrowserServiceImplApi23
        implements MediaBrowserServiceCompatApi24.ServiceCompatProxy
    {

        public Bundle getBrowserRootHints()
        {
            if(mCurConnection != null)
            {
                if(mCurConnection.rootHints == null)
                    return null;
                else
                    return new Bundle(mCurConnection.rootHints);
            } else
            {
                return MediaBrowserServiceCompatApi24.getBrowserRootHints(mServiceObj);
            }
        }

        public void notifyChildrenChanged(String s, Bundle bundle)
        {
            if(bundle == null)
            {
                MediaBrowserServiceCompatApi21.notifyChildrenChanged(mServiceObj, s);
                return;
            } else
            {
                MediaBrowserServiceCompatApi24.notifyChildrenChanged(mServiceObj, s, bundle);
                return;
            }
        }

        public void onCreate()
        {
            mServiceObj = MediaBrowserServiceCompatApi24.createService(MediaBrowserServiceCompat.this, this);
            MediaBrowserServiceCompatApi21.onCreate(mServiceObj);
        }

        public void onLoadChildren(String s, MediaBrowserServiceCompatApi24.ResultWrapper resultwrapper, Bundle bundle)
        {
            resultwrapper = s. new Result(resultwrapper) {

                public void detach()
                {
                    resultWrapper.detach();
                }

                volatile void onResultSent(Object obj)
                {
                    onResultSent((List)obj);
                }

                void onResultSent(List list)
                {
                    Object obj = null;
                    if(list != null)
                    {
                        ArrayList arraylist = new ArrayList();
                        list = list.iterator();
                        do
                        {
                            obj = arraylist;
                            if(!list.hasNext())
                                break;
                            obj = (MediaBrowserCompat.MediaItem)list.next();
                            Parcel parcel = Parcel.obtain();
                            ((MediaBrowserCompat.MediaItem) (obj)).writeToParcel(parcel, 0);
                            arraylist.add(parcel);
                        } while(true);
                    }
                    resultWrapper.sendResult(((List) (obj)), getFlags());
                }

                final MediaBrowserServiceImplApi24 this$1;
                final MediaBrowserServiceCompatApi24.ResultWrapper val$resultWrapper;

            
            {
                this$1 = final_mediabrowserserviceimplapi24;
                resultWrapper = resultwrapper;
                super(Object.this);
            }
            }
;
            MediaBrowserServiceCompat.this.onLoadChildren(s, resultwrapper, bundle);
        }

        final MediaBrowserServiceCompat this$0;

        MediaBrowserServiceImplApi24()
        {
            this$0 = MediaBrowserServiceCompat.this;
            super();
        }
    }

    class MediaBrowserServiceImplBase
        implements MediaBrowserServiceImpl
    {

        public Bundle getBrowserRootHints()
        {
            if(mCurConnection == null)
                throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem or onSearch methods");
            if(mCurConnection.rootHints == null)
                return null;
            else
                return new Bundle(mCurConnection.rootHints);
        }

        public void notifyChildrenChanged(final String parentId, Bundle bundle)
        {
            mHandler.post(bundle. new Runnable() {

                public void run()
                {
                    for(Iterator iterator = mConnections.keySet().iterator(); iterator.hasNext();)
                    {
                        Object obj = (IBinder)iterator.next();
                        obj = (ConnectionRecord)mConnections.get(obj);
                        Object obj1 = (List)((ConnectionRecord) (obj)).subscriptions.get(parentId);
                        if(obj1 != null)
                        {
                            obj1 = ((List) (obj1)).iterator();
                            while(((Iterator) (obj1)).hasNext()) 
                            {
                                Pair pair = (Pair)((Iterator) (obj1)).next();
                                if(MediaBrowserCompatUtils.hasDuplicatedItems(options, (Bundle)pair.second))
                                    performLoadChildren(parentId, ((ConnectionRecord) (obj)), (Bundle)pair.second);
                            }
                        }
                    }

                }

                final MediaBrowserServiceImplBase this$1;
                final Bundle val$options;
                final String val$parentId;

            
            {
                this$1 = final_mediabrowserserviceimplbase;
                parentId = s;
                options = Bundle.this;
                super();
            }
            }
);
        }

        public IBinder onBind(Intent intent)
        {
            if("android.media.browse.MediaBrowserService".equals(intent.getAction()))
                return mMessenger.getBinder();
            else
                return null;
        }

        public void onCreate()
        {
            mMessenger = new Messenger(mHandler);
        }

        public void setSessionToken(android.support.v4.media.session.MediaSessionCompat.Token token)
        {
            mHandler.post(token. new Runnable() {

                public void run()
                {
                    for(Iterator iterator = mConnections.values().iterator(); iterator.hasNext();)
                    {
                        ConnectionRecord connectionrecord = (ConnectionRecord)iterator.next();
                        try
                        {
                            connectionrecord.callbacks.onConnect(connectionrecord.root.getRootId(), token, connectionrecord.root.getExtras());
                        }
                        catch(RemoteException remoteexception)
                        {
                            Log.w("MBServiceCompat", (new StringBuilder()).append("Connection for ").append(connectionrecord.pkg).append(" is no longer valid.").toString());
                            iterator.remove();
                        }
                    }

                }

                final MediaBrowserServiceImplBase this$1;
                final android.support.v4.media.session.MediaSessionCompat.Token val$token;

            
            {
                this$1 = final_mediabrowserserviceimplbase;
                token = android.support.v4.media.session.MediaSessionCompat.Token.this;
                super();
            }
            }
);
        }

        private Messenger mMessenger;
        final MediaBrowserServiceCompat this$0;

        MediaBrowserServiceImplBase()
        {
            this$0 = MediaBrowserServiceCompat.this;
            super();
        }
    }

    public static class Result
    {

        private void checkExtraFields(Bundle bundle)
        {
            if(bundle != null && bundle.containsKey("android.media.browse.extra.DOWNLOAD_PROGRESS"))
            {
                float f = bundle.getFloat("android.media.browse.extra.DOWNLOAD_PROGRESS");
                if(f < -1E-005F || f > 1.00001F)
                    throw new IllegalArgumentException("The value of the EXTRA_DOWNLOAD_PROGRESS field must be a float number within [0.0, 1.0].");
            }
        }

        public void detach()
        {
            if(mDetachCalled)
                throw new IllegalStateException((new StringBuilder()).append("detach() called when detach() had already been called for: ").append(mDebug).toString());
            if(mSendResultCalled)
                throw new IllegalStateException((new StringBuilder()).append("detach() called when sendResult() had already been called for: ").append(mDebug).toString());
            if(mSendErrorCalled)
            {
                throw new IllegalStateException((new StringBuilder()).append("detach() called when sendError() had already been called for: ").append(mDebug).toString());
            } else
            {
                mDetachCalled = true;
                return;
            }
        }

        int getFlags()
        {
            return mFlags;
        }

        boolean isDone()
        {
            return mDetachCalled || mSendResultCalled || mSendErrorCalled;
        }

        void onErrorSent(Bundle bundle)
        {
            throw new UnsupportedOperationException((new StringBuilder()).append("It is not supported to send an error for ").append(mDebug).toString());
        }

        void onProgressUpdateSent(Bundle bundle)
        {
            throw new UnsupportedOperationException((new StringBuilder()).append("It is not supported to send an interim update for ").append(mDebug).toString());
        }

        void onResultSent(Object obj)
        {
        }

        public void sendError(Bundle bundle)
        {
            if(mSendResultCalled || mSendErrorCalled)
            {
                throw new IllegalStateException((new StringBuilder()).append("sendError() called when either sendResult() or sendError() had already been called for: ").append(mDebug).toString());
            } else
            {
                mSendErrorCalled = true;
                onErrorSent(bundle);
                return;
            }
        }

        public void sendProgressUpdate(Bundle bundle)
        {
            if(mSendResultCalled || mSendErrorCalled)
            {
                throw new IllegalStateException((new StringBuilder()).append("sendProgressUpdate() called when either sendResult() or sendError() had already been called for: ").append(mDebug).toString());
            } else
            {
                checkExtraFields(bundle);
                mSendProgressUpdateCalled = true;
                onProgressUpdateSent(bundle);
                return;
            }
        }

        public void sendResult(Object obj)
        {
            if(mSendResultCalled || mSendErrorCalled)
            {
                throw new IllegalStateException((new StringBuilder()).append("sendResult() called when either sendResult() or sendError() had already been called for: ").append(mDebug).toString());
            } else
            {
                mSendResultCalled = true;
                onResultSent(obj);
                return;
            }
        }

        void setFlags(int i)
        {
            mFlags = i;
        }

        private final Object mDebug;
        private boolean mDetachCalled;
        private int mFlags;
        private boolean mSendErrorCalled;
        private boolean mSendProgressUpdateCalled;
        private boolean mSendResultCalled;

        Result(Object obj)
        {
            mDebug = obj;
        }
    }

    private static interface ResultFlags
        extends Annotation
    {
    }

    private class ServiceBinderImpl
    {

        public void addSubscription(final String id, final IBinder token, Bundle bundle, final ServiceCallbacks callbacks)
        {
            mHandler.postOrRun(bundle. new Runnable() {

                public void run()
                {
                    Object obj = callbacks.asBinder();
                    obj = (ConnectionRecord)mConnections.get(obj);
                    if(obj == null)
                    {
                        Log.w("MBServiceCompat", (new StringBuilder()).append("addSubscription for callback that isn't registered id=").append(id).toString());
                        return;
                    } else
                    {
                        addSubscription(id, ((ConnectionRecord) (obj)), token, options);
                        return;
                    }
                }

                final ServiceBinderImpl this$1;
                final ServiceCallbacks val$callbacks;
                final String val$id;
                final Bundle val$options;
                final IBinder val$token;

            
            {
                this$1 = final_servicebinderimpl;
                callbacks = servicecallbacks;
                id = s;
                token = ibinder;
                options = Bundle.this;
                super();
            }
            }
);
        }

        public void connect(final String pkg, int i, final Bundle rootHints, final ServiceCallbacks callbacks)
        {
            if(!isValidPackage(pkg, i))
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Package/uid mismatch: uid=").append(i).append(" package=").append(pkg).toString());
            } else
            {
                mHandler.postOrRun(i. new Runnable() {

                    public void run()
                    {
                        Object obj;
                        ConnectionRecord connectionrecord;
                        obj = callbacks.asBinder();
                        mConnections.remove(obj);
                        connectionrecord = new ConnectionRecord();
                        connectionrecord.pkg = pkg;
                        connectionrecord.rootHints = rootHints;
                        connectionrecord.callbacks = callbacks;
                        connectionrecord.root = onGetRoot(pkg, uid, rootHints);
                        if(connectionrecord.root != null)
                            break MISSING_BLOCK_LABEL_173;
                        Log.i("MBServiceCompat", (new StringBuilder()).append("No root for client ").append(pkg).append(" from service ").append(getClass().getName()).toString());
                        callbacks.onConnectFailed();
_L1:
                        return;
                        obj;
                        Log.w("MBServiceCompat", (new StringBuilder()).append("Calling onConnectFailed() failed. Ignoring. pkg=").append(pkg).toString());
                        return;
                        try
                        {
                            mConnections.put(obj, connectionrecord);
                            if(mSession != null)
                            {
                                callbacks.onConnect(connectionrecord.root.getRootId(), mSession, connectionrecord.root.getExtras());
                                return;
                            }
                        }
                        catch(RemoteException remoteexception)
                        {
                            Log.w("MBServiceCompat", (new StringBuilder()).append("Calling onConnect() failed. Dropping client. pkg=").append(pkg).toString());
                            mConnections.remove(obj);
                            return;
                        }
                          goto _L1
                    }

                    final ServiceBinderImpl this$1;
                    final ServiceCallbacks val$callbacks;
                    final String val$pkg;
                    final Bundle val$rootHints;
                    final int val$uid;

            
            {
                this$1 = final_servicebinderimpl;
                callbacks = servicecallbacks;
                pkg = s;
                rootHints = bundle;
                uid = I.this;
                super();
            }
                }
);
                return;
            }
        }

        public void disconnect(ServiceCallbacks servicecallbacks)
        {
            mHandler.postOrRun(servicecallbacks. new Runnable() {

                public void run()
                {
                    IBinder ibinder = callbacks.asBinder();
                    if((ConnectionRecord)mConnections.remove(ibinder) == null);
                }

                final ServiceBinderImpl this$1;
                final ServiceCallbacks val$callbacks;

            
            {
                this$1 = final_servicebinderimpl;
                callbacks = ServiceCallbacks.this;
                super();
            }
            }
);
        }

        public void getMediaItem(final String mediaId, ResultReceiver resultreceiver, final ServiceCallbacks callbacks)
        {
            if(TextUtils.isEmpty(mediaId) || resultreceiver == null)
            {
                return;
            } else
            {
                mHandler.postOrRun(resultreceiver. new Runnable() {

                    public void run()
                    {
                        Object obj = callbacks.asBinder();
                        obj = (ConnectionRecord)mConnections.get(obj);
                        if(obj == null)
                        {
                            Log.w("MBServiceCompat", (new StringBuilder()).append("getMediaItem for callback that isn't registered id=").append(mediaId).toString());
                            return;
                        } else
                        {
                            performLoadItem(mediaId, ((ConnectionRecord) (obj)), receiver);
                            return;
                        }
                    }

                    final ServiceBinderImpl this$1;
                    final ServiceCallbacks val$callbacks;
                    final String val$mediaId;
                    final ResultReceiver val$receiver;

            
            {
                this$1 = final_servicebinderimpl;
                callbacks = servicecallbacks;
                mediaId = s;
                receiver = ResultReceiver.this;
                super();
            }
                }
);
                return;
            }
        }

        public void registerCallbacks(final ServiceCallbacks callbacks, Bundle bundle)
        {
            mHandler.postOrRun(bundle. new Runnable() {

                public void run()
                {
                    IBinder ibinder = callbacks.asBinder();
                    mConnections.remove(ibinder);
                    ConnectionRecord connectionrecord = new ConnectionRecord();
                    connectionrecord.callbacks = callbacks;
                    connectionrecord.rootHints = rootHints;
                    mConnections.put(ibinder, connectionrecord);
                }

                final ServiceBinderImpl this$1;
                final ServiceCallbacks val$callbacks;
                final Bundle val$rootHints;

            
            {
                this$1 = final_servicebinderimpl;
                callbacks = servicecallbacks;
                rootHints = Bundle.this;
                super();
            }
            }
);
        }

        public void removeSubscription(final String id, IBinder ibinder, final ServiceCallbacks callbacks)
        {
            mHandler.postOrRun(ibinder. new Runnable() {

                public void run()
                {
                    Object obj = callbacks.asBinder();
                    obj = (ConnectionRecord)mConnections.get(obj);
                    if(obj == null)
                        Log.w("MBServiceCompat", (new StringBuilder()).append("removeSubscription for callback that isn't registered id=").append(id).toString());
                    else
                    if(!removeSubscription(id, ((ConnectionRecord) (obj)), token))
                    {
                        Log.w("MBServiceCompat", (new StringBuilder()).append("removeSubscription called for ").append(id).append(" which is not subscribed").toString());
                        return;
                    }
                }

                final ServiceBinderImpl this$1;
                final ServiceCallbacks val$callbacks;
                final String val$id;
                final IBinder val$token;

            
            {
                this$1 = final_servicebinderimpl;
                callbacks = servicecallbacks;
                id = s;
                token = IBinder.this;
                super();
            }
            }
);
        }

        public void search(final String query, final Bundle extras, ResultReceiver resultreceiver, final ServiceCallbacks callbacks)
        {
            if(TextUtils.isEmpty(query) || resultreceiver == null)
            {
                return;
            } else
            {
                mHandler.postOrRun(resultreceiver. new Runnable() {

                    public void run()
                    {
                        Object obj = callbacks.asBinder();
                        obj = (ConnectionRecord)mConnections.get(obj);
                        if(obj == null)
                        {
                            Log.w("MBServiceCompat", (new StringBuilder()).append("search for callback that isn't registered query=").append(query).toString());
                            return;
                        } else
                        {
                            performSearch(query, extras, ((ConnectionRecord) (obj)), receiver);
                            return;
                        }
                    }

                    final ServiceBinderImpl this$1;
                    final ServiceCallbacks val$callbacks;
                    final Bundle val$extras;
                    final String val$query;
                    final ResultReceiver val$receiver;

            
            {
                this$1 = final_servicebinderimpl;
                callbacks = servicecallbacks;
                query = s;
                extras = bundle;
                receiver = ResultReceiver.this;
                super();
            }
                }
);
                return;
            }
        }

        public void sendCustomAction(final String action, final Bundle extras, ResultReceiver resultreceiver, final ServiceCallbacks callbacks)
        {
            if(TextUtils.isEmpty(action) || resultreceiver == null)
            {
                return;
            } else
            {
                mHandler.postOrRun(resultreceiver. new Runnable() {

                    public void run()
                    {
                        Object obj = callbacks.asBinder();
                        obj = (ConnectionRecord)mConnections.get(obj);
                        if(obj == null)
                        {
                            Log.w("MBServiceCompat", (new StringBuilder()).append("sendCustomAction for callback that isn't registered action=").append(action).append(", extras=").append(extras).toString());
                            return;
                        } else
                        {
                            performCustomAction(action, extras, ((ConnectionRecord) (obj)), receiver);
                            return;
                        }
                    }

                    final ServiceBinderImpl this$1;
                    final String val$action;
                    final ServiceCallbacks val$callbacks;
                    final Bundle val$extras;
                    final ResultReceiver val$receiver;

            
            {
                this$1 = final_servicebinderimpl;
                callbacks = servicecallbacks;
                action = s;
                extras = bundle;
                receiver = ResultReceiver.this;
                super();
            }
                }
);
                return;
            }
        }

        public void unregisterCallbacks(ServiceCallbacks servicecallbacks)
        {
            mHandler.postOrRun(servicecallbacks. new Runnable() {

                public void run()
                {
                    IBinder ibinder = callbacks.asBinder();
                    mConnections.remove(ibinder);
                }

                final ServiceBinderImpl this$1;
                final ServiceCallbacks val$callbacks;

            
            {
                this$1 = final_servicebinderimpl;
                callbacks = ServiceCallbacks.this;
                super();
            }
            }
);
        }

        final MediaBrowserServiceCompat this$0;

        ServiceBinderImpl()
        {
            this$0 = MediaBrowserServiceCompat.this;
            super();
        }
    }

    private static interface ServiceCallbacks
    {

        public abstract IBinder asBinder();

        public abstract void onConnect(String s, android.support.v4.media.session.MediaSessionCompat.Token token, Bundle bundle)
            throws RemoteException;

        public abstract void onConnectFailed()
            throws RemoteException;

        public abstract void onLoadChildren(String s, List list, Bundle bundle)
            throws RemoteException;
    }

    private static class ServiceCallbacksCompat
        implements ServiceCallbacks
    {

        private void sendRequest(int i, Bundle bundle)
            throws RemoteException
        {
            Message message = Message.obtain();
            message.what = i;
            message.arg1 = 1;
            message.setData(bundle);
            mCallbacks.send(message);
        }

        public IBinder asBinder()
        {
            return mCallbacks.getBinder();
        }

        public void onConnect(String s, android.support.v4.media.session.MediaSessionCompat.Token token, Bundle bundle)
            throws RemoteException
        {
            Bundle bundle1 = bundle;
            if(bundle == null)
                bundle1 = new Bundle();
            bundle1.putInt("extra_service_version", 1);
            bundle = new Bundle();
            bundle.putString("data_media_item_id", s);
            bundle.putParcelable("data_media_session_token", token);
            bundle.putBundle("data_root_hints", bundle1);
            sendRequest(1, bundle);
        }

        public void onConnectFailed()
            throws RemoteException
        {
            sendRequest(2, null);
        }

        public void onLoadChildren(String s, List list, Bundle bundle)
            throws RemoteException
        {
            Bundle bundle1 = new Bundle();
            bundle1.putString("data_media_item_id", s);
            bundle1.putBundle("data_options", bundle);
            if(list != null)
            {
                if(list instanceof ArrayList)
                    s = (ArrayList)list;
                else
                    s = new ArrayList(list);
                bundle1.putParcelableArrayList("data_media_item_list", s);
            }
            sendRequest(3, bundle1);
        }

        final Messenger mCallbacks;

        ServiceCallbacksCompat(Messenger messenger)
        {
            mCallbacks = messenger;
        }
    }

    private final class ServiceHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            Bundle bundle = message.getData();
            switch(message.what)
            {
            default:
                Log.w("MBServiceCompat", (new StringBuilder()).append("Unhandled message: ").append(message).append("\n  Service version: ").append(1).append("\n  Client version: ").append(message.arg1).toString());
                return;

            case 1: // '\001'
                mServiceBinderImpl.connect(bundle.getString("data_package_name"), bundle.getInt("data_calling_uid"), bundle.getBundle("data_root_hints"), new ServiceCallbacksCompat(message.replyTo));
                return;

            case 2: // '\002'
                mServiceBinderImpl.disconnect(new ServiceCallbacksCompat(message.replyTo));
                return;

            case 3: // '\003'
                mServiceBinderImpl.addSubscription(bundle.getString("data_media_item_id"), BundleCompat.getBinder(bundle, "data_callback_token"), bundle.getBundle("data_options"), new ServiceCallbacksCompat(message.replyTo));
                return;

            case 4: // '\004'
                mServiceBinderImpl.removeSubscription(bundle.getString("data_media_item_id"), BundleCompat.getBinder(bundle, "data_callback_token"), new ServiceCallbacksCompat(message.replyTo));
                return;

            case 5: // '\005'
                mServiceBinderImpl.getMediaItem(bundle.getString("data_media_item_id"), (ResultReceiver)bundle.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(message.replyTo));
                return;

            case 6: // '\006'
                mServiceBinderImpl.registerCallbacks(new ServiceCallbacksCompat(message.replyTo), bundle.getBundle("data_root_hints"));
                return;

            case 7: // '\007'
                mServiceBinderImpl.unregisterCallbacks(new ServiceCallbacksCompat(message.replyTo));
                return;

            case 8: // '\b'
                mServiceBinderImpl.search(bundle.getString("data_search_query"), bundle.getBundle("data_search_extras"), (ResultReceiver)bundle.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(message.replyTo));
                return;

            case 9: // '\t'
                mServiceBinderImpl.sendCustomAction(bundle.getString("data_custom_action"), bundle.getBundle("data_custom_action_extras"), (ResultReceiver)bundle.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(message.replyTo));
                break;
            }
        }

        public void postOrRun(Runnable runnable)
        {
            if(Thread.currentThread() == getLooper().getThread())
            {
                runnable.run();
                return;
            } else
            {
                post(runnable);
                return;
            }
        }

        public boolean sendMessageAtTime(Message message, long l)
        {
            Bundle bundle = message.getData();
            bundle.setClassLoader(android/support/v4/media/MediaBrowserCompat.getClassLoader());
            bundle.putInt("data_calling_uid", Binder.getCallingUid());
            return super.sendMessageAtTime(message, l);
        }

        private final ServiceBinderImpl mServiceBinderImpl;
        final MediaBrowserServiceCompat this$0;

        ServiceHandler()
        {
            this$0 = MediaBrowserServiceCompat.this;
            super();
            mServiceBinderImpl = new ServiceBinderImpl();
        }
    }


    public MediaBrowserServiceCompat()
    {
    }

    void addSubscription(String s, ConnectionRecord connectionrecord, IBinder ibinder, Bundle bundle)
    {
        List list = (List)connectionrecord.subscriptions.get(s);
        Object obj = list;
        if(list == null)
            obj = new ArrayList();
        for(Iterator iterator = ((List) (obj)).iterator(); iterator.hasNext();)
        {
            Pair pair = (Pair)iterator.next();
            if(ibinder == pair.first && MediaBrowserCompatUtils.areSameOptions(bundle, (Bundle)pair.second))
                return;
        }

        ((List) (obj)).add(new Pair(ibinder, bundle));
        connectionrecord.subscriptions.put(s, obj);
        performLoadChildren(s, connectionrecord, bundle);
    }

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

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
    }

    public final Bundle getBrowserRootHints()
    {
        return mImpl.getBrowserRootHints();
    }

    public android.support.v4.media.session.MediaSessionCompat.Token getSessionToken()
    {
        return mSession;
    }

    boolean isValidPackage(String s, int i)
    {
        if(s != null)
        {
            String as[] = getPackageManager().getPackagesForUid(i);
            int j = as.length;
            i = 0;
            while(i < j) 
            {
                if(as[i].equals(s))
                    return true;
                i++;
            }
        }
        return false;
    }

    public void notifyChildrenChanged(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        } else
        {
            mImpl.notifyChildrenChanged(s, null);
            return;
        }
    }

    public void notifyChildrenChanged(String s, Bundle bundle)
    {
        if(s == null)
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        if(bundle == null)
        {
            throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
        } else
        {
            mImpl.notifyChildrenChanged(s, bundle);
            return;
        }
    }

    public IBinder onBind(Intent intent)
    {
        return mImpl.onBind(intent);
    }

    public void onCreate()
    {
        super.onCreate();
        if(android.os.Build.VERSION.SDK_INT >= 26)
            mImpl = new MediaBrowserServiceImplApi24();
        else
        if(android.os.Build.VERSION.SDK_INT >= 23)
            mImpl = new MediaBrowserServiceImplApi23();
        else
        if(android.os.Build.VERSION.SDK_INT >= 21)
            mImpl = new MediaBrowserServiceImplApi21();
        else
            mImpl = new MediaBrowserServiceImplBase();
        mImpl.onCreate();
    }

    public void onCustomAction(String s, Bundle bundle, Result result)
    {
        result.sendError(null);
    }

    public abstract BrowserRoot onGetRoot(String s, int i, Bundle bundle);

    public abstract void onLoadChildren(String s, Result result);

    public void onLoadChildren(String s, Result result, Bundle bundle)
    {
        result.setFlags(1);
        onLoadChildren(s, result);
    }

    public void onLoadItem(String s, Result result)
    {
        result.setFlags(2);
        result.sendResult(null);
    }

    public void onSearch(String s, Bundle bundle, Result result)
    {
        result.setFlags(4);
        result.sendResult(null);
    }

    void performCustomAction(final String final_obj, Bundle bundle, ConnectionRecord connectionrecord, ResultReceiver resultreceiver)
    {
        resultreceiver = new Result(resultreceiver) {

            void onErrorSent(Bundle bundle1)
            {
                receiver.send(-1, bundle1);
            }

            void onProgressUpdateSent(Bundle bundle1)
            {
                receiver.send(1, bundle1);
            }

            void onResultSent(Bundle bundle1)
            {
                receiver.send(0, bundle1);
            }

            volatile void onResultSent(Object obj)
            {
                onResultSent((Bundle)obj);
            }

            final MediaBrowserServiceCompat this$0;
            final ResultReceiver val$receiver;

            
            {
                this$0 = MediaBrowserServiceCompat.this;
                receiver = resultreceiver;
                super(final_obj);
            }
        }
;
        mCurConnection = connectionrecord;
        onCustomAction(final_obj, bundle, resultreceiver);
        mCurConnection = null;
        if(!resultreceiver.isDone())
            throw new IllegalStateException((new StringBuilder()).append("onCustomAction must call detach() or sendResult() or sendError() before returning for action=").append(final_obj).append(" extras=").append(bundle).toString());
        else
            return;
    }

    void performLoadChildren(final String final_obj, final ConnectionRecord connection, Bundle bundle)
    {
        Result result = new Result(bundle) {

            volatile void onResultSent(Object obj)
            {
                onResultSent((List)obj);
            }

            void onResultSent(List list)
            {
                if(mConnections.get(connection.callbacks.asBinder()) != connection)
                {
                    if(MediaBrowserServiceCompat.DEBUG)
                        Log.d("MBServiceCompat", (new StringBuilder()).append("Not sending onLoadChildren result for connection that has been disconnected. pkg=").append(connection.pkg).append(" id=").append(parentId).toString());
                    return;
                }
                if((getFlags() & 1) != 0)
                    list = applyOptions(list, options);
                try
                {
                    connection.callbacks.onLoadChildren(parentId, list, options);
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(List list)
                {
                    Log.w("MBServiceCompat", (new StringBuilder()).append("Calling onLoadChildren() failed for id=").append(parentId).append(" package=").append(connection.pkg).toString());
                }
            }

            final MediaBrowserServiceCompat this$0;
            final ConnectionRecord val$connection;
            final Bundle val$options;
            final String val$parentId;

            
            {
                this$0 = MediaBrowserServiceCompat.this;
                connection = connectionrecord;
                parentId = s;
                options = bundle;
                super(final_obj);
            }
        }
;
        mCurConnection = connection;
        if(bundle == null)
            onLoadChildren(final_obj, result);
        else
            onLoadChildren(final_obj, result, bundle);
        mCurConnection = null;
        if(!result.isDone())
            throw new IllegalStateException((new StringBuilder()).append("onLoadChildren must call detach() or sendResult() before returning for package=").append(connection.pkg).append(" id=").append(final_obj).toString());
        else
            return;
    }

    void performLoadItem(final String final_obj, ConnectionRecord connectionrecord, ResultReceiver resultreceiver)
    {
        resultreceiver = new Result(resultreceiver) {

            void onResultSent(MediaBrowserCompat.MediaItem mediaitem)
            {
                if((getFlags() & 2) != 0)
                {
                    receiver.send(-1, null);
                    return;
                } else
                {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("media_item", mediaitem);
                    receiver.send(0, bundle);
                    return;
                }
            }

            volatile void onResultSent(Object obj)
            {
                onResultSent((MediaBrowserCompat.MediaItem)obj);
            }

            final MediaBrowserServiceCompat this$0;
            final ResultReceiver val$receiver;

            
            {
                this$0 = MediaBrowserServiceCompat.this;
                receiver = resultreceiver;
                super(final_obj);
            }
        }
;
        mCurConnection = connectionrecord;
        onLoadItem(final_obj, resultreceiver);
        mCurConnection = null;
        if(!resultreceiver.isDone())
            throw new IllegalStateException((new StringBuilder()).append("onLoadItem must call detach() or sendResult() before returning for id=").append(final_obj).toString());
        else
            return;
    }

    void performSearch(final String final_obj, Bundle bundle, ConnectionRecord connectionrecord, ResultReceiver resultreceiver)
    {
        resultreceiver = new Result(resultreceiver) {

            volatile void onResultSent(Object obj)
            {
                onResultSent((List)obj);
            }

            void onResultSent(List list)
            {
                if((getFlags() & 4) != 0 || list == null)
                {
                    receiver.send(-1, null);
                    return;
                } else
                {
                    Bundle bundle1 = new Bundle();
                    bundle1.putParcelableArray("search_results", (android.os.Parcelable[])list.toArray(new MediaBrowserCompat.MediaItem[0]));
                    receiver.send(0, bundle1);
                    return;
                }
            }

            final MediaBrowserServiceCompat this$0;
            final ResultReceiver val$receiver;

            
            {
                this$0 = MediaBrowserServiceCompat.this;
                receiver = resultreceiver;
                super(final_obj);
            }
        }
;
        mCurConnection = connectionrecord;
        onSearch(final_obj, bundle, resultreceiver);
        mCurConnection = null;
        if(!resultreceiver.isDone())
            throw new IllegalStateException((new StringBuilder()).append("onSearch must call detach() or sendResult() before returning for query=").append(final_obj).toString());
        else
            return;
    }

    boolean removeSubscription(String s, ConnectionRecord connectionrecord, IBinder ibinder)
    {
        if(ibinder == null)
            return connectionrecord.subscriptions.remove(s) != null;
        boolean flag1 = false;
        boolean flag = false;
        List list = (List)connectionrecord.subscriptions.get(s);
        if(list != null)
        {
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                if(ibinder == ((Pair)iterator.next()).first)
                {
                    flag = true;
                    iterator.remove();
                }
            } while(true);
            flag1 = flag;
            if(list.size() == 0)
            {
                connectionrecord.subscriptions.remove(s);
                flag1 = flag;
            }
        }
        return flag1;
    }

    public void setSessionToken(android.support.v4.media.session.MediaSessionCompat.Token token)
    {
        if(token == null)
            throw new IllegalArgumentException("Session token may not be null.");
        if(mSession != null)
        {
            throw new IllegalStateException("The session token has already been set.");
        } else
        {
            mSession = token;
            mImpl.setSessionToken(token);
            return;
        }
    }

    static final boolean DEBUG = Log.isLoggable("MBServiceCompat", 3);
    private static final float EPSILON = 1E-005F;
    public static final String KEY_MEDIA_ITEM = "media_item";
    public static final String KEY_SEARCH_RESULTS = "search_results";
    static final int RESULT_ERROR = -1;
    static final int RESULT_FLAG_ON_LOAD_ITEM_NOT_IMPLEMENTED = 2;
    static final int RESULT_FLAG_ON_SEARCH_NOT_IMPLEMENTED = 4;
    static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
    static final int RESULT_OK = 0;
    static final int RESULT_PROGRESS_UPDATE = 1;
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
    static final String TAG = "MBServiceCompat";
    final ArrayMap mConnections = new ArrayMap();
    ConnectionRecord mCurConnection;
    final ServiceHandler mHandler = new ServiceHandler();
    private MediaBrowserServiceImpl mImpl;
    android.support.v4.media.session.MediaSessionCompat.Token mSession;

}
