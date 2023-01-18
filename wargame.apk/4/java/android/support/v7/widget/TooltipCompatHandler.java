// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.view.accessibility.AccessibilityManager;

// Referenced classes of package android.support.v7.widget:
//            TooltipPopup

class TooltipCompatHandler
    implements android.view.View.OnLongClickListener, android.view.View.OnHoverListener, android.view.View.OnAttachStateChangeListener
{

    private TooltipCompatHandler(View view, CharSequence charsequence)
    {
        mAnchor = view;
        mTooltipText = charsequence;
        mAnchor.setOnLongClickListener(this);
        mAnchor.setOnHoverListener(this);
    }

    private void hide()
    {
        if(sActiveHandler == this)
        {
            sActiveHandler = null;
            if(mPopup != null)
            {
                mPopup.hide();
                mPopup = null;
                mAnchor.removeOnAttachStateChangeListener(this);
            } else
            {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        mAnchor.removeCallbacks(mShowRunnable);
        mAnchor.removeCallbacks(mHideRunnable);
    }

    public static void setTooltipText(View view, CharSequence charsequence)
    {
        if(TextUtils.isEmpty(charsequence))
        {
            if(sActiveHandler != null && sActiveHandler.mAnchor == view)
                sActiveHandler.hide();
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        } else
        {
            new TooltipCompatHandler(view, charsequence);
            return;
        }
    }

    private void show(boolean flag)
    {
        if(!ViewCompat.isAttachedToWindow(mAnchor))
            return;
        if(sActiveHandler != null)
            sActiveHandler.hide();
        sActiveHandler = this;
        mFromTouch = flag;
        mPopup = new TooltipPopup(mAnchor.getContext());
        mPopup.show(mAnchor, mAnchorX, mAnchorY, mFromTouch, mTooltipText);
        mAnchor.addOnAttachStateChangeListener(this);
        long l;
        if(mFromTouch)
            l = 2500L;
        else
        if((ViewCompat.getWindowSystemUiVisibility(mAnchor) & 1) == 1)
            l = 3000L - (long)ViewConfiguration.getLongPressTimeout();
        else
            l = 15000L - (long)ViewConfiguration.getLongPressTimeout();
        mAnchor.removeCallbacks(mHideRunnable);
        mAnchor.postDelayed(mHideRunnable, l);
    }

    public boolean onHover(View view, MotionEvent motionevent)
    {
        if(mPopup == null || !mFromTouch) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if((view = (AccessibilityManager)mAnchor.getContext().getSystemService("accessibility")).isEnabled() && view.isTouchExplorationEnabled()) goto _L1; else goto _L3
_L3:
        switch(motionevent.getAction())
        {
        case 8: // '\b'
        case 9: // '\t'
        default:
            return false;

        case 7: // '\007'
            if(mAnchor.isEnabled() && mPopup == null)
            {
                mAnchorX = (int)motionevent.getX();
                mAnchorY = (int)motionevent.getY();
                mAnchor.removeCallbacks(mShowRunnable);
                mAnchor.postDelayed(mShowRunnable, ViewConfiguration.getLongPressTimeout());
                return false;
            }
            break;

        case 10: // '\n'
            hide();
            return false;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public boolean onLongClick(View view)
    {
        mAnchorX = view.getWidth() / 2;
        mAnchorY = view.getHeight() / 2;
        show(true);
        return true;
    }

    public void onViewAttachedToWindow(View view)
    {
    }

    public void onViewDetachedFromWindow(View view)
    {
        hide();
    }

    private static final long HOVER_HIDE_TIMEOUT_MS = 15000L;
    private static final long HOVER_HIDE_TIMEOUT_SHORT_MS = 3000L;
    private static final long LONG_CLICK_HIDE_TIMEOUT_MS = 2500L;
    private static final String TAG = "TooltipCompatHandler";
    private static TooltipCompatHandler sActiveHandler;
    private final View mAnchor;
    private int mAnchorX;
    private int mAnchorY;
    private boolean mFromTouch;
    private final Runnable mHideRunnable = new Runnable() {

        public void run()
        {
            hide();
        }

        final TooltipCompatHandler this$0;

            
            {
                this$0 = TooltipCompatHandler.this;
                super();
            }
    }
;
    private TooltipPopup mPopup;
    private final Runnable mShowRunnable = new Runnable() {

        public void run()
        {
            show(false);
        }

        final TooltipCompatHandler this$0;

            
            {
                this$0 = TooltipCompatHandler.this;
                super();
            }
    }
;
    private final CharSequence mTooltipText;


}
