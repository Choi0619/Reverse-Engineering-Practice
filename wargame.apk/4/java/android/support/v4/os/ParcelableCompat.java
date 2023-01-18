// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.Parcel;

// Referenced classes of package android.support.v4.os:
//            ParcelableCompatCreatorCallbacks

public final class ParcelableCompat
{
    static class ParcelableCompatCreatorHoneycombMR2
        implements android.os.Parcelable.ClassLoaderCreator
    {

        public Object createFromParcel(Parcel parcel)
        {
            return mCallbacks.createFromParcel(parcel, null);
        }

        public Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return mCallbacks.createFromParcel(parcel, classloader);
        }

        public Object[] newArray(int i)
        {
            return mCallbacks.newArray(i);
        }

        private final ParcelableCompatCreatorCallbacks mCallbacks;

        ParcelableCompatCreatorHoneycombMR2(ParcelableCompatCreatorCallbacks parcelablecompatcreatorcallbacks)
        {
            mCallbacks = parcelablecompatcreatorcallbacks;
        }
    }


    private ParcelableCompat()
    {
    }

    public static android.os.Parcelable.Creator newCreator(ParcelableCompatCreatorCallbacks parcelablecompatcreatorcallbacks)
    {
        return new ParcelableCompatCreatorHoneycombMR2(parcelablecompatcreatorcallbacks);
    }
}
