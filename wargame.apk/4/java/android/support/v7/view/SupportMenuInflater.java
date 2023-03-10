// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.*;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.support.v7.widget.DrawableUtils;
import android.util.*;
import android.view.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SupportMenuInflater extends MenuInflater
{
    private static class InflatedOnMenuItemClickListener
        implements android.view.MenuItem.OnMenuItemClickListener
    {

        public boolean onMenuItemClick(MenuItem menuitem)
        {
            try
            {
                if(mMethod.getReturnType() == Boolean.TYPE)
                    return ((Boolean)mMethod.invoke(mRealOwner, new Object[] {
                        menuitem
                    })).booleanValue();
                mMethod.invoke(mRealOwner, new Object[] {
                    menuitem
                });
            }
            // Misplaced declaration of an exception variable
            catch(MenuItem menuitem)
            {
                throw new RuntimeException(menuitem);
            }
            return true;
        }

        private static final Class PARAM_TYPES[] = {
            android/view/MenuItem
        };
        private Method mMethod;
        private Object mRealOwner;


        public InflatedOnMenuItemClickListener(Object obj, String s)
        {
            mRealOwner = obj;
            Class class1 = obj.getClass();
            try
            {
                mMethod = class1.getMethod(s, PARAM_TYPES);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                s = new InflateException((new StringBuilder()).append("Couldn't resolve menu item onClick handler ").append(s).append(" in class ").append(class1.getName()).toString());
            }
            s.initCause(((Throwable) (obj)));
            throw s;
        }
    }

    private class MenuState
    {

        private char getShortcut(String s)
        {
            if(s == null)
                return '\0';
            else
                return s.charAt(0);
        }

        private Object newInstance(String s, Class aclass[], Object aobj[])
        {
            try
            {
                aclass = mContext.getClassLoader().loadClass(s).getConstructor(aclass);
                aclass.setAccessible(true);
                aclass = ((Class []) (aclass.newInstance(aobj)));
            }
            // Misplaced declaration of an exception variable
            catch(Class aclass[])
            {
                Log.w("SupportMenuInflater", (new StringBuilder()).append("Cannot instantiate class: ").append(s).toString(), aclass);
                return null;
            }
            return aclass;
        }

        private void setItem(MenuItem menuitem)
        {
            Object obj = menuitem.setChecked(itemChecked).setVisible(itemVisible).setEnabled(itemEnabled);
            boolean flag1;
            if(itemCheckable >= 1)
                flag1 = true;
            else
                flag1 = false;
            ((MenuItem) (obj)).setCheckable(flag1).setTitleCondensed(itemTitleCondensed).setIcon(itemIconResId);
            if(itemShowAsAction >= 0)
                menuitem.setShowAsAction(itemShowAsAction);
            if(itemListenerMethodName != null)
            {
                if(mContext.isRestricted())
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                menuitem.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(getRealOwner(), itemListenerMethodName));
            }
            if(menuitem instanceof MenuItemImpl)
                obj = (MenuItemImpl)menuitem;
            boolean flag;
            if(itemCheckable >= 2)
                if(menuitem instanceof MenuItemImpl)
                    ((MenuItemImpl)menuitem).setExclusiveCheckable(true);
                else
                if(menuitem instanceof MenuItemWrapperICS)
                    ((MenuItemWrapperICS)menuitem).setExclusiveCheckable(true);
            flag = false;
            if(itemActionViewClassName != null)
            {
                menuitem.setActionView((View)newInstance(itemActionViewClassName, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, mActionViewConstructorArguments));
                flag = true;
            }
            if(itemActionViewLayout > 0)
                if(!flag)
                    menuitem.setActionView(itemActionViewLayout);
                else
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            if(itemActionProvider != null)
                MenuItemCompat.setActionProvider(menuitem, itemActionProvider);
            MenuItemCompat.setContentDescription(menuitem, itemContentDescription);
            MenuItemCompat.setTooltipText(menuitem, itemTooltipText);
            MenuItemCompat.setAlphabeticShortcut(menuitem, itemAlphabeticShortcut, itemAlphabeticModifiers);
            MenuItemCompat.setNumericShortcut(menuitem, itemNumericShortcut, itemNumericModifiers);
            if(itemIconTintMode != null)
                MenuItemCompat.setIconTintMode(menuitem, itemIconTintMode);
            if(itemIconTintList != null)
                MenuItemCompat.setIconTintList(menuitem, itemIconTintList);
        }

        public void addItem()
        {
            itemAdded = true;
            setItem(menu.add(groupId, itemId, itemCategoryOrder, itemTitle));
        }

        public SubMenu addSubMenuItem()
        {
            itemAdded = true;
            SubMenu submenu = menu.addSubMenu(groupId, itemId, itemCategoryOrder, itemTitle);
            setItem(submenu.getItem());
            return submenu;
        }

        public boolean hasAddedItem()
        {
            return itemAdded;
        }

        public void readGroup(AttributeSet attributeset)
        {
            attributeset = mContext.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.MenuGroup);
            groupId = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.MenuGroup_android_id, 0);
            groupCategory = attributeset.getInt(android.support.v7.appcompat.R.styleable.MenuGroup_android_menuCategory, 0);
            groupOrder = attributeset.getInt(android.support.v7.appcompat.R.styleable.MenuGroup_android_orderInCategory, 0);
            groupCheckable = attributeset.getInt(android.support.v7.appcompat.R.styleable.MenuGroup_android_checkableBehavior, 0);
            groupVisible = attributeset.getBoolean(android.support.v7.appcompat.R.styleable.MenuGroup_android_visible, true);
            groupEnabled = attributeset.getBoolean(android.support.v7.appcompat.R.styleable.MenuGroup_android_enabled, true);
            attributeset.recycle();
        }

        public void readItem(AttributeSet attributeset)
        {
            attributeset = mContext.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.MenuItem);
            itemId = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.MenuItem_android_id, 0);
            itemCategoryOrder = 0xffff0000 & attributeset.getInt(android.support.v7.appcompat.R.styleable.MenuItem_android_menuCategory, groupCategory) | 0xffff & attributeset.getInt(android.support.v7.appcompat.R.styleable.MenuItem_android_orderInCategory, groupOrder);
            itemTitle = attributeset.getText(android.support.v7.appcompat.R.styleable.MenuItem_android_title);
            itemTitleCondensed = attributeset.getText(android.support.v7.appcompat.R.styleable.MenuItem_android_titleCondensed);
            itemIconResId = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.MenuItem_android_icon, 0);
            itemAlphabeticShortcut = getShortcut(attributeset.getString(android.support.v7.appcompat.R.styleable.MenuItem_android_alphabeticShortcut));
            itemAlphabeticModifiers = attributeset.getInt(android.support.v7.appcompat.R.styleable.MenuItem_alphabeticModifiers, 4096);
            itemNumericShortcut = getShortcut(attributeset.getString(android.support.v7.appcompat.R.styleable.MenuItem_android_numericShortcut));
            itemNumericModifiers = attributeset.getInt(android.support.v7.appcompat.R.styleable.MenuItem_numericModifiers, 4096);
            int i;
            if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.MenuItem_android_checkable))
            {
                if(attributeset.getBoolean(android.support.v7.appcompat.R.styleable.MenuItem_android_checkable, false))
                    i = 1;
                else
                    i = 0;
                itemCheckable = i;
            } else
            {
                itemCheckable = groupCheckable;
            }
            itemChecked = attributeset.getBoolean(android.support.v7.appcompat.R.styleable.MenuItem_android_checked, false);
            itemVisible = attributeset.getBoolean(android.support.v7.appcompat.R.styleable.MenuItem_android_visible, groupVisible);
            itemEnabled = attributeset.getBoolean(android.support.v7.appcompat.R.styleable.MenuItem_android_enabled, groupEnabled);
            itemShowAsAction = attributeset.getInt(android.support.v7.appcompat.R.styleable.MenuItem_showAsAction, -1);
            itemListenerMethodName = attributeset.getString(android.support.v7.appcompat.R.styleable.MenuItem_android_onClick);
            itemActionViewLayout = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.MenuItem_actionLayout, 0);
            itemActionViewClassName = attributeset.getString(android.support.v7.appcompat.R.styleable.MenuItem_actionViewClass);
            itemActionProviderClassName = attributeset.getString(android.support.v7.appcompat.R.styleable.MenuItem_actionProviderClass);
            if(itemActionProviderClassName != null)
                i = 1;
            else
                i = 0;
            if(i != 0 && itemActionViewLayout == 0 && itemActionViewClassName == null)
            {
                itemActionProvider = (ActionProvider)newInstance(itemActionProviderClassName, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, mActionProviderConstructorArguments);
            } else
            {
                if(i != 0)
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                itemActionProvider = null;
            }
            itemContentDescription = attributeset.getText(android.support.v7.appcompat.R.styleable.MenuItem_contentDescription);
            itemTooltipText = attributeset.getText(android.support.v7.appcompat.R.styleable.MenuItem_tooltipText);
            if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.MenuItem_iconTintMode))
                itemIconTintMode = DrawableUtils.parseTintMode(attributeset.getInt(android.support.v7.appcompat.R.styleable.MenuItem_iconTintMode, -1), itemIconTintMode);
            else
                itemIconTintMode = null;
            if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.MenuItem_iconTint))
                itemIconTintList = attributeset.getColorStateList(android.support.v7.appcompat.R.styleable.MenuItem_iconTint);
            else
                itemIconTintList = null;
            attributeset.recycle();
            itemAdded = false;
        }

        public void resetGroup()
        {
            groupId = 0;
            groupCategory = 0;
            groupOrder = 0;
            groupCheckable = 0;
            groupVisible = true;
            groupEnabled = true;
        }

        private static final int defaultGroupId = 0;
        private static final int defaultItemCategory = 0;
        private static final int defaultItemCheckable = 0;
        private static final boolean defaultItemChecked = false;
        private static final boolean defaultItemEnabled = true;
        private static final int defaultItemId = 0;
        private static final int defaultItemOrder = 0;
        private static final boolean defaultItemVisible = true;
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private int itemAlphabeticModifiers;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private CharSequence itemContentDescription;
        private boolean itemEnabled;
        private int itemIconResId;
        private ColorStateList itemIconTintList;
        private android.graphics.PorterDuff.Mode itemIconTintMode;
        private int itemId;
        private String itemListenerMethodName;
        private int itemNumericModifiers;
        private char itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private CharSequence itemTooltipText;
        private boolean itemVisible;
        private Menu menu;
        final SupportMenuInflater this$0;

        public MenuState(Menu menu1)
        {
            this$0 = SupportMenuInflater.this;
            super();
            itemIconTintList = null;
            itemIconTintMode = null;
            menu = menu1;
            resetGroup();
        }
    }


    public SupportMenuInflater(Context context)
    {
        super(context);
        mContext = context;
        mActionViewConstructorArguments = (new Object[] {
            context
        });
        mActionProviderConstructorArguments = mActionViewConstructorArguments;
    }

    private Object findRealOwner(Object obj)
    {
        while((obj instanceof Activity) || !(obj instanceof ContextWrapper)) 
            return obj;
        return findRealOwner(((ContextWrapper)obj).getBaseContext());
    }

    private void parseMenu(XmlPullParser xmlpullparser, AttributeSet attributeset, Menu menu)
        throws XmlPullParserException, IOException
    {
        int i;
        boolean flag;
        Menu menu1;
        MenuState menustate;
        menustate = new MenuState(menu);
        i = xmlpullparser.getEventType();
        flag = false;
        menu1 = null;
_L12:
        if(i != 2) goto _L2; else goto _L1
_L1:
        menu = xmlpullparser.getName();
        if(!menu.equals("menu")) goto _L4; else goto _L3
_L3:
        i = xmlpullparser.next();
_L13:
        int j;
        int l;
        j = 0;
        l = i;
_L10:
        if(j != 0)
            break MISSING_BLOCK_LABEL_501;
        l;
        JVM INSTR tableswitch 1 3: default 96
    //                   1 491
    //                   2 181
    //                   3 306;
           goto _L5 _L6 _L7 _L8
_L7:
        break; /* Loop/switch isn't completed */
_L5:
        int k;
        menu = menu1;
        k = j;
        i = ((flag) ? 1 : 0);
_L14:
        l = xmlpullparser.next();
        flag = i;
        j = k;
        menu1 = menu;
        if(true) goto _L10; else goto _L9
_L4:
        throw new RuntimeException((new StringBuilder()).append("Expecting menu, got ").append(menu).toString());
_L2:
        j = xmlpullparser.next();
        i = j;
        if(j != 1) goto _L12; else goto _L11
_L11:
        i = j;
          goto _L13
_L9:
        i = ((flag) ? 1 : 0);
        k = j;
        menu = menu1;
        if(!flag)
        {
            menu = xmlpullparser.getName();
            if(menu.equals("group"))
            {
                menustate.readGroup(attributeset);
                i = ((flag) ? 1 : 0);
                k = j;
                menu = menu1;
            } else
            if(menu.equals("item"))
            {
                menustate.readItem(attributeset);
                i = ((flag) ? 1 : 0);
                k = j;
                menu = menu1;
            } else
            if(menu.equals("menu"))
            {
                parseMenu(xmlpullparser, attributeset, ((Menu) (menustate.addSubMenuItem())));
                i = ((flag) ? 1 : 0);
                k = j;
                menu = menu1;
            } else
            {
                i = 1;
                k = j;
            }
        }
          goto _L14
_L8:
        String s = xmlpullparser.getName();
        if(flag && s.equals(menu1))
        {
            i = 0;
            menu = null;
            k = j;
        } else
        if(s.equals("group"))
        {
            menustate.resetGroup();
            i = ((flag) ? 1 : 0);
            k = j;
            menu = menu1;
        } else
        if(s.equals("item"))
        {
            i = ((flag) ? 1 : 0);
            k = j;
            menu = menu1;
            if(!menustate.hasAddedItem())
                if(menustate.itemActionProvider != null && menustate.itemActionProvider.hasSubMenu())
                {
                    menustate.addSubMenuItem();
                    i = ((flag) ? 1 : 0);
                    k = j;
                    menu = menu1;
                } else
                {
                    menustate.addItem();
                    i = ((flag) ? 1 : 0);
                    k = j;
                    menu = menu1;
                }
        } else
        {
            i = ((flag) ? 1 : 0);
            k = j;
            menu = menu1;
            if(s.equals("menu"))
            {
                k = 1;
                i = ((flag) ? 1 : 0);
                menu = menu1;
            }
        }
          goto _L14
_L6:
        throw new RuntimeException("Unexpected end of document");
          goto _L13
    }

    Object getRealOwner()
    {
        if(mRealOwner == null)
            mRealOwner = findRealOwner(mContext);
        return mRealOwner;
    }

    public void inflate(int i, Menu menu)
    {
        if(menu instanceof SupportMenu) goto _L2; else goto _L1
_L1:
        super.inflate(i, menu);
_L4:
        return;
_L2:
        XmlResourceParser xmlresourceparser;
        XmlResourceParser xmlresourceparser1;
        XmlResourceParser xmlresourceparser2;
        xmlresourceparser = null;
        xmlresourceparser2 = null;
        xmlresourceparser1 = null;
        XmlResourceParser xmlresourceparser3 = mContext.getResources().getLayout(i);
        xmlresourceparser1 = xmlresourceparser3;
        xmlresourceparser = xmlresourceparser3;
        xmlresourceparser2 = xmlresourceparser3;
        parseMenu(xmlresourceparser3, Xml.asAttributeSet(xmlresourceparser3), menu);
        if(xmlresourceparser3 != null)
        {
            xmlresourceparser3.close();
            return;
        }
        if(true) goto _L4; else goto _L3
_L3:
        menu;
        xmlresourceparser = xmlresourceparser1;
        throw new InflateException("Error inflating menu XML", menu);
        menu;
        if(xmlresourceparser != null)
            xmlresourceparser.close();
        throw menu;
        menu;
        xmlresourceparser = xmlresourceparser2;
        throw new InflateException("Error inflating menu XML", menu);
    }

    static final Class ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE[] = ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    static final Class ACTION_VIEW_CONSTRUCTOR_SIGNATURE[] = {
        android/content/Context
    };
    static final String LOG_TAG = "SupportMenuInflater";
    static final int NO_ID = 0;
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    final Object mActionProviderConstructorArguments[];
    final Object mActionViewConstructorArguments[];
    Context mContext;
    private Object mRealOwner;

}
