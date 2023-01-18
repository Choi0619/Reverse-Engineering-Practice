// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.text.*;
import android.util.*;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package android.support.v7.widget:
//            AppCompatEditText

class AppCompatTextViewAutoSizeHelper
{

    AppCompatTextViewAutoSizeHelper(TextView textview)
    {
        mAutoSizeTextType = 0;
        mNeedsAutoSizeText = false;
        mAutoSizeStepGranularityInPx = -1F;
        mAutoSizeMinTextSizeInPx = -1F;
        mAutoSizeMaxTextSizeInPx = -1F;
        mAutoSizeTextSizesInPx = new int[0];
        mHasPresetAutoSizeValues = false;
        mTextView = textview;
        mContext = mTextView.getContext();
    }

    private int[] cleanupAutoSizePresetSizes(int ai[])
    {
        int k = ai.length;
        if(k != 0)
        {
            Arrays.sort(ai);
            ArrayList arraylist = new ArrayList();
            for(int i = 0; i < k; i++)
            {
                int i1 = ai[i];
                if(i1 > 0 && Collections.binarySearch(arraylist, Integer.valueOf(i1)) < 0)
                    arraylist.add(Integer.valueOf(i1));
            }

            if(k != arraylist.size())
            {
                int l = arraylist.size();
                ai = new int[l];
                for(int j = 0; j < l; j++)
                    ai[j] = ((Integer)arraylist.get(j)).intValue();

                return ai;
            }
        }
        return ai;
    }

    private void clearAutoSizeConfiguration()
    {
        mAutoSizeTextType = 0;
        mAutoSizeMinTextSizeInPx = -1F;
        mAutoSizeMaxTextSizeInPx = -1F;
        mAutoSizeStepGranularityInPx = -1F;
        mAutoSizeTextSizesInPx = new int[0];
        mNeedsAutoSizeText = false;
    }

    private StaticLayout createStaticLayoutForMeasuring(CharSequence charsequence, android.text.Layout.Alignment alignment, int i, int j)
    {
        TextDirectionHeuristic textdirectionheuristic = (TextDirectionHeuristic)invokeAndReturnWithDefault(mTextView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
        charsequence = android.text.StaticLayout.Builder.obtain(charsequence, 0, charsequence.length(), mTempTextPaint, i).setAlignment(alignment).setLineSpacing(mTextView.getLineSpacingExtra(), mTextView.getLineSpacingMultiplier()).setIncludePad(mTextView.getIncludeFontPadding()).setBreakStrategy(mTextView.getBreakStrategy()).setHyphenationFrequency(mTextView.getHyphenationFrequency());
        i = j;
        if(j == -1)
            i = 0x7fffffff;
        return charsequence.setMaxLines(i).setTextDirection(textdirectionheuristic).build();
    }

    private StaticLayout createStaticLayoutForMeasuringPre23(CharSequence charsequence, android.text.Layout.Alignment alignment, int i)
    {
        float f;
        float f1;
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            f = mTextView.getLineSpacingMultiplier();
            f1 = mTextView.getLineSpacingExtra();
            flag = mTextView.getIncludeFontPadding();
        } else
        {
            f = ((Float)invokeAndReturnWithDefault(mTextView, "getLineSpacingMultiplier", Float.valueOf(1.0F))).floatValue();
            f1 = ((Float)invokeAndReturnWithDefault(mTextView, "getLineSpacingExtra", Float.valueOf(0.0F))).floatValue();
            flag = ((Boolean)invokeAndReturnWithDefault(mTextView, "getIncludeFontPadding", Boolean.valueOf(true))).booleanValue();
        }
        return new StaticLayout(charsequence, mTempTextPaint, i, alignment, f, f1, flag);
    }

    private int findLargestTextSizeWhichFits(RectF rectf)
    {
        int k = mAutoSizeTextSizesInPx.length;
        if(k == 0)
            throw new IllegalStateException("No available text sizes to choose from.");
        int j = 0;
        int i = 0 + 1;
        for(k--; i <= k;)
        {
            j = (i + k) / 2;
            if(suggestedSizeFitsInSpace(mAutoSizeTextSizesInPx[j], rectf))
            {
                int l = j + 1;
                j = i;
                i = l;
            } else
            {
                k = j - 1;
                j = k;
            }
        }

        return mAutoSizeTextSizesInPx[j];
    }

