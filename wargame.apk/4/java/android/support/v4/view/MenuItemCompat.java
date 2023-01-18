// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.content.res.ColorStateList;
import android.support.v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

// Referenced classes of package android.support.v4.view:
//            ActionProvider

public final class MenuItemCompat
{
    static class MenuItemCompatApi26Impl extends MenuItemCompatBaseImpl
    {

        public int getAlphabeticModifiers(MenuItem menuitem)
        {
            return menuitem.getAlphabeticModifiers();
        }

        public CharSequence getContentDescription(MenuItem menuitem)
        {
            return menuitem.getContentDescription();
        }

        public ColorStateList getIconTintList(MenuItem menuitem)
        {
            return menuitem.getIconTintList();
        }

        public android.graphics.PorterDuff.Mode getIconTintMode(MenuItem menuitem)
        {
            return menuitem.getIconTintMode();
        }

        public int getNumericModifiers(MenuItem menuitem)
        {
            return menuitem.getNumericModifiers();
        }

        public CharSequence getTooltipText(MenuItem menuitem)
        {
            return menuitem.getTooltipText();
        }

        public void setAlphabeticShortcut(MenuItem menuitem, char c, int i)
        {
            menuitem.setAlphabeticShortcut(c, i);
        }

        public void setContentDescription(MenuItem menuitem, CharSequence charsequence)
        {
            menuitem.setContentDescription(charsequence);
        }

        public void setIconTintList(MenuItem menuitem, ColorStateList colorstatelist)
        {
            menuitem.setIconTintList(colorstatelist);
        }

        public void setIconTintMode(MenuItem menuitem, android.graphics.PorterDuff.Mode mode)
        {
            menuitem.setIconTintMode(mode);
        }

        public void setNumericShortcut(MenuItem menuitem, char c, int i)
        {
            menuitem.setNumericShortcut(c, i);
        }

        public void setShortcut(MenuItem menuitem, char c, char c1, int i, int j)
        {
            menuitem.setShortcut(c, c1, i, j);
        }

        public void setTooltipText(MenuItem menuitem, CharSequence charsequence)
        {
            menuitem.setTooltipText(charsequence);
        }

        MenuItemCompatApi26Impl()
        {
        }
    }

    static class MenuItemCompatBaseImpl
        implements MenuVersionImpl
    {

        public int getAlphabeticModifiers(MenuItem menuitem)
        {
            return 0;
        }

        public CharSequence getContentDescription(MenuItem menuitem)
        {
            return null;
        }

        public ColorStateList getIconTintList(MenuItem menuitem)
        {
            return null;
        }

        public android.graphics.PorterDuff.Mode getIconTintMode(MenuItem menuitem)
        {
            return null;
        }

        public int getNumericModifiers(MenuItem menuitem)
        {
            return 0;
        }

        public CharSequence getTooltipText(MenuItem menuitem)
        {
            return null;
        }

        public void setAlphabeticShortcut(MenuItem menuitem, char c, int i)
        {
        }

        public void setContentDescription(MenuItem menuitem, CharSequence charsequence)
        {
        }

        public void setIconTintList(MenuItem menuitem, ColorStateList colorstatelist)
        {
        }

        public void setIconTintMode(MenuItem menuitem, android.graphics.PorterDuff.Mode mode)
        {
        }

        public void setNumericShortcut(MenuItem menuitem, char c, int i)
        {
        }

        public void setShortcut(MenuItem menuitem, char c, char c1, int i, int j)
        {
        }

        public void setTooltipText(MenuItem menuitem, CharSequence charsequence)
        {
        }

        MenuItemCompatBaseImpl()
        {
        }
    }

    static interface MenuVersionImpl
    {

        public abstract int getAlphabeticModifiers(MenuItem menuitem);

        public abstract CharSequence getContentDescription(MenuItem menuitem);

        public abstract ColorStateList getIconTintList(MenuItem menuitem);

        public abstract android.graphics.PorterDuff.Mode getIconTintMode(MenuItem menuitem);

        public abstract int getNumericModifiers(MenuItem menuitem);

        public abstract CharSequence getTooltipText(MenuItem menuitem);

        public abstract void setAlphabeticShortcut(MenuItem menuitem, char c, int i);

        public abstract void setContentDescription(MenuItem menuitem, CharSequence charsequence);

        public abstract void setIconTintList(MenuItem menuitem, ColorStateList colorstatelist);

        public abstract void setIconTintMode(MenuItem menuitem, android.graphics.PorterDuff.Mode mode);

        public abstract void setNumericShortcut(MenuItem menuitem, char c, int i);

        public abstract void setShortcut(MenuItem menuitem, char c, char c1, int i, int j);

        public abstract void setTooltipText(MenuItem menuitem, CharSequence charsequence);
    }

    public static interface OnActionExpandListener
    {

        public abstract boolean onMenuItemActionCollapse(MenuItem menuitem);

        public abstract boolean onMenuItemActionExpand(MenuItem menuitem);
    }


    private MenuItemCompat()
    {
    }

    public static boolean collapseActionView(MenuItem menuitem)
    {
        return menuitem.collapseActionView();
    }

    public static boolean expandActionView(MenuItem menuitem)
    {
        return menuitem.expandActionView();
    }

    public static ActionProvider getActionProvider(MenuItem menuitem)
    {
        if(menuitem instanceof SupportMenuItem)
        {
            return ((SupportMenuItem)menuitem).getSupportActionProvider();
        } else
        {
            Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
            return null;
        }
    }

    public static View getActionView(MenuItem menuitem)
    {
        return menuitem.getActionView();
    }

