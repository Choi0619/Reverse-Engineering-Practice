// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.view.*;

// Referenced classes of package android.support.v7.app:
//            AppCompatCallback, AppCompatDelegate, ActionBar

public class AppCompatActivity extends FragmentActivity
    implements AppCompatCallback, android.support.v4.app.TaskStackBuilder.SupportParentable, ActionBarDrawerToggle.DelegateProvider
{

    public AppCompatActivity()
    {
        mThemeId = 0;
    }

    private boolean performMenuItemShortcut(int i, KeyEvent keyevent)
    {
        if(android.os.Build.VERSION.SDK_INT < 26 && !keyevent.isCtrlPressed() && !KeyEvent.metaStateHasNoModifiers(keyevent.getMetaState()) && keyevent.getRepeatCount() == 0 && !KeyEvent.isModifierKey(keyevent.getKeyCode()))
        {
            Window window = getWindow();
            if(window != null && window.getDecorView() != null && window.getDecorView().dispatchKeyShortcutEvent(keyevent))
                return true;
        }
        return false;
    }

    public void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        getDelegate().addContentView(view, layoutparams);
    }

    public void closeOptionsMenu()
    {
        ActionBar actionbar = getSupportActionBar();
        if(getWindow().hasFeature(0) && (actionbar == null || !actionbar.closeOptionsMenu()))
            super.closeOptionsMenu();
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        int i = keyevent.getKeyCode();
        ActionBar actionbar = getSupportActionBar();
        if(i == 82 && actionbar != null && actionbar.onMenuKeyEvent(keyevent))
            return true;
        else
            return super.dispatchKeyEvent(keyevent);
    }

    public View findViewById(int i)
    {
        return getDelegate().findViewById(i);
    }

    public AppCompatDelegate getDelegate()
    {
        if(mDelegate == null)
            mDelegate = AppCompatDelegate.create(this, this);
        return mDelegate;
    }

    public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate()
    {
        return getDelegate().getDrawerToggleDelegate();
    }

    public MenuInflater getMenuInflater()
    {
        return getDelegate().getMenuInflater();
    }

    public Resources getResources()
    {
        if(mResources == null && VectorEnabledTintResources.shouldBeUsed())
            mResources = new VectorEnabledTintResources(this, super.getResources());
        if(mResources == null)
            return super.getResources();
        else
            return mResources;
    }

    public ActionBar getSupportActionBar()
    {
        return getDelegate().getSupportActionBar();
    }

    public Intent getSupportParentActivityIntent()
    {
        return NavUtils.getParentActivityIntent(this);
    }

    public void invalidateOptionsMenu()
    {
        getDelegate().invalidateOptionsMenu();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        getDelegate().onConfigurationChanged(configuration);
        if(mResources != null)
        {
            android.util.DisplayMetrics displaymetrics = super.getResources().getDisplayMetrics();
            mResources.updateConfiguration(configuration, displaymetrics);
        }
    }

    public void onContentChanged()
    {
        onSupportContentChanged();
    }

    protected void onCreate(Bundle bundle)
    {
        AppCompatDelegate appcompatdelegate = getDelegate();
        appcompatdelegate.installViewFactory();
        appcompatdelegate.onCreate(bundle);
        if(appcompatdelegate.applyDayNight() && mThemeId != 0)
            if(android.os.Build.VERSION.SDK_INT >= 23)
                onApplyThemeResource(getTheme(), mThemeId, false);
            else
                setTheme(mThemeId);
        super.onCreate(bundle);
    }

    public void onCreateSupportNavigateUpTaskStack(TaskStackBuilder taskstackbuilder)
    {
        taskstackbuilder.addParentStack(this);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(performMenuItemShortcut(i, keyevent))
            return true;
        else
            return super.onKeyDown(i, keyevent);
    }

    public final boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
        if(super.onMenuItemSelected(i, menuitem))
            return true;
        ActionBar actionbar = getSupportActionBar();
        if(menuitem.getItemId() == 0x102002c && actionbar != null && (actionbar.getDisplayOptions() & 4) != 0)
            return onSupportNavigateUp();
        else
            return false;
    }

    public boolean onMenuOpened(int i, Menu menu)
    {
        return super.onMenuOpened(i, menu);
    }

    public void onPanelClosed(int i, Menu menu)
    {
        super.onPanelClosed(i, menu);
    }

    protected void onPostCreate(Bundle bundle)
    {
        super.onPostCreate(bundle);
        getDelegate().onPostCreate(bundle);
    }

    protected void onPostResume()
    {
        super.onPostResume();
        getDelegate().onPostResume();
    }

    public void onPrepareSupportNavigateUpTaskStack(TaskStackBuilder taskstackbuilder)
    {
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        getDelegate().onSaveInstanceState(bundle);
    }

    protected void onStart()
    {
        super.onStart();
        getDelegate().onStart();
    }

    protected void onStop()
    {
        super.onStop();
        getDelegate().onStop();
    }

    public void onSupportActionModeFinished(ActionMode actionmode)
    {
    }

    public void onSupportActionModeStarted(ActionMode actionmode)
    {
    }

    public void onSupportContentChanged()
    {
    }

    public boolean onSupportNavigateUp()
    {
        Object obj = getSupportParentActivityIntent();
        if(obj != null)
        {
            if(supportShouldUpRecreateTask(((Intent) (obj))))
            {
                obj = TaskStackBuilder.create(this);
                onCreateSupportNavigateUpTaskStack(((TaskStackBuilder) (obj)));
                onPrepareSupportNavigateUpTaskStack(((TaskStackBuilder) (obj)));
                ((TaskStackBuilder) (obj)).startActivities();
                try
                {
                    ActivityCompat.finishAffinity(this);
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    finish();
                }
            } else
            {
                supportNavigateUpTo(((Intent) (obj)));
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void onTitleChanged(CharSequence charsequence, int i)
    {
        super.onTitleChanged(charsequence, i);
        getDelegate().setTitle(charsequence);
    }

    public ActionMode onWindowStartingSupportActionMode(android.support.v7.view.ActionMode.Callback callback)
    {
        return null;
    }

    public void openOptionsMenu()
    {
        ActionBar actionbar = getSupportActionBar();
        if(getWindow().hasFeature(0) && (actionbar == null || !actionbar.openOptionsMenu()))
            super.openOptionsMenu();
    }

    public void setContentView(int i)
    {
        getDelegate().setContentView(i);
    }

    public void setContentView(View view)
    {
        getDelegate().setContentView(view);
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        getDelegate().setContentView(view, layoutparams);
    }

    public void setSupportActionBar(Toolbar toolbar)
    {
        getDelegate().setSupportActionBar(toolbar);
    }

    public void setSupportProgress(int i)
    {
    }

    public void setSupportProgressBarIndeterminate(boolean flag)
    {
    }

    public void setSupportProgressBarIndeterminateVisibility(boolean flag)
    {
    }

    public void setSupportProgressBarVisibility(boolean flag)
    {
    }

    public void setTheme(int i)
    {
        super.setTheme(i);
        mThemeId = i;
    }

    public ActionMode startSupportActionMode(android.support.v7.view.ActionMode.Callback callback)
    {
        return getDelegate().startSupportActionMode(callback);
    }

    public void supportInvalidateOptionsMenu()
    {
        getDelegate().invalidateOptionsMenu();
    }

    public void supportNavigateUpTo(Intent intent)
    {
        NavUtils.navigateUpTo(this, intent);
    }

    public boolean supportRequestWindowFeature(int i)
    {
        return getDelegate().requestWindowFeature(i);
    }

    public boolean supportShouldUpRecreateTask(Intent intent)
    {
        return NavUtils.shouldUpRecreateTask(this, intent);
    }

    private AppCompatDelegate mDelegate;
    private Resources mResources;
    private int mThemeId;
}
