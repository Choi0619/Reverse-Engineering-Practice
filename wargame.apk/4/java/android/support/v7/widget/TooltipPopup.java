// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.widget.TextView;

class TooltipPopup
{

    TooltipPopup(Context context)
    {
        mContext = context;
        mContentView = LayoutInflater.from(mContext).inflate(android.support.v7.appcompat.R.layout.tooltip, null);
        mMessageView = (TextView)mContentView.findViewById(android.support.v7.appcompat.R.id.message);
        mLayoutParams.setTitle(getClass().getSimpleName());
        mLayoutParams.packageName = mContext.getPackageName();
        mLayoutParams.type = 1002;
        mLayoutParams.width = -2;
        mLayoutParams.height = -2;
        mLayoutParams.format = -3;
        mLayoutParams.windowAnimations = android.support.v7.appcompat.R.style.Animation_AppCompat_Tooltip;
        mLayoutParams.flags = 24;
    }

    private void computePosition(View view, int i, int j, boolean flag, android.view.WindowManager.LayoutParams layoutparams)
    {
        int k = mContext.getResources().getDimensionPixelOffset(android.support.v7.appcompat.R.dimen.tooltip_precise_anchor_threshold);
        int i1;
        int k1;
        Object obj;
        if(view.getWidth() < k)
            i = view.getWidth() / 2;
        if(view.getHeight() >= k)
        {
            int l = mContext.getResources().getDimensionPixelOffset(android.support.v7.appcompat.R.dimen.tooltip_precise_anchor_extra_offset);
            k = j + l;
            l = j - l;
            j = k;
            k = l;
        } else
        {
            j = view.getHeight();
            k = 0;
        }
        layoutparams.gravity = 49;
        obj = mContext.getResources();
        if(flag)
            i1 = android.support.v7.appcompat.R.dimen.tooltip_y_offset_touch;
        else
            i1 = android.support.v7.appcompat.R.dimen.tooltip_y_offset_non_touch;
        k1 = ((Resources) (obj)).getDimensionPixelOffset(i1);
        obj = getAppRootView(view);
        if(obj == null)
        {
            Log.e("TooltipPopup", "Cannot find app view");
            return;
        }
        ((View) (obj)).getWindowVisibleDisplayFrame(mTmpDisplayFrame);
        if(mTmpDisplayFrame.left < 0 && mTmpDisplayFrame.top < 0)
        {
            Object obj1 = mContext.getResources();
            int j1 = ((Resources) (obj1)).getIdentifier("status_bar_height", "dimen", "android");
            if(j1 != 0)
                j1 = ((Resources) (obj1)).getDimensionPixelSize(j1);
            else
                j1 = 0;
            obj1 = ((Resources) (obj1)).getDisplayMetrics();
            mTmpDisplayFrame.set(0, j1, ((DisplayMetrics) (obj1)).widthPixels, ((DisplayMetrics) (obj1)).heightPixels);
        }
        ((View) (obj)).getLocationOnScreen(mTmpAppPos);
        view.getLocationOnScreen(mTmpAnchorPos);
        view = mTmpAnchorPos;
        view[0] = view[0] - mTmpAppPos[0];
        view = mTmpAnchorPos;
        view[1] = view[1] - mTmpAppPos[1];
        layoutparams.x = (mTmpAnchorPos[0] + i) - mTmpDisplayFrame.width() / 2;
        i = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        mContentView.measure(i, i);
        i = mContentView.getMeasuredHeight();
        k = (mTmpAnchorPos[1] + k) - k1 - i;
        j = mTmpAnchorPos[1] + j + k1;
        if(flag)
            if(k >= 0)
            {
                layoutparams.y = k;
                return;
            } else
            {
                layoutparams.y = j;
                return;
            }
        if(j + i <= mTmpDisplayFrame.height())
        {
            layoutparams.y = j;
            return;
        } else
        {
            layoutparams.y = k;
            return;
        }
    }

    private static View getAppRootView(View view)
    {
        for(Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext())
            if(context instanceof Activity)
                return ((Activity)context).getWindow().getDecorView();

        return view.getRootView();
    }

    void hide()
    {
        if(!isShowing())
        {
            return;
        } else
        {
            ((WindowManager)mContext.getSystemService("window")).removeView(mContentView);
            return;
        }
    }

    boolean isShowing()
    {
        return mContentView.getParent() != null;
    }

    void show(View view, int i, int j, boolean flag, CharSequence charsequence)
    {
        if(isShowing())
            hide();
        mMessageView.setText(charsequence);
        computePosition(view, i, j, flag, mLayoutParams);
        ((WindowManager)mContext.getSystemService("window")).addView(mContentView, mLayoutParams);
    }

    private static final String TAG = "TooltipPopup";
    private final View mContentView;
    private final Context mContext;
    private final android.view.WindowManager.LayoutParams mLayoutParams = new android.view.WindowManager.LayoutParams();
    private final TextView mMessageView;
    private final int mTmpAnchorPos[] = new int[2];
    private final int mTmpAppPos[] = new int[2];
    private final Rect mTmpDisplayFrame = new Rect();
}