    public static int getAlphabeticModifiers(MenuItem menuitem)
    {
        if(menuitem instanceof SupportMenuItem)
            return ((SupportMenuItem)menuitem).getAlphabeticModifiers();
        else
            return IMPL.getAlphabeticModifiers(menuitem);
    }

    public static CharSequence getContentDescription(MenuItem menuitem)
    {
        if(menuitem instanceof SupportMenuItem)
            return ((SupportMenuItem)menuitem).getContentDescription();
        else
            return IMPL.getContentDescription(menuitem);
    }

    public static ColorStateList getIconTintList(MenuItem menuitem)
    {
        if(menuitem instanceof SupportMenuItem)
            return ((SupportMenuItem)menuitem).getIconTintList();
        else
            return IMPL.getIconTintList(menuitem);
    }

    public static android.graphics.PorterDuff.Mode getIconTintMode(MenuItem menuitem)
    {
        if(menuitem instanceof SupportMenuItem)
            return ((SupportMenuItem)menuitem).getIconTintMode();
        else
            return IMPL.getIconTintMode(menuitem);
    }

    public static int getNumericModifiers(MenuItem menuitem)
    {
        if(menuitem instanceof SupportMenuItem)
            return ((SupportMenuItem)menuitem).getNumericModifiers();
        else
            return IMPL.getNumericModifiers(menuitem);
    }

    public static CharSequence getTooltipText(MenuItem menuitem)
    {
        if(menuitem instanceof SupportMenuItem)
            return ((SupportMenuItem)menuitem).getTooltipText();
        else
            return IMPL.getTooltipText(menuitem);
    }

    public static boolean isActionViewExpanded(MenuItem menuitem)
    {
        return menuitem.isActionViewExpanded();
    }

    public static MenuItem setActionProvider(MenuItem menuitem, ActionProvider actionprovider)
    {
        if(menuitem instanceof SupportMenuItem)
        {
            return ((SupportMenuItem)menuitem).setSupportActionProvider(actionprovider);
        } else
        {
            Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
            return menuitem;
        }
    }

    public static MenuItem setActionView(MenuItem menuitem, int i)
    {
        return menuitem.setActionView(i);
    }

    public static MenuItem setActionView(MenuItem menuitem, View view)
    {
        return menuitem.setActionView(view);
    }

    public static void setAlphabeticShortcut(MenuItem menuitem, char c, int i)
    {
        if(menuitem instanceof SupportMenuItem)
        {
            ((SupportMenuItem)menuitem).setAlphabeticShortcut(c, i);
            return;
        } else
        {
            IMPL.setAlphabeticShortcut(menuitem, c, i);
            return;
        }
    }

    public static void setContentDescription(MenuItem menuitem, CharSequence charsequence)
    {
        if(menuitem instanceof SupportMenuItem)
        {
            ((SupportMenuItem)menuitem).setContentDescription(charsequence);
            return;
        } else
        {
            IMPL.setContentDescription(menuitem, charsequence);
            return;
        }
    }

    public static void setIconTintList(MenuItem menuitem, ColorStateList colorstatelist)
    {
        if(menuitem instanceof SupportMenuItem)
        {
            ((SupportMenuItem)menuitem).setIconTintList(colorstatelist);
            return;
        } else
        {
            IMPL.setIconTintList(menuitem, colorstatelist);
            return;
        }
    }

    public static void setIconTintMode(MenuItem menuitem, android.graphics.PorterDuff.Mode mode)
    {
        if(menuitem instanceof SupportMenuItem)
        {
            ((SupportMenuItem)menuitem).setIconTintMode(mode);
            return;
        } else
        {
            IMPL.setIconTintMode(menuitem, mode);
            return;
        }
    }

    public static void setNumericShortcut(MenuItem menuitem, char c, int i)
    {
        if(menuitem instanceof SupportMenuItem)
        {
            ((SupportMenuItem)menuitem).setNumericShortcut(c, i);
            return;
        } else
        {
            IMPL.setNumericShortcut(menuitem, c, i);
            return;
        }
    }

    public static MenuItem setOnActionExpandListener(MenuItem menuitem, OnActionExpandListener onactionexpandlistener)
    {
        return menuitem.setOnActionExpandListener(new android.view.MenuItem.OnActionExpandListener(onactionexpandlistener) {

            public boolean onMenuItemActionCollapse(MenuItem menuitem1)
            {
                return listener.onMenuItemActionCollapse(menuitem1);
            }

            public boolean onMenuItemActionExpand(MenuItem menuitem1)
            {
                return listener.onMenuItemActionExpand(menuitem1);
            }

            final OnActionExpandListener val$listener;

            
            {
                listener = onactionexpandlistener;
                super();
            }
        }
);
    }

    public static void setShortcut(MenuItem menuitem, char c, char c1, int i, int j)
    {
        if(menuitem instanceof SupportMenuItem)
        {
            ((SupportMenuItem)menuitem).setShortcut(c, c1, i, j);
            return;
        } else
        {
            IMPL.setShortcut(menuitem, c, c1, i, j);
            return;
        }
    }

    public static void setShowAsAction(MenuItem menuitem, int i)
    {
        menuitem.setShowAsAction(i);
    }

    public static void setTooltipText(MenuItem menuitem, CharSequence charsequence)
    {
        if(menuitem instanceof SupportMenuItem)
        {
            ((SupportMenuItem)menuitem).setTooltipText(charsequence);
            return;
        } else
        {
            IMPL.setTooltipText(menuitem, charsequence);
            return;
        }
    }

    static final MenuVersionImpl IMPL;
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
            IMPL = new MenuItemCompatApi26Impl();
        else
            IMPL = new MenuItemCompatBaseImpl();
    }
}
