// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.*;
import android.support.v4.media.*;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            PlaybackStateCompat, ParcelableVolumeInfo, IMediaControllerCallback

public interface IMediaSession
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaSession
    {

        public static IMediaSession asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            if(iinterface != null && (iinterface instanceof IMediaSession))
                return (IMediaSession)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag = false;
            String s4;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.support.v4.media.session.IMediaSession");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                String s5 = parcel.readString();
                Bundle bundle;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                if(parcel.readInt() != 0)
                    parcel = (MediaSessionCompat.ResultReceiverWrapper)MediaSessionCompat.ResultReceiverWrapper.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendCommand(s5, bundle, parcel);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean flag4;
                if(parcel.readInt() != 0)
                    parcel = (KeyEvent)KeyEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag4 = sendMediaButton(parcel);
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag4)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                registerCallbackListener(IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                unregisterCallbackListener(IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean flag5 = isTransportControlEnabled();
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag5)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                parcel = getPackageName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                parcel = getTag();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                parcel = getLaunchPendingIntent();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9: // '\t'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                long l = getFlags();
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                parcel = getVolumeAttributes();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 11: // '\013'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                adjustVolume(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                setVolumeTo(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                parcel = getMetadata();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 28: // '\034'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                parcel = getPlaybackState();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 29: // '\035'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                parcel = getQueue();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                parcel = getQueueTitle();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    TextUtils.writeToParcel(parcel, parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 31: // '\037'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                parcel = getExtras();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 32: // ' '
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                i = getRatingType();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean flag6 = isCaptioningEnabled();
                parcel1.writeNoException();
                i = ((flag2) ? 1 : 0);
                if(flag6)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                i = getRepeatMode();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean flag7 = isShuffleModeEnabledDeprecated();
                parcel1.writeNoException();
                i = ((flag3) ? 1 : 0);
                if(flag7)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                i = getShuffleMode();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                if(parcel.readInt() != 0)
                    parcel = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addQueueItem(parcel);
                parcel1.writeNoException();
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                MediaDescriptionCompat mediadescriptioncompat;
                if(parcel.readInt() != 0)
                    mediadescriptioncompat = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
                else
                    mediadescriptioncompat = null;
                addQueueItemAt(mediadescriptioncompat, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                if(parcel.readInt() != 0)
                    parcel = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                removeQueueItem(parcel);
                parcel1.writeNoException();
                return true;

            case 44: // ','
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                removeQueueItemAt(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                prepare();
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                prepareFromMediaId(s, parcel);
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                String s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                prepareFromSearch(s1, parcel);
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                Uri uri;
                if(parcel.readInt() != 0)
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                prepareFromUri(uri, parcel);
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                play();
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                String s2 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                playFromMediaId(s2, parcel);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                String s3 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                playFromSearch(s3, parcel);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                Uri uri1;
                if(parcel.readInt() != 0)
                    uri1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                playFromUri(uri1, parcel);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                skipToQueueItem(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                pause();
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                stop();
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                next();
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                previous();
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                fastForward();
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                rewind();
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                seekTo(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                if(parcel.readInt() != 0)
                    parcel = (RatingCompat)RatingCompat.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                rate(parcel);
                parcel1.writeNoException();
                return true;

            case 51: // '3'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                RatingCompat ratingcompat;
                if(parcel.readInt() != 0)
                    ratingcompat = (RatingCompat)RatingCompat.CREATOR.createFromParcel(parcel);
                else
                    ratingcompat = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                rateWithExtras(ratingcompat, parcel);
                parcel1.writeNoException();
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                setCaptioningEnabled(flag8);
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                setRepeatMode(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                setShuffleModeEnabledDeprecated(flag9);
                parcel1.writeNoException();
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                setShuffleMode(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                s4 = parcel.readString();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            sendCustomAction(s4, parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
        static final int TRANSACTION_addQueueItem = 41;
        static final int TRANSACTION_addQueueItemAt = 42;
        static final int TRANSACTION_adjustVolume = 11;
        static final int TRANSACTION_fastForward = 22;
        static final int TRANSACTION_getExtras = 31;
        static final int TRANSACTION_getFlags = 9;
        static final int TRANSACTION_getLaunchPendingIntent = 8;
        static final int TRANSACTION_getMetadata = 27;
        static final int TRANSACTION_getPackageName = 6;
        static final int TRANSACTION_getPlaybackState = 28;
        static final int TRANSACTION_getQueue = 29;
        static final int TRANSACTION_getQueueTitle = 30;
        static final int TRANSACTION_getRatingType = 32;
        static final int TRANSACTION_getRepeatMode = 37;
        static final int TRANSACTION_getShuffleMode = 47;
        static final int TRANSACTION_getTag = 7;
        static final int TRANSACTION_getVolumeAttributes = 10;
        static final int TRANSACTION_isCaptioningEnabled = 45;
        static final int TRANSACTION_isShuffleModeEnabledDeprecated = 38;
        static final int TRANSACTION_isTransportControlEnabled = 5;
        static final int TRANSACTION_next = 20;
        static final int TRANSACTION_pause = 18;
        static final int TRANSACTION_play = 13;
        static final int TRANSACTION_playFromMediaId = 14;
        static final int TRANSACTION_playFromSearch = 15;
        static final int TRANSACTION_playFromUri = 16;
        static final int TRANSACTION_prepare = 33;
        static final int TRANSACTION_prepareFromMediaId = 34;
        static final int TRANSACTION_prepareFromSearch = 35;
        static final int TRANSACTION_prepareFromUri = 36;
        static final int TRANSACTION_previous = 21;
        static final int TRANSACTION_rate = 25;
        static final int TRANSACTION_rateWithExtras = 51;
        static final int TRANSACTION_registerCallbackListener = 3;
        static final int TRANSACTION_removeQueueItem = 43;
        static final int TRANSACTION_removeQueueItemAt = 44;
        static final int TRANSACTION_rewind = 23;
        static final int TRANSACTION_seekTo = 24;
        static final int TRANSACTION_sendCommand = 1;
        static final int TRANSACTION_sendCustomAction = 26;
        static final int TRANSACTION_sendMediaButton = 2;
        static final int TRANSACTION_setCaptioningEnabled = 46;
        static final int TRANSACTION_setRepeatMode = 39;
        static final int TRANSACTION_setShuffleMode = 48;
        static final int TRANSACTION_setShuffleModeEnabledDeprecated = 40;
        static final int TRANSACTION_setVolumeTo = 12;
        static final int TRANSACTION_skipToQueueItem = 17;
        static final int TRANSACTION_stop = 19;
        static final int TRANSACTION_unregisterCallbackListener = 4;

        public Stub()
        {
            attachInterface(this, "android.support.v4.media.session.IMediaSession");
        }
    }

    private static class Stub.Proxy
        implements IMediaSession
    {

        public void addQueueItem(MediaDescriptionCompat mediadescriptioncompat)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(mediadescriptioncompat == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            mediadescriptioncompat.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            mediadescriptioncompat;
            parcel1.recycle();
            parcel.recycle();
            throw mediadescriptioncompat;
        }

        public void addQueueItemAt(MediaDescriptionCompat mediadescriptioncompat, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(mediadescriptioncompat == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            mediadescriptioncompat.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            mediadescriptioncompat;
            parcel1.recycle();
            parcel.recycle();
            throw mediadescriptioncompat;
        }

        public void adjustVolume(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void fastForward()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Bundle getExtras()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return bundle;
_L2:
            bundle = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public long getFlags()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.support.v4.media.session.IMediaSession";
        }

        public PendingIntent getLaunchPendingIntent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            PendingIntent pendingintent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return pendingintent;
_L2:
            pendingintent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public MediaMetadataCompat getMetadata()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            MediaMetadataCompat mediametadatacompat = (MediaMetadataCompat)MediaMetadataCompat.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return mediametadatacompat;
_L2:
            mediametadatacompat = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getPackageName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public PlaybackStateCompat getPlaybackState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            PlaybackStateCompat playbackstatecompat = (PlaybackStateCompat)PlaybackStateCompat.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return playbackstatecompat;
_L2:
            playbackstatecompat = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getQueue()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public CharSequence getQueueTitle()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            CharSequence charsequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return charsequence;
_L2:
            charsequence = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getRatingType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getRepeatMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getShuffleMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getTag()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParcelableVolumeInfo getVolumeAttributes()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParcelableVolumeInfo parcelablevolumeinfo = (ParcelableVolumeInfo)ParcelableVolumeInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parcelablevolumeinfo;
_L2:
            parcelablevolumeinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isCaptioningEnabled()
            throws RemoteException
        {
            boolean flag;
            Parcel parcel;
            Parcel parcel1;
            flag = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isShuffleModeEnabledDeprecated()
            throws RemoteException
        {
            boolean flag;
            Parcel parcel;
            Parcel parcel1;
            flag = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isTransportControlEnabled()
            throws RemoteException
        {
            boolean flag;
            Parcel parcel;
            Parcel parcel1;
            flag = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void next()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void pause()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void play()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void playFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void playFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void playFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
            parcel.writeInt(0);
              goto _L4
        }

        public void prepare()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void prepareFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void prepareFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void prepareFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
            parcel.writeInt(0);
              goto _L4
        }

        public void previous()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void rate(RatingCompat ratingcompat)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(ratingcompat == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            ratingcompat.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ratingcompat;
            parcel1.recycle();
            parcel.recycle();
            throw ratingcompat;
        }

        public void rateWithExtras(RatingCompat ratingcompat, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(ratingcompat == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            ratingcompat.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            ratingcompat;
            parcel1.recycle();
            parcel.recycle();
            throw ratingcompat;
            parcel.writeInt(0);
              goto _L4
        }

        public void registerCallbackListener(IMediaControllerCallback imediacontrollercallback)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(imediacontrollercallback == null)
                break MISSING_BLOCK_LABEL_57;
            imediacontrollercallback = imediacontrollercallback.asBinder();
_L1:
            parcel.writeStrongBinder(imediacontrollercallback);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediacontrollercallback = null;
              goto _L1
            imediacontrollercallback;
            parcel1.recycle();
            parcel.recycle();
            throw imediacontrollercallback;
        }

        public void removeQueueItem(MediaDescriptionCompat mediadescriptioncompat)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(mediadescriptioncompat == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            mediadescriptioncompat.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            mediadescriptioncompat;
            parcel1.recycle();
            parcel.recycle();
            throw mediadescriptioncompat;
        }

        public void removeQueueItemAt(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeInt(i);
            mRemote.transact(44, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void rewind()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void seekTo(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeLong(l);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void sendCommand(String s, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultreceiverwrapper)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeString(s);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L3:
            if(resultreceiverwrapper == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            resultreceiverwrapper.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void sendCustomAction(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean sendMediaButton(KeyEvent keyevent)
            throws RemoteException
        {
            boolean flag;
            Parcel parcel;
            Parcel parcel1;
            flag = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(keyevent == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            keyevent.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i == 0)
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            keyevent;
            parcel1.recycle();
            parcel.recycle();
            throw keyevent;
        }

        public void setCaptioningEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setRepeatMode(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setShuffleMode(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeInt(i);
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setShuffleModeEnabledDeprecated(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setVolumeTo(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void skipToQueueItem(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            parcel.writeLong(l);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void stop()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void unregisterCallbackListener(IMediaControllerCallback imediacontrollercallback)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if(imediacontrollercallback == null)
                break MISSING_BLOCK_LABEL_57;
            imediacontrollercallback = imediacontrollercallback.asBinder();
_L1:
            parcel.writeStrongBinder(imediacontrollercallback);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediacontrollercallback = null;
              goto _L1
            imediacontrollercallback;
            parcel1.recycle();
            parcel.recycle();
            throw imediacontrollercallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        throws RemoteException;

    public abstract void addQueueItemAt(MediaDescriptionCompat mediadescriptioncompat, int i)
        throws RemoteException;

    public abstract void adjustVolume(int i, int j, String s)
        throws RemoteException;

    public abstract void fastForward()
        throws RemoteException;

    public abstract Bundle getExtras()
        throws RemoteException;

    public abstract long getFlags()
        throws RemoteException;

    public abstract PendingIntent getLaunchPendingIntent()
        throws RemoteException;

    public abstract MediaMetadataCompat getMetadata()
        throws RemoteException;

    public abstract String getPackageName()
        throws RemoteException;

    public abstract PlaybackStateCompat getPlaybackState()
        throws RemoteException;

    public abstract List getQueue()
        throws RemoteException;

    public abstract CharSequence getQueueTitle()
        throws RemoteException;

    public abstract int getRatingType()
        throws RemoteException;

    public abstract int getRepeatMode()
        throws RemoteException;

    public abstract int getShuffleMode()
        throws RemoteException;

    public abstract String getTag()
        throws RemoteException;

    public abstract ParcelableVolumeInfo getVolumeAttributes()
        throws RemoteException;

    public abstract boolean isCaptioningEnabled()
        throws RemoteException;

    public abstract boolean isShuffleModeEnabledDeprecated()
        throws RemoteException;

    public abstract boolean isTransportControlEnabled()
        throws RemoteException;

    public abstract void next()
        throws RemoteException;

    public abstract void pause()
        throws RemoteException;

    public abstract void play()
        throws RemoteException;

    public abstract void playFromMediaId(String s, Bundle bundle)
        throws RemoteException;

    public abstract void playFromSearch(String s, Bundle bundle)
        throws RemoteException;

    public abstract void playFromUri(Uri uri, Bundle bundle)
        throws RemoteException;

    public abstract void prepare()
        throws RemoteException;

    public abstract void prepareFromMediaId(String s, Bundle bundle)
        throws RemoteException;

    public abstract void prepareFromSearch(String s, Bundle bundle)
        throws RemoteException;

    public abstract void prepareFromUri(Uri uri, Bundle bundle)
        throws RemoteException;

    public abstract void previous()
        throws RemoteException;

    public abstract void rate(RatingCompat ratingcompat)
        throws RemoteException;

    public abstract void rateWithExtras(RatingCompat ratingcompat, Bundle bundle)
        throws RemoteException;

    public abstract void registerCallbackListener(IMediaControllerCallback imediacontrollercallback)
        throws RemoteException;

    public abstract void removeQueueItem(MediaDescriptionCompat mediadescriptioncompat)
        throws RemoteException;

    public abstract void removeQueueItemAt(int i)
        throws RemoteException;

    public abstract void rewind()
        throws RemoteException;

    public abstract void seekTo(long l)
        throws RemoteException;

    public abstract void sendCommand(String s, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultreceiverwrapper)
        throws RemoteException;

    public abstract void sendCustomAction(String s, Bundle bundle)
        throws RemoteException;

    public abstract boolean sendMediaButton(KeyEvent keyevent)
        throws RemoteException;

    public abstract void setCaptioningEnabled(boolean flag)
        throws RemoteException;

    public abstract void setRepeatMode(int i)
        throws RemoteException;

    public abstract void setShuffleMode(int i)
        throws RemoteException;

    public abstract void setShuffleModeEnabledDeprecated(boolean flag)
        throws RemoteException;

    public abstract void setVolumeTo(int i, int j, String s)
        throws RemoteException;

    public abstract void skipToQueueItem(long l)
        throws RemoteException;

    public abstract void stop()
        throws RemoteException;

    public abstract void unregisterCallbackListener(IMediaControllerCallback imediacontrollercallback)
        throws RemoteException;
}
