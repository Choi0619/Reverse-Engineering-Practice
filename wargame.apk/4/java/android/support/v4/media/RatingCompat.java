// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.lang.annotation.Annotation;

// Referenced classes of package android.support.v4.media:
//            RatingCompatKitkat

public final class RatingCompat
    implements Parcelable
{
    public static interface StarStyle
        extends Annotation
    {
    }

    public static interface Style
        extends Annotation
    {
    }


    RatingCompat(int i, float f)
    {
        mRatingStyle = i;
        mRatingValue = f;
    }

    public static RatingCompat fromRating(Object obj)
    {
        if(obj == null || android.os.Build.VERSION.SDK_INT < 19) goto _L2; else goto _L1
_L1:
        int i = RatingCompatKitkat.getRatingStyle(obj);
        if(!RatingCompatKitkat.isRated(obj)) goto _L4; else goto _L3
_L3:
        i;
        JVM INSTR tableswitch 1 6: default 64
    //                   1 66
    //                   2 81
    //                   3 92
    //                   4 92
    //                   5 92
    //                   6 104;
           goto _L2 _L5 _L6 _L7 _L7 _L7 _L8
_L2:
        return null;
_L5:
        RatingCompat ratingcompat = newHeartRating(RatingCompatKitkat.hasHeart(obj));
_L10:
        ratingcompat.mRatingObj = obj;
        return ratingcompat;
_L6:
        ratingcompat = newThumbRating(RatingCompatKitkat.isThumbUp(obj));
        continue; /* Loop/switch isn't completed */
_L7:
        ratingcompat = newStarRating(i, RatingCompatKitkat.getStarRating(obj));
        continue; /* Loop/switch isn't completed */
_L8:
        ratingcompat = newPercentageRating(RatingCompatKitkat.getPercentRating(obj));
        continue; /* Loop/switch isn't completed */
_L4:
        ratingcompat = newUnratedRating(i);
        if(true) goto _L10; else goto _L9
_L9:
    }

    public static RatingCompat newHeartRating(boolean flag)
    {
        float f;
        if(flag)
            f = 1.0F;
        else
            f = 0.0F;
        return new RatingCompat(1, f);
    }

    public static RatingCompat newPercentageRating(float f)
    {
        if(f < 0.0F || f > 100F)
        {
            Log.e("Rating", "Invalid percentage-based rating value");
            return null;
        } else
        {
            return new RatingCompat(6, f);
        }
    }

    public static RatingCompat newStarRating(int i, float f)
    {
        i;
        JVM INSTR tableswitch 3 5: default 28
    //                   3 60
    //                   4 85
    //                   5 91;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_91;
_L1:
        Log.e("Rating", (new StringBuilder()).append("Invalid rating style (").append(i).append(") for a star rating").toString());
        return null;
_L2:
        float f1 = 3F;
_L5:
        if(f < 0.0F || f > f1)
        {
            Log.e("Rating", "Trying to set out of range star-based rating");
            return null;
        } else
        {
            return new RatingCompat(i, f);
        }
_L3:
        f1 = 4F;
          goto _L5
        f1 = 5F;
          goto _L5
    }

    public static RatingCompat newThumbRating(boolean flag)
    {
        float f;
        if(flag)
            f = 1.0F;
        else
            f = 0.0F;
        return new RatingCompat(2, f);
    }

    public static RatingCompat newUnratedRating(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
            return new RatingCompat(i, -1F);
        }
    }

    public int describeContents()
    {
        return mRatingStyle;
    }

    public float getPercentRating()
    {
        if(mRatingStyle != 6 || !isRated())
            return -1F;
        else
            return mRatingValue;
    }

    public Object getRating()
    {
        if(mRatingObj != null || android.os.Build.VERSION.SDK_INT < 19) goto _L2; else goto _L1
_L1:
        if(!isRated()) goto _L4; else goto _L3
_L3:
        mRatingStyle;
        JVM INSTR tableswitch 1 6: default 64
    //                   1 66
    //                   2 82
    //                   3 96
    //                   4 96
    //                   5 96
    //                   6 114;
           goto _L5 _L6 _L7 _L8 _L8 _L8 _L9
_L5:
        return null;
_L6:
        mRatingObj = RatingCompatKitkat.newHeartRating(hasHeart());
_L2:
        return mRatingObj;
_L7:
        mRatingObj = RatingCompatKitkat.newThumbRating(isThumbUp());
        continue; /* Loop/switch isn't completed */
_L8:
        mRatingObj = RatingCompatKitkat.newStarRating(mRatingStyle, getStarRating());
        continue; /* Loop/switch isn't completed */
_L9:
        mRatingObj = RatingCompatKitkat.newPercentageRating(getPercentRating());
        continue; /* Loop/switch isn't completed */
_L4:
        mRatingObj = RatingCompatKitkat.newUnratedRating(mRatingStyle);
        if(true) goto _L2; else goto _L10
_L10:
    }

    public int getRatingStyle()
    {
        return mRatingStyle;
    }

    public float getStarRating()
    {
        mRatingStyle;
        JVM INSTR tableswitch 3 5: default 32
    //                   3 35
    //                   4 35
    //                   5 35;
           goto _L1 _L2 _L2 _L2
_L1:
        return -1F;
_L2:
        if(isRated())
            return mRatingValue;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean hasHeart()
    {
        boolean flag = true;
        if(mRatingStyle != 1)
            return false;
        if(mRatingValue != 1.0F)
            flag = false;
        return flag;
    }

    public boolean isRated()
    {
        return mRatingValue >= 0.0F;
    }

    public boolean isThumbUp()
    {
        while(mRatingStyle != 2 || mRatingValue != 1.0F) 
            return false;
        return true;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("Rating:style=").append(mRatingStyle).append(" rating=");
        String s;
        if(mRatingValue < 0.0F)
            s = "unrated";
        else
            s = String.valueOf(mRatingValue);
        return stringbuilder.append(s).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRatingStyle);
        parcel.writeFloat(mRatingValue);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RatingCompat createFromParcel(Parcel parcel)
        {
            return new RatingCompat(parcel.readInt(), parcel.readFloat());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RatingCompat[] newArray(int i)
        {
            return new RatingCompat[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int RATING_3_STARS = 3;
    public static final int RATING_4_STARS = 4;
    public static final int RATING_5_STARS = 5;
    public static final int RATING_HEART = 1;
    public static final int RATING_NONE = 0;
    private static final float RATING_NOT_RATED = -1F;
    public static final int RATING_PERCENTAGE = 6;
    public static final int RATING_THUMB_UP_DOWN = 2;
    private static final String TAG = "Rating";
    private Object mRatingObj;
    private final int mRatingStyle;
    private final float mRatingValue;

}
