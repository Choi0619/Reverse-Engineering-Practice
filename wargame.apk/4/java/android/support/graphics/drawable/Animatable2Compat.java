// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.graphics.drawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

public interface Animatable2Compat
    extends Animatable
{
    public static abstract class AnimationCallback
    {

        android.graphics.drawable.Animatable2.AnimationCallback getPlatformCallback()
        {
            if(mPlatformCallback == null)
                mPlatformCallback = new android.graphics.drawable.Animatable2.AnimationCallback() {

                    public void onAnimationEnd(Drawable drawable)
                    {
                        AnimationCallback.this.onAnimationEnd(drawable);
                    }

                    public void onAnimationStart(Drawable drawable)
                    {
                        AnimationCallback.this.onAnimationStart(drawable);
                    }

                    final AnimationCallback this$0;

            
            {
                this$0 = AnimationCallback.this;
                super();
            }
                }
;
            return mPlatformCallback;
        }

        public void onAnimationEnd(Drawable drawable)
        {
        }

        public void onAnimationStart(Drawable drawable)
        {
        }

        android.graphics.drawable.Animatable2.AnimationCallback mPlatformCallback;

        public AnimationCallback()
        {
        }
    }


    public abstract void clearAnimationCallbacks();

    public abstract void registerAnimationCallback(AnimationCallback animationcallback);

    public abstract boolean unregisterAnimationCallback(AnimationCallback animationcallback);
}