    private Method getTextViewMethod(String s)
    {
        Method method;
        Method method1;
        try
        {
            method1 = (Method)sTextViewMethodByNameCache.get(s);
        }
        catch(Exception exception)
        {
            Log.w("ACTVAutoSizeHelper", (new StringBuilder()).append("Failed to retrieve TextView#").append(s).append("() method").toString(), exception);
            return null;
        }
        method = method1;
        if(method1 != null)
            break MISSING_BLOCK_LABEL_50;
        method1 = android/widget/TextView.getDeclaredMethod(s, new Class[0]);
        method = method1;
        if(method1 == null)
            break MISSING_BLOCK_LABEL_50;
        method1.setAccessible(true);
        sTextViewMethodByNameCache.put(s, method1);
        method = method1;
        return method;
    }

    private Object invokeAndReturnWithDefault(Object obj, String s, Object obj1)
    {
        boolean flag;
        Object obj2;
        obj2 = null;
        flag = false;
        obj = getTextViewMethod(s).invoke(obj, new Object[0]);
        s = ((String) (obj));
        obj = s;
        if(s == null)
        {
            obj = s;
            if(false)
                obj = obj1;
        }
_L2:
        return obj;
        obj;
        flag = true;
        Log.w("ACTVAutoSizeHelper", (new StringBuilder()).append("Failed to invoke TextView#").append(s).append("() method").toString(), ((Throwable) (obj)));
        obj = obj2;
        if(false) goto _L2; else goto _L1
_L1:
        obj = obj2;
        if(false) goto _L2; else goto _L3
_L3:
        return obj1;
        obj;
        if(true)
            if(!flag);
        throw obj;
    }

    private void setRawTextSize(float f)
    {
        boolean flag;
        if(f == mTextView.getPaint().getTextSize())
            break MISSING_BLOCK_LABEL_102;
        mTextView.getPaint().setTextSize(f);
        flag = false;
        if(android.os.Build.VERSION.SDK_INT >= 18)
            flag = mTextView.isInLayout();
        if(mTextView.getLayout() == null)
            break MISSING_BLOCK_LABEL_102;
        mNeedsAutoSizeText = false;
        Method method = getTextViewMethod("nullLayouts");
        if(method != null)
            try
            {
                method.invoke(mTextView, new Object[0]);
            }
            catch(Exception exception)
            {
                Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", exception);
            }
        if(!flag)
            mTextView.requestLayout();
        else
            mTextView.forceLayout();
        mTextView.invalidate();
    }

    private boolean setupAutoSizeText()
    {
        if(supportsAutoSizeText() && mAutoSizeTextType == 1)
        {
            if(!mHasPresetAutoSizeValues || mAutoSizeTextSizesInPx.length == 0)
            {
                int i = 1;
                for(float f = Math.round(mAutoSizeMinTextSizeInPx); Math.round(mAutoSizeStepGranularityInPx + f) <= Math.round(mAutoSizeMaxTextSizeInPx); f += mAutoSizeStepGranularityInPx)
                    i++;

                int ai[] = new int[i];
                float f1 = mAutoSizeMinTextSizeInPx;
                for(int j = 0; j < i; j++)
                {
                    ai[j] = Math.round(f1);
                    f1 += mAutoSizeStepGranularityInPx;
                }

                mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(ai);
            }
            mNeedsAutoSizeText = true;
        } else
        {
            mNeedsAutoSizeText = false;
        }
        return mNeedsAutoSizeText;
    }

    private void setupAutoSizeUniformPresetSizes(TypedArray typedarray)
    {
        int j = typedarray.length();
        int ai[] = new int[j];
        if(j > 0)
        {
            for(int i = 0; i < j; i++)
                ai[i] = typedarray.getDimensionPixelSize(i, -1);

            mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(ai);
            setupAutoSizeUniformPresetSizesConfiguration();
        }
    }

    private boolean setupAutoSizeUniformPresetSizesConfiguration()
    {
        int i = mAutoSizeTextSizesInPx.length;
        boolean flag;
        if(i > 0)
            flag = true;
        else
            flag = false;
        mHasPresetAutoSizeValues = flag;
        if(mHasPresetAutoSizeValues)
        {
            mAutoSizeTextType = 1;
            mAutoSizeMinTextSizeInPx = mAutoSizeTextSizesInPx[0];
            mAutoSizeMaxTextSizeInPx = mAutoSizeTextSizesInPx[i - 1];
            mAutoSizeStepGranularityInPx = -1F;
        }
        return mHasPresetAutoSizeValues;
    }

