// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            LifecycleOwner, LifecycleRegistry

public interface LifecycleRegistryOwner
    extends LifecycleOwner
{

    public abstract LifecycleRegistry getLifecycle();
}
