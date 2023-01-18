// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.media.AudioAttributes;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class AudioAttributesCompatApi21
{
    static final class Wrapper
    {

        public static Wrapper wrap(AudioAttributes audioattributes)
        {
            if(audioattributes == null)
                throw new IllegalArgumentException("AudioAttributesApi21.Wrapper cannot wrap null");
            else
                return new Wrapper(audioattributes);
        }

        public AudioAttributes unwrap()
        {
            return mWrapped;
        }

        private AudioAttributes mWrapped;

        private Wrapper(AudioAttributes audioattributes)
        {
            mWrapped = audioattributes;
        }
    }


    AudioAttributesCompatApi21()
    {
    }

    public static int toLegacyStreamType(Wrapper wrapper)
    {
        wrapper = wrapper.unwrap();
        int i;
        if(sAudioAttributesToLegacyStreamType == null)
            sAudioAttributesToLegacyStreamType = android/media/AudioAttributes.getMethod("toLegacyStreamType", new Class[] {
                android/media/AudioAttributes
            });
        i = ((Integer)sAudioAttributesToLegacyStreamType.invoke(null, new Object[] {
            wrapper
        })).intValue();
        return i;
        wrapper;
_L2:
        Log.w("AudioAttributesCompat", "getLegacyStreamType() failed on API21+", wrapper);
        return -1;
        wrapper;
        continue; /* Loop/switch isn't completed */
        wrapper;
        continue; /* Loop/switch isn't completed */
        wrapper;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String TAG = "AudioAttributesCompat";
    private static Method sAudioAttributesToLegacyStreamType;
}
