// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.SpinnerAdapter;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.app:
//            ActionBar, NavItemSelectedListener

class ToolbarActionBar extends ActionBar
{
    private final class ActionMenuPresenterCallback
        implements android.support.v7.view.menu.MenuPresenter.Callback
    {

        public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
            if(mClosingActionMenu)
                return;
            mClosingActionMenu = true;
            mDecorToolbar.dismissPopupMenus();
            if(mWindowCallback != null)
                mWindowCallback.onPanelClosed(108, menubuilder);
            mClosingActionMenu = false;
        }

        public boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            if(mWindowCallback != null)
            {
                mWindowCallback.onMenuOpened(108, menubuilder);
                return true;
            } else
            {
                return false;
            }
        }

        private boolean mClosingActionMenu;
        final ToolbarActionBar this$0;

        ActionMenuPresenterCallback()
        {
            this$0 = ToolbarActionBar.this;
            super();
        }
    }

    private final class MenuBuilderCallback
        implements android.support.v7.view.menu.MenuBuilder.Callback
    {

        public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
        {
            return false;
        }

        public void onMenuModeChange(MenuBuilder menubuilder)
        {
            if(mWindowCallback != null)
                if(mDecorToolbar.isOverflowMenuShowing())
                    mWindowCallback.onPanelClosed(108, menubuilder);
                else
                if(mWindowCallback.onPreparePanel(0, null, menubuilder))
                {
                    mWindowCallback.onMenuOpened(108, menubuilder);
                    return;
                }
        }

        final ToolbarActionBar this$0;

        MenuBuilderCallback()
        {
            this$0 = ToolbarActionBar.this;
            super();
        }
    }

    private class ToolbarCallbackWrapper extends WindowCallbackWrapper
    {

        public View onCreatePanelView(int i)
        {
            if(i == 0)
                return new View(mDecorToolbar.getContext());
            else
                return super.onCreatePanelView(i);
        }

        public boolean onPreparePanel(int i, View view, Menu menu)
        {
            boolean flag = super.onPreparePanel(i, view, menu);
            if(flag && !mToolbarMenuPrepared)
            {
                mDecorToolbar.setMenuPrepared();
                mToolbarMenuPrepared = true;
            }
            return flag;
        }

        final ToolbarActionBar this$0;

        public ToolbarCallbackWrapper(android.view.Window.Callback callback)
        {
            this$0 = ToolbarActionBar.this;
            super(callback);
        }
    }


    ToolbarActionBar(Toolbar toolbar, CharSequence charsequence, android.view.Window.Callback callback)
    {
        mMenuVisibilityListeners = new ArrayList();
        mDecorToolbar = new ToolbarWidgetWrapper(toolbar, false);
        mWindowCallback = new ToolbarCallbackWrapper(callback);
        mDecorToolbar.setWindowCallback(mWindowCallback);
        toolbar.setOnMenuItemClickListener(mMenuClicker);
        mDecorToolbar.setWindowTitle(charsequence);
    }

    private Menu getMenu()
    {
        if(!mMenuCallbackSet)
        {
            mDecorToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            mMenuCallbackSet = true;
        }
        return mDecorToolbar.getMenu();
    }

    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onmenuvisibilitylistener)
    {
        mMenuVisibilityListeners.add(onmenuvisibilitylistener);
    }

    public void addTab(ActionBar.Tab tab)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(ActionBar.Tab tab, int i)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(ActionBar.Tab tab, int i, boolean flag)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void addTab(ActionBar.Tab tab, boolean flag)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public boolean closeOptionsMenu()
    {
        return mDecorToolbar.hideOverflowMenu();
    }

    public boolean collapseActionView()
    {
        if(mDecorToolbar.hasExpandedActionView())
        {
            mDecorToolbar.collapseActionView();
            return true;
        } else
        {
            return false;
        }
    }

    public void dispatchMenuVisibilityChanged(boolean flag)
    {
        if(flag != mLastMenuVisibility)
        {
            mLastMenuVisibility = flag;
            int j = mMenuVisibilityListeners.size();
            int i = 0;
            while(i < j) 
            {
                ((ActionBar.OnMenuVisibilityListener)mMenuVisibilityListeners.get(i)).onMenuVisibilityChanged(flag);
                i++;
            }
        }
    }

    public View getCustomView()
    {
        return mDecorToolbar.getCustomView();
    }

    public int getDisplayOptions()
    {
        return mDecorToolbar.getDisplayOptions();
    }

    public float getElevation()
    {
        return ViewCompat.getElevation(mDecorToolbar.getViewGroup());
    }

    public int getHeight()
    {
        return mDecorToolbar.getHeight();
    }

    public int getNavigationItemCount()
    {
        return 0;
    }

    public int getNavigationMode()
    {
        return 0;
    }

    public int getSelectedNavigationIndex()
    {
        return -1;
    }

    public ActionBar.Tab getSelectedTab()
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public CharSequence getSubtitle()
    {
        return mDecorToolbar.getSubtitle();
    }

    public ActionBar.Tab getTabAt(int i)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public int getTabCount()
    {
        return 0;
    }

    public Context getThemedContext()
    {
        return mDecorToolbar.getContext();
    }

    public CharSequence getTitle()
    {
        return mDecorToolbar.getTitle();
    }

    public android.view.Window.Callback getWrappedWindowCallback()
    {
        return mWindowCallback;
    }

    public void hide()
    {
        mDecorToolbar.setVisibility(8);
    }

    public boolean invalidateOptionsMenu()
    {
        mDecorToolbar.getViewGroup().removeCallbacks(mMenuInvalidator);
        ViewCompat.postOnAnimation(mDecorToolbar.getViewGroup(), mMenuInvalidator);
        return true;
    }

    public boolean isShowing()
    {
        return mDecorToolbar.getVisibility() == 0;
    }

    public boolean isTitleTruncated()
    {
        return super.isTitleTruncated();
    }

    public ActionBar.Tab newTab()
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
    }

    void onDestroy()
    {
        mDecorToolbar.getViewGroup().removeCallbacks(mMenuInvalidator);
    }

    public boolean onKeyShortcut(int i, KeyEvent keyevent)
    {
        boolean flag = false;
        Menu menu = getMenu();
        if(menu != null)
        {
            int j;
            if(keyevent != null)
                j = keyevent.getDeviceId();
            else
                j = -1;
            if(KeyCharacterMap.load(j).getKeyboardType() != 1)
                flag = true;
            else
                flag = false;
            menu.setQwertyMode(flag);
            flag = menu.performShortcut(i, keyevent, 0);
        }
        return flag;
    }

    public boolean onMenuKeyEvent(KeyEvent keyevent)
    {
        if(keyevent.getAction() == 1)
            openOptionsMenu();
        return true;
    }

    public boolean openOptionsMenu()
    {
        return mDecorToolbar.showOverflowMenu();
    }

    void populateOptionsMenu()
    {
        MenuBuilder menubuilder;
        Menu menu;
        menubuilder = null;
        menu = getMenu();
        if(menu instanceof MenuBuilder)
            menubuilder = (MenuBuilder)menu;
        if(menubuilder != null)
            menubuilder.stopDispatchingItemsChanged();
        menu.clear();
        if(!mWindowCallback.onCreatePanelMenu(0, menu) || !mWindowCallback.onPreparePanel(0, null, menu))
            menu.clear();
        if(menubuilder != null)
            menubuilder.startDispatchingItemsChanged();
        return;
        Exception exception;
        exception;
        if(menubuilder != null)
            menubuilder.startDispatchingItemsChanged();
        throw exception;
    }

    public void removeAllTabs()
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onmenuvisibilitylistener)
    {
        mMenuVisibilityListeners.remove(onmenuvisibilitylistener);
    }

    public void removeTab(ActionBar.Tab tab)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void removeTabAt(int i)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public boolean requestFocus()
    {
        ViewGroup viewgroup = mDecorToolbar.getViewGroup();
        if(viewgroup != null && !viewgroup.hasFocus())
        {
            viewgroup.requestFocus();
            return true;
        } else
        {
            return false;
        }
    }

    public void selectTab(ActionBar.Tab tab)
    {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        mDecorToolbar.setBackgroundDrawable(drawable);
    }

    public void setCustomView(int i)
    {
        setCustomView(LayoutInflater.from(mDecorToolbar.getContext()).inflate(i, mDecorToolbar.getViewGroup(), false));
    }

    public void setCustomView(View view)
    {
        setCustomView(view, new ActionBar.LayoutParams(-2, -2));
    }

    public void setCustomView(View view, ActionBar.LayoutParams layoutparams)
    {
        if(view != null)
            view.setLayoutParams(layoutparams);
        mDecorToolbar.setCustomView(view);
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean flag)
    {
    }

    public void setDisplayHomeAsUpEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 4;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 4);
    }

    public void setDisplayOptions(int i)
    {
        setDisplayOptions(i, -1);
    }

    public void setDisplayOptions(int i, int j)
    {
        int k = mDecorToolbar.getDisplayOptions();
        mDecorToolbar.setDisplayOptions(i & j | ~j & k);
    }

    public void setDisplayShowCustomEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 16;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 16);
    }

    public void setDisplayShowHomeEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 2);
    }

    public void setDisplayShowTitleEnabled(boolean flag)
    {
        byte byte0;
        if(flag)
            byte0 = 8;
        else
            byte0 = 0;
        setDisplayOptions(byte0, 8);
    }

    public void setDisplayUseLogoEnabled(boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        setDisplayOptions(i, 1);
    }

    public void setElevation(float f)
    {
        ViewCompat.setElevation(mDecorToolbar.getViewGroup(), f);
    }

    public void setHomeActionContentDescription(int i)
    {
        mDecorToolbar.setNavigationContentDescription(i);
    }

    public void setHomeActionContentDescription(CharSequence charsequence)
    {
        mDecorToolbar.setNavigationContentDescription(charsequence);
    }

    public void setHomeAsUpIndicator(int i)
    {
        mDecorToolbar.setNavigationIcon(i);
    }

    public void setHomeAsUpIndicator(Drawable drawable)
    {
        mDecorToolbar.setNavigationIcon(drawable);
    }

    public void setHomeButtonEnabled(boolean flag)
    {
    }

    public void setIcon(int i)
    {
        mDecorToolbar.setIcon(i);
    }

    public void setIcon(Drawable drawable)
    {
        mDecorToolbar.setIcon(drawable);
    }

    public void setListNavigationCallbacks(SpinnerAdapter spinneradapter, ActionBar.OnNavigationListener onnavigationlistener)
    {
        mDecorToolbar.setDropdownParams(spinneradapter, new NavItemSelectedListener(onnavigationlistener));
    }

    public void setLogo(int i)
    {
        mDecorToolbar.setLogo(i);
    }

    public void setLogo(Drawable drawable)
    {
        mDecorToolbar.setLogo(drawable);
    }

    public void setNavigationMode(int i)
    {
        if(i == 2)
        {
            throw new IllegalArgumentException("Tabs not supported in this configuration");
        } else
        {
            mDecorToolbar.setNavigationMode(i);
            return;
        }
    }

    public void setSelectedNavigationItem(int i)
    {
        switch(mDecorToolbar.getNavigationMode())
        {
        default:
            throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");

        case 1: // '\001'
            mDecorToolbar.setDropdownSelectedPosition(i);
            break;
        }
    }

    public void setShowHideAnimationEnabled(boolean flag)
    {
    }

    public void setSplitBackgroundDrawable(Drawable drawable)
    {
    }

    public void setStackedBackgroundDrawable(Drawable drawable)
    {
    }

    public void setSubtitle(int i)
    {
        DecorToolbar decortoolbar = mDecorToolbar;
        CharSequence charsequence;
        if(i != 0)
            charsequence = mDecorToolbar.getContext().getText(i);
        else
            charsequence = null;
        decortoolbar.setSubtitle(charsequence);
    }

    public void setSubtitle(CharSequence charsequence)
    {
        mDecorToolbar.setSubtitle(charsequence);
    }

    public void setTitle(int i)
    {
        DecorToolbar decortoolbar = mDecorToolbar;
        CharSequence charsequence;
        if(i != 0)
            charsequence = mDecorToolbar.getContext().getText(i);
        else
            charsequence = null;
        decortoolbar.setTitle(charsequence);
    }

    public void setTitle(CharSequence charsequence)
    {
        mDecorToolbar.setTitle(charsequence);
    }

    public void setWindowTitle(CharSequence charsequence)
    {
        mDecorToolbar.setWindowTitle(charsequence);
    }

    public void show()
    {
        mDecorToolbar.setVisibility(0);
    }

    DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private ListMenuPresenter mListMenuPresenter;
    private boolean mMenuCallbackSet;
    private final android.support.v7.widget.Toolbar.OnMenuItemClickListener mMenuClicker = new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {

        public boolean onMenuItemClick(MenuItem menuitem)
        {
            return mWindowCallback.onMenuItemSelected(0, menuitem);
        }

        final ToolbarActionBar this$0;

            
            {
                this$0 = ToolbarActionBar.this;
                super();
            }
    }
;
    private final Runnable mMenuInvalidator = new Runnable() {

        public void run()
        {
            populateOptionsMenu();
        }

        final ToolbarActionBar this$0;

            
            {
                this$0 = ToolbarActionBar.this;
                super();
            }
    }
;
    private ArrayList mMenuVisibilityListeners;
    boolean mToolbarMenuPrepared;
    android.view.Window.Callback mWindowCallback;
}
