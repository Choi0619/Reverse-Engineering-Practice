// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray, AppCompatBackgroundHelper, ForwardingListener, ThemedSpinnerAdapter, 
//            ListPopupWindow, ViewUtils

public class AppCompatSpinner extends Spinner
    implements TintableBackgroundView
{
    private static class DropDownAdapter
        implements ListAdapter, SpinnerAdapter
    {

        public boolean areAllItemsEnabled()
        {
            ListAdapter listadapter = mListAdapter;
            if(listadapter != null)
                return listadapter.areAllItemsEnabled();
            else
                return true;
        }

        public int getCount()
        {
            if(mAdapter == null)
                return 0;
            else
                return mAdapter.getCount();
        }

        public View getDropDownView(int i, View view, ViewGroup viewgroup)
        {
            if(mAdapter == null)
                return null;
            else
                return mAdapter.getDropDownView(i, view, viewgroup);
        }

        public Object getItem(int i)
        {
            if(mAdapter == null)
                return null;
            else
                return mAdapter.getItem(i);
        }

        public long getItemId(int i)
        {
            if(mAdapter == null)
                return -1L;
            else
                return mAdapter.getItemId(i);
        }

        public int getItemViewType(int i)
        {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            return getDropDownView(i, view, viewgroup);
        }

        public int getViewTypeCount()
        {
            return 1;
        }

        public boolean hasStableIds()
        {
            return mAdapter != null && mAdapter.hasStableIds();
        }

        public boolean isEmpty()
        {
            return getCount() == 0;
        }

        public boolean isEnabled(int i)
        {
            ListAdapter listadapter = mListAdapter;
            if(listadapter != null)
                return listadapter.isEnabled(i);
            else
                return true;
        }

        public void registerDataSetObserver(DataSetObserver datasetobserver)
        {
            if(mAdapter != null)
                mAdapter.registerDataSetObserver(datasetobserver);
        }

        public void unregisterDataSetObserver(DataSetObserver datasetobserver)
        {
            if(mAdapter != null)
                mAdapter.unregisterDataSetObserver(datasetobserver);
        }

        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(SpinnerAdapter spinneradapter, android.content.res.Resources.Theme theme)
        {
            mAdapter = spinneradapter;
            if(spinneradapter instanceof ListAdapter)
                mListAdapter = (ListAdapter)spinneradapter;
            if(theme != null)
                if(android.os.Build.VERSION.SDK_INT >= 23 && (spinneradapter instanceof ThemedSpinnerAdapter))
                {
                    spinneradapter = (ThemedSpinnerAdapter)spinneradapter;
                    if(spinneradapter.getDropDownViewTheme() != theme)
                        spinneradapter.setDropDownViewTheme(theme);
                } else
                if(spinneradapter instanceof android.support.v7.widget.ThemedSpinnerAdapter)
                {
                    spinneradapter = (android.support.v7.widget.ThemedSpinnerAdapter)spinneradapter;
                    if(spinneradapter.getDropDownViewTheme() == null)
                    {
                        spinneradapter.setDropDownViewTheme(theme);
                        return;
                    }
                }
        }
    }

    private class DropdownPopup extends ListPopupWindow
    {

        void computeContentWidth()
        {
            Drawable drawable = getBackground();
            int i = 0;
            int i1;
            int j1;
            int k1;
            if(drawable != null)
            {
                drawable.getPadding(mTempRect);
                int j;
                int k;
                int l;
                if(ViewUtils.isLayoutRtl(AppCompatSpinner.this))
                    i = mTempRect.right;
                else
                    i = -mTempRect.left;
            } else
            {
                Rect rect = mTempRect;
                mTempRect.right = 0;
                rect.left = 0;
            }
            i1 = getPaddingLeft();
            j1 = getPaddingRight();
            k1 = getWidth();
            if(mDropDownWidth == -2)
            {
                k = compatMeasureContentWidth((SpinnerAdapter)mAdapter, getBackground());
                l = getContext().getResources().getDisplayMetrics().widthPixels - mTempRect.left - mTempRect.right;
                j = k;
                if(k > l)
                    j = l;
                setContentWidth(Math.max(j, k1 - i1 - j1));
            } else
            if(mDropDownWidth == -1)
                setContentWidth(k1 - i1 - j1);
            else
                setContentWidth(mDropDownWidth);
            if(ViewUtils.isLayoutRtl(AppCompatSpinner.this))
                i += k1 - j1 - getWidth();
            else
                i += i1;
            setHorizontalOffset(i);
        }

        public CharSequence getHintText()
        {
            return mHintText;
        }

        boolean isVisibleToUser(View view)
        {
            return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(mVisibleRect);
        }

        public void setAdapter(ListAdapter listadapter)
        {
            setAdapter(listadapter);
            mAdapter = listadapter;
        }

        public void setPromptText(CharSequence charsequence)
        {
            mHintText = charsequence;
        }

        public void show()
        {
            boolean flag = isShowing();
            computeContentWidth();
            setInputMethodMode(2);
            show();
            getListView().setChoiceMode(1);
            setSelection(getSelectedItemPosition());
            ViewTreeObserver viewtreeobserver;
            if(!flag)
                if((viewtreeobserver = getViewTreeObserver()) != null)
                {
                    android.view.ViewTreeObserver.OnGlobalLayoutListener ongloballayoutlistener = new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

                        public void onGlobalLayout()
                        {
                            if(!isVisibleToUser(_fld0))
                            {
                                dismiss();
                                return;
                            } else
                            {
                                computeContentWidth();
                                show();
                                return;
                            }
                        }

                        final DropdownPopup this$1;

            
            {
                this$1 = DropdownPopup.this;
                Object();
            }
                    }
;
                    viewtreeobserver.addOnGlobalLayoutListener(ongloballayoutlistener);
                    setOnDismissListener(ongloballayoutlistener. new android.widget.PopupWindow.OnDismissListener() {

                        public void onDismiss()
                        {
                            ViewTreeObserver viewtreeobserver = getViewTreeObserver();
                            if(viewtreeobserver != null)
                                viewtreeobserver.removeGlobalOnLayoutListener(layoutListener);
                        }

                        final DropdownPopup this$1;
                        final android.view.ViewTreeObserver.OnGlobalLayoutListener val$layoutListener;

            
            {
                this$1 = final_dropdownpopup;
                layoutListener = android.view.ViewTreeObserver.OnGlobalLayoutListener.this;
                Object();
            }
                    }
);
                    return;
                }
        }

        ListAdapter mAdapter;
        private CharSequence mHintText;
        private final Rect mVisibleRect = new Rect();
        final AppCompatSpinner this$0;


        public DropdownPopup(Context context, AttributeSet attributeset, int i)
        {
            this$0 = AppCompatSpinner.this;
            ListPopupWindow(context, attributeset, i);
            setAnchorView(AppCompatSpinner.this);
            setModal(true);
            setPromptPosition(0);
            setOnItemClickListener(new _cls1());
        }
    }


    public AppCompatSpinner(Context context)
    {
        this(context, ((AttributeSet) (null)));
    }

    public AppCompatSpinner(Context context, int i)
    {
        this(context, null, android.support.v7.appcompat.R.attr.spinnerStyle, i);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, android.support.v7.appcompat.R.attr.spinnerStyle);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, -1);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeset, int i, int j)
    {
        this(context, attributeset, i, j, null);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeset, int i, int j, android.content.res.Resources.Theme theme)
    {
        int i1;
        Object obj;
        super(context, attributeset, i);
        mTempRect = new Rect();
        TintTypedArray tinttypedarray = TintTypedArray.obtainStyledAttributes(context, attributeset, android.support.v7.appcompat.R.styleable.Spinner, i, 0);
        mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        int k;
        TypedArray typedarray;
        if(theme != null)
        {
            mPopupContext = new ContextThemeWrapper(context, theme);
        } else
        {
            int l = tinttypedarray.getResourceId(android.support.v7.appcompat.R.styleable.Spinner_popupTheme, 0);
            if(l != 0)
            {
                mPopupContext = new ContextThemeWrapper(context, l);
            } else
            {
                if(android.os.Build.VERSION.SDK_INT < 23)
                    theme = context;
                else
                    theme = null;
                mPopupContext = theme;
            }
        }
        if(mPopupContext == null) goto _L2; else goto _L1
_L1:
        i1 = j;
        if(j != -1) goto _L4; else goto _L3
_L3:
        if(android.os.Build.VERSION.SDK_INT < 11) goto _L6; else goto _L5
_L5:
        obj = null;
        theme = null;
        typedarray = context.obtainStyledAttributes(attributeset, ATTRS_ANDROID_SPINNERMODE, i, 0);
        k = j;
        theme = typedarray;
        obj = typedarray;
        if(!typedarray.hasValue(0))
            break MISSING_BLOCK_LABEL_142;
        theme = typedarray;
        obj = typedarray;
        k = typedarray.getInt(0, 0);
        i1 = k;
        if(typedarray != null)
        {
            typedarray.recycle();
            i1 = k;
        }
_L4:
        if(i1 == 1)
        {
            theme = new DropdownPopup(mPopupContext, attributeset, i);
            obj = TintTypedArray.obtainStyledAttributes(mPopupContext, attributeset, android.support.v7.appcompat.R.styleable.Spinner, i, 0);
            mDropDownWidth = ((TintTypedArray) (obj)).getLayoutDimension(android.support.v7.appcompat.R.styleable.Spinner_android_dropDownWidth, -2);
            theme.setBackgroundDrawable(((TintTypedArray) (obj)).getDrawable(android.support.v7.appcompat.R.styleable.Spinner_android_popupBackground));
            theme.setPromptText(tinttypedarray.getString(android.support.v7.appcompat.R.styleable.Spinner_android_prompt));
            ((TintTypedArray) (obj)).recycle();
            mPopup = theme;
            mForwardingListener = new ForwardingListener(theme) {

                public ShowableListMenu getPopup()
                {
                    return popup;
                }

                public boolean onForwardingStarted()
                {
                    if(!mPopup.isShowing())
                        mPopup.show();
                    return true;
                }

                final AppCompatSpinner this$0;
                final DropdownPopup val$popup;

            
            {
                this$0 = AppCompatSpinner.this;
                popup = dropdownpopup;
                ForwardingListener(final_view);
            }
            }
;
        }
_L2:
        theme = tinttypedarray.getTextArray(android.support.v7.appcompat.R.styleable.Spinner_android_entries);
        if(theme != null)
        {
            context = new ArrayAdapter(context, 0x1090008, theme);
            context.setDropDownViewResource(android.support.v7.appcompat.R.layout.support_simple_spinner_dropdown_item);
            setAdapter(context);
        }
        tinttypedarray.recycle();
        mPopupSet = true;
        if(mTempAdapter != null)
        {
            setAdapter(mTempAdapter);
            mTempAdapter = null;
        }
        mBackgroundTintHelper.loadFromAttributes(attributeset, i);
        return;
        Exception exception;
        exception;
        obj = theme;
        Log.i("AppCompatSpinner", "Could not read android:spinnerMode", exception);
        i1 = j;
        if(theme != null)
        {
            theme.recycle();
            i1 = j;
        }
        continue; /* Loop/switch isn't completed */
        context;
        if(obj != null)
            ((TypedArray) (obj)).recycle();
        throw context;
_L6:
        i1 = 1;
        if(true) goto _L4; else goto _L7
_L7:
    }

    int compatMeasureContentWidth(SpinnerAdapter spinneradapter, Drawable drawable)
    {
        int j;
        if(spinneradapter == null)
        {
            j = 0;
        } else
        {
            int i = 0;
            View view = null;
            int k = 0;
            int j1 = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
            int k1 = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
            j = Math.max(0, getSelectedItemPosition());
            int l1 = Math.min(spinneradapter.getCount(), j + 15);
            for(j = Math.max(0, j - (15 - (l1 - j))); j < l1;)
            {
                int i1 = spinneradapter.getItemViewType(j);
                int l = k;
                if(i1 != k)
                {
                    l = i1;
                    view = null;
                }
                view = spinneradapter.getView(j, view, this);
                if(view.getLayoutParams() == null)
                    view.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
                view.measure(j1, k1);
                i = Math.max(i, view.getMeasuredWidth());
                j++;
                k = l;
            }

            j = i;
            if(drawable != null)
            {
                drawable.getPadding(mTempRect);
                return i + (mTempRect.left + mTempRect.right);
            }
        }
        return j;
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.applySupportBackgroundTint();
    }

    public int getDropDownHorizontalOffset()
    {
        if(mPopup != null)
            return mPopup.getHorizontalOffset();
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return super.getDropDownHorizontalOffset();
        else
            return 0;
    }

    public int getDropDownVerticalOffset()
    {
        if(mPopup != null)
            return mPopup.getVerticalOffset();
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return super.getDropDownVerticalOffset();
        else
            return 0;
    }

    public int getDropDownWidth()
    {
        if(mPopup != null)
            return mDropDownWidth;
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return super.getDropDownWidth();
        else
            return 0;
    }

    public Drawable getPopupBackground()
    {
        if(mPopup != null)
            return mPopup.getBackground();
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return super.getPopupBackground();
        else
            return null;
    }

    public Context getPopupContext()
    {
        if(mPopup != null)
            return mPopupContext;
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return super.getPopupContext();
        else
            return null;
    }

    public CharSequence getPrompt()
    {
        if(mPopup != null)
            return mPopup.getHintText();
        else
            return super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList()
    {
        if(mBackgroundTintHelper != null)
            return mBackgroundTintHelper.getSupportBackgroundTintList();
        else
            return null;
    }

    public android.graphics.PorterDuff.Mode getSupportBackgroundTintMode()
    {
        if(mBackgroundTintHelper != null)
            return mBackgroundTintHelper.getSupportBackgroundTintMode();
        else
            return null;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mPopup != null && mPopup.isShowing())
            mPopup.dismiss();
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if(mPopup != null && android.view.View.MeasureSpec.getMode(i) == 0x80000000)
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), android.view.View.MeasureSpec.getSize(i)), getMeasuredHeight());
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mForwardingListener != null && mForwardingListener.onTouch(this, motionevent))
            return true;
        else
            return super.onTouchEvent(motionevent);
    }

    public boolean performClick()
    {
        if(mPopup != null)
        {
            if(!mPopup.isShowing())
                mPopup.show();
            return true;
        } else
        {
            return super.performClick();
        }
    }

    public volatile void setAdapter(Adapter adapter)
    {
        setAdapter((SpinnerAdapter)adapter);
    }

    public void setAdapter(SpinnerAdapter spinneradapter)
    {
        if(!mPopupSet)
        {
            mTempAdapter = spinneradapter;
        } else
        {
            super.setAdapter(spinneradapter);
            if(mPopup != null)
            {
                Context context;
                if(mPopupContext == null)
                    context = getContext();
                else
                    context = mPopupContext;
                mPopup.setAdapter(new DropDownAdapter(spinneradapter, context.getTheme()));
                return;
            }
        }
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        super.setBackgroundDrawable(drawable);
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.onSetBackgroundDrawable(drawable);
    }

    public void setBackgroundResource(int i)
    {
        super.setBackgroundResource(i);
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.onSetBackgroundResource(i);
    }

    public void setDropDownHorizontalOffset(int i)
    {
        if(mPopup != null)
            mPopup.setHorizontalOffset(i);
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            super.setDropDownHorizontalOffset(i);
            return;
        }
    }

    public void setDropDownVerticalOffset(int i)
    {
        if(mPopup != null)
            mPopup.setVerticalOffset(i);
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            super.setDropDownVerticalOffset(i);
            return;
        }
    }

    public void setDropDownWidth(int i)
    {
        if(mPopup != null)
            mDropDownWidth = i;
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            super.setDropDownWidth(i);
            return;
        }
    }

    public void setPopupBackgroundDrawable(Drawable drawable)
    {
        if(mPopup != null)
            mPopup.setBackgroundDrawable(drawable);
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            super.setPopupBackgroundDrawable(drawable);
            return;
        }
    }

    public void setPopupBackgroundResource(int i)
    {
        setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), i));
    }

    public void setPrompt(CharSequence charsequence)
    {
        if(mPopup != null)
        {
            mPopup.setPromptText(charsequence);
            return;
        } else
        {
            super.setPrompt(charsequence);
            return;
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorstatelist)
    {
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.setSupportBackgroundTintList(colorstatelist);
    }

    public void setSupportBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.setSupportBackgroundTintMode(mode);
    }

    private static final int ATTRS_ANDROID_SPINNERMODE[] = {
        0x10102f1
    };
    private static final int MAX_ITEMS_MEASURED = 15;
    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "AppCompatSpinner";
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    private DropdownPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    private final Rect mTempRect;





    // Unreferenced inner class android/support/v7/widget/AppCompatSpinner$DropdownPopup$1

/* anonymous class */
    class DropdownPopup._cls1
        implements android.widget.AdapterView.OnItemClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            setSelection(i);
            if(getOnItemClickListener() != null)
                performItemClick(view, i, mAdapter.getItemId(i));
            dismiss();
        }

        final DropdownPopup this$1;
        final AppCompatSpinner val$this$0;

            
            {
                this$1 = final_dropdownpopup;
                this$0 = AppCompatSpinner.this;
                Object();
            }
    }

}
