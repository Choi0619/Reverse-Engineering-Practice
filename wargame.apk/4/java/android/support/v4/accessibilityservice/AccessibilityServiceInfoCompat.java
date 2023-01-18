// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public final class AccessibilityServiceInfoCompat
{
    static class AccessibilityServiceInfoApi16Impl extends AccessibilityServiceInfoBaseImpl
    {

        public String loadDescription(AccessibilityServiceInfo accessibilityserviceinfo, PackageManager packagemanager)
        {
            return accessibilityserviceinfo.loadDescription(packagemanager);
        }

        AccessibilityServiceInfoApi16Impl()
        {
        }
    }

    static class AccessibilityServiceInfoApi18Impl extends AccessibilityServiceInfoApi16Impl
    {

        public int getCapabilities(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return accessibilityserviceinfo.getCapabilities();
        }

        AccessibilityServiceInfoApi18Impl()
        {
        }
    }

    static class AccessibilityServiceInfoBaseImpl
    {

        public int getCapabilities(AccessibilityServiceInfo accessibilityserviceinfo)
        {
            return !AccessibilityServiceInfoCompat.getCanRetrieveWindowContent(accessibilityserviceinfo) ? 0 : 1;
        }

        public String loadDescription(AccessibilityServiceInfo accessibilityserviceinfo, PackageManager packagemanager)
        {
            return null;
        }

        AccessibilityServiceInfoBaseImpl()
        {
        }
    }


    private AccessibilityServiceInfoCompat()
    {
    }

    public static String capabilityToString(int i)
    {
        switch(i)
        {
        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            return "UNKNOWN";

        case 1: // '\001'
            return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";

        case 2: // '\002'
            return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";

        case 4: // '\004'
            return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";

        case 8: // '\b'
            return "CAPABILITY_CAN_FILTER_KEY_EVENTS";
        }
    }

    public static String feedbackTypeToString(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        do
            if(i > 0)
            {
                int j = 1 << Integer.numberOfTrailingZeros(i);
                i &= ~j;
                if(stringbuilder.length() > 1)
                    stringbuilder.append(", ");
                switch(j)
                {
                case 1: // '\001'
                    stringbuilder.append("FEEDBACK_SPOKEN");
                    break;

                case 4: // '\004'
                    stringbuilder.append("FEEDBACK_AUDIBLE");
                    break;

                case 2: // '\002'
                    stringbuilder.append("FEEDBACK_HAPTIC");
                    break;

                case 16: // '\020'
                    stringbuilder.append("FEEDBACK_GENERIC");
                    break;

                case 8: // '\b'
                    stringbuilder.append("FEEDBACK_VISUAL");
                    break;
                }
            } else
            {
                stringbuilder.append("]");
                return stringbuilder.toString();
            }
        while(true);
    }

    public static String flagToString(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 1: // '\001'
            return "DEFAULT";

        case 2: // '\002'
            return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";

        case 4: // '\004'
            return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";

        case 8: // '\b'
            return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";

        case 16: // '\020'
            return "FLAG_REPORT_VIEW_IDS";

        case 32: // ' '
            return "FLAG_REQUEST_FILTER_KEY_EVENTS";
        }
    }

    public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return accessibilityserviceinfo.getCanRetrieveWindowContent();
    }

    public static int getCapabilities(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return IMPL.getCapabilities(accessibilityserviceinfo);
    }

    public static String getDescription(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return accessibilityserviceinfo.getDescription();
    }

    public static String getId(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return accessibilityserviceinfo.getId();
    }

    public static ResolveInfo getResolveInfo(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return accessibilityserviceinfo.getResolveInfo();
    }

    public static String getSettingsActivityName(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        return accessibilityserviceinfo.getSettingsActivityName();
    }

    public static String loadDescription(AccessibilityServiceInfo accessibilityserviceinfo, PackageManager packagemanager)
    {
        return IMPL.loadDescription(accessibilityserviceinfo, packagemanager);
    }

    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int DEFAULT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
    private static final AccessibilityServiceInfoBaseImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 18)
            IMPL = new AccessibilityServiceInfoApi18Impl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new AccessibilityServiceInfoApi16Impl();
        else
            IMPL = new AccessibilityServiceInfoBaseImpl();
    }
}
