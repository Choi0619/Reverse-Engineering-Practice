// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import java.lang.reflect.Method;

public class ActionBarDrawerToggle
    implements android.support.v4.widget.DrawerLayout.DrawerListener
{
    public static interface Delegate
    {

        public abstract Drawable getThemeUpIndicator();

        public abstract void setActionBarDescription(int i);

        public abstract void setActionBarUpIndicator(Drawable drawable, int i);
    }

    public static interface DelegateProvider
    {

        public abstract Delegate getDrawerToggleDelegate();
    }

    private static class SetIndicatorInfo
    {

        Method mSetHomeActionContentDescription;
        Method mSetHomeAsUpIndicator;
        ImageView mUpIndicatorView;

        SetIndicatorInfo(Activity activity)
        {
            mSetHomeAsUpIndicator = android/app/ActionBar.getDeclaredMethod("setHomeAsUpIndicator", new Class[] {
                android/graphics/drawable/Drawable
            });
            mSetHomeActionContentDescription = android/app/ActionBar.getDeclaredMethod("setHomeActionContentDescription", new Class[] {
                Integer.TYPE
            });
_L1:
            return;
            NoSuchMethodException nosuchmethodexception;
            nosuchmethodexception;
            activity = activity.findViewById(0x102002c);
            if(activity != null)
            {
                Object obj = (ViewGroup)activity.getParent();
                if(((ViewGroup) (obj)).getChildCount() == 2)
                {
                    activity = ((ViewGroup) (obj)).getChildAt(0);
                    obj = ((ViewGroup) (obj)).getChildAt(1);
                    if(activity.getId() == 0x102002c)
                        activity = ((Activity) (obj));
                    if(activity instanceof ImageView)
                    {
                        mUpIndicatorView = (ImageView)activity;
                        return;
                    }
                }
            }
              goto _L1
        }
    }

    private class SlideDrawable extends InsetDrawable
        implements android.graphics.drawable.Drawable.Callback
    {

        public void draw(Canvas canvas)
        {
            int i = 1;
            copyBounds(mTmpRect);
            canvas.save();
            boolean flag;
            int j;
            if(ViewCompat.getLayoutDirection(mActivity.getWindow().getDecorView()) == 1)
                flag = true;
            else
                flag = false;
            if(flag)
                i = -1;
            j = mTmpRect.width();
            canvas.translate(-mOffset * (float)j * mPosition * (float)i, 0.0F);
            if(flag && !mHasMirroring)
            {
                canvas.translate(j, 0.0F);
                canvas.scale(-1F, 1.0F);
            }
            super.draw(canvas);
            canvas.restore();
        }

        public float getPosition()
        {
            return mPosition;
        }

        public void setOffset(float f)
        {
            mOffset = f;
            invalidateSelf();
        }

        public void setPosition(float f)
        {
            mPosition = f;
            invalidateSelf();
        }

        private final boolean mHasMirroring;
        private float mOffset;
        private float mPosition;
        private final Rect mTmpRect = new Rect();
        final ActionBarDrawerToggle this$0;

        SlideDrawable(Drawable drawable)
        {
            boolean flag = false;
            this$0 = ActionBarDrawerToggle.this;
            super(drawable, 0);
            if(android.os.Build.VERSION.SDK_INT > 18)
                flag = true;
            mHasMirroring = flag;
        }
    }


    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerlayout, int i, int j, int k)
    {
        boolean flag;
        if(!assumeMaterial(activity))
            flag = true;
        else
            flag = false;
        this(activity, drawerlayout, flag, i, j, k);
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerlayout, boolean flag, int i, int j, int k)
    {
        mDrawerIndicatorEnabled = true;
        mActivity = activity;
        float f;
        if(activity instanceof DelegateProvider)
            mActivityImpl = ((DelegateProvider)activity).getDrawerToggleDelegate();
        else
            mActivityImpl = null;
        mDrawerLayout = drawerlayout;
        mDrawerImageResource = i;
        mOpenDrawerContentDescRes = j;
        mCloseDrawerContentDescRes = k;
        mHomeAsUpIndicator = getThemeUpIndicator();
        mDrawerImage = ContextCompat.getDrawable(activity, i);
        mSlider = new SlideDrawable(mDrawerImage);
        activity = mSlider;
        if(flag)
            f = 0.3333333F;
        else
            f = 0.0F;
        activity.setOffset(f);
    }

    private static boolean assumeMaterial(Context context)
    {
        return context.getApplicationInfo().targetSdkVersion >= 21 && android.os.Build.VERSION.SDK_INT >= 21;
    }

    private Drawable getThemeUpIndicator()
    {
        if(mActivityImpl != null)
            return mActivityImpl.getThemeUpIndicator();
        if(android.os.Build.VERSION.SDK_INT >= 18)
        {
            Object obj = mActivity.getActionBar();
            Drawable drawable;
            if(obj != null)
                obj = ((ActionBar) (obj)).getThemedContext();
            else
                obj = mActivity;
            obj = ((Context) (obj)).obtainStyledAttributes(null, THEME_ATTRS, 0x10102ce, 0);
            drawable = ((TypedArray) (obj)).getDrawable(0);
            ((TypedArray) (obj)).recycle();
            return drawable;
        } else
        {
            TypedArray typedarray = mActivity.obtainStyledAttributes(THEME_ATTRS);
            Drawable drawable1 = typedarray.getDrawable(0);
            typedarray.recycle();
            return drawable1;
        }
    }

    private void setActionBarDescription(int i)
    {
        if(mActivityImpl == null) goto _L2; else goto _L1
_L1:
        mActivityImpl.setActionBarDescription(i);
_L4:
        return;
_L2:
        if(android.os.Build.VERSION.SDK_INT < 18)
            break; /* Loop/switch isn't completed */
        ActionBar actionbar = mActivity.getActionBar();
        if(actionbar != null)
        {
            actionbar.setHomeActionContentDescription(i);
            return;
        }
        if(true) goto _L4; else goto _L3
_L3:
        if(mSetIndicatorInfo == null)
            mSetIndicatorInfo = new SetIndicatorInfo(mActivity);
        if(mSetIndicatorInfo.mSetHomeAsUpIndicator != null)
        {
            try
            {
                ActionBar actionbar1 = mActivity.getActionBar();
                mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(actionbar1, new Object[] {
                    Integer.valueOf(i)
                });
                actionbar1.setSubtitle(actionbar1.getSubtitle());
                return;
            }
            catch(Exception exception)
            {
                Log.w("ActionBarDrawerToggle", "Couldn't set content description via JB-MR2 API", exception);
            }
            return;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    private void setActionBarUpIndicator(Drawable drawable, int i)
    {
        if(mActivityImpl != null)
            mActivityImpl.setActionBarUpIndicator(drawable, i);
        else
        if(android.os.Build.VERSION.SDK_INT >= 18)
        {
            ActionBar actionbar = mActivity.getActionBar();
            if(actionbar != null)
            {
                actionbar.setHomeAsUpIndicator(drawable);
                actionbar.setHomeActionContentDescription(i);
                return;
            }
        } else
        {
            if(mSetIndicatorInfo == null)
                mSetIndicatorInfo = new SetIndicatorInfo(mActivity);
            if(mSetIndicatorInfo.mSetHomeAsUpIndicator != null)
            {
                try
                {
                    ActionBar actionbar1 = mActivity.getActionBar();
                    mSetIndicatorInfo.mSetHomeAsUpIndicator.invoke(actionbar1, new Object[] {
                        drawable
                    });
                    mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(actionbar1, new Object[] {
                        Integer.valueOf(i)
                    });
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(Drawable drawable)
                {
                    Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator via JB-MR2 API", drawable);
                }
                return;
            }
            if(mSetIndicatorInfo.mUpIndicatorView != null)
            {
                mSetIndicatorInfo.mUpIndicatorView.setImageDrawable(drawable);
                return;
            } else
            {
                Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator");
                return;
            }
        }
    }

    public boolean isDrawerIndicatorEnabled()
    {
        return mDrawerIndicatorEnabled;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        if(!mHasCustomUpIndicator)
            mHomeAsUpIndicator = getThemeUpIndicator();
        mDrawerImage = ContextCompat.getDrawable(mActivity, mDrawerImageResource);
        syncState();
    }

    public void onDrawerClosed(View view)
    {
        mSlider.setPosition(0.0F);
        if(mDrawerIndicatorEnabled)
            setActionBarDescription(mOpenDrawerContentDescRes);
    }

    public void onDrawerOpened(View view)
    {
        mSlider.setPosition(1.0F);
        if(mDrawerIndicatorEnabled)
            setActionBarDescription(mCloseDrawerContentDescRes);
    }

    public void onDrawerSlide(View view, float f)
    {
        float f1 = mSlider.getPosition();
        if(f > 0.5F)
            f = Math.max(f1, Math.max(0.0F, f - 0.5F) * 2.0F);
        else
            f = Math.min(f1, f * 2.0F);
        mSlider.setPosition(f);
    }

    public void onDrawerStateChanged(int i)
    {
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if(menuitem != null && menuitem.getItemId() == 0x102002c && mDrawerIndicatorEnabled)
        {
            if(mDrawerLayout.isDrawerVisible(0x800003))
                mDrawerLayout.closeDrawer(0x800003);
            else
                mDrawerLayout.openDrawer(0x800003);
            return true;
        } else
        {
            return false;
        }
    }

    public void setDrawerIndicatorEnabled(boolean flag)
    {
        if(flag != mDrawerIndicatorEnabled)
        {
            if(flag)
            {
                SlideDrawable slidedrawable = mSlider;
                int i;
                if(mDrawerLayout.isDrawerOpen(0x800003))
                    i = mCloseDrawerContentDescRes;
                else
                    i = mOpenDrawerContentDescRes;
                setActionBarUpIndicator(slidedrawable, i);
            } else
            {
                setActionBarUpIndicator(mHomeAsUpIndicator, 0);
            }
            mDrawerIndicatorEnabled = flag;
        }
    }

    public void setHomeAsUpIndicator(int i)
    {
        Drawable drawable = null;
        if(i != 0)
            drawable = ContextCompat.getDrawable(mActivity, i);
        setHomeAsUpIndicator(drawable);
    }

    public void setHomeAsUpIndicator(Drawable drawable)
    {
        if(drawable == null)
        {
            mHomeAsUpIndicator = getThemeUpIndicator();
            mHasCustomUpIndicator = false;
        } else
        {
            mHomeAsUpIndicator = drawable;
            mHasCustomUpIndicator = true;
        }
        if(!mDrawerIndicatorEnabled)
            setActionBarUpIndicator(mHomeAsUpIndicator, 0);
    }

    public void syncState()
    {
        if(mDrawerLayout.isDrawerOpen(0x800003))
            mSlider.setPosition(1.0F);
        else
            mSlider.setPosition(0.0F);
        if(mDrawerIndicatorEnabled)
        {
            SlideDrawable slidedrawable = mSlider;
            int i;
            if(mDrawerLayout.isDrawerOpen(0x800003))
                i = mCloseDrawerContentDescRes;
            else
                i = mOpenDrawerContentDescRes;
            setActionBarUpIndicator(slidedrawable, i);
        }
    }

    private static final int ID_HOME = 0x102002c;
    private static final String TAG = "ActionBarDrawerToggle";
    private static final int THEME_ATTRS[] = {
        0x101030b
    };
    private static final float TOGGLE_DRAWABLE_OFFSET = 0.3333333F;
    final Activity mActivity;
    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private Drawable mDrawerImage;
    private final int mDrawerImageResource;
    private boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private SetIndicatorInfo mSetIndicatorInfo;
    private SlideDrawable mSlider;

}
