// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;

// Referenced classes of package android.support.v7.app:
//            AppCompatDialog, AlertController

public class AlertDialog extends AppCompatDialog
    implements DialogInterface
{
    public static class Builder
    {

        public AlertDialog create()
        {
            AlertDialog alertdialog = new AlertDialog(P.mContext, mTheme);
            P.apply(alertdialog.mAlert);
            alertdialog.setCancelable(P.mCancelable);
            if(P.mCancelable)
                alertdialog.setCanceledOnTouchOutside(true);
            alertdialog.setOnCancelListener(P.mOnCancelListener);
            alertdialog.setOnDismissListener(P.mOnDismissListener);
            if(P.mOnKeyListener != null)
                alertdialog.setOnKeyListener(P.mOnKeyListener);
            return alertdialog;
        }

        public Context getContext()
        {
            return P.mContext;
        }

        public Builder setAdapter(ListAdapter listadapter, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mAdapter = listadapter;
            P.mOnClickListener = onclicklistener;
            return this;
        }

        public Builder setCancelable(boolean flag)
        {
            P.mCancelable = flag;
            return this;
        }

        public Builder setCursor(Cursor cursor, android.content.DialogInterface.OnClickListener onclicklistener, String s)
        {
            P.mCursor = cursor;
            P.mLabelColumn = s;
            P.mOnClickListener = onclicklistener;
            return this;
        }

        public Builder setCustomTitle(View view)
        {
            P.mCustomTitleView = view;
            return this;
        }

        public Builder setIcon(int i)
        {
            P.mIconId = i;
            return this;
        }

        public Builder setIcon(Drawable drawable)
        {
            P.mIcon = drawable;
            return this;
        }

        public Builder setIconAttribute(int i)
        {
            TypedValue typedvalue = new TypedValue();
            P.mContext.getTheme().resolveAttribute(i, typedvalue, true);
            P.mIconId = typedvalue.resourceId;
            return this;
        }

        public Builder setInverseBackgroundForced(boolean flag)
        {
            P.mForceInverseBackground = flag;
            return this;
        }

        public Builder setItems(int i, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mItems = P.mContext.getResources().getTextArray(i);
            P.mOnClickListener = onclicklistener;
            return this;
        }

        public Builder setItems(CharSequence acharsequence[], android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mItems = acharsequence;
            P.mOnClickListener = onclicklistener;
            return this;
        }

        public Builder setMessage(int i)
        {
            P.mMessage = P.mContext.getText(i);
            return this;
        }

        public Builder setMessage(CharSequence charsequence)
        {
            P.mMessage = charsequence;
            return this;
        }

        public Builder setMultiChoiceItems(int i, boolean aflag[], android.content.DialogInterface.OnMultiChoiceClickListener onmultichoiceclicklistener)
        {
            P.mItems = P.mContext.getResources().getTextArray(i);
            P.mOnCheckboxClickListener = onmultichoiceclicklistener;
            P.mCheckedItems = aflag;
            P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String s, String s1, android.content.DialogInterface.OnMultiChoiceClickListener onmultichoiceclicklistener)
        {
            P.mCursor = cursor;
            P.mOnCheckboxClickListener = onmultichoiceclicklistener;
            P.mIsCheckedColumn = s;
            P.mLabelColumn = s1;
            P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence acharsequence[], boolean aflag[], android.content.DialogInterface.OnMultiChoiceClickListener onmultichoiceclicklistener)
        {
            P.mItems = acharsequence;
            P.mOnCheckboxClickListener = onmultichoiceclicklistener;
            P.mCheckedItems = aflag;
            P.mIsMultiChoice = true;
            return this;
        }

        public Builder setNegativeButton(int i, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mNegativeButtonText = P.mContext.getText(i);
            P.mNegativeButtonListener = onclicklistener;
            return this;
        }

        public Builder setNegativeButton(CharSequence charsequence, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mNegativeButtonText = charsequence;
            P.mNegativeButtonListener = onclicklistener;
            return this;
        }

        public Builder setNeutralButton(int i, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mNeutralButtonText = P.mContext.getText(i);
            P.mNeutralButtonListener = onclicklistener;
            return this;
        }

        public Builder setNeutralButton(CharSequence charsequence, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mNeutralButtonText = charsequence;
            P.mNeutralButtonListener = onclicklistener;
            return this;
        }

        public Builder setOnCancelListener(android.content.DialogInterface.OnCancelListener oncancellistener)
        {
            P.mOnCancelListener = oncancellistener;
            return this;
        }

        public Builder setOnDismissListener(android.content.DialogInterface.OnDismissListener ondismisslistener)
        {
            P.mOnDismissListener = ondismisslistener;
            return this;
        }

        public Builder setOnItemSelectedListener(android.widget.AdapterView.OnItemSelectedListener onitemselectedlistener)
        {
            P.mOnItemSelectedListener = onitemselectedlistener;
            return this;
        }

        public Builder setOnKeyListener(android.content.DialogInterface.OnKeyListener onkeylistener)
        {
            P.mOnKeyListener = onkeylistener;
            return this;
        }

        public Builder setPositiveButton(int i, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mPositiveButtonText = P.mContext.getText(i);
            P.mPositiveButtonListener = onclicklistener;
            return this;
        }

        public Builder setPositiveButton(CharSequence charsequence, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mPositiveButtonText = charsequence;
            P.mPositiveButtonListener = onclicklistener;
            return this;
        }

        public Builder setRecycleOnMeasureEnabled(boolean flag)
        {
            P.mRecycleOnMeasure = flag;
            return this;
        }

        public Builder setSingleChoiceItems(int i, int j, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mItems = P.mContext.getResources().getTextArray(i);
            P.mOnClickListener = onclicklistener;
            P.mCheckedItem = j;
            P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int i, String s, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mCursor = cursor;
            P.mOnClickListener = onclicklistener;
            P.mCheckedItem = i;
            P.mLabelColumn = s;
            P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter listadapter, int i, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mAdapter = listadapter;
            P.mOnClickListener = onclicklistener;
            P.mCheckedItem = i;
            P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence acharsequence[], int i, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            P.mItems = acharsequence;
            P.mOnClickListener = onclicklistener;
            P.mCheckedItem = i;
            P.mIsSingleChoice = true;
            return this;
        }

        public Builder setTitle(int i)
        {
            P.mTitle = P.mContext.getText(i);
            return this;
        }

        public Builder setTitle(CharSequence charsequence)
        {
            P.mTitle = charsequence;
            return this;
        }

        public Builder setView(int i)
        {
            P.mView = null;
            P.mViewLayoutResId = i;
            P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view)
        {
            P.mView = view;
            P.mViewLayoutResId = 0;
            P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view, int i, int j, int k, int l)
        {
            P.mView = view;
            P.mViewLayoutResId = 0;
            P.mViewSpacingSpecified = true;
            P.mViewSpacingLeft = i;
            P.mViewSpacingTop = j;
            P.mViewSpacingRight = k;
            P.mViewSpacingBottom = l;
            return this;
        }

        public AlertDialog show()
        {
            AlertDialog alertdialog = create();
            alertdialog.show();
            return alertdialog;
        }

        private final AlertController.AlertParams P;
        private final int mTheme;

        public Builder(Context context)
        {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public Builder(Context context, int i)
        {
            P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, i)));
            mTheme = i;
        }
    }


    protected AlertDialog(Context context)
    {
        this(context, 0);
    }

    protected AlertDialog(Context context, int i)
    {
        super(context, resolveDialogTheme(context, i));
        mAlert = new AlertController(getContext(), this, getWindow());
    }

    protected AlertDialog(Context context, boolean flag, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        this(context, 0);
        setCancelable(flag);
        setOnCancelListener(oncancellistener);
    }

    static int resolveDialogTheme(Context context, int i)
    {
        if((i >>> 24 & 0xff) >= 1)
        {
            return i;
        } else
        {
            TypedValue typedvalue = new TypedValue();
            context.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.alertDialogTheme, typedvalue, true);
            return typedvalue.resourceId;
        }
    }

    public Button getButton(int i)
    {
        return mAlert.getButton(i);
    }

    public ListView getListView()
    {
        return mAlert.getListView();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mAlert.installContent();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(mAlert.onKeyDown(i, keyevent))
            return true;
        else
            return super.onKeyDown(i, keyevent);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(mAlert.onKeyUp(i, keyevent))
            return true;
        else
            return super.onKeyUp(i, keyevent);
    }

    public void setButton(int i, CharSequence charsequence, android.content.DialogInterface.OnClickListener onclicklistener)
    {
        mAlert.setButton(i, charsequence, onclicklistener, null);
    }

    public void setButton(int i, CharSequence charsequence, Message message)
    {
        mAlert.setButton(i, charsequence, null, message);
    }

    void setButtonPanelLayoutHint(int i)
    {
        mAlert.setButtonPanelLayoutHint(i);
    }

    public void setCustomTitle(View view)
    {
        mAlert.setCustomTitle(view);
    }

    public void setIcon(int i)
    {
        mAlert.setIcon(i);
    }

    public void setIcon(Drawable drawable)
    {
        mAlert.setIcon(drawable);
    }

    public void setIconAttribute(int i)
    {
        TypedValue typedvalue = new TypedValue();
        getContext().getTheme().resolveAttribute(i, typedvalue, true);
        mAlert.setIcon(typedvalue.resourceId);
    }

    public void setMessage(CharSequence charsequence)
    {
        mAlert.setMessage(charsequence);
    }

    public void setTitle(CharSequence charsequence)
    {
        super.setTitle(charsequence);
        mAlert.setTitle(charsequence);
    }

    public void setView(View view)
    {
        mAlert.setView(view);
    }

    public void setView(View view, int i, int j, int k, int l)
    {
        mAlert.setView(view, i, j, k, l);
    }

    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;
}
