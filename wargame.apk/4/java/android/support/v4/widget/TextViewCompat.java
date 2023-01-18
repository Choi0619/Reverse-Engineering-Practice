// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.TextView;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

// Referenced classes of package android.support.v4.widget:
//            AutoSizeableTextView

public final class TextViewCompat
{
    public static interface AutoSizeTextType
        extends Annotation
    {
    }

    static class TextViewCompatApi16Impl extends TextViewCompatBaseImpl
    {

        public int getMaxLines(TextView textview)
        {
            return textview.getMaxLines();
        }

        public int getMinLines(TextView textview)
        {
            return textview.getMinLines();
        }

        TextViewCompatApi16Impl()
        {
        }
    }

    static class TextViewCompatApi17Impl extends TextViewCompatApi16Impl
    {

        public Drawable[] getCompoundDrawablesRelative(TextView textview)
        {
            boolean flag = true;
            if(textview.getLayoutDirection() != 1)
                flag = false;
            textview = textview.getCompoundDrawables();
            if(flag)
            {
                Object obj = textview[2];
                Object obj1 = textview[0];
                textview[0] = obj;
                textview[2] = obj1;
            }
            return textview;
        }

        public void setCompoundDrawablesRelative(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            boolean flag = true;
            Drawable drawable4;
            if(textview.getLayoutDirection() != 1)
                flag = false;
            if(flag)
                drawable4 = drawable2;
            else
                drawable4 = drawable;
            if(!flag)
                drawable = drawable2;
            textview.setCompoundDrawables(drawable4, drawable1, drawable, drawable3);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, int i, int j, int k, int l)
        {
            boolean flag = true;
            int i1;
            if(textview.getLayoutDirection() != 1)
                flag = false;
            if(flag)
                i1 = k;
            else
                i1 = i;
            if(!flag)
                i = k;
            textview.setCompoundDrawablesWithIntrinsicBounds(i1, j, i, l);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            boolean flag = true;
            Drawable drawable4;
            if(textview.getLayoutDirection() != 1)
                flag = false;
            if(flag)
                drawable4 = drawable2;
            else
                drawable4 = drawable;
            if(!flag)
                drawable = drawable2;
            textview.setCompoundDrawablesWithIntrinsicBounds(drawable4, drawable1, drawable, drawable3);
        }

        TextViewCompatApi17Impl()
        {
        }
    }

    static class TextViewCompatApi18Impl extends TextViewCompatApi17Impl
    {

        public Drawable[] getCompoundDrawablesRelative(TextView textview)
        {
            return textview.getCompoundDrawablesRelative();
        }

        public void setCompoundDrawablesRelative(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            textview.setCompoundDrawablesRelative(drawable, drawable1, drawable2, drawable3);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, int i, int j, int k, int l)
        {
            textview.setCompoundDrawablesRelativeWithIntrinsicBounds(i, j, k, l);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            textview.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable1, drawable2, drawable3);
        }

