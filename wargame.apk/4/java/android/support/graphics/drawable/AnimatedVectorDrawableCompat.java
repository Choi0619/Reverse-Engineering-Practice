// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.graphics.drawable;

import android.animation.*;
import android.content.Context;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.graphics.drawable:
//            VectorDrawableCommon, Animatable2Compat, VectorDrawableCompat, AndroidResources, 
//            AnimatorInflaterCompat

public class AnimatedVectorDrawableCompat extends VectorDrawableCommon
    implements Animatable2Compat
{
    private static class AnimatedVectorDrawableCompatState extends android.graphics.drawable.Drawable.ConstantState
    {

        public int getChangingConfigurations()
        {
            return mChangingConfigurations;
        }

        public Drawable newDrawable()
        {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public Drawable newDrawable(Resources resources)
        {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public void setupAnimatorSet()
        {
            if(mAnimatorSet == null)
                mAnimatorSet = new AnimatorSet();
            mAnimatorSet.playTogether(mAnimators);
        }

        AnimatorSet mAnimatorSet;
        private ArrayList mAnimators;
        int mChangingConfigurations;
        ArrayMap mTargetNameMap;
        VectorDrawableCompat mVectorDrawable;



/*
        static ArrayList access$002(AnimatedVectorDrawableCompatState animatedvectordrawablecompatstate, ArrayList arraylist)
        {
            animatedvectordrawablecompatstate.mAnimators = arraylist;
            return arraylist;
        }

*/

        public AnimatedVectorDrawableCompatState(Context context, AnimatedVectorDrawableCompatState animatedvectordrawablecompatstate, android.graphics.drawable.Drawable.Callback callback, Resources resources)
        {
            if(animatedvectordrawablecompatstate != null)
            {
                mChangingConfigurations = animatedvectordrawablecompatstate.mChangingConfigurations;
                if(animatedvectordrawablecompatstate.mVectorDrawable != null)
                {
                    context = animatedvectordrawablecompatstate.mVectorDrawable.getConstantState();
                    int i;
                    int j;
                    if(resources != null)
                        mVectorDrawable = (VectorDrawableCompat)context.newDrawable(resources);
                    else
                        mVectorDrawable = (VectorDrawableCompat)context.newDrawable();
                    mVectorDrawable = (VectorDrawableCompat)mVectorDrawable.mutate();
                    mVectorDrawable.setCallback(callback);
                    mVectorDrawable.setBounds(animatedvectordrawablecompatstate.mVectorDrawable.getBounds());
                    mVectorDrawable.setAllowCaching(false);
                }
                if(animatedvectordrawablecompatstate.mAnimators != null)
                {
                    j = animatedvectordrawablecompatstate.mAnimators.size();
                    mAnimators = new ArrayList(j);
                    mTargetNameMap = new ArrayMap(j);
                    for(i = 0; i < j; i++)
                    {
                        callback = (Animator)animatedvectordrawablecompatstate.mAnimators.get(i);
                        context = callback.clone();
                        callback = (String)animatedvectordrawablecompatstate.mTargetNameMap.get(callback);
                        context.setTarget(mVectorDrawable.getTargetByName(callback));
                        mAnimators.add(context);
                        mTargetNameMap.put(context, callback);
                    }

                    setupAnimatorSet();
                }
            }
        }
    }

    private static class AnimatedVectorDrawableDelegateState extends android.graphics.drawable.Drawable.ConstantState
    {

        public boolean canApplyTheme()
        {
            return mDelegateState.canApplyTheme();
        }

        public int getChangingConfigurations()
        {
            return mDelegateState.getChangingConfigurations();
        }

        public Drawable newDrawable()
        {
            AnimatedVectorDrawableCompat animatedvectordrawablecompat = new AnimatedVectorDrawableCompat();
            animatedvectordrawablecompat.mDelegateDrawable = mDelegateState.newDrawable();
            animatedvectordrawablecompat.mDelegateDrawable.setCallback(animatedvectordrawablecompat.mCallback);
            return animatedvectordrawablecompat;
        }

        public Drawable newDrawable(Resources resources)
        {
            AnimatedVectorDrawableCompat animatedvectordrawablecompat = new AnimatedVectorDrawableCompat();
            animatedvectordrawablecompat.mDelegateDrawable = mDelegateState.newDrawable(resources);
            animatedvectordrawablecompat.mDelegateDrawable.setCallback(animatedvectordrawablecompat.mCallback);
            return animatedvectordrawablecompat;
        }

        public Drawable newDrawable(Resources resources, android.content.res.Resources.Theme theme)
        {
            AnimatedVectorDrawableCompat animatedvectordrawablecompat = new AnimatedVectorDrawableCompat();
            animatedvectordrawablecompat.mDelegateDrawable = mDelegateState.newDrawable(resources, theme);
            animatedvectordrawablecompat.mDelegateDrawable.setCallback(animatedvectordrawablecompat.mCallback);
            return animatedvectordrawablecompat;
        }

        private final android.graphics.drawable.Drawable.ConstantState mDelegateState;

        public AnimatedVectorDrawableDelegateState(android.graphics.drawable.Drawable.ConstantState constantstate)
        {
            mDelegateState = constantstate;
        }
    }


    AnimatedVectorDrawableCompat()
    {
        this(null, null, null);
    }

    private AnimatedVectorDrawableCompat(Context context)
    {
        this(context, null, null);
    }

    private AnimatedVectorDrawableCompat(Context context, AnimatedVectorDrawableCompatState animatedvectordrawablecompatstate, Resources resources)
    {
        mArgbEvaluator = null;
        mAnimatorListener = null;
        mAnimationCallbacks = null;
        mCallback = new android.graphics.drawable.Drawable.Callback() {

            public void invalidateDrawable(Drawable drawable)
            {
                invalidateSelf();
            }

            public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
            {
                scheduleSelf(runnable, l);
            }

            public void unscheduleDrawable(Drawable drawable, Runnable runnable)
            {
                unscheduleSelf(runnable);
            }

            final AnimatedVectorDrawableCompat this$0;

            
            {
                this$0 = AnimatedVectorDrawableCompat.this;
                super();
            }
        }
;
        mContext = context;
        if(animatedvectordrawablecompatstate != null)
        {
            mAnimatedVectorState = animatedvectordrawablecompatstate;
            return;
        } else
        {
            mAnimatedVectorState = new AnimatedVectorDrawableCompatState(context, animatedvectordrawablecompatstate, mCallback, resources);
            return;
        }
    }

    public static void clearAnimationCallbacks(Drawable drawable)
    {
        if(drawable == null || !(drawable instanceof Animatable))
            return;
        if(android.os.Build.VERSION.SDK_INT >= 24)
        {
            ((AnimatedVectorDrawable)drawable).clearAnimationCallbacks();
            return;
        } else
        {
            ((AnimatedVectorDrawableCompat)drawable).clearAnimationCallbacks();
            return;
        }
    }

    public static AnimatedVectorDrawableCompat create(Context context, int i)
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 24)
        {
            AnimatedVectorDrawableCompat animatedvectordrawablecompat = new AnimatedVectorDrawableCompat(context);
            animatedvectordrawablecompat.mDelegateDrawable = ResourcesCompat.getDrawable(context.getResources(), i, context.getTheme());
            animatedvectordrawablecompat.mDelegateDrawable.setCallback(animatedvectordrawablecompat.mCallback);
            animatedvectordrawablecompat.mCachedConstantStateDelegate = new AnimatedVectorDrawableDelegateState(animatedvectordrawablecompat.mDelegateDrawable.getConstantState());
            return animatedvectordrawablecompat;
        }
        obj = context.getResources();
        AttributeSet attributeset;
        obj = ((Resources) (obj)).getXml(i);
        attributeset = Xml.asAttributeSet(((XmlPullParser) (obj)));
        do
            i = ((XmlPullParser) (obj)).next();
        while(i != 2 && i != 1);
        if(i != 2)
        {
            try
            {
                throw new XmlPullParserException("No start tag found");
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.e("AnimatedVDCompat", "parser error", context);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.e("AnimatedVDCompat", "parser error", context);
            }
            return null;
        }
        context = createFromXmlInner(context, context.getResources(), ((XmlPullParser) (obj)), attributeset, context.getTheme());
        return context;
    }

    public static AnimatedVectorDrawableCompat createFromXmlInner(Context context, Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        context = new AnimatedVectorDrawableCompat(context);
        context.inflate(resources, xmlpullparser, attributeset, theme);
        return context;
    }

    public static void registerAnimationCallback(Drawable drawable, Animatable2Compat.AnimationCallback animationcallback)
    {
        while(drawable == null || animationcallback == null || !(drawable instanceof Animatable)) 
            return;
        if(android.os.Build.VERSION.SDK_INT >= 24)
        {
            registerPlatformCallback((AnimatedVectorDrawable)drawable, animationcallback);
            return;
        } else
        {
            ((AnimatedVectorDrawableCompat)drawable).registerAnimationCallback(animationcallback);
            return;
        }
    }

    private static void registerPlatformCallback(AnimatedVectorDrawable animatedvectordrawable, Animatable2Compat.AnimationCallback animationcallback)
    {
        animatedvectordrawable.registerAnimationCallback(animationcallback.getPlatformCallback());
    }

    private void removeAnimatorSetListener()
    {
        if(mAnimatorListener != null)
        {
            mAnimatedVectorState.mAnimatorSet.removeListener(mAnimatorListener);
            mAnimatorListener = null;
        }
    }

    private void setupAnimatorsForTarget(String s, Animator animator)
    {
        animator.setTarget(mAnimatedVectorState.mVectorDrawable.getTargetByName(s));
        if(android.os.Build.VERSION.SDK_INT < 21)
            setupColorAnimator(animator);
        if(mAnimatedVectorState.mAnimators == null)
        {
            mAnimatedVectorState.mAnimators = new ArrayList();
            mAnimatedVectorState.mTargetNameMap = new ArrayMap();
        }
        mAnimatedVectorState.mAnimators.add(animator);
        mAnimatedVectorState.mTargetNameMap.put(animator, s);
    }

    private void setupColorAnimator(Animator animator)
    {
        if(animator instanceof AnimatorSet)
        {
            ArrayList arraylist = ((AnimatorSet)animator).getChildAnimations();
            if(arraylist != null)
            {
                for(int i = 0; i < arraylist.size(); i++)
                    setupColorAnimator((Animator)arraylist.get(i));

            }
        }
        if(animator instanceof ObjectAnimator)
        {
            animator = (ObjectAnimator)animator;
            String s = animator.getPropertyName();
            if("fillColor".equals(s) || "strokeColor".equals(s))
            {
                if(mArgbEvaluator == null)
                    mArgbEvaluator = new ArgbEvaluator();
                animator.setEvaluator(mArgbEvaluator);
            }
        }
    }

    public static boolean unregisterAnimationCallback(Drawable drawable, Animatable2Compat.AnimationCallback animationcallback)
    {
        while(drawable == null || animationcallback == null || !(drawable instanceof Animatable)) 
            return false;
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return unregisterPlatformCallback((AnimatedVectorDrawable)drawable, animationcallback);
        else
            return ((AnimatedVectorDrawableCompat)drawable).unregisterAnimationCallback(animationcallback);
    }

    private static boolean unregisterPlatformCallback(AnimatedVectorDrawable animatedvectordrawable, Animatable2Compat.AnimationCallback animationcallback)
    {
        return animatedvectordrawable.unregisterAnimationCallback(animationcallback.getPlatformCallback());
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        if(mDelegateDrawable != null)
            DrawableCompat.applyTheme(mDelegateDrawable, theme);
    }

    public boolean canApplyTheme()
    {
        if(mDelegateDrawable != null)
            return DrawableCompat.canApplyTheme(mDelegateDrawable);
        else
            return false;
    }

    public void clearAnimationCallbacks()
    {
        if(mDelegateDrawable != null)
        {
            ((AnimatedVectorDrawable)mDelegateDrawable).clearAnimationCallbacks();
        } else
        {
            removeAnimatorSetListener();
            if(mAnimationCallbacks != null)
            {
                mAnimationCallbacks.clear();
                return;
            }
        }
    }

    public volatile void clearColorFilter()
    {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas)
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.draw(canvas);
        } else
        {
            mAnimatedVectorState.mVectorDrawable.draw(canvas);
            if(mAnimatedVectorState.mAnimatorSet.isStarted())
            {
                invalidateSelf();
                return;
            }
        }
    }

    public int getAlpha()
    {
        if(mDelegateDrawable != null)
            return DrawableCompat.getAlpha(mDelegateDrawable);
        else
            return mAnimatedVectorState.mVectorDrawable.getAlpha();
    }

    public int getChangingConfigurations()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getChangingConfigurations();
        else
            return super.getChangingConfigurations() | mAnimatedVectorState.mChangingConfigurations;
    }

    public volatile ColorFilter getColorFilter()
    {
        return super.getColorFilter();
    }

    public android.graphics.drawable.Drawable.ConstantState getConstantState()
    {
        if(mDelegateDrawable != null && android.os.Build.VERSION.SDK_INT >= 24)
            return new AnimatedVectorDrawableDelegateState(mDelegateDrawable.getConstantState());
        else
            return null;
    }

    public volatile Drawable getCurrent()
    {
        return super.getCurrent();
    }

    public int getIntrinsicHeight()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getIntrinsicHeight();
        else
            return mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getIntrinsicWidth();
        else
            return mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
    }

    public volatile int getMinimumHeight()
    {
        return super.getMinimumHeight();
    }

    public volatile int getMinimumWidth()
    {
        return super.getMinimumWidth();
    }

    public int getOpacity()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getOpacity();
        else
            return mAnimatedVectorState.mVectorDrawable.getOpacity();
    }

    public volatile boolean getPadding(Rect rect)
    {
        return super.getPadding(rect);
    }

    public volatile int[] getState()
    {
        return super.getState();
    }

    public volatile Region getTransparentRegion()
    {
        return super.getTransparentRegion();
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        inflate(resources, xmlpullparser, attributeset, null);
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        int i;
        int k;
        if(mDelegateDrawable != null)
        {
            DrawableCompat.inflate(mDelegateDrawable, resources, xmlpullparser, attributeset, theme);
            return;
        }
        i = xmlpullparser.getEventType();
        k = xmlpullparser.getDepth();
_L2:
        Object obj;
        if(i == 1 || xmlpullparser.getDepth() < k + 1 && i == 3)
            break MISSING_BLOCK_LABEL_270;
        if(i == 2)
        {
            obj = xmlpullparser.getName();
            if(!"animated-vector".equals(obj))
                break; /* Loop/switch isn't completed */
            obj = TypedArrayUtils.obtainAttributes(resources, theme, attributeset, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE);
            i = ((TypedArray) (obj)).getResourceId(0, 0);
            if(i != 0)
            {
                VectorDrawableCompat vectordrawablecompat = VectorDrawableCompat.create(resources, i, theme);
                vectordrawablecompat.setAllowCaching(false);
                vectordrawablecompat.setCallback(mCallback);
                if(mAnimatedVectorState.mVectorDrawable != null)
                    mAnimatedVectorState.mVectorDrawable.setCallback(null);
                mAnimatedVectorState.mVectorDrawable = vectordrawablecompat;
            }
            ((TypedArray) (obj)).recycle();
        }
_L4:
        i = xmlpullparser.next();
        if(true) goto _L2; else goto _L1
_L1:
        if(!"target".equals(obj)) goto _L4; else goto _L3
_L3:
label0:
        {
            obj = resources.obtainAttributes(attributeset, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET);
            String s = ((TypedArray) (obj)).getString(0);
            int j = ((TypedArray) (obj)).getResourceId(1, 0);
            if(j != 0)
            {
                if(mContext == null)
                    break label0;
                setupAnimatorsForTarget(s, AnimatorInflaterCompat.loadAnimator(mContext, j));
            }
            ((TypedArray) (obj)).recycle();
        }
          goto _L4
        ((TypedArray) (obj)).recycle();
        throw new IllegalStateException("Context can't be null when inflating animators");
        mAnimatedVectorState.setupAnimatorSet();
        return;
    }

    public boolean isAutoMirrored()
    {
        if(mDelegateDrawable != null)
            return DrawableCompat.isAutoMirrored(mDelegateDrawable);
        else
            return mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
    }

    public boolean isRunning()
    {
        if(mDelegateDrawable != null)
            return ((AnimatedVectorDrawable)mDelegateDrawable).isRunning();
        else
            return mAnimatedVectorState.mAnimatorSet.isRunning();
    }

    public boolean isStateful()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.isStateful();
        else
            return mAnimatedVectorState.mVectorDrawable.isStateful();
    }

    public volatile void jumpToCurrentState()
    {
        super.jumpToCurrentState();
    }

    public Drawable mutate()
    {
        if(mDelegateDrawable != null)
            mDelegateDrawable.mutate();
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.setBounds(rect);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setBounds(rect);
            return;
        }
    }

    protected boolean onLevelChange(int i)
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.setLevel(i);
        else
            return mAnimatedVectorState.mVectorDrawable.setLevel(i);
    }

    protected boolean onStateChange(int ai[])
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.setState(ai);
        else
            return mAnimatedVectorState.mVectorDrawable.setState(ai);
    }

    public void registerAnimationCallback(Animatable2Compat.AnimationCallback animationcallback)
    {
        if(mDelegateDrawable != null)
            registerPlatformCallback((AnimatedVectorDrawable)mDelegateDrawable, animationcallback);
        else
        if(animationcallback != null)
        {
            if(mAnimationCallbacks == null)
                mAnimationCallbacks = new ArrayList();
            if(!mAnimationCallbacks.contains(animationcallback))
            {
                mAnimationCallbacks.add(animationcallback);
                if(mAnimatorListener == null)
                    mAnimatorListener = new AnimatorListenerAdapter() {

                        public void onAnimationEnd(Animator animator)
                        {
                            animator = new ArrayList(mAnimationCallbacks);
                            int j = animator.size();
                            for(int i = 0; i < j; i++)
                                ((Animatable2Compat.AnimationCallback)animator.get(i)).onAnimationEnd(AnimatedVectorDrawableCompat.this);

                        }

                        public void onAnimationStart(Animator animator)
                        {
                            animator = new ArrayList(mAnimationCallbacks);
                            int j = animator.size();
                            for(int i = 0; i < j; i++)
                                ((Animatable2Compat.AnimationCallback)animator.get(i)).onAnimationStart(AnimatedVectorDrawableCompat.this);

                        }

                        final AnimatedVectorDrawableCompat this$0;

            
            {
                this$0 = AnimatedVectorDrawableCompat.this;
                super();
            }
                    }
;
                mAnimatedVectorState.mAnimatorSet.addListener(mAnimatorListener);
                return;
            }
        }
    }

    public void setAlpha(int i)
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.setAlpha(i);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setAlpha(i);
            return;
        }
    }

    public void setAutoMirrored(boolean flag)
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.setAutoMirrored(mDelegateDrawable, flag);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setAutoMirrored(flag);
            return;
        }
    }

    public volatile void setChangingConfigurations(int i)
    {
        super.setChangingConfigurations(i);
    }

    public volatile void setColorFilter(int i, android.graphics.PorterDuff.Mode mode)
    {
        super.setColorFilter(i, mode);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.setColorFilter(colorfilter);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setColorFilter(colorfilter);
            return;
        }
    }

    public volatile void setFilterBitmap(boolean flag)
    {
        super.setFilterBitmap(flag);
    }

    public volatile void setHotspot(float f, float f1)
    {
        super.setHotspot(f, f1);
    }

    public volatile void setHotspotBounds(int i, int j, int k, int l)
    {
        super.setHotspotBounds(i, j, k, l);
    }

    public volatile boolean setState(int ai[])
    {
        return super.setState(ai);
    }

    public void setTint(int i)
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.setTint(mDelegateDrawable, i);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setTint(i);
            return;
        }
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.setTintList(mDelegateDrawable, colorstatelist);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setTintList(colorstatelist);
            return;
        }
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.setTintMode(mDelegateDrawable, mode);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setTintMode(mode);
            return;
        }
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        if(mDelegateDrawable != null)
        {
            return mDelegateDrawable.setVisible(flag, flag1);
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setVisible(flag, flag1);
            return super.setVisible(flag, flag1);
        }
    }

    public void start()
    {
        if(mDelegateDrawable != null)
            ((AnimatedVectorDrawable)mDelegateDrawable).start();
        else
        if(!mAnimatedVectorState.mAnimatorSet.isStarted())
        {
            mAnimatedVectorState.mAnimatorSet.start();
            invalidateSelf();
            return;
        }
    }

    public void stop()
    {
        if(mDelegateDrawable != null)
        {
            ((AnimatedVectorDrawable)mDelegateDrawable).stop();
            return;
        } else
        {
            mAnimatedVectorState.mAnimatorSet.end();
            return;
        }
    }

    public boolean unregisterAnimationCallback(Animatable2Compat.AnimationCallback animationcallback)
    {
        if(mDelegateDrawable != null)
            unregisterPlatformCallback((AnimatedVectorDrawable)mDelegateDrawable, animationcallback);
        boolean flag;
        if(mAnimationCallbacks == null || animationcallback == null)
        {
            flag = false;
        } else
        {
            boolean flag1 = mAnimationCallbacks.remove(animationcallback);
            flag = flag1;
            if(mAnimationCallbacks.size() == 0)
            {
                removeAnimatorSetListener();
                return flag1;
            }
        }
        return flag;
    }

    private static final String ANIMATED_VECTOR = "animated-vector";
    private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
    private static final String LOGTAG = "AnimatedVDCompat";
    private static final String TARGET = "target";
    private AnimatedVectorDrawableCompatState mAnimatedVectorState;
    private ArrayList mAnimationCallbacks;
    private android.animation.Animator.AnimatorListener mAnimatorListener;
    private ArgbEvaluator mArgbEvaluator;
    AnimatedVectorDrawableDelegateState mCachedConstantStateDelegate;
    final android.graphics.drawable.Drawable.Callback mCallback;
    private Context mContext;

}
