// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.ActionProvider;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class ActionMenuItem
    implements SupportMenuItem
{

    public ActionMenuItem(Context context, int i, int j, int k, int l, CharSequence charsequence)
    {
        mShortcutNumericModifiers = 4096;
        mShortcutAlphabeticModifiers = 4096;
        mIconResId = 0;
        mIconTintList = null;
        mIconTintMode = null;
        mHasIconTint = false;
        mHasIconTintMode = false;
        mFlags = 16;
        mContext = context;
        mId = j;
        mGroup = i;
        mCategoryOrder = k;
        mOrdering = l;
        mTitle = charsequence;
    }

    private void applyIconTint()
    {
        if(mIconDrawable != null && (mHasIconTint || mHasIconTintMode))
        {
            mIconDrawable = DrawableCompat.wrap(mIconDrawable);
            mIconDrawable = mIconDrawable.mutate();
            if(mHasIconTint)
                DrawableCompat.setTintList(mIconDrawable, mIconTintList);
            if(mHasIconTintMode)
                DrawableCompat.setTintMode(mIconDrawable, mIconTintMode);
        }
    }

    public boolean collapseActionView()
    {
        return false;
    }

    public boolean expandActionView()
    {
        return false;
    }

    public ActionProvider getActionProvider()
    {
        throw new UnsupportedOperationException();
    }

    public View getActionView()
    {
        return null;
    }

    public int getAlphabeticModifiers()
    {
        return mShortcutAlphabeticModifiers;
    }

    public char getAlphabeticShortcut()
    {
        return mShortcutAlphabeticChar;
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
        return mIconDrawable;
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
        return null;
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
        return mOrdering;
    }

    public SubMenu getSubMenu()
    {
        return null;
    }

    public android.support.v4.view.ActionProvider getSupportActionProvider()
    {
        return null;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public CharSequence getTitleCondensed()
    {
        if(mTitleCondensed != null)
            return mTitleCondensed;
        else
            return mTitle;
    }

    public CharSequence getTooltipText()
    {
        return mTooltipText;
    }

    public boolean hasSubMenu()
    {
        return false;
    }

    public boolean invoke()
    {
        if(mClickListener != null && mClickListener.onMenuItemClick(this))
            return true;
        if(mIntent != null)
        {
            mContext.startActivity(mIntent);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean isActionViewExpanded()
    {
        return false;
    }

    public boolean isCheckable()
    {
        return (mFlags & 1) != 0;
    }

    public boolean isChecked()
    {
        return (mFlags & 2) != 0;
    }

    public boolean isEnabled()
    {
        return (mFlags & 0x10) != 0;
    }

    public boolean isVisible()
    {
        return (mFlags & 8) == 0;
    }

    public MenuItem setActionProvider(ActionProvider actionprovider)
    {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem setActionView(int i)
    {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem setActionView(View view)
    {
        throw new UnsupportedOperationException();
    }

    public volatile MenuItem setActionView(int i)
    {
        return setActionView(i);
    }

    public volatile MenuItem setActionView(View view)
    {
        return setActionView(view);
    }

    public MenuItem setAlphabeticShortcut(char c)
    {
        mShortcutAlphabeticChar = Character.toLowerCase(c);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c, int i)
    {
        mShortcutAlphabeticChar = Character.toLowerCase(c);
        mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public MenuItem setCheckable(boolean flag)
    {
        int i = mFlags;
        boolean flag1;
        if(flag)
            flag1 = true;
        else
            flag1 = false;
        mFlags = flag1 | i & -2;
        return this;
    }

    public MenuItem setChecked(boolean flag)
    {
        int i = mFlags;
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        mFlags = byte0 | i & -3;
        return this;
    }

    public SupportMenuItem setContentDescription(CharSequence charsequence)
    {
        mContentDescription = charsequence;
        return this;
    }

    public volatile MenuItem setContentDescription(CharSequence charsequence)
    {
        return setContentDescription(charsequence);
    }

    public MenuItem setEnabled(boolean flag)
    {
        int i = mFlags;
        byte byte0;
        if(flag)
            byte0 = 16;
        else
            byte0 = 0;
        mFlags = byte0 | i & 0xffffffef;
        return this;
    }

    public ActionMenuItem setExclusiveCheckable(boolean flag)
    {
        int i = mFlags;
        byte byte0;
        if(flag)
            byte0 = 4;
        else
            byte0 = 0;
        mFlags = byte0 | i & -5;
        return this;
    }

    public MenuItem setIcon(int i)
    {
        mIconResId = i;
        mIconDrawable = ContextCompat.getDrawable(mContext, i);
        applyIconTint();
        return this;
    }

    public MenuItem setIcon(Drawable drawable)
    {
        mIconDrawable = drawable;
        mIconResId = 0;
        applyIconTint();
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorstatelist)
    {
        mIconTintList = colorstatelist;
        mHasIconTint = true;
        applyIconTint();
        return this;
    }

    public MenuItem setIconTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mIconTintMode = mode;
        mHasIconTintMode = true;
        applyIconTint();
        return this;
    }

    public MenuItem setIntent(Intent intent)
    {
        mIntent = intent;
        return this;
    }

    public MenuItem setNumericShortcut(char c)
    {
        mShortcutNumericChar = c;
        return this;
    }

    public MenuItem setNumericShortcut(char c, int i)
    {
        mShortcutNumericChar = c;
        mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public MenuItem setOnActionExpandListener(android.view.MenuItem.OnActionExpandListener onactionexpandlistener)
    {
        throw new UnsupportedOperationException();
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
        return this;
    }

    public MenuItem setShortcut(char c, char c1, int i, int j)
    {
        mShortcutNumericChar = c;
        mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        mShortcutAlphabeticChar = Character.toLowerCase(c1);
        mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(j);
        return this;
    }

    public void setShowAsAction(int i)
    {
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

    public SupportMenuItem setSupportActionProvider(android.support.v4.view.ActionProvider actionprovider)
    {
        throw new UnsupportedOperationException();
    }

    public MenuItem setTitle(int i)
    {
        mTitle = mContext.getResources().getString(i);
        return this;
    }

    public MenuItem setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charsequence)
    {
        mTitleCondensed = charsequence;
        return this;
    }

    public SupportMenuItem setTooltipText(CharSequence charsequence)
    {
        mTooltipText = charsequence;
        return this;
    }

    public volatile MenuItem setTooltipText(CharSequence charsequence)
    {
        return setTooltipText(charsequence);
    }

    public MenuItem setVisible(boolean flag)
    {
        int i = mFlags;
        byte byte0;
        if(flag)
            byte0 = 0;
        else
            byte0 = 8;
        mFlags = byte0 | i & 8;
        return this;
    }

    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int NO_ICON = 0;
    private final int mCategoryOrder;
    private android.view.MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private Context mContext;
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
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;
}
