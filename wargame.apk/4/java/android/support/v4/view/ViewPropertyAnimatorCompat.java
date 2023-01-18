// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.animation.*;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorListener, ViewPropertyAnimatorUpdateListener

public final class ViewPropertyAnimatorCompat
{
    static class ViewPropertyAnimatorListenerApi14
        implements ViewPropertyAnimatorListener
    {

        public void onAnimationCancel(View view)
        {
            Object obj = view.getTag(0x7e000000);
            ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
            if(obj instanceof ViewPropertyAnimatorListener)
                viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
            if(viewpropertyanimatorlistener != null)
                viewpropertyanimatorlistener.onAnimationCancel(view);
        }

        public void onAnimationEnd(View view)
        {
            if(mVpa.mOldLayerType > -1)
            {
                view.setLayerType(mVpa.mOldLayerType, null);
                mVpa.mOldLayerType = -1;
            }
            if(android.os.Build.VERSION.SDK_INT >= 16 || !mAnimEndCalled)
            {
                if(mVpa.mEndAction != null)
                {
                    Runnable runnable = mVpa.mEndAction;
                    mVpa.mEndAction = null;
                    runnable.run();
                }
                Object obj = view.getTag(0x7e000000);
                ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
                if(obj instanceof ViewPropertyAnimatorListener)
                    viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
                if(viewpropertyanimatorlistener != null)
                    viewpropertyanimatorlistener.onAnimationEnd(view);
                mAnimEndCalled = true;
            }
        }

        public void onAnimationStart(View view)
        {
            mAnimEndCalled = false;
            if(mVpa.mOldLayerType > -1)
                view.setLayerType(2, null);
            if(mVpa.mStartAction != null)
            {
                Runnable runnable = mVpa.mStartAction;
                mVpa.mStartAction = null;
                runnable.run();
            }
            Object obj = view.getTag(0x7e000000);
            ViewPropertyAnimatorListener viewpropertyanimatorlistener = null;
            if(obj instanceof ViewPropertyAnimatorListener)
                viewpropertyanimatorlistener = (ViewPropertyAnimatorListener)obj;
            if(viewpropertyanimatorlistener != null)
                viewpropertyanimatorlistener.onAnimationStart(view);
        }

        boolean mAnimEndCalled;
        ViewPropertyAnimatorCompat mVpa;

        ViewPropertyAnimatorListenerApi14(ViewPropertyAnimatorCompat viewpropertyanimatorcompat)
        {
            mVpa = viewpropertyanimatorcompat;
        }
    }


    ViewPropertyAnimatorCompat(View view)
    {
        mStartAction = null;
        mEndAction = null;
        mOldLayerType = -1;
        mView = new WeakReference(view);
    }

    private void setListenerInternal(final View view, final ViewPropertyAnimatorListener listener)
    {
        if(listener != null)
        {
            view.animate().setListener(new AnimatorListenerAdapter() {

                public void onAnimationCancel(Animator animator)
                {
                    listener.onAnimationCancel(view);
                }

                public void onAnimationEnd(Animator animator)
                {
                    listener.onAnimationEnd(view);
                }

                public void onAnimationStart(Animator animator)
                {
                    listener.onAnimationStart(view);
                }

                final ViewPropertyAnimatorCompat this$0;
                final ViewPropertyAnimatorListener val$listener;
                final View val$view;

            
            {
                this$0 = ViewPropertyAnimatorCompat.this;
                listener = viewpropertyanimatorlistener;
                view = view1;
                super();
            }
            }
);
            return;
        } else
        {
            view.animate().setListener(null);
            return;
        }
    }

    public ViewPropertyAnimatorCompat alpha(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().alpha(f);
        return this;
    }

