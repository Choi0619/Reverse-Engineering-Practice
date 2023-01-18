// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.media.AudioAttributes;
import android.util.SparseIntArray;
import java.lang.annotation.Annotation;
import java.util.Arrays;

// Referenced classes of package android.support.v4.media:
//            AudioAttributesCompatApi21

public class AudioAttributesCompat
{
    public static interface AttributeContentType
        extends Annotation
    {
    }

    public static interface AttributeUsage
        extends Annotation
    {
    }

    private static abstract class AudioManagerHidden
    {

        public static final int STREAM_ACCESSIBILITY = 10;
        public static final int STREAM_BLUETOOTH_SCO = 6;
        public static final int STREAM_SYSTEM_ENFORCED = 7;
        public static final int STREAM_TTS = 9;

        private AudioManagerHidden()
        {
        }
    }

    public static class Builder
    {

        public AudioAttributesCompat build()
        {
            if(!AudioAttributesCompat.sForceLegacyBehavior && android.os.Build.VERSION.SDK_INT >= 21)
            {
                if(mAAObject != null)
                    return AudioAttributesCompat.wrap(mAAObject);
                android.media.AudioAttributes.Builder builder = (new android.media.AudioAttributes.Builder()).setContentType(mContentType).setFlags(mFlags).setUsage(mUsage);
                if(mLegacyStream != null)
                    builder.setLegacyStreamType(mLegacyStream.intValue());
                return AudioAttributesCompat.wrap(builder.build());
            } else
            {
                AudioAttributesCompat audioattributescompat = new AudioAttributesCompat();
                audioattributescompat.mContentType = mContentType;
                audioattributescompat.mFlags = mFlags;
                audioattributescompat.mUsage = mUsage;
                audioattributescompat.mLegacyStream = mLegacyStream;
                audioattributescompat.mAudioAttributesWrapper = null;
                return audioattributescompat;
            }
        }

        public Builder setContentType(int i)
        {
            switch(i)
            {
            default:
                mUsage = 0;
                return this;

            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
                mContentType = i;
                break;
            }
            return this;
        }

        public Builder setFlags(int i)
        {
            mFlags = mFlags | i & 0x3ff;
            return this;
        }

        public Builder setLegacyStreamType(int i)
        {
            if(i == 10)
            {
                throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
            } else
            {
                mLegacyStream = Integer.valueOf(i);
                mUsage = AudioAttributesCompat.usageForStreamType(i);
                return this;
            }
        }

        public Builder setUsage(int i)
        {
            switch(i)
            {
            default:
                mUsage = 0;
                return this;

            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
            case 15: // '\017'
                mUsage = i;
                return this;

            case 16: // '\020'
                break;
            }
            if(!AudioAttributesCompat.sForceLegacyBehavior && android.os.Build.VERSION.SDK_INT > 25)
            {
                mUsage = i;
                return this;
            } else
            {
                mUsage = 12;
                return this;
            }
        }

        private Object mAAObject;
        private int mContentType;
        private int mFlags;
        private Integer mLegacyStream;
        private int mUsage;

        public Builder()
        {
            mUsage = 0;
            mContentType = 0;
            mFlags = 0;
        }

        public Builder(AudioAttributesCompat audioattributescompat)
        {
            mUsage = 0;
            mContentType = 0;
            mFlags = 0;
            mUsage = audioattributescompat.mUsage;
            mContentType = audioattributescompat.mContentType;
            mFlags = audioattributescompat.mFlags;
            mLegacyStream = audioattributescompat.mLegacyStream;
            mAAObject = audioattributescompat.unwrap();
        }
    }


    private AudioAttributesCompat()
    {
        mUsage = 0;
        mContentType = 0;
        mFlags = 0;
    }


    public static void setForceLegacyBehavior(boolean flag)
    {
        sForceLegacyBehavior = flag;
    }

    static int toVolumeStreamType(boolean flag, int i, int j)
    {
        boolean flag1 = false;
        if((i & 1) != 1) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        return 1;
_L4:
        return 7;
_L2:
        if((i & 4) == 4)
            return !flag ? 6 : 0;
        switch(j)
        {
        case 15: // '\017'
        default:
            if(flag)
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown usage value ").append(j).append(" in audio attributes").toString());
            else
                return 3;

        case 0: // '\0'
            break; /* Loop/switch isn't completed */

        case 13: // '\r'
            break;

        case 1: // '\001'
        case 12: // '\f'
        case 14: // '\016'
        case 16: // '\020'
            return 3;

        case 2: // '\002'
            return 0;

        case 3: // '\003'
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 8;
            return i;

        case 4: // '\004'
            return 4;

        case 6: // '\006'
            return 2;

        case 5: // '\005'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
            return 5;

        case 11: // '\013'
            return 10;
        }
        if(true) goto _L3; else goto _L5
_L5:
        return !flag ? 3 : 0x80000000;
    }