        TextViewCompatApi18Impl()
        {
        }
    }

    static class TextViewCompatApi23Impl extends TextViewCompatApi18Impl
    {

        public void setTextAppearance(TextView textview, int i)
        {
            textview.setTextAppearance(i);
        }

        TextViewCompatApi23Impl()
        {
        }
    }

    static class TextViewCompatApi26Impl extends TextViewCompatApi23Impl
    {

        public int getAutoSizeMaxTextSize(TextView textview)
        {
            return textview.getAutoSizeMaxTextSize();
        }

        public int getAutoSizeMinTextSize(TextView textview)
        {
            return textview.getAutoSizeMinTextSize();
        }

        public int getAutoSizeStepGranularity(TextView textview)
        {
            return textview.getAutoSizeStepGranularity();
        }

        public int[] getAutoSizeTextAvailableSizes(TextView textview)
        {
            return textview.getAutoSizeTextAvailableSizes();
        }

        public int getAutoSizeTextType(TextView textview)
        {
            return textview.getAutoSizeTextType();
        }

        public void setAutoSizeTextTypeUniformWithConfiguration(TextView textview, int i, int j, int k, int l)
            throws IllegalArgumentException
        {
            textview.setAutoSizeTextTypeUniformWithConfiguration(i, j, k, l);
        }

        public void setAutoSizeTextTypeUniformWithPresetSizes(TextView textview, int ai[], int i)
            throws IllegalArgumentException
        {
            textview.setAutoSizeTextTypeUniformWithPresetSizes(ai, i);
        }

        public void setAutoSizeTextTypeWithDefaults(TextView textview, int i)
        {
            textview.setAutoSizeTextTypeWithDefaults(i);
        }

        TextViewCompatApi26Impl()
        {
        }
    }

    static class TextViewCompatBaseImpl
    {

        private static Field retrieveField(String s)
        {
            Field field = null;
            Field field1;
            try
            {
                field1 = android/widget/TextView.getDeclaredField(s);
            }
            catch(NoSuchFieldException nosuchfieldexception)
            {
                Log.e("TextViewCompatBase", (new StringBuilder()).append("Could not retrieve ").append(s).append(" field.").toString());
                return field;
            }
            field = field1;
            field1.setAccessible(true);
            return field1;
        }

        private static int retrieveIntFromField(Field field, TextView textview)
        {
            int i;
            try
            {
                i = field.getInt(textview);
            }
            // Misplaced declaration of an exception variable
            catch(TextView textview)
            {
                Log.d("TextViewCompatBase", (new StringBuilder()).append("Could not retrieve value of ").append(field.getName()).append(" field.").toString());
                return -1;
            }
            return i;
        }

        public int getAutoSizeMaxTextSize(TextView textview)
        {
            if(textview instanceof AutoSizeableTextView)
                return ((AutoSizeableTextView)textview).getAutoSizeMaxTextSize();
            else
                return -1;
        }

        public int getAutoSizeMinTextSize(TextView textview)
        {
            if(textview instanceof AutoSizeableTextView)
                return ((AutoSizeableTextView)textview).getAutoSizeMinTextSize();
            else
                return -1;
        }

        public int getAutoSizeStepGranularity(TextView textview)
        {
            if(textview instanceof AutoSizeableTextView)
                return ((AutoSizeableTextView)textview).getAutoSizeStepGranularity();
            else
                return -1;
        }

        public int[] getAutoSizeTextAvailableSizes(TextView textview)
        {
            if(textview instanceof AutoSizeableTextView)
                return ((AutoSizeableTextView)textview).getAutoSizeTextAvailableSizes();
            else
                return new int[0];
        }

        public int getAutoSizeTextType(TextView textview)
        {
            if(textview instanceof AutoSizeableTextView)
                return ((AutoSizeableTextView)textview).getAutoSizeTextType();
            else
                return 0;
        }

        public Drawable[] getCompoundDrawablesRelative(TextView textview)
        {
            return textview.getCompoundDrawables();
        }

        public int getMaxLines(TextView textview)
        {
            if(!sMaxModeFieldFetched)
            {
                sMaxModeField = retrieveField("mMaxMode");
                sMaxModeFieldFetched = true;
            }
            if(sMaxModeField != null && retrieveIntFromField(sMaxModeField, textview) == 1)
            {
                if(!sMaximumFieldFetched)
                {
                    sMaximumField = retrieveField("mMaximum");
                    sMaximumFieldFetched = true;
                }
                if(sMaximumField != null)
                    return retrieveIntFromField(sMaximumField, textview);
            }
            return -1;
        }

        public int getMinLines(TextView textview)
        {
            if(!sMinModeFieldFetched)
            {
                sMinModeField = retrieveField("mMinMode");
                sMinModeFieldFetched = true;
            }
            if(sMinModeField != null && retrieveIntFromField(sMinModeField, textview) == 1)
            {
                if(!sMinimumFieldFetched)
                {
                    sMinimumField = retrieveField("mMinimum");
                    sMinimumFieldFetched = true;
                }
                if(sMinimumField != null)
                    return retrieveIntFromField(sMinimumField, textview);
            }
            return -1;
        }

        public void setAutoSizeTextTypeUniformWithConfiguration(TextView textview, int i, int j, int k, int l)
            throws IllegalArgumentException
        {
            if(textview instanceof AutoSizeableTextView)
                ((AutoSizeableTextView)textview).setAutoSizeTextTypeUniformWithConfiguration(i, j, k, l);
        }

        public void setAutoSizeTextTypeUniformWithPresetSizes(TextView textview, int ai[], int i)
            throws IllegalArgumentException
        {
            if(textview instanceof AutoSizeableTextView)
                ((AutoSizeableTextView)textview).setAutoSizeTextTypeUniformWithPresetSizes(ai, i);
        }

        public void setAutoSizeTextTypeWithDefaults(TextView textview, int i)
        {
            if(textview instanceof AutoSizeableTextView)
                ((AutoSizeableTextView)textview).setAutoSizeTextTypeWithDefaults(i);
        }

        public void setCompoundDrawablesRelative(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            textview.setCompoundDrawables(drawable, drawable1, drawable2, drawable3);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, int i, int j, int k, int l)
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(i, j, k, l);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable1, drawable2, drawable3);
        }

        public void setTextAppearance(TextView textview, int i)
        {
            textview.setTextAppearance(textview.getContext(), i);
        }

        private static final int LINES = 1;
        private static final String LOG_TAG = "TextViewCompatBase";
        private static Field sMaxModeField;
        private static boolean sMaxModeFieldFetched;
        private static Field sMaximumField;
        private static boolean sMaximumFieldFetched;
        private static Field sMinModeField;
        private static boolean sMinModeFieldFetched;
        private static Field sMinimumField;
        private static boolean sMinimumFieldFetched;

        TextViewCompatBaseImpl()
        {
        }
    }


    private TextViewCompat()
    {
    }

    public static int getAutoSizeMaxTextSize(TextView textview)
    {
        return IMPL.getAutoSizeMaxTextSize(textview);
    }

    public static int getAutoSizeMinTextSize(TextView textview)
    {
        return IMPL.getAutoSizeMinTextSize(textview);
    }

    public static int getAutoSizeStepGranularity(TextView textview)
    {
        return IMPL.getAutoSizeStepGranularity(textview);
    }

    public static int[] getAutoSizeTextAvailableSizes(TextView textview)
    {
        return IMPL.getAutoSizeTextAvailableSizes(textview);
    }

    public static int getAutoSizeTextType(TextView textview)
    {
        return IMPL.getAutoSizeTextType(textview);
    }

    public static Drawable[] getCompoundDrawablesRelative(TextView textview)
    {
        return IMPL.getCompoundDrawablesRelative(textview);
    }

    public static int getMaxLines(TextView textview)
    {
        return IMPL.getMaxLines(textview);
    }

    public static int getMinLines(TextView textview)
    {
        return IMPL.getMinLines(textview);
    }

    public static void setAutoSizeTextTypeUniformWithConfiguration(TextView textview, int i, int j, int k, int l)
        throws IllegalArgumentException
    {
        IMPL.setAutoSizeTextTypeUniformWithConfiguration(textview, i, j, k, l);
    }

    public static void setAutoSizeTextTypeUniformWithPresetSizes(TextView textview, int ai[], int i)
        throws IllegalArgumentException
    {
        IMPL.setAutoSizeTextTypeUniformWithPresetSizes(textview, ai, i);
    }

    public static void setAutoSizeTextTypeWithDefaults(TextView textview, int i)
    {
        IMPL.setAutoSizeTextTypeWithDefaults(textview, i);
    }

    public static void setCompoundDrawablesRelative(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        IMPL.setCompoundDrawablesRelative(textview, drawable, drawable1, drawable2, drawable3);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, int i, int j, int k, int l)
    {
        IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textview, i, j, k, l);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textview, drawable, drawable1, drawable2, drawable3);
    }

    public static void setTextAppearance(TextView textview, int i)
    {
        IMPL.setTextAppearance(textview, i);
    }

    public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
    public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
    static final TextViewCompatBaseImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            IMPL = new TextViewCompatApi26Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 23)
            IMPL = new TextViewCompatApi23Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 18)
            IMPL = new TextViewCompatApi18Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 17)
            IMPL = new TextViewCompatApi17Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new TextViewCompatApi16Impl();
        else
            IMPL = new TextViewCompatBaseImpl();
    }
}