    public ViewPropertyAnimatorCompat alphaBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().alphaBy(f);
        return this;
    }

    public void cancel()
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().cancel();
    }

    public long getDuration()
    {
        View view = (View)mView.get();
        if(view != null)
            return view.animate().getDuration();
        else
            return 0L;
    }

    public Interpolator getInterpolator()
    {
        View view = (View)mView.get();
        if(view != null && android.os.Build.VERSION.SDK_INT >= 18)
            return (Interpolator)view.animate().getInterpolator();
        else
            return null;
    }

    public long getStartDelay()
    {
        View view = (View)mView.get();
        if(view != null)
            return view.animate().getStartDelay();
        else
            return 0L;
    }

    public ViewPropertyAnimatorCompat rotation(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().rotation(f);
        return this;
    }

    public ViewPropertyAnimatorCompat rotationBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().rotationBy(f);
        return this;
    }

    public ViewPropertyAnimatorCompat rotationX(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().rotationX(f);
        return this;
    }

    public ViewPropertyAnimatorCompat rotationXBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().rotationXBy(f);
        return this;
    }

    public ViewPropertyAnimatorCompat rotationY(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().rotationY(f);
        return this;
    }

    public ViewPropertyAnimatorCompat rotationYBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().rotationYBy(f);
        return this;
    }

    public ViewPropertyAnimatorCompat scaleX(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().scaleX(f);
        return this;
    }

    public ViewPropertyAnimatorCompat scaleXBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().scaleXBy(f);
        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().scaleY(f);
        return this;
    }

    public ViewPropertyAnimatorCompat scaleYBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().scaleYBy(f);
        return this;
    }

    public ViewPropertyAnimatorCompat setDuration(long l)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().setDuration(l);
        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator interpolator)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().setInterpolator(interpolator);
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener viewpropertyanimatorlistener)
    {
        View view;
label0:
        {
            view = (View)mView.get();
            if(view != null)
            {
                if(android.os.Build.VERSION.SDK_INT < 16)
                    break label0;
                setListenerInternal(view, viewpropertyanimatorlistener);
            }
            return this;
        }
        view.setTag(0x7e000000, viewpropertyanimatorlistener);
        setListenerInternal(view, new ViewPropertyAnimatorListenerApi14(this));
        return this;
    }

    public ViewPropertyAnimatorCompat setStartDelay(long l)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().setStartDelay(l);
        return this;
    }

    public ViewPropertyAnimatorCompat setUpdateListener(final ViewPropertyAnimatorUpdateListener listener)
    {
        final View view = (View)mView.get();
        if(view != null && android.os.Build.VERSION.SDK_INT >= 19)
        {
            android.animation.ValueAnimator.AnimatorUpdateListener animatorupdatelistener = null;
            if(listener != null)
                animatorupdatelistener = new android.animation.ValueAnimator.AnimatorUpdateListener() {

                    public void onAnimationUpdate(ValueAnimator valueanimator)
                    {
                        listener.onAnimationUpdate(view);
                    }

                    final ViewPropertyAnimatorCompat this$0;
                    final ViewPropertyAnimatorUpdateListener val$listener;
                    final View val$view;

            
            {
                this$0 = ViewPropertyAnimatorCompat.this;
                listener = viewpropertyanimatorupdatelistener;
                view = view1;
                super();
            }
                }
;
            view.animate().setUpdateListener(animatorupdatelistener);
        }
        return this;
    }

    public void start()
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().start();
    }

    public ViewPropertyAnimatorCompat translationX(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().translationX(f);
        return this;
    }

    public ViewPropertyAnimatorCompat translationXBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().translationXBy(f);
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().translationY(f);
        return this;
    }

    public ViewPropertyAnimatorCompat translationYBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().translationYBy(f);
        return this;
    }

    public ViewPropertyAnimatorCompat translationZ(float f)
    {
        View view = (View)mView.get();
        if(view != null && android.os.Build.VERSION.SDK_INT >= 21)
            view.animate().translationZ(f);
        return this;
    }

    public ViewPropertyAnimatorCompat translationZBy(float f)
    {
        View view = (View)mView.get();
        if(view != null && android.os.Build.VERSION.SDK_INT >= 21)
            view.animate().translationZBy(f);
        return this;
    }

    public ViewPropertyAnimatorCompat withEndAction(Runnable runnable)
    {
        View view;
label0:
        {
            view = (View)mView.get();
            if(view != null)
            {
                if(android.os.Build.VERSION.SDK_INT < 16)
                    break label0;
                view.animate().withEndAction(runnable);
            }
            return this;
        }
        setListenerInternal(view, new ViewPropertyAnimatorListenerApi14(this));
        mEndAction = runnable;
        return this;
    }

    public ViewPropertyAnimatorCompat withLayer()
    {
        View view;
label0:
        {
            view = (View)mView.get();
            if(view != null)
            {
                if(android.os.Build.VERSION.SDK_INT < 16)
                    break label0;
                view.animate().withLayer();
            }
            return this;
        }
        mOldLayerType = view.getLayerType();
        setListenerInternal(view, new ViewPropertyAnimatorListenerApi14(this));
        return this;
    }

    public ViewPropertyAnimatorCompat withStartAction(Runnable runnable)
    {
        View view;
label0:
        {
            view = (View)mView.get();
            if(view != null)
            {
                if(android.os.Build.VERSION.SDK_INT < 16)
                    break label0;
                view.animate().withStartAction(runnable);
            }
            return this;
        }
        setListenerInternal(view, new ViewPropertyAnimatorListenerApi14(this));
        mStartAction = runnable;
        return this;
    }

    public ViewPropertyAnimatorCompat x(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().x(f);
        return this;
    }

    public ViewPropertyAnimatorCompat xBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().xBy(f);
        return this;
    }

    public ViewPropertyAnimatorCompat y(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().y(f);
        return this;
    }

    public ViewPropertyAnimatorCompat yBy(float f)
    {
        View view = (View)mView.get();
        if(view != null)
            view.animate().yBy(f);
        return this;
    }

    public ViewPropertyAnimatorCompat z(float f)
    {
        View view = (View)mView.get();
        if(view != null && android.os.Build.VERSION.SDK_INT >= 21)
            view.animate().z(f);
        return this;
    }

    public ViewPropertyAnimatorCompat zBy(float f)
    {
        View view = (View)mView.get();
        if(view != null && android.os.Build.VERSION.SDK_INT >= 21)
            view.animate().zBy(f);
        return this;
    }

    static final int LISTENER_TAG_ID = 0x7e000000;
    private static final String TAG = "ViewAnimatorCompat";
    Runnable mEndAction;
    int mOldLayerType;
    Runnable mStartAction;
    private WeakReference mView;
}
