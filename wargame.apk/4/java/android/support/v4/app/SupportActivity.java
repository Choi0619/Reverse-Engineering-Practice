// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.*;
import android.os.Bundle;
import android.support.v4.util.SimpleArrayMap;

public class SupportActivity extends Activity
    implements LifecycleOwner
{
    public static class ExtraData
    {

        public ExtraData()
        {
        }
    }


    public SupportActivity()
    {
        mExtraDataMap = new SimpleArrayMap();
        mLifecycleRegistry = new LifecycleRegistry(this);
    }

    public ExtraData getExtraData(Class class1)
    {
        return (ExtraData)mExtraDataMap.get(class1);
    }

    public Lifecycle getLifecycle()
    {
        return mLifecycleRegistry;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        ReportFragment.injectIfNeededIn(this);
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        mLifecycleRegistry.markState(android.arch.lifecycle.Lifecycle.State.CREATED);
        super.onSaveInstanceState(bundle);
    }

    public void putExtraData(ExtraData extradata)
    {
        mExtraDataMap.put(extradata.getClass(), extradata);
    }

    private SimpleArrayMap mExtraDataMap;
    private LifecycleRegistry mLifecycleRegistry;
}
