// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.arch.lifecycle;

import android.app.*;
import android.os.Bundle;

// Referenced classes of package android.arch.lifecycle:
//            LifecycleRegistryOwner, LifecycleRegistry, LifecycleOwner

public class ReportFragment extends Fragment
{
    static interface ActivityInitializationListener
    {

        public abstract void onCreate();

        public abstract void onResume();

        public abstract void onStart();
    }


    public ReportFragment()
    {
    }

    private void dispatch(Lifecycle.Event event)
    {
        Object obj = getActivity();
        if(obj instanceof LifecycleRegistryOwner)
            ((LifecycleRegistryOwner)obj).getLifecycle().handleLifecycleEvent(event);
        else
        if(obj instanceof LifecycleOwner)
        {
            obj = ((LifecycleOwner)obj).getLifecycle();
            if(obj instanceof LifecycleRegistry)
            {
                ((LifecycleRegistry)obj).handleLifecycleEvent(event);
                return;
            }
        }
    }

    private void dispatchCreate(ActivityInitializationListener activityinitializationlistener)
    {
        if(activityinitializationlistener != null)
            activityinitializationlistener.onCreate();
    }

    private void dispatchResume(ActivityInitializationListener activityinitializationlistener)
    {
        if(activityinitializationlistener != null)
            activityinitializationlistener.onResume();
    }

    private void dispatchStart(ActivityInitializationListener activityinitializationlistener)
    {
        if(activityinitializationlistener != null)
            activityinitializationlistener.onStart();
    }

    static ReportFragment get(Activity activity)
    {
        return (ReportFragment)activity.getFragmentManager().findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag");
    }

    public static void injectIfNeededIn(Activity activity)
    {
        activity = activity.getFragmentManager();
        if(activity.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null)
        {
            activity.beginTransaction().add(new ReportFragment(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            activity.executePendingTransactions();
        }
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        dispatchCreate(mProcessListener);
        dispatch(Lifecycle.Event.ON_CREATE);
    }

    public void onDestroy()
    {
        super.onDestroy();
        dispatch(Lifecycle.Event.ON_DESTROY);
        mProcessListener = null;
    }

    public void onPause()
    {
        super.onPause();
        dispatch(Lifecycle.Event.ON_PAUSE);
    }

    public void onResume()
    {
        super.onResume();
        dispatchResume(mProcessListener);
        dispatch(Lifecycle.Event.ON_RESUME);
    }

    public void onStart()
    {
        super.onStart();
        dispatchStart(mProcessListener);
        dispatch(Lifecycle.Event.ON_START);
    }

    public void onStop()
    {
        super.onStop();
        dispatch(Lifecycle.Event.ON_STOP);
    }

    void setProcessListener(ActivityInitializationListener activityinitializationlistener)
    {
        mProcessListener = activityinitializationlistener;
    }

    private static final String REPORT_FRAGMENT_TAG = "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private ActivityInitializationListener mProcessListener;
}
