// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v4.util.Preconditions;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.DisplayMetrics;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Annotation;

public class CircularProgressDrawable extends Drawable
    implements Animatable
{
    public static interface ProgressDrawableSize
        extends Annotation
    {
    }

    private static class Ring
    {

        void draw(Canvas canvas, Rect rect)
        {
            RectF rectf = mTempBounds;
            float f = mRingCenterRadius + mStrokeWidth / 2.0F;
            if(mRingCenterRadius <= 0.0F)
                f = (float)Math.min(rect.width(), rect.height()) / 2.0F - Math.max(((float)mArrowWidth * mArrowScale) / 2.0F, mStrokeWidth / 2.0F);
            rectf.set((float)rect.centerX() - f, (float)rect.centerY() - f, (float)rect.centerX() + f, (float)rect.centerY() + f);
            f = (mStartTrim + mRotation) * 360F;
            float f1 = (mEndTrim + mRotation) * 360F - f;
            mPaint.setColor(mCurrentColor);
            mPaint.setAlpha(mAlpha);
            float f2 = mStrokeWidth / 2.0F;
            rectf.inset(f2, f2);
            canvas.drawCircle(rectf.centerX(), rectf.centerY(), rectf.width() / 2.0F, mCirclePaint);
            rectf.inset(-f2, -f2);
            canvas.drawArc(rectf, f, f1, false, mPaint);
            drawTriangle(canvas, f, f1, rectf);
        }

        void drawTriangle(Canvas canvas, float f, float f1, RectF rectf)
        {
            if(mShowArrow)
            {
                float f2;
                float f3;
                if(mArrow == null)
                {
                    mArrow = new Path();
                    mArrow.setFillType(android.graphics.Path.FillType.EVEN_ODD);
                } else
                {
                    mArrow.reset();
                }
                f2 = Math.min(rectf.width(), rectf.height()) / 2.0F;
                f3 = ((float)mArrowWidth * mArrowScale) / 2.0F;
                mArrow.moveTo(0.0F, 0.0F);
                mArrow.lineTo((float)mArrowWidth * mArrowScale, 0.0F);
                mArrow.lineTo(((float)mArrowWidth * mArrowScale) / 2.0F, (float)mArrowHeight * mArrowScale);
                mArrow.offset((rectf.centerX() + f2) - f3, rectf.centerY() + mStrokeWidth / 2.0F);
                mArrow.close();
                mArrowPaint.setColor(mCurrentColor);
                mArrowPaint.setAlpha(mAlpha);
                canvas.save();
                canvas.rotate(f + f1, rectf.centerX(), rectf.centerY());
                canvas.drawPath(mArrow, mArrowPaint);
                canvas.restore();
            }
        }

        int getAlpha()
        {
            return mAlpha;
        }

        float getArrowHeight()
        {
            return (float)mArrowHeight;
        }

        float getArrowScale()
        {
            return mArrowScale;
        }

        float getArrowWidth()
        {
            return (float)mArrowWidth;
        }

        int getBackgroundColor()
        {
            return mCirclePaint.getColor();
        }

        float getCenterRadius()
        {
            return mRingCenterRadius;
        }

        int[] getColors()
        {
            return mColors;
        }

        float getEndTrim()
        {
            return mEndTrim;
        }

        int getNextColor()
        {
            return mColors[getNextColorIndex()];
        }

        int getNextColorIndex()
        {
            return (mColorIndex + 1) % mColors.length;
        }

        float getRotation()
        {
            return mRotation;
        }

        boolean getShowArrow()
        {
            return mShowArrow;
        }

        float getStartTrim()
        {
            return mStartTrim;
        }

        int getStartingColor()
        {
            return mColors[mColorIndex];
        }

        float getStartingEndTrim()
        {
            return mStartingEndTrim;
        }

        float getStartingRotation()
        {
            return mStartingRotation;
        }

        float getStartingStartTrim()
        {
            return mStartingStartTrim;
        }

        android.graphics.Paint.Cap getStrokeCap()
        {
            return mPaint.getStrokeCap();
        }

        float getStrokeWidth()
        {
            return mStrokeWidth;
        }

        void goToNextColor()
        {
            setColorIndex(getNextColorIndex());
        }

        void resetOriginals()
        {
            mStartingStartTrim = 0.0F;
            mStartingEndTrim = 0.0F;
            mStartingRotation = 0.0F;
            setStartTrim(0.0F);
            setEndTrim(0.0F);
            setRotation(0.0F);
        }

        void setAlpha(int i)
        {
            mAlpha = i;
        }

        void setArrowDimensions(float f, float f1)
        {
            mArrowWidth = (int)f;
            mArrowHeight = (int)f1;
        }

        void setArrowScale(float f)
        {
            if(f != mArrowScale)
                mArrowScale = f;
        }

        void setBackgroundColor(int i)
        {
            mCirclePaint.setColor(i);
        }

        void setCenterRadius(float f)
        {
            mRingCenterRadius = f;
        }

        void setColor(int i)
        {
            mCurrentColor = i;
        }

        void setColorFilter(ColorFilter colorfilter)
        {
            mPaint.setColorFilter(colorfilter);
        }

        void setColorIndex(int i)
        {
            mColorIndex = i;
            mCurrentColor = mColors[mColorIndex];
        }

        void setColors(int ai[])
        {
            mColors = ai;
            setColorIndex(0);
        }

        void setEndTrim(float f)
        {
            mEndTrim = f;
        }

        void setRotation(float f)
        {
            mRotation = f;
        }

        void setShowArrow(boolean flag)
        {
            if(mShowArrow != flag)
                mShowArrow = flag;
        }

        void setStartTrim(float f)
        {
            mStartTrim = f;
        }

        void setStrokeCap(android.graphics.Paint.Cap cap)
        {
            mPaint.setStrokeCap(cap);
        }

        void setStrokeWidth(float f)
        {
            mStrokeWidth = f;
            mPaint.setStrokeWidth(f);
        }

        void storeOriginals()
        {
            mStartingStartTrim = mStartTrim;
            mStartingEndTrim = mEndTrim;
            mStartingRotation = mRotation;
        }

        int mAlpha;
        Path mArrow;
        int mArrowHeight;
        final Paint mArrowPaint = new Paint();
        float mArrowScale;
        int mArrowWidth;
        final Paint mCirclePaint = new Paint();
        int mColorIndex;
        int mColors[];
        int mCurrentColor;
        float mEndTrim;
        final Paint mPaint = new Paint();
        float mRingCenterRadius;
        float mRotation;
        boolean mShowArrow;
        float mStartTrim;
        float mStartingEndTrim;
        float mStartingRotation;
        float mStartingStartTrim;
        float mStrokeWidth;
        final RectF mTempBounds = new RectF();

        Ring()
        {
            mStartTrim = 0.0F;
            mEndTrim = 0.0F;
            mRotation = 0.0F;
            mStrokeWidth = 5F;
            mArrowScale = 1.0F;
            mAlpha = 255;
            mPaint.setStrokeCap(android.graphics.Paint.Cap.SQUARE);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(android.graphics.Paint.Style.STROKE);
            mArrowPaint.setStyle(android.graphics.Paint.Style.FILL);
            mArrowPaint.setAntiAlias(true);
            mCirclePaint.setColor(0);
        }
    }


    public CircularProgressDrawable(Context context)
    {
        mResources = ((Context)Preconditions.checkNotNull(context)).getResources();
        mRing.setColors(COLORS);
        setStrokeWidth(2.5F);
        setupAnimators();
    }

    private void applyFinishTranslation(float f, Ring ring)
    {
        updateRingColor(f, ring);
        float f1 = (float)(Math.floor(ring.getStartingRotation() / 0.8F) + 1.0D);
        ring.setStartTrim(ring.getStartingStartTrim() + (ring.getStartingEndTrim() - 0.01F - ring.getStartingStartTrim()) * f);
        ring.setEndTrim(ring.getStartingEndTrim());
        ring.setRotation(ring.getStartingRotation() + (f1 - ring.getStartingRotation()) * f);
    }

    private void applyTransformation(float f, Ring ring, boolean flag)
    {
        if(mFinishing)
            applyFinishTranslation(f, ring);
        else
        if(f != 1.0F || flag)
        {
            float f3 = ring.getStartingRotation();
            float f1;
            float f2;
            float f4;
            if(f < 0.5F)
            {
                f1 = f / 0.5F;
                f2 = ring.getStartingStartTrim();
                f1 = f2 + (MATERIAL_INTERPOLATOR.getInterpolation(f1) * 0.79F + 0.01F);
            } else
            {
                f2 = (f - 0.5F) / 0.5F;
                f1 = ring.getStartingStartTrim() + 0.79F;
                f2 = f1 - ((1.0F - MATERIAL_INTERPOLATOR.getInterpolation(f2)) * 0.79F + 0.01F);
            }
            f4 = mRotationCount;
            ring.setStartTrim(f2);
            ring.setEndTrim(f1);
            ring.setRotation(f3 + 0.21F * f);
            setRotation(216F * (f4 + f));
            return;
        }
    }

    private int evaluateColorChange(float f, int i, int j)
    {
        int k = i >> 24 & 0xff;
        int l = i >> 16 & 0xff;
        int i1 = i >> 8 & 0xff;
        i &= 0xff;
        return (int)((float)((j >> 24 & 0xff) - k) * f) + k << 24 | (int)((float)((j >> 16 & 0xff) - l) * f) + l << 16 | (int)((float)((j >> 8 & 0xff) - i1) * f) + i1 << 8 | (int)((float)((j & 0xff) - i) * f) + i;
    }

    private float getRotation()
    {
        return mRotation;
    }

    private void setRotation(float f)
    {
        mRotation = f;
    }

    private void setSizeParameters(float f, float f1, float f2, float f3)
    {
        Ring ring = mRing;
        float f4 = mResources.getDisplayMetrics().density;
        ring.setStrokeWidth(f1 * f4);
        ring.setCenterRadius(f * f4);
        ring.setColorIndex(0);
        ring.setArrowDimensions(f2 * f4, f3 * f4);
    }

    private void setupAnimators()
    {
        final Ring ring = mRing;
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            0.0F, 1.0F
        });
        valueanimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator1)
            {
                float f = ((Float)valueanimator1.getAnimatedValue()).floatValue();
                updateRingColor(f, ring);
                applyTransformation(f, ring, false);
                invalidateSelf();
            }

            final CircularProgressDrawable this$0;
            final Ring val$ring;

            
            {
                this$0 = CircularProgressDrawable.this;
                ring = ring1;
                super();
            }
        }
);
        valueanimator.setRepeatCount(-1);
        valueanimator.setRepeatMode(1);
        valueanimator.setInterpolator(LINEAR_INTERPOLATOR);
        valueanimator.addListener(new android.animation.Animator.AnimatorListener() {

            public void onAnimationCancel(Animator animator)
            {
            }

            public void onAnimationEnd(Animator animator)
            {
            }

            public void onAnimationRepeat(Animator animator)
            {
                applyTransformation(1.0F, ring, true);
                ring.storeOriginals();
                ring.goToNextColor();
                if(mFinishing)
                {
                    mFinishing = false;
                    animator.cancel();
                    animator.setDuration(1332L);
                    animator.start();
                    ring.setShowArrow(false);
                    return;
                } else
                {
                    mRotationCount = mRotationCount + 1.0F;
                    return;
                }
            }

            public void onAnimationStart(Animator animator)
            {
                mRotationCount = 0.0F;
            }

            final CircularProgressDrawable this$0;
            final Ring val$ring;

            
            {
                this$0 = CircularProgressDrawable.this;
                ring = ring1;
                super();
            }
        }
);
        mAnimator = valueanimator;
    }

    private void updateRingColor(float f, Ring ring)
    {
        if(f > 0.75F)
        {
            ring.setColor(evaluateColorChange((f - 0.75F) / 0.25F, ring.getStartingColor(), ring.getNextColor()));
            return;
        } else
        {
            ring.setColor(ring.getStartingColor());
            return;
        }
    }

    public void draw(Canvas canvas)
    {
        Rect rect = getBounds();
        canvas.save();
        canvas.rotate(mRotation, rect.exactCenterX(), rect.exactCenterY());
        mRing.draw(canvas, rect);
        canvas.restore();
    }

    public int getAlpha()
    {
        return mRing.getAlpha();
    }

    public boolean getArrowEnabled()
    {
        return mRing.getShowArrow();
    }

    public float getArrowHeight()
    {
        return mRing.getArrowHeight();
    }

    public float getArrowScale()
    {
        return mRing.getArrowScale();
    }

    public float getArrowWidth()
    {
        return mRing.getArrowWidth();
    }

    public int getBackgroundColor()
    {
        return mRing.getBackgroundColor();
    }

    public float getCenterRadius()
    {
        return mRing.getCenterRadius();
    }

    public int[] getColorSchemeColors()
    {
        return mRing.getColors();
    }

    public float getEndTrim()
    {
        return mRing.getEndTrim();
    }

    public int getOpacity()
    {
        return -3;
    }

    public float getProgressRotation()
    {
        return mRing.getRotation();
    }

    public float getStartTrim()
    {
        return mRing.getStartTrim();
    }

    public android.graphics.Paint.Cap getStrokeCap()
    {
        return mRing.getStrokeCap();
    }

    public float getStrokeWidth()
    {
        return mRing.getStrokeWidth();
    }

    public boolean isRunning()
    {
        return mAnimator.isRunning();
    }

    public void setAlpha(int i)
    {
        mRing.setAlpha(i);
        invalidateSelf();
    }

    public void setArrowDimensions(float f, float f1)
    {
        mRing.setArrowDimensions(f, f1);
        invalidateSelf();
    }

    public void setArrowEnabled(boolean flag)
    {
        mRing.setShowArrow(flag);
        invalidateSelf();
    }

    public void setArrowScale(float f)
    {
        mRing.setArrowScale(f);
        invalidateSelf();
    }

    public void setBackgroundColor(int i)
    {
        mRing.setBackgroundColor(i);
        invalidateSelf();
    }

    public void setCenterRadius(float f)
    {
        mRing.setCenterRadius(f);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mRing.setColorFilter(colorfilter);
        invalidateSelf();
    }

    public transient void setColorSchemeColors(int ai[])
    {
        mRing.setColors(ai);
        mRing.setColorIndex(0);
        invalidateSelf();
    }

    public void setProgressRotation(float f)
    {
        mRing.setRotation(f);
        invalidateSelf();
    }

    public void setStartEndTrim(float f, float f1)
    {
        mRing.setStartTrim(f);
        mRing.setEndTrim(f1);
        invalidateSelf();
    }

    public void setStrokeCap(android.graphics.Paint.Cap cap)
    {
        mRing.setStrokeCap(cap);
        invalidateSelf();
    }

    public void setStrokeWidth(float f)
    {
        mRing.setStrokeWidth(f);
        invalidateSelf();
    }

    public void setStyle(int i)
    {
        if(i == 0)
            setSizeParameters(11F, 3F, 12F, 6F);
        else
            setSizeParameters(7.5F, 2.5F, 10F, 5F);
        invalidateSelf();
    }

    public void start()
    {
        mAnimator.cancel();
        mRing.storeOriginals();
        if(mRing.getEndTrim() != mRing.getStartTrim())
        {
            mFinishing = true;
            mAnimator.setDuration(666L);
            mAnimator.start();
            return;
        } else
        {
            mRing.setColorIndex(0);
            mRing.resetOriginals();
            mAnimator.setDuration(1332L);
            mAnimator.start();
            return;
        }
    }

    public void stop()
    {
        mAnimator.cancel();
        setRotation(0.0F);
        mRing.setShowArrow(false);
        mRing.setColorIndex(0);
        mRing.resetOriginals();
        invalidateSelf();
    }

    private static final int ANIMATION_DURATION = 1332;
    private static final int ARROW_HEIGHT = 5;
    private static final int ARROW_HEIGHT_LARGE = 6;
    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final float CENTER_RADIUS = 7.5F;
    private static final float CENTER_RADIUS_LARGE = 11F;
    private static final int COLORS[] = {
        0xff000000
    };
    private static final float COLOR_CHANGE_OFFSET = 0.75F;
    public static final int DEFAULT = 1;
    private static final float GROUP_FULL_ROTATION = 216F;
    public static final int LARGE = 0;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final float MAX_PROGRESS_ARC = 0.8F;
    private static final float MIN_PROGRESS_ARC = 0.01F;
    private static final float RING_ROTATION = 0.21F;
    private static final float SHRINK_OFFSET = 0.5F;
    private static final float STROKE_WIDTH = 2.5F;
    private static final float STROKE_WIDTH_LARGE = 3F;
    private Animator mAnimator;
    private boolean mFinishing;
    private Resources mResources;
    private final Ring mRing = new Ring();
    private float mRotation;
    private float mRotationCount;






/*
    static float access$202(CircularProgressDrawable circularprogressdrawable, float f)
    {
        circularprogressdrawable.mRotationCount = f;
        return f;
    }

*/



/*
    static boolean access$302(CircularProgressDrawable circularprogressdrawable, boolean flag)
    {
        circularprogressdrawable.mFinishing = flag;
        return flag;
    }

*/
}
