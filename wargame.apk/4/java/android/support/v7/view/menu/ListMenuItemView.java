// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.*;

// Referenced classes of package android.support.v7.view.menu:
//            MenuItemImpl

public class ListMenuItemView extends LinearLayout
    implements MenuView.ItemView
{

    public ListMenuItemView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, android.support.v7.appcompat.R.attr.listMenuViewStyle);
    }

    public ListMenuItemView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset);
        attributeset = TintTypedArray.obtainStyledAttributes(getContext(), attributeset, android.support.v7.appcompat.R.styleable.MenuView, i, 0);
        mBackground = attributeset.getDrawable(android.support.v7.appcompat.R.styleable.MenuView_android_itemBackground);
        mTextAppearance = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.MenuView_android_itemTextAppearance, -1);
        mPreserveIconSpacing = attributeset.getBoolean(android.support.v7.appcompat.R.styleable.MenuView_preserveIconSpacing, false);
        mTextAppearanceContext = context;
        mSubMenuArrow = attributeset.getDrawable(android.support.v7.appcompat.R.styleable.MenuView_subMenuArrow);
        attributeset.recycle();
    }

    private LayoutInflater getInflater()
    {
        if(mInflater == null)
            mInflater = LayoutInflater.from(getContext());
        return mInflater;
    }

    private void insertCheckBox()
    {
        mCheckBox = (CheckBox)getInflater().inflate(android.support.v7.appcompat.R.layout.abc_list_menu_item_checkbox, this, false);
        addView(mCheckBox);
    }

    private void insertIconView()
    {
        mIconView = (ImageView)getInflater().inflate(android.support.v7.appcompat.R.layout.abc_list_menu_item_icon, this, false);
        addView(mIconView, 0);
    }

    private void insertRadioButton()
    {
        mRadioButton = (RadioButton)getInflater().inflate(android.support.v7.appcompat.R.layout.abc_list_menu_item_radio, this, false);
        addView(mRadioButton);
    }

    private void setSubMenuArrowVisible(boolean flag)
    {
        if(mSubMenuArrowView != null)
        {
            ImageView imageview = mSubMenuArrowView;
            int i;
            if(flag)
                i = 0;
            else
                i = 8;
            imageview.setVisibility(i);
        }
    }

    public MenuItemImpl getItemData()
    {
        return mItemData;
    }

    public void initialize(MenuItemImpl menuitemimpl, int i)
    {
        mItemData = menuitemimpl;
        mMenuType = i;
        if(menuitemimpl.isVisible())
            i = 0;
        else
            i = 8;
        setVisibility(i);
        setTitle(menuitemimpl.getTitleForItemView(this));
        setCheckable(menuitemimpl.isCheckable());
        setShortcut(menuitemimpl.shouldShowShortcut(), menuitemimpl.getShortcut());
        setIcon(menuitemimpl.getIcon());
        setEnabled(menuitemimpl.isEnabled());
        setSubMenuArrowVisible(menuitemimpl.hasSubMenu());
        setContentDescription(menuitemimpl.getContentDescription());
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        ViewCompat.setBackground(this, mBackground);
        mTitleView = (TextView)findViewById(android.support.v7.appcompat.R.id.title);
        if(mTextAppearance != -1)
            mTitleView.setTextAppearance(mTextAppearanceContext, mTextAppearance);
        mShortcutView = (TextView)findViewById(android.support.v7.appcompat.R.id.shortcut);
        mSubMenuArrowView = (ImageView)findViewById(android.support.v7.appcompat.R.id.submenuarrow);
        if(mSubMenuArrowView != null)
            mSubMenuArrowView.setImageDrawable(mSubMenuArrow);
    }

    protected void onMeasure(int i, int j)
    {
        if(mIconView != null && mPreserveIconSpacing)
        {
            android.view.ViewGroup.LayoutParams layoutparams = getLayoutParams();
            android.widget.LinearLayout.LayoutParams layoutparams1 = (android.widget.LinearLayout.LayoutParams)mIconView.getLayoutParams();
            if(layoutparams.height > 0 && layoutparams1.width <= 0)
                layoutparams1.width = layoutparams.height;
        }
        super.onMeasure(i, j);
    }

    public boolean prefersCondensedTitle()
    {
        return false;
    }

    public void setCheckable(boolean flag)
    {
        if(flag || mRadioButton != null || mCheckBox != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj;
        Object obj1;
        if(mItemData.isExclusiveCheckable())
        {
            if(mRadioButton == null)
                insertRadioButton();
            obj = mRadioButton;
            obj1 = mCheckBox;
        } else
        {
            if(mCheckBox == null)
                insertCheckBox();
            obj = mCheckBox;
            obj1 = mRadioButton;
        }
        if(flag)
        {
            ((CompoundButton) (obj)).setChecked(mItemData.isChecked());
            int i;
            if(flag)
                i = 0;
            else
                i = 8;
            if(((CompoundButton) (obj)).getVisibility() != i)
                ((CompoundButton) (obj)).setVisibility(i);
            if(obj1 != null && ((CompoundButton) (obj1)).getVisibility() != 8)
            {
                ((CompoundButton) (obj1)).setVisibility(8);
                return;
            }
            if(true)
                continue; /* Loop/switch isn't completed */
        }
        if(mCheckBox != null)
            mCheckBox.setVisibility(8);
        if(mRadioButton != null)
        {
            mRadioButton.setVisibility(8);
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void setChecked(boolean flag)
    {
        Object obj;
        if(mItemData.isExclusiveCheckable())
        {
            if(mRadioButton == null)
                insertRadioButton();
            obj = mRadioButton;
        } else
        {
            if(mCheckBox == null)
                insertCheckBox();
            obj = mCheckBox;
        }
        ((CompoundButton) (obj)).setChecked(flag);
    }

    public void setForceShowIcon(boolean flag)
    {
        mForceShowIcon = flag;
        mPreserveIconSpacing = flag;
    }

    public void setIcon(Drawable drawable)
    {
        boolean flag;
        if(mItemData.shouldShowIcon() || mForceShowIcon)
            flag = true;
        else
            flag = false;
        break MISSING_BLOCK_LABEL_19;
        if((flag || mPreserveIconSpacing) && (mIconView != null || drawable != null || mPreserveIconSpacing))
        {
            if(mIconView == null)
                insertIconView();
            if(drawable != null || mPreserveIconSpacing)
            {
                ImageView imageview = mIconView;
                if(!flag)
                    drawable = null;
                imageview.setImageDrawable(drawable);
                if(mIconView.getVisibility() != 0)
                {
                    mIconView.setVisibility(0);
                    return;
                }
            } else
            {
                mIconView.setVisibility(8);
                return;
            }
        }
        return;
    }

    public void setShortcut(boolean flag, char c)
    {
        if(flag && mItemData.shouldShowShortcut())
            c = '\0';
        else
            c = '\b';
        if(c == 0)
            mShortcutView.setText(mItemData.getShortcutLabel());
        if(mShortcutView.getVisibility() != c)
            mShortcutView.setVisibility(c);
    }

    public void setTitle(CharSequence charsequence)
    {
        if(charsequence != null)
        {
            mTitleView.setText(charsequence);
            if(mTitleView.getVisibility() != 0)
                mTitleView.setVisibility(0);
        } else
        if(mTitleView.getVisibility() != 8)
        {
            mTitleView.setVisibility(8);
            return;
        }
    }

    public boolean showsIcon()
    {
        return mForceShowIcon;
    }

    private static final String TAG = "ListMenuItemView";
    private Drawable mBackground;
    private CheckBox mCheckBox;
    private boolean mForceShowIcon;
    private ImageView mIconView;
    private LayoutInflater mInflater;
    private MenuItemImpl mItemData;
    private int mMenuType;
    private boolean mPreserveIconSpacing;
    private RadioButton mRadioButton;
    private TextView mShortcutView;
    private Drawable mSubMenuArrow;
    private ImageView mSubMenuArrowView;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
    private TextView mTitleView;
}