    static int toVolumeStreamType(boolean flag, AudioAttributesCompat audioattributescompat)
    {
        return toVolumeStreamType(flag, audioattributescompat.getFlags(), audioattributescompat.getUsage());
    }

    private static int usageForStreamType(int i)
    {
        byte byte0 = 2;
        switch(i)
        {
        case 9: // '\t'
        default:
            byte0 = 0;
            // fall through

        case 0: // '\0'
        case 6: // '\006'
            return byte0;

        case 1: // '\001'
        case 7: // '\007'
            return 13;

        case 2: // '\002'
            return 6;

        case 3: // '\003'
            return 1;

        case 4: // '\004'
            return 4;

        case 5: // '\005'
            return 5;

        case 8: // '\b'
            return 3;

        case 10: // '\n'
            return 11;
        }
    }

    static String usageToString(int i)
    {
        switch(i)
        {
        case 15: // '\017'
        default:
            return new String((new StringBuilder()).append("unknown usage ").append(i).toString());

        case 0: // '\0'
            return new String("USAGE_UNKNOWN");

        case 1: // '\001'
            return new String("USAGE_MEDIA");

        case 2: // '\002'
            return new String("USAGE_VOICE_COMMUNICATION");

        case 3: // '\003'
            return new String("USAGE_VOICE_COMMUNICATION_SIGNALLING");

        case 4: // '\004'
            return new String("USAGE_ALARM");

        case 5: // '\005'
            return new String("USAGE_NOTIFICATION");

        case 6: // '\006'
            return new String("USAGE_NOTIFICATION_RINGTONE");

        case 7: // '\007'
            return new String("USAGE_NOTIFICATION_COMMUNICATION_REQUEST");

        case 8: // '\b'
            return new String("USAGE_NOTIFICATION_COMMUNICATION_INSTANT");

        case 9: // '\t'
            return new String("USAGE_NOTIFICATION_COMMUNICATION_DELAYED");

        case 10: // '\n'
            return new String("USAGE_NOTIFICATION_EVENT");

        case 11: // '\013'
            return new String("USAGE_ASSISTANCE_ACCESSIBILITY");

        case 12: // '\f'
            return new String("USAGE_ASSISTANCE_NAVIGATION_GUIDANCE");

        case 13: // '\r'
            return new String("USAGE_ASSISTANCE_SONIFICATION");

        case 14: // '\016'
            return new String("USAGE_GAME");

        case 16: // '\020'
            return new String("USAGE_ASSISTANT");
        }
    }