    private boolean suggestedSizeFitsInSpace(int i, RectF rectf)
    {
        CharSequence charsequence = mTextView.getText();
        int j;
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 16)
            j = mTextView.getMaxLines();
        else
            j = -1;
        if(mTempTextPaint == null)
            mTempTextPaint = new TextPaint();
        else
            mTempTextPaint.reset();
        mTempTextPaint.set(mTextView.getPaint());
        mTempTextPaint.setTextSize(i);
        obj = (android.text.Layout.Alignment)invokeAndReturnWithDefault(mTextView, "getLayoutAlignment", android.text.Layout.Alignment.ALIGN_NORMAL);
        if(android.os.Build.VERSION.SDK_INT >= 23)
            obj = createStaticLayoutForMeasuring(charsequence, ((android.text.Layout.Alignment) (obj)), Math.round(rectf.right), j);
        else
            obj = createStaticLayoutForMeasuringPre23(charsequence, ((android.text.Layout.Alignment) (obj)), Math.round(rectf.right));
        if(j != -1 && (((StaticLayout) (obj)).getLineCount() > j || ((StaticLayout) (obj)).getLineEnd(((StaticLayout) (obj)).getLineCount() - 1) != charsequence.length()))
            return false;
        return (float)((StaticLayout) (obj)).getHeight() <= rectf.bottom;
    }

    private boolean supportsAutoSizeText()
    {
        return !(mTextView instanceof AppCompatEditText);
    }

    private void validateAndSetAutoSizeTextTypeUniformConfiguration(float f, float f1, float f2)
        throws IllegalArgumentException
    {
        if(f <= 0.0F)
            throw new IllegalArgumentException((new StringBuilder()).append("Minimum auto-size text size (").append(f).append("px) is less or equal to (0px)").toString());
        if(f1 <= f)
            throw new IllegalArgumentException((new StringBuilder()).append("Maximum auto-size text size (").append(f1).append("px) is less or equal to minimum auto-size ").append("text size (").append(f).append("px)").toString());
        if(f2 <= 0.0F)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The auto-size step granularity (").append(f2).append("px) is less or equal to (0px)").toString());
        } else
        {
            mAutoSizeTextType = 1;
            mAutoSizeMinTextSizeInPx = f;
            mAutoSizeMaxTextSizeInPx = f1;
            mAutoSizeStepGranularityInPx = f2;
            mHasPresetAutoSizeValues = false;
            return;
        }
    }

    void autoSizeText()
    {
        if(isAutoSizeEnabled()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(!mNeedsAutoSizeText)
            break; /* Loop/switch isn't completed */
        if(mTextView.getMeasuredHeight() <= 0 || mTextView.getMeasuredWidth() <= 0)
            continue; /* Loop/switch isn't completed */
        int i;
        int j;
        if(((Boolean)invokeAndReturnWithDefault(mTextView, "getHorizontallyScrolling", Boolean.valueOf(false))).booleanValue())
            i = 0x100000;
        else
            i = mTextView.getMeasuredWidth() - mTextView.getTotalPaddingLeft() - mTextView.getTotalPaddingRight();
        j = mTextView.getHeight() - mTextView.getCompoundPaddingBottom() - mTextView.getCompoundPaddingTop();
        if(i <= 0 || j <= 0)
            continue; /* Loop/switch isn't completed */
        synchronized(TEMP_RECTF)
        {
            TEMP_RECTF.setEmpty();
            TEMP_RECTF.right = i;
            TEMP_RECTF.bottom = j;
            float f = findLargestTextSizeWhichFits(TEMP_RECTF);
            if(f != mTextView.getTextSize())
                setTextSizeInternal(0, f);
        }
        break; /* Loop/switch isn't completed */
        if(true) goto _L1; else goto _L3
_L3:
        mNeedsAutoSizeText = true;
        return;
        exception;
        rectf;
        JVM INSTR monitorexit ;
        throw exception;
    }

    int getAutoSizeMaxTextSize()
    {
        return Math.round(mAutoSizeMaxTextSizeInPx);
    }

    int getAutoSizeMinTextSize()
    {
        return Math.round(mAutoSizeMinTextSizeInPx);
    }

    int getAutoSizeStepGranularity()
    {
        return Math.round(mAutoSizeStepGranularityInPx);
    }

    int[] getAutoSizeTextAvailableSizes()
    {
        return mAutoSizeTextSizesInPx;
    }

    int getAutoSizeTextType()
    {
        return mAutoSizeTextType;
    }

    boolean isAutoSizeEnabled()
    {
        return supportsAutoSizeText() && mAutoSizeTextType != 0;
    }

    void loadFromAttributes(AttributeSet attributeset, int i)
    {
        float f1 = -1F;
        float f2 = -1F;
        float f = -1F;
        attributeset = mContext.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.AppCompatTextView, i, 0);
        if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeTextType))
            mAutoSizeTextType = attributeset.getInt(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeTextType, 0);
        if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeStepGranularity))
            f = attributeset.getDimension(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeStepGranularity, -1F);
        if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeMinTextSize))
            f1 = attributeset.getDimension(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeMinTextSize, -1F);
        if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeMaxTextSize))
            f2 = attributeset.getDimension(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeMaxTextSize, -1F);
        if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizePresetSizes))
        {
            i = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizePresetSizes, 0);
            if(i > 0)
            {
                TypedArray typedarray = attributeset.getResources().obtainTypedArray(i);
                setupAutoSizeUniformPresetSizes(typedarray);
                typedarray.recycle();
            }
        }
        attributeset.recycle();
        if(supportsAutoSizeText())
        {
            if(mAutoSizeTextType == 1)
            {
                if(!mHasPresetAutoSizeValues)
                {
                    attributeset = mContext.getResources().getDisplayMetrics();
                    float f3 = f1;
                    if(f1 == -1F)
                        f3 = TypedValue.applyDimension(2, 12F, attributeset);
                    f1 = f2;
                    if(f2 == -1F)
                        f1 = TypedValue.applyDimension(2, 112F, attributeset);
                    f2 = f;
                    if(f == -1F)
                        f2 = 1.0F;
                    validateAndSetAutoSizeTextTypeUniformConfiguration(f3, f1, f2);
                }
                setupAutoSizeText();
            }
            return;
        } else
        {
            mAutoSizeTextType = 0;
            return;
        }
    }

    void setAutoSizeTextTypeUniformWithConfiguration(int i, int j, int k, int l)
        throws IllegalArgumentException
    {
        if(supportsAutoSizeText())
        {
            android.util.DisplayMetrics displaymetrics = mContext.getResources().getDisplayMetrics();
            validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(l, i, displaymetrics), TypedValue.applyDimension(l, j, displaymetrics), TypedValue.applyDimension(l, k, displaymetrics));
            if(setupAutoSizeText())
                autoSizeText();
        }
    }

    void setAutoSizeTextTypeUniformWithPresetSizes(int ai[], int i)
        throws IllegalArgumentException
    {
        int k;
        if(!supportsAutoSizeText())
            break MISSING_BLOCK_LABEL_146;
        k = ai.length;
        if(k <= 0) goto _L2; else goto _L1
_L1:
        int ai2[] = new int[k];
        if(i != 0) goto _L4; else goto _L3
_L3:
        int ai1[] = Arrays.copyOf(ai, k);
_L6:
        mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(ai1);
        if(!setupAutoSizeUniformPresetSizesConfiguration())
            throw new IllegalArgumentException((new StringBuilder()).append("None of the preset sizes is valid: ").append(Arrays.toString(ai)).toString());
        break; /* Loop/switch isn't completed */
_L4:
        android.util.DisplayMetrics displaymetrics = mContext.getResources().getDisplayMetrics();
        int j = 0;
        do
        {
            ai1 = ai2;
            if(j >= k)
                break;
            ai2[j] = Math.round(TypedValue.applyDimension(i, ai[j], displaymetrics));
            j++;
        } while(true);
        if(true) goto _L6; else goto _L5
_L2:
        mHasPresetAutoSizeValues = false;
_L5:
        if(setupAutoSizeText())
            autoSizeText();
    }

    void setAutoSizeTextTypeWithDefaults(int i)
    {
        if(!supportsAutoSizeText()) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 0 1: default 32
    //                   0 60
    //                   1 65;
           goto _L3 _L4 _L5
_L3:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown auto-size text type: ").append(i).toString());
_L4:
        clearAutoSizeConfiguration();
_L2:
        return;
_L5:
        android.util.DisplayMetrics displaymetrics = mContext.getResources().getDisplayMetrics();
        validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12F, displaymetrics), TypedValue.applyDimension(2, 112F, displaymetrics), 1.0F);
        if(setupAutoSizeText())
        {
            autoSizeText();
            return;
        }
        if(true) goto _L2; else goto _L6
_L6:
    }

    void setTextSizeInternal(int i, float f)
    {
        Resources resources;
        if(mContext == null)
            resources = Resources.getSystem();
        else
            resources = mContext.getResources();
        setRawTextSize(TypedValue.applyDimension(i, f, resources.getDisplayMetrics()));
    }

    private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
    private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 112;
    private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
    private static final String TAG = "ACTVAutoSizeHelper";
    private static final RectF TEMP_RECTF = new RectF();
    static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1F;
    private static final int VERY_WIDE = 0x100000;
    private static Hashtable sTextViewMethodByNameCache = new Hashtable();
    private float mAutoSizeMaxTextSizeInPx;
    private float mAutoSizeMinTextSizeInPx;
    private float mAutoSizeStepGranularityInPx;
    private int mAutoSizeTextSizesInPx[];
    private int mAutoSizeTextType;
    private final Context mContext;
    private boolean mHasPresetAutoSizeValues;
    private boolean mNeedsAutoSizeText;
    private TextPaint mTempTextPaint;
    private final TextView mTextView;

}
