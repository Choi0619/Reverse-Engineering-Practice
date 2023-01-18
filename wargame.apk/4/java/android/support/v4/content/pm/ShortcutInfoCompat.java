// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.pm;

import android.content.*;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.IconCompat;
import android.text.TextUtils;
import java.util.Arrays;

public class ShortcutInfoCompat
{
    public static class Builder
    {

        public ShortcutInfoCompat build()
        {
            if(TextUtils.isEmpty(mInfo.mLabel))
                throw new IllegalArgumentException("Shortcut much have a non-empty label");
            if(mInfo.mIntents == null || mInfo.mIntents.length == 0)
                throw new IllegalArgumentException("Shortcut much have an intent");
            else
                return mInfo;
        }

        public Builder setActivity(ComponentName componentname)
        {
            mInfo.mActivity = componentname;
            return this;
        }

        public Builder setDisabledMessage(CharSequence charsequence)
        {
            mInfo.mDisabledMessage = charsequence;
            return this;
        }

        public Builder setIcon(int i)
        {
            return setIcon(IconCompat.createWithResource(mInfo.mContext, i));
        }

        public Builder setIcon(Bitmap bitmap)
        {
            return setIcon(IconCompat.createWithBitmap(bitmap));
        }

        public Builder setIcon(IconCompat iconcompat)
        {
            mInfo.mIcon = iconcompat;
            return this;
        }

        public Builder setIntent(Intent intent)
        {
            return setIntents(new Intent[] {
                intent
            });
        }

        public Builder setIntents(Intent aintent[])
        {
            mInfo.mIntents = aintent;
            return this;
        }

        public Builder setLongLabel(CharSequence charsequence)
        {
            mInfo.mLongLabel = charsequence;
            return this;
        }

        public Builder setShortLabel(CharSequence charsequence)
        {
            mInfo.mLabel = charsequence;
            return this;
        }

        private final ShortcutInfoCompat mInfo = new ShortcutInfoCompat();

        public Builder(Context context, String s)
        {
            mInfo.mContext = context;
            mInfo.mId = s;
        }
    }


    private ShortcutInfoCompat()
    {
    }


    Intent addToIntent(Intent intent)
    {
        intent.putExtra("android.intent.extra.shortcut.INTENT", mIntents[mIntents.length - 1]).putExtra("android.intent.extra.shortcut.NAME", mLabel.toString());
        if(mIcon != null)
            mIcon.addToShortcutIntent(intent);
        return intent;
    }

    public ComponentName getActivity()
    {
        return mActivity;
    }

    public CharSequence getDisabledMessage()
    {
        return mDisabledMessage;
    }

    public String getId()
    {
        return mId;
    }

    public Intent getIntent()
    {
        return mIntents[mIntents.length - 1];
    }

    public Intent[] getIntents()
    {
        return (Intent[])Arrays.copyOf(mIntents, mIntents.length);
    }

    public CharSequence getLongLabel()
    {
        return mLongLabel;
    }

    public CharSequence getShortLabel()
    {
        return mLabel;
    }

    ShortcutInfo toShortcutInfo()
    {
        android.content.pm.ShortcutInfo.Builder builder = (new android.content.pm.ShortcutInfo.Builder(mContext, mId)).setShortLabel(mLabel).setIntents(mIntents);
        if(mIcon != null)
            builder.setIcon(mIcon.toIcon());
        if(!TextUtils.isEmpty(mLongLabel))
            builder.setLongLabel(mLongLabel);
        if(!TextUtils.isEmpty(mDisabledMessage))
            builder.setDisabledMessage(mDisabledMessage);
        if(mActivity != null)
            builder.setActivity(mActivity);
        return builder.build();
    }

    private ComponentName mActivity;
    private Context mContext;
    private CharSequence mDisabledMessage;
    private IconCompat mIcon;
    private String mId;
    private Intent mIntents[];
    private CharSequence mLabel;
    private CharSequence mLongLabel;



/*
    static Context access$102(ShortcutInfoCompat shortcutinfocompat, Context context)
    {
        shortcutinfocompat.mContext = context;
        return context;
    }

*/


/*
    static String access$202(ShortcutInfoCompat shortcutinfocompat, String s)
    {
        shortcutinfocompat.mId = s;
        return s;
    }

*/



/*
    static CharSequence access$302(ShortcutInfoCompat shortcutinfocompat, CharSequence charsequence)
    {
        shortcutinfocompat.mLabel = charsequence;
        return charsequence;
    }

*/


/*
    static CharSequence access$402(ShortcutInfoCompat shortcutinfocompat, CharSequence charsequence)
    {
        shortcutinfocompat.mLongLabel = charsequence;
        return charsequence;
    }

*/


/*
    static CharSequence access$502(ShortcutInfoCompat shortcutinfocompat, CharSequence charsequence)
    {
        shortcutinfocompat.mDisabledMessage = charsequence;
        return charsequence;
    }

*/



/*
    static Intent[] access$602(ShortcutInfoCompat shortcutinfocompat, Intent aintent[])
    {
        shortcutinfocompat.mIntents = aintent;
        return aintent;
    }

*/


/*
    static IconCompat access$702(ShortcutInfoCompat shortcutinfocompat, IconCompat iconcompat)
    {
        shortcutinfocompat.mIcon = iconcompat;
        return iconcompat;
    }

*/


/*
    static ComponentName access$802(ShortcutInfoCompat shortcutinfocompat, ComponentName componentname)
    {
        shortcutinfocompat.mActivity = componentname;
        return componentname;
    }

*/
}