    public static AudioAttributesCompat wrap(Object obj)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21 && !sForceLegacyBehavior)
        {
            AudioAttributesCompat audioattributescompat = new AudioAttributesCompat();
            audioattributescompat.mAudioAttributesWrapper = AudioAttributesCompatApi21.Wrapper.wrap((AudioAttributes)obj);
            return audioattributescompat;
        } else
        {
            return null;
        }
    }

    public boolean equals(Object obj)
    {
        if(this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (AudioAttributesCompat)obj;
        if(android.os.Build.VERSION.SDK_INT >= 21 && !sForceLegacyBehavior && mAudioAttributesWrapper != null)
            return mAudioAttributesWrapper.unwrap().equals(((AudioAttributesCompat) (obj)).unwrap());
        if(mContentType != ((AudioAttributesCompat) (obj)).getContentType() || mFlags != ((AudioAttributesCompat) (obj)).getFlags() || mUsage != ((AudioAttributesCompat) (obj)).getUsage())
            break; /* Loop/switch isn't completed */
        if(mLegacyStream == null) goto _L4; else goto _L3
_L3:
        if(mLegacyStream.equals(((AudioAttributesCompat) (obj)).mLegacyStream)) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if(((AudioAttributesCompat) (obj)).mLegacyStream == null)
            return true;
        if(true) goto _L5; else goto _L6
_L6:
    }

    public int getContentType()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21 && !sForceLegacyBehavior && mAudioAttributesWrapper != null)
            return mAudioAttributesWrapper.unwrap().getContentType();
        else
            return mContentType;
    }

    public int getFlags()
    {
        int j;
        int k;
        if(android.os.Build.VERSION.SDK_INT >= 21 && !sForceLegacyBehavior && mAudioAttributesWrapper != null)
            return mAudioAttributesWrapper.unwrap().getFlags();
        j = mFlags;
        k = getLegacyStreamType();
        if(k != 6) goto _L2; else goto _L1
_L1:
        int i = j | 4;
_L4:
        return i & 0x111;
_L2:
        i = j;
        if(k == 7)
            i = j | 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getLegacyStreamType()
    {
        if(mLegacyStream != null)
            return mLegacyStream.intValue();
        if(android.os.Build.VERSION.SDK_INT >= 21 && !sForceLegacyBehavior)
            return AudioAttributesCompatApi21.toLegacyStreamType(mAudioAttributesWrapper);
        else
            return toVolumeStreamType(false, mFlags, mUsage);
    }

    public int getUsage()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21 && !sForceLegacyBehavior && mAudioAttributesWrapper != null)
            return mAudioAttributesWrapper.unwrap().getUsage();
        else
            return mUsage;
    }

    public int getVolumeControlStream()
    {
        if(this == null)
            throw new IllegalArgumentException("Invalid null audio attributes");
        if(android.os.Build.VERSION.SDK_INT >= 26 && !sForceLegacyBehavior && unwrap() != null)
            return ((AudioAttributes)unwrap()).getVolumeControlStream();
        else
            return toVolumeStreamType(true, this);
    }

    public int hashCode()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21 && !sForceLegacyBehavior && mAudioAttributesWrapper != null)
            return mAudioAttributesWrapper.unwrap().hashCode();
        else
            return Arrays.hashCode(new Object[] {
                Integer.valueOf(mContentType), Integer.valueOf(mFlags), Integer.valueOf(mUsage), mLegacyStream
            });
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("AudioAttributesCompat:");
        if(unwrap() != null)
        {
            stringbuilder.append(" audioattributes=").append(unwrap());
        } else
        {
            if(mLegacyStream != null)
            {
                stringbuilder.append(" stream=").append(mLegacyStream);
                stringbuilder.append(" derived");
            }
            stringbuilder.append(" usage=").append(usageToString()).append(" content=").append(mContentType).append(" flags=0x").append(Integer.toHexString(mFlags).toUpperCase());
        }
        return stringbuilder.toString();
    }

    public Object unwrap()
    {
        if(mAudioAttributesWrapper != null)
            return mAudioAttributesWrapper.unwrap();
        else
            return null;
    }

    String usageToString()
    {
        return usageToString(mUsage);
    }

    public static final int CONTENT_TYPE_MOVIE = 3;
    public static final int CONTENT_TYPE_MUSIC = 2;
    public static final int CONTENT_TYPE_SONIFICATION = 4;
    public static final int CONTENT_TYPE_SPEECH = 1;
    public static final int CONTENT_TYPE_UNKNOWN = 0;
    private static final int FLAG_ALL = 1023;
    private static final int FLAG_ALL_PUBLIC = 273;
    public static final int FLAG_AUDIBILITY_ENFORCED = 1;
    private static final int FLAG_BEACON = 8;
    private static final int FLAG_BYPASS_INTERRUPTION_POLICY = 64;
    private static final int FLAG_BYPASS_MUTE = 128;
    private static final int FLAG_DEEP_BUFFER = 512;
    public static final int FLAG_HW_AV_SYNC = 16;
    private static final int FLAG_HW_HOTWORD = 32;
    private static final int FLAG_LOW_LATENCY = 256;
    private static final int FLAG_SCO = 4;
    private static final int FLAG_SECURE = 2;
    private static final int SDK_USAGES[] = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 16
    };
    private static final int SUPPRESSIBLE_CALL = 2;
    private static final int SUPPRESSIBLE_NOTIFICATION = 1;
    private static final SparseIntArray SUPPRESSIBLE_USAGES;
    private static final String TAG = "AudioAttributesCompat";
    public static final int USAGE_ALARM = 4;
    public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
    public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
    public static final int USAGE_ASSISTANT = 16;
    public static final int USAGE_GAME = 14;
    public static final int USAGE_MEDIA = 1;
    public static final int USAGE_NOTIFICATION = 5;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
    public static final int USAGE_NOTIFICATION_EVENT = 10;
    public static final int USAGE_NOTIFICATION_RINGTONE = 6;
    public static final int USAGE_UNKNOWN = 0;
    private static final int USAGE_VIRTUAL_SOURCE = 15;
    public static final int USAGE_VOICE_COMMUNICATION = 2;
    public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
    private static boolean sForceLegacyBehavior;
    private AudioAttributesCompatApi21.Wrapper mAudioAttributesWrapper;
    int mContentType;
    int mFlags;
    Integer mLegacyStream;
    int mUsage;

    static 
    {
        SUPPRESSIBLE_USAGES = new SparseIntArray();
        SUPPRESSIBLE_USAGES.put(5, 1);
        SUPPRESSIBLE_USAGES.put(6, 2);
        SUPPRESSIBLE_USAGES.put(7, 2);
        SUPPRESSIBLE_USAGES.put(8, 1);
        SUPPRESSIBLE_USAGES.put(9, 1);
        SUPPRESSIBLE_USAGES.put(10, 1);
    }



/*
    static AudioAttributesCompatApi21.Wrapper access$202(AudioAttributesCompat audioattributescompat, AudioAttributesCompatApi21.Wrapper wrapper)
    {
        audioattributescompat.mAudioAttributesWrapper = wrapper;
        return wrapper;
    }

*/

}
