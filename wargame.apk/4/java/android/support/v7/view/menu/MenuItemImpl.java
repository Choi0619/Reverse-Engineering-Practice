// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.LinearLayout;

// Referenced classes of package android.support.v7.view.menu:
//            MenuBuilder, SubMenuBuilder

public final class MenuItemImpl
    implements SupportMenuItem
{

    MenuItemImpl(MenuBuilder menubuilder, int i, int j, int k, int l, CharSequence charsequence, int i1)
    {
        mShortcutNumericModifiers = 4096;
        mShortcutAlphabeticModifiers = 4096;
        mIconResId = 0;
        mIconTintList = null;
        mIconTintMode = null;
        mHasIconTint = false;
        mHasIconTintMode = false;
        mNeedToApplyIconTint = false;
        mFlags = 16;
        mShowAsAction = 0;
        mIsActionViewExpanded = false;
        mMenu = menubuilder;
        mId = j;
        mGroup = i;
        mCategoryOrder = k;
        mOrdering = l;
        mTitle = charsequence;
        mShowAsAction = i1;
    }

    private Drawable applyIconTintIfNecessary(Drawable drawable)
    {
        Drawable drawable1;
label0:
        {
            drawable1 = drawable;
            if(drawable == null)
                break label0;
            drawable1 = drawable;
            if(!mNeedToApplyIconTint)
                break label0;
            if(!mHasIconTint)
            {
                drawable1 = drawable;
                if(!mHasIconTintMode)
                    break label0;
            }
            drawable1 = DrawableCompat.wrap(drawable).mutate();
            if(mHasIconTint)
                DrawableCompat.setTintList(drawable1, mIconTintList);
            if(mHasIconTintMode)
                DrawableCompat.setTintMode(drawable1, mIconTintMode);
            mNeedToApplyIconTint = false;
        }
        return drawable1;
    }

    public void actionFormatChanged()
    {
        mMenu.onItemActionRequestChanged(this);
    }

    public boolean collapseActionView()
    {
        if((mShowAsAction & 8) != 0)
        {
            if(mActionView == null)
                return true;
            if(mOnActionExpandListener == null || mOnActionExpandListener.onMenuItemActionCollapse(this))
                return mMenu.collapseItemActionView(this);
        }
        return false;
    }

    public boolean expandActionView()
    {
        while(!hasCollapsibleActionView() || mOnActionExpandListener != null && !mOnActionExpandListener.onMenuItemActionExpand(this)) 
            return false;
        return mMenu.expandItemActionView(this);
    }

    public android.view.ActionProvider getActionProvider()
    {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public View getActionView()
    {
        if(mActionView != null)
            return mActionView;
        if(mActionProvider != null)
        {
            mActionView = mActionProvider.onCreateActionView(this);
            return mActionView;
        } else
        {
            return null;
        }
    }

    public int getAlphabeticModifiers()
    {
        return mShortcutAlphabeticModifiers;
    }

    public char getAlphabeticShortcut()
    {
        return mShortcutAlphabeticChar;
    }

    Runnable getCallback()
    {
        return mItemCallback;
    }

    public CharSequence getContentDescription()
    {
        return mContentDescription;
    }

    public int getGroupId()
    {
        return mGroup;
    }

    public Drawable getIcon()
    {
        if(mIconDrawable != null)
            return applyIconTintIfNecessary(mIconDrawable);
        if(mIconResId != 0)
        {
            Drawable drawable = AppCompatResources.getDrawable(mMenu.getContext(), mIconResId);
            mIconResId = 0;
            mIconDrawable = drawable;
            return applyIconTintIfNecessary(drawable);
        } else
        {
            return null;
        }
    }

    public ColorStateList getIconTintList()
    {
        return mIconTintList;
    }

    public android.graphics.PorterDuff.Mode getIconTintMode()
    {
        return mIconTintMode;
    }

    public Intent getIntent()
    {
        return mIntent;
    }

    public int getItemId()
    {
        return mId;
    }

    public android.view.ContextMenu.ContextMenuInfo getMenuInfo()
    {
        return mMenuInfo;
    }

    public int getNumericModifiers()
    {
        return mShortcutNumericModifiers;
    }

    public char getNumericShortcut()
    {
        return mShortcutNumericChar;
    }

    public int getOrder()
    {
        return mCategoryOrder;
    }

    public int getOrdering()
    {
        return mOrdering;
    }

    char getShortcut()
    {
        if(mMenu.isQwertyMode())
            return mShortcutAlphabeticChar;
        else
            return mShortcutNumericChar;
    }

    String getShortcutLabel()
    {
        char c;
        StringBuilder stringbuilder;
        c = getShortcut();
        if(c == 0)
            return "";
        stringbuilder = new StringBuilder(sPrependShortcutLabel);
        c;
        JVM INSTR lookupswitch 3: default 60
    //                   8: 82
    //                   10: 71
    //                   32: 93;
           goto _L1 _L2 _L3 _L4
_L1:
        stringbuilder.append(c);
_L6:
        return stringbuilder.toString();
_L3:
        stringbuilder.append(sEnterShortcutLabel);
        continue; /* Loop/switch isn't completed */
_L2:
        stringbuilder.append(sDeleteShortcutLabel);
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append(sSpaceShortcutLabel);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public SubMenu getSubMenu()
    {
        return mSubMenu;
    }

    public ActionProvider getSupportActionProvider()
    {
        return mActionProvider;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public CharSequence getTitleCondensed()
    {
        CharSequence charsequence;
        Object obj;
        if(mTitleCondensed != null)
            charsequence = mTitleCondensed;
        else
            charsequence = mTitle;
        obj = charsequence;
        if(android.os.Build.VERSION.SDK_INT < 18)
        {
            obj = charsequence;
            if(charsequence != null)
            {
                obj = charsequence;
                if(!(charsequence instanceof String))
                    obj = charsequence.toString();
            }
        }
        return ((CharSequence) (obj));
    }

    CharSequence getTitleForItemView(MenuView.ItemView itemview)
    {
        if(itemview != null && itemview.prefersCondensedTitle())
            return getTitleCondensed();
        else
            return getTitle();
    }

    public CharSequence getTooltipText()
    {
        return mTooltipText;
    }

    public boolean hasCollapsibleActionView()
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if((mShowAsAction & 8) != 0)
        {
            if(mActionView == null && mActionProvider != null)
                mActionView = mActionProvider.onCreateActionView(this);
            flag = flag1;
            if(mActionView != null)
                flag = true;
        }
        return flag;
    }

    public boolean hasSubMenu()
    {
        return mSubMenu != null;
    }

    public boolean invoke()
    {
_L2:
        return true;
        if(mClickListener != null && mClickListener.onMenuItemClick(this) || mMenu.dispatchMenuItemSelected(mMenu, this)) goto _L2; else goto _L1
_L1:
        if(mItemCallback != null)
        {
            mItemCallback.run();
            return true;
        }
        if(mIntent == null)
            continue; /* Loop/switch isn't completed */
        mMenu.getContext().startActivity(mIntent);
        return true;
        ActivityNotFoundException activitynotfoundexception;
        activitynotfoundexception;
        Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", activitynotfoundexception);
        if(mActionProvider != null && mActionProvider.onPerformDefaultAction()) goto _L2; else goto _L3
_L3:
        return false;
    }

    public boolean isActionButton()
    {
        return (mFlags & 0x20) == 32;
    }

    public boolean isActionViewExpanded()
    {
        return mIsActionViewExpanded;
    }

    public boolean isCheckable()
    {
        return (mFlags & 1) == 1;
    }

    public boolean isChecked()
    {
        return (mFlags & 2) == 2;
    }

    public boolean isEnabled()
    {
        return (mFlags & 0x10) != 0;
    }

    public boolean isExclusiveCheckable()
    {
        return (mFlags & 4) != 0;
    }

    public boolean isVisible()
    {
        if(mActionProvider == null || !mActionProvider.overridesItemVisibility()) goto _L2; else goto _L1
_L1:
        if((mFlags & 8) != 0 || !mActionProvider.isVisible()) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if((mFlags & 8) != 0)
            return false;
        if(true) goto _L3; else goto _L5
_L5:
    }

    public boolean requestsActionButton()
    {
        return (mShowAsAction & 1) == 1;
    }

    public boolean requiresActionButton()
    {
        return (mShowAsAction & 2) == 2;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionprovider)
    {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public SupportMenuItem setActionView(int i)
    {
        Context context = mMenu.getContext();
        setActionView(LayoutInflater.from(context).inflate(i, new LinearLayout(context), false));
        return this;
    }

    public SupportMenuItem setActionView(View view)
    {
        mActionView = view;
        mActionProvider = null;
        if(view != null && view.getId() == -1 && mId > 0)
            view.setId(mId);
        mMenu.onItemActionRequestChanged(this);
        return this;
    }

    public volatile MenuItem setActionView(int i)
    {
        return setActionView(i);
    }

    public volatile MenuItem setActionView(View view)
    {
        return setActionView(view);
    }

    public void setActionViewExpanded(boolean flag)
    {
        mIsActionViewExpanded = flag;
        mMenu.onItemsChanged(false);
    }

    public MenuItem setAlphabeticShortcut(char c)
    {
        if(mShortcutAlphabeticChar == c)
        {
            return this;
        } else
        {
            mShortcutAlphabeticChar = Character.toLowerCase(c);
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public MenuItem setAlphabeticShortcut(char c, int i)
    {
        if(mShortcutAlphabeticChar == c && mShortcutAlphabeticModifiers == i)
        {
            return this;
        } else
        {
            mShortcutAlphabeticChar = Character.toLowerCase(c);
            mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i);
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public MenuItem setCallback(Runnable runnable)
    {
        mItemCallback = runnable;
        return this;
    }

    public MenuItem setCheckable(boolean flag)
    {
        int i = mFlags;
        int j = mFlags;
        boolean flag1;
        if(flag)
            flag1 = true;
        else
            flag1 = false;
        mFlags = flag1 | j & -2;
        if(i != mFlags)
            mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setChecked(boolean flag)
    {
        if((mFlags & 4) != 0)
        {
            mMenu.setExclusiveItemChecked(this);
            return this;
        } else
        {
            setCheckedInt(flag);
            return this;
        }
    }

    void setCheckedInt(boolean flag)
    {
        int i = mFlags;
        int j = mFlags;
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        mFlags = byte0 | j & -3;
        if(i != mFlags)
            mMenu.onItemsChanged(false);
    }

    public SupportMenuItem setContentDescription(CharSequence charsequence)
    {
        mContentDescription = charsequence;
        mMenu.onItemsChanged(false);
        return this;
    }

    public volatile MenuItem setContentDescription(CharSequence charsequence)
    {
        return setContentDescription(charsequence);
    }

    public MenuItem setEnabled(boolean flag)
    {
        if(flag)
            mFlags = mFlags | 0x10;
        else
            mFlags = mFlags & 0xffffffef;
        mMenu.onItemsChanged(false);
        return this;
    }

    public void setExclusiveCheckable(boolean flag)
    {
        int i = mFlags;
        byte byte0;
        if(flag)
            byte0 = 4;
        else
            byte0 = 0;
        mFlags = byte0 | i & -5;
    }

    public MenuItem setIcon(int i)
    {
        mIconDrawable = null;
        mIconResId = i;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIcon(Drawable drawable)
    {
        mIconResId = 0;
        mIconDrawable = drawable;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorstatelist)
    {
        mIconTintList = colorstatelist;
        mHasIconTint = true;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mIconTintMode = mode;
        mHasIconTintMode = true;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIntent(Intent intent)
    {
        mIntent = intent;
        return this;
    }

    public void setIsActionButton(boolean flag)
    {
        if(flag)
        {
            mFlags = mFlags | 0x20;
            return;
        } else
        {
            mFlags = mFlags & 0xffffffdf;
            return;
        }
    }

    void setMenuInfo(android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
    {
        mMenuInfo = contextmenuinfo;
    }

    public MenuItem setNumericShortcut(char c)
    {
        if(mShortcutNumericChar == c)
        {
            return this;
        } else
        {
            mShortcutNumericChar = c;
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public MenuItem setNumericShortcut(char c, int i)
    {
        if(mShortcutNumericChar == c && mShortcutNumericModifiers == i)
        {
            return this;
        } else
        {
            mShortcutNumericChar = c;
            mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public MenuItem setOnActionExpandListener(android.view.MenuItem.OnActionExpandListener onactionexpandlistener)
    {
        mOnActionExpandListener = onactionexpandlistener;
        return this;
    }

    public MenuItem setOnMenuItemClickListener(android.view.MenuItem.OnMenuItemClickListener onmenuitemclicklistener)
    {
        mClickListener = onmenuitemclicklistener;
        return this;
    }

    public MenuItem setShortcut(char c, char c1)
    {
        mShortcutNumericChar = c;
        mShortcutAlphabeticChar = Character.toLowerCase(c1);
        mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setShortcut(char c, char c1, int i, int j)
    {
        mShortcutNumericChar = c;
        mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        mShortcutAlphabeticChar = Character.toLowerCase(c1);
        mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(j);
        mMenu.onItemsChanged(false);
        return this;
    }

    public void setShowAsAction(int i)
    {
        switch(i & 3)
        {
        default:
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            mShowAsAction = i;
            break;
        }
        mMenu.onItemActionRequestChanged(this);
    }

    public SupportMenuItem setShowAsActionFlags(int i)
    {
        setShowAsAction(i);
        return this;
    }

    public volatile MenuItem setShowAsActionFlags(int i)
    {
        return setShowAsActionFlags(i);
    }

    public void setSubMenu(SubMenuBuilder submenubuilder)
    {
        mSubMenu = submenubuilder;
        submenubuilder.setHeaderTitle(getTitle());
    }

    public SupportMenuItem setSupportActionProvider(ActionProvider actionprovider)
    {
        if(mActionProvider != null)
            mActionProvider.reset();
        mActionView = null;
        mActionProvider = actionprovider;
        mMenu.onItemsChanged(true);
        if(mActionProvider != null)
            mActionProvider.setVisibilityListener(new android.support.v4.view.ActionProvider.VisibilityListener() {

                public void onActionProviderVisibilityChanged(boolean flag)
                {
                    mMenu.onItemVisibleChanged(MenuItemImpl.this);
                }

                final MenuItemImpl this$0;

            
            {
                this$0 = MenuItemImpl.this;
                super();
            }
            }
);
        return this;
    }

    public MenuItem setTitle(int i)
    {
        return setTitle(((CharSequence) (mMenu.getContext().getString(i))));
    }

    public MenuItem setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        mMenu.onItemsChanged(false);
        if(mSubMenu != null)
            mSubMenu.setHeaderTitle(charsequence);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charsequence)
    {
        mTitleCondensed = charsequence;
        if(charsequence == null)
            charsequence = mTitle;
        mMenu.onItemsChanged(false);
        return this;
    }

    public SupportMenuItem setTooltipText(CharSequence charsequence)
    {
        mTooltipText = charsequence;
        mMenu.onItemsChanged(false);
        return this;
    }

    public volatile MenuItem setTooltipText(CharSequence charsequence)
    {
        return setTooltipText(charsequence);
    }

    public MenuItem setVisible(boolean flag)
    {
        if(setVisibleInt(flag))
            mMenu.onItemVisibleChanged(this);
        return this;
    }

    boolean setVisibleInt(boolean flag)
    {
        boolean flag1 = false;
        int i = mFlags;
        int j = mFlags;
        byte byte0;
        if(flag)
            byte0 = 0;
        else
            byte0 = 8;
        mFlags = byte0 | j & -9;
        flag = flag1;
        if(i != mFlags)
            flag = true;
        return flag;
    }

    public boolean shouldShowIcon()
    {
        return mMenu.getOptionalIconsVisible();
    }

    boolean shouldShowShortcut()
    {
        return mMenu.isShortcutsVisible() && getShortcut() != 0;
    }

    public boolean showsTextAsAction()
    {
        return (mShowAsAction & 4) == 4;
    }

    public String toString()
    {
        if(mTitle != null)
            return mTitle.toString();
        else
            return null;
    }

    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int IS_ACTION = 32;
    static final int NO_ICON = 0;
    private static final int SHOW_AS_ACTION_MASK = 3;
    private static final String TAG = "MenuItemImpl";
    private static String sDeleteShortcutLabel;
    private static String sEnterShortcutLabel;
    private static String sPrependShortcutLabel;
    private static String sSpaceShortcutLabel;
    private ActionProvider mActionProvider;
    private View mActionView;
    private final int mCategoryOrder;
    private android.view.MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private int mFlags;
    private final int mGroup;
    private boolean mHasIconTint;
    private boolean mHasIconTintMode;
    private Drawable mIconDrawable;
    private int mIconResId;
    private ColorStateList mIconTintList;
    private android.graphics.PorterDuff.Mode mIconTintMode;
    private final int mId;
    private Intent mIntent;
    private boolean mIsActionViewExpanded;
    private Runnable mItemCallback;
    MenuBuilder mMenu;
    private android.view.ContextMenu.ContextMenuInfo mMenuInfo;
    private boolean mNeedToApplyIconTint;
    private android.view.MenuItem.OnActionExpandListener mOnActionExpandListener;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers;
    private int mShowAsAction;
    private SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;
}
